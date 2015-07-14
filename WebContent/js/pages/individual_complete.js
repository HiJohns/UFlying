$(document).ready(function () {
	function updateStatus() {
		model.status = $('#toAuth').prop('checked') ? 
				($('#toContract').prop('checked') ? UF.enums.status.contracting : UF.enums.status.authenticating) : 
				UF.enums.status.base;
				
		$('input[name="status"]').val(model.status);
	}
	
	function updateCity() {
    	var currProv = $(this).val();
    	UF.stores.cities = [];
    	_.each(UF.stores.provinces, function (province) {
    		if (province.name == currProv) {
    			UF.stores.cities = province.cities;
    		}
    	});
    	
    	$('select[name="city"]')
            .prop('disabled', UF.stores.cities.length == 0)
    		.each(UF.utils.loadSelect);
    }
	
	$('form').submit(function () {
		if (model.status == UF.enums.status.base && !$('#toAuth').prop('checked')) {
			$('*[data-group="forAuth"]').val(null);
		}
	})

    // hack
    model.mobilePhone = model.phone;
    model.emailAddress = model.email;
    model.headPhotoUrl = model.photoUrl;
    
    $('#toAuth').click(updateStatus);
    $('#toContract').click(updateStatus);
    $('.collapse input').attr('data-group', 'forAuth');
    
    $('select[name="province"]').each(UF.utils.loadSelect);
    
    UF.utils.renderModel(model);
    
    $('select[name="province"]').each(updateCity).change(updateCity);
    
    switch (model.status) {
    case UF.enums.status.base:
    	$('#toAuth').prop('checked', false);
    	$('#toContract').prop('checked', false);
    	break;
    case UF.enums.status.authenticated:
    	$('#toContract').prop('checked', false);
    	$('.portal').hide();
    	$('.portal[data-name="contract"]').show().removeClass('collapse');
    	$('.portal.withHeader').show();
    	$('footer').show();    }
    
    $('#reauthenticateButton').click(function (e) {
    	e.preventDefault();
    	$('.collapse input').val('');
    	$('.collapse img').attr('src', '');
    	$('input[name="status"]').val(UF.enums.status.authenticating);
    })
    
    var select = $('select[name="city"]');
    select.prop('disabled', select.find('option').length <= 1);
    UF.utils.validate.call(select);
});
