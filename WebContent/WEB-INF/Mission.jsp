<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html ng-app="UfMission">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>空中梦想</title>
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
		
		<script src="common/Uf.js"></script>
		<script src="common/components/footer/Footer.js"></script>
		<script src="common/components/videos/Videos.js"></script>
		<script src="common/misc/Utils.js"></script>
		<script src="common/models/Cities.js"></script>
		<script src="common/models/Login.js"></script>
		
		<script src="mission/Mission.js"></script>
		<script src="mission/components/dialogLogin/DialogLogin.js"></script>
		<script src="mission/components/dialogMissionEdit/DialogMissionEdit.js"></script>
		<script src="mission/models/MissionConfigs.js"></script>
		
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="3rdParty/angular-dialog-service/dialogs.min.css">
		<link rel="stylesheet" type="text/css" href="common/Uf.css">
		<link rel="stylesheet" type="text/css" href="mission/Mission.css">
	</head>
	<body ng-controller="Main">
	    <section class="main">
	    	<div class="center">
		        <img src="img/Logo.png"/>
		    	<div class="actions">
			        <div class="btn-group">
			            <img src="img/task/caption.png"/>
			            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" ng-disabled="missionConfigs == null || loading">
			                请选择任务类型 <span class="caret"></span>
			            </button>
			            <ul class="dropdown-menu">
			                <li ng-repeat="config in missionConfigs"><a href="#" ng-click="mission()">{{config.typeName}}</a></li>
			           </ul>
			        </div>
		    	</div>
	    	</div>
	    </section>
	    <com-videos></com-videos>
	    <com-footer></com-footer>
	</body>
</html>