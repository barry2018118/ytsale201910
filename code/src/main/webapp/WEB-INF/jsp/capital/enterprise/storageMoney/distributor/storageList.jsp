<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap/bootstrap-table.css" />
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>预收款设置记录</span>&nbsp;&gt;&nbsp;<span class="dq"></span>
    </h1>
    <!--列表上方索引 E-->
    
    <!--列表上方右侧按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toOperationList()">
        <i class="icon-rotate-left"></i>返回
    </button>
    <!--列表上方右侧按钮 E-->
</div>

<div class="row">
    <div class="widget-content">
        <div id="main_list">
            <table id="user_table_details_card" class="table table-hover"></table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        var oTable2 = new TableInit() ;
        oTable2.Init() ;
    }) ;

    var TableInit = function() {
        var oTableInitCard = new Object();
        oTableInitCard.Init = function() {
            $('#user_table_details_card').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",d
                url : '${ctx}/capital/enterprise/storageMoney/distributor/${childId}/StorageList',
                method : 'get',
                queryParams : oTableInitCard.queryParams,
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
                    visible: false
                }, {
                    title : '序号',
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '变动时间',
                    field : 'createTime',
                    align : 'center',
                    width : '8%',
                    formatter: function (value, row, index) {
                        return timestampToTime(value) ;
                    }
                }, {
                    title : '变动前金额(元)',
                    field : 'beforeMoney',
                    width : '8%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                },{
                    title : '本次变动金额(元)',
                    field : 'storageMoney',
                    align : 'center',
                    width : '8%',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                }, {
                    title : '变动后金额(元)',
                    field : 'afterMoney',
                    width : '8%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                }]
            });
        };
        return oTableInitCard ;
    }

    function toOperationList() {
        var requestUrl = "${ctx}/capital/enterprise/storageMoney/tomain" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
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