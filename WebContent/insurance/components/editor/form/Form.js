UfInsurance.controller('comEditorForm', function ($scope, modFlyermodels, Upload, modLogin) {
	modFlyermodels.load(function (list) {
		$scope.flyerModels = list;
	});
	
	modLogin.watch(function (account) {
		$scope.account = account;
		$scope.$apply();
	});
	
	$scope.flyerPicture = function (file, index) {		
	}
});