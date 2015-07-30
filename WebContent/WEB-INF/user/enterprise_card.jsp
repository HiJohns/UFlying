<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="zh_CN" xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>电子会员证</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/card.css">
    <style type="text/css">
 	.card .content {
	    background-color: rgb(45, 130, 167);
	}
 	.card footer {
	    background-color: rgb(102, 158, 185);
	}
    </style>
  </head>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
  <body>
  	<div class="card">
  		<div class="content">
  			<img name="photoUrl" class="people">
  			<div class="summary">
  				<p>
  					<label>姓名</label>
  					<span name="companyName"></span>
  				</p>
  				<p>
  					<label>电话</label>
  					<span name="companyPhone"></span>
  				</p>
  			</div>
  			<img src="img/qrcode.png" class="qrcode">
  			<small>
  				<p>使用说明</p>
  				<ol>
  					<li>持卡人为UFlying无人机联盟（以下简称『联盟』）会员。</li>
  					<li>持卡人须遵守联盟相关规定，在联盟框架内行使权利并履行义务。</li>
  					<li>持卡人自动享有空中梦想会员资格，并可享受相应的会员待遇。</li>
  					<li>最终解释权归联盟所有。</li>
  				</ol>
  				<p>服务热线：4006-509-579</p>
  			</small>
  		</div>
  		<footer>
  			<label>会员ID：</label>
  			<span name="eid"></span>
  		</footer>
  	</div>
  	<div class="control">
	  	<a href="javascript:history.go(-1)">点击返回</a> 
  	</div>
  </body>
</html>