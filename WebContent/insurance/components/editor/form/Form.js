UfInsurance.controller('comEditorForm', function ($scope, modFlyermodels, Upload) {
	modFlyermodels.load(function (list) {
		$scope.flyerModels = list;
	})
	
	$scope.flyerPicture = function (file, index) {
		
	}
});