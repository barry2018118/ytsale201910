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
        <span>网站信息</span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
</div>

<form id="editWebsiteInfo" method="post" action="" class="form-horizontal" enctype="multipart/form-data">
    <input type="hidden" name="domain" id="domain" value="www" />
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">网站信息</div>
                </div>
                
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">Logo：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请上传Logo" type="file" id="logo" name="logo" value="">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="logo" class="error"></span>
                        </div>
                    </div>
                    <c:if test="${!empty enterprise.logo}">
                        <div class="form-group">
                            <label class="control-label col-md-2"></label>
                            <!-- <img src="${ctx}${enterprise.logo}" style="max-width: 120px;max-height: 120px"> -->
                            <img src="${showResourcePath}${enterprise.logo}" style="max-width: 120px;max-height: 120px">
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-md-2">Banner：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请上传Banner" type="file" id="banner" name="banner" value="">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="banner" class="error"></span>
                        </div>
                    </div>
                    <c:if test="${!empty enterprise.banner}">
                        <div class="form-group">
                            <label class="control-label col-md-2"></label>
                            <!-- <img src="${ctx}${enterprise.banner}" style="max-width: 120px;max-height: 120px"> -->
                            <img src="${showResourcePath}${enterprise.banner}" style="max-width: 120px;max-height: 120px">
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-md-2">平台名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入平台名称" type="text" 
                                    id="platformName" name="platformName" value="${enterprise.platformName}" maxlength="15">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="platformName" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">客服电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入客服电话" type="text" 
                                    id="customerPhone" name="customerPhone" value="${enterprise.customerPhone}" maxlength="15">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="customerPhone" class="error"></span>
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
                    <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
                </a>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("logoCheck", function(value, element, params) {
        var reg= /.(jpg|JPG|jpeg|JPEG|png|PNG)$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "Logo文件格式不正确！") ; 
    
    jQuery.validator.addMethod("bannerCheck", function(value, element, params) {
        var reg= /.(jpg|JPG|jpeg|JPEG|png|PNG)$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "Banner文件格式不正确！") ; 
    
    var iscommiting = false;
    $(function() {
        $("#editWebsiteInfo").validate({
            rules: {
                logo: {
                    logoCheck: true
                },
                banner: {
                    bannerCheck: true
                },
                platformName: {
                    required: true, 
                },
                customerPhone: {
                    required: true
                }
            },
            messages: {
                logo: {
                    required: "请上传Logo！"
                },
                banner: {
                    required: "请上传Banner！"
                },
                platformName: {
                    required: "请输入平台名称！"
                },
                customerPhone: {
                    required: "请输入客服电话！"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/platform/enterprise/editWebsiteInfo",
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
                                    toShowWebsiteInfo() ;
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
    }) ;

    function toShowWebsiteInfo() {
        var requestUrl = "${ctx}/platform/enterprise/toEditWebsiteInfo" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
</script>