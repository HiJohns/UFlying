UfMission = angular.module('UfMission',['ui.bootstrap','dialogs.main', 'ngCookies', 'UfCommon', 'ngRoute'])
	.controller('Main', function($scope,$rootScope, modLogin, modMissionconfigs){
        $scope.missionConfigs = null;
        modMissionconfigs.load(function (configs) {
            $scope.missionConfigs = configs;
            if (!$scope.$$phase) $scope.$apply();
        });
	})
	.config(['$routeProvider', '$locationProvider',
	    function ($routeProvider, $locationProvider) {
			$routeProvider
				.when('/mission_page', {
					templateUrl: 'mission/views/main/Main.html',
					controller: 'vieMain'
				})
				.when('/mission_page/create/:type', {
					templateUrl: 'mission/views/editor/Editor.html',
					controller: 'vieEditor'
				})
;			
			$locationProvider.html5Mode(true);
		}
	])
	.run(function($templateCache){
		$templateCache.put('/dialogs/custom.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> User\'s Name</h4></div><div class="modal-body"><ng-form name="nameDialog" novalidate role="form"><div class="form-group input-group-lg" ng-class="{true: \'has-error\'}[nameDialog.username.$dirty && nameDialog.username.$invalid]"><label class="control-label" for="course">Name:</label><input type="text" class="form-control" name="username" id="username" ng-model="user.name" ng-keyup="hitEnter($event)" required><span class="help-block">Enter your full name, first &amp; last.</span></div></ng-form></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="cancel()">Cancel</button><button type="button" class="btn btn-primary" ng-click="save()" ng-disabled="(nameDialog.$dirty && nameDialog.$invalid) || nameDialog.$pristine">Save</button></div>');
  		$templateCache.put('/dialogs/custom2.html','<div class="modal-header"><h4 class="modal-title"><span class="glyphicon glyphicon-star"></span> Custom Dialog 2</h4></div><div class="modal-body"><label class="control-label" for="customValue">Custom Value:</label><input type="text" class="form-control" id="customValue" ng-model="data.val" ng-keyup="hitEnter($event)"><span class="help-block">Using "dialogsProvider.useCopy(false)" in your applications config function will allow data passed to a custom dialog to retain its two-way binding with the scope of the calling controller.</span></div><div class="modal-footer"><button type="button" class="btn btn-default" ng-click="done()">Done</button></div>');
	});
	
