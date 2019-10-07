<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>


<!--首页内容-->
<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span class="dq">首页</span>
    </h1>
    <!--列表上方索引 E-->

    <!--列表上方右侧按钮 S-->
    <div class="fr"></div>
    <!--列表上方右侧按钮 E-->
</div>

<div class="row">
    <!--start 公告区-->
    <div class="col-md-6">
        <div class="widget-container fluid-height clearfix">
            <div class="heading">
                <div class="pull-left">公告区</div>
                <div class="pull-right fresh" onclick="toNoticePage()">
                                                更多 <i class="icon-angle-right"></i>
                </div>
            </div>
            <div class="widget-content" id="noticeShowArea"></div>
        </div>
    </div>
    <!--end 公告区-->
    
    <!--start 资金区 -->
    <div class="col-md-6" id="capitalShowArea"></div>
    <!--end 资金区 -->
</div>

<!--start 热门景区销售排行前十名-->
<div class="row">
    <div class="col-md-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">热门景区销售排行前十名</div>
                <div class="pull-right">
                    <select class="form-control" style=" margin-top: -6px;" id="scenicSaleDataFlag" onchange="getScenicSaleData()">
		                <option value="1">今日 </option>
		                <option value="2">七天 </option>
		                <option value="3">本月 </option>
		            </select>
                </div>
            </div>
            <div class="widget-content padded text-center" id="showScenicSaleDataArea"></div>
        </div>
    </div>
</div>
<!--end 热门景区销售排行前十名-->

<!--start 商户销售排行前十名-->
<div class="row">
    <div class="col-md-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">商户销售排行前十名</div>
                <div class="pull-right">
                    <select class="form-control" style=" margin-top: -6px;" id="distributionSaleDataFlag" onchange="getDistributionSaleData()">
                        <option value="1">今日 </option>
                        <option value="2">七天 </option>
                        <option value="3">本月 </option>
                    </select>
                </div>
            </div>
            <div class="widget-content padded text-center" id="showDistributionSaleDataArea"></div>
        </div>
    </div>

</div>
<!--start 商户销售排行前十名-->

<!--start OTA销售排行前五名
<div class="row">
    <div class="col-md-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">OTA销售排行前五名</div>
                <div class="pull-right">
                    <select class="form-control" style=" margin-top: -6px;">
                        <option value="Category 1">当天 </option>
                        <option value="Category 2">七天 </option>
                        <option value="Category 3">本月 </option>
                    </select>
                </div>
            </div>
            <div class="widget-content padded text-center">
                <img src="${imagesPath}/chiefly/chart_3.jpg" alt="" style="width: 100%">
            </div>
        </div>
    </div>
</div>
<!--start OTA销售排行前五名-->

<!--公告弹出框 start-->
<div class="modal fade" id="noticeModal"></div>


<script type="text/javascript">
    $(document).ready(function() {
        // 获取公告信息
        var requestUrl = "${ctx}/chiefly/shop/getNotice" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'noticeShowArea') ;
        
        // 获取公告信息
        var requestUrl = "${ctx}/chiefly/shop/toCapital" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'capitalShowArea') ;
        
        // 热门景区销售排行前十名
        getScenicSaleData();
        
        // 商户销售排行前十名
        getDistributionSaleData();
        
        // OTA销售排行前五名
        
    }) ;
    
    function toNoticePage() {
        // 获取公告信息
        var requestUrl = "${ctx}/info/notice/main" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function getScenicSaleData() {
        var scenicSaleDataFlag = $("#scenicSaleDataFlag").val() ;
        
        var requestUrl = "${ctx}/chiefly/shop/getScenicSaleChart" ;
        var requestData = {"scenicSaleDataFlag":scenicSaleDataFlag} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'showScenicSaleDataArea') ;
    }
    
    function getDistributionSaleData() {
        var distributionSaleDataFlag = $("#distributionSaleDataFlag").val() ;
        
        var requestUrl = "${ctx}/chiefly/shop/getDistributionSaleChart" ;
        var requestData = {"distributionSaleDataFlag":distributionSaleDataFlag} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'showDistributionSaleDataArea') ;
    }
    
</script>