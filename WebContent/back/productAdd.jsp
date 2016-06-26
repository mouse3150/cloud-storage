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
<meta name=GENERATOR content="MSHTML 8.00.7601.17514">
	<script type="text/javascript">
        $(function () {
            $.each(product, function (k, p) { 
                var option = "<option id='" + p.proId + "' value='" + p.proName + "'>" + p.description + "</option>";
                $("#txtProduct").append(option);
            });
             
            $("#txtProduct").change(function () {
                var selValue = $(this).val();
                var selId = $("[value='"+selValue + "']").attr("id");
                $("#txtProductVersion option:gt(0)").remove();
                $.each(productVersion, function (k, p) { 
                    if (p.proId == selId) {
                        var option = "<option value='" + p.versionNum + "'>" + p.versionNum + "</option>";
                        $("#txtProductVersion").append(option);
                    }
                });
                
                $("#txtLevel option:gt(0)").remove();
                $.each(productLevel, function (k, p) { 
                    if (p.proId == selId) {
                        var option = "<option value='" + p.level + "'>" + p.description + "</option>";
                        $("#txtLevel").append(option);
                    }
                });
            }); 
            
        });
    </script>

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
								<a href="${basePath}/login.jsp">登录</a> <!--| <a onclick="register()">注册</a>  -->
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
   		<li><a href="${basePath}/pages/password.jsp">密码修改</a></li>
   		<li><a href="${basePath}/pages/myLicense.jsp">我的许可</a>
   		<shiro:hasAnyRoles name="admin">
         	<li><a href="${basePath}/back/productManage.jsp" class="cc">产品管理</a>
     	</shiro:hasAnyRoles>
   		<li><a href="${basePath}/index.jsp">许可管理</a>
   </ul>
  </div>
  
  <script language="javascript" type="text/javascript">
      $(".leftul3").each(function () {
          if ($(this).find("li").size() == 0) {
              $(this).remove();
          }
      });
    </script>
  <div class="biaoti">产品添加</div>
  <div class="userinfo">
  	<p class="two"> </p>
	<p class="three"> </p>
	<p class="four"> </p>
		<form id="productAdd" action="${basePath}/product/uploadSingle" enctype="multipart/form-data" method="post">
			<div class="www_zzjs_net">
	   			<span class="userinfotitle">产品名称：</span>
	   			<select class="se1" id="txtProduct" name="name">
        			<option value="0">--请选择产品--</option>
    	 		</select>
    	 		<span class="error" id="flag0"></span>
	   		</div>
	   
		   <div class="rou">
		    	<span class="userinfotitle">版本：</span>
		    	<select class="se2" id="txtProductVersion" name="version">
        			<option value="0">--请选择版本--</option>
    	 		</select>
		    	<span class="error" id="flag1"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<span class="userinfotitle">产品类型：</span>
		    	<select class="se2" id="txtLevel" name="type">
        			<option value="0">--请选择类型--</option>
    	 		</select>
		    	<span class="error" id="flag2"></span>
		   </div>
		   
		   <div class="rou">
		    	<span class="userinfotitle">上传文件：</span>
		    	<input type="file" name="file" id="uploadFile"/>
		    	<span class="error" id="flag3"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<a class="pbg4" onclick="add()" href="javascript:void(0);">添加</a>
		    	<span id="addResult" class="error"></span>
		   </div>
		</form>
    <p class="four"> </p>
	<p class="three"> </p>
	<p class="two"> </p>
  </div>
  <div class="clear"></div>

<script type="text/javascript">
	function logout(method) {
		window.parent.frames.location.href="${basePath}/logout";
	}
	function register() {
		logout();
		window.location.href = "${basePath}/register.jsp"
	}
	
	
	function onblurProductName() {
		$("#flag0").html("");
	}
	
	$(function(){
		$("#txtProduct").focus(
			function(){
				$("#flag0").html("");
			}		
		);
		
		$("#txtProductVersion").focus(
				function(){
					$("#flag1").html("");
				}	
		);
		
		$("#txtLevel").focus(
				function(){
					$("#flag2").html("");
				}		
		);
		
		$("#uploadFile").focus(
				function(){
					$("#flag3").html("");
				}		
		);
	});
	
	function add() {		
		if($("#txtProduct").val() == 0) {
			$("#flag0").html("请选择产品名称");
			return false;
		}
		
		if($("#txtProductVersion").val() == 0) {
			$("#flag1").html("请选择产品版本");
			return false;
		}
		
		if($("#txtLevel").val() == 0) {
			$("#flag2").html("请选择平台");
			return false;
		}
		
		if($("#uploadFile").val() == "" || $("#uploadFile").val() == null) {
			$("#flag3").html("请选择上传的文件");
			return false;
		}
		<%--
		$.ajax({
			cache: true,
			type: "POST",
			dataType: "text",
			url: "${basePath}/product/uploadSingle",
			data:$('#productAdd').serialize(),
			async: false,
			error: function(request) {
				alert("Connection error");
			},
			success: function(data) {
				alert(data);
				if(data == "success") {
					$("#addResult").html("添加成功！");
				} else {
					$("#addResult").html("添加失败！");
				}
			}
		});--%>
		$("#productAdd").submit();
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
