UfInsurance = angular.module('UfInsurance',['ui.bootstrap','dialogs.main', 'ngCookies', 'UfCommon'])
	.controller('Main', function($scope,$rootScope,$timeout,dialogs,$cookies,$http){

		$scope.myInterval = 2000;
	    $http.get("insurance/models/text.json").
	    then(function(response) {
	    	$scope.questions = response.data.section.questions;
	    	$scope.processes = response.data.section.processes;
	    	$scope.processes2 = response.data.section.processes2;
	    	$scope.processes3 = response.data.section.processes3;
	    	$scope.apply = response.data.section.apply;
	    	$scope.images = response.data.section.images;
	    	$scope.image1 = response.data.section.images[0].source;
	    	$scope.image2 = response.data.section.images[1].source;
	    	$scope.image3 = response.data.section.images[2].source;
	    	$scope.image4 = response.data.section.images[3].source;
	    	$scope.types = response.data.section.types;      
	      }, function(response) {
	    	  alert('json数据不对');

	      }) ;
	});


