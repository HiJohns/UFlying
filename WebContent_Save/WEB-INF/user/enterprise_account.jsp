<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh_CN" xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>会员信息</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
    <style type="text/css">
    	.logout {
    		background-color: rgb(90, 194, 231);
    	}
        span[name="status"] + a {
            margin-left: 10px;
        }
    </style>
    <script type="text/javascript" name="interface">
        contextPath = '<%=request.getContextPath()%>';
        model = {
       			eid: "${account.eid}"
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
    <script type="text/javascript">
    	$(document).ready(function () {
    		$('button.logout').click(function () {
    			location.href = contextPath + '/logout';
    		});

            if (model.status == _enums.status.base || model.status == _enums.status.authenticated) {
                $('span[name="status"] + a').show();
            }
            else {
                $('span[name="status"] + a').hide();
            }
    	});
    </script>
  </head>
<body class="personal_summary">
    <hgroup class="usersCommon boxWidth">
        <img src="img/Logo.png" />
        <button data-icon="LeftBlue" class="back"></button>
    </hgroup>
    <section class="portal withHeader boxWidth">
        <header>
            <div><img src="img/Right_DarkGray.png" alt="Next" /><span>会员信息</span></div>
        </header>
        <div class="staticInfo">
            <section>
                <label>联系人手机：</label>
                <span name="phone"/>
            </section>
            <section>
                <label>会员ID：</label>
                <span name="eid"/>
            </section>
        </div>
    </section>
    <section class="portal boxWidth">
        <div class="staticInfo">
            <section>
                <label>联系人姓名：</label>
                <span name="name"/>
            </section>
            <section>
                <label>性别：</label>
                <span name="sex"/>
            </section>
            <section>
                <label>企业名称：</label>
                <span>XXX有限公司</span>
            </section>
            <section>
                <label>企业邮箱：</label>
                <span name="email"/>
            </section>
            <section>
                <label>固定电话：</label>
                <span name="phone"/>
            </section>
            <section>
                <label>所在地：</label>
                <span name="province+city"></span>
            </section>
        </div>
    </section>
    <section class="portal boxWidth">
        <div class="staticInfo">
            <section>
                <label>认证会员状态：</label>
                <span><span name="status"></span><a href="<%=request.getContextPath()%>/enterprise_complete">我要认证>></a></span>
            </section>
            <section>
                <label>联系人身份证号：</label>
                <span name="idCardNumber"></span>
            </section>
            <section>
                <label>联系人照片：</label>
                <span>证件照<a href="#">>></a><br/>身份证照片<a href="#">>></a></span>
            </section>
            <section>
                <label>企业证件照片：</label>
                <span>营业执照照片<a href="#">>></a><br/>机构证照片<a href="#">>></a></span>
            </section>
        </div>
    </section>
    <section class="portal boxWidth form">
    	<fieldset>
    		<section>
    			<strong></strong>
    			<button class="logout">登 出</button>
    		</section>
    	</fieldset>
    </section>
</body>
</html>
