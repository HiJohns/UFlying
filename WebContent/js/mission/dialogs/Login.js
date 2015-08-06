UFlying.controller('dialogs.Login', function ($scope, $modalInstance, $http, data) {
    $scope.cancel = function () {
        $modalInstance.dismiss('Canceled');
    }

    $scope.ok = function () {
        $modalInstance.close();
    }
});
