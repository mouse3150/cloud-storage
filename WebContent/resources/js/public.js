// JavaScript Document
//导航菜单

$(".div_imgz .img_ul").width($(".div_imgz .img_ul li").size()*1440);

$(".ttp").find("h2").hover(function(){
	$(this).parent().find("p").animate({height:"30px"},0)
	//$(this).parent().find("p").addClass("bor1");

},function(){
 
	$(this).parent().find("p").animate({height:"15px"},0)
	//$(this).parent().find("p").removeClass("bor1");
	
});


//
  
//$(".right_ul").find("li:even").addClass("bgg");
$(".touimg").each(function() {
		$(this).width($(this).find("img").width())
		$(this).hide();
    });
$(function(){
	 
    $(".right_ul").find(".bgg").hover(function(){
     var tscr= $(this).find(".imgt img").attr("src");
	 var widthh=$(this).width();
	 var touwidth=$(this).find(".touimg").width();
	 var tt=(widthh-touwidth)/2;
	 $(this).addClass("bgt");
	 $(this).find(".text").addClass("text1");
	 $(this).find(".touimg").css("left",tt);
  	 $(this).find(".touimg").show();
	 
	 $(this).parent().parent().find(".right_img").find("img").attr("src",tscr);
	 //alert($(".right_img img").attr("src"));
     $(this).parent().parent().find(".right_img").show();	 
  
 },function(){
	 $(this).removeClass("bgt");
	 $(this).find(".text").removeClass("text1");
	 $(this).find(".touimg").hide();
	 $(this).parent().parent().find(".right_img").hide();

  });
});
	


$(".a3").click(function(){
	if($(this).parents("tr").next().is(":visible"))
	{$(this).parents("tr").next().hide();
		}
	else{
	$(".tr1").hide();
	$(this).parents("tr").next().show();}	
				
		});
$(".nav1").hover(function(){
		$(this).find(".thred").show();
		size=$(this).find("li").size();
		$(this).find("li:last").css("background","none");
		$(this).find("li").each(function() {
          $(this).find("dl:last").css("background","none"); 
        });
		$(this).parents("td").css("z-index",1000);
		var hei=0;		
		for(i=0;i<size;i++){
			if(hei<$(this).find("li").eq(i).height()){
				hei=$(this).find("li").eq(i).height()
				}	
			
			}
		$(this).find("li").height(hei);
		$(this).find("a:first").addClass("cc1");	
	},function(){
		$(this).find(".thred").hide();
		$(this).find("a:first").removeClass("cc1");
		$(this).parents("td").css("z-index",10);
		});		

$(".chan_z").find(".w_236:last").css("margin-right","0px");


$(".zy0 li").hover(function(){
	$(".zy0 li").removeClass("cc");
	$(".zy1").hide();
	$(".zy3 .zy1").eq($(".zy0 li").index(this)).show();
	$(this).addClass("cc");	
	});
	
$(".zhi_ul").find("li:last").css("background","none");
$(".guan_ul").find("li:last").css("background","none");


$(".zhi_ul").find("li").click(function(){
	$(".addli").removeClass("addli");
	$(this).addClass("addli");
//	var indext=$(this);
//	$(".zhi_con").hide();
//	$(".zhi_con").eq($(".zhi_ul").find("li").index(indext)).show();
});

$(".img_ul").find("ul li").hover(function(){
	$(this).find(".guan_d").fadeIn(500);
	},function(){
	$(this).find(".guan_d").fadeOut(500);	
});



	$(".ul_gua ul").width($(".ul_gua ul li").size()*757);
    unclick = true;
    $(".guan_left").click(function (){
        var next1 = $(this).next();
        if (unclick) {
            unclick = false;
            next1.animate({ scrollLeft: -757 + next1.scrollLeft() }, 500, function () {
             unclick = true;
            })
        }
        return false
    })
    $(".guan_righ").click(function () {
        var pre1 = $(this).prev();
        if (unclick) {
            unclick = false
            pre1.animate({ scrollLeft: 757 + pre1.scrollLeft() }, 500,function(){unclick = true;
            })
        }
        return false
  
       });
