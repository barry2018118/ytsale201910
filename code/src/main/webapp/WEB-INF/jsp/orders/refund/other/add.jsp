<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>退款详情</span>
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
                                <label class="control-label">${order.orderno}</label>
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
                        <label class="control-label col-md-2">退款单价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${refund.unitPrice}</label>
                                <input type="hidden" id="one" value="${refund.unitPrice}">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款数量：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${refund.num}</label>
                                <input type="hidden" id="num" value="${refund.num}">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款总额：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥<span id="price"></span></label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">申请退款时间：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${fund.createTimetime}
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">申请退款人：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${cname.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>

                    <h4>实际退款信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">审核时间：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${fund.audiTimetime}
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">扣款费用：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">￥${refund.deductPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">返款金额：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">￥${refund.refundPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款状态：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">
                                    <c:if test="${fund.status == 0}" >待审核</c:if>
                                    <c:if test="${fund.status == 1}" >审核通过</c:if>
                                    <c:if test="${fund.status == 2}" >审核不通过</c:if>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
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
                    <button class="btn btn-footer btn-primary" type="button"  onclick="toSet(${refund.id},2)">审核不通过</button>
                </a>
                <button class="btn btn-footer btn-cancel" onclick="toProductCategoryList()">返回</button>
                <a href="javascript:;">
                    <button class="btn btn-footer btn-primary" type="button"  onclick="toSet(${refund.id},1)">审核通过</button>
                </a>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(document).ready(function() {
        $("#price").text( $("#one").val() *  $("#num").val());
    }) ;
    function toProductCategoryList() {
        var requestUrl = "${ctx}/orders/refund/tomain";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }
    function toSet(id,state) {
        art.dialog({
            title : '消息',
            id : "toDelete",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '请您再次确认退款？',
            ok : function() {
                doSetAudi(id,state) ;
            },
            cancel : function() {
            }
        });
    }
    function doSetAudi(id,state) {
        var url = "${ctx}/orders/refund/other/" + id + "/" + state + "/setAudi" ;
        jQuery.ajax({
            type:"post",
            async: true,
            cache: false,
            url: url ,
            dataType:"json",
            success:function(data) {
                if(data.flag) {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'succeed',
                        width : 200,
                        height : 80,
                        content : data.message,
                        close:function() {
                            toProductCategoryList();
                        }
                    }) ;
                } else {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'warning',
                        width : 200,
                        height : 80,
                        content : data.message
                    }) ;
                }
            } ,
            error : function(e){
            }
        });
    }
</script>