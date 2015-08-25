UfMission.factory('modMissionconfigs', function ($http, $timeout, misThread) {
    var _configs = null;
    
    var _waiting = [];
    
    function loaded() {
    	_.each(_waiting, function (callback) {
    		callback(_configs);
    	});
    }

    return {
    	find: function (type) {
    		return _.find(_configs, function (config) { return config.typeInitials == type; });
    	},
        load: function (callback) {
            if (_configs !== null) {
            	return callback(_configs);
            }
            
            _waiting.push(callback);
            if (_waiting.length > 1) {
            	return;
            }
            
            misThread
            	.assign('POST', '../../mission_fee', null)
            	.then(function (configs) {
        			_configs = configs;
        			loaded();
                }, function (status) {
        			console.log('mission_fee调用失败，原因：', status);
                    _configs = [{"typeName":"求婚","typeInitials":"QH","standardFee":3000,"standardDuration":180,"extraFee":1500,"extraDuration"
                    	:60,"missionType":1,"contractType":101},{"typeName":"婚礼","typeInitials":"HL","standardFee":3000,"standardDuration"
                    		:180,"extraFee":1500,"extraDuration":60,"missionType":2,"contractType":102},{"typeName":"公关活动","typeInitials"
                    		:"GH","standardFee":8000,"standardDuration":180,"extraFee":3000,"extraDuration":60,"missionType":3,"contractType"
                    		:103},{"typeName":"广告","typeInitials":"GG","standardFee":6000,"standardDuration":180,"extraFee":2500
                    		,"extraDuration":60,"missionType":4,"contractType":104},{"typeName":"宣传片","typeInitials":"XC","standardFee"
                    		:6000,"standardDuration":180,"extraFee":2500,"extraDuration":60,"missionType":5,"contractType":105},
                    		{"typeName":"私人教练","typeInitials":"PL","standardFee":500,"standardDuration":120,"extraFee":200,"extraDuration"
                    		:60,"missionType":6,"contractType":106}];
                    loaded();
                });
        }
    }
});
