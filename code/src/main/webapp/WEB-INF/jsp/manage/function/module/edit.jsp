<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>编辑模块</title>

<div class="page-title">
	<h1 class="fl">
		<span>功能注册</span>&nbsp;&gt;&nbsp;<span>功能管理</span>&nbsp;&gt;&nbsp;<span class="dq">编辑模块</span>
	</h1>
	<button class="btn btn-primary fr btn-top" type="button" onclick="goBack()">
		<i class="icon-rotate-left"></i>返回
	</button>
</div>

<form id="editModuleForm" method="post" action="${ctx}/manage/function/module/${module.id}/edit?t=90" class="form-horizontal">
	<div class="row">
		<div class="col-lg-12">
	        <div class="">
	            <div class="widget-content padded">
	            	<div class="form-group" style="height:60px;margin-bottom:22px;">
						<label class="control-label col-md-2" style="line-height:54px;">模块图标：</label>
						<div class="col-md-7" >
							<div class="rel">
								<div id="choiceModuleIcon" class="choiceModuleIcon clearfix">
									<a href="javascript:;" icon="icon-default">
										<img src="${imagesPath}/sys/img-default.png" />
									</a>
									<a href="javascript:;" icon="icon-account">
										<img src="${imagesPath}/sys/img-account.png" />
									</a>
									<a href="javascript:;" icon="icon-resource">
										<img src="${imagesPath}/sys/img-resource.png" />
									</a>
									<a href="javascript:;" icon="icon-product">
										<img src="${imagesPath}/sys/img-product.png" />
									</a>
									<a href="javascript:;" icon="icon-sale">
										<img src="${imagesPath}/sys/img-sale.png" />
									</a>
									<a href="javascript:;" icon="icon-operator">
										<img src="${imagesPath}/sys/img-operator.png" />
									</a>
									<a href="javascript:;" icon="icon-finance">
										<img src="${imagesPath}/sys/img-finance.png" />
									</a>
									<a href="javascript:;" icon="icon-statistical">
										<img src="${imagesPath}/sys/img-statistical.png" />
									</a>
									<a href="javascript:;" icon="icon-funcReg">
										<img src="${imagesPath}/sys/img-funcReg.png" />
									</a>
								</div>
							</div>
						</div>
					</div>
	            	<input type="hidden" id="icon" name="icon" value="${module.icon}" />
	            
					<div class="form-group">
						<label class="control-label col-md-2">模块名称：</label>
						<div class="col-md-7">
							<div class="rel">
								<input type="text" id="name" name="name" maxlength="12" class="form-control" placeholder="" value="${module.name}" />
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
								<input type="text" id="url" name="url" maxlength="50" class="form-control" placeholder="" value="${module.url}" />
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
						<label class="control-label col-md-2">模块备注：</label>
						<div class="col-md-7">
							<textarea id="description" name="description" class="form-control" rows="5" maxlength="60">${module.description}</textarea>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="row" style="min-height:60px;">
		<div class="form-group btn-form" >
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
		showChoiceIcon() ;
		$("#choiceModuleIcon > a").click(function() {
			$("#choiceModuleIcon > a").removeClass("active") ;
			$(this).addClass("active") ;
			var iconValue = $(this).attr("icon") ;
			$("#icon").val(iconValue) ;
		}) ;
	
		$("#editModuleForm").validate({
			rules: {
				name: {required:true}
			},
			messages:{
				name: {required:"模块名称不能为空!"}
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
								id: "editModuleSuccess" ,
								title: '消息',
								width: 160, 
								height: 80,
							    time: 3 ,
							    icon: 'succeed' ,
							    content: '操作成功' ,
							    close:function() {
							    	var requestUrl = "${ctx}/manage/function/module/main" ;
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
	
	function showChoiceIcon() {
		var icon = $("#icon").val() ;
		$("#choiceModuleIcon > a").each(function(index) {
			var thisIcon = $(this).attr("icon") ;
			if(thisIcon == icon) {
				$(this).addClass("active") ;
			}
		}) ;
	}
	
	function goBack() {
		var requestUrl = "${ctx}/manage/function/view" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
</script>