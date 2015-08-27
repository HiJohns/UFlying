UfCommon.directive('comScrollview', function () {
    return {
        restrict: 'E',
        scope: {
        	images: '=',
        	myInterval: '='
        },
        templateUrl: 'common/components/scrollview/Scrollview.html',
        link: function(scope, element, attributes, interval){
        	scope.images = scope.images;
        	scope.myInterval = scope.myInterval || 2000;
        }
    }
    
    
})

