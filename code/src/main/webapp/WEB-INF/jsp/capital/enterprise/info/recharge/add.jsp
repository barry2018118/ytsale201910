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
        <span>充值</span>&nbsp;&gt;&nbsp;<span class="dq"></span>
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
                    <div class="pull-left"></div>
                </div>

                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">充值方式：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="radio-inline">
                                    <input type="radio"  id="channel1" name="channel" value="支付宝"  checked/><span>支付宝</span>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio"  id="channel2" name="channel" value="微信" /><span>微信</span>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="channel" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">费率：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">6‰</label>
                                <input  type="hidden" id="rate" name="rate" value="0.006">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">充值金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入充值金额"
                                       type="text" id="rechargeMoney" name="rechargeMoney" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="rechargeMoney" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">费率扣除金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2" id="" name="">￥<span id="rateMoneyText">0.00</span></label>
                                <input  type="hidden" id="rateMoney" name="rateMoney" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="rateMoney" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">实际充值金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥<span id="actualMoneyText">0.00</span></label>
                                <input  type="hidden" id="actualMoney" name="actualMoney" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="actualMoney" class="error"></span>
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
    var iscommiting = false;
    $(function() {
        $("#addEnterpriseForm").validate({
            rules: {
                rechargeMoney: {
                    required : true,
                    digits : true
                }
            },
            messages: {
                rechargeMoney: {
                    required: "充值金额不能为空！",
                    digits:"请输入整数"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/capital/enterprise/info/recharge/add",
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

        $("#rechargeMoney").on('input',function() {
            var rechargeMoney = $(this).val() ;
            var rate = $("#rate").val() ;
            var rateMoney = rechargeMoney * rate;
            var actualMoney = rechargeMoney - rateMoney;
            $("#rateMoney").val(rateMoney);
            $("#actualMoney").val(actualMoney);
            window.document.getElementById("rateMoneyText").innerText = rateMoney;
            window.document.getElementById("actualMoneyText").innerText = actualMoney;
        }) ;
    }) ;

    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/info/recharge/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>