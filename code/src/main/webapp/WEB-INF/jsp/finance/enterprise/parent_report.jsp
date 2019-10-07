<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<style>
.load-path{
	height:30px;
	line-height:30px;
	text-align:center;
	font-size:28px;
	font-weight:bold;
}
</style>

<link rel="stylesheet" href="${cssPath}/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath}/locales/bootstrap-datepicker.zh-CN.js"></script>

<div class="widget-content padded clearfix" >
    <input type="hidden" id="parentId" value="${enterprise.id}">
	<div class="filter1">
		<form class="form-horizontal" style="margin-bottom:0;">
			<div id="summaryData" style="display:block;">
				<div class="form-group" style="line-height:30px;">
					<label class="control-label col-md-2">供应商：</label>
					<div class="input-group fl col-md-6" style="margin-top:7px;">
					   <b>${enterprise.name}</b> 
					</div>
				</div>
				<div class="form-group" style="line-height:30px;">
					<label class="control-label col-md-2">统计方式：</label>
					<select class="form-control fl col-md-2" id="searchMethod" style="width:200px; margin-top:7px;" name="searchMethod">
						<option value="1" selected="selected">按销售</option>
						<option value="2" >按消费</option>
					</select>
				</div>
				<div class="form-group" style="line-height:30px;">
					<label class="control-label col-md-2">时间范围：</label>
					<div class="input-group date date-picker fl col-md-4" style="margin-top:7px;">
						<input class="form-control" type="text" id="startDate" name="startDate" value="${startDate}" />
						<span class="input-group-addon" style="padding: 3px 12px; width:15px"><i class="icon-calendar"></i></span>
					</div>
					<div class="input-group date date-picker fl col-md-4" style="margin-top:7px;">
						<input class="form-control" type="text" id="endDate" name="endDate" value="${endDate}" />
						<span class="input-group-addon" style="padding: 3px 12px; width:15px"><i class="icon-calendar"></i></span>
					</div>
					<label class="control-label" style="margin-left:20px;" id="showLastMoney"></label>
				</div>
				<div class="form-group" style="text-align:center;">
					<button class="btn btn-warning btn-top" type="button" onclick="exportReport()" style="margin-top:5px;margin-bottom:5px;">
						<i class="icon-download-alt"></i>导出
					</button>
				</div>
			</div>
		</form>
	</div>
</div>
 
<div id ="load" class="load-path" style="display: none">
	<label style="line-height:30px; " id="msg"></label>
</div>
<div id = "path" class="load-path" style="display: none;">
  	<label style="line-height:30px; " id="fileUrl"></label>
  	<a href='javascript:;' id="down" >【下载】</a>
</div>
 
<script type="text/javascript">
    var showResourcePath = "http://49.4.6.218:8889/report/" ;
	$(document).ready(function() {
		$('.date-picker').datepicker({
	        language: 'zh-CN',
	        autoclose: true,
	        todayHighlight: true
	    }) ;
	}) ;
	
	function exportReport() {
        var parentId = $("#parentId").val() ;
        var searchMethod = $("#searchMethod").val() ;
        var startDate = $("#startDate").val() ;
        var endDate = $("#endDate").val() ;
        
        var url = "${ctx}/finance/enterprise/parent/" + parentId + "/export" ;
        var param = {"searchMethod":searchMethod, "startDate":startDate, "endDate":endDate} ;
        
        $.ajax({
            url: url ,
            data: param, 
            type: 'GET',
            dataType:"text",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            timeout:120000,
            beforeSend: function() {
                $("#path").hide();
                $("#load").show() ;
                $("#msg").html("正在导出。。。。。。") ;
            },
            success: function(data) {
                var json = eval("("+data+")");
                if(json.code==1){
                    $("#fileUrl").html("") ;
                    $("#path").show() ;
                    $("#load").hide() ;
                    $("#down").prop("href", showResourcePath + json.filepath) ;
                    $("#down").prop("download",json.fileName) ;
                }else{
                    $("#path").hide() ;
                    $("#load").show() ;
                    $("#msg").html(json.info) ;
                }
            },
            error:function(e) {
                $("#path").show() ;
                $("#load").hide();
                $("#fileUrl").html("导出失败，请重新加载！");
            }
        })
    }
	
</script>