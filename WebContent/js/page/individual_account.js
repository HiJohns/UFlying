UF.page.IndividualAccount = (function () {
	return {
		dependencies: [
		               'Form',
		               'Account'
		               ],
		init: function () {
			UF.business.Account.editUrl = contextPath + '/individual_complete';
		}
	};
})();

