UfCommon.directive('comWizard', function ($injector) {
	return {
		restrict: 'E', 
		scope: {
			config: '='
		}, 
		templateUrl: 'common/components/wizard/Wizard.html',
		controller: function ($scope, $timeout) {
			function broadCastData() {
				var card = $scope.config.cards[$scope.active];
				$scope.$broadcast('data', card.name, $scope.model.getData(card.name));
			}
			
			$scope.model = $injector.get($scope.config.model);
			
			$scope.active = $scope.config.active || 0;
			$timeout(broadCastData, 100);
			
			var tabs = [];
			_.each($scope.config.cards, function (card, index) {
				tabs.push(card.title);
			});
			
			$scope.headerTabs = tabs;
			
			$scope.$on('forward', function (e, data) {
				console.log('Wizard: forward');
				$scope.model
					.setData($scope.config.cards[$scope.active].name, data)
					.then(function () {
						console.log('Wizard: setData.then active=', $scope.active, "cards=", $scope.config.cards.length);
						if ($scope.active < $scope.config.cards.length) {
							++$scope.active;
							broadCastData();
							$scope.$apply();
						}
						else {
							$scope.$broadcast('beforesave');
							$scope.model.save();
						}
					}, function (errMsg) {
						
					});
			});

			$scope.$on('backward', function () {
				if ($scope.active > 0) $scope.active--;
			});
		}
	}
})

