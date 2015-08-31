UfInsurance.factory('modFormsPingan360', function ($http) {
	return {
		create: function () {
			return {
				flyer: {
					mode: null, // 机型代码，整数
					vendor: null, // 厂商代码，整数
					weight: null // 飞机重量，浮点
				}, 
				picture: [], // 飞机照片句柄，字符串数组，长度为6
				owner: null, // 设备所有人，字符串
				receipt: null, // 发票句柄，字符串
				control: null, // 飞控编号
				price: null,    // 飞行价格
				insuree: { // 被保险人信息
					id: 0,	// 会员ID
					type: 0, // 会员类型
					name: null, // 会员名
					phone: null,	// 会员电话
					idCardNumber: null,	// 会员身份证号
					idCardPicture: null,	// 会员身份证照片句柄
					address: null	// 会员地址
				}, 
				maximum: null	// 赔偿上限
			}
		}
	}
})