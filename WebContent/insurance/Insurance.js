UfInsurance = angular.module('UfInsurance',['ui.bootstrap','dialogs.main', 'ngCookies', 'UfCommon', 'ngRoute'])
	.controller('Main', function($scope,$rootScope,$timeout,dialogs,$cookies,$http,modInstructionsApplication){
	})
	.config(['$routeProvider', '$locationProvider',
 	    function ($routeProvider, $locationProvider) {
 			$routeProvider
 				.when('/insurance_page', {
 					templateUrl: 'insurance/views/instructions/Instructions.html',
 					controller: 'vieInstructions'
 				});			
 			$locationProvider.html5Mode(true);
 		}
 	]);