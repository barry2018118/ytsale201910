<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>商品管理--列表</title>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>商品管理</span>&nbsp;&gt;&nbsp;<span class="dq">列表</span>
	</h1>
	<!--列表上方索引 E-->

	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<label class="fl" style="line-height:30px; margin-left:10px;">商品名称&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:150px;" id="productName" name="productName" placeholder="请输入商品名称" />
		<label class="fl" style="line-height:30px; margin-left:5px;">商品编号&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:150px;" id="productNo" name="productNo" placeholder="请输入商品编号" />
		<label class="fl" style="line-height:30px; margin-left:5px;">商品类别&nbsp;&nbsp;</label>
		<select class="form-control fl" id="productategory" name="productategory" style="width:80px;">
			<option value="" selected="selected">全部</option>
			<c:forEach items="${ctategory}" var="role"  varStatus="s">
				<option value="${role.id}">${role.name}</option>
			</c:forEach>
		</select>
		<label class="fl" style="line-height:30px; margin-left:5px;">商品状态&nbsp;&nbsp;</label>
		<select class="form-control fl" id="productStatus" name="productStatus" style="width:70px;">
			<option value="" selected="selected">全部</option>
			<option value="0">停售</option>
			<option value="1">启售</option>
		</select>
		<label class="fl" style="line-height:30px; margin-left:5px;">商品来源&nbsp;&nbsp;</label>
		<select class="form-control fl" id="productSource" name="productSource" style="width:80px;">
			<option value="" selected="selected">全部</option>
			<option value="1">系统内商品</option>
			<option value="2">第三方商品</option>
		</select>
		<label class="fl" style="line-height:30px; margin-left:5px;">商品归属&nbsp;&nbsp;</label>
		<select class="form-control fl" id="productAffiliation" name="productAffiliation" style="width:80px;">
			<option value="" selected="selected">全部</option>
			<option value="0">分销商品</option>
			<option value="1">自主商品</option>
		</select>
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
			<i class="icon-search"></i>搜索
		</button>
		<a href="javascript:;">
			<button class="btn btn-warning fl btn-top" type="submit" id="toAdd">
				<i class="icon-plus"></i>新建
			</button>
		</a>
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="productCategory_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
		var requestUrl = "${ctx}/product/supplier/list" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl = "${ctx}/product/supplier/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;
        }) ;

        // toAdd按钮绑定事件，点击后进入新建页
        $("#toAdd").click(function() {
			var requestUrl = "${ctx}/product/supplier/toAdd" ;
			var requestData = {} ;
			cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
        }) ;
    }) ;
</script>
