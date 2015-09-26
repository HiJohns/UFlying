UfMission.controller('vieMissionedit', function ($scope, modCities, $cookies, $timeout, $http, $location, modMissionconfigs, $routeParams, dialogs, modLogin, misUtils) {
    $scope.page = 'form';
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
	        $scope.headerActive = 2;
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

    $scope.place = 1;
    
    $scope.headerTabs = [ '任务设置', '确认', '提交' ];
    $scope.headerActive = 0;

    // const.
    $scope.bankAccount = {
        name: '刘鹏',
        account: '6216610100010698856',
        branch: '中国银行北京分行东大桥支行'
    };
    
    _.delay(function () {
	    $scope.qrcodePayment = 'common/img/8cd0b33a3c074896bf537ed479b683ad.jpg';
	    $('.card').css('display', '');
    }, 100);
});
