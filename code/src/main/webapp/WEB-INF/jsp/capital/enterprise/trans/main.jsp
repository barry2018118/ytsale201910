<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<style>
	.page-title .tab-box{
		width:500px;
		height:36px;
		margin:0 auto;
	}
	.page-title .tab{
		width:160px;
		height:36px;
		line-height:36px;
		text-align:center;
		font-size:16px;
		border-radius:20px;
		display:inline-block;
		background:#fff;
		border:1px solid #00aae9;
		color:#00aae9;
		float:left;
		position:relative;
		text-decoration:none;
		z-index:2;
	}
	.page-title .tab-center{
		z-index:1;
		margin-left:-26px;
		border-radius:0;
		width:180px;
	}
	.page-title .tab-last{
		margin-left:-26px;
	}
	.page-title .tab-active{
		background:#00aae9;
		color:#fff;
		z-index:3;
	}
</style>
<title>交易资金--列表</title>
<div class="page-title " style="height:auto; ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>交易资金</span>&nbsp;&gt;&nbsp;<span class="dq">列表</span>
	</h1>
	<!--列表上方索引 E-->

	<div class="tab-box clear"  style="width:800px; margin:20px auto;">
		<a href="javascript:;" class="tab tab-active" id="view1" onclick="toAll();">全部</a>
		<a href="javascript:;" class="tab tab-center" id="view4" onclick="toXiaDan();">下单</a>
		<a href="javascript:;" class="tab tab-last" id="view5" onclick="toTuiKuan();">退款</a>
		<input type="hidden" id="actionType" name="actionType" value="100" />
	</div>

	<!--列表上方右侧按钮 S-->
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="user_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/capital/enterprise/trans/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
    }) ;

    function removeViews(){
        $("#user_data_area").empty() ;
        $("#view1").removeClass("tab-active") ;
        $("#view4").removeClass("tab-active") ;
        $("#view5").removeClass("tab-active") ;
	}

    function toAll() {

        removeViews();
        $("#actionType").val(100) ;
        $("#view1").addClass("tab-active") ;
        var requestUrl = "${ctx}/capital/enterprise/trans/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
    }

    function toXiaDan() {
        removeViews();
        $("#view4").addClass("tab-active") ;
        $("#actionType").val(3) ;
        var requestUrl = "${ctx}/capital/enterprise/trans/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
    }

    function toTuiKuan() {
        removeViews();
        $("#actionType").val(4) ;
        $("#view5").addClass("tab-active") ;
        var requestUrl = "${ctx}/capital/enterprise/trans/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
    }
</script>
