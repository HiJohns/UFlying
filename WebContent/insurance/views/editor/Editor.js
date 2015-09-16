UfInsurance.controller('vieEditor', ['$scope', '$templateCache', '$routeParams', 'misLogin', function ($scope, $templateCache, $routeParams, misLogin) {
	misLogin.promptLogin(function (account) {
	});
	
	$scope.wizardConfig = {
		cards: [
               { path: 'insurance/components/editor/form/Pingan360.html', controller: 'comEditorForm', name: 'form', title: '填写保单', form: "insuranceForm" },
               { path: 'insurance/components/editor/confirm/Pingan360.html', controller: 'comEditorConfirm', name: 'confirm', title: '提交', submit: true }
               ],
        model: 'modFormsPingan360'
	};
}])