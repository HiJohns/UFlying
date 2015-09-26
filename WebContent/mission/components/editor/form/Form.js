UfMission.controller('comEditorForm', function ($scope, $timeout, $routeParams, modCities, modMissionconfigs, misLogin, misUtils) {
	misLogin.promptLogin(function (account) {
		$scope.data.account = account;
	});
	
    $scope.selectTime = function() {
        $timeout(function() {
          $scope.opened = true;
        });
      };

    $scope.provinceChange = function () {
    	var province = _.find($scope.provinces, function (prov) {
    		return prov.name == $scope.data.province;
    	});

    	if (_.isUndefined(province)) return;

    	$scope.cities = province.cities;
        $scope.data.city = $scope.cities.length > 0 ? $scope.cities[0] : null;
        $scope.cityChange();
    }

    $scope.cityChange = function () {
        $scope.data.address = '';
        $scope.missionForm.address.$setPristine();
    }

    $scope.dateOptions = {
		'year-format': "'yy'",
		'starting-day': 1,
		'show-weeks': false,
		'current-text': '今天',
		'clear-text': '清空',
		'close-text': '完成'
    };
    
    $scope.loading = true;

    function allFinished(allSuccess) {
        $scope.loading = false;
        if (!$scope.$$phase) $scope.$apply();
    }
    
    function loadCities(callback) {
        modCities.load(function (data) {
            var provinces = [];
            for (var province in data) {
                provinces.push({
                    name: province,
                    cities: data[province]
                });
            }

            $scope.provinces = provinces;
            
            $scope.data.province = provinces.length > 0 ? provinces[0].name : null;
            $scope.cities = provinces.length > 0 ? provinces[0].cities : [];
            $scope.data.city = $scope.cities[0];
            callback(true);
        });
    }
    
    function loadMissionConfigs(callback) {
        modMissionconfigs.load(function (configs) {
            $scope.configs = configs;
            $scope.data.missionConfig = modMissionconfigs.find($routeParams.type);
            callback(true);
        });
    }
    
    function updateTimeValidateStatus(model, valid) {
    	model.$setValidity('timeRange', valid);
    	model.$setDirty();
    }
    
    misUtils.waitForAll(allFinished, loadCities, loadMissionConfigs);
	$timeout(function () {
	    $scope.minDate = $scope.data.date;
	});
    	    
    $scope.$watch('data.startTime <= data.endTime', function (newValue) {
    	updateTimeValidateStatus($scope.missionForm.startTime, newValue);
    	updateTimeValidateStatus($scope.missionForm.endTime, newValue);
    });
});
