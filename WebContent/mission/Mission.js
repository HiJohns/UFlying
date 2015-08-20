UfMission = angular.module('UfMission',['ui.bootstrap','dialogs.main', 'ngCookies', 'UfCommon'])
	.controller('Main', function($scope,$rootScope,$timeout,dialogs,$cookies, modLogin, modMissionconfigs){
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
                'mission/components/dialogLogin/DialogLogin.html',
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

        $scope.missionConfigs = null;
        modMissionconfigs.load(function (configs) {
            $scope.missionConfigs = configs;
            $scope.$apply();
        });
	})
    .filter('missionPlace', function () {
        var text = [
             '',
            '室内',
            '室外',
            '室内+室外'
        ];

        return function (place) {
            return text[place];
        };
    })
    .filter('userId', function (misUtils) {
        return function (user) {
            var isIndividual = user > 0;
            if (!isIndividual) user = -user;
            return _.isNumber(user) ? ((isIndividual ? 'G' : 'E') + misUtils.packZeros(user, 10)) : '';
        }
    })
    .filter('money', function () {
        return function (money) {
            return _.isNumber(money) ? money.toFixed(2) + '元' : money;
        }
    })
    .filter('dateFormat', function () {
        var _tmpl = _.template('<%=year%>年<%=month%>月<%=day%>日');
        return function (date) {
            return _.isDate(date) ? _tmpl({ year: date.getFullYear(), month: date.getMonth()+1, day: date.getDate() }) : date;
        }
    })
    .filter('timeFormat', function (misUtils) {
        return misUtils.renderTime;
    })
	.run(function($templateCache){
		$templateCache.put('/dialogs/custom.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> User\'s Name</h4></div><div class="modal-body"><ng-form name="nameDialog" novalidate role="form"><div class="form-group input-group-lg" ng-class="{true: \'has-error\'}[nameDialog.username.$dirty && nameDialog.username.$invalid]"><label class="control-label" for="course">Name:</label><input type="text" class="form-control" name="username" id="username" ng-model="user.name" ng-keyup="hitEnter($event)" required><span class="help-block">Enter your full name, first &amp; last.</span></div></ng-form></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="cancel()">Cancel</button><button type="button" class="btn btn-primary" ng-click="save()" ng-disabled="(nameDialog.$dirty && nameDialog.$invalid) || nameDialog.$pristine">Save</button></div>');
  		$templateCache.put('/dialogs/custom2.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> Custom Dialog 2</h4></div><div class="modal-body"><label class="control-label" for="customValue">Custom Value:</label><input type="text" class="form-control" id="customValue" ng-model="data.val" ng-keyup="hitEnter($event)"><span class="help-block">Using "dialogsProvider.useCopy(false)" in your applications config function will allow data passed to a custom dialog to retain its two-way binding with the scope of the calling controller.</span></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="done()">Done</button></div>');
	});
	