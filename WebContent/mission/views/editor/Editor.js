UfMission.controller('vieEditor', ['$scope', '$templateCache', '$routeParams', 'misLogin', 'misEnums', function ($scope, $templateCache, $routeParams, misLogin, misEnums) {
	misLogin.promptLogin(function (account) {
	});
	
	$scope.$on('wizardSaveError', function (e, params) {
		switch (params.error) {
		case misEnums.errors.httpFailed: 
			alert('网络连接异常，请稍候重试');
			break;
		case misEnums.errors.failToSave: 
			alert('保存失败，请联系客服或稍候重试');
			break;
		}
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