<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<style>
#addEnterpriseForm input.checkbox{
	vertical-align:top;
	margin-right:5px;
}
</style> 
<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span>账号管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="history()" >企业管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">新建企业</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="history()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>

<form  class="form-horizontal" id="addEnterpriseForm">
<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">机构信息</div>
            </div>
            <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">机构名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入机构名称" type="text" id="name"  name="name" maxlength="20" onblur="checkUnique()" >
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="error" >*</font><span hidden="true" for="name" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">机构地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入详细地址" type="text" id="address"  name="address" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="联系人" type="text" value="" id="contacterName" name="contacterName" maxlength="20">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterName" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入联系电话" type="text" value="" id="contacterTel" name="contacterTel" maxlength="11">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterTel" class="error"></span></div>
                    </div>
                    <div class="form-group">
	                     <label class="control-label col-md-2">是否启用：</label>
                        <div class="col-md-7">
                            <div class="rel">
                          	  <select class="form-control" id="status" name="status" >
                           			<option value="1" selected="selected">是</option>
                           			<option value="0">否</option>
                           		</select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
	                    <label class="control-label col-md-2">机构简介：</label>
		                    <div class="col-md-7">
		                        <div class="rel">
		                            <textarea maxlength="200" id="introduction" name="introduction" placeholder="机构简介" class="form-control" rows="5"></textarea>
		                        </div>
		                    </div>
	                     <div class="col-md-3 red-tag tag"><span hidden="true" for="introduction" class="error"></span></div>
                   </div>
            </div>
        </div>
    </div>
</div>
<!-- 基本信息 End -->

<!--footer start-->
<div class="row" style="min-height:60px;">
	<div class="form-group btn-form">
	    <div class="col-md-11 text-center">
	        <button class="btn btn-footer btn-cancel" onclick="history()">取消</button>
	        <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" id="next">保存</button></a>
	    </div>
	</div>
</div>
<input type="hidden" name="domain" id="domain" value="www"/>
</form>
<!--footer end-->
<script type="text/javascript">
		var iscommiting = false;
		$(function() {
			$("#addEnterpriseForm").validate({
				rules : {
						name : {required : true},
						contacterName: {required: true},
						contacterTel: {required: true,number: true}
					},
				messages : {
						name : {required : "企业名称不能为空"},
						contacterName: {required:"联系人不能为空"},
						contacterTel: {required:"联系电话不能为空",number: "请输入正确的手机号"}
					},
				submitHandler: function(form) {
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				
				jQuery(form).ajaxSubmit({
					type: "post",
					url:"${ctx}/manage/enterprise/add",
					/*beforeSubmit:function(formData, jqForm, options){
								if($("#sid:checked").length>0){
									return true;
								}else{
									art.dialog({
										title: '消息',
										id: "scenic_id" ,
										width: 160, 
										height: 80,
									    time: 3 ,
									    icon: 'warning' ,
									    content: '请至少选择一个景区' ,
									    close:function(){
									    iscommiting = false;
									    }
									});
									return false;
								}
							},*/
					dataType:"text",
					contentType: "application/x-www-form-urlencoded;charset=UTF-8",
					timeout:120000,
					success: function(data) {
						if(data=="success") {
                            art.dialog({
								title: '消息',
								id: "T" ,
								width: 160, 
								height: 80,
							    time: 2 ,
							    icon: 'succeed' ,
							    content: '新建企业成功' ,
							    close:function(){
							    
							    	history();
							    }
							});
							
						} else {
							art.dialog({
								title: '消息',
								id: "T" ,
								width: 160, 
								height: 80,
							    time: 2 ,
							    icon: 'warning' ,
							    content: '新建企业失败' ,
							    close:function(){
							    history()
							    }
							});
							iscommiting = false;
						}
					},
					error:function(e) {
						iscommiting = false;
						error_show("服务器内部错误，请稍后再试...") ;
					}
				});
			}
		});
	});
	
	//全选
	function selectAll(obj) {
		if($(obj).is(":checked")){
			$("#addEnterpriseForm").find("input[type='checkbox']").each(function(){
				$(this).prop("checked", true);
			});
		}else{
			$("#addEnterpriseForm").find("input[type='checkbox']").each(function(){
				$(this).prop("checked", false);
			});
		}
	}
	
	function history(){
		var requestUrl = "${ctx}/manage/enterprise/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
	
	//检查机构名是否重复
	function checkUnique(){
		var n=$("#name").val();
		if(n!=''){
			$.get(
				"${ctx}/manage/enterprise/check",
				{"name":n},
				function (data){
					if(data){
						$("#error").text("*");
						$("#next").prop("disabled",false);
					}else{
						$("#error").prop("color","red");
						$("#error").text("*企业名称已存在");
						$("#next").prop("disabled",true);
					}
				}
			);
		}else{
			$("#error").text("*");
			$("#next").prop("disabled",false);
		};
	}
</script>

