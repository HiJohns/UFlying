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
	    	    message: "${message}",
	            eid: "${form.eid}",
	            companyName: "${form.companyName}",
	            companyPhone: "${form.companyPhone}",
	            email: "${form.email}",
	            address: "${form.address}",
	            province: "${form.province}",
	            city: "${form.city}",
	            status: "${form.status}",
	            name: "${form.name}",
	            businessLicenseNumber: "${form.businessLicenceNumber}",
	            taxRegistrationNumber: "${form.taxRegistrationNumber}",
	            businessLicenceUrl: "${form.businessLicenceUrl}",
	            taxRegistrationUrl: "${form.taxRegistrationUrl}",
	            photoUrl: "${form.photoUrl}",
	            name: "${form.name}",
	            phone: "${form.phone}",
	            sex: "${form.sex}",
	            idCardNumber: "${form.idCardNumber}",
	            idCardUrl1: "${form.idCardUrl1}",
	            idCardUrl2: "${form.idCardUrl2}"
	        };
	        contextPath = '<%=request.getContextPath()%>';
	    </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/jquery.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/underscore-min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/3rdParty/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/loader.js"></script>
    </head>
    <body class="enterprise_complete">
        <hgroup class="usersCommon boxWidth">
            <img src="img/Logo.png" />
            <button data-icon="LeftBlue" class="back"></button>
        </hgroup>
        <form action="<%=request.getContextPath()%>/enterprise_edit" enctype="multipart/form-data"  method="post">
            <input type="hidden" name="eid"/>                        
            <input type="hidden" name="phone"/>   
            <input type="hidden" name="status"/>                     
            <section class="portal withHeader boxWidth">
                <header>
                    <div><img src="img/Right_DarkGray.png" alt="Next" /><span>基本信息</span></div>
                </header>
                <div class="staticInfo">
                    <section>
                        <label>会员ID：</label>
                        <span name="eid"></span>
                    </section>
                    <section>
                        <label>联系人手机：</label>
                        <span name="phone"></span>
                    </section>
                </div>
            </section>
		    <section class="portal boxWidth alertBox">
		    	<div class="staticInfo alert" name="message">
		   		</div>
			</section>
            <section class="portal boxWidth form">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" data-prototype="ename" required placeholder="填写企业名称" name="companyName" />
                    </section>
                    <section>
                        <strong>*</strong><input type="text" required placeholder="填写企业电话" name="companyPhone" data-icon="Phone" msg-empty="请填写企业电话"/>
                    </section>
                    <section>
                        <strong>*</strong><input type="email" data-prototype="email" required placeholder="填写企业邮箱" name="email" msg-empty="请填写企业邮箱"/>
                    </section>
                    <section class="horizon">
                        <strong>*</strong>
                        <select required="true" name="province" required data-prototype="province">
                            <option value="" disabled selected>省/直辖市/自治区</option>
                        </select>
                        <select required="true" name="city" disabled required data-prototype="city">
                            <option value="" disabled selected>城市</option>
                        </select>
                    </section>
                    <section>
                        <strong>*</strong>
                        <input type="text" required placeholder="填写地址" data-prototype="address" name="address"/>
	                </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" data-prototype="name" required placeholder="填写联系人姓名" name="name"/>
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
                        <p><a href="#" class="upload-btn" id="upload-photo-pic">
                            <input type="file" required msg-empty="请提交联系人近照" accept="image/*" id="photo" name="photo" >
                            <img name="photoUrl"/>
                        </a></p>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form fileInput collapse">
                <fieldset>
                    <section>
                        <strong>*</strong>
                        <input type="text" required="true" class="extended" required placeholder="填写联系人身份证号码" data-prototype="idCardNumber" name="idCardNumber"/>
                    </section>
                    <div><small>上传本人身份证原件正面、反面照片各一张，jpg格式，确保图片（证件底纹、文字、人物照片）清晰，无模糊，无高光白等；照片大小为100KB以上，10MB以内（建议使用800W像素以上相机拍摄）；确保证件边角显示完整</small></div>
                    <section>
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-id-front">
                            <input type="file" required="required" msg-empty="请提交联系人身份证正面照片" accept="image/*" id="idFront" name="identity_front" >
	                        <img name="idCardUrl1"/>
                        </a></p>
                    </section>
                    <section>
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-id-back">
                            <input type="file" required="required" msg-empty="请提交i联系人身份证反面照片" accept="image/*" id="idBack" name="identity_back" >
	                        <img name="idCardUrl2"/>
                        </a></p>
                    </section>
                </fieldset>
            </section>
            <section class="portal boxWidth form collapse">
                <fieldset>
                    <section>
                        <strong>*</strong><input type="text" required placeholder="企业营业执照号码" name="businessLicenceNumber" msg-empty="请企业营业执照号码"/>
                    </section>
                    <section class="fileInput">
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-enterprise-registration">
                            <input type="file" required="required" accept="image/*" id="businessLicenceImg" msg-empty="请提交企业营业执照照片" name="businessLicenceImg" >
	                        <img name="businessLicenseUrl"/>
                        </a></p>
                    </section>
                    <section>
                        <strong>*</strong><input type="text" placeholder="填写组织机构代码" name="taxRegistrationNumber" required msg-empty="请填写组织机构代码" />
                    </section>
                    <section class="fileInput">
                        <strong>*</strong>
                        <p><a href="javascript:;" class="upload-btn" id="upload-photo-enterprise-certificate">
                            <input type="file" required="required" accept="image/*" id="businessLicenceImg" msg-empty="请提交组织机构证照片" name="taxRegistrationImg" >
	                        <img name="taxRegistrationUrl"/>
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
