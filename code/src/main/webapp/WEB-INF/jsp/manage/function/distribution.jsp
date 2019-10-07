<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>
	企业功能设置
</title>

<div class="page-title">
	<h1 class="fl">
		<span>功能注册</span>&nbsp;&gt;&nbsp;<span>功能管理</span>&nbsp;&gt;&nbsp;<span class="dq">企业功能设置</span>
	</h1>
</div>
  
<form id="distributionForm" method="post" action="${ctx}/manage/function/distribution?t=90">
<input type="hidden" value="${param.enterpriseId}" name="enterpriseId">
<div class="row">
	<!-- 表格 start -->
	<div class="widget-content">
  		<div class="st_tree">
  			<table class="table table-bordered table-striped">
      			<thead>
      			<tr>
          			<th class="td1">模块/菜单/功能</th>
          			<th>子功能</th>
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
								<td>
									<div class="td-edit"></div>
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
									<td class="td1">
										<label class="checkbox">
											<input type="checkbox" name="menuId[]" value="${menu.id}" <c:if test="${menu.isHave=='1'}">checked</c:if>><span></span>
										</label>
										${menu.name}
		                          	</td>
                          			<td>
                              			<div class="td-edit"></div>
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
                               			<td class="td1">
                                   			<label class="checkbox">
                                   				<input type="checkbox" name="actionId[]" value="${action.id}" <c:if test="${action.isHave=='1'}">checked</c:if>><span>
                                   			</span></label>
                                   			${action.name}
                               			</td>
                               		<td>
                               			<c:forEach items="${action.buttons}" var="button" varStatus="bu">
	                               			<label class="checkbox">
	                               				<input type="checkbox" name="buttonId[]" value="${button.id}" <c:if test="${button.isHave=='1'}">checked</c:if>><span></span>
	                               			</label>
	                               			<span class="btn-icon">
	                               				${button.name}
	                               			</span>
                               			</c:forEach>
                               		</td>
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
			<a href="javascript:;">
				<button class="btn btn-footer btn-primary" type="submit">保存</button>
			</a>
        <button type="button" class="btn btn-footer btn-cancel" onclick="gobackenterprise()">取消</button>
    </div>
	</div>
</div>
</form>

<script type="text/javascript" src="${jsPath}/SimpleTree.js"></script>
<script type="text/javascript">
	var iscommiting = false ;
	
	function gobackenterprise(){
		var requestUrl = "${ctx}/manage/enterprise/main" ;
		var requestData={};
    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
	
	$(function(){
		$(".st_tree").SimpleTree({
		});
		
		$("#distributionForm").validate({
			submitHandler: function(form) {
				if(iscommiting) {
					return false;
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
								id: "distributionEnterpriseFunction" ,
								width: 160, 
								height: 80,
							    time: 3 ,
							    icon: 'succeed' ,
							    content: '操作成功' ,
							    close:function() {
							    	var requestUrl = "${ctx}/manage/function/distribution" ;
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
</script>