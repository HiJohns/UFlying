UfInsurance.directive('comInstructionsClaim', function (modInstructionsClaim) {
	return {
		restrict: 'E', 
		scope: {
		},
		templateUrl: 'insurance/components/instructions/claim/Claim.html',
		link: function (scope) {
			_.extend(scope, modInstructionsClaim);
		}
	}
})