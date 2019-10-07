<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>消费列表</span>&nbsp;&gt;&nbsp;<span class="dq">消费信息</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toProductCategoryList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>

<form class="form-horizontal">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">商品</div>
                </div>
                <div class="widget-content padded">
                    <h4>基础信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">订单编号：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${orders.orderno}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">码信息：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${entity.cardPwd}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">商品名称：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${product.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">景区名称：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${scenic.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">核销单价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${entity.unitPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">核销数：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${entity.printnum}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">核销总价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${entity.price}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label class="control-label col-md-2">核销状态：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${provinces.status}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="control-label col-md-2">核销源：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${entity.tuanname}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">核销时间：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${entity.printtimName}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">流水号：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${entity.serialNum}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function toProductCategoryList() {
        var requestUrl = "${ctx}/orders/platform/consume/main";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }
</script>