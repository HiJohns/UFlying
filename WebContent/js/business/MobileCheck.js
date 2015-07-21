UF.business.MobileCheck = (function () {
	return {
		check: function (phone, callback) {
			$('button[data-name="btnVerify"]').prop('disabled', true);
			$.ajax({
				  url: contextPath + '/check_mobile',
				  method: 'POST',
				  data: {
				    phone: '14332143132'
				  },
				  success: function (data) {
					  var isValid = data.reason == "0";
					  $('button[data-name="btnVerify"]').prop('disabled', !isValid);
					  callback(isValid);
				  }
				});			
		}
	}
})();