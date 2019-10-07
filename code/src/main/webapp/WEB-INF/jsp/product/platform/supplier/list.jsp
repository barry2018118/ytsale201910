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
				url : '${ctx}/product/platform/supplier/getSearch',
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
                },{
                    title : 'ID',
                    field : 'productId',
                    align : 'center',
                    visible: false,
                }, {
					title : '序号',
					align : 'center',
					width : '5%',
					formatter: function (value, row, index) {
                        return index+1 ;  
                    }  
				},{
					title : '商品编号',
					field : 'no',
                    align : 'center',
                    width : '10%'
				}, {
                    title : '创建企业',
                    field : 'enterpriseName',
                    width : '10%'
                }, {
                    title : '商品名称',
                    field : 'name',
                }, {
                    title : '市场价',
                    field : 'marketPrice',
                    width : '5%'
                }, {
                    title : '商品类别',
                    field : 'categoryId',
                    width : '7%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "常规代理";
                        if(value == 2)
                            return "时段包票" ;
                        if(value == 3)
                            return "第三方票务" ;
                    }
                }, {
                    title : '商品状态',
                    field : 'status',
					align : 'center',
					width : '7%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "启售" ;
                        if(value == 0)
                            return "停售" ;
                    }
                }, {
                    title : '商品来源',
                    field : 'thirdPlatformId',
                    align : 'center',
                    width : '7%',
                    formatter: function (value, row, index) {
                        if(value != null && value != '')
                            return "第三方商品" ;
                        else
                            return "系统内商品" ;
                    }
                /* }, {
                    title : '商品归属',
                    field : 'isSupplier',
                    align : 'center',
                    width : '7%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "自主商品" ;
                        if(value == 0)
                            return "分销商品" ;
                    } */
                } , {
                    title : '有效期',
                    width : '15%',
                    formatter: function (value, row, index) {
                        return row.starttime + "至" + row.endtime;
                    }
                }, {
                    title : '库存',
                    field : 'stronum',
                    width : '7%',
                }, {
                    title : '更新时间',
                    field : 'updateTime',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                    }
                },  {
		            title: '操作',
		            align : 'center',
		            visible: true,
                    sortable: true,
                    width : '10%',
		            formatter:function(value, row, index) {
                        var str = "";
                        var see = '<a href="javascript:;"  onclick="toCard(\''+ row.productId + '\')">查看</a>' ;
                        var look = '<a href="javascript:;" onclick="toQueryCost(\''+ row.productId + '\');" >查看成本</a>' ;
                        str = str + see + '&nbsp;&nbsp;' + look ;
                        return  str;
                    }
		        }]
			});
		};

		oTableInit.queryParams = function(params) {
            var productName = $("#productName").val() ;
            var productNo = $("#productNo").val() ;
            var productategory = $("#productategory").val() ;
            var productStatus = $("#productStatus").val() ;
            var productSource = $("#productSource").val() ;
            var productAffiliation = $("#productAffiliation").val() ;
			return {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
                productName : productName,
                productNo : productNo,
                productategory : productategory,
                productStatus : productStatus,
                productSource : productSource,
                productAffiliation : productAffiliation
			};
		};
		return oTableInit ;
	}


    function toQueryCost(_sproductId){
        var requestUrl = "${ctx}/product/price/query/cost" ;
        var requestData = {'sproductId':_sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toCard(id) {
        var requestUrl = "${ctx}/product/platform/supplier/" + id + "/toCard" ;
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