<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${cssPath}/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<%-- <script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.min.js"></script> --%>
<title>云端行分销平台--用户登录</title>

<body>
	<div class="top-fix">
		<div class="header-w">
			<img src="${imagesPath}/fx_logo.png" alt="酷秀ERP云平台" class="left" />
		</div>
	</div>
	
	<div class="center-fix">
		<div class="center-w">
			<img src="${imagesPath}/fx_login_pic.png" alt="" class="left" />
			<div class="right login-box radius">
				<h4>欢迎登录</h4>
				<form:form method="post" id="demo_104" commandName="" htmlEscape="true">
					<input type="hidden" name="lt" value="" />
					<input type="hidden" name="execution" value="" />
					<input type="hidden" name="_eventId" value="submit" />
					<div class="inp-box radius">
						<span class="user left"></span> <input cssErrorClass=""
							tabindex="1" id="username" path="username" autocomplete="on"
							name="loginName" onblur="" class="form-control"
							placeholder="请输入用户名" type="text" maxlength="20" />
					</div>
					<div class="inp-box radius">
						<span class="pw left"></span> <input cssErrorClass="" tabindex="2"
							id="password" path="password" accesskey="" autocomplete="off"
							name="password" onblur="" type="password" class="form-control"
							placeholder="请输入密码" maxlength="20" />
					</div>
					<div class="inp-box radius">
						<input name="vcode" id="vcode" type="text" placeholder="请输入验证码" style="width:146px;" onblur="" />
						<img align="right" src="${ctx}/authCode" id="vcodeImage" style="cursor:pointer;margin-bottom: -15px;border-radius:5px;" /><br />
					</div>
					<p class="error" id="erroReply">&nbsp;</p>
					<form:errors path="*" id="msg" element="p" />
					<button type="button" id="save" class="radius">登&nbsp;录</button>
				</form:form>
			</div>
		</div>
	</div>

	<div class="bottom-fix">2011 - 2017  云端行. All Rights
		Powered by YunDuanXing</div>
</body>

<script type="text/javascript">
	
	//页面加载完成之后
	$(function() {
		$('#vcodeImage').click(function() {  
			$(this).hide().attr('src', '${ctx}/authCode?'+ Math.floor(Math.random() * 100)).fadeIn();
			event.cancelBubble = true;
		});
		
		//让账号文本框获取焦点
		$("#username").focus();
		//给整个登录窗口注册keydown事件
		$(window).keydown(function(event) {
			if (event.keyCode == 13) {
				login() ;
			}
		});
		
		$("#save").click(function() {
			login() ;
		});
		/* $("#demo_104").validate({
        rules:{
            name:{ required:true},
            password:{required:true},
            vcode:{required:true,checkCode:true}
        },
        messages:{
            name:{required:"姓名不能为空"},
            password:{required:"密码不能为空"},
            vcode:{required:"验证码不能为空"}
        }
    });
    
      jQuery.validator.addMethod("checkCode", function(value, element) {       
                var strCode = ${sessionScope.strCode};
                var inpCode = $('#vcode').val();
                if(strCode==""||strCode == null){
                    changeCode();
                    //用后台的字符与页面输入的验证码进行比较
                }else if(inpCode == strCode){
                    return true;
                }else{
                    return false;
                }
         }, "验证码不正确"); */
	}) ; 
	
	function login() {
		//发送ajax请求进行登录认证
		$.ajax({
			url : "${ctx}/login",
			type : "post",
			data : {
				"username" : $("#username").val(),
				"password" : $("#password").val(),
				"vcode"    : $("#vcode").val(),
			},
			beforeSend : function() {
				$("#erroReply").html("正在进行身份验证请稍后...");
				return true;
			},
			success : function(data) {
				if (data == "nameOrPasswordError") {
					$("#erroReply").html("用户名或密码错误");
					var requestUrl = "${ctx}/login";
					var recive = '';
					var requestData = {};
					cserpLoadPage(requestUrl, requestData, 'POST', 'mainContentDiv') ;
				}  else if (data == "checkNumError") {
					$("#erroReply").html("验证码错误");
					changeCode();
					var requestUrl = "${ctx}/login";
					var recive = '';
					var requestData = {};
					cserpLoadPage(requestUrl, requestData, 'POST', 'mainContentDiv');
				} else {
					window.location.href = "${ctx}/manage";
				}
			}
		});
	}

	//切换验证码  
	/* function changeCode() {
		$('#kaptchaImage').hide().attr('src', '${ctx}/kaptcha/vcode?' + Math.floor(Math.random() * 100)).fadeIn() ;
		event.cancelBubble = true ;
	} */
	function changeCode(){
        $('#vcodeImage').attr('src','${ctx}/authCode?'+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
    }
</script>
</html>