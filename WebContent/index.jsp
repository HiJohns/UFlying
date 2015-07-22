
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>空中梦想</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href='3rdParty/bootstrap/dist/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="3rdParty/jquery.js"> </script>
<!---- start-smoth-scrolling---->
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				function moveTo() {
					instance.stop();
					instance.move($(this).attr('data-target'));
					setTimeout(function () {
						console.log('restart');
						instance.start();
					}, 10000);
				}
				
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});

                var unslider = $(".my.banner").unslider({
                    delay: 6000, 
                    complete: function (e) {
                    	var current = unslider.data('unslider').current;
                    	console.log(current);
                    	var banners = $(".bannerPickerMark > ul > li").removeClass('active');
                    	$(banners[current]).addClass('active');
                    }
                });
                
                var instance = unslider.data('unslider');
                
                $('.unslider-arrow').click(function () {
                	instance[this.className.split(' ')[1]]();
                })
                
                var bannerCount = $(".banner.my > ul > li").length;
                var current = instance.current;
                for (var i = 0; i < bannerCount; i++) {
                	$('<li>').attr('data-target', i).addClass(i == current ? 'active' : '').click(moveTo)
                		.appendTo('.bannerPickerMark > ul');
                }
			});
		</script>
 	<!---- start-smoth-scrolling---->
    <script type="text/javascript" src="3rdParty/Unslider.min.js"></script>
    <script type="text/javascript" src="js/statistic.js"> </script>
    <script type="text/javascript">
        var rowWidth = 10;
        var total = 22;
        var padding = 3;
        $(document).ready(function () {
            var container = $('.vendors');
            function padRight(n) {
                var s = (n+1).toString();
                while (s.length < padding) {
                    s = '0' + s;
                }

                return s;
            }

            for (var i = 0; i < total; i++) {
                var div;
                if (i % 10 == 0) {
                    div = $('<row>').addClass('clearfix').appendTo(container);
                }

                $('<img>').attr('src', 'images/Vendors/' + padRight(i) + '.png').appendTo(div);
            }
//            div = $('<row>').addClass('clearfix').appendTo(container);
//            div = $('<row>').addClass('clearfix').appendTo(container);
        });
    </script>
</head>
<style>
.my.banner { position: relative; overflow: auto; }
.my.banner li { 
    list-style: none;
    float: left; 
    height: 800px;
    padding-top: 430px;
}
.my.banner ul li:first-child {
    background:url(images/bannerPA.jpg) no-repeat;
}
.my.banner ul li:nth-child(2) {
    background:url(images/bannerB.jpg) no-repeat;
}
.my.banner ul li:last-child {
    background:url(images/bannerC.jpg) no-repeat;
}
.my.banner li p.lead {
    width: 50%;
    text-align: center;
    margin: 0px auto 20px;
    color: white;
    font-size: 3em;
}
.my.banner li p.lead3 {
    width: 50%;
    text-align: center;
    margin: 0px auto 20px;
    color: #E04444;
    font-size: 1.5em;
}
.my.banner .btn {
    color: white;
    border: white 1px solid;
}
.my.banner .btn:hover {
    background-color: white;
    color: #333;
}

.social-media {
    margin: -30px 0 30px;
}

.social-media .row {
    margin: 0 auto;
    font-size: 2em;
}

.odometer > span {
    background: rgba(0, 0, 0, 0) url("images/numpad_bg.png") repeat scroll 0 0;
    color: white;
    float: left;
    font-size: 1.1em;
    height: 51px;
    margin-right: 3px;
    padding: 3px 0 0 5px;
    width: 36px;
}
.cont_txt {
    float: left;
    color: rgb(237, 101, 0);
    font-size: 0.8em;
    margin-right: 10px;
    margin-top: 8px;
}
.vendors {
    background-color: rgb(244, 42, 66);
    margin-top: -20px;
    padding-top: 20px;
}
.vendors header {
    text-align: center;
    margin-bottom: 40px;
}
.vendors h1 {
	color:#fff;
	font-size:2.3em;
	font-family: 'Microsoft YaHei', 'Open Sans', sans-serif;
	font-weight:bold;
	letter-spacing:1.5px;
}
.vendors img {
    width: 105px;
    height: 55px;
}
.vendors row {
    display: block;
    margin: 10px auto;
    width: 1050px;
}
.arrowMark {
    height: 60px;
    left: 0;
    position: absolute;
    top: 400px;
    width: 100%;
}
.unslider-arrow.next {
    float: right;
    text-align: right;
}
.unslider-arrow.prev {
    float: left;
}
.unslider-arrow:hover {
    opacity: 0.8;
}
.unslider-arrow {
    border: 2px solid white;
    border-radius: 12px;
    color: white;
    cursor: pointer;
    display: inline;
    font-size: 2em;
    height: 2.1em;
    margin-left: 100px;
    margin-right: 100px;
    opacity: 0.3;
    padding: 5px 16px;
    width: 2em;
}

