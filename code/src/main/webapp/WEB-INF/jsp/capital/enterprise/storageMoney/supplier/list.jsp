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
                url : '${ctx}/capital/enterprise/storageMoney/supplier/toSearch',
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
                    width : '15%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '供应商名称',
                    field : 'name',
                    align : 'left'

                }, {
                    title : '预收款(元)',
                    field : 'storageMoney',
                    width : '15%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                }, {
                    title: '操作',
                    align : 'center',
                    width : '20%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var index = '<a href="javascript:;" onclick="toSet(\''+ row.id + '\');">查看明细</a>' ;
                        // var edit = '<a href="javascript:;" onclick="toSearch(\''+ row.id + '\');">查看资金变动</a>' ;
                        // return  index + "&nbsp;&nbsp;" + edit;
                        return  index;
                    }
                }]
            });
        };
		//检索
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

    function toSet(id) {
        var requestUrl = "${ctx}/capital/enterprise/storageMoney/supplier/" + id + "/toStorage" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toSearch(id) {
        var requestUrl = "${ctx}/capital/enterprise/storageMoney/supplier/" + id + "/toCard" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

</script>