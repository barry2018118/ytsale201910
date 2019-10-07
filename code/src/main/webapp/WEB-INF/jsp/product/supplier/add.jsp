<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<style>
	#productAddForm input.checkbox {
		vertical-align: top;
		margin-right: 5px;
	}
</style>
<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
	<!--列表上方索引 S-->
	<h1 class="fl">
		<span>商品</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
	</h1>
	<!--列表上方索引 E-->
	<!--返回按钮 S-->
	<button class="btn btn-primary fr btn-top" type="button" onclick="toProductCategoryList()"><i class="icon-rotate-left"></i>返回</button>
	<!--返回按钮 E-->
</div>

<form id="productAddForm" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="row">
		<div class="col-lg-12">
			<div class="widget-container fluid-height">
				<div class="heading">
					<div class="pull-left">商品</div>
				</div>
				<div class="widget-content padded">
					<h4>商品基本信息</h4>
					<div class="form-group">
						<label class="control-label col-md-2">商品类别：</label>
						<div class="col-md-3">
							<div class="rel">
								<select class="form-control" id="categoryId" name="categoryId">
									<option value="" selected="selected">请选择</option>
									<c:forEach items="${ctategory}" var="ctategory" varStatus="s">
										<option value="${ctategory.id}">${ctategory.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="categoryId" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">商品名称：</label>
						<div class="col-md-3">
							<div class="rel">
								<input class="form-control" placeholder="请输入商品名称" type="text" id="name" name="name" maxlength="30">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="name" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">市场价：</label>
						<div class="col-md-3">
							<div class="rel">
								<input class="form-control" placeholder="请输入市场价" type="text" id="marketPrice" name="marketPrice" maxlength="10">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="marketPrice" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">限售价：</label>
						<div class="col-md-3">
							<div class="rel">
								<input class="form-control" placeholder="请输入限售价" type="text" id="limitPrice" name="limitPrice" maxlength="10">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="limitPrice" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">所属景区：</label>
						<div class="col-md-3">
							<div class="rel">
								<a data-toggle="modal" href="#myModal4">
									<input type="button" id="selectScenic" name="selectScenic" value="选择景区" class="btn btn-warning btn-top" onclick="exportAll()">
								</a>
								<input type="hidden" id="scenicId" name="scenicId">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="selectScenic" class="error"></span>
						</div>
					</div>
					<div class="form-group" hidden>
						<label class="control-label col-md-2">省份：</label>
						<div class="col-md-3">
							<div class="rel">
								<input type="text" id="provinceId" name="provinceId">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="provinceId" class="error"></span>
						</div>
					</div>
					<div class="form-group" hidden>
						<label class="control-label col-md-2">城市：</label>
						<div class="col-md-3">
							<div class="rel">
								<input type="text" id="cityId" name="cityId">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="cityId" class="error"></span>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-2">费用包含：</label>
						<div class="col-md-5">
							<div class="rel">
								<textarea maxlength="300" id="costInside" name="costInside" placeholder="请输入费用包含" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							<span hidden="true" for="costInside" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">费用不包含：</label>
						<div class="col-md-5">
							<div class="rel">
								<textarea maxlength="300" id="costOutside" name="costOutside" placeholder="请输入费用不包含" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							<span hidden="true" for="costOutside" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">产品图片：</label>
						<div class="col-md-3">
							<div class="rel">
								<input class="form-control" placeholder="请上传产品图片" type="file" id="file" name="file">
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							<font id="error">*</font><span hidden="true" for="file" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">商品说明：</label>
						<div class="col-md-5">
							<div class="rel">
								<textarea maxlength="2000" id="productInfo" name="productInfo" placeholder="商品说明" class="form-control" rows="10"></textarea>
							</div>
						</div>
						<div class="col-md-3 red-tag tag">
							<span hidden="true" for="costOutside" class="error"></span>
						</div>
					</div>
					<h4>购买信息</h4>
					<div class="form-group">
						<label class="control-label col-md-2"><span class="red-tag"> * </span> 提前购买时间：</label>
						<div class="col-md-10">
							<label class="radio" for="buyOption1"><input id="buyOption1" checked="checked" name="buyOption" type="radio" value="1"><span>用户任意时间购买均可使用</span></label>
						</div>
						<label class="control-label col-md-2"></label>
						<div class="col-md-10">
							<label class="radio" for="buyOption2"><input id="buyOption2" name="buyOption" type="radio" value="2">
								<span>用户需要在出游前
	                                <select class="form-control" style="margin-right:5px; width:50px; display: inline-block" id="buyTimeHour" name="buyTimeHour">
		                                <c:forEach var="i" begin="0" end="24" step="1">
											<option value="${i}">${i}</option>
										</c:forEach>
	                           		</select>
	                           		<span class="tag">小时</span>
									<select class="form-control" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block" id="buyTimeMinute" name="buyTimeMinute">
										<c:forEach var="o" begin="0" end="60" step="1">
											<option value="${o}">${o}</option>
										</c:forEach>
									</select>
									<span class="tag" style="margin:0 5px;">分钟购买，方可使用</span>
								</span>
							</label>
						</div>
						<label class="control-label col-md-2"></label>
						<div class="col-md-10">
							<label class="radio" for="buyOption3"><input id="buyOption3" name="buyOption" type="radio" value="3">
								<span>用户需要在游玩
	                            	<select class="form-control" style="margin-right:5px; width:100px; display: inline-block" id="buyUseDay" name="buyUseDay">
		                                <option value="0">当天</option>
		                                <c:forEach var="u" begin="1" end="7" step="1">
											<option value="${u}">${u}天</option>
										</c:forEach>
	                                </select>
	                                <span class="tag">的</span>
									<select class="form-control" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block" id="buyUseHour" name="buyUseHour">
										<c:forEach var="i" begin="0" end="24" step="1">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
									<span class="tag">点</span>
									<select class="form-control" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block" id="buyUseMinute" name="buyUseMinute">
										<c:forEach var="e" begin="0" end="60" step="1">
											<option value="${e}">${e}</option>
										</c:forEach>
									</select>
									<span class="tag" style="margin:0 5px;">分前购买，并在</span>
									<select class="form-control" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block" id="buyUseAfterHour" name="buyUseAfterHour">
										<c:forEach var="h" begin="0" end="24" step="1">
											<option value="${h}">${h}</option>
										</c:forEach>
									 </select>
									<span class="tag" style="margin:0 5px;">小时候后，方可使用</span>
								</span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">购买人必须提供:</label>
						<div class="col-md-7">
							<label class="radio-inline"><input checked="checked" type="checkbox" disabled><span>手机</span></label>
							<label class="radio-inline"><input checked="checked" type="checkbox" disabled><span>姓名</span></label>
							<label class="radio-inline"><input name="isMustCard" type="checkbox" value="1"><span>身份证</span></label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">是否实名制:</label>
						<div class="col-md-7">
							<label class="radio-inline"><input name="isRealname" type="radio" value="1"><span>是</span></label>
							<label class="radio-inline"><input name="isRealname" checked="checked" type="radio" value="0"><span>否</span></label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">预订数量限制:</label>
						<div class="col-md-7">
							<span>每笔订单最少购买</span>
							<input class="form-control" type="number" style="width:80px; display: inline-block;" id="buyMinNumber" name="buyMinNumber">
							<span>张，最多购买</span>
							<input class="form-control" type="number" style="width:80px; display: inline-block;" id="buyMaxNumber" name="buyMaxNumber">
							<span>张</span>
						</div>
						<div class="col-md-3 red-tag tag">
							<span hidden="true" for="buyMinNumber" class="error"></span>
							<span hidden="true" for="buyMaxNumber" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">游玩日期限制:</label>
						<div class="col-md-10">
							<label class="radio-inline fl" for="playMode1">
								<input checked="checked" id="playMode1" name="playMode" type="radio" value="1"><span>不限制日期</span>
							</label>
							<label class="radio-inline" for="playMode2">
								<input name="playMode" id="playMode2" type="radio" value="2"><span>指定日期游玩 </span>
								<%--<div class="input-group date datepicker fr" style=" width:150px; margin-left:10px;margin-top:-5px;">--%>
									<%--<input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" placeholder="指定日期" type="text" id="playDate" name="playDate"><span class="input-group-addon"><i class="icon-calendar"></i></span>--%>
								<%--</div>--%>
							</label>
						</div>
					</div>
					<h4>有效期信息</h4>
					<div class="form-group">
						<label class="control-label col-md-2">产品有效期段:</label>
						<div class="col-sm-2 input-group date datepicker fr" style=" width:150px; margin-left:10px;margin-top:-5px;">
							<input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="validStartDate" name="validStartDate" placeholder="起止日期" type="text"><span class="input-group-addon"><i class="icon-calendar"></i></span>
						</div>
						<div class="fl tag"> 至 </div>
						<div class="col-sm-2 input-group date datepicker fr" style=" width:150px; margin-left:10px;margin-top:-5px;">
							<input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="validEndDate" name="validEndDate" placeholder="结束日期" type="text"><span class="input-group-addon"><i class="icon-calendar"></i></span>
						</div>
						<div class="col-md-3 red-tag tag">
							*<span hidden="true" for="validEndDate" class="error"></span>
						</div>
					</div>
					<h4>库存信息</h4>
					<div class="form-group">
						<label class="control-label col-md-2">库存模式:</label>
						<div class="col-md-10">
							<label class="radio-inline fl"><input checked="checked" id="storeMode1" name="storeMode" type="radio" value="1"><span>不限制库存</span></label>
							<label class="radio-inline">   <input name="storeMode" id="storeMode2" type="radio" value="2">
								<span>总库存
                                </span><input class="form-control" placeholder="请输入库存量" type="number" style="width:130px; float: right; margin: -7px 0 0 10px;" id="storeNum" name="storeNum">
							</label>
						</div>
					</div>
					<h4>退款规则信息</h4>
					<div class="form-group">
						<label class="control-label col-md-2">退款设置:</label>
						<div class="col-md-7">
							<label class="radio-inline"><input checked="checked" name="refundMode" type="radio" value="1"><span>随时退</span></label>
							<label class="radio-inline"><input name="refundMode" type="radio" value="2"><span>不可退</span></label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">退款时间:</label>
						<div class="col-md-10">
							<label class="radio-inline"><input checked="checked" id="refundTime1" name="refundTime" type="radio" value="1"><span>不限制</span></label>
							<label class="radio-inline"><input id="refundTime2" name="refundTime" type="radio" value="2">
								<span>在购买后的</span>
								<input class="form-control" type="number" style="width:80px; display: inline-block;" id="refundAfterDay" name="refundAfterDay">
								<span>天内可退</span>
							</label>
							<label class="radio-inline"><input id="refundTime3" name="refundTime" type="radio" value="3">
                                <span>出游日前</span>
                                <input class="form-control" id="refAfterDay" name="refundAfterDay" type="number" style="width:80px; display: inline-block;">
                                <span>天的</span>
                                <select class="form-control" id="refundAfterHour" name="refundAfterHour" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block">
                                    <c:forEach var="h" begin="0" end="24" step="1">
                                        <option value="${h}">${h}</option>
                                    </c:forEach>
                                </select>
                                <span class="tag">点</span>
                                    <select class="form-control" id="refundAfterMinute" name="refundAfterMinute" style="margin-right:5px; margin-left:5px; width:50px; display: inline-block">
                                        <c:forEach var="o" begin="0" end="60" step="1">
                                            <option value="${o}">${o}</option>
                                        </c:forEach>
                                    </select>
                                <span>分之前可退</span>
                            </label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">审核设置:</label>
						<div class="col-md-7">
							<label class="radio-inline"><input checked="checked" name="auditMode" type="radio" value="1"><span>不需要审核</span></label>
							<label class="radio-inline"><input name="auditMode" type="radio" value="2"><span>需要审核</span></label>
						</div>
					</div>
					<div class="form-group">
                        <label class="control-label col-md-2">退款方式:</label>
                        <div class="col-md-7">
                            <label class="radio-inline"><input checked="checked" name="refundMethod" type="radio" value="1"><span>整订单退款</span></label>
                            <label class="radio-inline"><input name="refundMethod" type="radio" value="2"><span>可部分退款</span></label>
                        </div>
                    </div>
					<div class="form-group">
						<label class="control-label col-md-2">手续费扣费模式:</label>
						<div class="col-md-3">
							<select class="form-control" id="serviceMode" name="serviceMode">
								<option value="1">无手续费</option>
								<option value="2">按票扣除</option>
								<%--<option value="3">按订单扣除</option>--%>
							</select>
						</div>
						<div class="col-md-2" id="serviceModeCost">
						</div>
					</div>
					<h4>第三方平台</h4>
					<div class="form-group">
						<label class="control-label col-md-2">第三方接口:</label>
						<div class="col-md-3">
							<select class="form-control" id="thirdPlatformId" name="thirdPlatformId">
								<option value="" selected="selected">请选择</option>
								<c:forEach items="${thirdplatform}" var="platform" varStatus="s">
									<option value="${platform.id}">${platform.name}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label class="control-label col-md-2">第三方产品编号:</label>
						<div class="col-md-3">
							<input class="form-control" placeholder="请输入第三方产品编号" type="text" id="thirdPlatformNo" name="thirdPlatformNo"  maxlength="35">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row" style="min-height:60px;">
		<div class="form-group btn-form">
			<div class="col-md-11 text-center">
				<button class="btn btn-footer btn-cancel" onclick="toProductCategoryList()">取消</button>
				<button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
			</div>
		</div>
	</div>
</form>
<!--弹框 start-->
<div class="modal fade" id="myModal4">
	<div class="modal-dialog pd-t80" style="width:880px;">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
				<h4 class="modal-title">选择景区</h4>
			</div>
			<div class="modal-body" style="overflow-y: scroll;">
				<div class="form-group tb-pd" style="margin-bottom: 10px;">
					<div class="page-title ">
						<!--列表上方右侧按钮 S-->
						<div class="fr">
							<input class="form-control fl" type="text" style="width:180px;" id="scenicName" name="scenicName" placeholder="请输入景区名称" />
							<button class="btn btn-primary fl btn-top" type="submit" id="toSearch">
								<i class="icon-search"></i>搜索
							</button>
						</div>
					</div>
					<div id="paymentExportDialog" class="filter"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹框 end-->
<script type="text/javascript">
    jQuery.validator.addMethod("filecheck", function(value, element, params) {
        var reg = /.(jpg|JPG|jpeg|JPEG|png|PNG)$/;
        return this.optional(element) || (reg.test(value));
    }, "图片文件格式不正确！");

    jQuery.validator.addMethod("scenicChack", function(value, element, params) {
        if(value == "选择景区")
            return false;
        else
            return true;
    }, "请选择景区！");

    jQuery.validator.addMethod("buyMaxNumbercheck", function(value, element, params) {
        var min = $("#buyMinNumber").val();
        if(min == null) min = 0;
        if(value == null) value = 0;
        if(value < min)
            return false;
        else
            return true;
    }, "预定数量输入有误！");

    jQuery.validator.addMethod("validEndDateChack", function(value, element, params) {
        var start = $("#validStartDate").val();
        if(start == null) start = 0;
        if(value == null) value = 0;
        if((value != 0 && start == 0) || (value == 0 && start != 0) || (value < start))
            return false;
        else
            return true;
    }, "有效日期有误！");

    var iscommiting = false;
    $(function() {
        // toSearch按钮绑定事件，点击后执行搜索
        $("#toSearch").click(function() {
            var requestUrl = "${ctx}/product/supplier/toScenic" ;
            var requestData = {} ;
           cserpLoadPage(requestUrl, requestData, 'GET', 'paymentExportDialog') ;
        }) ;

        $("#serviceModeCost").hide();
        $('#buyTimeHour').attr({
            "disabled": "disabled"
        });
        $('#buyTimeMinute').attr({
            "disabled": "disabled"
        });
        $('#buyUseDay').attr({
            "disabled": "disabled"
        });
        $('#buyUseHour').attr({
            "disabled": "disabled"
        });
        $('#buyUseMinute').attr({
            "disabled": "disabled"
        });
//        $('#playDate').attr({
//            "disabled": "disabled"
//        });
        $('#storeNum').attr({
            "disabled": "disabled"
        });
        $('#refundAfterDay').attr({
            "disabled": "disabled"
        });
        $('#refAfterDay').attr({
            "disabled": "disabled"
        });
        $('#refundAfterHour').attr({
            "disabled": "disabled"
        });
        $('#refundAfterMinute').attr({
            "disabled": "disabled"
        });
        $('#serviceProductCost').attr({
            "disabled": "disabled"
        });
        $('#serviceOrderCost').attr({
            "disabled": "disabled"
        });
        $('#buyUseAfterHour').attr({
            "disabled": "disabled"
        });
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true, //选中之后自动隐藏日期选择框
            clearBtn: true, //清除按钮
            todayBtn: true, //今日按钮
            format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });

        $("#productAddForm").validate({
            rules: {
                categoryId: {
                    required: true,
                },
                name: {
                    required: true,
                },
                marketPrice: {
                    required: true,
                    number:true
                },
                limitPrice: {
                    required: true,
                    number:true
                },
               selectScenic: {
                    scenicChack: true
                },
//                provinceId: {
//                    required: true,
//                },
//                cityId: {
//                    required: true,
//                },
                file: {
                    required: true,
                    filecheck: true,
                },
                buyOption: {
                    required: true,
                },
                buyMinNumber: {
                    digits: true,
                },
                buyUseAfterHour: {
                    digits: true,
                },
                buyMaxNumber: {
                    digits: true,
                    buyMaxNumbercheck : true
                },
                storeNum: {
                    digits: true,
                },
                refundAfterDay: {
                    digits: true,
                },
                refAfterDay: {
                    digits: true,
                },
                refundAfterHour: {
                    digits: true,
                },
                refundAfterMinute: {
                    digits: true,
                },
                validEndDate:{
                    required: true,
                    validEndDateChack: true
                }
            },
            messages: {
                categoryId: {
                    required: "请选择商品类别！",
                },
                name: {
                    required: "商品名称不能为空！",
                },
                marketPrice: {
                    required: "市场价不能为空！",
                    number: "请输入整数!"
                },
                limitPrice: {
                    required: "限售价不能为空！",
                    number: "请输入整数!"
                },
                scenicId: {
                    required: "请选择景区！",
                },
//                provinceId: {
//                    required: "请选择省份！",
//                },
//                cityId: {
//                    required: "请选择市区！",
//                },
                file: {
                    required: "请上传图片！",
                },
                buyOption: {
                    required: "请选择购买模式！",
                },
                buyMinNumber: {
                    digits: "请输入整数!",
                },
                buyUseAfterHour: {
                    digits: "请输入整数!",
                },
                buyMaxNumber: {
                    digits: "请输入整数!",
                },
                storeNum: {
                    digits: "请输入整数!",
                },
                refundAfterDay: {
                    digits: "请输入整数!",
                },
                refAfterDay: {
                    digits: "请输入整数!",
                },
                refundAfterHour: {
                    digits: "请输入整数!",
                },
                refundAfterMinute: {
                    digits: "请输入整数!",
                },
                validEndDate:{
                    required: "请选择有效日期",
                }
            },
            submitHandler: function(form) {
                if(iscommiting) {
                    return false;
                }
                iscommiting = true;
                
                jQuery(form).ajaxSubmit({
                    method: "post",
                    url: "${ctx}/product/supplier/add",
                    beforeSubmit: function(formData, jqForm, options) {},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout: 120000,
                    success: function(data) {
                        if(data.flag) {
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'succeed',
                                content: data.message,
                                close: function() {
                                    toProductCategoryList();
                                }
                            });
                        } else {
                            iscommiting = false;
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'warning',
                                content: data.message,
                                close: function() {}
                            });
                            iscommiting = false;
                        }
                    },
                    error: function(e) {
                        iscommiting = false;
                    }
                });
            }
        });

        $("#provinceId").change(function() {
            var theProvinceId = $(this).val();
            var _url = "${ctx}/info/area/province/" + theProvinceId + "/city";
            $.ajax({
                url: _url,
                type: "get",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                timeout: 120000,
                beforeSend: function() {},
                success: function(data) {
                    $("#cityId").html("");
                    if(data.flag) {
                        // $("<option></option>").val("").text("请选择").appendTo("#province") ;
                        $(data.message).each(function(i, item) {
                            $("<option></option>").val(item.id).text(item.name).appendTo("#cityId");
                        });
                    } else {
                        $("<option></option>").val("").text("请选择").appendTo("#cityId");
                    }
                },
                error: function(e) {
                    $("#cityId").html("");
                }
            });
        });
        //提前购买时间
        $('#buyOption1').click(function() {
            $('#buyTimeHour').attr({
                "disabled": "disabled"
            });
            $('#buyTimeMinute').attr({
                "disabled": "disabled"
            });
            $('#buyUseDay').attr({
                "disabled": "disabled"
            });
            $('#buyUseHour').attr({
                "disabled": "disabled"
            });
            $('#buyUseMinute').attr({
                "disabled": "disabled"
            });
            $('#buyUseAfterHour').attr({
                "disabled": "disabled"
            });
        });
        $('#buyOption2').click(function() {
            $('#buyTimeHour').removeAttr("disabled");
            $('#buyTimeMinute').removeAttr("disabled");
            $('#buyUseDay').attr({
                "disabled": "disabled"
            });
            $('#buyUseHour').attr({
                "disabled": "disabled"
            });
            $('#buyUseMinute').attr({
                "disabled": "disabled"
            });
            $('#buyUseAfterHour').attr({
                "disabled": "disabled"
            });
        });
        $('#buyOption3').click(function() {
            $('#buyTimeHour').attr({
                "disabled": "disabled"
            });
            $('#buyTimeMinute').attr({
                "disabled": "disabled"
            });
            $('#buyUseDay').removeAttr("disabled");
            $('#buyUseHour').removeAttr("disabled");
            $('#buyUseMinute').removeAttr("disabled");
            $('#buyUseAfterHour').removeAttr("disabled");
        });
