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
				url : '${ctx}/product/salegroup/product/${salegroupId}/getSearch',
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
                        var distribution = '<a href="javascript:;" onclick="toDistribution(\'${salegroupId}\', \''+ row.productId + '\')">开启分销</a>' ;
                        var stopDistribution = '<a href="javascript:;" onclick="toStopDistribution(\'${salegroupId}\', \''+ row.productId + '\')">停止分销</a>' ;
                        // var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">删除</a>' ;
                        var see = '<a href="javascript:;" onclick="toQueryProfit(\'${salegroupId}\',\''+ row.productId + '\');"  >查看利润</a>' ;
                        var set = '<a href="javascript:;" onclick="toSettingProfit(\'${salegroupId}\',\''+ row.productId + '\');" >设置利润</a>' ;
                        
                        var flag = row.isDistribution ;
                        if(flag == 0) {
                            return distribution + "&nbsp;&nbsp;" + see + "&nbsp;&nbsp;" + set ;
                        } else {
                            return stopDistribution + "&nbsp;&nbsp;" + see + "&nbsp;&nbsp;" + set ;
                        }
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
        var url = "${ctx}/product/salegroup/product/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/product/salegroup/product/${salegroupId}/list" ;
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
    function toProductCategoryList() {
        var requestUrl =  "${ctx}/product/salegroup/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toQueryProfit(groupId,sproductId) {
        var requestUrl =  "${ctx}/product/price/query/profit" ;
        var requestData = {'groupId':groupId,'sproductId':sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toSettingProfit(groupId,sproductId) {
        var requestUrl =  "${ctx}/product/price/setting/profit" ;
        var requestData = {'groupId':groupId,'sproductId':sproductId} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function toDistribution(groupId, sproductId) {
        art.dialog({
            title : '消息',
            id : "toDistribution",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否开启分销？',
            ok : function() {
                doDistribution(groupId, sproductId) ;
            },
            cancel : function() {
            }
        });
    }
    
    function toStopDistribution(groupId, sproductId) {
        art.dialog({
            title : '消息',
            id : "toStopDistribution",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否进行停止分销？',
            ok : function() {
                doStopDistribution(groupId, sproductId) ;
            },
            cancel : function() {
            }
        });
    }
    
    function doDistribution(groupId, sproductId) {
        var url = "${ctx}/product/salegroup/product/" + groupId + "/describution/" + sproductId ;
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
                            var requestUrl = "${ctx}/product/salegroup/product/${salegroupId}/list" ;
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
            error : function(e){stopDescribution
            } 
        });
    }
    
    function doStopDistribution(groupId, sproductId) {
        var url = "${ctx}/product/salegroup/product/" + groupId + "/stopDescribution/" + sproductId ;
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
                            var requestUrl = "${ctx}/product/salegroup/product/${salegroupId}/list" ;
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

</script>
