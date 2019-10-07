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

<form class="form-horizontal" id="addEnterpriseForm">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left"></div>
                </div>

                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">选择银行卡：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="bankCardId" name="bankCardId">
                                    <option value="" selected="selected">请选择</option>
                                    <c:forEach items="${bank}" var="card"  varStatus="s">
                                        <option value="${card.id}">${card.bank} - ${card.cardNo} - ${card.cardMaster}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="bankCardId" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">可申请提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${capital}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">

                        </div>
                    </div>
                   <%-- <div class="form-group">
                        <label class="control-label col-md-2">卡号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2"><span id="bankCardIdText"></span></label>
                                <input  type="hidden" id="bankCard" name="bankCard">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bankCard" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">持卡人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2"><span id="cardMasterText"></span></label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardMaster" class="error"></span>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="control-label col-md-2">申请提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入申请提现金额"
                                       type="text" id="extractMoney" name="extractMoney" maxlength="10">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            * <span hidden="true" for="extractMoney" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">确认提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入申请提现金额"
                                       type="text" id="extractMoneyChack" name="extractMoneyChack" maxlength="10">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            * <span hidden="true" for="extractMoneyChack" class="error"></span>
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label class="control-label col-md-2">费率：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">0.006%</label>
                                <input  type="hidden" id="rate" name="rate" value="0.006">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
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
                        <label class="control-label col-md-2">实际提现金额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥<span id="actualMoneyText">0.00</span></label>
                                <input  type="hidden" id="actualMoney" name="actualMoney" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="actualMoney" class="error"></span>
                        </div>
                    </div>--%>
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
                bankCardId:{
                    required: true,
                },
                extractMoney: {
                    required : true,
                    digits : true
                },
                extractMoneyChack:{
                    required: true,
                    equalTo: "#extractMoney"
                }
            },
            messages: {
                bankCardId:{
                    required: "请选择银行卡！",
                },
                extractMoney: {
                    required: "提现金额不能为空！",
                    digits:"请输入整数"
                },
                extractMoney: {
                    required: "确认提现金额不能为空！",
                    equalTo: "确认提现金额不一致！"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/capital/enterprise/info/extract/add",
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

        /*$("#bankCardId").change(function() {
            var bankCardId = $(this).val() ;
            var _url = "${ctx}/capital/enterprise/bankCard/"+bankCardId+"/search" ;
            $.ajax({
                url: _url ,
                type: "post",
                dataType:"json",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                timeout:120000,
                beforeSend: function() {
                },
                success: function(data) {
                    if(data.flag) {
                        $(data.message).each(function(i, item) {
                            $("#cardMasterText").text(item.cardMaster);
                            $("#bankCardIdText").text(item.cardNo);
                            $("#bankCard").text(item.cardNo);
                        }) ;
                    }
                },
                error:function(e) {

                }
            }) ;
        }) ;

        $("#extractMoney").on('input',function() {
            var rechargeMoney = $(this).val() ;
            var rate = $("#rate").val() ;
            var rateMoney = rechargeMoney * rate;
            var actualMoney = rechargeMoney - rateMoney;
            $("#rateMoney").val(rateMoney);
            $("#actualMoney").val(actualMoney);
            window.document.getElementById("rateMoneyText").innerText = rateMoney;
            window.document.getElementById("actualMoneyText").innerText = actualMoney;
        }) ;*/
    }) ;

    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/info/extract/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>