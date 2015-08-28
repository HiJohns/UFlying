<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html ng-app="UfInsurance">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>空中梦想</title>

        <base href="<%=request.getContextPath() %>/">

		<link rel="stylesheet" href="3rdParty/bootstrap/css/bootstrap.min.css">

		<script src="3rdParty/angular/angular.min.js"></script>
		<script src="3rdParty/angular-sanitize/angular-sanitize.min.js"></script>
		<script src="3rdParty/angular-translate/angular-translate.min.js"></script>
        <script src="3rdParty/angular-localize/angular-localize_zh-cn.js"></script>
	    <script src="3rdParty/jquery/jquery.min.js" type="text/javascript"></script>
	    <script src="3rdParty/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
	    <script src="3rdParty/underscore/underscore-min.js" type="text/javascript"></script>
	    <script src="3rdParty/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="3rdParty/angular-bootstrap/ui-bootstrap.js"></script>
		<script src="3rdParty/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
		<script src="3rdParty/angular-dialog-service/dialogs-default-translations.min.js"></script>
		<script src="3rdParty/angular-dialog-service/dialogs.min.js"></script>
		<script src="3rdParty/angular-cookies/angular-cookies.min.js"></script>
		<script src="3rdParty/angular-route/angular-route.min.js"></script>
		
		<script src="common/Uf.js"></script>
		<script src="common/components/scrollview/Scrollview.js"></script>		
		<script src="common/components/footer/Footer.js"></script>
		<script src="common/components/videos/Videos.js"></script>
		<script src="common/misc/Utils.js"></script>
		<script src="common/models/Cities.js"></script>
		<script src="common/models/Login.js"></script>
		
		<script src="insurance/Insurance.js"></script>
		<script src="insurance/models/instructions/Application.js"></script>
		<script src="insurance/models/instructions/Claim.js"></script>
		<script src="insurance/models/instructions/Faq.js"></script>
		<script src="insurance/models/instructions/Info.js"></script>
		<script src="insurance/components/instructions/application/Application.js"></script>
		<script src="insurance/components/instructions/claim/Claim.js"></script>
		<script src="insurance/components/instructions/faq/Faq.js"></script>
		<script src="insurance/components/instructions/info/Info.js"></script>
		<script src="insurance/views/instructions/Instructions.js"></script>
		
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="3rdParty/angular-dialog-service/dialogs.min.css">
		<link rel="stylesheet" type="text/css" href="common/Uf.css">
		<link rel="stylesheet" type="text/css" href="insurance/Insurance.css">
</head>

<body ng-controller="Main">
	<ng-view></ng-view>
    <com-footer></com-footer>
</body>
</html>
			