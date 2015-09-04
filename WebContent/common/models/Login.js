UfCommon.factory('modLogin', function (misThread) {
    var _current = null;
    var _watchers = [];
    return {
        load: function (callback) {
			if (_current != null) return callback(_current);
			
			misThread
				.assign('POST', '../../login_info', null, 60000)
				.then(function (data) {
					_current = data.account;
					callback(_current);
					_.each(_watchers, function (watcher) {
						watcher(_current);
					})
				}, function (status) {
					console.log('登录信息载入失败，错误码：' + status);
					_current = null;
					callback(null);
				});
        },
        getCurrentUser: function () {
            return _current;
        }, 
        watch: function (callback) {
        	_watchers.push(callback);
        }
    }
});
