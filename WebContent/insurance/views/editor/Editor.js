UfInsurance.controller('vieEditor', ['$scope', '$templateCache', '$routeParams', 'misLogin', function ($scope, $templateCache, $routeParams, misLogin) {
	misLogin.promptLogin(function (account) {
	});
	
	$scope.wizardConfig = {
		cards: [
               { path: 'insurance/components/editor/form/FormPingan360.html', controller: 'comEditorForm', name: 'form', title: '填写保单', form: "insuranceForm" },
               { path: 'insurance/components/editor/confirm/Confirm.html', controller: 'comEditorConfirm', name: 'confirm', title: '提交' }
               ],
        model: 'modFormsPingan360'
	};
}])