UfCommon.directive('comWizard', function ($injector) {
	return {
		restrict: 'E', 
		scope: {
			config: '='
		}, 
		templateUrl: 'common/components/wizard/Wizard.html',
		controller: function ($scope, $timeout) {
			function broadCastData() {
				var card = cards[$scope.active];
				$scope.$broadcast('data', card.name, model.getData(card.name));
			}
			
			function createTabs() {
				var tabs = [];
				_.each(cards, function (card, index) {
					tabs.push(card.title);
				});
				
				$scope.headerTabs = tabs;
			}
			
			function _moveNext() {
				if ($scope.active >= cards.length - 1) return;
				
				++$scope.active;
				broadCastData();
				if (!$scope.$$phase) $scope.$apply();
			}
			
			function _saveError(error) {
				$scope.$emit('wizardSaveError', error);
			}
			
			var config = $scope.config;
			var cards = config.cards;
			
			var model = $injector.get(config.model);
			
			$scope.active = config.active || 0;
			$timeout(broadCastData, 100); // 广播首页的数据。这里用timeout的原因是首页创建在此之后
			
			createTabs();
			
			$scope.$on('forward', function (e, data) {
				var activeCard = cards[$scope.active];
				model.setData(activeCard.name, data);
				
				if (_.isFunction(model.isValid) && !model.isValid(activeCard.name)) return;
				
				if (activeCard.submit) {
					model.save()
						.then(_moveNext, _saveError);
				}
				else {
					_moveNext();
				}
			});

			$scope.$on('backward', function () {
				if ($scope.active > 0) $scope.active--;
			});
		}
	}
})