//导航;
/*$(".nav").find("td:last").after("<td><div class=\"nav1\"><a href=\"http://www.cninfo.com.cn/information/companyinfo.html?brief?szcn300379\" target=\"_blank\">投资者关系</a></div></td>");
$(".top_r1").css("margin-left",490-$(".top_r1").width());
$(".nav td").eq(3).find("li").eq(2).find("h3").find("a").html("售后服务类型");
$(".nav td").eq(3).find("li:last").find("h1").html("&nbsp;").css("height",54);
*/
//
$(".nxet").addClass("nxet_t");
$(".yser ul").width($(".yser li").size()*130);
var unclick=true;
$(".perv").click(function(){
	$(".nxet").addClass("nxet_t");
	dom=$(this).next();
	if(unclick){
		unclick=false;
		dom.animate({scrollLeft:-130+dom.scrollLeft()},300,function(){
			if(dom.scrollLeft()<130){
				$(".perv").removeClass("perv_t");
				}
			unclick=true;
			});
		 return false;
		}
	});
$(".nxet").click(function(){
	$(".perv").addClass("perv_t");
	dom=$(this).prev();
	if(unclick){
		unclick=false;
		dom.animate({scrollLeft:130+dom.scrollLeft()},300,function(){
			if(dom.scrollLeft()>$(".yser ul").width()-700){
				$(".nxet").removeClass("nxet_t");
				}
			unclick=true;
			});
		 return false;
		}
	});
$(".yser li").hover(function(){
		$(this).addClass("hce_r");
		$(this).click(function(){
			$(".chise").removeClass("chise");
			$(this).addClass("chise");
			});
	},function(){
		$(this).removeClass("hce_r");
		});
