<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<style>
    #addEnterpriseForm input.checkbox {
        vertical-align: top;
        margin-right: 5px;
    }
</style>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>个人资料</span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal" id="editUsername">
    <input type="hidden" id="id" name="id" value="${user.id}" >
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">个人账号</div>
                </div>
                
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">账号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder=""
                                    type="text" id="username" name="username"  value="${user.username}" maxlength="11" disabled="disabled">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="username" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">姓名：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="姓名"
                                       type="text" id="name" name="name" value="${user.name}" maxlength="10">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="name" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">手机号码：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入手机号码"
                                       type="text" id="phone" name="phone" value="${user.phone}" maxlength="11">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="phone" class="error"></span>
                        </div>
                    </div>
                </div>
            </div>
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
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("userphoneCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "手机号格式不正确！") ;

    var iscommiting = false;
    $(function() {
        $("#editUsername").validate({
            rules: {
                name: {
                    required: true
                },
                phone: {
                    required: true,
                    userphoneCheck: true
                }
            },
            messages: {
                name: {
                    required: "姓名不能为空！"
                },
                phone: {
                    required: "手机号不能为空！"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/my/user/${user.username}/editUsername",
                    beforeSubmit : function(formData, jqForm, options) {
                    },
                    dataType : "json",
                    contentType : "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout : 120000,
                    success : function(data) {
                        if (data.flag) {
                            art.dialog({
                                title : '消息',
                                width : 220,
                                height : 80,
                                time : 3,
                                icon : 'succeed',
                                content : data.message,
                                close : function() {
                                    toOperationList() ;
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

    function toOperationList() {
        var requestUrl = "${ctx}/my/user/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>