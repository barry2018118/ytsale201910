<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="widget-container fluid-height clearfix">
    <div class="heading">
        <div class="pull-left">资金区</div>
        <div class="pull-right">
            <select class="form-control" style=" margin-top: -6px;" id="capitalDataFlag" onchange="getCapitalData()">
                <option value="1">今日 </option>
                <option value="2">七天 </option>
                <option value="3">本月 </option>
            </select>
        </div>
    </div>
    
    <div class="widget-content padded" id="showCapitalDataArea"></div>
</div>


<script type="text/javascript">
    $(document).ready(function() {
        getCapitalData() ;
    }) ;
    
    function getCapitalData() {
        var capitalDataFlag = $("#capitalDataFlag").val() ;
        
        var requestUrl = "${ctx}/chiefly/shop/getCapital" ;
        var requestData = {"capitalDataFlag":capitalDataFlag} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'showCapitalDataArea') ;
    }
</script>