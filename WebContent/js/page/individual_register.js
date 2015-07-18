function getVerifyCode() {
	var phone = $('#phone').val();

	if (phone == '') {
		alert('请输入手机号');
		return;
	}
	$('#code_btn').text("正在发送...").attr('href', 'javascript:void(0);');
	$.ajax({
		type : 'post',
		url : contextPath + '/register_verify_code',
		data : {
			phone : phone
		},
		error : function() {
			alert('验证码发送失败，请稍后再试');
		},
		success : function(data) {
			setInterval("timer()", 1000);
			$('.collapse').addClass('in').find('input').each(UF.utils.validate);
			
			$('input[type="submit"]').prop('disabled', false);
			// alert(data.reason);
		}
	});
}
var seconds = 60;
function timer() {
	seconds = seconds - 1;
	if (seconds > 0) {
		$('#code_btn').text(seconds + "秒后可再次获取").attr('href', 'javascript:void(0);');
	} else {
		$('#code_btn').text("重新获取验证码").prop('disabled', false).attr('href', 'javascript:getVerifyCode();');
	}
}

$(document).ready(function () {
	$('#code_btn').click(function (e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		getVerifyCode();
	});
})