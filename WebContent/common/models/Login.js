UfCommon.factory('modLogin', function ($http) {
    var _current = null;
    return {
        getLoginInfo: function () {
            function onLoginInfo(response) {
                _current = response.account;
                if (_.isFunction(_success)) {
                    _success(_current);
                }
            }

            function onLoginInfoFailure(response) {
                console.log('login failed:', response);
                if (_.isFunction(_failure)) {
                    _failure(response);
                }
            }

            var _success = null;
            var _failure = null;

            $.ajax({
                url: contextPath + 'login_info',
                method: 'post',
                success: onLoginInfo,
                error: onLoginInfoFailure
            });

            return {
                then: function (success, failure) {
                    _success = success;
                    _failure = failure;
                }
            }
        },
        getCurrentUser: function () {
            return _current;
        }
    }
});
