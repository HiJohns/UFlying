UF.business.Complete = (function () {
	function updateCity() {
    	var currProv = $(this).val();
    	UF.Stores.cities = [];
    	_.each(UF.Stores.provinces, function (province) {
    		if (province.name == currProv) {
    			UF.Stores.cities = province.cities;
    		}
    	});
    	
    	$('select[name="city"]')
            .prop('disabled', UF.Stores.cities.length == 0)
    		.each(UF.business.Form.loadSelect);
    }
	
	function updateStatus() {
		model.status = $('#toAuth').prop('checked') ? 
				($('#toContract').prop('checked') ? UF.base.Enums.status.contracting : UF.base.Enums.status.authenticating) : 
				UF.base.Enums.status.base;
				
				//UF.business.Form.renderModel(model);
		$('input[name="status"]').val(model.status);
	}
	
	return {
		dependencies: [
		               'Form',
		               'Mobile'
		               ],
		init: function () {
		    $('select[name="province"]').each(updateCity).change(updateCity);
		    
		    var city = $('select[name="city"]');
		    city.prop('disabled', city.find('option').length <= 1).val(model.city);
		    
		    $('#toAuth').click(updateStatus);
		    $('#toContract').click(updateStatus);
		    
		    switch (parseInt(model.status, 10)) {
		    case UF.base.Enums.status.base:
		    	$('#toAuth').prop('checked', false);
		    	$('#toContract').prop('checked', false);
		    	break;
		    case UF.base.Enums.status.authenticated:
		    	$('#toContract').prop('checked', false);
		    	$('.portal').hide();
		    	$('.portal[data-name="contract"]').show().removeClass('collapse');
		    	$('.portal.withHeader').show();
		    	$('footer').show();    
		    }
		}
	}
})();