footer section {
    overflow: hidden;
    background-color: black;
}

footer .item {
    float: left;
    text-align: center;
    width: 33.33%;
}

footer .center {
    width: 900px;
    margin: 40px auto;
    overflow: hidden
}

footer section:first-child {
    background-color: #373737;
}

footer p, footer h3, footer a {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    color: #898989;
}

footer h3 {
    font-size: 1em;
    margin-bottom: 5px;
}

footer p {
    font-size: small;
    margin-top: 5px;
}


footer .linkBox {
    float: left;
    margin-left: 5%;
    width: 16%;
}


footer .linkBox > div {
    font-size: small;
    font-weight: bold;
    margin: 10px 0;
}

.center > img {
    width: 10%;
    float: right;
    margin-right: 4%;
}
.bannerPickerMark {
    height: 10px;
    position: absolute;
    top: 675px;
    width: 100%;
}
.bannerPickerMark ul {
    height: 10px;
    margin: 0 auto;
    width: 180px;
}
.bannerPickerMark li {
    border: 2px solid #dcdcdc;
    cursor: pointer;
    display: inline;
    float: left;
    margin: 2px 1px 0;
    width: 58px;
}
.bannerPickerMark .active {
    border: 3px solid white;
    margin-top: 0;
}
</style>
<body>
<!--body-->
<ul class="side_nav">
<li><a class="scroll" href="#home1"></a></li>
<li><a class="scroll" href="#skills"></a></li>
<li><a class="scroll" href="#gallery"></a></li>
<li><a class="scroll" href="#about"></a></li>
<li><a class="scroll" href="#vendors"></a></li>
</ul>
 <div id="home1" class="top-header">		  
        <div class="logo">
            <a href="index.html"><img src="images/logo_top.png" alt="" /></a>
        </div>			  
         <div class="top-menu">
             <span class="menu"></span> 
             <nav class="cl-effect-1">
              <ul>
                 <li><a href="<%=request.getContextPath()%>/login">加入联盟</a></li>
                 <li><a href="#">派发任务</a></li>
                 <li><a href="#">承接任务</a></li>
                 <li><a href="#">网上投保</a></li>						 
             </ul>	
             </nav>					 
         </div>	
          <div class="clearfix"> </div>
                 
 </div>
    <div class="banner my">
        <ul>
            <li>
                <p class="lead"></p>
                <p class="lead3">详情请咨询：4006-509-579   </p>
            </li>
            <li>
                <p class="lead">给你翅膀，带你飞 ！</p>
                <p class="lead3">携手实现每个人的空中梦想</p>
                <p class="lead">
                    <a class="btn btn-lg" href="<%=request.getContextPath()%>/login">我要加入</a>
                </p>
            </li>
            <li>
                <p class="lead">给你翅膀，带你飞 ！</p>
                <p class="lead3">携手实现每个人的空中梦想</p>
                <p class="lead">
                    <a class="btn btn-lg" href="<%=request.getContextPath()%>/login">我要加入</a>
                </p>
            </li>
        </ul>
        
    </div>
<div class="social-media">
    <div class="row clearfix " style="width:920px;">
        <div class="main">
            <span class="cont">
                <span id="finish" class="odometer"></span>
                <span class="f1 cont_txt">会员&nbsp;&nbsp;</span>
            </span>
            <span class="cont" style="margin-right: 18px;">
                <span id="trading" class="odometer"></span>
                <span class="f1 cont_txt">理事企业&nbsp;&nbsp;</span>
            </span>
            <span class="cont" style="margin-left: 18px;">
                <span id="com_num" class="odometer"></span>
                <span class="f1 cont_txt">万元保额&nbsp;&nbsp;</span>
            </span>
            <span class="cont">
                <span id="investor" class="odometer"></span>
                <span class="f2 cont_txt">场任务</span>
            </span>
        </div>
    </div>
