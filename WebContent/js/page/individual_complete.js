UF.page.IndividualComplete = (function () {
	function updateStatus() {
		model.status = $('#toAuth').prop('checked') ? 
				($('#toContract').prop('checked') ? UF.base.Enums.status.contracting : UF.base.Enums.status.authenticating) : 
				UF.base.Enums.status.base;
				
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
    		.each(UF.business.Form.loadSelect);
    }
	
	return {
		dependencies: [
		               'Form'
		               ],
		init: function () {
			UF.business.Form.init();
			
			$('form').submit(function () {
				if (model.status == UF.base.Enums.status.base) {
					if (!$('#toAuth').prop('checked')) {
						$('*[data-group="forAuth"]').val(null);
						$('input[name="experience"]').val(0);
						$('*[data-group="forAuth"]').removeAttr('required');
					}
					else {
						$('*[data-group="forAuth"]').attr('required', 'true');
					}
				}
			})

		    // hack
		    model.mobilePhone = model.phone;
		    model.emailAddress = model.email;
		    model.headPhotoUrl = model.photoUrl;
		    
		    $('#toAuth').click(updateStatus);
		    $('#toContract').click(updateStatus);
		    $('.collapse input').attr('data-group', 'forAuth');
		    
		    $('select[name="province"]').each(UF.business.Form.loadSelect);
		    
		    UF.business.Form.renderModel(model);
		    
		    $('select[name="province"]').each(updateCity).change(updateCity);
		    
		    switch (model.status) {
		    case UF.base.Enums.status.base:
		    	$('#toAuth').prop('checked', false);
		    	$('#toContract').prop('checked', false);
		    	break;
		    case UF.base.Enums.status.authenticated:
		    	$('#toContract').prop('checked', false);
		    	$('.portal').hide();
		    	$('.portal[data-name="contract"]').show().removeClass('collapse');
		    	$('.portal.withHeader').show();
		    	$('footer').show();    }
		    
		    $('#reauthenticateButton').click(function (e) {
		    	e.preventDefault();
		    	$('.collapse input').val('');
		    	$('.collapse img').attr('src', '');
		    	$('input[name="status"]').val(UF.base.Enums.status.authenticating);
		    })
		    
		    var select = $('select[name="city"]');
		    select.prop('disabled', select.find('option').length <= 1);
		    UF.business.Form.validate.call(select);
		}
	}
})();
