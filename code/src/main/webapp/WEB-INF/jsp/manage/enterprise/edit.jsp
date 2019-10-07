<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<style>
#editEnterpriseForm input.checkbox{
	vertical-align:top;
	margin-right:5px;
}
</style> 
<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="historyThree()" >企业管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="historyThree()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>
<form class="form-horizontal"  id="editEnterpriseForm" enctype="multipart/form-data" >
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
                                <input class="form-control" placeholder="请输入机构名称" type="text" id="name"  name="name" value="${enterprise.name }" maxlength="20" onblur="checkNameUni('${enterprise.id }')">
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="nameMsg" >*</font><span hidden="true" for="name"  class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">机构地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入详细地址" type="text" id="address"  name="address" value="${enterprise.address }" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="联系人" type="text"  id="contacterName" name="contacterName" value="${enterprise.contacterName }" maxlength="20">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterName" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入联系电话" type="text"  id="contacterTel" name="contacterTel" value="${enterprise.contacterTel }" maxlength="11">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterTel" class="error"></span></div>
                    </div>
                    <div class="form-group">
	                     <label class="control-label col-md-2">是否启用：</label>
                        <div class="col-md-7">
                            <div class="rel">
                          	  <select class="form-control" id="status" name="status" value="${enterprise.status }" >
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
		                            <textarea maxlength="200" id="introduction" name="introduction" placeholder="机构简介" class="form-control" rows="5">${enterprise.introduction }</textarea>
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
    <div class="col-md-12 text-center">
        <a href="javascript:;"><button class="btn btn-footer btn-cancel" type="button" onClick="historyThree()">取消</button></a>
        <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" id="toSave">保存</button></a>
    </div>
</div>
</div>
<input  type="hidden" value="${enterprise.id }"  id="id"  name="id">
<input  type="hidden" value="${enterprise.domain }"  id="domain"  name="domain">
</form>
<!--footer end-->
<script type="text/javascript">
	$(document).ready(function() {
	    var iscommiting = false;
		$("#editEnterpriseForm").validate({
			rules: {
				name:{required: true},
				contacterName: {required: true},
				contacterTel: {required: true,number: true}
			},
			messages:{
				name: {required:"机构名称不能为空"},
				contacterName: {required:"联系人不能为空"},
				contacterTel: {required:"联系电话不能为空",number: "请输入正确的手机号"}
			},
			submitHandler: function(form) {
			
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				jQuery(form).ajaxSubmit({
						type : "post",
						url : "${ctx }/manage/enterprise/edit",
						contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						dataType : "text",
						success : function(data) {
							if (data == "success") {
                                    art.dialog({
										title: '消息',
										id: "enterpriseEditOK" ,
										width: 160, 
										height: 80,
									    time: 3 ,
									    icon: 'succeed' ,
									    content: '编辑机构成功' ,
									    close:function() {
									    	var requestUrl = "${ctx }/manage/enterprise/main" ;
											var requestData = {start:'${param.start}'} ;
											cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
									    }
								});
							} else {
								iscommiting =false;
							}
						},
						error : function(e) {
								iscommiting =false;
						}
					});
				}
		});
	});
	
	//全选
	function selectAll(obj) {
		if($(obj).is(":checked")){
			$("#editEnterpriseForm").find("input[type='checkbox']").each(function(){
				$(this).prop("checked", true);
			});
		}else{
			$("#editEnterpriseForm").find("input[type='checkbox']").each(function(){
				$(this).prop("checked", false);
			});
		}
	}
	
	//检查机构名是否重复
	function checkNameUni(id){
		var name=$("#name").val();
		if(name!=''){
			$.get(
				"${ctx}/manage/enterprise/check",
				{"id":id,"name":name},
				function (data){
					if(data=='true'){
						$("#nameMsg").text("*");
						$("#toSave").prop("disabled",false);
					}else{
						$("#nameMsg").prop("color","red");
						$("#nameMsg").text("*机构名称已存在");
						$("#toSave").prop("disabled",true);
					}
				}
			);
		}else{
			$("#nameMsg").text("*");
			$("#toSave").prop("disabled",false);
		}
	}
	function historyThree(){
		var requestUrl = "${ctx}/manage/enterprise/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
</script>