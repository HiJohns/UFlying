UfInsurance.factory('modFormsPingan360', function ($http) {
    var _data = {
        flyer: null, 
        flyerPictures: [], // 飞机照片句柄，字符串数组，长度为6
        owner: null, // 设备所有人，字符串
        receipt: null, // 发票句柄，字符串
        control: null, // 飞控编号
        price: null,    // 飞行价格
        insuree: null, 
        maximum: null	// 赔偿上限
    };
    
    function temp() {
		function uploadFile(config, callback) {
			Upload.upload({
				url: 'upload',
				fields: {
					uid: $scope.data.insuree.uid
				},
				file: config.file
			})
			.success(function (data, status) {
	            if (_.isArray(config.target)) {
	                config.target[config.index] = data.fileHandle;
	            }
	            else {
	                config.target = data.fileHandle;
	            }

				console.log('success: ', arguments);
	            callback(true);
			})
			.error(function (data, status) {
				console.log('Uploading ' + config.file.name + 'failed!')
	            callback(false);
			});
		}

	    function uploaded(allSuccess) {
	        if (allSuccess) {
	            $scope.$emit('saved');
	        }
	        else {
	            alert('部分文件上传失败，请稍候重试。');
	        }
	    }
		
        var tasks = [];
		_.each([
		        { file: data.flyerPictures_0, target: data.flyerPictures, index: 0 },
		        { file: data.flyerPictures_1, target: data.flyerPictures, index: 1 },
		        { file: data.flyerPictures_2, target: data.flyerPictures, index: 2 },
		        { file: data.flyerPictures_3, target: data.flyerPictures, index: 3 },
		        { file: data.flyerPictures_4, target: data.flyerPictures, index: 4 },
		        { file: data.flyerPictures_5, target: data.flyerPictures, index: 5 },
		        { file: data.receiptFile, target: $scope.data.receipt }
		        ], function (config) {
                    tasks.push(uploadFile.bind(null, config));
                });

        misUtils.waitForAll(uploaded, tasks);
    }

	return {
        getData: function (cardName) {
            return _.clone(_data);
        },
        setData: function (cardName, data) {
			console.log('Pingan360: setData');
        	_.extend(_data, data);
        	return new Promise(function (resolve, reject) {
        		resolve(_data);
        	});
        },
        isValid: function (cardName) {
        	return _.isObject(_data.flyer) &&
        		_data.flyerPictures.length === 6 && 
        		_.isString(_data.owner) && _data.owner.length > 0 &&
        		_.isObject(_data.receipt) && 
        		_.isNumber(_data.price) &&
        		_.isObject(_data.insuree)
        }
	}
})
