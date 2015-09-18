<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html ng-app="UfMission">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>空中梦想</title>
        <base href="<%=request.getContextPath() %>/">
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
		<script src="common/components/footer/Footer.js"></script>
		<script src="common/components/videos/Videos.js"></script>
		<script src="common/components/dialogLogin/DialogLogin.js"></script>
		<script src="common/components/formPageHeader/FormPageHeader.js"></script>
		<script src="common/components/helpSideBar/HelpSideBar.js"></script>
		<script src="common/misc/Utils.js"></script>
		<script src="common/misc/Thread.js"></script>
		<script src="common/misc/Filters.js"></script>
		<script src="common/models/Cities.js"></script>
		<script src="common/models/Login.js"></script>
		
		<script src="mission/Mission.js"></script>
		<script src="mission/models/MissionConfigs.js"></script>
		<script src="mission/views/main/Main.js"></script>
		<script src="mission/views/missionEdit/MissionEdit.js"></script>
		
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="3rdParty/angular-dialog-service/dialogs.min.css">
		<link rel="stylesheet" type="text/css" href="common/Uf.css">
		<link rel="stylesheet" type="text/css" href="mission/Mission.css">
	</head>
	<body ng-controller="Main">
	    <ng-view></ng-view>
	    <com-footer></com-footer>
	</body>
</html>
