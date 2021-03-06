<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>编辑菜单</title>

<div class="page-title">
	<h1 class="fl">
		<span>功能注册</span>&nbsp;&gt;&nbsp;<span>功能管理</span>&nbsp;&gt;&nbsp;<span class="dq">编辑菜单</span>
	</h1>
	<button class="btn btn-primary fr btn-top" type="button" onclick="goBack()">
		<i class="icon-rotate-left"></i>返回
	</button>
</div>

<form id="editMenuForm" method="post" action="${ctx}/manage/function/menu/${menu.id}/edit?t=90" class="form-horizontal">
	<div class="row">
		<div class="col-lg-12">
	        <div class="">
	            <div class="widget-content padded">
	            	<div class="form-group">
						<label class="control-label col-md-2">所属模块：</label>
						<div class="col-md-7 tag">
							<div class="rel" style="color:#00aae9;">
								${module.name}
							</div>
						</div>
					</div>
	            
					<div class="form-group">
						<label class="control-label col-md-2">菜单名称：</label>
						<div class="col-md-7">
							<div class="rel">
								<input type="text" id="name" name="name" maxlength="12" class="form-control" placeholder="" value="${menu.name}" />
							</div>
						</div>
						<div class="col-md-3 tag">
							<font color="gray" class="red-tag">*</font>
							<span hidden="true" for="name" class="error"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-2">功能URL：</label>
						<div class="col-md-7">
							<div class="rel">
								<input type="text" id="url" name="url" maxlength="50" class="form-control" placeholder="" value="${menu.url}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2"></label>
						<div class="col-md-7 red-tag tag">
							<font color="gray" class="fl red-tag">最多50字符&nbsp;（填写示例：/manage/userFunction/getMenu）</font>
							<span hidden="true" for="url" class="error fl"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-2">菜单备注：</label>
						<div class="col-md-7">
							<textarea id="description" name="description" class="form-control" rows="5" maxlength="60">${menu.description}</textarea>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="row" style="min-height:60px;">
		<div class="form-group btn-form">
		    <div class="col-md-11 text-center">
		        <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" id="save">保存</button></a>
		        <button class="btn btn-footer btn-cancel" type="button" onclick="goBack()">取消</button>
		    </div>
		</div>
	</div>
</form>

<script type="text/javascript">
	var iscommiting = false ;
	
	$(function() {
		$("#editMenuForm").validate({
			rules: {
				name: {required:true}
			},
			messages:{
				name: {required:"菜单名称不能为空!"}
			},
			submitHandler: function(form) {
				if(iscommiting) {
					return false ;
				}
				iscommiting = true ;
				
				jQuery(form).ajaxSubmit({
					type: "post",
					dataType:"text",
					contentType: "application/x-www-form-urlencoded;charset=UTF-8",
					timeout:120000,
					success: function(data) {
						if(data=="success") {
							art.dialog({
								id: "editMenuSuccess" ,
								title: '消息',
								width: 160, 
								height: 80,
							    time: 3 ,
							    icon: 'succeed' ,
							    content: '操作成功' ,
							    close:function() {
							    	var requestUrl = "${ctx}/manage/function/view" ;
							    	var requestData = {} ;
							    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
							    }
							}) ;
						} else {
							iscommiting = false ;
						}
					},
					error:function(e) {
						iscommiting = false;
					}
				});
			}
		});
	}) ;
	
	function goBack() {
		var requestUrl = "${ctx}/manage/function/view" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
</script>