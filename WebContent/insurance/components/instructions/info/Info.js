UfInsurance.directive('comInstructionsInfo', function (modInstructionsInfo) {
	return {
		restrict: 'E', 
		scope: {
		},
		templateUrl: 'insurance/components/instructions/info/Info.html',
		link: function (scope) {
			_.extend(scope, modInstructionsInfo);
			scope.scrollInterval = 6000;
		}
	}
})