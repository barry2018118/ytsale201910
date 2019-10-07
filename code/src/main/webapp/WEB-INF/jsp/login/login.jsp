<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/login_new.css">
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
</head>

<body>
    <div class="top">
        <div class="logo fl">
            <c:choose>
                <c:when test="${empty enterprise.logo}">
                    <img class="fl" src="${imagesPath}/login/logo.png" alt="">
                </c:when>
                <c:otherwise>
                    <img class="fl" src="${showResourcePath}${enterprise.logo}" alt="">
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${empty enterprise.platformName}">
                    <span class="fl">电子票务分销平台</span>
                </c:when>
                <c:otherwise>
                    <span class="fl">${enterprise.platformName}</span>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="fr serve">
            <c:if test="${!empty enterprise.customerPhone}">
                <ul>
                    <li>客服电话：${enterprise.customerPhone}</li>
                    <!-- 
                        <li>|</li>
                        <li>全程服务电话：${enterprise.customerPhone}</li>
                    -->
                </ul>
            </c:if>
        </div>
    </div>

    <div class="login">
        <div class="content">
            <div class="logbox">
                <div class="log-cont">
                    <div class="log-title">欢迎登录</div>
                    <!--登录信息-->
                    <div class="log-content clearfix" id="loginDiv1">
                        <form:form method="post" id="loginForm1" commandName="" htmlEscape="true">
                            <input type="text" id="username" class="log-in user" placeholder="请输入用户名" maxlength="11">
                            <input type="password" id="password" class="log-in pass" placeholder="请输入密码" maxlength="16">
                            <input type="text" id="vcode" name="vcode" class="fl log-yz" placeholder="请输入验证码" maxlength="4">
                            <div class="fl">
                                <img src="${ctx}/authCode" id="vcodeImage" style="width: 94px; height: 32px;">
                            </div>
                        </form:form>
                    </div>
                    
                    <!--动态验证码-->
                    <div class="log-content clearfix" id="loginDiv2" style="display: none">
                        <form:form method="post" id="loginForm2" commandName="" htmlEscape="true">
                            <input type="text" id="thePhone" class="log-in user" value="" disabled="disabled">
                            <input type="text" id="phoneCode" class="fl log-yz" placeholder="请输入动态码" maxlength="4">
                            <div class="fr">
                                <input type="text" id="getPhoneCode" class="dtm" value="获取动态码">
                            </div>
                        </form:form>
                    </div>
                    
                    <!--错误提示 （正常提示的话，去掉error样式）-->
                    <div class="tip error" id="erroReply"></div>
                    <div class="log-btn">
                        <a href="javascript:;" id="save">登录</a>
                        <a href="javascript:;" id="save2" style="display: none">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="content index_cont">
        <div class="index-tit">供应商与分销商的桥梁</div>
        <div class="index-intro">掌握全国景区一手票源，渠道可靠，成本低，利润高，满足客户一站式旅游预订需求</div>
        <div class="cp clearfix">
            <ul>
                <li><img src="${imagesPath}/login/icon_1.png" alt="">
                    <p>产品丰富 资源共享</p></li>
                <li><img src="${imagesPath}/login/icon_2.png" alt="">
                    <p>数据稳定 数据备份</p></li>
                <li><img src="${imagesPath}/login/icon_3.png" alt="">
                    <p>数据统计 报表输出</p></li>
            </ul>
        </div>
    </div>
    <div class="bottom">2011-2018 四川云端行. All right reserved
       蜀ICP备18016499号  Powered by YunDuanXing</div>
</body>

