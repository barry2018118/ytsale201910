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
                url : '${ctx}/capital/platform/trans/toSearch',
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
                    width : '2%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '变动时间',
                    field : 'createTime',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                    }
                }, {
                    title : '业务',
                    field : 'actionType',
                    align : 'center',
                    formatter: function (value, row, index) {
                        switch (value){
							case 1 :  return "充值";
                            case 2 :  return "提现";
                            case 3 :  return "下单";
                            case 4 :  return "退款";
                            case 5 :  return "消费";
                            default:  return "无数据";
						}
                    }
                }, {
                    title : '变动资金',
                    field : 'capitalType',
                    align : 'center',
                    formatter: function (value, row, index) {
                        switch (value){
                            case 0 :  return "预收款";
                            case 1 :  return "平台资金";
                            default:  return "无数据";
                        }
                    }
                }, {
                    title : '变动前金额（元）',
                    field : 'oldPrice',
                    align : 'center',
                    formatter: function (value, row, index) {
                       if(value)
                           return "￥"+value;
                       if(!value)
                           return "￥0.00";
                    }
                }, {
                    title : '本次变动金额（元）',
                    field : 'price',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                },{
                    title : '变动后金额（元）',
                    field : 'currentPrice',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                },  {
                    title: '操作',
                    align : 'center',
                    width : '10%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toEdit(\''+ row.id + '\');">查看</a>' ;
                        return   edit;
                    }
                }]
            });
        };
		//检索
        oTableInit.queryParams = function(params) {
            var actionType = $("#actionType").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                actionType : actionType
            };
        };

        return oTableInit ;
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

    function toEdit(id) {
        var requestUrl = "${ctx}/capital/platform/trans/" + id + "/card" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>