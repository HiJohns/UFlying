UfMission.controller('comEditorResult', function ($scope, $location) {
    // const.
    $scope.bankAccount = {
        name: '刘鹏',
        account: '6216610100010698856',
        branch: '中国银行北京分行东大桥支行'
    };
    
    _.delay(function () {
	    $scope.qrcodePayment = 'common/img/8cd0b33a3c074896bf537ed479b683ad.jpg';
	    $('.card').css('display', '');
    }, 100);
    
    $scope.toMain = function () {
    	$location.path('mission_page')
    }
});
