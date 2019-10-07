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
                                <label class="control-label col-md-2">${card.channel}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">费率：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">6‰</label>
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
                                 <label class="control-label col-md-2">￥${card.rechargeMoney}</label>
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
                        <label class="control-label col-md-2">实际充值金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${card.actualMoney}</label>
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
</form>

<script type="text/javascript">
    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/info/recharge/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>