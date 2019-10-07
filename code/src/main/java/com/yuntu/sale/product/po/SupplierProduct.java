package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商商品Entity
 * @author zy
 * @version 2018-04-02
 */
public class SupplierProduct extends BasePo implements Serializable {

	private static final long serialVersionUID = 7055075734988597412L;
	private Long id;            //id
	private String no;		// 产品编号
	private Long enterpriseId;		// 供应商企业Id
	private String name;		// 商品名称
	private BigDecimal marketPrice;		// 市场价
	private BigDecimal limitPrice;	// 限售价（网络最小销售价格）
	private Long scenicId;		// 景区Id
	private Long categoryId;		// 商品类别
	private Long provinceId;		// 省
	private Long cityId;		// 市
	private String introduce;		// 商品介绍
	private String costInside;		// 费用包含
	private String costOutside;		// 费用不包含
	private String pic;		// 产品图片地址
	private Integer buyOption;		// 购买选择
	private Integer buyTimeHour;		// 提前购买时间-小时
	private Integer buyTimeMinute;		// 提前购买时间-分钟
	private Integer buyUseDay;		// 游玩前-天
	private Integer buyUseHour;		// 游玩前-小时
	private Integer buyUseMinute;		// 游玩前-分钟
	private Integer buyUseAfterHour;   //N小时候方可使用
	private Integer buyMinNumber;		// 每单最少购买张数
	private Integer buyMaxNumber;		// 每单最多购买张数
	private Integer playMode;		// 游玩方式
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date playDate;		// 游玩日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validStartDate;		// 产品有效期-开始日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validEndDate;		// 产品有效期-截止日期
	private Integer storeMode;		// 库存模式
	private Integer storeNum;       // 库存量
	private Integer refundMode;		// 退款设置
	private Integer auditMode;		// 审核设置
	private Integer serviceMode;		// 手续费扣费模式
	private BigDecimal serviceProductCost;		// 每张票扣除费用
	private BigDecimal serviceOrderCost;		// 每个订单扣除费用
	private String serviceTel;		// 客服电话
	private String messageTemplate;		// 短信模板
	private Long thirdPlatformId;		// 第三方平台Id
	private String thirdPlatformNo;		// 第三方平台产品编号
	private Integer isRealname;   // 是否实名制（1：是，0：否）
	private Integer status;		// 商品状态（1：启用，0：停用）
	private Integer isDelete;		// 是否删除（1：是，0：否）
	private Integer refundTime;    //退款时间模式（1：不限，2：N天后）
	private String productInfo;		//商品说明
	private Integer refundAfterDay; //N天前可退款
	private Integer refundAfterHour; //（N天）N小时前可退款
	private Integer refundAfterMinute; //（N天N小时）N分钟前可退款
	private Integer isMustCard; //是否需要提供身份证
	
	/**
	 * 退款方式（1：整订单退款，2：可部分退款）
	 */
	private Integer refundMethod;

	/**
	 * 当日价格
	 */
	private String todayPrice;

	public SupplierProduct() {
		this.status = GrobalConstant.I_SUPPLIER_PRODUCT_STATUS_AVAILABLE;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}

	public Integer getIsMustCard() {
		return isMustCard;
	}

	public void setIsMustCard(Integer isMustCard) {
		this.isMustCard = isMustCard;
	}


	public Integer getRefundAfterHour() {
		return refundAfterHour;
	}

	public void setRefundAfterHour(Integer refundAfterHour) {
		this.refundAfterHour = refundAfterHour;
	}

	public Integer getRefundAfterMinute() {
		return refundAfterMinute;
	}

	public void setRefundAfterMinute(Integer refundAfterMinute) {
		this.refundAfterMinute = refundAfterMinute;
	}


	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getCostInside() {
		return costInside;
	}

	public void setCostInside(String costInside) {
		this.costInside = costInside;
	}
	
	public String getCostOutside() {
		return costOutside;
	}

