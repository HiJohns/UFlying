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

			$scope.moveForward = function () {
				$scope.active++;
			}

			$scope.moveBackward = function () {
				$scope.active--;
			}
			
			$scope.submit = function () {
				if (_.isFunction($scope.config.submit)) {
					$scope.config.submit();
				}
			}
		}
	}
})

