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

<!--弹框 shop-->
<div class="modal fade" id="myModal4">
	<div class="modal-dialog pd-t80" style="width:880px;">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
				<h4 class="modal-title">电子码核销</h4>
			</div>
			<div class="modal-body" style="overflow-y: scroll;">
				<div class="form-group tb-pd" style="margin-bottom: 10px;">
					<div id="consumeDialog" class="filter"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹框 end-->

<!--弹框 shop-->
<div class="modal fade" id="myModal5">
	<div class="modal-dialog pd-t80" style="width:880px;">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
				<h4 class="modal-title">商品购买</h4>
			</div>
			<div class="modal-body" style="overflow-y: scroll;">
				<div class="form-group tb-pd" style="margin-bottom: 10px;">
					<div id="paymentExportDialog" class="filter"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹框 end-->

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
                url :'${ctx}/orders/other/getSearch',
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
                    field : 'orderno',
                    width : '14%',
                    align : 'center'
                }, {
                    title : '商品名称',
                    field : 'uproductName',
                    align : 'left'
                }, {
                    title : '商品单价',
                    field : 'fxPrice',
                    align : 'left',
                    formatter: function (value, row, index) {
                        return  "￥" +value;
                    }
                }, {
                    title : '购买数量',
                    field : 'num',
                    align : 'left'
                }, {
                    title : '订单总价',
                    field : 'price',
                    align : 'left',
                    formatter: function (value, row, index) {
                        return "￥" + value;
                    }
                }, {
                    title : '下单时间',
                    field : 'createdAt',
                    width : '15%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return timestampToTime(value * 1000) ;
                    }
                }, {
                    title : '订单状态',
                    field : 'statusName',
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
                        var bay = '<a href="javascript:;"  onclick="sendOrderMessageNo(\''+ row.ordersId + '\')">重新发码</a>' ;
                        var arrStatus=[10,11,12,13];
                        if(row.supplierId == ${enterId} && $.inArray(row.status, arrStatus)!=-1){
                            var say = '<a data-toggle="modal" href="#myModal4" onclick="toConsume(\''+ row.id + '\');" >手动核销</a>' ;
                        } else{
                            var say = " ";
                        }
                        var say1 ='<a data-toggle="modal" href="#myModal5"  onclick="toexit(\''+ row.id + '\')">退款</a>' ;
                        str = see + '&nbsp;&nbsp;' + bay + '&nbsp;&nbsp;' + say1 + '&nbsp;&nbsp;' + say ;
                        return  str;
                    }
                }]
            });
        };

        oTableInit.queryParams = function(params) {
            var productName = $("#productName").val() ;
            var productNo = $("#productNo").val() ;
            var linkMan = $("#linkMan").val() ;
            var linkPhone = $("#linkPhone").val() ;
            var starttime = $("#starttime").val() ;
            var endtime = $("#endtime").val() ;
            var state = $("#state").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                productName : productName,
                productNo : productNo,
                linkMan : linkMan,
                linkPhone : linkPhone,
                starttime : starttime,
                endtime : endtime,
                state : state,
            };
        };
        return oTableInit ;
    }

    function toexit(id) {
        var requestUrl = "${ctx}/orders/my/" + id + "/toexit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'paymentExportDialog') ;
    }

    function toCard(id) {
        var requestUrl = "${ctx}/orders/other/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function toConsume(id) {
        var requestUrl = "${ctx}/orders/consume/query/code" ;
        var requestData = {'enterpriseOrderId':id} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'consumeDialog') ;
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

    function  sendOrderMessageNo(orderId) {
        var _url = "${ctx}/orders/code/repeatSendCode?orderId="+orderId ;
        $.ajax({
            url: _url ,
            type: "get",
            dataType:"json",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            async: true,
            beforeSend: function() {
            },
            success: function(data) {
                if(data.flag) {
                    art.dialog({
                        title: '消息',
                        width: 200,
                        height: 80,
                        time: 3,
                        icon: 'succeed',
                        content: data.message,
                        close: function() {
                        }
                    });
                } else {
                    art.dialog({
                        title: '消息',
                        width: 200,
                        height: 80,
                        time: 3,
                        icon: 'warning',
                        content: data.message,
                        close: function() {}
                    });
                }
            },
            error:function(e) {
            }
        });
    }
</script>