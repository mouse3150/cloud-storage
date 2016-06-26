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
<script type=text/javascript src="${basePath}/resources/js/json2.js"></script>
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
                
                $("#txtPlatform option:gt(0)").remove();
                $.each(productLevel, function (k, p) { 
                    if (p.proId == selId) {
                        var option = "<option value='" + p.level + "'>" + p.description + "</option>";
                        $("#txtPlatform").append(option);
                    }
                });
            }); 
            
        });
    </script>

	
	<script type="text/javascript">
	
		function CheckLoginDownLoad(e,l){
			$("#contentInfo").html("");
		    var islogin = ${isLogined};
		    var type = l;
		    if (!islogin) {
		        alert("该内容需要登录后方能下载!");
		        window.location.href = "${basePath}/login.jsp";
		        return false;
		    }
		  //许可证下载限制
		    if($(e).parent().parent().children().first().html() == 'Param' && type != '') {
		    	alert("抱歉！ 暂不提供许可证下载");
		    	return false;
		    }
		    
		    if ($(e).attr("id") == "") {
		        alert("暂无文件");
		        return false;
		    } else {
		        if(type == '') {
		        	<%--$.ajax({
						type: "GET",
						dataType: "text",
						url: "${basePath}/product/download",
						data:"productId="+$(e).attr("id"),
						async: false,
						error: function(request) {
							alert("服务器连接错误！");
						},
						success: function(data) {
							if(data == "no_file") {
								alert("产品文件不存在!");
							} else if(data == "no_product") {
								alert("您要下载的产品不存在!");
							}
						}
					});--%>
		        	//window.location.href = "${basePath}/product/download?productId=" + $(e).attr("id");
		        	
		        	var tmpForm = $("<form action='${basePath}/product/download' method='post'></form>");
		            tmpForm.append("<input type='hidden' value='"+$(e).attr("id")+"' name='productId'/>");
		            tmpForm.appendTo(document.body).submit();
		        } else {
		        	//window.location.href = "/license/create?productId=" + $(e).attr("id");
		        	var tmpForm = $("<form action='${basePath}/license/create' method='post'></form>");
		            tmpForm.append("<input type='hidden' value='"+$(e).attr("id")+"' name='productId'/>");
		            tmpForm.appendTo(document.body).submit();
		        }
		        
		    }
		}
		function redirect(url) {
			window.parent.frames.location.href=url;
		}
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
								<shiro:principal/>&nbsp;<a href="${basePath}/pages/userinfo.jsp">【管理】</a>&nbsp;|&nbsp;&nbsp;<a onclick="logout()">退出</a>
							</c:when>
							<c:otherwise>
								<a href="${basePath}/login.jsp">登录</a>&nbsp;&nbsp;<!--|&nbsp;&nbsp;<a onclick="register()">注册</a>  -->
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
   		<li><a href="${basePath}/index.jsp">许可管理</a></li>
   		<li><a href="${basePath}/pages/product.jsp"  class="cc">产品管理</a></li>
   		<li><a href="${basePath}/back/objectManage.jsp">存储Demo</a>
   			<ul class="leftul3">
   				<li><a href="${basePath}/index.jsp">技术白皮书</a></li>
   				<li><a href="${basePath}/index.jsp">使用说明</a></li>
   				<li><a href="${basePath}/index.jsp">开发文档</a></li>
   			</ul>
   		</li>
   </ul>

  </div>

  <div class="w710r">
   <div class="rczp pbb">
      <div class="zcxz1">
         <span>产品</span>
         <select class="se1" id="txtProduct" name="productName">
        	<option value="0">--请选择产品--</option>
    	 </select>
         <span>版本</span>
         <select class="se2" id="txtProductVersion" name="productVersion">
        	<option value="0">--请选择版本--</option>
    	 </select>
         <span>类型</span>
         <select class="se2" id="txtPlatform" name="platform">
        	<option value="0">--请选择类型--</option>
    	 </select>
         <a href="javascript:void(0);" onclick="productQuery();">查询</a>
         <shiro:hasAnyRoles name="admin">
	         &nbsp;&nbsp;
	         <a class="pbg4" href="${basePath}/back/productAdd.jsp">创建</a>
         </shiro:hasAnyRoles>
      </div>   
      <table width="810px" border="0" cellspacing="0" cellpadding="0" class="ta4"> 
          <tbody id="productBody">
          	<tr>
              <th width="90">产品名称</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="50">版本</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="80">级别</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="95">创建时间</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="60">大小</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="70">产品下载</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="70">许可下载</th>
          	</tr>
          	<tr id="template" style="display:none">
	          	<td id="product_name"></td>
	          	<td>&nbsp;</td>
	          	<td id="product_version"></td>
	          	<td>&nbsp;</td>
	          	<td id="product_platform"></td>
	          	<td>&nbsp;</td>
	          	<td id="product_createTime"></td>
	          	<td>&nbsp;</td>
	          	<td id="product_fileSize"></td>
	          	<td>&nbsp;</td>
	          	<td id="download_file"></td>
	          	<td>&nbsp;</td>
	          	<td id="create_license"></td>
          	</tr>
      	</tbody>
      </table>
      <p class="error" id="contentInfo">${resultInfo}</p>
      <div class="ym" id="setpage"></div>
   </div>
  </div>
  <div class="clear"></div>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			   type: "GET",
			   url: "${basePath}/product/list",
			   dataType: "json",
			   //data: "",
			   success: function(msg){
			     cpage = msg.from;
			     totalpage = msg.totalPage;
			     pagesize = msg.limit;
			     setpage(cpage,totalpage,pagesize);
			     deleteElements();
			     var data = msg.data;
			     addElements(data);
			   }
			});
		
	});
	function gotopage(num) {
		var productName=null;
		var productVersion=null;
		var platform=null;
		if($("#txtProduct").val() != '0') {
			productName = $("#txtProduct").val();
		}
		
		if($("#txtProductVersion").val() != '0') {
			productVersion = $("#txtProductVersion").val();
		}
		
		if($("#txtPlatform").val() != '0') {
			platform = $("#txtPlatform").val();
		}
		$("#contentInfo").html("");
		$.ajax({
			   type: "GET",
			   url: "${basePath}/product/list",
			   data: {"currentPage":num, "productName":productName, "productVersion":productVersion, "txtPlatform":platform},
			   dataType: "json",
			   success: function(msg){
				   cpage = msg.from;
				   totalpage = msg.totalPage;
				   pagesize = msg.limit;
				   setpage(cpage,totalpage,pagesize);
				   deleteElements();  
				   var data = msg.data;
				   addElements(data);
			   }
		})
	}

	function addElements(data) {
		$.each(data, function(k, v) {
	    	 var row = $("#template").clone();
	    	 row.removeAttr("style");
	    	 row.attr("class","preAttr");
	    	 row.find("#product_name").text(v.name);
	    	 row.find("#product_version").text(v.version);
	    	 row.find("#product_platform").text(v.platform);
	    	 row.find("#product_createTime").text(v.createTime.substring(0,19));
	    	 row.find("#product_fileSize").text(v.fileSize + "M");
	    	 row.find("#download_file").html("<a class=\"a6\" id=\"" + v.id + "\" onclick=\"return CheckLoginDownLoad(this, '');\"></a>");
	    	 row.find("#create_license").html("<a class=\"a6\" id=\"" + v.id + "\" onclick=\"return CheckLoginDownLoad(this, 'license');\"></a>");
	    	 $("#productBody").append(row);
	     });
	}
	
	function deleteElements() {
		$(".preAttr").remove();
	}
	
	function productQuery() {
		var productName=null;
		var productVersion=null;
		var platform=null;
		if($("#txtProduct").val() != '0') {
			productName = $("#txtProduct").val();
		}
		
		if($("#txtProductVersion").val() != '0') {
			productVersion = $("#txtProductVersion").val();
		}
		
		if($("#txtPlatform").val() != '0') {
			platform = $("#txtPlatform").val();
		}
		$.ajax({
			   type: "GET",
			   url: "${basePath}/product/list",
			   data: {"productName":productName, "productVersion":productVersion, "platform":platform},
			   dataType: "json",
			   success: function(msg){
				   cpage = msg.from;
				   totalpage = msg.totalPage;
				   pagesize = msg.limit;
				   setpage(cpage,totalpage,pagesize);
				   deleteElements();  
				   var data = msg.data;
				   //var jsonStr = JSON.stringify(msg);
				   //alert(jsonStr);
				   //alert(data);
				   if(data=="") {
					   $("#contentInfo").html("无查询结果！");
				   } else {
					   $("#contentInfo").html("");
					   addElements(data);
				   }
			   }
		})
	}
