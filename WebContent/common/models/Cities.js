UfCommon.factory('modCities', ['$http', 'misThread', function ($http, misThread, scope) {
	function buildCache(data) {
		var result = {};
		var provinces = {};
		_.each(data, function (record) {
			switch (record.regionType) {
			case 1:
				provinces[record.regionId] = record;
				result[record.regionName] = [];
				break;
			case 2:
				result[provinces[record.parentId].regionName].push(record.regionName);
				break;
			}
		});
		
		return result;
	}
	
	var cache = null;
	return {
		load: function (callback) {
			if (cache != null) return callback(cache);
			
			misThread
				.assign('POST', '../../region', null)
				.then(function (data) {
					callback(buildCache(data));
				}, function (status) {
					console.log('省市信息载入失败，错误码：' + status);
				});
		}
	}
}])
