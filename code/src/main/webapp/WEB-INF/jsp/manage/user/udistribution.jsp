<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>
	用户授权
</title>

<div class="page-title">
	<h1 class="fl">
		<span>系统管理</span>&nbsp;&gt;&nbsp;<a href="#"  onclick="gobackudistribution()"><span>功能管理</span></a>&nbsp;&gt;&nbsp;<span class="dq">功能设置</span>
	</h1>
	<!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="gobackudistribution()"><i class="icon-rotate-left"></i>返回</button>
</div>
  
<form id="distributionForm" method="post" action="${ctx}/manage/user/saveAuthorize?t=90">
<!-- role Id -->
<input type="hidden" name="userId" value="${userId}">
<div class="row">
	<!-- 表格 start -->
	<div class="widget-content">
  		<div class="st_tree addW-td1">
  			<table class="table table-bordered table-striped">
      			<thead>
      			<tr>
          			<th class="td1">
              			功能
          			</th>
				</tr>
				</thead>
			</table>
          
			<c:forEach items="${lstModule}" var="module" varStatus="mo">
				<ul>
					<li>
						<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<td class="td1" style="font-size:14px; font-weight:bold;">
									<label class="checkbox">
										<input type="checkbox" name="moduleId[]" value="${module.id}" <c:if test="${module.isHave=='1'}">checked</c:if>><span></span>
									</label>
									${module.name}
	                          	</td>
							</tr>
						</tbody>
						</table>
					</li>
					
					<ul>
					<c:forEach items="${module.menus}" var="menu" varStatus="me">
                  		<li>
                      		<table class="table table-bordered table-striped">
                      		<tbody>
                      			<tr>
									<td class="td1" style="padding-left:65px !important;">
										<label class="checkbox">
											<input type="checkbox" name="menuId[]" value="${menu.id}" <c:if test="${menu.isHave=='1'}">checked</c:if>><span></span>
										</label>
										${menu.name}
		                          	</td>
                      			</tr>
                      		</tbody>
                      		</table>
                  		</li>
					
						<ul>
							<c:forEach items="${menu.actions}" var="action" varStatus="ac">
                       		<li>
                           		<table class="table table-bordered table-striped" style="padding-left:30px;">
                           		<tbody>
                           			<tr>
                               			<td class="td1" style="padding-left:110px !important;">
                                   			<label class="checkbox">
                                   				<input type="checkbox" name="actionId[]" value="${action.id}" <c:if test="${action.isHave=='1'}">checked</c:if>><span>
                                   			</span></label>
                                   			${action.name}
                               			</td>
                               		<%-- <td>
                               			<c:forEach items="${action.buttons}" var="button" varStatus="bu">
	                               			<label class="checkbox">
	                               				<input type="checkbox" name="buttonId[]" value="${button.id}" <c:if test="${button.isHave=='1'}">checked</c:if>><span></span>
	                               			</label>
	                               			<span class="btn-icon">
	                               				${button.name}
	                               			</span>
                               			</c:forEach>
                               		</td> --%>
                           			</tr>
                           		</tbody>
                           		</table>
							</li>
                   			</c:forEach>
                   		</ul>
					</c:forEach>
              		</ul>
				</ul>
			</c:forEach>
		</div>
		<div class="col-md-12 text-center">
        		<button  type="button"  class="btn btn-footer btn-cancel" onclick="gobackudistribution()">取消</button>
				<a href="javascript:;">
					<button class="btn btn-footer btn-primary" type="submit">保存</button>
				</a>
   		 </div>
	</div>
</div>
</form>
<script type="text/javascript" src="${jsPath}/SimpleTree.js"></script>
<script type="text/javascript" src="${jsPath}/business/function.js?t=90"></script>
<script type="text/javascript">
	var iscommiting = false ;
	
	$(function(){
		
		$(".st_tree").SimpleTree({
			click:function() {
			}
		});
		
		
		$("#distributionForm").validate({
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
								title: '消息',
								id: "distributionUserFunction" ,
								width:160,
								height:80,
							    time: 3 ,
							    icon: 'succeed' ,
							    content: '操作成功' ,
							    close:function() {
							    	gobackudistribution();
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
	
	/* function toAddModule() {
		var requestUrl = "${ctx}/manage/function/module/main" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
	function toAddMenu(moduleId) {
		var requestUrl = "${ctx}/manage/function/module/main" ;
		var requestData = {"moduleId": moduleId} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
	function toAddAction(menuId) {
		var requestUrl = "${ctx}/manage/function/module/main" ;
		var requestData = {"menuId": menuId} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
	function toAddButton(actionId) {
		var requestUrl = "${ctx}/manage/function/module/main" ;
		var requestData = {"actionId": actionId} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	} */
	
	function gobackudistribution(){
		var requestUrl = "${ctx}/manage/user/authorize" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
</script>