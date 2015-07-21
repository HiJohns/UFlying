<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh_CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
	<script type="text/javascript" name="interface">
	    model = {
	    };
	    contextPath = '<%=request.getContextPath()%>';
	</script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
    <title>登录</title>
    <style type="text/css">
        hgroup.usersCommon {
            padding-left: 0px;
        }
        hgroup img {
            float: none;
        }
        .login {
            text-align: center;
        }
        .login a {
            color: #999;
        }
        .login footer {
            background-color: rgb(79,96,111);
            height: 55px;
            box-sizing: border-box;
            padding-top: 17px;
        }
        .login footer a {
            border: 0 none;
            color: white;
            cursor: pointer;
            float: none !important;
            margin-left: auto !important;
            margin-right: auto !important;
            padding-right: 25px;
            text-decoration: none;
            font-size: small;
        }
        .login footer img {
        	margin-right: 10px;
        }
</style>
</head>
<body class="login uniPage">
    <hgroup class="usersCommon boxWidth">
        <a href="<%=request.getContextPath()%>/index.jsp"><img src="img/Logo.png" /></a>
    </hgroup>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <div class="portal boxWidth">
            <fieldset class="form">
                <section>
                    <strong>*</strong>
                    <input data-icon="User" type="text" value="${form.login}" placeholder="手机号/会员ID" 
                    	required msg-empty="请填写手机号/会员ID" name="user" aria-describedby="basic-addon1" 
                    	data-regex="^(\d{11}|[GQ]\d{10})$" msg-regex="手机号应该为11位数字，会员ID应该以字母开头，后接10位数字"/>
                </section>
                <section>
                    <strong>*</strong>
                    <input data-icon="Key" type="password" value="${form.password}" required msg-empty="请填写密码" 
                    	placeholder="输入密码" name="password" aria-describedby="basic-addon1" />
                </section>
                <section>
                    <strong></strong>
                    <input type="submit" value="登 录" />
                </section>
                <div class="centerAlign">
                	<a href="<%=request.getContextPath()%>/reset_password"><span>忘记密码？</span></a>
                </div>
           </fieldset>
            <footer>
                <a href="<%=request.getContextPath()%>/accountType">
                	<img src="img/Right_DarkGray.png"/>
                	立即注册
                </a>
            </footer>
        </div>
    </form>
</body>
</html>
