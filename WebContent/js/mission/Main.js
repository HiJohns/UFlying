UFlying = angular.module('UFlying',['ui.bootstrap','dialogs.main', 'ngCookies'])
	.controller('Task', function($scope,$rootScope,$timeout,dialogs,$cookies,UFlyingLogin, UFlyingMissionConfigs){
        function openDialog(mission, callback) {
            var dlg = dialogs.create(
                'dialogs/MissionEdit.html',
                'dialogs.MissionEdit', {
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
                'dialogs/Login.html',
                'dialogs.Login',
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
            UFlyingLogin.getLoginInfo().then(onLoginInfo, onLoginFailed);
        }

        $scope.missionConfigs = null;
        UFlyingMissionConfigs.load(function (configs) {
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
    .filter('userId', function (UFlyingUtils) {
        return function (user) {
            var isIndividual = user > 0;
            if (!isIndividual) user = -user;
            return _.isNumber(user) ? ((isIndividual ? 'G' : 'E') + UFlyingUtils.packZeros(user, 10)) : '';
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
    .filter('timeFormat', function (UFlyingUtils) {
        return UFlyingUtils.renderTime;
    })
	.controller('customDialogCtrl',function($scope,$modalInstance,data){
		//-- Variables --//

		$scope.user = {name : ''};

		//-- Methods --//
		
		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel
		
		$scope.save = function(){
			$modalInstance.close($scope.user.name);
		}; // end save
		
		$scope.hitEnter = function(evt){
			if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.user.name,null) || angular.equals($scope.user.name,'')))
				$scope.save();
		};
	}) // end controller(customDialogCtrl)

	.config(function($translateProvider){
		/**
		 * These are the defaults set by the dialogs.main module when Angular-Translate
		 * is not loaded.  You can reset them in your module's configuration
		 * function by using $translateProvider.  If you want to use these when
		 * Angular-Translate is used and loaded, then you need to also load
		 * dialogs-default-translations.js or include them where you are setting
		 * translations in your module.  These were separated out when Angular-Translate
		 * is loaded so as not to clobber translation set elsewhere in one's 
		 * application.
		 
		$translateProvider.translations('en-US',{
	        DIALOGS_ERROR: "Error",
	        DIALOGS_ERROR_MSG: "An unknown error has occurred.",
	        DIALOGS_CLOSE: "Close",
	        DIALOGS_PLEASE_WAIT: "Please Wait",
	        DIALOGS_PLEASE_WAIT_ELIPS: "Please Wait...",
	        DIALOGS_PLEASE_WAIT_MSG: "Waiting on operation to complete.",
	        DIALOGS_PERCENT_COMPLETE: "% Complete",
	        DIALOGS_NOTIFICATION: "Notification",
	        DIALOGS_NOTIFICATION_MSG: "Unknown application notification.",
	        DIALOGS_CONFIRMATION: "Confirmation",
	        DIALOGS_CONFIRMATION_MSG: "Confirmation required.",
	        DIALOGS_OK: "OK",
	        DIALOGS_YES: "Yes",
	        DIALOGS_NO: "No"
        });
		*/
	})

	.run(function($templateCache){
		$templateCache.put('/dialogs/custom.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> User\'s Name</h4></div><div class="modal-body"><ng-form name="nameDialog" novalidate role="form"><div class="form-group input-group-lg" ng-class="{true: \'has-error\'}[nameDialog.username.$dirty && nameDialog.username.$invalid]"><label class="control-label" for="course">Name:</label><input type="text" class="form-control" name="username" id="username" ng-model="user.name" ng-keyup="hitEnter($event)" required><span class="help-block">Enter your full name, first &amp; last.</span></div></ng-form></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="cancel()">Cancel</button><button type="button" class="btn btn-primary" ng-click="save()" ng-disabled="(nameDialog.$dirty && nameDialog.$invalid) || nameDialog.$pristine">Save</button></div>');
  		$templateCache.put('/dialogs/custom2.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> Custom Dialog 2</h4></div><div class="modal-body"><label class="control-label" for="customValue">Custom Value:</label><input type="text" class="form-control" id="customValue" ng-model="data.val" ng-keyup="hitEnter($event)"><span class="help-block">Using "dialogsProvider.useCopy(false)" in your applications config function will allow data passed to a custom dialog to retain its two-way binding with the scope of the calling controller.</span></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="done()">Done</button></div>');
	});
	
