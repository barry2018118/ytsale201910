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
        <span>系统管理</span>&nbsp;&gt;&nbsp;<span><a href="javascript:;" onclick="historyThree()" >企业管理</a></span>&nbsp;&gt;&nbsp;<span class="dq">详情</span>
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
                <div class="pull-left">机构信息</div>
            </div>
            <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">机构名称：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="name"  name="name" value="${enterprise.name }" maxlength="20" readonly="readonly" >
                            </div>
                        </div>
                    <div class="col-md-3 red-tag tag"><font id="error" >*</font><span hidden="true" for="name"  class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">机构地址：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text" id="address"  name="address" value="${enterprise.address }" maxlength="20" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系人：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="contacterName" name="contacterName" value="${enterprise.contacterName }" maxlength="20" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterName" class="error"></span></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <input class="form-control" placeholder="" type="text"  id="contacterTel" name="contacterTel" value="${enterprise.contacterTel }" maxlength="11" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">*<span hidden="true" for="contacterTel" class="error"></span></div>
                    </div>
                    <div class="form-group">
	                     <label class="control-label col-md-2">是否启用：</label>
                        <div class="col-md-7">
                            <div class="rel">
                            	<c:if test="${enterprise.status==1 }">
                            	 <input class="form-control" placeholder="" value="是" type="text"  id="status" name="status"  maxlength="11" readonly="readonly">
                            	</c:if>
                            	<c:if test="${enterprise.status==0 }">
                            	 <input class="form-control" placeholder="" value="否" type="text"  id="status" name="status"  maxlength="11" readonly="readonly">
                            	</c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
	                    <label class="control-label col-md-2">机构简介：</label>
		                    <div class="col-md-7">
		                        <div class="rel">
		                            <textarea maxlength="200" id="introduction" name="introduction"  placeholder="机构简介"
		                             class="form-control" rows="5" readonly="readonly">${enterprise.introduction }</textarea>
		                        </div>
		                    </div>
	                     <div class="col-md-3 red-tag tag"><span hidden="true" for="introduction" class="error"></span></div>
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
<input  type="hidden" value="${enterprise.id }"  id="id"  name="id">
<input  type="hidden" value="${enterprise.domain }"  id="domain"  name="domain">
</form>
<!--footer end-->
<script type="text/javascript">
	function goBack(){
		var requestUrl = "${ctx}/manage/enterprise/main" ;
		var requestData = {start:'${param.start}'} ;
		cserpLoadPage(requestUrl, requestData, 'GET','mainContentDiv') ;
	}
</script>