<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>订单详情</span>
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
                    <div class="pull-left">订单信息</div>
                </div>
                <div class="widget-content padded">
                    <h4></h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">订单号：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${orders.orderno}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">所属景区：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${scenic.name}</label>
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
                        <label class="control-label col-md-2">商品单价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${orders.unitPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">购买数量：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${orders.num}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">订单总价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${orders.price}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">订单状态：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${orders.statusName}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">下单时间：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">
                                    ${orders.createdAttime}
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">下单来源：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${orders.tuanname}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <h4>取票信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">姓名：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${orders.customerName}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">手机号：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${orders.tel}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">身份证：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${orders.idcard}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <h4>出游日期</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">预计游玩时间：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${order.consumeTimeTime}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <c:if test="${product.isRealname == 1}" >
                        <h4>实名制信息</h4>
                        <c:forEach items="${code}" var="li">
                            <div class="form-group">
                                <label class="control-label col-md-2">姓名：</label>
                                <div class="col-md-5">
                                    <div class="rel">
                                        <label class="control-label">${li.name}  &nbsp; &nbsp; ( 码 : ${li.code} )</label>
                                    </div>
                                </div>
                                <div class="col-md-3 red-tag tag">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">身份证：</label>
                                <div class="col-md-5">
                                    <div class="rel">
                                        <label class="control-label">${li.card}</label>
                                    </div>
                                </div>
                                <div class="col-md-3 red-tag tag">
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${hasfund == 1}" >
                        <h4>退款信息</h4>
                        <c:forEach items="${fund}" var="refund">
                            <div class="form-group">
                                <label class="control-label col-md-2">退款时间：</label>
                                <div class="col-md-5">
                                    <div class="rel">
                                        <label class="control-label">${refund.refundTime}  &nbsp; &nbsp; ( 数量 : ${refund.refundnum} )</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">退款状态：</label>
                                <div class="col-md-5">
                                    <div class="rel">
                                        <label class="control-label">${refund.refundstatus}</label>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${consumeType == 1}" >
                        <h4>消费信息</h4>
                        <c:forEach items="${consume}" var="co">
                            <div class="form-group">
                                <label class="control-label col-md-2">消费码:</label>
                                <div class="col-md-7">
                                    <label class="control-label">${co.code}</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">消费数量:</label>
                                <div class="col-md-7">
                                    <label class="control-label">${co.num} &nbsp; &nbsp; ( 时间 : ${co.time} )</label>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${consumeType == 2}" >
                        <h4>消费信息</h4>
                        <div class="form-group">
                            <label class="control-label col-md-2">消费码:</label>
                            <div class="col-md-7">
                                <label class="control-label">${consumeCode}</label>
                            </div>
                        </div>
                        <c:forEach items="${consume}" var="co">
                            <div class="form-group">
                                <label class="control-label col-md-2">消费数量:</label>
                                <div class="col-md-7">
                                    <label class="control-label">${co.printnum} &nbsp; &nbsp; ( 时间 : ${co.printtimName} )</label>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function toProductCategoryList() {
        var requestUrl = "${ctx}/orders/platform/my/main";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }
</script>