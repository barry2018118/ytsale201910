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
				url : '${ctx}/product/supplier/getSearch',
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
				}, {
					title : '商品编号',
					field : 'no',
					width : '10%'
				}, {
                    title : '商品名称',
                    field : 'name'
                }, {
                    title : '市场价',
                    field : 'marketPrice',
                    width : '5%'
                }, {
                    title : '商品类别',
                    field : 'categoryId',
                    align : 'center',
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
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "自主商品" ;
                        if(value == 0)
                            return "分销商品" ;
                    } */
                }, {
                    title : '有效期',
                    width : '12%',
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
                    width : '10%',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                    }
                }, {
		            title: '操作',
		            align : 'center',
		            width : '15%',
		            visible: true,
                    sortable: true,
		            formatter:function(value, row, index) {
                        var str = "";
                        var see = '<a href="javascript:;"  onclick="toCard(\''+ row.productId + '\')">详情</a>' ;
                        if(row.isSupplier == 1){
                            var edit = '<a href="javascript:;" onclick="toEdit(\''+ row.productId + '\')">编辑</a>' ;
                            var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">删除</a>' ;
                            var status = "";
                            if(row.status == 1) {
                                status = '<a href="javascript:;" onclick="toStatus(\''+ row.productId + '\')">停售</a>' ;
                            }
                            if(row.status == 0) {
                                status = '<a href="javascript:;" onclick="toStatus(\''+ row.productId + '\')">启售</a>' ;
                            }
                            var setMark = '<a href="javascript:;" onclick="toSettingCost(\''+ row.productId + '\');">设置成本</a>' ;
                            
                            str = str + edit + '&nbsp;&nbsp;' + del + '&nbsp;&nbsp;' + see + '&nbsp;&nbsp;' + status+ '&nbsp;&nbsp;' 
                            + setMark + '&nbsp;&nbsp;' ;
                        }else{
                            var look = '<a href="javascript:;" onclick="toQueryCost(\''+ row.productId + '\');" >查看成本</a>' ;
                            var ota = '<a href="javascript:;" onclick="toSettingOta(\''+ row.productId + '\');" >设置渠道售卖价</a>' ;
                            str = str + see + '&nbsp;&nbsp;' + look + '&nbsp;&nbsp;' + ota + '&nbsp;&nbsp;';
                        }
                        return str;
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

    function toEdit(id) {
        var requestUrl = "${ctx}/product/supplier/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toQueryCost(_sproductId){
        var requestUrl = "${ctx}/product/price/query/cost" ;
        var requestData = {'sproductId':_sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function toSettingCost(_sproductId) {
        var requestUrl = "${ctx}/product/price/setting/cost" ;
        var requestData = {'sproductId':_sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toSettingOta(_sproductId) {
        var requestUrl = "${ctx}/product/price/setting/ota" ;
        var requestData = {'sproductId':_sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toCard(id) {
        var requestUrl = "${ctx}/product/supplier/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toStatus(id) {
        var url = "${ctx}/product/supplier/" + id + "/status" ;
        jQuery.ajax({
            type:"post",
            async: true,
            cache: false,
            url: url ,
            dataType:"json",
            success:function(data) {
                if(data.flag) {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'succeed',
                        width : 200,
                        height : 80,
                        content : data.message,
                        close:function() {
                            var requestUrl = "${ctx}/product/supplier/list" ;
                            var requestData = {} ;
                            cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;
                        }
                    }) ;
                } else {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'warning',
                        width : 200,
                        height : 80,
                        content : data.message
                    }) ;
                }
            } ,
            error : function(e){
            }
        });
    }
	
	function toDelete(id) {
        art.dialog({
            title : '消息',
            id : "toDeleteProductCategory",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否删除',
            ok : function() {
                doDeleteProductCategory(id) ;
            },
            cancel : function() {
            }
        });
	}
	
	function doDeleteProductCategory(id) {
        var url = "${ctx}/product/supplier/" + id + "/delete" ;
        jQuery.ajax({
            type:"post",
            async: true,    
            cache: false,     
            url: url , 
            dataType:"json",  
            success:function(data) {
                if(data.flag) {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'succeed',
                        width : 240,
                        height : 80,
                        content : data.message,
                        close:function() {
                            var requestUrl = "${ctx}/product/supplier/list" ;
                            var requestData = {} ;
                            cserpLoadPage(requestUrl, requestData, 'GET', 'productCategory_data_area') ;
                        }
                    }) ;
                } else {
                    art.dialog({
	                    title : '消息',
	                    id : "",
	                    time: 3,
	                    icon : 'warning',
	                    width : 240,
	                    height : 80,
	                    content : data.message
                    }) ;
                }
            } ,
            error : function(e){
            } 
        });
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
