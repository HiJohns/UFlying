<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<div class="container navbar-inverse">
  <div class="navbar-header">
    <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand hidden-sm">大宝贝</a>
  </div>
  <div class="navbar-collapse collapse" role="navigation">
    <ul class="nav navbar-nav menus">
    	<li id="menuHuoDongList" class="active"><a class="huoDongList" href="${rc.contextPath}/bigbaby/huoDong/list">活动列表</a></li>
	    <li id="menuHuoDongCreate"><a class="huoDongCreate" href="${rc.contextPath}/bigbaby/huoDong/create">创建活动</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right hidden-sm">
       <li><a href="http://www.bigbaby.cn/" target="_blank">大宝贝主页</a></li>
    </ul>
  </div>
</div>    

<script type="text/javascript">
	$(document).ready(function(){
		var token = $.cookie("token");
		$(".huoDongCreate").attr("href","${rc.contextPath}/bigbaby/huoDong/create");
		
		var menu_active = $.cookie("menu_active");
		if (menu_active != undefined) {
			$(".menus > li").removeClass("active");
            $("#" + menu_active).addClass("active");
        }
		$('.menus li').click(function () {
			$(".menus > li").removeClass("active");
            // 设置cookie,当前选中的submenu是什么，下拉菜单
            var menuId = $(this).attr("id");
            $.cookie("menu_active", menuId, { path: "/", expires: 2});
            $("#" + menu_active).addClass("active");
        });
		
	});
</script>