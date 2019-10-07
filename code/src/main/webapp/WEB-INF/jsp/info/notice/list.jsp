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
                url : '${ctx}/info/notice/getByTitle',
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
                    title : '序列',
                    /*align : 'noticeType',*/
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '公告类型',
                    field : 'noticeType',
                    width : '10%',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var noticeType_val ;
                        if (row['noticeType'] === 1) {
                            noticeType_val = '系统公告' ;
                        }
                        return noticeType_val ;
                    }
                }, {
                    title : '公告标题',
                    field : 'title',
                    width : '30%',
                    align : 'center',

                }, {
                    title : '创建者',
                    field : 'createName',
                    width : '10%',
                    align : 'center'
                },{
                    title : '创建时间',
                    field : 'createTime',
                    width : '15%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return formatDateTime(value);
                    }
                },{
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var edit = '<a href="javascript:;" onclick="toEditNotice(\''+ row.id + '\')">编辑</a>' ;
                        var del = '<a href="javascript:;" onclick="toDeleteNotice(\''+ row.id + '\')">删除</a>' ;
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
                title : searchName
            };
        };

        return oTableInit ;
    }

    function toEditNotice(id) {
        var requestUrl = "${ctx}/info/notice/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }


    function toDeleteNotice(id) {
        art.dialog({
            title : '消息',
            id : "toDeleteNotice",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否删除',
            ok : function() {
                doDeleteNotice(id) ;
            },
            cancel : function() {
            }
        });
    }


    function doDeleteNotice(id) {
        var url = "${ctx}/info/notice/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/info/notice/list" ;
                            var requestData = {} ;
                            cserpLoadPage(requestUrl, requestData, 'GET', 'scenic_data_area') ;
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

    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };
</script>