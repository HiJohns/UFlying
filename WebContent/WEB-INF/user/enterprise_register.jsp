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
	<script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" name="interface">
        contextPath = '<%=request.getContextPath()%>';
        model = {
    	    message: "${message}",
        	phone: '',
        	code: '',
        	password: '',
        	confirmPassword: ''
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
</head>
<body class="enterprise">
    <hgroup class="usersCommon boxWidth">
        <img src="img/Logo.png" />
        <button class="back" data-icon="LeftBlue"></button>
    </hgroup>
    <c:if test="${!empty message}"><div role="alert" class="alert alert-danger">${message}</div></c:if>
    <form id="register-form" action="<%=request.getContextPath()%>/enterprise_register" method="post">
        <section class="form portal withHeader boxWidth">
            <header>
                <div><img src="img/Right_DarkGray.png" /><span>企业账号</span></div>
            </header>
            <fieldset>
                <section>
                    <strong>*</strong>
                    <input type="tel" id="phone" name="phone" required 
                    	data-prototype="mobile"	placeholder="请输入手机号" />
                </section>
                <section>
                	<strong></strong>
                    <button id="code_btn" data-name="btnVerify" class="btn btn-primary">获取验证码</button>
                </section>
                <section class="collapse">
                    <strong>*</strong>
                    <input type="text" id="code" name="code" required data-prototype="mobileVerification"
                    	placeholder="请输入验证码" />
                </section>
            </fieldset>
        </section>
        <section class="form portal boxWidth">
            <fieldset>
                <section>
                    <strong>*</strong>
                    <input type="password" id="password" name="password" required
                    placeholder="填写密码" data-prototype="password"/>
                </section>
                <section>
                    <strong>*</strong>
                    <input type="password" placeholder="填写确认密码" 
                    	name="confirmPassword" data-prototype="confirmPassword" required />
                </section>
            </fieldset>
        </section>
        <section class="form portal boxWidth">
            <fieldset>
                <section>
                    <strong></strong>
                    <input type="submit" disabled class="btn" value="下一步"/>
                </section>
            </fieldset>
        </section>
    </form>
</body>
</html>
