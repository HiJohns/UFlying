UfCommon.factory('misLogin', ['$http', 'modLogin', function ($http, modLogin, dialogs) {
    function _prompt(callback) {
        dialogs.create(
            	'common/components/dialogLogin/DialogLogin.html',
                'comDialogLogin',
                {},
                {
                    size:'md',
                    keyboard: true,
                    backdrop: false
                }).result.then(function (account) {
                    var d = new Date();
                    $cookies.put(
                        'token', 
                        account.token, { 
                            path: '/', 
                            expires: new Date(
                                d.getFullYear(), 
                                d.getMonth(),
                                d.getDate() + 15, 
                                d.getHours(),
                                d.getMinutes()
                            )
                        });

                    callback(account);
                });
    }
    
	return {
		promptLogin: function (callback) {
			modLogin.load(function (data) {
				if (_.isObject(data)) {
					callback(data);
				}
				else {
					_prompt(callback);
				}
			});
		}
	}
}]);