</script>

<script type="text/javascript">
	//var totalpage,pagesize,cpage,count,curcount,outstr; 
	function setpage(cpage,totalpage,pagesize) {
		outstr = "";
	    if(totalpage<=10){        //总页数小于十页 
	        for (count=1;count<=totalpage;count++) {    
	        	if(count!=cpage) { 
	                outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
	            } else { 
	                outstr = outstr + "<a class='cc' >"+count+"</span>"; 
	            } 
	        } 
	    }
	    
	    if(totalpage > 10) {        //总页数大于十页 
	        if(parseInt((cpage-1)/10) == 0) {             
	            for (count=1;count<=10;count++) {    
	            	if(count!=cpage) { 
	                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
	                } else { 
	                    outstr = outstr + "<a class='cc'>"+count+"</span>"; 
	                }
	            }
	            outstr = outstr + "<a class='sx' href='javascript:void(0)' onclick='gotopage("+count+")'>向后</a>"; 
	        } else if (parseInt((cpage-1)/10) == parseInt(totalpage/10)) {
	            outstr = outstr + "<a class='sx' href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>向前</a>"; 
	            for (count=parseInt(totalpage/10)*10+1;count<=totalpage;count++) {
	            	if(count!=cpage) {
	                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
	                } else {
	                    outstr = outstr + "<a class='cc'>"+count+"</span>"; 
	                }
	            }
	        } else {  
	            outstr = outstr + "<a class='sx' href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>向前</a>"; 
	            for (count=parseInt((cpage-1)/10)*10+1;count<=parseInt((cpage-1)/10)*10+10;count++) {         
	                if(count!=cpage) { 
	                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
	                } else { 
	                    outstr = outstr + "<a class='cc'>"+count+"</span>"; 
	                }
	            }
	            outstr = outstr + "<a class='sx' href='javascript:void(0)' onclick='gotopage("+count+")'>向后 </a>"; 
	        }
	    }
	    document.getElementById("setpage").innerHTML = "<span id='info'>共 "+totalpage+" 页 | 第 "+ cpage +" 页</span>" + outstr; 
	    outstr = ""; 
	}
	
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
