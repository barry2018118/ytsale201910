<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>分销商预收款（查看和设置对下级分销商的预收款)</span>&nbsp;&gt;&nbsp;<span class="dq"></span>
	</h1>
	<!--列表上方索引 E-->
	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<label class="fl" style="line-height:30px; margin-left:10px;">&nbsp;&nbsp;</label>
		<input class="form-control fl datepicker" type="text" style="width:180px;" id="searchName" name="searchName" placeholder="分销商名称" />
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearchUser">
			<i class="icon-search"></i>搜索
		</button>
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="user_data_area"></div>

<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/capital/enterprise/storageMoney/distributor/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearchUser").click(function() {
            var requestUrl = "${ctx}/capital/enterprise/storageMoney/distributor/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
        }) ;
    }) ;
</script>
