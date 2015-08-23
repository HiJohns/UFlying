UfMission.factory('modMissionconfigs', function ($http, $timeout) {
    var configs = null;

    return {
    	find: function (type) {
    		return _.find(configs, function (config) { return config.typeInitials == type; });
    	},
        load: function (callback) {
            if (configs == null) {
            	$.ajax({
            		url: 'mission_fee',
            		method: 'post',
            		timeout: 5000,
            		success: function (data) {
            			configs = data;
            			callback(configs);
            		},
            		error: function (jqXHR, textStatus, errorThrown) {
            			console.log('mission_fee调用失败，原因：', textStatus);
                        configs = [{"typeName":"求婚","typeInitials":"QH","standardFee":3000,"standardDuration":180,"extraFee":1500,"extraDuration"
                        	:60,"missionType":1,"contractType":101},{"typeName":"婚礼","typeInitials":"HL","standardFee":3000,"standardDuration"
                        		:180,"extraFee":1500,"extraDuration":60,"missionType":2,"contractType":102},{"typeName":"公关活动","typeInitials"
                        		:"GH","standardFee":8000,"standardDuration":180,"extraFee":3000,"extraDuration":60,"missionType":3,"contractType"
                        		:103},{"typeName":"广告","typeInitials":"GG","standardFee":6000,"standardDuration":180,"extraFee":2500
                        		,"extraDuration":60,"missionType":4,"contractType":104},{"typeName":"宣传片","typeInitials":"XC","standardFee"
                        		:6000,"standardDuration":180,"extraFee":2500,"extraDuration":60,"missionType":5,"contractType":105},
                        		{"typeName":"私人教练","typeInitials":"PL","standardFee":500,"standardDuration":120,"extraFee":200,"extraDuration"
                        		:60,"missionType":6,"contractType":106}];
                        callback(configs);
            		}
            	});
            	
            	return;
                $http.post('mission_fee').then(function (response) {
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
