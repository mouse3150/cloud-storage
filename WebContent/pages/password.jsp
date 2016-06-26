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
								<a href="${basePath}/login.jsp">登录</a> | <a onclick="register()">注册</a>
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
   		<li><a href="${basePath}/pages/userinfo.jsp">用户信息</a></li>
   		<li><a href="${basePath}/pages/password.jsp" class="cc">密码修改</a></li>
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
		<form id="changePassword">
			<div class="www_zzjs_net">
	   			<span class="userinfotitle">用户名：</span>
	   			<span class="userinfotitle"><shiro:principal/></span>
	   		</div>
	   
		   <div class="rou">
		    	<span class="userinfotitle">输入新密码：</span>
		    	<input style="margin-top:5px;" onblur="onblurPassword()" name="password" type="password" id="txtPassword"/>
		    	<span id="flag2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="passwordPrompt" class="hightlight"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<span class="userinfotitle">确认密码：</span>
		    	<input style="margin-top:5px;" onblur="onblurPassword2()" name="password2" type="password" id="txtPassword2"/>
		    	<span id="flag3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="passwordPrompt2" class="hightlight"></span>
		   </div>
		   
		   <div class="rou">
		    	<a style="margin-top:3px;" class="pbg4" onclick="changePassword()" href="javascript:void(0);">提交</a>
		    	<span id="changeResult" class="error"></span>
		   </div>
		</form>
    <p class="four"> </p>
	<p class="three"> </p>
	<p class="two"> </p>
  </div>
  <div class="clear"></div>
<script type="text/javascript">

//密码校验
$(function(){
	$("#txtPassword").focus(
		function(){
			$("#passwordPrompt").attr("class","hightlight");
			$("#flag2").removeAttr("class");
			$("#passwordPrompt").html(validatePrompt.pwd.onFocus);
		}		
	);
	
});

function onblurPassword() {
	 var password = $("#txtPassword").val();
	 if(validateRules.isNull(password)) {
		$("#passwordPrompt").attr("class","error");
		$("#flag2").attr("class","spanError");
	    $("#passwordPrompt").html(validatePrompt.pwd.isNull);
	    return false;
	 }
	 if(!validateRules.betweenLength(password, 6, 20)) {
		 $("#passwordPrompt").attr("class","error");
		 $("#flag2").attr("class","spanError");
	     $("#passwordPrompt").html(validatePrompt.pwd.error.badLength);
	     return false;
	 }
	 
	 if(!validateRules.isPwd(password)) {
		 $("#passwordPrompt").attr("class","error");
		 $("#flag2").attr("class","spanError");
	     $("#passwordPrompt").html(validatePrompt.pwd.error.badFormat);
	     return false;
	 }

	 $("#passwordPrompt").html("");
	 $("#flag2").attr("class","spanSuccess");
	 return true;
}

//确认密码：

$(function(){
	$("#txtPassword2").focus(
		function(){
			$("#passwordPrompt2").attr("class","hightlight");
			$("#flag3").removeAttr("class");
			$("#passwordPrompt2").html(validatePrompt.pwdRepeat.onFocus);
		}		
	);
	
});

function onblurPassword2() {
	 var password2 = $("#txtPassword2").val();
	 var password = $("#txtPassword").val();
	 if(validateRules.isNull(password2)) {
		$("#passwordPrompt2").attr("class","error");
		$("#flag3").attr("class","spanError");
	    $("#passwordPrompt2").html(validatePrompt.pwdRepeat.isNull);
	    return false;
	 }

	if(!validateRules.isPwdRepeat(password2, password)) {
		 $("#passwordPrompt2").attr("class","error");
		 $("#flag3").attr("class","spanError");
	     $("#passwordPrompt2").html(validatePrompt.pwdRepeat.error.badFormat2);
	     return false;
	 }
	
	 $("#passwordPrompt2").html("");
	 $("#flag3").attr("class","spanSuccess");
	 return true;
}
	
	function logout(method) {
		window.parent.frames.location.href="${basePath}/logout";
	}
	function register() {
		logout();
		window.location.href = "${basePath}/register.jsp"
	}
	
	function changePassword() {
		var vp = onblurPassword();
		var vpp = onblurPassword2();
		if(vp && vpp) {
			//alert("一切OK！！！");
		} else {
			//alert("有错！");
			return false;
		}
		
		//$("form#register").submit();
		
		$.ajax({
				cache: true,
				type: "POST",
				dataType: "text",
				url: "${basePath}/user/changepw",
				data:$('#changePassword').serialize(),
				async: false,
				error: function(request) {
					alert("Connection error");
				},
				success: function(data) {
					//alert(data);
					if(data == "success") {
						$("#changeResult").html("密码修改成功！");
					} else {
						$("#changeResult").html("密码修改失败！");
					}
				}
			});
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
