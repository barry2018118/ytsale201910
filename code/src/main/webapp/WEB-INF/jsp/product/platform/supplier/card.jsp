<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>商品</span>&nbsp;&gt;&nbsp;<span class="dq">商品详情</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toProductCategoryList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>

<form class="form-horizontal">
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
                                <c:forEach items="${ctategory}" var="ctategory" varStatus="s">
                                    <c:if test="${ctategory.id == entity.categoryId}">  <label class="control-label">${ctategory.name}</label></c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">商品名称：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${entity.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">市场价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${entity.marketPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">限售价：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">￥${entity.limitPrice}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">所属景区：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${scenic.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">省份：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${provinces.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">城市：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <label class="control-label">${city.name}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">费用包含：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${entity.costInside}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">费用不包含：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="control-label">${entity.costOutside}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">产品图片：</label>
                        <div class="col-md-3">
                            <div class="rel">
                                <c:if test="${entity.pic != null}"> <img src="${showResourcePath}${entity.pic}" style="max-width:120px;max-height: 120px;"></c:if>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">商品说明：</label>
                        <div class="col-md-5">
                            <div class="rel">
                                <label class="">${entity.productInfo}</label>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                        </div>
                    </div>
                    <h4>购买信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">提前购买时间:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.buyOption}"> <label class="control-label">用户任意时间购买均可使用<span class="red-tag"> * </span></label></c:if>
                            <c:if test="${2 == entity.buyOption}"> <label class="control-label">用户需要在出游前__ ${entity.buyTimeHour} 小时  ${entity.buyTimeMinute} 分钟 __ 购买，方可使用 </label></c:if>
                            <c:if test="${3 == entity.buyOption}"> <label class="control-label">用户需要在游玩<c:if test="${0 == entity.buyUseDay}">__当天__ </c:if><c:if test="${0 != entity.buyUseDay}">__ ${entity.buyUseDay} 天__</c:if>  的 __ ${entity.buyUseHour} 点 ${entity.buyUseMinute} __ 分前购买，并在 __${entity.buyUseAfterHour}__ 小时候后，方可使用。</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">购买人必须提供:</label>
                        <div class="col-md-7">
                            <label class="control-label">手机</label>
                            <label class="control-label">，姓名</label>
                            <c:if test="${entity.isMustCard == 1}"><label class="control-label">，身份证</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">是否实名制:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.isRealname}"><label class="control-label">是</label></c:if>
                            <c:if test="${0 == entity.isRealname}"><label class="control-label">否</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">预订数量限制:</label>
                        <div class="col-md-7">
                            <span>每笔订单最少购买</span>
                            <label class="control-label">__${entity.buyMinNumber}__</label>
                            <span>张，最多购买</span>
                            <label class="control-label">__${entity.buyMaxNumber}__</label>
                            <span>张</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">游玩日期限制:</label>
                        <div class="col-md-10">
                            <c:if test="${1 == entity.playMode}"><label class="control-label">不限制日期</label></c:if>
                            <c:if test="${2 == entity.playMode}"><label class="control-label">指定日期游玩
                                <%--:<fmt:formatDate value="${entity.playDate}" pattern="yyyy-MM-dd"/>--%>
                            </label></c:if>
                        </div>
                    </div>
                    
                    <h4>退款规则信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款设置:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.refundMode}"><label class="control-label">随时退</label></c:if>
                            <c:if test="${2 == entity.refundMode}"><label class="control-label">不可退</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款时间:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.refundTime}"><label class="control-label">不限制</label></c:if>
                            <c:if test="${2 == entity.refundTime}"><label class="control-label">在购买后的${entity.refundAfterDay}天内可退</label></c:if>
                            <c:if test="${3 == entity.refundTime}"><label class="control-label">在出游日前${entity.refundAfterDay}天${entity.refundAfterHour}点${entity.refundAfterMinute}分钟前可退</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">审核设置:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.auditMode}"><label class="control-label">不需要审核</label></c:if>
                            <c:if test="${2 == entity.auditMode}"><label class="control-label">需要审核</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">退款方式:</label>
                        <div class="col-md-7">
                            <c:if test="${1 == entity.refundMethod}"><label class="control-label">整订单退款</label></c:if>
                            <c:if test="${2 == entity.refundMethod}"><label class="control-label">可部分退款</label></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">手续费扣费模式:</label>
                        <div class="col-md-3">
                            <c:if test="${1 == entity.serviceMode}"><label class="control-label">无手续费</label></c:if>
                            <c:if test="${2 == entity.serviceMode}"><label class="control-label">按票扣除,手续费用 ￥${entity.serviceProductCost}</label></c:if>
                            <c:if test="${3 == entity.serviceMode}"><label class="control-label">按订单扣除,手续费用 ￥${entity.serviceOrderCost}</label></c:if>
                            </select>
                        </div>
                        <div class="col-md-2" id="serviceModeCost">
                        </div>
                    </div>
                    <h4>第三方平台</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">第三方接口:</label>
                        <div class="col-md-3">
                            <c:forEach items="${thirdplatform}" var="platform" varStatus="s">
                                <c:if test="${platform.id == entity.thirdPlatformId}"><label class="control-label">${platform.name}</label></c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">第三方产品编号:</label>
                        <div class="col-md-3">
                            <label class="control-label">${entity.thirdPlatformNo}</label>
                        </div>
                    </div>
                    <h4>有效期信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">产品有效期段:</label>
                        <div class="col-md-3">
                            <label class="control-label"><fmt:formatDate value="${entity.validStartDate}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${entity.validEndDate}" pattern="yyyy-MM-dd"/></label>
                        </div>
                    </div>
                    
                    <h4>库存信息</h4>
                    <div class="form-group">
                        <label class="control-label col-md-2">库存模式:</label>
                        <div class="col-md-10">
                            <c:if test="${1 == entity.storeMode}"><label class="control-label">不限制库存</label></c:if>
                            <c:if test="${2 == entity.storeMode}"><label class="control-label">总库存 : ${entity.storeNum}</label></c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function toProductCategoryList() {
        var requestUrl = "${ctx}/product/platform/supplier/main";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }
</script>