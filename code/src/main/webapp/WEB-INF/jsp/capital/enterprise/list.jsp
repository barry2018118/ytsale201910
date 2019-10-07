<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp" %>

<title>银行卡管理--列表</title>
<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>资金管理</span>&nbsp;&gt;&nbsp;<span class="dq">资金概览</span>
    </h1>
    <!--列表上方索引 E-->
</div>

<form class="form-horizontal" id="addEnterpriseForm">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">平台余额：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${info.totalMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"></font><span hidden="true" for="bank" class="error"></span>
                        </div>
                    </div>
                    <!--
	                    <div class="form-group">
	                        <label class="control-label col-md-2">可提现资金：</label>
	                        <div class="col-md-7">
	                            <div class="rel">
	                                <label class="control-label col-md-2">￥${info.usableMoney} </label>
	                            </div>
	                        </div>
	                        <div class="col-md-3 red-tag tag">
	                            <font id="error"></font><span hidden="true" for="cardNo" class="error"></span>
	                        </div>
	                    </div>
                    -->
                    <div class="form-group">
                        <label class="control-label col-md-2">冻结资金：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <label class="control-label col-md-2">￥${info.forzenMoney}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error"> </font><span hidden="true" for="cardMaster" class="error"></span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
            </div>
        </div>
    </div>
</form>