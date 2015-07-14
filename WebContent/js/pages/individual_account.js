(function () {
	var msg = UF.utils.makeMappingTables({
		__namespace: UF.enums.status,
		base: '您已注册成为普通会员，可以订购其他会员发布的服务。若有意提供服务，请点击下方的『编辑』按钮申请成为认证会员。',
		authenticating: '您的认证资料正在审核中，无法在线修改。如有需要请联系客服。',
		authenticated: '您已完成认证，可以在本站发布服务。点击下方的『编辑』按钮可申请商签会员，享受专属服务。',
		contracting: '您的商签协议正在审核中，无法在线修改。如有需要请联系客服。',
		contracted: '您已签署商业协议，不允许更改个人信息。如有需要请联系客服。'
	});
	
	$(document).ready(function () {
		$('small[data-name="message"]').html(msg[model.status]);
		
		$('.btn[data-name="edit"]').prop('disabled', 
				model.status != UF.enums.status.authenticated && model.status != UF.enums.status.base)
			.click(function (e) {
				e.preventDefault();
				if (model.status != UF.enums.status.authenticated && model.status != UF.enums.status.base) return;
				location.href = contextPath + '/individual_complete';
			});
		
		if (model.status == UF.enums.status.normal) {
			$('section[name="authentication"]').hide();
		}
	});
})();

