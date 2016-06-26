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
 
<script>
function selectStart() {
	var start = {
		    elem: '#start',
		    format: 'YYYY-MM-DD',
		    min: laydate.now(), //设定最小日期为当前日期
		    max: '2099-06-16', //最大日期
		    istime: true,
		    istoday: false,
		    choose: function(datas){
		         end.min = datas; //开始日选好后，重置结束日的最小日期
		         end.start = datas //将结束日的初始值设定为开始日
		    }
		};
		var end = {
		    elem: '#end',
		    format: 'YYYY-MM-DD',
		    min: laydate.now(),
		    max: '2099-06-16',
		    istime: true,
		    istoday: false,
		    choose: function(datas){
		        start.max = datas; //结束日选好后，重置开始日的最大日期
		        start.min = '2000-06-16';
		    }
		};
		laydate(start);
		laydate(end);
	}
	
</script>
  



</head>
<body class="body" onload="selectStart()">
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
  <div class="biaoti">许可证创建</div>
  <div class="userinfo">
  	<p class="two"> </p>
	<p class="three"> </p>
	<p class="four"> </p>
		<form id="productAdd" action="${basePath}/license/add" method="post">
			<div class="www_zzjs_net">
				<span class="error">*</span>
	   			<span class="userinfotitle">产品名称：</span>
	   			<select class="lic" id="txtProduct" name="productName">
        			<option value="0">--请选择产品--</option>
    	 		</select>
    	 		<span class="error" id="flag0"></span>
	   		</div>
	   
		   <div class="rou">
		   		<span class="error">*</span>
		    	<span class="userinfotitle">版本：</span>
		    	<select class=lic id="txtProductVersion" name="productVersion">
        			<option value="0">--请选择版本--</option>
    	 		</select>
		    	<span class="error" id="flag1"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span class="error">*</span>
		    	<span class="userinfotitle">级别：</span>
		    	<select class="lic" id="txtLevel" name="Level">
        			<option value="0">--请选择版本--</option>
    	 		</select>
		    	<span class="error" id="flag2"></span>
		   </div>
		   
		   <div class="rou">
		   		<span class="error">*</span>
		    	<span class="userinfotitle">类型：</span>
		    	<select class="lic" id="licenseType" name="type">
        			<option value="0">--请选择类型--</option>
        			<option value="release">发行版</option>
        			<option value="trial">测试版</option>
        			<option value="temp">临时版</option>
    	 		</select>
		    	<span class="error" id="flag3"></span>
		   </div>
		   
		    <div class="www_zzjs_net">
		    	<span>&nbsp;</span>
		    	<span class="userinfotitle">项目名称：</span>
		    	<input class="licenseItem" type="text" name="projectName" id="projectName"/>
		   </div>
		   
		   <div class="rou">
		   		<span>&nbsp;</span>
		    	<span class="userinfotitle">客户名称：</span>
		    	<input class="licenseItem" type="text" name="consumerName" id="consumerName"/>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span>&nbsp;</span>
		    	<span class="userinfotitle">订单号：</span>
		    	<input class="licenseItem" type="text" name="orderId" id="orderId"/>
		   </div>
		   
		   <div class="rou">
		   		<span class="error">*</span>
		    	<span class="userinfotitle">最大CPU个数：</span>
		    	<input class="licenseItem" type="text" name="maxCpuNum" id="maxCpuNum"/>
		    	<span class="error" id="flag4"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span>&nbsp;</span>
		    	<span class="userinfotitle">绑定IP地址：</span>
		    	<input class="licenseItem" type="text" name="boundIpAddress" id="boundIpAddress"/>
		    	<span class="subtitle">是否用于校验：</span>
		    	<input type="checkbox" name="isBoundIpAddress" id="isBoundIpAddress">
		    	<span class="error" id="flag5"></span>
		   </div>
		   
		   <div class="rou">
		   		<span>&nbsp;</span>
		    	<span class="userinfotitle">绑定MAC地址：</span>
		    	<input class="licenseItem" type="text" name="boundMacAddress" id="boundMacAddress"/>
		    	<span class="subtitle">是否用于校验：</span>
		    	<input type="checkbox" name="isBoundMacAddress" id="isBoundMacAddress" value="1">
		    	<span class="error" id="flag6"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		   		<span>&nbsp;</span>
		    	<span class="userinfotitle">绑定主机名：</span>
		    	<input class="licenseItem" type="text" name="boundHostname" id="boundHostname"/>
		    	<span class="subtitle">是否用于校验：</span>
		    	<input type="checkbox" name="isBoundHostname" id="isBoundHostname" value="1">
		    	<span class="error" id="flag7"></span>
		   </div>
  
		   <div class="rou">
		   		<span class="error">*</span>
		    	<span class="userinfotitle">开始时间：</span>
		    	<input style="width:95px" name="createDate" class="laydate-icon" id="start" />&nbsp;&nbsp;
		    	<span>结束时间：</span>
		    	<input style="width:95px" name="expireDate" class="laydate-icon" id="end"/>&nbsp;&nbsp;&nbsp;
		    	<span>是否永久：</span>
		    	<input type="checkbox" name="isForever" id="isForever" onchange="onChange(this)">
		    	<span class="error" id="flag8"></span>
		   </div>
		   
		   <div class="www_zzjs_net">
		    	<a class="pbg4" onclick="add()" href="javascript:void(0);">创建</a>
		    	<span id="addResult" class="error"></span>
		   </div>
		</form>
    <p class="four"> </p>
	<p class="three"> </p>
	<p class="two"> </p>
  </div>
  <div class="clear"></div>
  <script  type="text/javascript">
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
		
		$("#txtType").focus(
				function(){
					$("#flag3").html("");
				}		
		);
		
		$("#maxCpuNum").focus(
				function(){
					$("#flag4").html("");
				}		
		);
		
		$("#isBoundIpAddress").focus(
				function(){
					$("#flag5").html("");
				}		
		);
		$("#boundIpAddress").focus(
				function(){
					$("#flag5").html("");
				}		
		);
		
		$("#isBoundMacAddress").focus(
				function(){
					$("#flag6").html("");
				}		
		);
		$("#boundMacAddress").focus(
				function(){
					$("#flag6").html("");
				}		
		);
		
		$("#isBoundHostname").focus(
				function(){
					$("#flag7").html("");
				}		
		);
		$("#boundHostname").focus(
				function(){
					$("#flag7").html("");
				}		
		);
		
		$("#start").focus(
				function(){
					$("#flag8").html("");
				}		
		);
		
		$("#end").focus(
				function(){
					$("#flag8").html("");
				}		
		);
		
		$("#isForever").focus(
				function(){
					$("#flag8").html("");
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
			$("#flag2").html("请选择产品级别");
			return false;
		}
		
		if($("#licenseType").val() == 0) {
			$("#flag3").html("请选择产品类型");
			return false;
		}
		
		//校验maxCpuNum
		var va = $("#maxCpuNum").val().trim();
		if(va != '') {
			var reg1 = /^[1-9][\d]*$/;
			if($("#maxCpuNum").val().trim().match(reg1) == null)  {
				$("#flag4").html("请输入8~256间的整数.");
				return false;
			}
			
			if(va < 8 || va > 256) {
				$("#flag4").html("请输入8~256间的整数.");
				return false;
			}
		} else {
			$("#flag4").html("不能为空，请输入8~256间的整数.");
			return false;
		}
		
		if($("#isBoundIpAddress")[0].checked && $("#boundIpAddress").val() == "") {
			$("#flag5").html("已指定IP地址校验，请输入License绑定的IP地址.");
		}
		
		if($("#isBoundMacAddress")[0].checked && $("#boundMacAddress").val() == "") {
			$("#flag6").html("已指定Mac地址校验，请输入License绑定的Mac地址.");
		}
		
		if($("#isBoundHostname")[0].checked && $("#boundHostname").val() == "") {
			$("#flag7").html("已指定主机名校验，请输入License绑定的主机名.");
		}
		

		
		if($("#start").val() == "") {
			$("#flag8").html("请指定License的开始时间.");
			return false;
		} 
		
		if($("#isForever")[0].checked == true) {
			$("#end").html("-1");
		}
		
		if($("#isForever")[0].checked == false && $("#end").val() == "") {
			$("#flag8").html("请指定License的截止时间.");
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
