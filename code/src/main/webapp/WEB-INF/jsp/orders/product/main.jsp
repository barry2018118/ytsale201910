<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<title>商品购买--商品列表</title>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>商品购买</span>&nbsp;&gt;&nbsp;<span class="dq">商品列表</span>
	</h1>
	<!--列表上方索引 E-->

	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<label class="fl" style="line-height:30px; margin-left:10px;">商品名称&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:180px;" id="productName" name="productName" placeholder="请输入商品名称" />
		<label class="fl" style="line-height:30px; margin-left:10px;">商品编号&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:180px;" id="productNo" name="productNo" placeholder="请输入商品编号" />
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
			<i class="icon-search"></i>搜索
		</button>
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="productCategory_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
		var requestUrl = "${ctx}/orders/product/list" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl = "${ctx}/orders/product/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;
        }) ;
    }) ;
</script>
