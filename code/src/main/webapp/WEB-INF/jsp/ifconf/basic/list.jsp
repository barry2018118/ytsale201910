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
			<table id="ifconf_table_details" class="table table-hover"></table>
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
            $('#ifconf_table_details').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/ifconf/basic/list/page',
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
                    title : '接口名称',
                    field : 'name',
                    width : '35%',
                },{
                    title : '类型',
                    field : 'typeId',
                    width : '15%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var type_val='' ;
                        if (row['typeId'] === 1) {
                            type_val = '渠道' ;
                        }else if (row['typeId'] === 2) {
                            type_val = '供应商' ;
                        }else{
                            type_val = '--' ;
                        }
                        return type_val ;
                    }
                },{
                    title : '状态',
                    field : 'status',
                    width : '15%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var companyType_val='' ;
                        if (row['status'] === 5) {
                            companyType_val = '已开启' ;
                        }else if (row['status'] === 2) {
                            companyType_val = '已禁用' ;
                        }else{
                            companyType_val = '已删除' ;
                        }
                        return companyType_val ;
                    }
                },{
                    title : '更新时间',
                    field : 'updatedAt',
                    width : '15%',
                    formatter:function(value, row, index) {
                        return timestampFormat(row['updatedAt']);
                    }
                },
					{
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toEditIfconfBaisc(\''+ row.id + '\')">编辑</a>' ;
                        var able ='';
                        if (row['status'] == 5) {
                            able = '<a href="javascript:;" onclick="toUpStatusIfconfBaisc(\''+ row.id + '\',2,this)">禁用</a>' ;
                        }else if (row['status'] == 2) {
                            able = '<a href="javascript:;" onclick="toUpStatusIfconfBaisc(\''+ row.id + '\',5,this)">启用</a>' ;
                        }
                        var del = '<a href="javascript:;" onclick="toUpStatusIfconfBaisc(\''+ row.id + '\',1,this)">删除</a>' ;
                        return edit + '&nbsp;&nbsp;' + able +'&nbsp;&nbsp;' + del ;
                    }
                }]
            });
        };

        oTableInit.queryParams = function(params) {
            var searchName = $("#searchName").val() ;
            var searchTypeId = $("#searchTypeId").val();
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                name : searchName,
                typeId:searchTypeId
            };
        };

        return oTableInit ;
    }

    function toEditIfconfBaisc(id) {
        var requestUrl = "${ctx}/ifconf/basic/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }


    function toUpStatusIfconfBaisc(id,status,domObj) {
        art.dialog({
            title : '警告',
            id : "toUpStatusIfconfBaisc",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否'+$(domObj).text(),
            ok : function() {
                doUpStatusIfconfBaisc(id,status) ;
            },
            cancel : function() {
            }
        });
    }


    function doUpStatusIfconfBaisc(id,_status) {
        var url = "${ctx}/ifconf/basic/" + id + "/status" ;
        jQuery.ajax({
            type:"post",
            async: true,
            cache: false,
            url: url ,
            data:{'status':_status},
            dataType:"text",
            success:function(data) {
                if(data&&data=='ok') {
                    art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'succeed',
                        width : 200,
                        height : 80,
                        content : '操作成功！',
                        close:function() {
                            var requestUrl = "${ctx}/ifconf/basic/main" ;
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
                        content : '操作失败，请稍后再试'
                    }) ;
                }
            } ,
            error : function(e){
                console.log(e);
                art.dialog({
                        title : '消息',
                        id : "",
                        time: 3,
                        icon : 'warning',
                        width : 200,
                        height : 80,
                        content : '处理失败，请求稍后再试！'
                    }) ;
            }
        });

    }
</script>