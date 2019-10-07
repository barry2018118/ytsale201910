<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<style>
	#productAddForm input.checkbox {
		vertical-align: top;
		margin-right: 5px;
	}
</style>
<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<form id="productAddForm" method="post" action="" class="form-horizontal" enctype="multipart/form-data">
	<h4>申请退款信息</h4>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">订单号：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="id" name="id" value="${orders.ordersId}">
				<label class="control-label">${orders.orderno}</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">所属景区：</label>
		<div class="col-md-3">
			<div class="input-group">
				<label class="control-label">${scenic.name}</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">商品名称：</label>
		<div class="col-md-5">
			<div class="rel">
				<label class="control-label">${product.name}</label>
			</div>
		</div>
	</div>
	<div class="form-group">
        <label class="control-label col-md-1"></label>
        <label class="control-label col-md-2">退款方式：</label>
        <div class="col-md-3">
            <div class="rel">
                <c:if test="${1 == refundMethod}"><label class="control-label">整订单退款</label></c:if>
                <c:if test="${2 == refundMethod}"><label class="control-label">可部分退款</label></c:if>
            </div>
        </div>
    </div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">可退数量：</label>
		<div class="col-md-3">
			<div class="rel">
				<label class="control-label">${num}</label>
				<input type="hidden" id="productnum" name="productnum" value="${num}">
				<input type="hidden" id="refundMethod" name="refundMethod" value="${refundMethod}">
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">退款单价：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="money" name="money" value="${orders.fxPrice}">
				<label class="control-label">${orders.fxPrice}</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			<span hidden="true" for="money" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">退款数量：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="number" id="num" name="num" placeholder="请输入数量">
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			<span hidden="true" for="num" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">商品总额：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="price" name="price" value="0">
				<label class="control-label" id="twoprice">0</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			<span hidden="true" for="price" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">手续费：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="fixmoney" name="fixmoney" value="0">
				<label class="control-label" id="fixmoneylable">0</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">退款总额：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="countmoney" name="countmoney" value="0">
				<label class="control-label" id="countmoneylable">0</label>
			</div>
		</div>
	</div>
	<c:if test="${product.isRealname == 1}" >
		<div class="form-group">
			<label class="control-label col-md-1"></label>
			<label class="control-label col-md-2">选择退款票：</label>
			<div class="control-label col-md-9">
				<div class="rel">
					<c:forEach items="${cardlist}" var="li">
						<label class="control-label col-md-10 checkbox-inline" style="text-align : left;left:15px;"><input name="box" type="checkbox" value="${li.id}"><span>姓名： ${li.name}  &nbsp; &nbsp; ( 码 : ${li.code} ) &nbsp; &nbsp; 身份证：${li.card}</span></label>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:if>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">退款原因:</label>
		<div class="col-md-8">
			<textarea maxlength="100" id="notes" name="notes" placeholder="请输入退款原因" class="form-control" rows="5"></textarea>
		</div>
	</div>
	<div class="row" style="min-height:60px;">
		<div class="form-group btn-form">
			<div class="col-md-11 text-center">
				<button aria-hidden="true" class="btn btn-footer btn-cancel" data-dismiss="modal" type="button" id="quxiao">取消</button>
				<button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
    var iscommiting = false;
    $(function() {
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true, //选中之后自动隐藏日期选择框
            clearBtn: true, //清除按钮
            todayBtn: true, //今日按钮
            format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        $("#productAddForm").validate({
            rules: {
                num: {
                    required: true,
                    digits:true,
                }
            },
            messages: {
                num: {
                    required: "输入退款商品数量！",
                    digits:"请输入整数!",
                }
            },
            submitHandler: function(form) {
                var inutnum = $("#num").val();
                if(${product.refundMode} == 2){
                    art.dialog({
                        title: '消息',
                        width: 200,
                        height: 80,
                        time: 5,
                        icon: 'warning',
                        content: "该商品不能退!",
                        close: function() {}
                    });
                    if(!iscommiting) {
                        return true;
                    }
                    return false;
                }
                
                var refundMethod = $("#refundMethod").val();
                var buyNum = ${num};
                if(refundMethod == 1) {
                    if(buyNum != inutnum) {
	                    art.dialog({
	                        title: '消息',
	                        width: 200,
	                        height: 80,
	                        time: 5,
	                        icon: 'warning',
	                        content: "退款数量必须等于可退数量!",
	                        close: function() {}
	                    });
	                    if(!iscommiting) {
	                        return true;
	                    }
                    return false;
                    }
                } else {
                    if(inutnum > ${num}) {
	                    art.dialog({
	                        title: '消息',
	                        width: 200,
	                        height: 80,
	                        time: 5,
	                        icon: 'warning',
	                        content: "退款数量大于可退数量!",
	                        close: function() {}
	                    });
	                    if(!iscommiting) {
	                        return true;
	                    }
	                    return false;
	                }
	                if( $("#num").val()  <= 0){
	                    art.dialog({
	                        title: '消息',
	                        width: 200,
	                        height: 80,
	                        time: 5,
	                        icon: 'warning',
	                        content: "退款数量大于0!",
	                        close: function() {}
	                    });
	                    if(!iscommiting) {
	                        return true;
	                    }
	                    return false;
	                }
                }
                var fixmoneyey = $("#fixmoney").val();
                var countmoneyey = $("#countmoney").val();
                if( fixmoneyey  >  countmoneyey){
                    art.dialog({
                        title: '消息',
                        width: 200,
                        height: 80,
                        time: 5,
                        icon: 'warning',
                        content: "手续费用大于退款总额!",
                        close: function() {}
                    });
                    if(!iscommiting) {
                        return true;
                    }
                    return false;
                }
                <c:if test="${product.isRealname == 1}" >
                var chacknum = $("input[type='checkbox']:checked").length;
                if( chacknum  !=  inutnum){
                    art.dialog({
                        title: '消息',
                        width: 200,
                        height: 80,
                        time: 5,
                        icon: 'warning',
                        content: "退款数与选择的退款票数不相符!",
                        close: function() {}
                    });
                    if(!iscommiting) {
                        return true;
                    }
                    return false;
                }
				</c:if>
                if(iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type: "post",
                    url: "${ctx}/orders/my/add",
                    beforeSubmit: function(formData, jqForm, options) {},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout: 120000,
                    success: function(data) {
                        if(data.flag) {
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'succeed',
                                content: data.message,
                                close: function() {
                                    $("#quxiao").click();
                                    toOperationList();
                                }
                            });
                        } else {
                            iscommiting = false;
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'warning',
                                content: data.message,
                                close: function() {}
                            });
                            iscommiting = false;
                        }
                    },
                    error: function(e) {
                        iscommiting = false;
                    }
                });
            }
        });
        $("#num").on('input',function() {
            var num = $(this).val() ;
            var money = $("#money").val() ;
            var rateMoney = num * money;
            $("#price").val(rateMoney);
            $("#twoprice").text(rateMoney);

            $("#countmoney").val(rateMoney);
            $("#countmoneylable").text(rateMoney);
            <c:if test="${product.serviceProductCost != null}">
            if (${product.serviceMode} == 2){//按票扣费
                var mo = num * ${product.serviceProductCost} ;
                $("#fixmoney").val(mo);
                $("#fixmoneylable").text(mo);

                $("#countmoney").val(rateMoney - mo);
                $("#countmoneylable").text(rateMoney - mo);
            }
            </c:if>
            <c:if test="${product.serviceOrderCost != null}">
            if (${product.serviceMode} == 3){//按单扣费
                var mo = ${product.serviceOrderCost} ;
                $("#fixmoney").val(mo);
                $("#fixmoneylable").text(mo);

                $("#countmoney").val(rateMoney - mo);
                $("#countmoneylable").text(rateMoney - mo);
            }
            </c:if>
        }) ;
    });
    function toOperationList() {
        var requestUrl = "${ctx}/orders/orders/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>