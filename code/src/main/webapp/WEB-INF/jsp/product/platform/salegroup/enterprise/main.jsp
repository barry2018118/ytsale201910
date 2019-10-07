<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>商品组--设置分销商</title>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>商品组</span>&nbsp;&gt;&nbsp;<span class="dq">设置分销商</span>
	</h1>
	<!--列表上方索引 E-->

	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<input class="form-control fl" type="text" style="width:180px;" id="searchName" name="searchName" placeholder="分销商账号" />
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
			<i class="icon-search"></i>搜索
		</button>
		<!--列表上方右侧按钮 S-->
		<button class="btn btn-primary fr btn-top" type="button" id="back" name="back" onclick="toProductCategoryList();"><i class="icon-rotate-left"></i>返回</button>
		<!--列表上方右侧按钮 E-->
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="productCategory_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
		var requestUrl = "${ctx}/product/platform/salegroup/enterprise/${salegroupId}/list" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl = "${ctx}/product/platform/salegroup/enterprise/${salegroupId}/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;
        }) ;

    }) ;
</script>
