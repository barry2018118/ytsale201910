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
        <span>企业信息</span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <!--列表上方右侧按钮 E-->
</div>

<form class="form-horizontal" id="editEnterpriseForm">
    <input type="hidden" name="domain" id="domain" value="www" />
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">企业信息</div>
                </div>
                
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">企业名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入企业名称" type="text" 
                                    id="name" name="name" value="${enterprise.name}" maxlength="20">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font id="error">*</font><span hidden="true" for="name" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司负责人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入公司负责人" type="text" 
                                    id="contacterName" name="contacterName" value="${enterprise.contacterName}" maxlength="10">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="contacterName" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">负责人手机：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入负责人手机" type="text" 
                                    id="contacterPhone" name="contacterPhone" value="${enterprise.contacterPhone}" maxlength="11">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="contacterPhone" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司邮箱：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入公司邮箱" type="text"
                                    id="email" name="email" value="${enterprise.email}" maxlength="35">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="email" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" id="province_val" value="${enterprise.province}">
                        <label class="control-label col-md-2">省份：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="province" name="province"></select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="province" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" id="city_val" value="${enterprise.city}">
                        <label class="control-label col-md-2">城市：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="city" name="city">
                                    <option value="${city.id}" selected="selected">${city.name}</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="city" class="error"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入公司地址" type="text"
                                    id="address" name="address" value="${enterprise.address}" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">公司简介：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <textarea maxlength="100" id="introduction" name="introduction"
                                    placeholder="请输入公司简介" class="form-control" rows="5">${enterprise.introduction} 
                                </textarea>
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
                    <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
                </a>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("usernameCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "运营商主账号格式不正确！") ; 
    
    jQuery.validator.addMethod("passwordCheck", function(value, element, params) {
        var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "账号密码格式不正确！") ;
    
    jQuery.validator.addMethod("phoneCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "运营商主账号格式不正确！") ; 

    var iscommiting = false;
    $(function() {
        $("#editEnterpriseForm").validate({
            rules: {
                name: {
                    required: true
                },
                contacterName : {
                    required: true
                },
                contacterPhone: {
                    required: true,
                    phoneCheck: true
                },
                email: {
                    required: true,
                    email: true
                },
                province: {
                    required: true
                },
                city: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "运营商名称不能为空！"
                },
                contacterName: {
                    required: "公司负责人不能为空！"
                },
                contacterPhone: {
                    required: "负责人手机不能为空！"
                },
                email: {
                    required: "公司邮箱不能为空！",
                    email: "请输入正确的公司邮箱！"
                },
                province: {
                    required: "请选择所属省份！"
                },
                city: {
                    required: "请选择所属城市！"
                }
            },
            submitHandler: function(form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type : "post",
                    url : "${ctx}/my/enterprise/info/${enterprise.id}/edit",
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
        
        // 初始化省份数据
        getProvince() ;
        // 省市联动
        $("#province").change(function() {
            var theProvinceId = $(this).val() ;
            var _url = "${ctx}/info/area/province/"+theProvinceId+"/city" ;
            $.ajax({
                url: _url ,
                type: "get",
                dataType:"json",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                timeout:120000,
                beforeSend: function() {
                },
                success: function(data) {
                    $("#city").html("") ;
                    if(data.flag) {
                        $("<option></option>").val("").text("请选择").appendTo("#city") ;
                        $(data.message).each(function(i, item) {
                            $("<option></option>").val(item.id).text(item.name).appendTo("#city") ;
                        }) ;
                    } else {
                        $("<option></option>").val("").text("请选择").appendTo("#city") ;
                    }
                },
                error:function(e) {
                    $("#city").html("") ;
                }
            }) ;
        }) ;
    }) ;

    
    function getProvince() {
        var _url = "${ctx}/info/area/province" ;
        $.ajax({
	        url: _url ,
	        type: "get",
	        dataType:"json",
	        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	        timeout:120000,
	        beforeSend: function() {
	        },
            success: function(data) {
                $("#province").html("") ;
                if(data.flag) {
                    $("<option></option>").val("").text("请选择").appendTo("#province") ;
                    $(data.message).each(function(i, item) {
                        $("<option></option>").val(item.id).text(item.name).appendTo("#province") ;
                    }) ;
                    
                    // 设置省份数据回显
                    $("#province").val($("#province_val").val()) ;
                } else {
                    $("<option></option>").val("").text("请选择").appendTo("#province") ;
                }
            },
            error:function(e) {
                $("#province").html("") ;
            }
        }) ;
    }
    
</script>