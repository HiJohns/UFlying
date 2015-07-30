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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
    <style type="text/css">
        .horizon {
        	width: 100%!important;
        }
        .horizon button {
        	width: 27%!important;
        	margin-left: 5px!important;
        	margin-right: 5px!important;
        }
        .staticInfo small {
        	color: red;
        	padding: 10px 30px;
        	float: left;
        }
    </style>
    <script type="text/javascript" name="interface">
        model = {
    	    message: "${message}",
            eid: "${account.eid}",
            companyName: "${account.companyName}",
            companyPhone: "${account.companyPhone}",
            email: "${account.emailAddress}",
            address: "${account.address}",
            province: "${account.province}",
            city: "${account.city}",
            status: "${account.status}",
            name: "${account.name}",
            businessLicenseNumber: "${account.businessLicenceNumber}",
            taxRegistrationNumber: "${account.taxRegistrationNumber}",
            businessLicenceUrl: "${account.businessLicenceUrl}",
            taxRegistrationUrl: "${account.taxRegistrationUrl}",
            photoUrl: "${account.headImgUrl}",
            name: "${account.name}",
            phone: "${account.mobilePhone}",
            sex: "${account.sex}",
            idCardNumber: "${account.idCardNumber}",
            idCardUrl1: "${account.idCardUrl1}",
            idCardUrl2: "${account.idCardUrl2}"
        };
        contextPath = '<%=request.getContextPath()%>';
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
  </head>
<body class="personal_summary">
    <hgroup class="usersCommon boxWidth">
        <a href="<%=request.getContextPath() %>/index.jsp"><img src="img/Logo.png" /></a>
        <button data-icon="LeftBlue" class="back"></button>
    </hgroup>
    <section class="portal withHeader boxWidth">
        <header>
            <div><img src="img/Right_DarkGray.png" alt="Next" /><span>会员信息</span></div>
        </header>
        <div class="staticInfo">
            <section>
                <label>会员ID：</label>
                <span name="eid"/>
            </section>
            <section>
                <label>认证会员状态：</label>
                <span><span name="status"></span></span>
            </section>
            <section class="authentication">
                <label>电子会员证：</label>
                <span>点击查看<a href="<%=request.getContextPath()%>/enterprise_card">&gt;&gt;&gt;</a></span>
            </section>
        </div>
    </section>
    <section class="portal boxWidth alertBox">
    	<div class="staticInfo alert" name="message">
   		</div>
	</section>
    <section class="portal boxWidth">
        <div class="staticInfo">
            <section>
                <label>企业名称：</label>
                <span name="companyName"></span>
            </section>
            <section>
                <label>企业邮箱：</label>
                <span name="email"/>
            </section>
            <section>
                <label>固定电话：</label>
                <span name="companyPhone"/>
            </section>
            <section>
                <label>所在地：</label>
                <span name="province+city"></span>
            </section>
            <section>
                <label>地址：</label>
                <span name="address"></span>
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
                <label>联系人手机：</label>
                <span name="phone"/>
            </section>
        </div>
    </section>
    <section class="portal boxWidth authentication">
        <div class="staticInfo">
            <section>
                <label>联系人身份证号码：</label>
                <span name="idCardNumber"></span>
            </section>
            <section>
                <label>联系人证件照正面：</label>
                <img name="idCardUrl1"/>
            </section>
            <section>
                <label>联系人证件照反面：</label>
                <img name="idCardUrl2"/>
            </section>
        </div>
    </section>
    <section class="portal boxWidth authentication">
        <div class="staticInfo">
            <section>
                <label>企业Logo：</label>
                <img name="photoUrl"/>
            </section>
            <section>
                <label>营业执照号码：</label>
                <span name="businessLicenseNumber"></span>
            </section>
            <section>
                <label>营业执照照片：</label>
                <img name="businessLicenseUrl"/>
            </section>
            <section>
                <label>组织机构证号码：</label>
                <span name="taxRegistrationNumber"></span>
            </section>
            <section>
                <label>组织机构证照片：</label>
                <img name="businessLicenseUrl"/>
            </section>
        </div>
    </section>
    <section class="portal boxWidth">
    	<div class="staticInfo">
    		<small data-name="message">
    		</small>
   		</div>
	</section>
    <section class="portal boxWidth form">
    	<fieldset>
    		<section class="horizon">
    			<button class="btn btn-primary" data-name="edit">完善信息</button>
    			<button class="btn btn-default" data-name="changePassword">修改密码</button>
    			<button class="btn btn-danger" data-name="logout">退出</button>
    		</section>
    	</fieldset>
    </section>
</body>
</html>
