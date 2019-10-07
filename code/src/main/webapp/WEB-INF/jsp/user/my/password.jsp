<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>
<link rel="stylesheet" href="${cssPath}/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath}/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath}/locales/bootstrap-datepicker.zh-CN.js"></script>

<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>员工密码</span>&nbsp;&gt;&nbsp;<span class="dq">修改密码</span>
    </h1>
    <!--列表上方索引 E-->
</div>

<form  class="form-horizontal" id="editpassword">
<input type="hidden" name="id" value="${user.id}">
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">修改密码</div>
            </div>
        
            <div class="widget-content padded">
                <div class="form-group">
                    <label class="control-label col-md-2">新密码：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入新密码：（必须包含字母和数字）" 
                                    type="password" id="password" name="password" maxlength="16">
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
                                    type="password" id="password2" name="password2" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="password2" class="error"></span>
                        </div>
                    </div>

                    <div class="row" style="min-height:60px;">
                        <div class="form-group btn-form">
                            <div class="col-md-11 text-center">
                                <button class="btn btn-footer btn-cancel" onclick="toOperationList()">取消</button>
                                <a href="javascript:;">
                                    <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
                                </a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</form>

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
                    required: "新密码不能为空！",
                    rangelength: "新密码长度应在{0}到{1}位之间！"
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
	                url:"${ctx}/my/user/${user.id}/password",
	                dataType:"json",
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
                                    var requestUrl = "${ctx}/my/user/main" ;
                                    var requestData = {} ;
                                    cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	                            }
	                        });
	                        iscommiting = false;
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
    function toOperationList() {
        var requestUrl = "${ctx}/my/user/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

</script>