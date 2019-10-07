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
        <span>银行卡管理</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal" id="addEnterpriseForm">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">银行卡</div>
                </div>
                
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">开户银行：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入开户银行"
                                    type="text" id="bank" name="bank" maxlength="30" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">开户地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入开户地址"
                                    type="text" id="address" name="address" maxlength="30" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="address" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">银行卡号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入银行卡号"
                                       type="text" id="cardNo" name="cardNo" maxlength="40">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="cardNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">持卡人（或公司名）：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入持卡人（或公司名）"
                                       type="text" id="cardMaster" name="cardMaster" maxlength="30">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="cardMaster" class="error"></span>
                        </div>
                    </div>
                <!--
                    <div class="form-group">
                        <label class="control-label col-md-2">持卡人身份证号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入持卡人身份证号"
                                       type="text" id="cardMasterNo" name="cardMasterNo" maxlength="18">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="cardMasterNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">持卡人手机号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入持卡人手机号"
                                       type="text" id="cardMasterPhone" name="cardMasterPhone" maxlength="11">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="cardMasterPhone" class="error"></span>
                        </div>
                    </div>
                -->
                    <div class="form-group">
                        <label class="control-label col-md-2">备注信息：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <textarea maxlength="100" id="remark" name="remark"
                                    placeholder="" class="form-control" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="remark" class="error"></span>
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
    jQuery.validator.addMethod("userCardCheck", function(value, element, params) {
        var reg= /^[0-9][0-9]{17}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "持卡人身份证号格式不正确！") ;
    
    jQuery.validator.addMethod("userphoneCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "持卡人手机号格式不正确！") ;

    var iscommiting = false;
    $(function() {
        $("#addEnterpriseForm").validate({
            rules: {
                bank: {
                    required: true
                },
                address: {
                    required: true
                },
                cardNo: {
                    required: true
                },
                cardMaster: {
                    required: true,
                }
            },
            messages: {
                bank: {
                    required: "开户银行不能为空！"
                },
                address: {
                    required: "开户地址不能为空！"
                },
                cardNo: {
                    required: "银行卡号不能为空！"
                },
                cardMaster: {
                    required: "持卡人（或公司名）不能为空！"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/capital/enterprise/bankCard/add",
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
        var requestUrl = "${ctx}/capital/enterprise/bankCard/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>