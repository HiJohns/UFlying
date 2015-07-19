<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh_CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
	<title>忘记密码</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
    <script type="text/javascript" name="interface">
        contextPath = '<%=request.getContextPath()%>';
        model = {
            	phone: '',
            	code: '',
            	password: '',
            	confirmPassword: ''
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
</head>
<body>
    <hgroup class="usersCommon boxWidth">
        <img src="img/Logo.png" />
        <button class="back" data-icon="LeftBlue"></button>
    </hgroup>
    <c:if test="${!empty message}"><div role="alert" class="alert alert-danger">${message}</div></c:if>
    <form id="register-form" action="<%=request.getContextPath()%>/reset_password" method="post">
        <section class="form portal withHeader boxWidth">
            <header>
                <div><img src="img/Right_DarkGray.png" /><span>忘记密码</span></div>
            </header>
            <fieldset>
                <section>
                    <strong>*</strong>
                    <input type="tel" id="phone" name="phone" data-icon="Phone" msg-empty="请填写注册手机号" 
                    	required msg-empty="请填写手机号" data-regexName="mobile" msg-regex="手机号应该为11位数字"
                    	placeholder="请输入注册手机号" aria-describedby="basic-addon1" />
                </section>
                <section>
                	<strong></strong>
                    <button data-name="btnVerify" class="btn btn-primary">获取验证码</button>
                </section>
                <section class="collapse">
                    <strong>*</strong>
                    <input type="text" id="code" name="code" required="required" 
                    	msg-empty="请填写验证码" placeholder="请填写验证码" data-regex="^\d{6}$" msg-regex="验证码应该为6位数字"
                    	aria-describedby="basic-addon1" data-icon="Key" />
                </section>
            </fieldset>
        </section>
        <section class="form portal boxWidth">
            <fieldset>
                <section>
                    <strong>*</strong>
                    <input type="password" id="password" name="password" msg-empty="请填写新密码" required="required" 
                    placeholder="填写新密码" data-match="confirmPassword" msg-match="应与确认密码一致"
                    aria-describedby="basic-addon1" data-icon="Lock" />
                </section>
                <section>
                    <strong>*</strong>
                    <input type="password" placeholder="填写确认密码" msg-empty="请填写同一密码" 
                    	name="confirmPassword" data-match="password" msg-match="两次填写的密码应该一致"
                    required="required" data-icon="Lock" />
                </section>
            </fieldset>
        </section>
        <section class="form portal boxWidth">
            <fieldset>
                <section>
                    <strong></strong>
                    <input type="submit" disabled class="btn" value="下一步" disabled="true"/>
                </section>
            </fieldset>
        </section>
    </form>
</body>
</html>