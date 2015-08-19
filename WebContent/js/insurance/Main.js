UFlying = 	angular.module('myApp',['ui.bootstrap']).controller('Insurance',function($scope,$window, $http){
    $http.get("text.json").
    then(function(response) {
    	$scope.questions = response.data.section.questions;
    	$scope.processes = response.data.section.processes;
    	$scope.processes2 = response.data.section.processes2;
    	$scope.processes3 = response.data.section.processes3;
    	$scope.apply = response.data.section.apply;
    	$scope.types = response.data.section.types;        	
      }, function(response) {
    	  alert('json数据不对');

      }) 
})
