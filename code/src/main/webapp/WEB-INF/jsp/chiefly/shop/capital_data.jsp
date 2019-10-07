<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="form-horizontal" style="height: 195px; overflow: hidden; padding-top: 10px;">
	<div class="form-group">
		<label class="control-label col-md-2">平台余额：</label>
		<div class="col-md-3">
			<div class="red-tag tag">￥${capitalInfo.totalMoney}</div>
		</div>
	</div>
    <!-- 
		<div class="form-group">
			<label class="control-label col-md-2">可提现额：</label>
			<div class="col-md-3">
				<div class="tag">￥${capitalInfo.usableMoney}</div>
			</div>
			<div class="col-md-3 tag"></div>
		</div>
    -->
	<div class="form-group">
		<label class="control-label col-md-2">累计充值：</label>
		<div class="col-md-3">
			<div class="tag">￥${recharge.actualMoney}</div>
		</div>
		<div class="col-md-3 tag"></div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">累计提现：</label>
		<div class="col-md-3">
			<div class="tag">￥${extract.extractMoney}</div>
		</div>
		<div class="col-md-3 tag"></div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {}) ;
</script>