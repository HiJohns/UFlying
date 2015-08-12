UFlying.factory('cities', ['$http', function ($http, scope) {
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
			
			$http.post('../region').then(function (response) {
				callback(buildCache(response.data));
			}, function (response) {
				console.log('省市信息载入失败，错误码：' + response.status);
			});
		}
	}
}])
