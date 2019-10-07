<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span></span>
	</h1>
	<!--列表上方索引 E-->
	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<label class="fl" style="line-height:30px; margin-left:10px;">订单编号&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:150px;" id="productNo" name="productNo" placeholder="请输入订单编号" />
		<label class="fl" style="line-height:30px; margin-left:10px;">商品名称&nbsp;&nbsp;</label>
		<input class="form-control fl" type="text" style="width:150px;" id="productName" name="productName" placeholder="请输入商品名称" />
		<label class="fl" style="line-height:30px; margin-left:10px;">下单人&nbsp;&nbsp;</label>
        <input class="form-control fl" type="text" style="width:100px;" id="linkMan" name="linkMan" placeholder="请输入下单人" maxlength="10" />
        <label class="fl" style="line-height:30px; margin-left:10px;">手机号&nbsp;&nbsp;</label>
        <input class="form-control fl" type="text" style="width:120px;" id="linkPhone" name="linkPhone" placeholder="请输入手机号" maxlength="11" />
		<label class="fl" style="line-height:30px; margin-left:10px;">下单时间&nbsp;&nbsp;</label>
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="starttime" name="starttime" placeholder="开始时间" />
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="endtime" name="endtime" placeholder="结束时间" />
		<label class="fl" style="line-height:30px; margin-left:10px;">订单状态&nbsp;&nbsp;</label>
		<select class="form-control fl" id="state" name="state" style="width:90px;">
			<option value="" selected="selected">全部</option>
			<option value="1" >等待支付</option>
			<option value="2" >等待发码</option>
			<option value="3" >已全部退款</option>
			<option value="4" >已成功消费</option>
			<option value="5" >全部消费部分退款</option>
			<option value="6" >全部消费全部退款</option>
			<option value="7" >部分消费部分退款</option>
			<option value="10" >未消费</option>
			<option value="11" >未消费部分退款</option>
			<option value="12" >部分消费未退款</option>
			<option value="13" >部分消费部分退款</option>
			<option value="14" >未消费退款审核中</option>
			<option value="15" >已消费退款审核中</option>
			<option value="16" >第三方发码失败</option>
		</select>
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
			<i class="icon-search"></i>搜索
		</button>
	</div>
	<!--列表上方右侧按钮 E-->
</div>

<!-- 列表 -->
<div id="user_data_area"></div>


<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true, //选中之后自动隐藏日期选择框
            clearBtn: true, //清除按钮
            todayBtn: true, //今日按钮
            format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/orders/my/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl =  "${ctx}/orders/my/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
        }) ;
    }) ;
</script>
