UF.page.ChangePassword = (function () {
	return {
		dependencies: [
		               'Form'
		               ],
		init: function () {
			UF.business.Form.init();
			$('input[type="submit"]').prop('disabled', false);
		}
	}
})();