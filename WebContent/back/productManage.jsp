﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.*"%>
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

	
	<script type="text/javascript">
	
		function deleteProduct(e,l){
		    var islogin = ${isLogined};
		    var type = l;
		    if (!islogin) {
		        alert("该内容需要登录后方能删除");
		        return false;
		    }
		    
		    var r=confirm("您确定删除吗？");
		    
		    if (r==false) return false;
		    
		    if ($(e).attr("id") == "") {
		        alert("暂无文件");
		        return false;
		    } else {
		    	$.ajax({
					type: "POST",
					dataType: "text",
					url: "${basePath}/product/delete",
					data:"productId="+$(e).attr("id"),
					async: false,
					error: function(request) {
						alert("服务器连接错误！");
					},
					success: function(data) {
						if(data == "success") {
							deleteElement($(e).attr("id"));
							alert("删除成功！");
							
						} else if(data == "no_exist") {
							$("#deleteResult").html("不存在删除条目，操作失败！");
						}
					}
				});
		    	//window.location.href = "${basePath}/product/delete?productId=" + $(e).attr("id");		        
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
								<a href="${basePath}/pages/userinfo.jsp"><shiro:principal/></a>,  欢迎您！| <a
									onclick="logout()">退出</a>
							</c:when>
							<c:otherwise>
								<a href="${basePath}/login.jsp">登录</a><!-- | <a onclick="register()">注册</a>  -->
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
  
  <div class="w710r">
   <div class="rczp pbb">
      <div class="zcxz2">
         <span>产品</span>
         <select class="se1" id="txtProduct" name="productName">
        	<option value="0">--请选择产品--</option>
    	 </select>
         <span>版本</span>
         <select class="se2" id="txtProductVersion" name="productVersion">
        	<option value="0">--请选择版本--</option>
    	 </select>
         <span>平台</span>
         <select class="se2" id="txtLevel" name="platform">
        	<option value="0">--请选择类型--</option>
    	 </select>
         <a class="pbg4" href="javascript:void(0);" onclick="productQuery();">查询</a>
         &nbsp;&nbsp;
         <a class="pbg4" href="${basePath}/back/productAdd.jsp">添加</a>
      </div>   
      <table width="794px" border="0" cellspacing="0" cellpadding="0" class="ta4"> 
          <tbody id="productBody">
          	<tr>
              <th width="90">产品名称</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="50">版本</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="80">平台</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="95">创建时间</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="60">大小</th>
              <th width="1"><img src="<%=basePath%>/resources/images/tlbg35.jpg"></th>
              <th width="70">删除</th>
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
	          	<td id="delete_file"></td>
          	</tr>
      	</tbody>
      </table>
      <p class="error" id="contentInfo">${resultInfo}</p>
      <div class="ym" id="setpage"></div>
      <div class="error" id="deleteResult">${result}</div>
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
			})
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
		
		if($("#txtLevel").val() != '0') {
			platform = $("#txtLevel").val();
		}
		$("#contentInfo").html("");
		$.ajax({
			   type: "GET",
			   url: "${basePath}/product/list",
			   data: {"currentPage":num, "productName":productName, "productVersion":productVersion, "txtLevel":level},
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
	    	 v.createTime.substring(0,18);
	    	 row.find("#product_createTime").text(v.createTime.substring(0,19));
	    	 row.find("#product_fileSize").text(v.fileSize + "M");
	    	 row.find("#delete_file").html("<a class=\"aD6\" id=\"" + v.id + "\" onclick=\"return deleteProduct(this, '');\"></a>");
	    	 $("#productBody").append(row);
	     });
	}
	
	function deleteElements() {
		$(".preAttr").remove();
	}
	
	function deleteElement(id) {
		$("#"+id).parent().parent().remove();
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
		
		if($("#txtLevel").val() != '0') {
			platform = $("#txtLevel").val();
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
