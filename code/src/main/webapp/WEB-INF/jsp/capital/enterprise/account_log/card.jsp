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
        <span>平台资金变动</span>&nbsp;&gt;&nbsp;<span class="dq">查看</span>
    </h1>
    <!--列表上方索引 E-->

    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList();">
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
                        <label class="control-label col-md-2">变动时间：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5" ><fmt:formatDate value="${card.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">业务：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">
                                    <c:if test="${card.actionType == 1}" >充值</c:if>
                                    <c:if test="${card.actionType == 2}" >提现</c:if>
                                    <c:if test="${card.actionType == 3}" >下单</c:if>
                                    <c:if test="${card.actionType == 4}" >退款</c:if>
                                    <c:if test="${card.actionType == 5}" >消费</c:if>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">变动资金：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                 <label class="control-label col-md-5">
                                     <c:if test="${card.capitalType == 0}" >预收款</c:if>
                                     <c:if test="${card.capitalType == 1}" >平台资金</c:if>
                                 </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardMaster" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">变动前金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5" id="" name="">
                                    ￥<c:if test="${card.oldPrice != null}">${card.oldPrice}</c:if>
                                    <c:if test="${card.oldPrice == null}">0.00</c:if>
                                    </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="cardMasterNo" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">本次变动金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">
                                    ￥<c:if test="${card.price != null}">${card.price}</c:if>
                                    <c:if test="${card.price == null}">0.00</c:if>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                             <span hidden="true" for="cardMasterPhone" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">变动后金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">
                                    ￥<c:if test="${card.currentPrice != null}">${card.currentPrice}</c:if>
                                    <c:if test="${card.currentPrice == null}">0.00</c:if>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="cardMasterPhone" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">业务描述：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-5">${card.actionDesc}</label>
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
                <button class="btn btn-footer btn-cancel" onclick="toOperationList();">返回</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/accountLog/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>