<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath}/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath}/locales/bootstrap-datepicker.zh-CN.js"></script>
    
<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="history()" >权限管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">新建权限</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="history()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>

<form  class="form-horizontal" id="addRoleForm">
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
                                <input class="form-control" placeholder="请输入角色名称" type="text" id="title"  name="title" maxlength="20" onblur="checkRoleName()">
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="error" >*</font><span hidden="true" for="title" class="error"></span></div>
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
<input type="hidden" name="isDelete" id="isDelete" value="0"/>
</form>
<!--footer end-->
<script type="text/javascript">
		var iscommiting = false;
		$(function() {
			$("#addRoleForm").validate({
				rules : {
						title : {required : true}
					},
				messages : {
						title : {required : "角色名称不能为空"}
					},
				submitHandler: function(form) {
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				
				jQuery(form).ajaxSubmit({
					type: "post",
					url:"${ctx}/manage/role/add",
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
							    content: '新建角色成功' ,
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
							    content: '新建角色失败' ,
							    close:function(){
							    history();
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
	
	
	function history(){
		var requestUrl = "${ctx}/manage/role/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
	
	function checkRoleName(){
		var n=$("#title").val();
		if(n!=''){
			$.get(
				"${ctx}/manage/role/check",
				{"title":n},
				function (data){
					if(data){
						$("#error").text("*");
						$("#next").prop("disabled",false);
					}else{
						$("#error").prop("color","red");
						$("#error").text("*角色名称已存在");
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

