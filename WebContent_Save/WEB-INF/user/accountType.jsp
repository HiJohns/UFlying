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
        <title>会员类型</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
        <style type="text/css">
            .boxWidth {
                width: 360px;
                border-radius: 4px;
                padding: 5px 14px;
            }

            .boxWidth:not(:first-child) {
                margin: 45px auto 55px;
                text-align:center;
            }

            label {
                background-color: transparent;
                border: 4px solid white;
                border-radius: 5px;
                color: white;
                font-family: simsun;
                font-size: 1.1em;
                font-weight: bold;
                padding: 5px 14px;
            }

            .boxWidth:last-child a {
                background-color: transparent;
                border: 4px solid white;
                border-radius: 75px;
                height: 151px;
                width: 151px;
                cursor: pointer;
                text-decoration: none;
            }

            .boxWidth:last-child a img {
                margin-top: 17px;
            }

            .boxWidth:last-child div {
                font-family: simsun;
                color: white;
                font-size: 1.1em;
                font-weight: bold;
                margin-top: 5px;
            }

            .boxWidth:last-child a:first-child {
                float: left;
            }
            .boxWidth:last-child a:last-child {
                float: right;
            }
            @media only screen and (min-width: 600px) {
                .boxWidth {
                    width: 360px;
                    border-radius: 4px;
                    padding: 5px 14px;
                }

                .boxWidth:not(:first-child) {
                    margin: 45px auto 55px;
                    text-align:center;
                }

                label {
                    background-color: transparent;
                    border: 4px solid white;
                    border-radius: 5px;
                    color: white;
                    font-family: simsun;
                    font-size: 1.1em;
                    font-weight: bold;
                    padding: 5px 14px;
                }

                .boxWidth:last-child button {
                    background-color: transparent;
                    border: 4px solid white;
                    border-radius: 75px;
                    height: 151px;
                    width: 151px;
                    cursor: pointer;
                }

                .boxWidth:last-child div {
                    font-family: simsun;
                    color: white;
                    font-size: 1.1em;
                    font-weight: bold;
                    margin-top: 5px;
                }

                .boxWidth:last-child button:first-child {
                    float: left;
                }
                .boxWidth:last-child button:last-child {
                    float: right;
                }
            }
        </style>
    </head>
    <script type="text/javascript" name="interface">
        contextPath = '<%=request.getContextPath()%>';
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<body class="accountType">
    <hgroup class="usersCommon boxWidth">
        <img src="img/Logo.png" />
        <button class="back" data-icon="LeftBlue"></button>
    </hgroup>
    <div class="boxWidth"><label>请选择您的账号类型</label></div>
    <div class="boxWidth">
        <a name="individual" href="<%=request.getContextPath()%>/individual_register"><img src="img/Personal.png" /><div>个人账户</div></a>
        <a name="enterprise" href="<%=request.getContextPath()%>/enterprise_register"><img src="img/Corporate.png"/><div>企业账户</div></a>
    </div>
</body>
</html>
