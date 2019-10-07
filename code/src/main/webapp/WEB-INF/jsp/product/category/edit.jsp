<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>商品类别</span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toProductCategoryList()"><i class="icon-rotate-left"></i>返回</button>
    <!--列表上方右侧按钮 E-->
</div>

<form id="productCategoryEditForm" method="post" class="form-horizontal">
    <input type="hidden" id="productCategoryId" name="" value="${entity.id}" />
	<div class="row">
	    <div class="col-lg-12">
	        <div class="widget-container fluid-height">
	            <div class="heading">
	                <div class="pull-left">商品类别</div>
	            </div>
	            <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">商品类别名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入商品类别名称" type="text" id="name" name="name"
                                    value="${entity.name}" maxlength="10" />
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"><font id="nameMsg">*</font><span hidden="true" for="title" class="error"></span></div>
                    </div>
                </div>
	        </div>
	    </div>
	</div>

	<div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-12 text-center">
	           <button class="btn btn-footer btn-cancel" type="button" onClick="toProductCategoryList()">取消</button>
	           <button class="btn btn-footer btn-primary" type="submit" id="toSave">保存</button>
	       </div>
	   </div>
    </div>
</form>

<script type="text/javascript">
	$(document).ready(function() {
	    var iscommiting = false ;
		$("#productCategoryEditForm").validate({
			rules: {
				name: {required: true}
			},
			messages: {
				name: {required: "商品类别名称不能为空！"}
			},
			submitHandler: function(form) {
                var theId = $("#productCategoryId").val() ;
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				jQuery.ajax({
                    type: "post",
					async: true,
					cache: false,
					data: {
                        "id": theId,
                        "name": $("#name").val()
                    },
					url: "${ctx}/product/category/" + theId + "/edit",
                    dataType: "json",
					success: function(data) {
						if (data.flag) {
							art.dialog({
								title: '消息',
								width: 200, 
								height: 80,
							    time: 3,
							    icon: 'succeed',
							    content: data.message,
							    close: function() {
							    	toProductCategoryList() ;
							    }
							});
						} else {
							iscommiting =false ;
							art.dialog({
                                title: '消息',
                                width: 200, 
                                height: 80,
                                time: 3,
                                icon: 'warning',
                                content: data.message,
                                close: function() {
                                }
                            });
						}
					},
					error : function(e) {
                        iscommiting =false ;
					}
				}) ;
			}
		}) ;
	}) ;
	
	function toProductCategoryList() {
		var requestUrl = "${ctx}/product/category/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
</script>