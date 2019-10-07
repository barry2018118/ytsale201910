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
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/my/user/myUserSearch',
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
                /* }, {
                    title : '员工类型',
                    width : '10%',
                    field : 'accountType',
                    align : 'center',
                    formatter:function(value, row, index) {
                        var companyType_val ;
                        if (row['accountType'] === 0) {
                            companyType_val = '平台' ;
                        }
                        if (row['accountType'] === 1) {
                            companyType_val = '商户' ;
                        }
                        return companyType_val ;
                    } */
                }, {
                    title : '员工账号',
                    field : 'username',
                    align : 'center'
                }, {
                    title : '员工姓名',
                    field : 'name',
                    width : '15%',
                    align : 'center'
                }, {
                    title : '员工电话',
                    field : 'phone',
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
                    width : '20%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {

                        var edit = '<a href="javascript:;" onclick="toEdit(\''+ row.id + '\')">编辑</a>' ;
                        var setStatus ;
                        if (row['status'] === 1) {
                            setStatus = '<a href="javascript:;" onclick="toSetStatus(\''+ row.id + '\', 0)">停用</a>' ;
                        }
                        if (row['status'] === 0) {
                            setStatus = '<a href="javascript:;" onclick="toSetStatus(\''+ row.id + '\', 1)">启用</a>' ;
                        }
                        var del = '<a href="javascript:;" onclick="toDelete(\''+ row.id + '\')">注销</a>' ;

                        var update = '<a href="javascript:;" onclick="toPassword(\''+ row.id + '\')">重置密码</a>' ;
                        var distribution = '<a href="javascript:;" onclick="toDistribution(\''+ row.id + '\')">分配权限</a>' ;
                        return  edit + '&nbsp;&nbsp;' + setStatus + '&nbsp;&nbsp;' + del+ '&nbsp;&nbsp;' 
                                + update + '&nbsp;&nbsp;' + distribution ;
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
        var requestUrl = "${ctx}/my/user/" + id + "/toEdit" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }

    function toPassword(id) {
        var requestUrl = "${ctx}/my/user/" + id + "/toPassword" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function toSetStatus(id, status) {
        var contentStr = "是否" ;
        status==0?contentStr+="停用？":contentStr+="启用？" ;

        art.dialog({
            title : '消息',
            id : "toSetStatus",
            width : 200,
            height : 80,
            icon : 'warning',
            content : contentStr,
            ok : function() {
                setStatus(id, status) ;
            },
            cancel : function() {
            }
        });
    }

    function setStatus(id, status) {
        var url = "${ctx}/my/user/" + id + "/setStatus" ;
        jQuery.ajax({
            type:"post",
            async: true,
            cache: false,
            url: url,
            data: {"status": status},
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
                            var requestUrl = "${ctx}/my/user/main" ;
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

    function toDelete(id) {
        art.dialog({
            title : '消息',
            id : "toDelete",
            width : 200,
            height : 80,
            icon : 'warning',
            content : '是否注销？',
            ok : function() {
                doDelete(id) ;
            },
            cancel : function() {
            }
        });
    }

    function doDelete(id) {
        var url = "${ctx}/my/user/" + id + "/delete" ;
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
                            var requestUrl = "${ctx}/my/user/main" ;
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
    
    function toDistribution(id) {
        var requestUrl = "${ctx}/my/user/" + id + "/toDistribution" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
</script>