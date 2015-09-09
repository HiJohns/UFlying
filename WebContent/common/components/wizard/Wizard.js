UfCommon.directive('comWizard', function () {
	return {
		restrict: 'E', 
		scope: {
			config: '='
		}, 
		templateUrl: 'common/components/wizard/Wizard.html',
		controller: function ($scope) {
			$scope.active = 0;
			
			var tabs = [];
			_.each($scope.config.cards, function (card, index) {
				tabs.push(card.title);
			});
			
			$scope.headerTabs = tabs;
			
			$scope.$on('submit', function () {
				if (_.isFunction($scope.config.submit)) {
					$scope.config.submit();
				}
			});

			$scope.$on('forward', function () {
				$scope.active++;
			});

			$scope.$on('backward', function () {
				$scope.active--;
			});
		}
	}
})

