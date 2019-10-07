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
        <span>接口配置</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toIfconfBasicList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>


<form id="ifconfBasicAddForm" method="post" class="form-horizontal">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">接口信息</div>
                </div>
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">接口名称：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入接口名称" type="text" id="name" name="name" maxlength="30" />
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="name" class="error"></span>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">固定标识：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入固定标识" type="text" id="ename" name="ename" maxlength="30" />
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="ename" class="error"></span>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">接口类型：</label>
                        <div class="col-md-2">
                            <div class="rel">
                                    <select class="form-control" id="typeId" name="typeId">
                                        <!-- <option value="" >请选择</option> -->
                                        <option value="2" selected="selected">供应商</option>
                                        <option value="1">渠道</option>
                                    </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="typeId" class="error"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">固定参数：</label>
                   </div>     
                   <div class=" addel_fix" >
                        <div class=" form-group target_fix">
                            <div class=" col-md-2"></div>
                            <div class="control-label col-xs-4 col-md-2">

                                <label class="control-label col-md-2">键：</label>
                                <div class="col-md-9">
                                    <input type="text" name="dataKey" class="form-control">
                                </div>
                            </div>
                            <div class="control-label col-xs-4 col-md-2">
                                <label class="control-label col-md-2">值：</label>
                                <div class="col-md-9">
                                    <input type="text" name="dataValue" class="form-control">
                                </div>
                            </div>
                            <div class="tag col-xs-4 col-md-2"><button type="button" class="btn btn-danger addel-delete btn-top"><i class="icon icon-remove"></i> 删除</button></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-2"></div>
                            <div class="col-xs-12 col-md-2">
                                    <button type="button" class="btn btn-success btn-block addel-add btn-top"><i class="icon icon-plus"></i> 添加</button>
                            </div>
                        </div>    
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-2">动态参数：</label>
                    </div>
                    <div class=" addel_dynamic" >
                        <div class=" form-group target_dynamic">
                            <div class=" col-md-2"></div>
                            <div class="control-label col-xs-4 col-md-2">
                                <label class="control-label col-md-2">键：</label>
                                <div class="col-md-9">
                                    <input type="text" name="dynamicKey" class="form-control">
                                </div>
                            </div>
                            <div class="control-label col-xs-4 col-md-2">
                                <label class="control-label col-md-2">值：</label>
                                <div class="col-md-9">
                                    <input type="text" name="dynamicValue" class="form-control">
                                </div>
                            </div>
                            <div class="tag col-xs-4 col-md-2"><button type="button" class="btn btn-danger addel-delete btn-top"><i class="icon icon-remove"></i> 删除</button></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-2"></div>
                            <div class="col-xs-12 col-md-2">  
                                    <button type="button" class="btn btn-success btn-block addel-add btn-top"><i class="icon icon-plus"></i> 添加</button>
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
                <button class="btn btn-footer btn-cancel" onclick="toIfconfBasicList()">取消</button>
                <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
var iscommiting = false;
$(function() {
    initAddle('fix');
    initAddle('dynamic');
    
    $("#ifconfBasicAddForm").validate({
        rules: {
            name: {
                required: true
            },
            ename: {
                required: true
            }
        },
        messages: {
            name: {
                required: "接口名称不能为空！"
            },
            ename: {
                required: "固定标识不能为空！"
            }
        },
        
        submitHandler: function(form) {
            if (iscommiting) {
                return false;
            }
            iscommiting = true;
            var _data = {};
            var	_config = {};
            
            var dataEl = $(".target_fix");
            if(dataEl){
                dataEl.each(function(index, element) {
                    var _dataKey=$.trim($("input[name='dataKey']",$(element)).val());
                    var _dataValue=$.trim($("input[name='dataValue']",$(element)).val());
                    if(_dataKey&&_dataKey.length>0)
                        _data[_dataKey] = _dataValue;
                })
            }
            
            var configEl = $(".target_dynamic");
            if(configEl){
                configEl.each(function(index, element) {
                    var _dataKey=$.trim($("input[name='dynamicKey']",$(element)).val());
                    var _dataValue=$.trim($("input[name='dynamicValue']",$(element)).val());
                    if(_dataKey&&_dataKey.length>0)
                        _config[_dataKey] = _dataValue;
                })
            }
            
            jQuery(form).ajaxSubmit({
                type : "post",
                url : "${ctx}/ifconf/basic/add",
                data:{
                    'data':JSON.stringify(_data),
                    'interfaceConfig':JSON.stringify(_config)
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
                                toIfconfBasicList() ;
                            }
                        });
                    } else {
                        art.dialog({
                            title : '消息',
                            width : 200,
                            height : 80,
                            time : 3,
                            icon : 'warning',
                            content : '新建接口配置失败！'
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
                            content : '新建接口配置失败！请稍后再试！'
                        });
                }
            });
        }
    });
}) ;

function initAddle(str){
    $('.addel_'+str).addel({
		classes: {target: 'target_'+str},
		animation: {duration: 300}
	}).on('addel:delete', function (event) {
        var keyVal=event.target.find(':input').val();
		if (keyVal&&!window.confirm('确定删除:' + '"' + keyVal + '"?')) {
			event.preventDefault();
		}
    });
}

function toIfconfBasicList() {
    var requestUrl = "${ctx}/ifconf/basic/main" ;
    var requestData = {} ;
    cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
</script>