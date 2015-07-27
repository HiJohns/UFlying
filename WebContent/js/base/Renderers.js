UF.base.Renderers = {
	sex: {
		0: '女',
		1: '男',
		2: '保密'
	}, 
	uid: function (uid, model){
        if (_.isString(uid) && uid.match(/^[A-Za-z]/) != null) return uid;
		var result = uid.toString();
		while (result.length < 10) result = '0' + result;
		return 'G' + result;
	}, 
	eid: function (eid, model){
        if (_.isString(eid) && eid.match(/^[A-Za-z]/) != null) return eid;
		var result = eid.toString();
		while (result.length < 10) result = '0' + result;
		return 'E' + result;
	}, 
	status: {
		0: '普通用户',
		1: '待认证',
		2: '认证用户',
		3: '待商签',
		4: '商签用户'
	}
}