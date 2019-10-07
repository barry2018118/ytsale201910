<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<style>
    #editEnterpriseForm input.checkbox {
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
        <span>平台运营商</span>&nbsp;&gt;&nbsp;<span>${enterprise.name}</span>&nbsp;&gt;&nbsp;<span class="dq">查看</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal">
    <input type="hidden" name="domain" id="domain" value="www" />
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">平台运营商</div>
                </div>
                
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">运营商名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="name"  name="name" value="${enterprise.name}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                <!-- 
                    <div class="form-group">
                        <label class="control-label col-md-2">运营商主账号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="username" name="username" value="${user.username}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                -->
                    <div class="form-group">
                        <label class="control-label col-md-2">公司负责人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="contacterName" name="contacterName" value="${enterprise.contacterName}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">负责人手机：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="contacterPhone" name="contacterPhone" value="${enterprise.contacterPhone}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司邮箱：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="email" name="email" value="${enterprise.email}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">省份：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="email" name="email" value="${province.name}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">城市：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="email" name="email" value="${city.name}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" type="text" id="address" name="address" value="${enterprise.address}" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司简介：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <textarea maxlength="100" id="introduction" name="introduction" 
                                class="form-control" rows="5" readonly="readonly">${enterprise.address}</textarea>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <span hidden="true" for="introduction" class="error"></span>
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
                    <button class="btn btn-footer btn-primary" onclick="toOperationList()">返回</button>
                </a>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function() {
    }) ;

    function toOperationList() {
        var requestUrl = "${ctx}/platform/enterprise/shop" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
</script>