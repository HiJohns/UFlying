UfInsurance = angular.module('UfInsurance',['ui.bootstrap','dialogs.main', 'ngCookies', 'UfCommon', 'ngRoute', 'ngFileUpload'])
	.controller('Main', function($scope,$rootScope,$timeout,dialogs,$cookies,$http,modInstructionsApplication,Upload){
	})
	.config(['$routeProvider', '$locationProvider',
 	    function ($routeProvider, $locationProvider) {
 			$routeProvider
 				.when('/insurance_page', {
 					templateUrl: 'insurance/views/instructions/Instructions.html',
 					controller: 'vieInstructions'
 				})
 				.when('/insurance_page/create/:type', {
 					templateUrl: 'insurance/views/editor/Editor.html',
 					controller: 'vieEditor'
 				});			
 			$locationProvider.html5Mode(true);
 		}
 	]);