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
			<table id="platformEnterprise_table_details" class="table table-hover"></table>
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
			$('#platformEnterprise_table_details').bootstrapTable({
				// contentType: "application/x-www-form-urlencoded",
				url : '${ctx}/platform/enterprise/shop/search',
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
					width : '10%',
					align : 'center',
					formatter: function (value, row, index) {  
                        return index+1 ;  
                    }
				}, {
                    title : '商户类型',
                    width : '10%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var companyType_val ; 
                        if (row['companyType'] === 1) {
                            companyType_val = '运营商' ;
                        }
                        if (row['companyType'] === 2) {
                            companyType_val = '供应商' ;
                        }
                        if (row['companyType'] === 3) {
                            companyType_val = '分销商' ;
                        }
                        return companyType_val ;
                    }
                }, {
                    title : '公司名称',
                    field : 'name'
                }, {
                    title : '公司负责人',
                    field : 'contacterName',
                    width : '15%'
                }, {
                    title : '负责人手机',
                    field : 'contacterPhone',
                    width : '15%',
                    align : 'center'
                }, {
                    title : '状态',
                    width : '10%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var ststus_val ; 
                        if (row['status'] === 1) {
                            ststus_val = '启用' ;
                        }
                        if (row['status'] === 0) {
                           ststus_val = '<font color="red">停用</font>' ; 
                        }
                        return ststus_val ;
                    }
                },  {
		            title: '操作',
		            align : 'center',
		            width : '15%',
		            visible: true,
                    sortable: true,
		            formatter:function(value, row, index) {
                        /* var setStatus ;
                        if (row['status'] === 1) {
                            setStatus = '<a href="javascript:;" onclick="toSetStatus(\''+ row.id + '\', 0)">停用</a>' ;
                        } 
                        if (row['status'] === 0) {
                            setStatus = '<a href="javascript:;" onclick="toSetStatus(\''+ row.id + '\', 1)">启用</a>' ;
                        } 
                        var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">删除</a>' ; */
                        var view = '<a href="javascript:;" onclick="toView(\''+ row.id + '\')">查看</a>' ;
                        return view ;
                    }
		        }]
			});
		};

		oTableInit.queryParams = function(params) {
            var companyType = $("#companyType").val() ;
            var searchName = $("#searchName").val() ;
			
			return {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
				companyType: companyType,
				name : searchName
			};
		};

		return oTableInit ;
	}
    
    function toView(id) {
        var requestUrl = "${ctx}/platform/enterprise/" + id + "/toView" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
	
</script>