UFlying.factory('UFlyingMissionConfigs', function ($http) {
    var configs = null;

    return {
        load: function (callback) {
            if (configs == null) {
                $http.post('../mission_fee').then(function (response) {
                    configs = response;
                    callback(configs);
                }, function (response) {
                    console.log('任务配置载入失败！错误码：' + response.status);
                    configs = [{ 
                        missionType: 1, 
                        typeName: '求婚', 
                        typeInitials: 'QH', 
                        standardFee: 3000, 
                        standardDuration: 180, 
                        extraFee: 1500,
                        extraDuration: 60
                    }];
                    callback(configs);
                });
            }
            else {
                callback(configs);
            }
        }
    }
});
