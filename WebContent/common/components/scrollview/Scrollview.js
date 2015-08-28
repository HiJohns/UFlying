UfCommon.directive('comScrollview', function () {
    return {
        restrict: 'E',
        scope: {
        	images: '=',
        	interval: '='
        },
        templateUrl: 'common/components/scrollview/Scrollview.html',
        link: function(scope, element, attrs){
        	scope.interval = scope.interval || 2000;
        }
    }
    
    
})

