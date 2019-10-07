<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>
<script type="text/javascript" src="${jsPath}/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${jsPath}/artDialog/plugins/iframeTools.source.js?skin=blue"></script>

<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>个人信息管理</span>&nbsp;&gt;&nbsp;<span>更改密码</span>
    </h1>
    <!--列表上方索引 E-->
</div>

<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="">
            <div class="widget-content padded">
                <form id="editpassword" action="${ctx}/manage/user/editpassword" class="form-horizontal">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="form-group">
                        <label class="control-label col-md-2">新密码：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入新密码：（必须包含字母和数字）" 
                                    type="text" id="password" name="password" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="password" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">确认密码：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入确认密码" 
                                    type="text" id="password2" name="password2" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="password2" class="error"></span>
                        </div>
                    </div>
                    
					<div class="form-group btn-form">
                        <div class="col-md-12 text-center">
                            <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" >保存</button></a>
                        </div>
                    </div>
                 </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    jQuery.validator.addMethod("passwordCheck", function(value, element, params) {
        var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "账号密码格式不正确！") ;

    iscommiting=false;

    $(document).ready(function() {
        $("#editpassword").validate({
            rules:{
                password: {
                    required: true,
                    rangelength: [6, 16], 
                    passwordCheck: true
                },
                password2: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages:{
                password: {
                    required: "账号密码不能为空！",
                    rangelength: "账号密码长度应在{0}到{1}位之间！"
                },
                password2: {
                    required: "确认密码不能为空！",
                    equalTo: "确认密码与账号密码不一致！"
                }
            },
            submitHandler: function(form){
            if(iscommiting){
                return false;
            }
            iscommiting = true;
    
            jQuery(form).ajaxSubmit({
                type: "post",
				url:"${ctx}/manage/user/editpassword",
				dataType:"text",
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",
				timeout:120000,
                success: function(data) {
                    if (data.flag) {
                        art.dialog({
                            title : '消息',
                            width : 220,
                            height : 80,
                            time : 3,
                            icon : 'succeed',
                            content : data.message,
                            close : function() {
                            }
                        });
                    } else {
				        art.dialog({
	                        title : '消息',
	                        width : 200,
	                        height : 80,
	                        time : 3,
	                        icon : 'warning',
	                        content : data.message,
	                        close : function() {
	                        }
                        });
                        iscommiting = false;
                        }
                    },
                    error : function(e) {
                        iscommiting = false;
                    }
                });
            }
        });
     }) ;

</script>