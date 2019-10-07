<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>子功能列表</title>

<div class="page-title">
	<h1 class="fl">
		所属功能：
		<span class="dq">${module.name}</span>&nbsp;&gt;&nbsp;
		<span class="dq">${menu.name}</span>&nbsp;&gt;&nbsp;
		<span class="dq">${action.name}</span>
	</h1>
	<button class="btn btn-primary fr btn-top" type="button" onclick="goBack()">
		<i class="icon-rotate-left"></i>返回
	</button>
</div>

<input type="hidden" id="ctx" value="${ctx}" />
<div class="row" >
	<div class="widget-content">
		<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th width="50">序号</th>
				<th width="20%">子功能名称</th>
				<th>子功能URL</th>
				<th width="10%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${lstButton}" var="button" varStatus="bu">
			<tr>
				<td align="center">${bu.index +1}</td>
				<td>${button.name}</td>
				<td>${button.url}</td>
				<td align="center">
					<a href="javascript:;" onclick="toButtonEdit('${button.id}')" style="margin-right:10px;">编辑</a>
					<a href="javascript:;" onclick="toButtonDelete('${action.id}', '${button.id}')" style="margin-right:10px;">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="${jsPath}/business/function.js?t=90"></script>
<script type="text/javascript">
	$(function() {
	}) ;
	
	function goBack() {
		var requestUrl = "${ctx}/manage/function/view" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	}
</script>