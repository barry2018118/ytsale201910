<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<title></title>
<div class="page-title ">
      <!--列表上方索引 S-->
      <h1 class="fl">
          <span>系统管理</span>&nbsp;&gt;&nbsp;<span class="dq">账号管理</span>&nbsp;&gt;&nbsp;<span class="dq">用户管理</span>
      </h1>
      <!--列表上方索引 E-->
      <!--列表上方右侧按钮 S-->
      <div class="fr" >
          <input class="form-control fl"  type="text" style="width:180px;" name="searchN" placeholder="用户名">
          <button class="btn btn-primary fl btn-top" type="submit" id="sub1"><i class="icon-search"></i>搜索</button>
          <a href="javascript:;">
              <button class="btn btn-warning fl btn-top" type="submit" id="sub2"><i class="icon-plus"></i>新建</button>
          </a>
      </div>
      <!--列表上方右侧按钮 E-->
  </div>
 <!-- 具体列表 -->
  <div id = "main_list"></div>
<script type="text/javascript">
	$(document).ready(function() {
		var requestUrl = "${ctx}/manage/user/list" ;
		var requestData = {} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'main_list') ;
		$("#sub1").click(function(){
			var requestUrl = "${ctx }/manage/user/list" ;
			var requestData = {"name":$("input[name='searchN']").val()} ;
			cserpLoadPage(requestUrl, requestData, 'GET', 'main_list') ;
		}); 
		$("#sub2").click(function(){
			var requestUrl = "${ctx }/manage/user/toAdd" ;
			var requestData = {"pid":0,start:'${param.start}'} ;
			cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
		}); 
	}) ;
	function next(page){
		var requestUrl = "${ctx}/manage/user/list" ;
		var requestData = {"start":page,"name":$("input[name='searchN']").val()} ;
		cserpLoadPage(requestUrl, requestData, 'GET', 'main_list') ;
	}
</script>