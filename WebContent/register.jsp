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
<script type=text/javascript src="${basePath}/resources/js/ProJson.js"></script>
<script type=text/javascript src="${basePath}/resources/js/CityJson.js"></script>
<script type=text/javascript src="${basePath}/resources/js/CompanySize.js"></script>
<script type=text/javascript src="${basePath}/resources/js/CompanyType.js"></script>
<script type=text/javascript src="${basePath}/resources/js/ConsumerTitle.js"></script>
<meta name=GENERATOR content="MSHTML 8.00.7601.17514">

	
	<script type="text/javascript">
        $(function () {
        	
        	$.each(CompanyType, function (k, p) { 
                var option = "<option id='" + k + "' value='" + p + "'>" + p + "</option>";
                $("#txtComType").append(option);
            });
        	
        	$.each(ConsumerTitle, function (k, p) { 
                var option = "<option id='" + k + "' value='" + p + "'>" + p + "</option>";
                $("#txtTitle").append(option);
            });
        	
        	$.each(CompanySize, function (k, p) { 
                var option = "<option id='" + k + "' value='" + p + "'>" + p + "</option>";
                $("#txtComSize").append(option);
            });
        	
            $.each(province, function (k, p) { 
                var option = "<option id='" + p.ProID + "' value='" + p.ProName + "'>" + p.ProName + "</option>";
                $("#txtProvince").append(option);
            });
             
            $("#txtProvince").change(function () {
                var selValue = $(this).val();
                var selId = $("[value='"+selValue + "']").attr("id");
                $("#txtCity option:gt(0)").remove();
                $.each(city, function (k, p) { 
                    if (p.ProID == selId) {
                        var option = "<option value='" + p.CityName + "'>" + p.CityName + "</option>";
                        $("#txtCity").append(option);
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
								<a href="${basePath}/login.jsp">登录</a> | <a onclick="register()">注册</a>
							</c:otherwise>
						</c:choose>
					</div>
					<div class=clear></div>
				</div>
				<div class=clear></div>
			</div>
			<div class=clear></div>
		</div>

		<div class=banner>
			<img src="${basePath}/resources/images/2014213937339644444.jpg"
				width=1440 height=273>
		</div>

<div class="main">
  <div id="logindiv">
   <div class="zc">
   <form action="${basePath}/user/register" id="register" method="post">
      <table width="961px" class="ta2"> 
          <tbody>
          <tr>
              <th colspan="3">填写注册信息</th>
          </tr> 
          <tr>
            <td colspan="3" class="td2">&nbsp;</td>
          </tr>       
          <tr>
            <td class="td1"><span class="error">*</span>用户名：</td>
            <td colspan="2"><input onblur="onblurUser()" name="name" type="text" id="txtUserName"><span id="flag">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="userNamePrompt" class="hightlight"></span></td>
          </tr>
          <tr>
            <td class="td1"><span class="error">*</span>密码：</td>
            <td colspan="2"><input onblur="onblurPassword()" name="password" type="password" id="txtPassword"><span id="flag2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="passwordPrompt" class="hightlight"></span></td>
          </tr>
          <tr>
            <td class="td1"><span class="error">*</span>确认密码：</td>
            <td colspan="2"><input onblur="onblurPassword2()" type="password" id="txtPassword2"><span id="flag3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="passwordPrompt2" class="hightlight"></span></td>
          </tr>       
		  
          <tr>
            <td class="td1"><span class="error">*</span>电子邮箱：</td>
            <td colspan="2"><input onblur="onblurEmail()" name="email" type="text" id="txtEmail"><span id="flag4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="emailPrompt" class="hightlight"></span></td>
          </tr>
          
          <tr>
            <td>&nbsp;</td>
            <td>
               <a onclick="return checkRegister();" id="lbtnRegister" class="bg3">立即注册</a> 
            </td>
            <td></td>
          </tr>
       </tbody></table>
	</form>
	<div id="registerSuccess" class="hightlight" style="display:true;font-size:14px">
   	</div>
   </div>
   
  <div class="clear"></div>
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
		function register() {
			logout();
			window.location.href = "${basePath}/register.jsp"
		}
		function logout() {
			window.location.href="${basePath}/logout";
		}
	</script>
	
<script type="text/javascript">
//用户校验：
$(function(){
	$("#txtUserName").focus(
		function(){
			$("#userNamePrompt").attr("class","hightlight");
			$("#flag").removeAttr("class");
			$("#userNamePrompt").html(validatePrompt.regName.onFocus);
		}	
	);
	
});

function onblurUser() {
	 var username = $("#txtUserName").val();
	 if(validateRules.isNull(username)) {
		$("#userNamePrompt").attr("class","error");
		$("#flag").attr("class","spanError");
	    $("#userNamePrompt").html(validatePrompt.regName.isNull);
	    return false;
	 }
	 if(!validateRules.betweenLength(username, 6, 20)) {
		 $("#userNamePrompt").attr("class","error");
		 $("#flag").attr("class","spanError");
	     $("#userNamePrompt").html(validatePrompt.regName.error.badLength);
	     return false;
	 }
	 
	 if(!validateRules.isUid(username)) {
		 $("#userNamePrompt").attr("class","error");
		 $("#flag").attr("class","spanError");
	     $("#userNamePrompt").html(validatePrompt.regName.error.badFormat);
	     return false;
	 }
	 
	 var exist = validateUser(username);
	 
	 if(exist) {
		 $("#userNamePrompt").attr("class","error");
		 $("#flag").attr("class","spanError");
   		 $("#userNamePrompt").html(validatePrompt.regName.error.beUsed);
		 return false;
	 }
<%--
	 $.ajax({
		   type: "POST",
		   url: "${basePath}/user/validateUsername",
		   data: "userName=" + username,
		   async: false,
		   dataType: "json",
		   success: function(msg) {
			   if(msg == 'exist') {
				   $("#userNamePrompt").attr("class","error");
				   $("#flag").attr("class","spanError");
				   $("#userNamePrompt").html(validatePrompt.regName.error.beUsed);
				   return false;
			   } else {
				   $("#userNamePrompt").attr("class","error");
				   $("#flag").attr("class","spanError");
				   $("#userNamePrompt").html(validatePrompt.regName.error.beUsed);
				   alert(msg);
				   return false;
			   }
		   }
	 });
	 --%>
	 
	 $("#userNamePrompt").html("");
	 $("#flag").attr("class","spanSuccess")
	 return true;
}

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
	 <%--
	 if(!validateRules.betweenLength(password2, 6, 20)) {
		 $("#passwordPrompt2").attr("class","error");
		 $("#flag3").attr("class","spanError");
	     $("#passwordPrompt2").html(validatePrompt.pwdRepeat.error.badLength);
	     return false;
	 }
	 
	 if(!validateRules.isPwd(password2)) {
		 $("#passwordPrompt2").attr("class","error");
		 $("#flag2").attr("class","spanError");
	     $("#passwordPrompt2").html(validatePrompt.pwdRepeat.error.badFormat1);
	     return false;
	 }
	--%>
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

//校验邮箱：

$(function(){
	$("#txtEmail").focus(
		function(){
			$("#emailPrompt").attr("class","hightlight");
			$("#flag4").removeAttr("class");
			$("#emailPrompt").html(validatePrompt.email.onFocus);
		}		
	);
	
});

function onblurEmail() {
	 var email = $("#txtEmail").val();
	 if(validateRules.isNull(email)) {
		$("#emailPrompt").attr("class","error");
		$("#flag4").attr("class","spanError");
	    $("#emailPrompt").html(validatePrompt.email.isNull);
	    return false;
	 }
	 
	 if(!validateRules.isEmail(email)) {
		 $("#emailPrompt").attr("class","error");
		 $("#flag4").attr("class","spanError");
	     $("#emailPrompt").html(validatePrompt.email.error.badFormat);
	     return false;
	 }
	 
	 var exist = validateEmail(email);
	 if (exist) {
		 $("#emailPrompt").attr("class","error");
		 $("#flag4").attr("class","spanError");
		 $("#emailPrompt").html(validatePrompt.email.error.beUsed);
	 	 return false;
	 }
<%--
	 $.ajax({
		   type: "POST",
		   url: "${basePath}/user/validateEmail",
		   data: "email=" + email,
		   dataType: "text",
		   success: function(msg) {
			   if(msg == 'exist') {
				   $("#emailPrompt").attr("class","error");
				   $("#flag4").attr("class","spanError");
				   $("#emailPrompt").html(validatePrompt.email.error.beUsed);
				   return false;
			   } else {
				   
			   }
		   }
	 });
--%>
	 $("#emailPrompt").html("");
	 $("#flag4").attr("class","spanSuccess");
	 return true;
}

//手机号码校验

$(function(){
	$("#txtTel").focus(
		function(){
			$("#telPrompt").attr("class","hightlight");
			$("#flag5").removeAttr("class");
			$("#telPrompt").html(validatePrompt.phone.onFocus);
		}		
	);
	
});

function onblurTel() {
	 var phone = $("#txtTel").val();
	 if(validateRules.isNull(phone)) {
		$("#telPrompt").attr("class","error");
		$("#flag5").attr("class","spanError");
	    $("#telPrompt").html(validatePrompt.phone.isNull);
	    return false;
	 }
	 
	 if(!validateRules.isTel(phone)) {
		 $("#telPrompt").attr("class","error");
		 $("#flag5").attr("class","spanError");
	     $("#telPrompt").html(validatePrompt.phone.error.badFormat);
	     return false;
	 }

	 $("#telPrompt").html("");
	 $("#flag5").attr("class","spanSuccess");
	 return true;
}


function validateUser(username) {
	var exist;
	$.ajax({
		cache: true,
		type: "POST",
		url: "${basePath}/user/validateUser",
		data: "userName=" + username,
		dataType: "text",
		async: false,
		error: function(request) {
			alert("访问服务器失败！");
		},
		success: function(data) {
			if(data == "exist") {
				exist = true;
			} else {
				exist = false;
			}
		}
	});
	return exist;
}


function validateEmail(email) {
	var exist;
	$.ajax({
		cache: true,
		type: "POST",
		url: "${basePath}/user/validateEmail",
		data: "email=" + email,
		async: false,
		error: function(request) {
			alert("访问服务器失败！");
		},
		success: function(data) {
			if(data == 'exist') {
				exist = true;
			} else {
				exist = false;
			}
		}
	});
	return exist;
}

function checkRegister() {
	var vu = onblurUser();
	var vp = onblurPassword();
	var vpp = onblurPassword2();
	var ve = onblurEmail();
	var vt = onblurTel();
		
	$.ajax({
			cache: true,
			type: "POST",
			url: "${basePath}/user/register",
			data:$('#register').serialize(),
			async: false,
			error: function(request) {
				alert("访问服务器失败！");
			},
			success: function(data) {
				alert("恭喜您，注册成功！");
				$("#registerSuccess").html("注册成功，请单击<a href=\"${basePath}/login.jsp\">此处</a>登录，<span id=\"time\">5</span>秒后跳转到登录页面！");
				startDown();
			}
		});
}
</script>
<script type="text/javascript"> 
	var t = 6;
	var inter;
	function countDown() { 
		//var time = document.getElementById("time");
		//alert(time);
		t--;
		$("#time").html(t)
		//time.value=t; 
		if (t <= 0) { 
			location.href="${basePath}/login.jsp"; 
			clearInterval(inter); 
		}
	}
	function setDown() {
		inter = setInterval("countDown()",1500);
	}
	function startDown() {
		setDown();
		countDown();
	}
//window.onload=countDown; 
</script> 
</body>
</html>
