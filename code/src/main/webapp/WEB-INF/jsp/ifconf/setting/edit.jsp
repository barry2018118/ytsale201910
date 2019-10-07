<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>
<script type="text/javascript" src="${jsPath}/addel.jquery.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span><c:if test="${typeId==1}">渠道</c:if><c:if test="${typeId==2}">供应商</c:if>接口配置</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toIfconfSettingList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>


<form id="ifconfSettingAddForm" method="post" class="form-horizontal" >
    <input type="hidden" name="typeId" value="${typeId}" />
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">接口配置</div>
                </div>
                <div class="widget-content padded">
                    <div class="form-group">
                            <label class="control-label col-md-3"><h5>系统配置参数</h5></label>
                    </div>
                    <div class="form-group">
                            <label class="control-label col-md-2">配置别名：</label>
                            <div class="col-md-3">
                                <div class="rel">
                                    <input class="form-control" placeholder="配置别名" type="text" id="name" name="name" maxlength="32" value="${enterprisePlatformInterfacePo.name}" />
                                </div>
                            </div>
                            <div class="col-md-3 red-tag tag">
                                *<span hidden="true" for="name" class="error"></span>
                            </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="interfaceId" value="${enterprisePlatformInterfacePo.interfaceId}" />
                        <label class="control-label col-md-2">接口：</label>
                        <div class="col-md-3">
                                <label class="control-label col-md-2">${enterprisePlatformInterfacePo.interfaceName}</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">是否由系统发码（短信）：</label>
                        <div class="col-md-2">
                            <div class="rel">
                                    <select class="form-control" id="sendmes" name="sendmes">
                                        <option value="1" <c:if test="${enterprisePlatformInterfacePo.sendmes==1}">selected="selected"</c:if>>否</option>
                                        <option value="2" <c:if test="${enterprisePlatformInterfacePo.sendmes==1}">selected="selected"</c:if>>是</option>
                                    </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="sendmes" class="error"></span>
                        </div>
                    </div>
                    
                    <c:if test="${typeId==1}">
                        <div class="form-group">
                            <label class="control-label col-md-2">账号或标识：</label>
                            <div class="col-md-3">
                                <div class="rel">
                                    <input class="form-control" placeholder="账号或标识" type="text" id="acountNo" name="acountNo" maxlength="32" value="${enterprisePlatformInterfacePo.acountNo}" />
                                </div>
                            </div>
                            <div class="col-md-3 red-tag tag">
                                *<span hidden="true" for="acountNo" class="error"></span>
                            </div>
                        </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label class="control-label col-md-3"><h5>接口配置参数</h5></label>
                    </div>
                    <div class=" addel_dynamic" >
                        <div class=" form-group target_dynamic">
                                <label class="control-label col-md-2" x="targetKey">&nbsp;&nbsp;</label>
                                <div class="col-md-3">
                                    <div class="rel">
                                            <input type="text"  x="targetVal" name="dynamicValue" class="form-control">
                                    </div>
                                </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <button class="btn btn-footer btn-cancel" onclick="toIfconfSettingList()">取消</button>
                <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
var iscommiting = false;
$(function() {
    initAddle('${enterprisePlatformInterfacePo.config}','${resJson}');
    
    $("#ifconfSettingAddForm").validate({
        rules: {
            name: {
                required: true
            },
            interfaceId: {
                required: true
            }
        },
        messages: {
            name: {
                required: "接口别名不能为空！"
            },
            interfaceId: {
                required: "请选择接口！"
            }
        },
        submitHandler: function(form) {
            if (iscommiting) {
                return false;
            }
            iscommiting = true;
        
            var	_config = {};
            var configEl = $(".target_dynamic");
            if(configEl){
                configEl.each(function(index, element) {
                    var _dataObj=$("input[x='targetVal']",$(element));
                    var _dataKey=$.trim(_dataObj.attr("name"));
                    var _dataValue=$.trim(_dataObj.val());
                    _config[_dataKey] = _dataValue;
                })
            }
            
            jQuery(form).ajaxSubmit({
                type : "post",
                url : "${ctx}/ifconf/setting/${typeId}/${enterprisePlatformInterfacePo.id}/edit",
                data:{
                    'config':JSON.stringify(_config)
                },
                dataType : "text",
                contentType : "application/x-www-form-urlencoded",
                timeout : 60000,
                success : function(data) {
                    if (data&&data=='ok') {
                        art.dialog({
                            title : '消息',
                            width : 220,
                            height : 80,
                            time : 3,
                            icon : 'succeed',
                            content : '配置成功！' ,
                            close : function() {
                                toIfconfSettingList() ;
                            }
                        });
                    } else {
                        art.dialog({
                            title : '消息',
                            width : 200,
                            height : 80,
                            time : 3,
                            icon : 'warning',
                            content : '编辑接口配置失败！'
                        });
                        iscommiting = false;
                    }
                },
                error : function(e) {
                    iscommiting = false;
                    console.log(e);
                    art.dialog({
                            title : '消息',
                            width : 200,
                            height : 80,
                            time : 3,
                            icon : 'warning',
                            content : '编辑接口配置失败！请稍后再试！'
                        });
                }
            });
        }
    });
}) ;

function initAddle(_dataStr,_confStr){
    var _xaddel=$(".addel_dynamic");
    var _xtarget=$(".target_dynamic");
    if(_dataStr&&_dataStr.length>0){
        var _dataJson=JSON.parse(_dataStr);
        var _confJson=JSON.parse(_confStr);
        for(var key in _confJson){
            var newObj=_xtarget.clone(true);
            $("label[x='targetKey']",newObj).text(_confJson[key]+"：");
            var valObj=$("input[x='targetVal']",newObj);
            valObj.attr("name",key);
            valObj.val(_dataJson[key]);
            newObj.insertAfter($(".target_dynamic:last"));
    　　}  
    }
    _xtarget.remove();
    $('.addel_dynamic').addel({
		classes: {target: 'target_dynamic'},
		animation: {duration: 300}
    });
}

function toIfconfSettingList() {
    var requestUrl = "${ctx}/ifconf/setting/${typeId}/main" ;
    var requestData = {} ;
    cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
</script>