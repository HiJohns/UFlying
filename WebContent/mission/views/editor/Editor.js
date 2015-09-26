UfMission.controller('vieEditor', ['$scope', '$templateCache', '$routeParams', 'misLogin', function ($scope, $templateCache, $routeParams, misLogin) {
	misLogin.promptLogin(function (account) {
	});
	
	$scope.wizardConfig = {
		cards: [
               { path: 'mission/components/editor/form/Form.html', controller: 'comEditorForm', name: 'form', title: '创建任务', form: "missionForm" },
               { path: 'mission/components/editor/confirm/Confirm.html', controller: 'comEditorConfirm', name: 'confirm', title: '确认', submit: true, form: "missionConfirmForm" },
               { path: 'mission/components/editor/result/Result.html', controller: 'comEditorResult', name: 'result', title: '提交' }
               ],
        model: 'modMission'
	};
}])