UF.page.ResetPassword = (function () {
	return {
		dependencies: [
		               'Form',
		               'Mobile'
		               ],
		init: function () {
			UF.business.Form.init();
			$('input[type="submit"]').prop('disabled', true);
			$('form').each(UF.business.Mobile.prepareGetVerifyCode);
		}
	}
})();