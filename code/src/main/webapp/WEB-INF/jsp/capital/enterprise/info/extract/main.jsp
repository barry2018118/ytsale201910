<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>

<title>提现--提现列表</title>
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>提现</span>&nbsp;&gt;&nbsp;<span class="dq">提现列表</span>
	</h1>
	<!--列表上方索引 E-->
	<!--列表上方右侧按钮 S-->
	<div class="fr">
		<label class="fl" style="line-height:30px; margin-left:10px;">提现时间&nbsp;&nbsp;</label>
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="starttime" name="starttime" placeholder="开始时间" />
		<input class="form-control fl datepicker" type="text" style="width:110px;" id="endtime" name="endtime" placeholder="截止时间" />
		<label class="fl" style="line-height:30px; margin-left:10px;">提现状态&nbsp;&nbsp;</label>
		<select class="form-control fl" id="state" name="state" style="width:90px;">
			<option value="" selected="selected">全部</option>
			<option value="0">待审核</option>
			<option value="1">已审核</option>
			<option value="2">未通过</option>
		</select>
		<button class="btn btn-primary fl btn-top" type="submit" id="toSearchUser">
			<i class="icon-search"></i>搜索
		</button>
		<a href="javascript:;">
			<button class="btn btn-warning fl btn-top" type="submit" id="toAddUser">
				<i class="icon-plus"></i>提现
			</button>
		</a>
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
        var requestUrl = "${ctx}/capital/enterprise/info/extract/list" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;

        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearchUser").click(function() {
            var requestUrl = "${ctx}/capital/enterprise/info/extract/list" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'user_data_area') ;
        }) ;

        // toAdd按钮绑定事件，点击后进入新建页
        $("#toAddUser").click(function() {
            var requestUrl = "${ctx}/capital/enterprise/info/extract/toAdd" ;
            var requestData = {} ;
            cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
        }) ;
    }) ;
</script>
