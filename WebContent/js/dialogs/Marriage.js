UFlying.controller('marriageDialog', function ($scope, $modalInstance, cities, data) {
    cities.load(function (data) {
        var provinces = [];
        for (var province in data) {
            provinces.push({
                name: province,
                cities: data[province]
            });
        }

        $scope.provinces = provinces;
        
        $scope.province = provinces.length > 0 ? provinces[0].name : null;
        $scope.cities = provinces.length > 0 ? provinces[0].cities : [];
        $scope.city = $scope.cities[0];
    });

    $scope.cancel = function() {
        $modalInstance.dismiss('Canceled');
    }

    $scope.provinceChange = function () {
        var province = _.find($scope.provinces, function (prov) {
            return prov.name == $scope.province;
        });

        if (_.isUndefined(province)) return;

        $scope.cities = province.cities;
        $scope.city = $scope.cities.length > 0 ? $scope.cities[0] : null;
        $scope.cityChange();
    }

    $scope.cityChange = function () {
        $scope.address = '';
    }
})
