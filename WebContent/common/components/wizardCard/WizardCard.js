UfCommon.directive('comWizardcard', function ($compile, $http, misUtils) {
	var _cardTmpl = null;
	function linker(scope, element, attrs) {
		function apply(allSuccess) {
			if (!allSuccess) return;
			element.html(_cardTmpl(scope));
			$compile(element.contents())(scope);
		}
		
		function loadCard(callback) {
			$http.get(scope.path).then(function (response) {
				scope.data = response.data;
				callback(true);
			}, function (response) {
				console.log('载入' + scope.path + '失败！错误码' + response.status);
				callback(false);
			});
		}
		
		function loadTemplate(callback) {
			$http.get('common/components/wizardCard/WizardCard.html').then(function (response) {
				_cardTmpl = _.template(response.data);
				callback(true);
			}, function (response) {
				console.log('载入' + scope.path + '失败！错误码' + response.status);
				callback(false);
			});
		}
		
		misUtils.waitForAll(apply, loadCard, loadTemplate);
		
		scope.nextText = scope.last ? "提交" : '下一步';
		
		scope.next = function () {
			if (scope.last) 
				scope.$parent.submit() 
			else 
				scope.$parent.moveForward();
		}
		
		scope.prev = function () {
			scope.$parent.moveBackward();
		}
	}
	
	return {
		restrict: 'E',
		link: linker,
		scope: {
			controller: '=',
			path: '=',
			name: '=',
			active: '=',
			first: '=',
			last: '='
		}
	}
})