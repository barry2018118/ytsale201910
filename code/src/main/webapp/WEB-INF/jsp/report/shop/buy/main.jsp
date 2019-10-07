<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${cssPath}/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath}/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath}/locales/bootstrap-datepicker.zh-CN.js"></script>

<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>统计分析</span>&nbsp;&gt;&nbsp;<span class="dq">采购汇总报表</span>
    </h1>
    <!--列表上方索引 E-->

    <!--列表上方右侧按钮 S-->
    <div class="fr">
        <label class="fl" style="line-height:30px; margin-left:10px;">商品名称：&nbsp;&nbsp;</label>
        <input class="form-control fl" type="text" style="width:180px;" id="productName" name="productName" placeholder="商品名称" />
        <%-- <label class="fl" style="line-height:30px; margin-left:10px;">商品类别&nbsp;&nbsp;</label>
        <select class="form-control fl" id="productCategoryId" name="productCategoryId" style="width:90px;">
            <option value="" selected="selected">全部</option>
            <c:forEach items="${lstProductCategory}" var="productCategory" varStatus="s">
                <option value="${productCategory.id}">${productCategory.name}</option>
            </c:forEach>
        </select> --%>
        <label class="fl" style="line-height:30px; margin-left:10px;">统计时间：&nbsp;&nbsp;</label>
        <input class="form-control fl datepicker" type="text" style="width:180px;" id="startDate" name="startDate" placeholder="开始日期" value="${startDate}" />
        <input class="form-control fl datepicker" type="text" style="width:180px;" id="endDate" name="endDate" placeholder="截止日期" value="${endDate}" />
        
        <button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
            <i class="icon-search"></i>搜索
        </button>
        <!--
	        <button class="btn btn-warning btn-top" type="button" onclick="">
	            <i class="icon-download-alt"></i>导出
	        </button>
        -->
    </div>
    <!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="shop_buy_main_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
    
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/report/shop/buy/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'shop_buy_main_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl = "${ctx}/report/shop/buy/list" ;
	        var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'shop_buy_main_area') ;
        }) ;

    }) ;
</script>
