<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!--首页内容-->
<div class="page-title ">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span class="dq">查询订单码</span>
	</h1>
	<!--列表上方索引 E-->

	<!--列表上方右侧按钮 S-->
	<div class="fr"></div>
	<!--列表上方右侧按钮 E-->
</div>

<div class="row">
	<div class="col-md-12">
		<div class="widget-container fluid-height clearfix">
			<div class="heading">
				<div class="pull-left">查询订单码</div>
				<div class="pull-right fresh">
					<i class="icon-refresh"></i>刷新
				</div>
			</div>
			<div class="widget-content padded ">
				<form class="form-horizontal" style="padding-top: 40px;">
					<div class="form-group">
						<div class="col-md-4"></div>

						<div class="col-md-4">
							<input class="form-control" type="text" value=""
								placeholder="请输入订单码">
						</div>
						<div class="col-md-4"></div>
					</div>
				</form>

				<div class="row" style="margin:30px 0 40px;">
					<div class="form-group">
						<div class="col-md-12 text-center">
							<a href="javascript:;">
								<button class="btn btn-footer btn-primary" type="submit"
									id="next">查 询</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>