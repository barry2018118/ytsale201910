<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap/bootstrap-table.css" />
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>
<title>商品组--设置商品</title>
<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>商品组</span>&nbsp;&gt;&nbsp;<span class="dq">设置商品</span>&nbsp;&gt;&nbsp;<span class="dq">添加商品</span>
    </h1>
    <!--列表上方索引 E-->

    <!--列表上方右侧按钮 S-->
    <div class="fr">
        <input type="hidden" id="saleGroupId" value="${saleGroupId}">
        <label class="fl" style="line-height:30px; margin-left:10px;">商品名称&nbsp;&nbsp;</label>
        <input class="form-control fl" type="text" style="width:180px;" id="productName" name="productName" placeholder="请输入商品名称" />
        <label class="fl" style="line-height:30px; margin-left:10px;">商品编号&nbsp;&nbsp;</label>
        <input class="form-control fl" type="text" style="width:180px;" id="productNo" name="productNo" placeholder="请输入商品编号" />
        <label class="fl" style="line-height:30px; margin-left:10px;">商品类别&nbsp;&nbsp;</label>
        <select class="form-control fl" id="productategory" name="productategory" style="width:90px;">
            <option value="" selected="selected">全部</option>
            <c:forEach items="${ctategory}" var="role"  varStatus="s">
                <option value="${role.id}">${role.name}</option>
            </c:forEach>
        </select>
        <label class="fl" style="line-height:30px; margin-left:10px;">商品状态&nbsp;&nbsp;</label>
        <select class="form-control fl" id="productStatus" name="productStatus" style="width:90px;">
            <option value="" selected="selected">全部</option>
            <option value="0">停售</option>
            <option value="1">启售</option>
        </select>
        <label class="fl" style="line-height:30px; margin-left:10px;">商品来源&nbsp;&nbsp;</label>
        <select class="form-control fl" id="productSource" name="productSource" style="width:90px;">
            <option value="" selected="selected">全部</option>
            <option value="1">系统内商品</option>
            <option value="2">第三方商品</option>
        </select>
        <label class="fl" style="line-height:30px; margin-left:10px;">商品归属&nbsp;&nbsp;</label>
        <select class="form-control fl" id="productAffiliation" name="productAffiliation" style="width:90px;">
            <option value="" selected="selected">全部</option>
            <option value="0">分销商品</option>
            <option value="1">自主商品</option>
        </select>
        <button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
            <i class="icon-search"></i>搜索
        </button>
        <!--列表上方右侧按钮 S-->
        <button class="btn btn-primary fr btn-top" type="button" id="back" name="back" onclick="toProductCategoryList();"><i class="icon-rotate-left"></i>返回</button>
        <!--列表上方右侧按钮 E-->
    </div>
    <!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->

<form id="productEnterpriseAddForm" method="post" class="form-horizontal">
    <div class="row">
        <div class="widget-content">
            <div id="main_list">
                <table id="table" class="table table-hover"></table>
            </div>
        </div>
    </div>

    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <button class="btn btn-footer btn-cancel" onclick="toProductCategoryList();">取消</button>
                <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">

    $(document).ready(function() {
        var oTable2 = new TableInit() ;
        oTable2.Init() ;
        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            $('#table').bootstrapTable('destroy');
            oTable2 = new TableInit() ;
            oTable2.Init() ;
        }) ;
    }) ;

    var TableInit = function() {
        var oTableInit2 = new Object();
        oTableInit2.Init = function() {
            $('#table').bootstrapTable({
                // contentType: "application/x-www-form-urlencoded",
                url : '${ctx}/product/salegroup/product/addSearch',
                method : 'get',
                queryParams : oTableInit2.queryParams,
                queryParamsType : '',
                toolbar : '#toolbar',
                striped : true,
                cache : false,
                pagination : true,
                sidePagination : "client",
                pageNumber : 1,
                pageSize : 1000,
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
                    title : '<input type="button" value="全选" class="btn" id="selectAll" class="form-control" onclick="chackboxOnclick();">',
                    align : 'center',
                    width : '10%',
                    formatter: function (value, row, index) {
                        if(row.isExist == "1") {
                            return "-" ;
                        } else {
                            return " <label class=\"checkbox\"><input type=\"checkbox\" class=\"form-control checkboxs\" name=\"box[]\" value=\""+row.productId+"\"><span></span></lable> " ;
                        }
                    }
                }, {
                    title : '序号',
                    align : 'center',
                    width : '10%',
                    formatter: function (value, row, index) {
                        return index+1 ;
                    }
                }, {
                    title : '商品编号',
                    field : 'no',
                    width : '10%'
                }, {
                    title : '商品名称',
                    field : 'name',
                    width : '10%'
                }, {
                    title : '市场价',
                    field : 'marketPrice',
                    width : '10%'
                }, {
                    title : '商品类别',
                    field : 'categoryId',
                    width : '10%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "常规代理";
                        if(value == 2)
                            return "时段包票" ;
                        if(value == 3)
                            return "第三方票务" ;
                    }
                }, {
                    title : '商品状态',
                    field : 'status',
                    width : '10%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "启售" ;
                        if(value == 0)
                            return "停售" ;
                    }
                }, {
                    title : '商品来源',
                    field : 'thirdPlatformId',
                    width : '10%',
                    formatter: function (value, row, index) {
                        if(value != null && value != '')
                            return "第三方商品" ;
                        else
                            return "系统内商品" ;
                    }
                }, {
                    title : '商品归属',
                    field : 'isSupplier',
                    width : '10%',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return "自主商品" ;
                        if(value == 0)
                            return "分销商品" ;
                    }
                }]
            });
        };
        oTableInit2.queryParams = function(params) {
            var saleGroupId = $("#saleGroupId").val() ;
            var productNo = $("#productNo").val() ;
            var productName = $("#productName").val() ;
            var productategory = $("#productategory").val() ;
            var productStatus = $("#productStatus").val() ;
            var productSource = $("#productSource").val() ;
            var productAffiliation = $("#productAffiliation").val() ;
            return {
                pageNumber : params.pageNumber,
                pageSize : params.pageSize,
                saleGroupId : saleGroupId,
                productNo : productNo,
                productName : productName,
                productategory : productategory,
                productStatus : productStatus,
                productSource : productSource,
                productAffiliation : productAffiliation
            };
        };
        return oTableInit2 ;
    }
    var iscommiting = false;
    $(function() {
        $("#productEnterpriseAddForm").validate({
            rules: {
            },
            messages: {
            },
            submitHandler: function(form) {
                if(iscommiting){
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type: "post",
                    url: "${ctx}/product/salegroup/product/${saleGroupId}/add",
                    dataType: "json",
                    beforeSubmit : function(formData, jqForm, options) {
                    },
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout: 120000,
                    success: function(data) {
                        if(data.flag) {
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'succeed',
                                content: data.message,
                                close:function() {
                                    toProductCategoryList() ;
                                }
                            }) ;
                        } else {
                            iscommiting = false ;
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'warning',
                                content: data.message,
                                close:function() {
                                }
                            });
                        }
                    },
                    error:function(e) {
                        iscommiting = false ;
                    }
                }) ;
            }
        }) ;
    }) ;

    function toProductCategoryList() {
        var requestUrl = "${ctx}/product/salegroup/product/${saleGroupId}/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    var isChack = false;
    function chackboxOnclick() {
        if(isChack){
            $("#table :checkbox,#checkboxs").prop("checked", false);
            isChack = false;
        }else if(!isChack){
            $("#table :checkbox,#checkboxs").prop("checked", true);
            isChack = true;
        }
    }
</script>
