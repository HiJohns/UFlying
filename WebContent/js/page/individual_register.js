UF.page.IndividualRegister = (function () {
	return {
		dependencies: [
		               'Form',
		               'Mobile'
		               ],
		init: function () {
			$('input[type="submit"]').prop('disabled', true);
			$('form').each(UF.business.Mobile.prepareGetVerifyCode);
		}
	}
})();