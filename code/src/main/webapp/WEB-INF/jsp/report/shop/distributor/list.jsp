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
				url : '${ctx}/report/shop/distributor/search',
				method : 'get',
				queryParams : oTableInit.queryParams,
				queryParamsType : '',
				toolbar : '#toolbar',
				striped : true,
				cache : false,
				pagination : true,
			    sidePagination : "client",
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
                    field : 'enterpriseId',
                    align : 'center',
                    visible: false,
                }, {
					title : '序号',
					align : 'center',
					width : '5%',
					formatter: function (value, row, index) {  
                        return index+1 ;  
                    }  
				}, {
					title : '分销商',
					field : 'enterpriseName',
					align : 'left'
                }, {
                    title : '销售数量',
                    field : 'saleAmount',
                    width : '10%'
                }, {
                    title : '销售金额',
                    field : 'saleTotalPrice',
                    width : '12%',
                    formatter: function (value, row, index) {  
                        return '￥' + value ;
                    } 
                }, {
                    title : '退款数量',
                    field : 'refundAmount',
                    width : '10%'
                }, {
                    title : '退款手续费',
                    field : 'refundFeeTotalPrice',
                    width : '12%',
                    formatter: function (value, row, index) {  
                        return '￥' + value ;
                    } 
                }, {
                    title : '退款金额',
                    field : 'refundTotalPrice',
                    width : '12%',
                    formatter: function (value, row, index) {  
                        return '￥' + value ;
                    } 
                }]
			});
		};

		oTableInit.queryParams = function(params) {
            var childName = $("#childName").val();
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
			return {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
				childName : childName, 
				startDate : startDate,
				endDate : endDate
			};
		};

		return oTableInit ;
	}
	
</script>