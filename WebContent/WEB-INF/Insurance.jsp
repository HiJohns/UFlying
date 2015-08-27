<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>空中梦想</title>

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
		<script src="common/Uf.js"></script>
		<script src="common/components/scrollview/Scrollview.js"></script>		
		<script src="common/components/footer/Footer.js"></script>
		<script src="common/components/videos/Videos.js"></script>
		<script src="common/misc/Utils.js"></script>
		<script src="common/models/Cities.js"></script>
		<script src="common/models/Login.js"></script>
		<script src="insurance/Insurance.js"></script>
		
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="3rdParty/bootstrap/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="3rdParty/angular-dialog-service/dialogs.min.css">
		<link rel="stylesheet" type="text/css" href="common/Uf.css">
		<link rel="stylesheet" type="text/css" href="insurance/Insurance.css">
</head>

<body ng-app="UfInsurance" >
		<section class="main" ng-controller="Main" >
		<div class="center">
	
			<img src="img/insurance/logo.png" width=100%/>
		
			<tabset>
				<tab heading="保险种类" class="heading">		
				<com-scrollview images="images" myInterval="myInterval"></com-scrollview>
				</tab>
				<tab heading="保险流程" class="heading"><img align="center" src="img/insurance/process.png" width=100%>
				  <br>
				  <ol>
                  <li ng-repeat="x in apply">
                     {{ x.value }}
		          </li>
		          </ol> 
		          <b>投保所需资料</b>
		          <ul>
		         		<li ng-repeat="x in types">
				    		<b>{{ x.type}}</b><p /> {{ x.resource }}
				  		</li>
				  </ul> 
				  
				</tab>
				<tab heading="常见问题" class="heading">   
					         
					<img align="center" src="img/insurance/question.png"  width=100%>
					<p />
				  	<ul>
				  	<li ng-repeat="x in questions">
				    	<b>{{ x.question}}</b><p /> {{ x.answer }}
				  	</li>
				  	</ul>  
		  </tab>
				<tab heading="理赔流程" class="heading">
				<p />
				<br />
			<b>UFLYING无人机联盟理赔流程</b>
          
              <b>机身受损</b>
              <ol>
                  <li ng-repeat="x in processes">
                     {{ x.value }}
          </li>
          </ol>   
			
			<b>造成第三者人身或财产损失</b>
                <ol>
                    <li ng-repeat="x in processes2">
                        {{ x.value }}
                    </li>
                </ol>  
			
			<b>理赔所需资料</b>
                <ol>
                    <li ng-repeat="x in processes3">
                        {{ x.value }}
                    </li>
                </ol>  
			注：单纯机身损失，提供1、2项资料，如涉及第三者财产及人身损失，需另提供3、4、5、6项资料      
			</tab>
			</tabset>
			</div>
				</section>
	    <com-footer></com-footer>
</body>
</html>
			