//
		$("form iframe").hide();
		
		
		
	// 验证规则		
	var validateRules = {
	    isNull: function(str) {
	        return (str == "" || typeof str != "string");
	    },
	    betweenLength: function(str, _min, _max) {
	        return (str.length >= _min && str.length <= _max);
	    },
	    isUid: function(str) {
	        return new RegExp(validateRegExp.username).test(str);
	    },
	    fullNumberName: function(str) {
	        return new RegExp(validateRegExp.fullNumber).test(str);
	    },
	    isPwd: function(str) {
	        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
	    },
	    isPwdRepeat: function(str1, str2) {
	        return (str1 == str2);
	    },
		isEmail: function(str) {
		    return new RegExp(validateRegExp.email).test(str);
		},
		isTel: function(str) {
			return new RegExp(validateRegExp.tel).test(str);
		},
		isMobile: function(str) {
			return new RegExp(validateRegExp.mobile).test(str);
		},
		checkType: function(element) {
			return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
		},
		isRealName: function(str) {
			return new RegExp(validateRegExp.realname).test(str);
		},
		isCompanyname: function(str) {
			return new RegExp(validateRegExp.companyname).test(str);
		},
		isCompanyaddr: function(str) {
			return new RegExp(validateRegExp.companyaddr).test(str);
		},
		isCompanysite: function(str) {
			return new RegExp(validateRegExp.companysite).test(str);
		},
		simplePwd: function(str) {
			// var pin = $("#regName").val();
			// if (pin.length > 0) {
			// pin = strTrim(pin);
			// if (pin == str) {
			// return true;
			// }
			// }
			return pwdLevel(str) == 1;
		},
		weakPwd: function(str) {
			for (var i = 0; i < weakPwdArray.length; i++) {
			     if (weakPwdArray[i] == str) {
			        return true;
			     }
			}
			return false;
		}
	};
	
	// 验证文本
	var validatePrompt = {
	    regName: {
	        onFocus: "6-20位字符，支持中英文、数字及\"-\"、\"_\"组合",
	        succeed: "",
	        isNull: "请输入用户名",
	        error: {
	            beUsed: "该用户名已被使用，请重新输入。如果您是该用户，请登录",
	            badLength: "用户名长度只能在6-20位字符之间",
	            badFormat: "用户名只能由中文、英文、数字及\"-\"、\"_\"组成",
	            fullNumberName: "用户名不能是纯数字，请重新输入"
	        },
	        onFocusExpand: function() {
	            $("#morePinDiv").removeClass().addClass("intelligent-error hide");
	        }
	    },

	    pwd: {
	        //onFocus: "6-20位字符，可使用字母、数字或符号的组合，不建议使用纯数字，纯字母，纯符号",
	    	onFocus: "6-20位字符，可使用字母、数字或符号的组合",
	        succeed: "",
	        isNull: "请输入密码",
	        error: {
	            badLength: "密码长度只能在6-20位字符之间",
	            badFormat: "密码只能由英文、数字及标点符号组成",
	            simplePwd: "<span>该密码比较简单，有被盗风险，建议您更改为复杂密码，如字母+数字的组合</span>",
	            weakPwd: "<span>该密码比较简单，有被盗风险，建议您更改为复杂密码</span>"
	        },
	        onFocusExpand: function() {
	            $("#pwdstrength").hide();
	        }
	    },
	    pwdRepeat: {
	        onFocus: "请再次输入密码",
	        succeed: "",
	        isNull: "请确认密码",
	        error: {
	            badLength: "密码长度只能在6-20位字符之间",
	            badFormat2: "两次输入密码不一致",
	            badFormat1: "密码只能由英文、数字及标点符号组成"
	        }
	    },
	    phone: {
	        onFocus: "请输入手机号码",
	        succeed: "",
	        isNull: "手机号码不能为空",
	        error: {
	            badFormat: "您输入的手机号码格式不正确，请确认后重新输入"
	        }
	    },
	    email: {
	        onFocus: "请输入邮箱地址",
	        succeed: "",
	        isNull: "邮箱地址不能为空",
	        error: {
	        	beUsed: "该邮箱地址已经被注册，请您确认",
	            badFormat: "您输入的邮箱格式不正确，请确认后重新输入"
	        }
	    },
	    protocol: {
	        onFocus: "",
	        succeed: "",
	        isNull: "请先阅读并同意《京东用户注册协议》",
	        error: ""
	    },
	    empty: {
	        onFocus: "",
	        succeed: "",
	        isNull: "",
	        error: ""
	    }
	};
	
	var validateRegExp = {
		    decmal: "^([+-]?)\\d*\\.\\d+$",
		    // 浮点数
		    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",
		    // 正浮点数
		    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",
		    // 负浮点数
		    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",
		    // 浮点数
		    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",
		    // 非负浮点数（正浮点数 + 0）
		    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",
		    // 非正浮点数（负浮点数 +
		    // 0）
		    intege: "^-?[1-9]\\d*$",
		    // 整数
		    intege1: "^[1-9]\\d*$",
		    // 正整数
		    intege2: "^-[1-9]\\d*$",
		    // 负整数
		    num: "^([+-]?)\\d*\\.?\\d+$",
		    // 数字
		    num1: "^[1-9]\\d*|0$",
		    // 正数（正整数 + 0）
		    num2: "^-[1-9]\\d*|0$",
		    // 负数（负整数 + 0）
		    ascii: "^[\\x00-\\xFF]+$",
		    // 仅ACSII字符
		    chinese: "^[\\u4e00-\\u9fa5]+$",
		    // 仅中文
		    color: "^[a-fA-F0-9]{6}$",
		    // 颜色
		    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
		    // 日期
		    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
		    // 邮件
		    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$",
		    // 身份证
		    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",
		    // ip地址
		    letter: "^[A-Za-z]+$",
		    // 字母
		    letter_l: "^[a-z]+$",
		    // 小写字母
		    letter_u: "^[A-Z]+$",
		    // 大写字母
		    mobile: "^0?(13|15|18|14|17)[0-9]{9}$",
		    // 手机
		    notempty: "^\\S+$",
		    // 非空
		    password: "^.*[A-Za-z0-9\\w_-]+.*$",
		    // 密码
		    fullNumber: "^[0-9]+$",
		    // 数字
		    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",
		    // 图片
		    qq: "^[1-9]*[1-9][0-9]*$",
		    // QQ号码
		    rar: "(.*)\\.(rar|zip|7zip|tgz)$",
		    // 压缩文件
		    tel: "^[0-9\-()（）]{7,18}$",
		    // 电话号码的函数(包括验证国内区号,国际区号,分机号)
		    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",
		    // url
		    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$",
		    // 户名
		    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
		    // 单位名
		    zipcode: "^\\d{6}$",
		    // 邮编
		    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$",
		    // 真实姓名
		    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
		    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
		    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
		};