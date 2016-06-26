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
<script type=text/javascript src="${basePath}/resources/js/Product.js"></script>
<script type=text/javascript src="${basePath}/resources/js/ProductVersion.js"></script>
<script type=text/javascript src="${basePath}/resources/js/ProductLevel.js"></script>
<script type=text/javascript src="../resources/laydate/laydate.js"></script>
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
   		<li><a href="${basePath}/index.jsp" class="cc">许可管理</a></li>
   		<li><a href="${basePath}/pages/product.jsp">产品管理</a></li>
   		<li><a href="${basePath}/back/objectManage.jsp">存储Demo</a>
   			<ul class="leftul3">
   				<li><a href="${basePath}/index.jsp">技术白皮书</a></li>
   				<li><a href="${basePath}/index.jsp">使用说明</a></li>
   				<li><a href="${basePath}/index.jsp">开发文档</a></li>
   			</ul>
   		</li>
   </ul>
  </div>
  
  <script language="javascript" type="text/javascript">
      $(".leftul3").each(function () {
          if ($(this).find("li").size() == 0) {
              $(this).remove();
          }
      });
    </script>
  <div class="biaoti">许可证详情</div><span class="error" id="contentInfo">${resultInfo}</span>
  <div class="userinfo">
  	<p class="two"> </p>
	<p class="three"> </p>
	<p class="four"> </p>
			<div class="www_zzjs_net">
	   			<span class="userinfotitle">产品名称：</span>
	   			<span class="userinfotitle">${license.productName}</span>
	   		</div>
	   
		   <div class="rou">
		    	<span class="userinfotitle">版本：</span>
		    	<span class="userinfotitle">${license.productVersion}</span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<span class="userinfotitle">License级别：</span>
		    	
		    	<span class="userinfotitle">
		    		<c:choose>
						<c:when test="${license.level == 'Advanced'}">高级版</c:when>
						<c:when test="${license.level == 'Standard'}">标准版</c:when>
						<c:otherwise>基础版</c:otherwise>
					</c:choose>
				</span>
		   </div>
		   
		   <div class="rou">
		    	<span class="userinfotitle">License类型：</span>
				<span class="userinfotitle">
					<c:choose>
						<c:when test="${license.type == 'release'}">发行版</c:when>
						<c:when test="${license.type == 'trial'}">试用版</c:when>
						<c:otherwise>临时版</c:otherwise>
					</c:choose>
				</span>
		   </div>
		   
		    <div class="www_zzjs_net">
		    	<span class="userinfotitle">项目名称：</span>
		    	<span class="userinfotitle">${license.projectName}</span>
		   </div>
		   
		   <div class="rou">
		    	<span class="userinfotitle">客户名称：</span>
		    	<span class="userinfotitle">${license.consumerName}</span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<span class="userinfotitle">订单号：</span>
		    	<span class="userinfotitle">${license.orderId}</span>
		   </div>
		   
		   <div class="rou">
		    	<span class="userinfotitle">最大CPU个数：</span>
		    	<span class="userinfotitle">${license.maxCpuNum}</span>
		    	<span class="userinfotitle">个</span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<span class="userinfotitle">绑定IP地址：</span>
		    	<span style="width:90px">${license.boundIpAddress}</span>
		    	<span class="subtitle">是否用于校验：</span>
		    	<span>${license.isBoundIpAddress}</span>
		   </div>
		   
		   <div class="rou">
		   		<span class="userinfotitle">绑定Mac地址：</span>
		    	<span style="width:90px">${license.boundMacAddress}</span>
		    	<span class="subtitle">是否用于校验：</span>
		    	<span>${license.isBoundMacAddress}</span>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span class="userinfotitle">绑定主机名：</span>
		    	<span >${license.boundHostname}</span>
		    	<span class="subtitle">是否用于校验：</span>
		    	<span>${license.isBoundHostname}</span>
		   </div>
  
		   <div class="rou">
		    	<span class="userinfotitle">开始时间：</span>
		    	<span>${license.createDate}</span>
		    	<span class="subtitle">结束时间：</span>
		    	<span>${license.expireDate}</span>
		    	<c:if test="${license.expireDate == '-1'}">
		    		<span style="margin-left:40px">
		    			<c:out value="永久License"></c:out>
		    		</span>
		    		
		    	</c:if>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span class="userinfotitle">绑定主机名：</span>
		    	<span >${license.boundHostname}</span>
		    	<span class="subtitle">是否用于校验：</span>
		    	<span>${license.isBoundHostname}</span>
		   </div>
		   
		   <div class="rou">
		   		<span class="userinfotitle">创建者：</span>
		    	<span class="userinfotitle">${licenseCreator}</span>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span class="userinfotitle">创建时间：</span>
		    	<span class="userinfotitle">${license.addTime}</span>
		   </div>
		   
		   <div class="rou">
		    	<a class="pbg4" href="${basePath}/index.jsp">返回</a>
		    	<a class="pbg4" onclick="CheckLoginDownLoad()">下载</a>
		   </div>
    <p class="four"> </p>
	<p class="three"> </p>
	<p class="two"> </p>
  </div>
  <div class="clear"></div>
  <script  type="text/javascript">
  
  function CheckLoginDownLoad(){
	    
	    var tmpForm = $("<form action='${basePath}/license/download' method='post'></form>");
        tmpForm.append("<input type='hidden' value='"+'${license.id}'+"' name='licenseId'/>");
        tmpForm.appendTo(document.body).submit();
	}
	function redirect(url) {
		window.parent.frames.location.href=url;
	}
  //获取select对象选中option对应的值。
  	function checkField(val) {
		alert("selected value is:" + $(val).children('option:selected').val());
		}
  
  	function onChange(val) {
  		if(val.checked == true) {
  			$("#end").val("-1")
  		} else {
  			$("#end").val("")
  		}
	}

	
	</script>

<script type="text/javascript">

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
