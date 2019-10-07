<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<%@taglib prefix="date"  uri="/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>


<div class="page-title ">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="historyThree()" >账号管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">详情</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="goBack()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>
<form method="post" class="form-horizontal"  id="editEnterpriseForm" enctype="multipart/form-data" >
<!-- 基本信息 start -->
<div class="row">
    <div class="col-lg-12">
        <div class="widget-container fluid-height">
            <div class="heading">
                <div class="pull-left">账号信息</div>
            </div>
            <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">登录账号：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="name"  name="name" value="${user.username }" maxlength="20" readonly="readonly" >
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="contacterName" name="contacterName" value="${user.name }" maxlength="20" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="contacterTel" name="contacterTel" value="${user.phone }" maxlength="11" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">所属机构：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="address"  name="address" value="${user.enterpriseName }" maxlength="20" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">已分配角色：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="address"  name="address" value="${user.title}" maxlength="20" readonly="readonly">
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>
<!-- 基本信息 End -->
<!--footer start-->
<div class="row" style="min-height:60px;">
<div class="form-group btn-form">
    <div class="col-md-12 text-center">
    </div>
</div>
</div>
</form>
<!--footer end-->
<script type="text/javascript">
	function goBack(){
		var requestUrl = "${ctx}/manage/user/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
</script>