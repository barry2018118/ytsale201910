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
    .page-title .tab-last{
        margin-left:-26px;
    }
    .page-title .tab-active{
        background:#00aae9;
        color:#fff;
        z-index:3;
    }
</style>
<title>退款列表</title>

<div class="page-title " style="height:auto; " >
    <div class="tab-box clear">
        <a href="javascript:;" class="tab " id="view" onclick="toSale();">我申请的退款</a>
        <a href="javascript:;" class="tab tab-last tab-active" id="views" onclick="toStock();">待审核的退款</a>
    </div>
    <!-- tab tab-last  -->
</div>


<div id="chart_middle"></div>
<script type="text/javascript">
    function toSale() {
        $("#chart_middle").empty() ;
        $("#view").removeClass("tab-active") ;
        $("#views").removeClass("tab-active") ;
        $("#view").addClass("tab-active") ;
        var requestUrl ="/orders/refund/my/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'chart_middle') ;
    }

    function toStock() {
        $("#chart_middle").empty();
        $("#view").removeClass("tab-active") ;
        $("#views").removeClass("tab-active") ;
        $("#views").addClass("tab-active") ;
        var requestUrl = "/orders/refund/other/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'chart_middle') ;
    }

    $(document).ready(function() {
        var requestUrl = "/orders/refund/other/main" ;
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'chart_middle');
    }) ;

</script>