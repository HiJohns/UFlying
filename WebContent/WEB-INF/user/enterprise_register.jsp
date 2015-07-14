<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh_CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>会员注册</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" name="interface">
        contextPath = '<%=request.getContextPath()%>';
        model = {
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>

    <script type="text/javascript">
function getVerifyCode() {
	var phone = $('#phone').val();

	if (phone == '') {
		alert('请输入手机号');
		return;
	}
	$('#code_btn').text("正在发送...").attr('href', 'javascript:void(0);');
	$.ajax({
		type : 'post',
		url : '<%=request.getContextPath()%>/register_verify_code',
		data : {
			phone : phone
		},
		error : function() {
			alert('验证码发送失败，请稍后再试');
		},
		success : function(data) {
			setInterval("timer()", 1000);
			alert(data.reason);
		}
	});
}
var seconds = 60;
function timer() {
	seconds = seconds - 1;
	if (seconds > 0) {
		$('#code_btn').text(seconds + "秒后可再次获取").attr('href', 'javascript:void(0);');
	} else {
		$('#code_btn').text("获取验证码").attr('href', 'javascript:getVerifyCode();');
	}
}
$(document).ready(preventPost);
function preventPost() {
	$('form:first').submit(function() {
		$('input[type="submit"]').val('正在保存...').attr('disabled', 'disabled');
	});
}
</script>
</head>
<body class="personal">
    <hgroup class="usersCommon boxWidth">
        <img src="img/Logo.png" />
        <button class="back" data-icon="LeftBlue"></button>
    </hgroup>
    <c:if test="${!empty message}"><div role="alert" class="alert alert-danger">${message}</div></c:if>
    <form id="register-form" action="<%=request.getContextPath()%>/individual_register" method="post">
        <div class="form portal withHeader boxWidth">
            <header>
                <div><img src="img/Right_DarkGray.png" /><span>企业账号</span></div>
            </header>
            <fieldset>
                <section>
                    <strong>*</strong>
                    <input type="tel" id="phone" name="phone" data-icon="Phone" value="${form.phone}" required="required" placeholder="请输入手机号" aria-describedby="basic-addon1" />
                    <a id="code_btn" href="javascript:getVerifyCode();">获取验证码</a>
                </section>
                <section>
                    <strong>*</strong>
                    <input type="text" id="code" name="code" required="required" placeholder="请输入验证码" aria-describedby="basic-addon1" data-icon="Key" />
                </section>
                <section>
                    <strong>*</strong>
                    <input type="password" id="password" name="password" required="required" placeholder="设置密码" aria-describedby="basic-addon1" data-icon="Lock" />
                </section>
                <section>
                    <strong>*</strong>
                    <input type="password" placeholder="确认密码" name="confirmPassword" data-icon="Lock" />
                </section>
                <section>
                    <strong></strong>
                    <input type="submit" value="下一步" />
                </section>
            </fieldset>
        </div>
    </form>
</body>
</html>
