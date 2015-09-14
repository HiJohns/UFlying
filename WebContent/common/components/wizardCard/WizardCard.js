UfCommon.directive('comWizardcard', function ($compile, $http, misUtils) {
	var _cardTmpl = null;
	
	function linker(scope, element, attrs) {
		function apply(allSuccess) {
			if (!allSuccess) return;
			element.html(_cardTmpl(scope));
			$compile(element.contents())(scope);
			
			if (_.isString(scope.form) && _.isObject(scope.$$childHead)) {
				scope.form = scope.$$childHead[scope.form];
			}
		}
		
		function loadCard(callback) {
			$http.get(scope.path).then(function (response) {
				scope.pageHtml = response.data;
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
			console.log('WizardCard: next');
			if (_.isObject(scope.form)) scope.form.$submitted = true;
			if (scope.form.$invalid) return;
			scope.$emit('forward', scope.data);
		}
		
		scope.prev = function () {
			scope.$emit('backward');
		}
		
		scope.$on('data', function (e, name, data) { 
			if (name === scope.name) scope.data = data; 
		});
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
			last: '=',
			form: '='
		}
	}
})