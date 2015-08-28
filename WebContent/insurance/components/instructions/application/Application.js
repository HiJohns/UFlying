UfInsurance.directive('comInstructionsApplication', function (modInstructionsApplication) {
	return {
		restrict: 'E', 
		templateUrl: 'insurance/components/instructions/application/Application.html',
		scope: {
		},
		link: function (scope) {
			_.extend(scope, modInstructionsApplication);
		}
	}
})