<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>
	全部功能
</title>

<div class="page-title">
	<h1 class="fl">
		<span>功能注册</span>&nbsp;&gt;&nbsp;<span>功能管理</span>&nbsp;&gt;&nbsp;<span class="dq">全部功能</span>
	</h1>
	<button class="btn btn-primary fr btn-top" type="button" onclick="goBack()">
		<i class="icon-rotate-left"></i>返回
	</button>
</div>
  
<input type="hidden" id="ctx" value="${ctx}" />
<div class="row">
	<!-- 表格 start -->
	<div class="widget-content">
  		<div class="st_tree addW-td1">
  			<table class="table table-bordered table-striped">
      			<thead>
      			<tr>
          			<th class="td1">
          				<label class="fl" style="font-weight:bold;display:inline-block;width:90%;text-align:center;">模块/菜单/功能
          				</label>
              			
          					<img src="${imagesPath}/func/new_module.png" class="td1-right" onclick="toModuleAdd()" title="新建模块" />
          				<!-- 
              				<a href="javascript:;" onclick="toModuleAdd()" class="td1-right">新建模块</a>
						-->
          			</th>
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
								<td class="td1">
									<label class="checkbox">${module.name}</label>
									<img src="${imagesPath}/func/new_menu.png" class="td1-right" onclick="toMenuAdd('${module.id}')" title="新建菜单" />
									<img src="${imagesPath}/iconList-del.png" class="td1-right delModuleCls" onclick="toModuleDelete('${module.id}')" title="删除" />
									<img src="${imagesPath}/iconList-edit.png" class="td1-right" onclick="toModuleEdit('${module.id}')" title="编辑" />
									<!-- 
										<a href="javascript:;" onclick="toModuleDelete('${module.id}')" class="td1-right">删除</a>
									-->
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
										<label class="checkbox">${menu.name}</label>
										<img src="${imagesPath}/func/new_action.png" class="td1-right" onclick="toActionAdd('${menu.id}')" title="新建功能" />
										<img src="${imagesPath}/iconList-del.png" class="td1-right delMenuCls" onclick="toMenuDelete('${menu.id}')" title="删除" />
										<img src="${imagesPath}/iconList-edit.png" class="td1-right" onclick="toMenuEdit('${menu.id}')" title="编辑" />
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
                                   			<label class="checkbox">${action.name}</label>
                                   			<%--<img src="${imagesPath}/func/new_button.png" class="td1-right" onclick="toButtonAdd('${action.id}')" title="新建按钮"  />
                                   			--%><img src="${imagesPath}/iconList-del.png" class="td1-right delAction" onclick="toActionDelete('${action.id}')" title="删除" />
											<img src="${imagesPath}/iconList-edit.png" class="td1-right" onclick="toActionEdit('${action.id}')" title="编辑" />
                               			</td>
                               		<td>
                               			<c:forEach items="${action.buttons}" var="button" varStatus="bu">
                               				<span class="btn-icon">
                               					${button.name}
                               				</span>
                               			</c:forEach>
                               			<c:if test="${!empty action.buttons}">
                               				<a href="javascript:;" onclick="toButtonList('${action.id}')" class="td1-right">编辑/查看</a>
                               			</c:if>
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
	</div>
</div>

<script type="text/javascript" src="${jsPath}/SimpleTree.js"></script>
<script type="text/javascript" src="${jsPath}/business/function.js?t=90"></script>
<script type="text/javascript">
	$(function(){
		$(".st_tree").SimpleTree({
			click:function() {
			}
		});
		
		$(".delModuleCls").click(function(event) {
			event.stopPropagation() ;
		}) ;
		$(".delMenuCls").click(function(event) {
			event.stopPropagation() ;
		}) ;
		$(".delAction").click(function(event) {
			event.stopPropagation() ;
		}) ;
	}) ;
	
	function goBack() {
		var requestUrl = "${ctx}/manage/function/module/main" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
</script>
