UFlying.factory('UFlyingMissionConfigs', function ($http, $timeout) {
    var configs = null;

    return {
        load: function (callback) {
            if (configs == null) {
            	$.ajax({
            		url: '../mission_fee',
            		method: 'post',
            		timeout: 5000,
            		success: function (data) {
            			configs = response;
            			console.log('mission_fee调用成功: ', data);
            			callback(configs);
            		},
            		error: function (jqXHR, textStatus, errorThrown) {
            			console.log('mission_fee调用失败，原因：', textStatus);
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
            		}
            	});
            	
            	return;
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
