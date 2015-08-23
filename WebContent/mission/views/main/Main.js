UfMission.controller('vieMain', ['$scope', '$route', '$routeParams', '$location', function ($scope, $route, $routeParams, $location) {
    function openDialog(mission, callback) {
        var dlg = dialogs.create(
        	'mission/components/dialogMissionEdit/DialogMissionEdit.html',
            'comDialogMissionedit', {
                config: mission
            },
            {
                size:'md',
                keyboard: true,
                backdrop: false,
                windowClass: 'my-class'
            });

        dlg.result.then(callback);
    }

    function login(followingAction) {
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

                openDialog(followingAction);
            });
    }

	$scope.mission = function () {
        function onLoginInfo(info) {
            $scope.loading = false;
        	var config = _.findWhere($scope.missionConfigs, { missionType: missionType });
            if (info == null) {
                login(config);
            }
            else {
                openDialog(config);
            }
        }

        function onLoginFailed(response) {
            $scope.loading = false;
            alert('非常抱歉，无法连接服务器，错误码为' + response.status + '，请致电客服为您解决问题。');
        }

        $scope.loading = true;
        missionType = _.isObject(this.config) ? this.config.missionType : 1;
        modLogin.getLoginInfo().then(onLoginInfo, onLoginFailed);
    }
	
	console.log($location.path());
}]);
