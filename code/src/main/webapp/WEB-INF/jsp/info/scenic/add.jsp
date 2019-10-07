<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>景区名称</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toInfoScenicList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>


<form id="infoScenicAddForm" method="post" class="form-horizontal">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">景区信息</div>
                </div>
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">景区名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入景区名称" type="text" id="name" name="name" maxlength="30" />
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="name" class="error"></span>
                        </div>

                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2">景区星级：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                    <select class="form-control" id="level" name="level">
                                        <option value="" selected="selected">请选择</option>
                                        <option value="5">5星级</option>
                                        <option value="4">4星级</option>
                                        <option value="3">3星级</option>
                                        <option value="0">未评星级</option>
                                    </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="level" class="error"></span>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">省份：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="provinceId" name="provinceId"></select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font  id="error">*</font>
                            <span hidden="true" for="provinceId" class="error"></span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-2">城市：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="cityId" name="cityId">
                                    <option value="0" selected="selected">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            <font  id="error">*</font>
                            <span hidden="true" for="cityId" class="error"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">景区地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入景区地址" type="text"
                                       id="address" name="address" maxlength="30">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">景区电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入景区电话"
                                       type="text" id="tel" name="tel" maxlength="16">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <button class="btn btn-footer btn-cancel" onclick="toInfoScenicList()">取消</button>
                <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    jQuery.validator.addMethod("phoneCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "电话格式不正确！") ;
    var iscommiting = false;
    $(function() {

        getProvince();

        $("#infoScenicAddForm").validate({
            rules: {
                name: {
                    required: true
                },
                level: {
                    required: true
                },
                provinceId: {
                    required: true
                },
                cityId: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "景区名称不能为空！"
                },
                level: {
                    required: "景区星级不能为空！"
                },
                provinceId: {
                    required: "请选择所属省份！"
                },
                cityId: {
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
                    url : "${ctx}/info/scenic/add",
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
                                content : data.message ,
                                close : function() {
                                    toInfoScenicList() ;
                                }
                            });
                        } else {
                            art.dialog({
                                title : '消息',
                                width : 200,
                                height : 80,
                                time : 3,
                                icon : 'warning',
                                content : '新建运营商失败！',
                                close : function() {
                                    toInfoScenicList() ;
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


            $("#provinceId").change(function() {
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
                        $("#cityId").html("") ;
                        if(data.flag) {
                            // $("<option></option>").val("").text("请选择").appendTo("#province") ;
                            $(data.message).each(function(i, item) {
                                $("<option></option>").val(item.id).text(item.name).appendTo("#cityId") ;
                            }) ;
                        } else {
                            $("<option></option>").val("").text("请选择").appendTo("#cityId") ;
                        }
                    },
                    error:function(e) {
                        $("#cityId").html("") ;
                    }
                }) ;
        }) ;
    }) ;

    function toInfoScenicList() {
        var requestUrl = "${ctx}/info/scenic/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

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
                    $("<option></option>").val("").text("请选择").appendTo("#provinceId") ;
                    $(data.message).each(function(i, item) {
                        $("<option></option>").val(item.id).text(item.name).appendTo("#provinceId") ;
                    }) ;
                } else {
                    $("<option></option>").val("").text("请选择").appendTo("#provinceId") ;
                }
            },
            error:function(e) {
                $("#provinceId").html("") ;
            }
        }) ;
    }
</script>