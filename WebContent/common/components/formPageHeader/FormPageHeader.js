UfCommon.directive('comFormpageheader', function () {
    return {
        restrict: 'E',
        scope: {
        	tabTitles: '=tabs',
        	active: '='
        },
        templateUrl: 'common/components/formPageHeader/FormPageHeader.html',
        link: function (scope, element, attrs) {
        	scope.tabs = [];
        	scope.active = scope.active || 0;
        	_.each(scope.tabTitles, function (title, index) {
        		scope.tabs.push({ title: title, active: scope.active == index})
        	});
        	
        	scope.$watch('active', function (newValue) {
        		_.each(scope.tabs, function (tab, index) {
        			tab.active = scope.active == index;
        		});
        	});
        }
    }
})

