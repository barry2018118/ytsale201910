<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap/bootstrap-table.css" />
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

<div class="row">
	<div class="col-md-12">
		<table id="scenico" class="table"></table>
	</div>
</div>

<script type="text/javascript">

    var TableInit = function() {
        var table = new Object();
        table.Init = function() {
            $('#scenico').bootstrapTable({
                contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/info/scenic/getByName',
                method : 'get',
                queryParams : table.queryParams,
                queryParamsType : '',
                toolbar : '#toolbarr',
                striped : true,
                cache : false,
                pagination : true,
                sidePagination : "server",
                pageNumber : 1,
                pageSize : 10,
                pageList : [10, 30, 50],
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
                    width : '15%',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '景区名称',
                    field : 'name',
                    align : 'left'
                }, {
                    title: '操作',
                    align : 'center',
                    width : '25%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var see = '<a href="javascript:;" class="btn btn-footer btn-primary" aria-hidden="true" data-dismiss="modal" onclick="setScenic(\''+ row.name  + '\',' + row.id + ',' + row.provinceId + ',' + row.cityId + ')">选择</a>' ;
                        return  see;
                    }
                }]
            });
        };

        table.queryParams = function(params) {
            var scenicName = $("#scenicName").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                name : scenicName
            };
        };
        return table ;
    }

    $(document).ready(function() {
        var scenico = new TableInit() ;
        scenico.Init() ;
    }) ;

    function setScenic(name,id,provinceid,cityid) {
        $("#selectScenic").val(name);
        $("#scenicId").val(id);
        $("#provinceId").val(provinceid);
        $("#cityId").val(cityid);
    }
</script>