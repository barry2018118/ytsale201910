<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>平台运营商--列表</title>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>平台运营商</span>&nbsp;&gt;&nbsp;<span class="dq">列表</span>
	</h1>
	<!--列表上方索引 E-->

	<!--列表上方右侧按钮 S-->
	<div class="fr">
        <label class="fl" style="line-height:30px; margin-left:10px;">运营商名称：</label>
		<input class="form-control fl" type="text" style="width:180px;" id="searchName" name="searchName" placeholder="运营商名称" />
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearchOperation">
			<i class="icon-search"></i>搜索
		</button>
		<a href="javascript:;">
			<button class="btn btn-warning fl btn-top" type="submit" id="toAddOperation">
				<i class="icon-plus"></i>新建
			</button>
		</a>
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="platformEnterprise_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
		var requestUrl = "${ctx}/platform/enterprise/operation/list" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'platformEnterprise_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearchOperation").click(function() {
            var requestUrl = "${ctx}/platform/enterprise/operation/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'platformEnterprise_data_area') ;
        }) ;

        // toAdd按钮绑定事件，点击后进入新建页
        $("#toAddOperation").click(function() {
			var requestUrl = "${ctx}/platform/enterprise/toAdd" ;
			var requestData = {} ;
			cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
        }) ;
    }) ;
</script>
