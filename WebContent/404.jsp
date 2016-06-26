<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<%@ include file="common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>ArcGIS云管理产品许可管理工具</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="Description" content="云计算班">
<meta name="Keywords" content="云计算班">

<link rel=stylesheet type=text/css
	href="${basePath}/resources/styles/public.css">
<link rel=stylesheet type=text/css
	href="${basePath}/resources/styles/style.css">
<link rel="shortcut icon" href="${basePath}/resources/images/gcloud.ico" />
<script type=text/javascript src="${basePath}/resources/js/jquery.js"></script>
<script type=text/javascript src="${basePath}/resources/js/public.js"></script>
<meta name=GENERATOR content="MSHTML 8.00.7601.17514">
<script type=text/javascript>
		$(".zhichi").click(function(){ $(".divjs").load("js.aspx");});
	</script>

<body class="body" onload="init()">
	<div class="headt pr">
		<div class=top>
			<a class=logo href="http://www.geoq.cn"></a>
			<div class=top_right>
				<div class=top_r1>
					<div id="hychead_divlogin" class="dz">
						<!--<div id="hychead_divlogin" class="dz">
							<c:choose>
								<c:when test="${isLogined}">
									<a href="${basePath}/pages/userinfo.jsp"><shiro:principal/></a>,  欢迎您！| <a
										onclick="logout()">退出</a>
								</c:when>
								<c:otherwise>
									<a href="${basePath}/login.jsp">登录</a> | <a onclick="register()">注册</a>
								</c:otherwise>
							</c:choose>
						</div>  -->
					</div>
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
	<div class="nopage">
	</div>
	<p style="text-align:center;margin-bottom:20px;font-size:16px">您访问的页面不存在，<a href="${basePath}/index.jsp">请点击此处返回！</a></p>
	<div class=clear></div>
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
		
		
	</script>
</body>
</html>