<script type="text/javascript">
    // 倒计时秒数
    var wait = 60;
    
    // random string value
    var _randomValue ;

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
        
        $("#getPhoneCode").click(function() {
            var username = $("#username").val() ;
            $(this).addClass("dtm1") ;
            showCatpionTime(this) ;
            
            jQuery.ajax({
                type : "post",
                data: {"phone": username},
                async : true,
                cache : false,
                url : "${ctx}/getPhoneCode",
                dataType : "json",
                success : function(data) {
                    _randomValue = data.value ;
                    $("#erroReply").html("序号："+ data.key + " 的验证码已发送至您的手机！") ;
                },
                error : function(e) {}
            });
        });
        
        $("#save2").click(function() {
            login2() ;
        });
    
    });

    function login() {
        var username = $("#username").val();
        if(username==null || username=="") {
            $("#erroReply").html("请输入用户名！");
            return false ;
        } else {
            var reg= /^[1][0-9]{10}$/ ;
            if(!reg.test(username)) {
                $("#erroReply").html("用户名格式不正确！");
                return false ;
            }
        }
        var password = $("#password").val();
        if(password==null || password=="") {
            $("#erroReply").html("请输入密码！");
            return false ;
        } else {
            var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/ ;
            if(!reg.test(password)) {
                $("#erroReply").html("密码错误！");
                return false ;
            }
        }
        var vcode = $("#vcode").val();
        if(vcode==null || vcode=="") {
            $("#erroReply").html("请输入验证码！");
            return false ;
        }
    
        //发送ajax请求进行登录认证
        $.ajax({
            url : "${ctx}/login",
            type : "post",
            data : {
                "username" : $("#username").val(),
                "password" : $("#password").val(),
                "vcode"    : $("#vcode").val()
            },
            beforeSend : function() {
                $("#erroReply").html("正在进行身份验证...");
                return true;
            },
            success : function(data) {
                if (data == "nameOrPasswordError") {
                    $("#erroReply").html("用户名或密码错误！");
                    $("#password").val("");
                    return false ;
                } else if (data == "userDelete") {
                    $("#erroReply").html("账号不存在！");
                    return false ;
                } else if (data == "userUnsable") {
                    $("#erroReply").html("账号已禁用！");
                    return false ;
                } else if (data == "checkNumError") {
                    $("#erroReply").html("验证码错误！");
                    changeCode();
                    return false ;
                /* } else if (data == "masterUser") {
                    var username = $("#username").val() ;
                    $("#thePhone").val(username);
                    $("#erroReply").html("");
                    $("#loginDiv1").hide() ;
                    $("#loginDiv2").show() ;
                    $("#save").hide() ;
                    $("#save2").show() ;
                    return false ; */
                } else {
                    $("#erroReply").html("");
                    window.location.href = "${ctx}/manage";
                }
            }
        });
    }
    
    function login2() {
        var phoneCode = $("#phoneCode").val() ;
        if(phoneCode==null || phoneCode=="") {
            $("#erroReply").html("请输入动态码！");
            return false ;
        } else {
            if(phoneCode != _randomValue) {
                $("#erroReply").html("动态码错误！");
                return false ;
            } else {
                
                $.ajax({
                    url : "${ctx}/masteruser/login",
                    type : "post",
                    data : {
                        "username" : $("#username").val(),
                        "password" : $("#password").val()
                    },
                    beforeSend : function() {
                        $("#erroReply").html("正在进行身份验证...");
                        return true;
                    },
                    success : function(data) {
                        if (data == "nameOrPasswordError") {
                            $("#erroReply").html("用户名或密码错误！");
                            $("#password").val("");
                            return false ;
                        } else if (data == "userDelete") {
                            $("#erroReply").html("账号不存在！");
                            return false ;
                        } else if (data == "userUnsable") {
                            $("#erroReply").html("账号已禁用！");
                            return false ;
                        } else {
                            $("#erroReply").html("");
                            window.location.href = "${ctx}/manage";
                        }
                    }
                });
            }
        }
    }
    
    function changeCode() {
        $('#vcodeImage').attr('src', '${ctx}/authCode?' + Math.random());
    }
    
    function showCatpionTime(obj) {
        if (wait == 0) {
            $(obj).attr("disabled", false) ;
            $(obj).val("获取动态码") ;
            $(obj).removeClass("btn-grey").addClass("btn-dtm") ;
            $("#erroReply").html(""); 
            wait = 60 ;
            $("#getPhoneCode").removeClass("dtm1") ;
        } else {
            $(obj).attr("disabled", true) ;
            $(obj).val("重新发送(" + wait + ")") ;
            wait-- ;
            setTimeout(function() {
                showCatpionTime(obj) ;
            }, 1000) ;
        }
    }
</script>
</html>