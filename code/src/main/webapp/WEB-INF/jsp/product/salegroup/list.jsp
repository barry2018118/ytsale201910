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
				url : '${ctx}/product/salegroup/getSearch',
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
                    title : '分销组名称',
                    field : 'name',
                    width : '10%'
                }, {
                    title : '分销商数',
                    field : 'userNumber',
                    width : '10%'
                }, {
                    title : '商品数',
                    field : 'productNumber',
                    width : '10%'
                }, {
                    title : '创建时间',
                    field : 'createTime',
                    width : '10%',
                    formatter: function (value, row, index) {
                        return  timestampToTime(value);
                    }
                },  {
                    title: '操作',
                    align : 'center',
                    width : '15%',
		            visible: true,
                    sortable: true,
		            formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toEdit(\''+ row.id + '\')">编辑</a>' ;
                        var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">删除</a>' ;
                        var enter = '<a href="javascript:;" onclick="toEnter(\''+ row.id + '\')">设置分销商</a>' ;
                        var product = '<a href="javascript:;" onclick="toProduct(\''+ row.id + '\')">设置商品</a>' ;
                        return edit + '&nbsp;&nbsp;' + del + '&nbsp;&nbsp;' + enter + '&nbsp;&nbsp;' + product;
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
	
    function toEdit(id) {
        var requestUrl = "${ctx}/product/salegroup/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
	//设置分销商
    function toEnter(id) {
        var requestUrl = "${ctx}/product/salegroup/enterprise/" + id + "/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    //设置商品
    function toProduct(id) {
        var requestUrl = "${ctx}/product/salegroup/product/" + id + "/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
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
        var url = "${ctx}/product/salegroup/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/product/salegroup/list" ;
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
