UfInsurance.controller('vieEditor', function ($scope, $templateCache) {
	$scope.wizardConfig = {
		cards: [
               { path: 'insurance/components/editor/form/Form.html', controller: 'comEditorForm', name: 'form', title: '填写保单' },
               { path: 'insurance/components/editor/confirm/Confirm.html', controller: 'comEditorConfirm', name: 'confirm', title: '提交' }
               ]
	};
})