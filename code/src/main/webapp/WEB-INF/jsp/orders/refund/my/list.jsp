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
			<table id="user_table_details" class="table table-hover"></table>
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
            $('#user_table_details').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",d
                url : '${ctx}/orders/refund/my/getSearch',
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
                columns : [{
                    title : 'ID',
                    field : 'id',
                    align : 'center',
                    visible: false,
                }, {
                    title : '序号',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '订单编号',
                    field : 'orderNo',
                    align : 'left'
                }, {
                    title : '商品名称',
                    field : 'productName',
                    align : 'left'
                }, {
                    title : '商品单价',
                    field : 'fxprise',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                }, {
                    title : '退款数量',
                    field : 'num',
                    align : 'center'
                }, {
                    title : '退款人',
                    field : 'creaName',
                    align : 'center'
                }, {
                    title : '退款时间',
                    field : 'time',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value != null){
                            return timestampToTime(value*1000);
                        }else{
                            return value;
                        }
                    }
                }, {
                    title : '审核状态',
                    field : 'state',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value == 0)
                            return "待审核";
                        if(value == 1)
                            return "审核通过";
                        if(value == 2)
                            return "审核不通过";
                    }
                }, {
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toSearch(\''+ row.id + '\');">退款详情</a>' ;
                        return  edit;
                    }
                }]
            });
        };
        //检索
        oTableInit.queryParams = function(params) {
            var productNo = $("#productNo").val() ;
            var productName = $("#productName").val() ;
            var starttime = $("#starttime").val() ;
            var endtime = $("#endtime").val() ;
            var state = $("#state").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                productNo : productNo,
                productName : productName,
                starttime : starttime,
                endtime : endtime,
                state : state
            };
        };

        return oTableInit ;
    }
    function toSearch(id) {
        var requestUrl = "${ctx}/orders/refund/my/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+h+m+s;
    }
</script>