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

<!--弹框 start-->
<div class="modal fade" id="myModal4">
    <div class="modal-dialog pd-t80" style="width:880px;">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                <h4 class="modal-title">导出分销商报表</h4>
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
            $('#platformEnterprise_table_details').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/finance/enterprise/child/search',
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
                    title : '公司名称',
                    field : 'name'
                },  {
                    title: '操作',
                    align : 'center',
                    width : '20%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var report = '<a data-toggle="modal" href="#myModal4" onclick="toReport(\''+ row.id + '\')">导出报表</a>' ;
                        return report ;
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
    
    function toReport(childId) {
        var requestUrl = "${ctx}/finance/enterprise/child/" + childId + "/report" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'paymentExportDialog') ;
    }
    
</script>