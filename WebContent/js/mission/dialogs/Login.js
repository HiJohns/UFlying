UFlying.controller('dialogs.Login', function ($scope, $modalInstance, $http, data, $cookies) {
    $scope.cancel = function () {
        $modalInstance.dismiss('Canceled');
    }

    $scope.ok = function () {
    	$http.post('../ajax_login', JSON.stringify({ user: $scope.user, password: $scope.password }))
    		.then(function (response) {
    			var data = response.data;
    			if (data.status != 0) {
    				alert('登录失败！原因如下：' + data.reason);
    				return;
    			}
    			
    			$modalInstance.close(data.account);
    		}, function (response) {
    			alert('登录失败！');
    		})
    }
});
