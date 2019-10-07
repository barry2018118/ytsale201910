<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<%@taglib prefix="date"  uri="/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>


<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="historyThree()" >权限管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">编辑</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="historyThree()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>
<form method="post" class="form-horizontal"  id="editRoleForm" enctype="multipart/form-data" >
<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">角色信息</div>
            </div>
            <div class="widget-content padded">
                     <div class="form-group">
                        <label class="control-label col-md-2">角色名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="请输入角色名称" type="text" id="title"  name="title"
                                 value="${infoRole.title }" maxlength="20" onblur="checkRole('${infoRole.id}')" >
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="nameMsg" >*</font><span hidden="true" for="title" class="error"></span></div>
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
<input  type="hidden" value="${infoRole.id }"  id="id"  name="id">
</form>
<!--footer end-->
<script type="text/javascript">
	$(document).ready(function() {
	    var iscommiting = false;
		$("#editRoleForm").validate({
			rules: {
				title:{required: true}
			},
			messages:{
				title: {required:"角色名称不能为空"}
			},
			submitHandler: function(form) {
			
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				jQuery.ajax({
						type : "post",
						async : true,
						cache : false,
						data:{"id":$("#id").val(),"title":$("#title").val()} ,
						url : "${ctx }/manage/role/edit",
						dataType : "text",
						success : function(data) {
							if (data == "success") {
								art.dialog({
									title: '消息',
									width: 160, 
									height: 80,
								    time: 3 ,
								    icon: 'succeed' ,
								    content: '编辑角色成功' ,
								    close:function() {
								    	var requestUrl = "${ctx }/manage/role/main" ;
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
	function checkRole(id){
		var title=$("#title").val();
		if(name!=''){
			$.get(
				"${ctx}/manage/role/check",
				{"id":id,"title":title},
				function (data){
					if(data=='true'){
						$("#nameMsg").text("*");
						$("#toSave").prop("disabled",false);
					}else{
						$("#nameMsg").prop("color","red");
						$("#nameMsg").text("*角色名称已存在");
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
		var requestUrl = "${ctx}/manage/role/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
</script>