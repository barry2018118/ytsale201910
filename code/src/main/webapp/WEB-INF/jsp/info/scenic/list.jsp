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
			<table id="infoScenic_table_details" class="table table-hover"></table>
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
            $('#infoScenic_table_details').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/info/scenic/getByName',
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
                    title : '景区编号',
                    field : 'shopNo',
                    width : '20%',
                }, {
                    title : '景区名称',
                    field : 'name',
                }, {
                    title : '景区地址',
                    field : 'address',
                }, {
                    title : '景区星级',
                    field : 'level',
                    width : '10%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var companyType_val ;
                        if (row['level'] === 0) {
                            companyType_val = '未评星级' ;
                        }
                        if (row['level'] === 3) {
                            companyType_val = '3星级' ;
                        }
                        if (row['level'] === 4) {
                            companyType_val = '4星级' ;
                        }
                        if (row['level'] === 5) {
                            companyType_val = '5星级' ;
                        }

                        return companyType_val ;
                    }
                },{
                    title : '景区电话',
                    field : 'tel',
                    width : '15%',

                },
					{
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toEditInfoScenic(\''+ row.id + '\')">编辑</a>' ;
                        var del = '<a href="javascript:;" onclick="toDeleteInfoScenic(\''+ row.id + '\')">删除</a>' ;
                        return edit + '&nbsp;&nbsp;' + del ;
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

    function toEditInfoScenic(id) {
        var requestUrl = "${ctx}/info/scenic/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }


    function toDeleteInfoScenic(id) {
        art.dialog({
            title : '消息',
            id : "toDeleteInfoScenic",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否删除',
            ok : function() {
                doDeleteInfoScenic(id) ;
            },
            cancel : function() {
            }
        });
    }


    function doDeleteInfoScenic(id) {
        var url = "${ctx}/info/scenic/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/info/scenic/main" ;
                            var requestData = {} ;
                            cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
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