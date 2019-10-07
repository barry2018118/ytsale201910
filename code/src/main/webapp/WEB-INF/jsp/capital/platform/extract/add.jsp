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
        <span>申请提现</span>&nbsp;&gt;&nbsp;<span class="dq"></span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal" id="addEnterpriseForm" method="post" action="" enctype="multipart/form-data">
    <input type="hidden" id="id" name="id" value="${card.id}">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left"></div>
                </div>

                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">提现企业：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2" id="infoEnterprise">${infoEnterprise.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">提现卡号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2" id="extractCardNo">${bank.cardNo}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">申请提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${card.extractMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
                        </div>
                    </div>
                 <%--   <div class="form-group">
                        <label class="control-label col-md-2">费率：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">${card.rate}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardMaster" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">费率扣除金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2" id="" name="">￥${card.rateMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="cardMasterNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">实际提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${card.actualMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="cardMasterPhone" class="error"></span>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="control-label col-md-2">审核状态：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">
                                    <c:if test="${card.status == 0}" >待审核</c:if>
                                    <c:if test="${card.status == 1}" >已审核</c:if>
                                    <c:if test="${card.status == 2}" >未通过</c:if>
                                <input  type="hidden" id="status" name="status" value="0">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="status" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">打款凭证：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input type="file" id="file" class="form-control" placeholder="请上传打款凭证" name="file" style="width: 160px; display: inline; margin-right: 10px; padding: 1px; padding-left: 9px;" />
                                <span style="color: red;">*（文件类型：xls, xlsx，文件大小：2M）</span>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="file" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">拒绝原因：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入拒绝原因"
                                       type="text" id="reason" name="reason"  >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="reason" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">申请时间：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2"><fmt:formatDate value="${card.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="cardMasterPhone" class="error"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <a href="javascript:;">
                    <button class="btn btn-footer btn-primary" type="submit" id="hasnext" onclick="tostate(2);">不通过</button>
                </a>
                <button class="btn btn-footer btn-cancel" onclick="toOperationList()">取消</button>
                <a href="javascript:;">
                    <button class="btn btn-footer btn-primary" type="submit" id="next"  onclick="tostate(1);">通过审核</button>
                </a>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("filecheck", function(value, element, params) {
        var reg= /.(jpg|JPG|jpeg|JPEG|png|PNG)$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "图片文件格式不正确！") ;
    var iscommiting = false;
    $(function() {
        $("#addEnterpriseForm").validate({
            rules: {
                file:{
                    required: true,
                    filecheck: true,
                },
                reason: {
                    required : true,
                }
            },
            messages: {
                file:{
                    required: "请上传打款凭证！",
                },
                reason: {
                    required: "请输入拒绝原因！",
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/capital/platform/money/extract/add",
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

    function tostate(num) {
        if(num == 1){
            $("#file").rules("remove","required");
            $("#reason").rules("remove","required");
            $("#file").rules("add", {
                required: true,
                messages: {
                    required: "请上传打款凭证"
                }
            });
        }else if(num == 2){
            $("#file").rules("remove","required");
            $("#reason").rules("remove","required");
            $("#reason").rules("add", {
                required: true,
                messages: {
                    required: "请输入拒绝原因"
                }
            });
        }
        $("#status").val(num);
    }

    function toOperationList() {
        var requestUrl = "${ctx}/capital/platform/money/extract/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>