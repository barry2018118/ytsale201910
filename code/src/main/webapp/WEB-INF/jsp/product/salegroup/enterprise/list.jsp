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
				url : '${ctx}/product/salegroup/enterprise/${salegroupId}/getSearch',
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
                    title : '分销商账号',
                    field : 'contacterPhone',
                    width : '10%'
                }, {
                    title : '分销商公司名称',
                    field : 'name',
                    width : '10%'
                }, {
                   title: '操作',
                    align : 'center',
                    width : '15%',
		            visible: true,
                    sortable: true,
		            formatter:function(value, row, index) {
                        var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">删除</a>' ;
                        return del;
                    }
		        }]
			});
		};

		oTableInit.queryParams = function(params) {
            var searchName = $("#searchName").val() ;
			return {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
				name : searchName
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
        var url = "${ctx}/product/salegroup/enterprise/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/product/salegroup/enterprise/${salegroupId}/list" ;
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

</script>
