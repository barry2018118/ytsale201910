<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>模块列表</title>

<div class="page-title">
	<h1 class="fl">
		<span>功能注册</span>&nbsp;&gt;&nbsp;<span>功能管理</span>&nbsp;&gt;&nbsp;<span class="dq">模块列表</span>
	</h1>
</div>

<div class="container-fluid">
	<ul class="fun-list">
		<c:forEach items="${lstModule}" var="module" varStatus="mo">
		<li name='func'>
			<a href="javascript:;">
				<span>
					<img src="${imagesPath}/sys/img-${fn:substringAfter(module.icon, 'icon-')}.png" alt="${module.name}" title="${module.name}" />
				</span>
				<p>${module.name}</p>
			</a>
		</li>
		</c:forEach>
		<li>
			<a href="javascript:;" onclick="toModuleAdd()">
				<span>
					<img src="${imagesPath}/sys/img-add.png" alt="新建" title="新建" />
				</span>
				<p>新建</p>
			</a>
		</li>
	</ul>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".container-fluid > ul > li[name='func'] > a").click(function() {
			var requestUrl = "${ctx}/manage/function/view"
			var requestData = {} ;
			cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
		}) ;
	}) ;
	
	function toModuleAdd() {
		var requestUrl = "${ctx}/manage/function/module/add" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
</script>