</div>
<!---->
<div id="skills" class="skills">
	 <div class="container">
		 <div class="skills-head">
			 <h3>UFLYING无人机联盟</h3>
			 <p></p>
			 <h5>融合无人机行业各方面资源，一个平等充分的信息交流、资源共享、互帮互助的平台。发挥大数据优势，为联盟会员提供最便捷的服务。</h5>
		 </div> 
		 <div class="skill-grids">
			 <div class="col-md-4 skill-grid">
			 	<div>
				 <img src="images/research.png" alt=""/>
				 <h3><a href="#">无人机保险</a></h3>
				 <p>无人机飞行是一项有风险的活动，联盟为成员提供包括机身，三者，团队等多重保障在内的保险服务。</p>
				 </div>
			 </div>
			 <div class="col-md-4 skill-grid">
			 	<div>
				 <img src="images/design.png" alt=""/>
				 <h3><a href="#">商业任务</a></h3>
				 <p>通过大数据平台，为会员对接商业任务。飞手承接更多任务，需求方更快捷地找到可信任的执行团队。</p>
				 </div>
			 </div>
			 <div class="col-md-4 skill-grid">
			 	<div>
				 <img src="images/code.png" alt=""/>
				 <h3><a href="#">文化娱乐</a></h3>
				 <p>带您玩转无人机，无人机将成为新的文化时尚。多样的玩法，专业的教练，让零基础也能享受无人机的乐趣。</p>
				 </div>
			 </div>
			 <div class="clearfix"></div>
		 </div>
		 <div class="pencils">
			 <img src="images/pencils.png" alt=""/>
		 </div>
	 </div>
</div>
<!---->
<!------ Light Box ------>
    <link rel="stylesheet" href="css/swipebox.css">
    <script src="js/jquery.swipebox.min.js"></script> 
    <script type="text/javascript">
		jQuery(function($) {
			$(".swipebox").swipebox();
		});
	</script>
    <!------ Eng Light Box ------>	
<div id="gallery" class="gallery">
	 <div class="container">
		 <div class="gallery-head">
			 <h3>空中家园</h3>
			 <p>您可以约上三五飞友到这里一叙；也可在这里享受联盟的各项线下服务，买保险、修飞机、接任务、租电池等；更可举办您自己的小型沙龙……</p>
			 <h5>为联盟会员提供线下服务，飞友自己的空中家园</h5>
		 </div>
		 </div>
			  <ul id="filters" class="clearfix">
						<li class="active" style="display:none"><span class="filter" data-filter="app card icon logos web">VIEW ALL</span></li>
						<li><span class="filter" data-filter="app card icon logos web">交友聚会</span></li>
						<li><span class="filter" data-filter="app card icon logos web">理赔维修</span></li>
						<li><span class="filter" data-filter="app card icon logos web">承接任务</span></li>
			 </ul>
					<div id="portfoliolist">
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic1.jpg" class="swipebox"  title="空中家园旗舰店正门"> <img src="images/pic1.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店正门</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>				
					<div class="portfolio app mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="app" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic2.jpg" class="swipebox"  title="空中家园旗舰店办公区"> <img src="images/pic2.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店办公区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>					
					<div class="portfolio icon mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="icon" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic3.jpg" class="swipebox"  title="空中家园旗舰店室内"> <img src="images/pic3.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店室内</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>

					</div>
					
					<div class="portfolio app mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="app" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic4.jpg" class="swipebox"  title="空中家园旗舰店会议区"> <img src="images/pic4.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店会议区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>			
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic5.jpg" class="swipebox"  title="空中家园旗舰店休闲区"> <img src="images/pic5.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店休闲区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>	
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic6.jpg" class="swipebox"  title="空中家园会员用品区"> <img src="images/pic6.jpg" class="img-responsive" alt="">
							 <div class="caption">
							 <div class="caption-info">
								 <h4>空中家园会员用品区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>	
					<div class="portfolio icon mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="icon" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic7.jpg" class="swipebox"  title="空中家园旗舰店办公区"> <img src="images/pic7.jpg" class="img-responsive" alt="">
								<div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店办公区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
						</div>
						<div class="portfolio logos mix_all wow bounceIn" data-wow-delay="0.4s" data-cat="logos" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic8.jpg" class="swipebox"  title="空中家园旗舰店工作区"> <img src="images/pic8.jpg" class="img-responsive" alt="">
							<div class="caption">
							 <div class="caption-info">
								 <h4>空中家园旗舰店工作区</h4>								
								 <p>UFLYING无人机联盟</p>
								 <p>空中家园</p>
								 </div>
								 </div></a>
		                </div>
					</div>
					
