UfMission.controller('vieMissionedit', function ($scope, modCities, $cookies, $http, $location, modMissionconfigs, $routeParams, dialogs, modLogin, misUtils) {
    $scope.page = 'form';
    $scope.cancel = function() {
        $modalInstance.dismiss('Canceled');
    }

    $scope.close = function() {
        $location.path('/mission_page');
    }

    $scope.submit = function () {
        if ($scope.missionForm.$invalid) return;
        var account = modLogin.getCurrentUser();
        if (account == null) {
        	login(_submit);
        }
        else {
        	_submit(account);
        }
        
        function _submit(account) {
	        $scope.user = account.uid || -account.eid;
	        $scope.phone = account.mobilePhone;
	        $scope.page = 'summary';
	        $scope.headerActive = 1;
	        $scope.place = parseInt($('input[type="radio"][name="place"]:checked').val(), 10);
	        var match = $('section.date > input[type=date]').val().match(/(\d+)年(\d+)月(\d+)日/);
	        $scope.date = new Date(match[1], parseInt(match[2], 10)-1, match[3]);
	
	        var duration = Math.ceil(($scope.endTime.getTime() - $scope.startTime.getTime()) / (60*1000));
	
	        var config = $scope.config;
	        $scope.payment = config.standardFee + 
	            (duration > config.standardDuration ? config.extraFee * Math.ceil((duration - config.standardDuration) / config.extraDuration) : 0);
        	
        }
    }

    $scope.checkout = function () {
        $scope.loading = true;
        $http.post('create_mission', JSON.stringify({
            missionType: $scope.config.missionType,
            typeInitials: $scope.config.typeInitials,
            address: $scope.address,
            city: $scope.city,
            province: $scope.province,
            endTime: misUtils.renderJsonTime($scope.endTime),
            startTime: misUtils.renderJsonTime($scope.startTime),
            missionDate: misUtils.renderJsonDate($scope.date),
            payment: $scope.payment,
            place: $scope.place,
            remark: $scope.remark
        })).then(function (result) {
            $scope.missionId = result.data.missionId;
            $scope.page = 'result';
            $scope.loading = false;
        }, function (result) {
            alert('提交失败!请稍候重试。');
            $scope.loading = false;
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
    
    function login(callback) {
        dialogs.create(
            	'common/components/dialogLogin/DialogLogin.html',
                'comDialogLogin',
                {},
                {
                    size:'md',
                    keyboard: true,
                    backdrop: false
                }).result.then(function (account) {
                    var d = new Date();
                    $cookies.put(
                        'token', 
                        account.token, { 
                            path: '/', 
                            expires: new Date(
                                d.getFullYear(), 
                                d.getMonth(),
                                d.getDate() + 15, 
                                d.getHours(),
                                d.getMinutes()
                            )
                        });

                    callback(account);
                });
    }

    $scope.loading = true;
    modCities.load(function (data) {
        $scope.loading = false;
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
        if (!$scope.$$phase) $scope.$apply();
    });
    
    modMissionconfigs.load(function (configs) {
    	$scope.configs = configs;
        $scope.config = modMissionconfigs.find($routeParams.type);
        if (!$scope.$$phase) $scope.$apply();
    });
    
    modLogin.load(function () {});

    $scope.dateOptions = {
      'year-format': "'yy'",
      'starting-day': 1,
      'show-weeks': false,
      'current-text': '今天',
      'clear-text': '清空',
      'close-text': '完成'
    };
    var date = new Date();
    var _dateTemplate = _.template('<%=year%>年<%=month%>月<%=date%>日');
    $scope.minDate = $scope.date = new Date(date.getFullYear(), date.getMonth(), date.getDate() + 2);
    $scope.startTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 8, 0);
    $scope.endTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 17, 0);
    
    $scope.place = 1;
    
    $scope.$watch('startTime < endTime', function (newValue) {
    	$scope.timeClass = newValue ? '' : 'invalid';
    });
    
    $scope.headerTabs = [ '任务设置', '确认' ];
    $scope.headerActive = 0;

    // const.
    $scope.bankAccount = {
        name: '刘鹏',
        account: '6216610100010698856',
        branch: '中国银行北京分行东大桥支行'
    };
    
    _.delay(function () {
    $scope.qrcodePayment = 'common/img/8cd0b33a3c074896bf537ed479b683ad.jpg';
    }, 100);
});
