UfInsurance.controller('vieEditor', function ($scope, $templateCache, $routeParams) {
	$scope.wizardConfig = {
		cards: [
               { path: 'insurance/components/editor/form/FormPingan360.html', controller: 'comEditorForm', name: 'form', title: '填写保单' },
               { path: 'insurance/components/editor/confirm/Confirm.html', controller: 'comEditorConfirm', name: 'confirm', title: '提交' }
               ]
	};
})