<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title>我的供应商--列表</title>
<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>供应商列表</span>
    </h1>
    <!--列表上方索引 E-->

    <!--列表上方右侧按钮 S-->
    <div class="fr">
        <label class="fl" style="line-height:30px; margin-left:10px;">供应商名称：</label>
        <input class="form-control fl" type="text" style="width:180px;" id="searchName" name="searchName" placeholder="供应商名称" />
        <button class="btn btn-primary fl btn-top" type="submit" id="toSearchSupplier">
            <i class="icon-search"></i>搜索
        </button>
    </div>
    <!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="platformEnterprise_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/finance/enterprise/parent/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'platformEnterprise_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearchSupplier").click(function() {
            var requestUrl = "${ctx}/finance/enterprise/parent/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'platformEnterprise_data_area') ;
        }) ;

    }) ;
    
</script>