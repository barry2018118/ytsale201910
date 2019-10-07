<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>账号管理</span>&nbsp;&gt;&nbsp;<span>更改密码</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="goBack()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>
<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="">
            <div class="widget-content padded">
                <form id="modifyPassword" action="#" class="form-horizontal">
                    <input type="hidden" name="id"  value="${ep.id}">
                    <div class="form-group">
                        <label class="control-label col-md-2">新密码：</label>
                        <input type="password" style="display:none;" />
                        <div class="col-md-7">
                            <div class="rel">
                                <input maxlength="20" class="form-control" placeholder="" type="password" name="password" id="password" value="">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"><font id="passwords" color="red">*</font><span hidden="true" for="password" class="error" ></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">密码确认：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input maxlength="20" class="form-control" placeholder="" type="password" name="ispassword" id="ispassword" value="">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag"><font id="ispasswords" color="red">*</font><span hidden="true" for="ispassword" class="error" ></span></div>
                    </div>
                    
                    <br><br><br>
					<div class="form-group btn-form">
					    <div class="col-md-12 text-center">
					        <a href="javascript:;"><button class="btn btn-footer btn-primary" type="submit" >保存</button></a>
					        <button class="btn btn-footer btn-cancel" type="button" onclick="goBack()">取消</button>
					    </div>
					</div>
                 </form>
            </div>
        </div>
    </div>
</div> 
<script type="text/javascript">
iscommiting=false;
$(document).ready(function() {
        $("#modifyPassword").validate({
		   rules:{
             password:{
		       required:true,
		       minlength:6,
		       maxlength:15
		     },
		     ispassword:{
		       required:true,
		       equalTo:"#password"
		     }
		   },
		   messages:{
		        password:{
		          required:"密码不能为空",
		          minlength:"长度在6~15之间",
		          maxlength:"长度在6~15之间"
		        },
		        ispassword:{
		          required:"请再次输入密码",
		          equalTo:"两次输入密码不一致,请重新输入"
		        }
		   },
		     
       submitHandler: function(form){
		    
		    if(iscommiting){
					return false;
				}
			iscommiting = true;
		    
		    jQuery(form).ajaxSubmit({
			type: "post",
			url:"${ctx}/manage/employee/editpassword",
			dataType:"text",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			success: function(data) {
				if(data=="success") {
					art.dialog({
						title: '消息',
						id: "modifyPasswordOk",
						width: 160, 
						height: 80,
						time: 2 ,
						icon: 'succeed' ,
						content: '修改成功!' ,
						close:function(){
							var requestUrl = "${ctx }/manage/enterprise/main" ;
							var requestData = {start:'${param.start}'} ;
							cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
						}
					});
				 } else {
				 	art.dialog({
						title: '消息',
						id: "modifyPasswordError",
						width: 160, 
						height: 80,
						time: 2 ,
						icon: 'error' ,
						content: '修改失败!' ,
					});
					iscommiting =false;
				}
			},
				error:function(e) {
				    art.dialog({
						title: '消息',
						id: "modifyError" ,
						width: 160, 
						height: 80,
						time: 2 ,
						icon: 'error' ,
						content: '服务器内部错误，请稍后再试...' ,
					    close:function(){
					   		iscommiting = false;
					 	}
				  });
				}
			 });
	       }
	     });
       });
    
    function goBack() {
		var requestUrl = "${ctx }/manage/enterprise/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>                    
                    