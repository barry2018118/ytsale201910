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
                url : '${ctx}/orders/platform/consume/toSearch',
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
                    width : '5%',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '订单编号',
                    field : 'orderNo',
                    width : '14%',
                    align : 'center'
                }, {
                    title : '商品名称',
                    field : 'productName',
                    align : 'left'
                }, {
                    title : '下单时间',
                    field : 'createdTime',
                    align : 'left'
                }, {
                    title : '购买数量',
                    field : 'num',
                    align : 'left'
                }, {
                    title : '消费时间',
                    field : 'consumeTime',
                    align : 'left'
                }, {
                    title : '消费数量',
                    field : 'consumenum',
                    width : '15%',
                    align : 'center'
                }, {
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var see = '<a href="javascript:;"  onclick="toCard(\''+ row.id + '\')">查看详情</a>' ;
                        return  see;
                    }
                }]
            });
        };

        oTableInit.queryParams = function(params) {
            var productName = $("#productName").val() ;
            var productNo = $("#productNo").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                productName : productName,
                productNo : productNo
            };
        };
        return oTableInit ;
    }

    function toCard(id) {
        var requestUrl = "${ctx}/orders/platform/consume/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() < 10 ?'0'+date.getDate() : date.getDate();
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+" "+h+m+s;
    }
</script>
