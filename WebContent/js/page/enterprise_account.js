UF.page.EnterpriseAccount = (function () {
	return {
		dependencies: [
		               'Form',
		               'Account'
		               ],
		init: function () {
			UF.business.Account.editUrl = contextPath + '/enterprise_complete';
		}
	}
})();