UF.page.IndividualAccount = (function () {
	var msg = UF.base.Utils.makeMappingTables({
		__namespace: UF.base.Enums.status,
		base: '您已注册成为联盟会员，请点击下方的『完善信息』按钮申请成为认证会员，认证会员可享受更多会员服务。',
		authenticating: '您的认证资料正在审核中，无法在线修改。如有需要请联系客服。',
		authenticated: '您认证成为联盟认证会员，可以享受认证会员的各项服务。点击下方的『完善信息』按钮可申请商签会员，享受专属服务。',
		contracting: '您的商签协议正在审核中，无法在线修改。如有需要请联系客服。',
		contracted: '您已签署商业协议，为保证商业协议的法律效力，不允许在线修改个人信息。如有需要请联系客服。'
	});
	
	return {
		dependencies: [
		               'Form'
		               ],
		init: function () {
			$('small[data-name="message"]').html(msg[model.status]);
	
	        $('.btn[data-name="logout"]').click(function () {
	            location.href = contextPath + '/logout';
	        });
			
	        $('.btn[data-name="changePassword"]').click(function () {
	            location.href = contextPath + '/change_password';
	        });
			
			$('.btn[data-name="edit"]').prop('disabled', 
					model.status != UF.base.Enums.status.authenticated && model.status != UF.base.Enums.status.base)
				.click(function (e) {
					e.preventDefault();
					if (model.status != UF.base.Enums.status.authenticated && model.status != UF.base.Enums.status.base) return;
					location.href = contextPath + '/individual_complete';
				});
			
			if (model.status == UF.base.Enums.status.normal) {
				$('section[name="authentication"]').hide();
			}
		}
	};
})();

