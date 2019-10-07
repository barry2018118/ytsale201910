<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<style>
.highcharts-subtitle tspan, .highcharts-title tspan{
    font-size:20px;
    font-weight:bold;
}
.highcharts-subtitle tspan{
    font-size:18px;
}
</style>

<div id="distributionSaleContainer" style="width: 100%; height: 300px;margin:0 auto;">
<input type="hidden" id="distribution_sale_categories" value="${distributionSaleChart.categories}"/>
<input type="hidden" id="distribution_sale_name" value="${distributionSaleChart.name}" />
<input type="hidden" id="distribution_sale_data" value="${distributionSaleChart.data}" />
<input type="hidden" id="distributionHasChart" value="${distributionHasChart}" />

<script type="text/javascript">
    $(document).ready(function() {
        var distributionHasChart = $("#distributionHasChart").val() ;
        if(distributionHasChart == "yes") {
            var xData = $("#distribution_sale_categories").val().split(",") ;
	        var dataStr = $("#distribution_sale_data").val().split(",") ;
	        var dataValue = new Array() ;
	        for(var i=0; i<dataStr.length; i++) {
	            dataValue[i] = parseInt(dataStr[i]) ;
	        }
	        
	        var yData = [{
	            name: $("#distribution_sale_name").val(),
	            data: dataValue
	        }] ;
	        
	        var chart ;
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'distributionSaleContainer',
	                type: 'column'
	            },
	            title: {
	                text: '商户销售排行前十名'
	            },
	            subtitle: {
	                text: ''
	            },
	            xAxis: {
	                categories: xData,
	                crosshair: true
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: '销售量',
	                    rotation: 0
	                },
                    labels: {
                       step: 1
                    }
	            },
	            /* tooltip: {
	                // head + 每个 point + footer 拼接成完整的 table
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} 张</b></td></tr>',
	                footerFormat: '</table>',
	                shared: true,
	                useHTML: true
	            }, */
	            plotOptions: {
	                column: {
	                    borderWidth: 0
	                }
	            },
	            series: yData
	       });
        } else {
            $("#showDistributionSaleDataArea").html("商户销售排行数据为空！");
        }
    }) ;
    
</script>