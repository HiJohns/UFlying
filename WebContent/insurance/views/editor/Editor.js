UfInsurance.controller('vieEditor', function ($scope, $templateCache, $routeParams) {
	$scope.wizardConfig = {
		cards: [
               { path: 'insurance/components/editor/formPingan360/FormPingan360.html', controller: 'comEditorFormPingan360', name: 'form', title: '填写保单' },
               { path: 'insurance/components/editor/confirm/Confirm.html', controller: 'comEditorConfirm', name: 'confirm', title: '提交' }
               ]
	};
})