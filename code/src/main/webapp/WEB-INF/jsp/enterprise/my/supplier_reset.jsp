<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>


<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>主账号重置</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form  class="form-horizontal" id="resetEnterpriseForm">
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">主账号重置</div>
            </div>
        
            <div class="widget-content padded">
                <div class="form-group">
                    <label class="control-label col-md-2">供应商：</label>
                    <div class="col-md-7">
                        <div class="rel">
                            <input class="form-control" type="text" value="${enterprise.name}" readonly="readonly">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">主账号：</label>
                    <div class="col-md-7">
                        <div class="rel">
                            <input class="form-control" placeholder="请输入主账号（必须为手机号）" 
                                type="text" id="username" name="username" maxlength="11">
                        </div>
                    </div>
                    <div class="col-md-3 red-tag tag">
                        <font id="error">*</font><span hidden="true" for="username" class="error"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">确认主账号：</label>
                    <div class="col-md-7">
                        <div class="rel">
                            <input class="form-control" placeholder="请输入确认主账号（必须为手机号）" 
                                type="text" id="username2" name="username2" maxlength="11">
                        </div>
                    </div>
                    <div class="col-md-3 red-tag tag">
                        <font id="error">*</font><span hidden="true" for="username2" class="error"></span>
                    </div>
                </div>
            
                <div class="row" style="min-height:60px;">
                    <div class="form-group btn-form">
                        <div class="col-md-11 text-center">
                            <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" id="next">保存</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("usernameCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "主账号格式不正确！") ; 

    iscommiting=false;

    $(document).ready(function() {
        $("#resetEnterpriseForm").validate({
            rules:{
                username: {
                    required: true,
                    usernameCheck: true
                },
                username2: {
                    required: true,
                    equalTo: "#username"
                }
            },
            messages:{
                username: {
                    required: "主账号不能为空！"
                },
                username2: {
                    required: "确认主账号不能为空！",
                    equalTo: "确认主账号与主账号不一致！"
                }
            },
            submitHandler: function(form){
	            if(iscommiting){
	                return false;
	            }
	            iscommiting = true;
    
	            jQuery(form).ajaxSubmit({
	                type: "post",
	                url: "${ctx}/my/enterprise/supplier/${enterprise.id}/doResetAccount",
	                dataType: "json",
	                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	                timeout: 120000,
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
                                    toOperationList();
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
                                    toOperationList();
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
        var requestUrl = "${ctx}/my/enterprise/supplier" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

</script>