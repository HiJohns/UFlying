UF.business.VerificationByMobile = (function () {
	var _form = null;
	
	function startCountDown(btn) {
		var count = 60;
		
		var handle = setInterval(function () {
			$(btn).text(count + '秒后可重新获取验证码');
			if (count-- == 0) {
				clearInterval(handle);
				$(btn).text('重新获取验证码').prop('disabled', false);
			}
		}, 1000);
	}

	function requestCode(mobile) {
		$.ajax({
			type : 'post',
			url : contextPath + '/register_verify_code',
			data : {
				phone : mobile
			},
			error : function() {
				alert('验证码发送失败，请稍后再试');
			},
			success : function(data) {
				startCountDown($(_form).find('button[data-name="btnVerify"]'));
				$(_form).find('.collapse').addClass('in').find('input').each(UF.business.Form.validate);
				$(_form).find('input[type="submit"]').prop('disabled', false);
			}
		});
	}
	
	return {
		dependencies: [
		               'Form'
		               ],
		getVerifyCode: function () {
			_form = this;
			$(_form).find('button[data-name="btnVerify"]').prop('disabled', false)
			.click(function (e) {
				e.preventDefault();
				var phone = $(_form).find('input[name="phone"]').each(UF.business.Form.validate);
				if ($(phone).is('.invalid')) return;
				
				$(this).prop('disabled', true).text('正在发送……');
				
				requestCode($(phone).val());
			});
		}
	}
})();