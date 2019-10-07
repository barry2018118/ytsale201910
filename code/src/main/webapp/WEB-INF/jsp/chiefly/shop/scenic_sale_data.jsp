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

<div id="scenicSaleContainer" style="width: 100%; height: 300px;margin:0 auto;">
<input type="hidden" id="scenic_sale_categories" value="${scenicSaleChart.categories}"/>
<input type="hidden" id="scenic_sale_name" value="${scenicSaleChart.name}" />
<input type="hidden" id="scenic_sale_data" value="${scenicSaleChart.data}" />
<input type="hidden" id="hasChart" value="${hasChart}" />

<!-- 
<img src="${imagesPath}/chiefly/chart_1.jpg" alt="" style="width: 100%">
-->

<script type="text/javascript">
    $(document).ready(function() {
        var hasChart = $("#hasChart").val() ;
        if(hasChart == "yes") {
            var xData = $("#scenic_sale_categories").val().split(",") ;
	        var dataStr = $("#scenic_sale_data").val().split(",") ;
	        var dataValue = new Array() ;
	        for(var i=0; i<dataStr.length; i++) {
	            dataValue[i] = parseInt(dataStr[i]) ;
	        }
	        
	        var yData = [{
	            name: $("#scenic_sale_name").val(),
	            data: dataValue
	        }] ;
	        
	        var chart ;
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'scenicSaleContainer',
	                type: 'column'
	            },
	            title: {
	                text: '热门景区销售排行'
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
            $("#showScenicSaleDataArea").html("景区销售排行数据为空！");
        }
    }) ;
    
</script>