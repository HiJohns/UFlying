UF.base.Prototypes = {
	mobile: {
        emptyMessage: '请填写手机号',
        regexName: 'mobile',
        regexMessage: '手机号应该为11位数字',
        remote: 'mobileRegistered',
        remoteCheckingMessage: '验证中……',
        remoteMessage: '该手机已注册过',
        icon: 'Phone' 
    }, 
    mobileVerification: {
    	emptyMessage: '请填写验证码',
        regexName: 'mobileVerification',
        regexMessage: '验证码应该为6位数字',
        icon: 'Key'
    }, 
    password: {
    	emptyMessage: '请填写密码',
        match: 'confirmPassword',
        matchMessage: '应与确认密码一致',
        icon: 'Lock'
    },
    name: {
        icon: 'Name',
        emptyMessage: '请填写姓名'
    },
    ename: {
        icon: 'EnterpriseName',
        emptyMessage: '请填写企业名称'
    },
    phone: {
        icon: 'Phone',
        emptyMessage: '请填写固定电话'
    },
    confirmPassword: {
    	icon: 'Lock',
        emptyMessage: '请填写同一密码',
        match: 'password',
        matchMessage: '两次填写的密码应该一致'
    },
    email: {
    	icon: 'Mail',
        regexName: 'email',
        regexMessage: '邮件地址格式不正确',
        emptyMessage: '请填写Email地址'
    },
    province: {
    	emptyMessage: '请填写省/直辖市/自治区',
        store: 'provinces',
        valueField: 'name',
        textField: 'name'
    },
    city: {
    	emptyMessage: '请填写城市',
        store: 'cities',
        valueField: 'name',
        textField: 'name'
    },
    address: {
    	emptyMessage: '请填写地址',
        icon: 'Address'
    },
    qq: {
    	 icon: 'qq',
        emptyMessage: '请填写QQ号'
    },
    idCardNumber: {
    	icon: 'Identity',
        emptyMessage: '请填写身份证号码',
        regexName: 'idCardNumber',
        regexMessage: '身份证号码应该为18位数字'
    },
    init: function () {
    	for (var field in this) {
    		if (field != 'init' && _.isObject(this[field])) {
    			this[field] = UF.base.Utils.makeMappingTable(this[field], UF.base.FormLabels);
    		}
    	}
    }
};
	
