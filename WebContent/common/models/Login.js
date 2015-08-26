UfCommon.factory('modLogin', function (misThread) {
    var _current = null;
    return {
        load: function (callback) {
			if (_current != null) return callback(_current);
			
			misThread
				.assign('POST', '../../login_info', null)
				.then(function (data) {
					_current = data.account;
					callback(_current);
				}, function (status) {
					console.log('登录信息载入失败，错误码：' + status);
				});
        },
        getCurrentUser: function () {
            return _current;
        }
    }
});