<div class="clearfix"></div>					
				</div>
				
	  <!-- Script for gallery Here -->
				<script type="text/javascript" src="js/jquery.mixitup.min.js"></script>
					<script type="text/javascript">
					$(function () {
						
						var filterList = {
						
							init: function () {
							
								// MixItUp plugin
								// http://mixitup.io
								$('#portfoliolist').mixitup({
									targetSelector: '.portfolio',
									filterSelector: '.filter',
									effects: ['fade'],
									easing: 'snap',
									// call the hover effect
									onMixEnd: filterList.hoverEffect()
								});				
							
							},
							
							hoverEffect: function () {
							
								// Simple parallax effect
								$('#portfoliolist .portfolio').hover(
									function () {
										$(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
										$(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
									},
									function () {
										$(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
										$(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
									}		
								);				
							
							}
				
						};
						
						// Run the show!
						filterList.init();
						
						
					});	
					</script>

</div>
<!---->
<div id="about" class="about">
	 <div class="container">
		 <div class="about-head">
			 <h3>无人机航拍服务</h3>
			 <p>任务经纪平台</p>
			 <h5>无论您有什么样的航拍需求，无论您需要什么样的航拍团队，来无人机联盟的任务经纪平台，都可以得到满意的答案！</h5>
		 </div> 
		 <div class="about-sec">
			 <div class="col-md-6 about-info">
				 <div class="about-grid">
					 <div class="about-icon">
						 <i class="icon1"></i>
					 </div>
					 <div class="about-icon-info">
						 <h4>我是婚礼新人：</h4>
						 <p>我要从不同的角度、不同的视野来记录我的婚礼，还要为我的婚礼添加与众不同的亮点。怎么找到满意的飞手，谁能策划拍摄的流程？最最关键的是，人生只有一次的大事，如何保证成功率，不留遗憾……</p>
					 </div>
					 <div class="clearfix"></div>
				 </div>
				 <div class="about-grid">
					 <div class="about-icon">
						 <i class="icon2"></i>
					 </div>
					 <div class="about-icon-info">
						 <h4>我是广告经理：</h4>
						 <p>我们的广告需要空中的拍摄，迅速的追踪，高速的俯冲，与主角的擦肩而过。画面，速度，激情，创意，都是我们的需求，但是这一切都伴随着风险，不能在客户面前有任何的闪失，哪里去找既靠谱又“保险”的航拍团队？
						 </p>
					 </div>
					 <div class="clearfix"></div>
				 </div>
				 <div class="about-grid">
					 <div class="about-icon">
						 <i class="icon3"></i>
					 </div>
					 <div class="about-icon-info">
						 <h4>我是影视导演：</h4>
						 <p>我要能够懂得构图，胆大心细，能够拍出场面感的航拍人。比如连贯的长镜头，比如细致的特写镜头，完全体现导演意图的镜头感！</p>
					 </div>
					 <div class="clearfix"></div>
				 </div>
			 </div>
			 <div class="col-md-6 about-phones">
				 <img src="images/phones.png" alt=""/>
			 </div>
			 <div class="clearfix"></div>
		 </div>
	 </div>
</div>

<!---fotter-->
<section id="vendors" class="vendors">
    <header>
        <h1>合作伙伴</h1>
    </header>
    <div>
    </div>
</section>
<footer>
    <section>
        <div class="center">
            <div class="item">
                <img src="images/Notes.png" />
                <h3>成为签约飞手</h3>
                <p>为超过1000家客户服务，百分之百获得任务</p>
            </div>
            <div class="item">
                <img src="images/Awards.png" />
                <h3>全国代理加盟</h3>
                <p>会员服务、商业任务、保险服务授权经销</p>
            </div>
            <div class="item">
                <img src="images/Charity.png" />
                <h3>公益服务申请</h3>
                <p>免费为全国公益性组织提供无人机服务</p>
            </div>
        </div>
    </section>
    <section>
        <div class="center">
            <div class="linkBox">
                <div><a href="#">空中梦想</a></div>
                <div><a href="#">UFLYING无人机联盟</a></div>
                <div><a href="#">空中家园</a></div>
            </div>
            <div class="linkBox">
                <div><a href="#">派发任务</a></div>
                <div><a href="#">商业合作</a></div>
                <div><a href="#">广告合作</a></div>
            </div>
            <div class="linkBox">
                <div><a href="#">签约飞手</a></div>
                <div><a href="#">代理加盟</a></div>
            </div>
            <div class="linkBox">
                <div><a href="#">代理加盟</a></div>
                <div><a href="#">联系我们</a></div>
            </div>
            <img src="img/qrcode.png"/>
        </div>
    </section>
</footer>
<!---->
<div class="arrowMark">
	<div class="unslider-arrow prev"><</div>
	<div class="unslider-arrow next">></div>
</div>
<div class="bannerPickerMark">
	<ul></ul>
</div>
</body>
</html>
