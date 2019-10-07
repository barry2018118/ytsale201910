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
		<input class="form-control fl" type="text" style="width:110px;" id="productNo" name="productNo" placeholder="订单编号" />
		<label class="fl" style="line-height:30px; margin-left:10px;">商品名称&nbsp;&nbsp;</label>
		<input class="form-control fl " type="text" style="width:110px;" id="productName" name="productName" placeholder="商品名称" />
		<label class="fl" style="line-height:30px; margin-left:10px;">退款时间&nbsp;&nbsp;</label>
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="starttime" name="starttime" placeholder="开始时间" />
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="endtime" name="endtime" placeholder="截止时间" />
		<label class="fl" style="line-height:30px; margin-left:10px;">审核状态&nbsp;&nbsp;</label>
		<select class="form-control fl" id="state" name="state" style="width:90px;">
			<option value="" selected="selected">全部</option>
			<option value="0">待审核</option>
            <option value="1">审核通过</option>
            <option value="2">审核不通过</option>
		</select>
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
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        // 页面初始化完成加载list请求
        var requestUrl = "${ctx}/orders/refund/other/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearchUser").click(function() {
            var requestUrl = "${ctx}/orders/refund/other/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
        }) ;
    }) ;
</script>
