UfInsurance.directive('comInstructionsFaq', function (modInstructionsFaq) {
	return {
		restrict: 'E', 
		scope: {
		},
		templateUrl: 'insurance/components/instructions/faq/Faq.html',
		link: function (scope) {
			_.extend(scope, modInstructionsFaq);
		}
	}
})