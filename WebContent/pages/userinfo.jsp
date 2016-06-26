<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>ArcGIS云管理产品许可管理工具</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">


<link rel="stylesheet" type="text/css" href="${basePath}/resources/styles/public.css">
<link rel=stylesheet type=text/css
	href="${basePath}/resources/styles/style.css">
<link rel="shortcut icon" href="${basePath}/resources/images/gcloud.ico" />
<script type=text/javascript src="${basePath}/resources/js/jquery.js"></script>
<script type=text/javascript src="${basePath}/resources/js/public.js"></script>
<meta name=GENERATOR content="MSHTML 8.00.7601.17514">


</head>
<body class="body">
	<div class="headt pr">
		<div class="top">
			<a class="logo" href="http://www.geoq.cn"></a>
			<div class="top_right">
				<div class="top_r1">
					<div id="hychead_divlogin" class="dz">
						<c:choose>
							<c:when test="${isLogined}">
								<a href="${basePath}/pages/userinfo.jsp"><shiro:principal/></a>,  欢迎您！| <a
									onclick="logout()">退出</a>
							</c:when>
							<c:otherwise>
								<a href="${basePath}/login.jsp">登录</a><!-- | <a onclick="register()">注册</a> --> 
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clear"></div>
				</div>

				<div class=clear></div>
				
			</div>
			<div class=clear></div>
		</div>
		<div class=banner>
			<img src="${basePath}/resources/images/2014213937339644444.jpg">
		</div>
<div class="main">
  <div class="w150l">
   <ul class="lenav">
   		<li><a href="${basePath}/pages/userinfo.jsp" class="cc">用户信息</a></li>
   		<li><a href="${basePath}/pages/password.jsp">密码修改</a></li>
   		<li><a href="${basePath}/pages/myLicense.jsp">我的许可</a>
   		<shiro:hasAnyRoles name="admin">
         	<li><a href="${basePath}/back/productManage.jsp">产品管理</a>
     	</shiro:hasAnyRoles>
     	<li><a href="${basePath}/index.jsp">许可管理</a>
   </ul>
  </div>

  <div class="userinfo">
  	<p class="two"> </p>
	<p class="three"> </p>
	<p class="four"> </p>
	   <div class="www_zzjs_net">
	   		<span class="userinfotitle">用户名：</span>
	   		<span class="userinfotitle" id="userName"></span>
	   </div>
	   
	   <div class="rou">
	   		<span class="userinfotitle">电子邮箱：</span>
	   		<span class="userinfotitle" id="email"></span>
	   </div>
	   
	   <div class="www_zzjs_net">
	    	<span class="userinfotitle">登录时间：</span>
	    	<span class="userinfotitle" id="loginTime"></span>
	   </div>
	   
	   <div class="rou">
	    	<span class="userinfotitle">上次登录登录时间：</span>
	    	<span class="userinfotitle" id="preLoginTime"></span>
	   </div>
<!--	   
	   <div class="www_zzjs_net">
	    	<span class="userinfotitle">许可证下载限制：</span>
	    	<span class="userinfotitle" id="downloadLimit"></span>
	   </div>
	   
	   <div class="rou">
	    	<span class="userinfotitle">已下载：</span>
	    	<span class="userinfotitle" id="downloadNum"></span>
	   </div>
    --> 		
    <p class="four"> </p>
	<p class="three"> </p>
	<p class="two"> </p>
  </div>
  <div class="clear"></div>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			   type: "GET",
			   url: "${basePath}/user/userinfo",
			   dataType: "json",
			   //data: "",
			   success: function(msg){
					$("#userName").html(msg.name);
					$("#email").html(msg.email);
					
					if(msg.loginTime == null) {
						$("#loginTime").html("");
					} else {
						$("#loginTime").html(msg.loginTime.substring(0,19));
					}
					
					if(msg.preLoginTime == null) {
						$("#preLoginTime").html("");
					} else {
						$("#preLoginTime").html(msg.preLoginTime.substring(0,19));
					}
					
					//$("#downloadLimit").html(msg.downloadNumLimit + "次");
					//$("#downloadNum").html(msg.licenseDownloadNum + "次");
			   }
			})
	});
	function logout(method) {
		window.parent.frames.location.href="${basePath}/logout";
	}
	function register() {
		logout();
		window.location.href = "${basePath}/register.jsp"
	}
</script>
</div>
	<div class="footer">
		<div class="fot_left">
				<a href="https://github.com/mouse3150">项目网址</a>
				| <a href="http://www.buaa.edu.cn/">友情链接</a>
		</div>
		<p class="fot_p"></p>
		<p>© 2016 云计算班第八组. 版权所有</p>
		<p></p>
			<span>京ICP证120282号</span>
			<div class="clear"></div>
	</div>
</div>
</body>
</html>