	public void setCostOutside(String costOutside) {
		this.costOutside = costOutside;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public Integer getBuyOption() {
		return buyOption;
	}

	public void setBuyOption(Integer buyOption) {
		this.buyOption = buyOption;
	}
	
	public Integer getBuyTimeHour() {
		return buyTimeHour;
	}

	public void setBuyTimeHour(Integer buyTimeHour) {
		this.buyTimeHour = buyTimeHour;
	}
	
	public Integer getBuyTimeMinute() {
		return buyTimeMinute;
	}

	public void setBuyTimeMinute(Integer buyTimeMinute) {
		this.buyTimeMinute = buyTimeMinute;
	}


	public Integer getBuyUseAfterHour() {
		return buyUseAfterHour;
	}

	public void setBuyUseAfterHour(Integer buyUseAfterHour) {
		this.buyUseAfterHour = buyUseAfterHour;
	}


	public Integer getBuyUseDay() {
		return buyUseDay;
	}

	public void setBuyUseDay(Integer buyUseDay) {
		this.buyUseDay = buyUseDay;
	}
	
	public Integer getBuyUseHour() {
		return buyUseHour;
	}

	public void setBuyUseHour(Integer buyUseHour) {
		this.buyUseHour = buyUseHour;
	}
	
	public Integer getBuyUseMinute() {
		return buyUseMinute;
	}

	public void setBuyUseMinute(Integer buyUseMinute) {
		this.buyUseMinute = buyUseMinute;
	}
	
	public Integer getBuyMinNumber() {
		return buyMinNumber;
	}

	public void setBuyMinNumber(Integer buyMinNumber) {
		this.buyMinNumber = buyMinNumber;
	}
	
	public Integer getBuyMaxNumber() {
		return buyMaxNumber;
	}

	public void setBuyMaxNumber(Integer buyMaxNumber) {
		this.buyMaxNumber = buyMaxNumber;
	}
	
	public Integer getPlayMode() {
		return playMode;
	}

	public void setPlayMode(Integer playMode) {
		this.playMode = playMode;
	}

	public Date getPlayDate() {
		return playDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public Date getValidStartDate() {
		return validStartDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	
	public Integer getStoreMode() {
		return storeMode;
	}

	public void setStoreMode(Integer storeMode) {
		this.storeMode = storeMode;
	}
	
	public Integer getRefundMode() {
		return refundMode;
	}

	public void setRefundMode(Integer refundMode) {
		this.refundMode = refundMode;
	}
	
	public Integer getAuditMode() {
		return auditMode;
	}

	public void setAuditMode(Integer auditMode) {
		this.auditMode = auditMode;
	}
	
	public Integer getServiceMode() {
		return serviceMode;
	}

	public void setServiceMode(Integer serviceMode) {
		this.serviceMode = serviceMode;
	}
	
	public BigDecimal getServiceProductCost() {
		return serviceProductCost;
	}

	public void setServiceProductCost(BigDecimal serviceProductCost) {
		this.serviceProductCost = serviceProductCost;
	}
	
	public BigDecimal getServiceOrderCost() {
		return serviceOrderCost;
	}

	public void setServiceOrderCost(BigDecimal serviceOrderCost) {
		this.serviceOrderCost = serviceOrderCost;
	}
	
	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	
	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
	
	public Long getThirdPlatformId() {
		return thirdPlatformId;
	}

	public void setThirdPlatformId(Long thirdPlatformId) {
		this.thirdPlatformId = thirdPlatformId;
	}
	
	public String getThirdPlatformNo() {
		return thirdPlatformNo;
	}

	public void setThirdPlatformNo(String thirdPlatformNo) {
		this.thirdPlatformNo = thirdPlatformNo;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	public Integer getIsRealname() {
		return isRealname;
	}

	public void setIsRealname(Integer isRealname) {
		this.isRealname = isRealname;
	}

	public String getTodayPrice() {
		return todayPrice;
	}

	public void setTodayPrice(String todayPrice) {
		this.todayPrice = todayPrice;
	}

	public Integer getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Integer refundTime) {
		this.refundTime = refundTime;
	}

	public Integer getRefundAfterDay() {
		return refundAfterDay;
	}

	public void setRefundAfterDay(Integer refundAfterDay) {
		this.refundAfterDay = refundAfterDay;
	}
	
	public Integer getRefundMethod() {
		return refundMethod;
	}

	public void setRefundMethod(Integer refundMethod) {
		this.refundMethod = refundMethod;
	}

	@Override
	public String toString() {
		return "SupplierProduct{" +
				"id=" + id +
				", no='" + no + '\'' +
				", enterpriseId=" + enterpriseId +
				", name='" + name + '\'' +
				", marketPrice=" + marketPrice +
				", limitPrice=" + limitPrice +
				", scenicId=" + scenicId +
				", categoryId=" + categoryId +
				", provinceId=" + provinceId +
				", cityId=" + cityId +
				", introduce='" + introduce + '\'' +
				", costInside='" + costInside + '\'' +
				", costOutside='" + costOutside + '\'' +
				", pic='" + pic + '\'' +
				", buyOption=" + buyOption +
				", buyTimeHour=" + buyTimeHour +
				", buyTimeMinute=" + buyTimeMinute +
				", buyUseDay=" + buyUseDay +
				", buyUseHour=" + buyUseHour +
				", buyUseMinute=" + buyUseMinute +
				", buyMinNumber=" + buyMinNumber +
				", buyMaxNumber=" + buyMaxNumber +
				", playMode=" + playMode +
				", playDate=" + playDate +
				", validStartDate=" + validStartDate +
				", validEndDate=" + validEndDate +
				", storeMode=" + storeMode +
				", storeNum=" + storeNum +
				", refundMode=" + refundMode +
				", auditMode=" + auditMode +
				", serviceMode=" + serviceMode +
				", serviceProductCost=" + serviceProductCost +
				", serviceOrderCost=" + serviceOrderCost +
				", serviceTel='" + serviceTel + '\'' +
				", messageTemplate='" + messageTemplate + '\'' +
				", thirdPlatformId=" + thirdPlatformId +
				", thirdPlatformNo='" + thirdPlatformNo + '\'' +
				", isRealname=" + isRealname +
				", status=" + status +
				", isDelete=" + isDelete +
				'}';
	}
}