UfMission.factory('modMission', function ($http, misUtils) {
	var _date = new Date();
	var _data = {
		province: null,
		city: null,
		place: 1,
		missionType: null,
		payment: 0,
		typeInitials: null,
		address: '',
		missionDate: new Date(_date.getFullYear(), _date.getMonth(), _date.getDate() + 2),
	    startTime: new Date(_date.getFullYear(), _date.getMonth(), _date.getDate(), 8, 0),
		endTime: new Date(_date.getFullYear(), _date.getMonth(), _date.getDate(), 17, 0)
	};
	
	var _agree = false;
	
	return {
        getData: function (cardName) {
            return _.clone(_data);
        },
        setData: function (cardName, data) {
        	switch (cardName) {
        	case 'form':
    	        var config = data.missionConfig;
            	if (_.isObject(config)) {
            		data.missionType = config.missionType;
                    data.typeInitials = config.typeInitials;
                    
        	        var duration = Math.ceil((data.endTime.getTime() - data.startTime.getTime()) / (60*1000));
        	        data.payment = config.standardFee + 
        	            (duration > config.standardDuration ? config.extraFee * Math.ceil((duration - config.standardDuration) / config.extraDuration) : 0);
            	}
            	
            	_.extend(_data, data);
        		break;
        	case 'confirm':
        		_agree = data.agree;
        	}
        },
        isValid: function (cardName) {
        	return cardName != 'confirm' || _agree;
        },
        save: function () {
        	var data = _.clone(_data);
        	delete data.missionConfig;
        	delete data.account;
        	data.endTime = misUtils.renderJsonTime(data.endTime);
            data.startTime = misUtils.renderJsonTime(data.startTime);
            data.missionDate = misUtils.renderJsonDate(data.missionDate);
        	
        	return new Promise(function (resolve, reject) {
                $http.post('create_mission', JSON.stringify(data)).then(function (result) {
                    _data.missionId = result.data.missionId;
                    resolve(_data);
                }, function (result) {
                    console.log('提交失败!请稍候重试。');
                    reject();
                });
        	});
        }
	}
});