//        $('#playMode1').click(function() {
//            $('#playDate').attr({
//                "disabled": "disabled"
//            });
//        });
//        $('#playMode2').click(function() {
//            $('#playDate').removeAttr("disabled");
//        });
        $('#storeMode1').click(function() {
            $('#storeNum').attr({
                "disabled": "disabled"
            });
        });
        $('#storeMode2').click(function() {
            $('#storeNum').removeAttr("disabled");
        });
        $('#refundTime1').click(function() {
            $('#refundAfterDay').attr({
                "disabled": "disabled"
            });

            $('#refAfterDay').attr({
                "disabled": "disabled"
            });
            $('#refundAfterHour').attr({
                "disabled": "disabled"
            });
            $('#refundAfterMinute').attr({
                "disabled": "disabled"
            });
        });
        $('#refundTime2').click(function() {
            $('#refundAfterDay').removeAttr("disabled");

            $('#refAfterDay').attr({
                "disabled": "disabled"
            });
            $('#refundAfterHour').attr({
                "disabled": "disabled"
            });
            $('#refundAfterMinute').attr({
                "disabled": "disabled"
            });
        });
        $('#refundTime3').click(function() {

            $('#refundAfterDay').attr({
                "disabled": "disabled"
            });

            $('#refAfterDay').removeAttr("disabled");
            $('#refundAfterHour').removeAttr("disabled");
            $('#refundAfterMinute').removeAttr("disabled");
        });
        $("#serviceMode").change(function() {
            var mode = $(this).val();
            $("#serviceModeCost").hide();
            $('#serviceProductCost').attr({
                "disabled": "disabled"
            });
            $('#serviceOrderCost').attr({
                "disabled": "disabled"
            });
            if(mode == 2){
                $("#serviceModeCost").show();
                window.document.getElementById("serviceModeCost").innerHTML = "<input class=\"form-control\" placeholder=\"请输入手续费\" type=\"text\" id=\"serviceProductCost\" name=\"serviceProductCost\" pattern=\"^[0-9]+(\\.[0-9]{2})?$\" title=\"请输入正确费用!(两位小数)\" >";
                $('#serviceProductCost').removeAttr("disabled");
                $('#serviceOrderCost').attr({
                    "disabled": "disabled"
                });
            }
            if(mode == 3){
                $("#serviceModeCost").show();
                window.document.getElementById("serviceModeCost").innerHTML = "<input class=\"form-control\" placeholder=\"请输入手续费\" type=\"text\" id=\"serviceOrderCost\" name=\"serviceOrderCost\" pattern=\"^[0-9]+(\\.[0-9]{2})?$\" title=\"请输入正确费用!(两位小数)\" >\n";
                $('#serviceOrderCost').removeAttr("disabled");
                $('#serviceProductCost').attr({
                    "disabled": "disabled"
                });
            }
        });
    });
    function exportAll() {
        var requestUrl = "${ctx}/product/supplier/toScenic";
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET','paymentExportDialog') ;
    }
    function toProductCategoryList() {
        var requestUrl = "${ctx}/product/supplier/main";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }
</script>