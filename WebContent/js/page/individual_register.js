UF.page.IndividualRegister = (function () {
	return {
		dependencies: [
		               'Form',
		               'VerificationByMobile',
		               'MobileCheck'
		               ],
		init: function () {
			UF.business.Form.init();
			$('input[type="submit"]').prop('disabled', true);
			$('form').each(UF.business.VerificationByMobile.getVerifyCode);
		}
	}
})();