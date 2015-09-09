UfCommon.factory('modDevices', function (misThread) {
	var _cache = null;
	return {
		load: function (callback) {
			if (_cache != null) return callback(_cache);
			
			misThread
				.assign('POST', '../../device', null)
				.then(function (data) {
					_cache = data;
					callback(_cache);
				}, function (status) {
					console.log('飞行器载入失败，错误码：' + status);
				});
		}
	}
})