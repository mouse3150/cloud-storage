<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<%@ include file="common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>ArcGIS云管理产品许可管理工具</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">


<link rel=stylesheet type=text/css
	href="${basePath}/resources/styles/public.css">
<link rel=stylesheet type=text/css
	href="${basePath}/resources/styles/style.css">
<link rel="shortcut icon" href="${basePath}/resources/images/gcloud.ico" />
<script type=text/javascript src="${basePath}/resources/js/jquery.js"></script>
<script type=text/javascript src="${basePath}/resources/js/public.js"></script>
<meta name=GENERATOR content="MSHTML 8.00.7601.17514">

</head>

<body class="body" onload="init()">
	<div class="headt pr">
		<div class=top>
			<a class=logo href="http://www.geoq.cn"></a>
			<div class=top_right>
				<div class=top_r1>
					
					<div class=clear></div>
				</div>
				<div class=clear></div>
			</div>
			<div class=clear></div>
		</div>

		<div class=banner>
			<img src="<%=basePath%>/resources/images/2014213937339644444.jpg">
		</div>

<div class="main">
  <div id="logindiv">
	<div class="bt">管理员登录</div>
	<div class="dl">
		<form action="" id="login" method="post">
			<table class=ta1 border=0 cellSpacing=0 cellPadding=0 width=340>
				<tbody>
					<tr>
						<td colspan=2 width=340 style="text-align: center;font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;录</td>
					</tr>
					<tr>
						<td width=90>账号：</td>
						<td width=250><input name="username"
							 id="username"
							class="input1" type="text"></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input name="password"
							 id="password"
							class="input2" type="password"></td>
					</tr>
					<c:if test="${!empty loginErrorMsg}">
						<tr class="error">
							<td class="error">&nbsp;</td>
							<td class="error">${loginErrorMsg}</td>
						</tr>
					</c:if>
					<tr>
						<td>&nbsp;</td>
						<td><input name="rememberMe" id="rememberMe" class="input3" type="checkbox">记住我  </td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a class=bg3 onclick="login()" href="javascript:void(0);">登录</a> 
							<!--<a onclick="register()">立即注册</a>  -->
							
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<div class=clear></div>
	</div> 
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
	<script type="text/javascript">
		function init() {
			var islogined = ${isLogined};
			if(islogined) {
				window.location.href = "${basePath}/pages/product.jsp"
			} else {
				$("div#logindiv").show();
			}
		}
		function register() {
			logout();
			window.location.href = "${basePath}/register.jsp"
		}
		function logout() {
			window.parent.frames.location.href="${basePath}/logout";
		}
		
		function login() {
			$(".error").hide(); 
	        var checklogin = document.getElementById("checklogin");
	        if ($("#username").val() == "") {
	            alert("请输入用户名！");
	            $("#password").focus();
	            return false;
	        }
	        if ($("#password").val() == "") {
	            alert("请输入用户密码！");
	            $("#password").focus();
	            return false;
	        }
	        $("form#login").submit();
	     }
	</script>
</body>
</html>
