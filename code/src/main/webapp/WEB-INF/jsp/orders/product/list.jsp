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

<!--弹框 shop-->
<div class="modal fade" id="myModal4">
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


<!--弹框 price-->
<div class="modal fade" id="myModalprice">
	<div class="modal-dialog pd-t80" style="width:880px;">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
				<h4 class="modal-title">价格详情</h4>
			</div>
			<div class="modal-body" style="overflow-y: scroll;">
				<div class="form-group tb-pd" style="margin-bottom: 10px;">
					<div id="paymentPriceDialog" class="filter"></div>
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
            $('#productCategory_table_details').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/orders/product/toSearch',
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
                    title : '商品编号',
                    field : 'no',
                    width : '12%',
                    align : 'center'
                }, {
                    title : '商品名称',
                    field : 'name',
                    align : 'left'
                }, {
                    title : '今日成本',
                    field : 'todayPrice',
                    align : 'left',
                     width : '6%'
                }, {
                    title : '市场价',
                    field : 'marketPrice',
                    align : 'left',
                    width : '6%',
                    formatter: function (value, row, index) {
                        return "￥" + value;
                    }
                }, {
                    title : '限售价',
                    field : 'limitPrice',
                    align : 'left',
                    width : '6%',
                    formatter: function (value, row, index) {
                        return "￥" + value;
                    }
                }, {
                    title : '商品有效期',
                    field : '',
                    width : '15%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return timestampToDate(row.validStartDate) + " 至 " + timestampToDate(row.validEndDate);
                    }
                }, {
                    title : '库存',
                    field : 'storeNum',
                    width : '6%'
                }, {
                    title : '更新时间',
                    field : 'updateTime',
                    width : '10%',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                    }
                } ,{
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                  /*      var see = '<a href="javascript:;"  onclick="toCard(\''+ row.id + '\')">查看详情</a>' ;
                        var look = '<a data-toggle="modal" href="#myModalprice"  onclick="priceAll()">查看价格</a>' ;
                        var say = '<a data-toggle="modal" href="#myModal4"  onclick="exportAll(\''+ row.id + '\')">购买</a>' ;
                        str = see + '&nbsp;&nbsp;' + look  + '&nbsp;&nbsp;' + say;*/
                        
                        var str = "";
                         if(row.status == 1){
                            var say = '<a data-toggle="modal" href="#myModal4" onclick="exportAll(\''+ row.id + '\')">购买 </a>'  ;
                            str += say + '&nbsp;&nbsp;';
                        }
                        var look = '<a  data-toggle="modal" href="#myModalprice" onclick="priceAll(\''+ row.id + '\')">价格详情</a>' ;
                        var see = '<a href="javascript:;"  onclick="toCard(\''+ row.id + '\')">商品详情</a>' ;
                        str = str + look + '&nbsp;&nbsp;' + see  + '&nbsp;&nbsp;';
                        return  str;
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
        var requestUrl = "${ctx}/orders/product/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function timestampToDate(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() < 10 ?'0'+date.getDate() : date.getDate();
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D;
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

    function exportAll(id) {
        var requestUrl ="${ctx}/orders/product/"+id+"/toShop" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET','paymentExportDialog') ;
    }

    function priceAll(id) {
        var requestUrl = "${ctx}/orders/product/toPrice" ;
        var requestData = {'sproductId':id} ;
        cserpLoadPage(requestUrl, requestData, 'GET','paymentPriceDialog') ;
    }

</script>
