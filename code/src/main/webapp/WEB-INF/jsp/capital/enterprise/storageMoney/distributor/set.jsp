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
        <span>预收款</span>&nbsp;&gt;&nbsp;<span class="dq">设置</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal" id="addEnterpriseForm">
    <input type="hidden" id="id" value="${storage.id}" name="id">
    <input type="hidden" id="childId" value="${storage.childId}" name="childId">
    <input type="hidden" id="parentId" value="${storage.parentId}" name="parentId">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left"></div>
                </div>
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">分销商名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">${enterprise.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="channel" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">当前预收款：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">￥${storage.storageMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">预收款：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入预收款"
                                       type="text" id="storageMoney" name="storageMoney" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="storageMoney" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">确认预收款：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入确认预收款"
                                       type="text" id="money" name="money" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="money" class="error"></span>
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
    jQuery.validator.addMethod("MoneyCheck", function(value, element, params) {
        var reg = /^[0-9][0-9]{0,16}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "预收款金额请输入整数！") ;

    var iscommiting = false;
    $(function() {
        $("#addEnterpriseForm").validate({
            rules: {
                money: {
                    required : true,
                    number : true,
                    equalTo: "#storageMoney"
                },
                storageMoney: {
                    required : true,
                    number : true,
                    rangelength: [0, 16],
                    MoneyCheck: true
                }
            },
            messages: {
                money: {
                    required: "确认预收款不能为空！",
                    number:"请输入整数"
                },
                storageMoney: {
                    required: "预收款不能为空！",
                    rangelength: "预收款金额应在{0}到{16}位之间！",
                    number:"请输入整数"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;
                toSetmoney(form);
            }
        });
    }) ;

    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/storageMoney/tomain" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    function toSetmoney(form) {
        art.dialog({
            title : '消息',
            id : "toDelete",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否进行设置？',
            ok : function() {
                doSetmoney(form) ;
            },
            cancel : function() {
            }
        });
    }

    function doSetmoney(form) {
        jQuery(form).ajaxSubmit({
            type : "post",
            url : "${ctx}/capital/enterprise/storageMoney/distributor/add",
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
</script>