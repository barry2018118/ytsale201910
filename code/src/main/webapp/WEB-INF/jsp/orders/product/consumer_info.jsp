<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<h4>实名制信息</h4>
<c:forEach var="i" begin="1" end="${buyNum}" step="1">
    <div class="form-group">
	    <label class="control-label col-md-2"></label>
		<div class="col-md-4">
			<label class="control-label col-md-4">游玩人姓名：</label>
			<div class="col-md-8">
				<input class="form-control " placeholder="请输入姓名" type="text" name="consumerName" maxlength="12">
			</div>
		</div>
		<div class="col-md-4 ">
			<label class="control-label col-md-4">身份证号：</label>
			<div class="col-md-8">
				<input class="form-control t" placeholder="请输入身份证号" type="text" name="consumerCard" maxlength="18" onchange="checkName(this);">
			</div>
		</div>
	</div>
</c:forEach>
<div class="form-group">
	<label class="control-label col-md-2"></label>
	<div class="col-md-4">
		<label class="control-label col-md-4"></label>
		<div class="col-md-8 red-tag tag">
			<span hidden="true" for="consumerName" class="error"></span>
		</div>
	</div>
	<div class="col-md-4 ">
		<label class="control-label col-md-4"></label>
		<div class="col-md-8 red-tag tag">
			<span hidden="true" for="consumerCard" class="error"></span>
		</div>
	</div>
</div>