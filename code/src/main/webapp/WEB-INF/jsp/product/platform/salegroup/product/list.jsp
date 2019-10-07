<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap/bootstrap-table.css" />
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

<div class="row">
	<div class="widget-content">
		<div id="main_list">
			<table id="productCategory_table_details" class="table table-hover"></table>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var oTable = new TableInit() ;
		oTable.Init() ;
	}) ;

	var TableInit = function() {
		var oTableInit = new Object();
		oTableInit.Init = function() {
			$('#productCategory_table_details').bootstrapTable({
				// contentType: "application/x-www-form-urlencoded",
				url : '${ctx}/product/platform/salegroup/product/${salegroupId}/getSearch',
				method : 'get',
				queryParams : oTableInit.queryParams,
				queryParamsType : '',
				toolbar : '#toolbar',
				striped : true,
				cache : false,
				pagination : true,
				sidePagination : "server",
				pageNumber : 1,
				pageSize : 20,
				pageList : [20, 30, 50],
				sortable : false,
				sortOrder : "asc",
				strictSearch : true,
				clickToSelect : true,
				height : 620,
				uniqueId : "id",
				cardView : false,
				detailView : false,
                columns : [ {
                    title : 'ID',
                    field : 'id',
                    align : 'center',
                    visible: false,
                }, {
                    title : '序号',
                    align : 'center',
                    width : '10%',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '商品编号',
                    field : 'no',
                    width : '25%'
                }, {
                    title : '商品名称',
                    field : 'name'
                }, {
                   title: '操作',
                    align : 'center',
                    width : '20%',
		            visible: true,
                    sortable: true,
		            formatter:function(value, row, index) {
                        var see = '<a href="javascript:;" onclick="toQueryProfit(\'${salegroupId}\',\''+ row.productId + '\');"  >查看利润</a>' ;
                        return see;
                    }
		        }]
			});
		};

		oTableInit.queryParams = function(params) {
            var searchName = $("#searchName").val() ;
            var searchNo = $("#searchNo").val() ;
			return {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
				name : searchName,
				no : searchNo
			};
		};

		return oTableInit ;
	}

    function toProductCategoryList() {
        var requestUrl =  "${ctx}/product/platform/salegroup/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toQueryProfit(groupId,sproductId) {
        var requestUrl =  "${ctx}/product/price/query/profit" ;
        var requestData = {'groupId':groupId,'sproductId':sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

</script>
