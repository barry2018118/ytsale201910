<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap/bootstrap-table.css" />
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

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
                url : '${ctx}/capital/platform/money/extract/toSearch',
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
                    title : '企业',
                    field : 'name',
                    align : 'left'
                }, {
                    title : '提现卡号',
                    field : 'bankCardId',
                    align : 'center',
                    formatter: function (value, row,index){
                        var _url = "${ctx}/capital/enterprise/bankCard/"+value+"/search" ;
                        var num = null;
                        $.ajax({
                            url: _url ,
                            type: "post",
                            dataType:"json",
                            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                            async: false,
                            beforeSend: function() {
                            },
                            success: function(data) {
                                if(data.flag) {
                                    $(data.message).each(function(i, item) {
                                        num = item.cardNo;
                                    }) ;
                                }
                            },
                            error:function(e) {
                            }
                        });
                        return num;
                    }
                }, {
                    title : '申请提现金额',
                    field : 'extractMoney',
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        if(value)
                            return "￥"+value;
                        if(!value)
                            return "￥0.00";
                    }
                }, {
                    title : '状态',
                    field : 'status',
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        var msg = "";
                        if(value == 0){
                            msg = "待审核";
                        }else if(value == 1){
                            msg = "已审核";
                        }else if(value == 2){
                            msg = "未通过";
                        }
                        return msg;
                    }
                }, {
                    title : '提现申请时间',
                    field : 'createTime',
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                    }
                }, {
                    title : '审核时间',
                    field : 'auditTime',
                    width : '10%',
                    align : 'center',
                    formatter: function (value, row, index) {
                        var time = null;
                        if(typeof value !== "undefined"){
                            time =  timestampToTime(value);
                        }
                        return time;
                    }
                }, {
                    title: '操作',
                    align : 'center',
                    width : '15%',
                    visible: true,
                    sortable: true,
                    formatter:function(value, row, index) {
                        var state = "";
                        if(row.status == 0){
                            state = '<a href="javascript:;" onclick="toState(\''+ row.id + '\');">审核</a>' ;
                        }
                        var edit = '<a href="javascript:;" onclick="toSearch(\''+ row.id + '\');">查看</a>' ;
                        return  state + "&nbsp;&nbsp" + edit;
                    }
                }]
            });
        };
        //检索
        oTableInit.queryParams = function(params) {
            var starttime = $("#starttime").val() ;
            var endtime = $("#endtime").val() ;
            var state = $("#state").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                start : starttime,
                end : endtime,
                state : state
            };
        };

        return oTableInit ;
    }

    function toState(id) {
        var requestUrl = "${ctx}/capital/platform/money/extract/" + id + "/toAdd" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    function toSearch(id) {
        var requestUrl = "${ctx}/capital/platform/money/extract/" + id + "/toCard" ;
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