<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh_CN">
    <head>
        <title>完善信息</title>
        <meta charset="utf8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/3rdParty/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/users.css">
        <script type="text/javascript" name="interface">
        	model = {
                eid: "${form.eid}"
        	};
            contextPath = '<%=request.getContextPath()%>';
        </script>

        <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Cities.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
    </head>
    <body class="enterprise_complete">
        <hgroup class="usersCommon boxWidth">
            <img src="img/Logo.png" />
            <button data-icon="LeftBlue" class="back"></button>
        </hgroup>
        <c:if test="${!empty message}"><div role="alert" class="alert alert-danger">${message}</div></c:if>
        <form action="<%=request.getContextPath()%>/individual_edit" enctype="multipart/form-data"  method="post">
            <section class="portal withHeader boxWidth">
                <header>
                    <div><img src="img/Right_DarkGray.png" alt="Next" /><span>基本信息</span></div>
                </header>
                <div class="staticInfo">
                    <section>
                        <label>会员ID：</label>
                        <span name="uid"></span>
                        <input type="hidden" name="uid"/>                        
                    </section>
                    <section>
                        <label>联系人手机：</label>
                        <span name="phone"></span>
                        <input type="hidden" name="phone"/>                        
                    </section>
                </div>
            </section>
            <section class="portal boxWidth form">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" placeholder="输入企业名称" name="ename" />
                    </section>
                    <section>
                        <strong>*</strong><input type="text" data-icon="Name" placeholder="输入联系人姓名" name="name"/>
                    </section>
                    <section class="horizon noFrameRow">
                        <strong>*</strong>
                        <input type="radio" name="sex" value="0" id="personal_female" />
                        <label for="personal_female">女</label>
                        <input type="radio" name="sex" value="1" id="personal_male" />
                        <label for="personal_male">男</label>
                        <input type="radio" name="sex" value="2" id="personal_secret" />
                        <label for="personal_secret">保密</label>
                    </section>
                    <section>
                        <strong>*</strong><input type="email" data-icon="Mail" placeholder="输入邮箱" name="email"/>
                        <a href="getConfirmationCode">立即验证</a>
                    </section>
                    <section>
                        <strong>*</strong><input type="tel" data-icon="Phone" placeholder="输入固定电话" name="phone" />
                    </section>
                    <section class="horizon">
                        <strong>*</strong>
                        <select name="province">
                            <option value="" disabled selected>省/直辖市/自治区</option>
                        </select>
                        <select name="city" disabled="">
                            <option disabled selected>城市</option>
                        </select>
                    </section>
                    <section>
                        <strong>*</strong>
                        <input type="text" placeholder="输入地址" name="address" data-icon="Address"/>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form collapseHeader">
                <fieldset>
                    <section class="noFrameRow">
                        <strong></strong>
                        <input type="checkbox" id="toAuth" data-toggle="collapse" data-target=".form.collapse" aria-expanded="false"/> 
                        <label for="toAuth">申请成为认证会员</label>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form fileInput collapse">
                <fieldset>
                    <small>上传为联系人本人近期二寸免冠证件照（半年内），jpg格式，大小不超过2MB（高度在139像素至197像素之间；宽度在96像素至150像素之间）</small>
                    <section>
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-pic">
                            <input type="file" required="required" accept="image/*" id="photo" name="photoUrl" >
                            <img/>
                        </a></p>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form fileInput collapse">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" placeholder="联系人身份证号" name="idCardNumber"/>
                    </section>
                    <div><small>上传本人身份证原件正面、反面照片各一张，jpg格式，确保图片（证件底纹、文字、人物照片）清晰，无模糊，无高光白等；照片大小为100KB以上，10MB以内（建议使用800W像素以上相机拍摄）；确保证件边角显示完整</small></div>
                    <section>
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-id-front">
                            <input type="file" required="required" accept="image/*" id="idFront" name="idCardUrl1" >
	                        <img />
                        </a></p>
                    </section>
                    <section>
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-id-back">
                            <input type="file" required="required" accept="image/*" id="idBack" name="idCardUrl2" >
	                        <img />
                        </a></p>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form collapse">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" placeholder="企业营业执照号码" name="registration"/>
                    </section>
                    <section class="fileInput">
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-enterprise-registration">
                            <input type="file" required="required" accept="image/*" id="registration" name="registration" >
	                        <img />
                        </a></p>
                    </section>
                    <section>
                        <strong>*</strong><input type="text" placeholder="组织机构代码" name="certificate" />
                    </section>
                    <section class="fileInput">
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-enterprise-certificate">
                            <input type="file" required="required" accept="image/*" id="certificate" name="certificate" >
	                        <img />
                        </a></p>
                    </section>
                </fieldset>
            </section>
            <footer class="portal boxWidth form">
                <fieldset>
                    <section>
                        <strong></strong><input type="submit" value="提 交"/>
                    </section>
                </fieldset>
            </footer>
        </form>
    </body>
    <script type="text/javascript">
        $("input[type=file]").on("change", function(evt) {
            var img = $(this).siblings("img").attr("src", "");
            var file = evt.target.files[0];
            if (!file.type.match('image.*')) {
                return false;
            }

            var reader = new FileReader();
            reader.onload = (function(file) {
                return function(e) {
                    $(img).attr('src', e.target.result);
                };
            })(file);
            reader.readAsDataURL(file);
        });
</script>
    
</html>
