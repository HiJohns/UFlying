UFlying.controller('dialogs.MissionEdit', function ($scope, $modalInstance, cities, $timeout, data, $http, UFlyingLogin, UFlyingUtils) {
    $scope.cancel = function() {
        $modalInstance.dismiss('Canceled');
    }

    $scope.submit = function () {
        if ($scope.missionForm.$invalid) return;
        var account = UFlyingLogin.getCurrentUser();
        $scope.user = account.uid || -account.eid;
        $scope.phone = account.mobilePhone;
        $scope.timeDescript = $scope.dateText;
        $scope.page = 'summary';
        $scope.place = parseInt($('input[type="radio"][name="place"]:checked').val(), 10);

        var duration = Math.ceil(($scope.endTime.getTime() - $scope.startTime.getTime()) / (60*1000));

        var config = $scope.config;
        $scope.payment = config.standardFee + 
            (duration > config.standardDuration ? config.extraFee * Math.ceil((duration - config.standardDuration) / config.extraDuration) : 0);
    }

    $scope.checkout = function () {
        $http.post('../create_mission', JSON.stringify({
            missionType: $scope.config.missionType,
            address: $scope.address,
            city: $scope.city,
            province: $scope.province,
            endTime: UFlyingUtils.renderJsonTime($scope.endTime),
            startTime: UFlyingUtils.renderJsonTime($scope.startTime),
            missionDate: UFlyingUtils.renderJsonDate($scope.date),
            payment: $scope.payment,
            place: $scope.place,
            remark: $scope.remark
        })).then(function (result) {
        });
    }

    $scope.edit = function () {
        $scope.page = 'form';
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

    $scope.open = function() {
      $timeout(function() {
        $scope.opened = true;
      });
    };

    $scope.setMode = function (mode) {
        console.log('setMode', mode);
        $scope.mode = mode;
    }

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

    $scope.dateOptions = {
      'year-format': "'yy'",
      'starting-day': 1,
      'show-weeks': false,
      'current-text': '今天',
      'clear-text': '清空',
      'close-text': '完成'
    };

    var _dateTemplate = _.template('<%=year%>年<%=month%>月<%=date%>日');

    var date = $scope.date = new Date();
    $scope.dateText = _dateTemplate({
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        date: date.getDate()
    });

    $scope.startTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 8, 0);
    $scope.endTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 17, 0);
    $scope.place = 1;
    $scope.config = data.config;

    $scope.page = 'form';
});
