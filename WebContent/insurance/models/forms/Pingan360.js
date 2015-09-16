UfInsurance.factory('modFormsPingan360', function ($http, Upload, misUtils) {
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
    
    function _save() {
    	return new Promise(function (resolve, reject) {
    		console.log('提交对象：', _data)
    		resolve();
    	});
    }
    
    function _uploadFiles() {
	    return new Promise(function (resolve, reject) {
			function uploadFile(config, callback) {
				Upload.upload({
					url: 'upload',
					fields: {
						uid: _data.insuree.uid
					},
					file: config.file
				})
				.success(function (data, status) {
	                config.tgtObj[config.field] = data.fileHandle;

					console.log('文件上传成功: ', arguments);
		            callback(true);
				})
				.error(function (data, status) {
					console.log(config.file.name + '上传失败!')
		            callback(false);
				});
			}

		    function uploaded(allSuccess) {
		        if (allSuccess) {
		        	for (var field in _data) {
		        		if (/flyerPictures_\d+/.test(field) || field === 'receiptFile') {
		        			delete _data[field];
		        		}
		        	}
		        	
		            resolve();
		        }
		        else {
		            reject();
		        }
		    }
			try {
		        var tasks = [];
				_.each([
				        { file: _data.flyerPictures_0, tgtObj: _data.flyerPictures, field: 0 },
				        { file: _data.flyerPictures_1, tgtObj: _data.flyerPictures, field: 1 },
				        { file: _data.flyerPictures_2, tgtObj: _data.flyerPictures, field: 2 },
				        { file: _data.flyerPictures_3, tgtObj: _data.flyerPictures, field: 3 },
				        { file: _data.flyerPictures_4, tgtObj: _data.flyerPictures, field: 4 },
				        { file: _data.flyerPictures_5, tgtObj: _data.flyerPictures, field: 5 },
				        { file: _data.receiptFile, tgtObj: _data, field: 'receipt' }
				        ], function (config) {
		                    tasks.push(uploadFile.bind(null, config));
		                });
				
		        misUtils.waitForAll(uploaded, tasks);
			}
			catch (e) {
				console.log(e);
				reject();
			}
	    });
    }

	return {
        getData: function (cardName) {
            return _.clone(_data);
        },
        setData: function (cardName, data) {
        	_.extend(_data, data);
        },
        isValid: function (cardName) {
        	return _.isObject(_data.flyer) &&
        		_data.flyerPictures.length === 6 && 
        		_.isString(_data.owner) && _data.owner.length > 0 &&
        		_.isObject(_data.receipt) && 
        		_.isNumber(_data.price) &&
        		_.isObject(_data.insuree)
        },
        save: function () {
        	return new Promise(function (resolve, reject) {
            	_uploadFiles()
        		.then(function () {
        			_save().then(resolve, reject);
        		}, reject);
        	});
        }
	}
})
