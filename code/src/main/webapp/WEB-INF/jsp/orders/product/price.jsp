<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${jsPath}/calendar/calendar-price-jquery.min.css" />
<script type="text/javascript" src="${jsPath}/calendar/calendar-price-jquery.js"></script>
<style>
	#productAddForm input.checkbox {
		vertical-align: top;
		margin-right: 5px;
	}
</style>
<div class="row">
	<div class="container"></div>
</div>
<script type="text/javascript">
	var iscommiting = false;
	var _startDate='<fmt:formatDate value="${supplierProduct.validStartDate}" pattern="yyyy-MM-dd"/>';
	var _endDate='<fmt:formatDate value="${supplierProduct.validEndDate}" pattern="yyyy-MM-dd"/>';
	var zxCalendar;
	$(document).ready(function() {
		 var todayStr=_formatDate(new Date(),'yyyy-MM');
		 var isAfterToday=_compareDate(_startDate,todayStr);
		
		var priceData = loadCostPrice('${supplierProduct.id}',todayStr);
		if(priceData&&priceData.length<=0){
			priceData=[];
		}
		zxCalendar = $.CalendarPrice({
		  el: '.container',
		  startDate: _startDate,
		  endDate: _endDate,
		  data: priceData,
		  // 配置在日历中要显示的字段
		  show: [
			{
			  key: 'costPrice',
			  name: '￥'
			}
		  ],
		  monthChange: function (month) {
			var changeData=loadCostPrice('${supplierProduct.id}',month);
			zxCalendar.update(changeData);
		  },
		  error: function (err) {
			console.error(err.msg);
		  },
		  // 自定义颜色
		  style: {
			// 头部背景色
			headerBgColor: '#098cc2',
			// 头部文字颜色
			headerTextColor: '#fff',
			// 周一至周日背景色，及文字颜色
			weekBgColor: '#098cc2',
			weekTextColor: '#fff',
			// 周末背景色，及文字颜色
			weekendBgColor: '#098cc2',
			weekendTextColor: '#fff',
			// 有效日期颜色
			validDateTextColor: '#333',
			validDateBgColor: '#fff',
			validDateBorderColor: '#eee',
			// Hover
			validDateHoverBgColor: '#098cc2',
			validDateHoverTextColor: '#fff',
			// 无效日期颜色
			invalidDateTextColor: '#ccc',
			invalidDateBgColor: '#fff',
			invalidDateBorderColor: '#eee',
			// 底部背景颜色
			footerBgColor: '#fff',
		  }
		  // 点击有效的某一触发的回调函数
		  // 注意：配置了此参数，设置窗口无效，即不能针对日期做参数设置
		  // 返回每天的数据
		  ,everyday: function (dayData) {
				//console.log('点击某日，返回当天的数据');
				//console.log(dayData);
		  }
		  // 隐藏底部按钮（重置、确定、取消），前台使用该插件时，则需要隐藏底部按钮
		  ,hideFooterButton: true
		});
	
	});
	
	function loadCostPrice(sproductId,month){
		var resultData={}
		var url = "${ctx}/product/price/load/enterprise/cost/month" ;
		jQuery.ajax({
			type:"get",
			async: false,
			cache: false,
			url: url ,
			data:{'sproductId':sproductId,'month':month},
			dataType:"text",
			success:function(data) {
				var jsonObj={};
				try{
					jsonObj=JSON.parse(data);
				}catch(e){
					console.log(e);
				}
				if(data&&jsonObj) {
					resultData=jsonObj.data;
				} else {
					art.dialog({
						title : '消息',
						id : "costId",
						time: 3,
						icon : 'warning',
						width : 200,
						height : 80,
						 zIndex : 999999,
						content : '加载数据失败，请稍后再试'
					}) ;
				}
			} ,
			error : function(e){
				console.log(e);
				art.dialog({
						title : '消息',
						id : "costId",
						time: 3,
						icon : 'warning',
						width : 200,
						height : 80,
						 zIndex : 999999,
						content : '加载数据失败，请求稍后再试！'
					}) ;
			}
		});
		return resultData;
	}
	
	</script>