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
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="historyThree()" >账号管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">编辑用户</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="historyThree()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>
<form class="form-horizontal"  id="editUserForm" enctype="multipart/form-data" >
<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">账号信息</div>
            </div>
            <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">所属机构：</label>
                        <div class="col-md-7">
                            <div class="rel">
                               <select class="form-control" id="enterpriseId" name="enterpriseId" ">
                           			<c:forEach items="${listEnterprise }" var="s">
                           					<c:if test="${s.name==user.enterpriseName }">
                           					<option selected="selected" value="${s.id }">${s.name }</option>
                           					</c:if>
                           					<c:if test="${s.name!=user.enterpriseName }">
                           					<option value="${s.id }">${s.name }</option>
                           					</c:if>
                           			</c:forEach>
                           		</select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"><font id="enterpriseIdMsg" >*</font><span hidden="true" for="enterpriseId" class="error"></span></div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-2">登录账号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="username"  name="username" value="${user.username }" maxlength="20" onblur="checkUser('${user.id}')">
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="nameMsg" >*</font><span hidden="true" for="username"  class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">登录密码：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="password" id="password"  name="password" value="" maxlength="20" >
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="error" >*</font><span hidden="true" for="password"  class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="name" name="name" value="${user.name }" maxlength="20">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="name" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="phone" name="phone" value="${user.phone }" maxlength="11" >
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="phone" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">分配角色：</label>
                        <div class="col-md-7">
                            <div class="rel">
 								<select class="form-control" id="roleId" name="roleId" disabled="disabled">
                           			<option value="">${user.title }</option>
                           			<c:forEach items="${listRole }" var="s">
                           					<c:if test="${s.title==user.title }">
                           					<option selected="selected" value="${s.id }">${s.title }</option>
                           					</c:if>
                           					<c:if test="${s.title!=user.title }">
                           					<option value="${s.id }">${s.title }</option>
                           					</c:if>
                           			</c:forEach>
                           		</select>                            
                           		</div>
                        </div>
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
<input  type="hidden"   id="id" value="${user.id }"  name="id">
<input  type="hidden"   id="isDelete" value="0"  name="isDelete">
</form>
<!--footer end-->
<script type="text/javascript">
	$(document).ready(function() {
	
	    var iscommiting = false;
		$("#editUserForm").validate({
			rules: {
				enterpriseId:{required: true},
				username:{required: true},
				password: {required: true},
				name: {required: true},
				phone: {required: true,number: true}
			},
			messages:{
				enterpriseId: {required:"所属机构不能为空"},
				username: {required:"登录账号不能为空"},
				password: {required:"登录密码不能为空"},
				name: {required:"联系人不能为空"},
				phone: {required:"联系电话不能为空",number: "请输入正确的手机号"}
			},
			submitHandler: function(form) {
			
				if(iscommiting){
					return false;
				}
				iscommiting = true;
				jQuery(form).ajaxSubmit({
						type : "post",
						url : "${ctx }/manage/user/edit",
						dataType : "text",
						success : function(data) {
							if (data == "success") {
								art.dialog({
										title: '消息',
										width: 160, 
										height: 80,
									    time: 3 ,
									    icon: 'succeed' ,
									    content: '操作成功' ,
									    close:function() {
									    	var requestUrl = "${ctx }/manage/user/main" ;
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
	
	function checkUser(id){
		var username=$("#username").val();
		if(username!=''){
			$.get(
				"${ctx}/manage/user/check",
				{"id":id,"username":username},
				function (data){
					if(data=='true'){
						$("#nameMsg").text("*");
						$("#toSave").prop("disabled",false);
					}else{
						$("#nameMsg").prop("color","red");
						$("#nameMsg").text("*账户名称已存在");
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
		var requestUrl = "${ctx}/manage/user/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
</script>