package com.yuntu.sale.orders.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单表子-企业订单表Entity
 * @author zy
 * @version 2018-04-23
 */
public class EnterpriseOrders implements Serializable {

	private static final long serialVersionUID = 5680262794409580081L;
	private Long id;
	private Long ordersId;		// 订单id
	private String orderno;		// 订单编号
	private Long enterpriseId;		// 订单所属企业id
	private String enterpriseName;		// 订单所属企业名称
	private Long parentId;		// 上级id
	private String parentName;		// 上级名称
	private Integer paymentMethod;		// payment_method
	private Integer catid1;		//
	private Long typeId;		//
	private Long sproductId;		// 产品id
	private Long uproductId;		// 产品分销id 关系表
	private String uproductName;		// 产品分销名称
	private Integer num;		// 购买数量
	private BigDecimal fxPrice;		// 我对上级的购买价
	private BigDecimal price;		// 购买总价
	private Long province;		// 商品所属省
	private Long city;		// 商品所属城市
	private Long supplierId;		// 商品供应商Id
	private String supplierName;		// 商品供应商名称
	private Long shopId;		// 商品所属商户Id
	private String shopName;		// 商品所属商户名称
	private Integer isOrder;		// is_order
	private Integer isAudit;		// is_audit
	private Integer refundFeeType;		// refund_fee_type
	private BigDecimal refundFee;		// refund_fee
	private BigDecimal lockprice;		// lockprice
	private Integer printnum;		// 核销数
	private Integer locknum;		// 已退数
	private Integer clocknum;		// 冻结数
	private Integer isRemind;		// is_remind
	private Integer status;		// 订单状态
	private Long createdId;		// created_id
	private String createdBy;		// created_by
	private Integer createdAt;		// created_at
	private String createdAttime;   //
	private Integer saleMode;		// 销售方式(2:众包)
	private Integer integral;		// integral
	private Integer sintegral;		// sintegral
	private BigDecimal fxSale;		// fx_sale
	private Integer version;		// 版本号

	private String statusName;     //订单状态

	public EnterpriseOrders() {
		this.paymentMethod = 1;//默认 1
		this.catid1 = 0;
		this.typeId = 0L;
		this.isOrder = 0;
		this.isAudit = 2;
		this.refundFeeType = 1;
		this.refundFee = new BigDecimal(0);
		this.lockprice = new BigDecimal(0);
		this.printnum = 0;
		this.locknum = 0;
		this.clocknum = 0;
		this.isRemind = 1;
		this.createdAt = (int)(System.currentTimeMillis()/1000);
		this.createdBy = "";
		this.saleMode=2;
		this.integral=0;
		this.sintegral=0;
		this.fxPrice=new BigDecimal(0);
		this.parentName = "";
		this.uproductName = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getCatid1() {
		return catid1;
	}

	public void setCatid1(Integer catid1) {
		this.catid1 = catid1;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public Long getUproductId() {
		return uproductId;
	}

	public void setUproductId(Long uproductId) {
		this.uproductId = uproductId;
	}

	public String getUproductName() {
		return uproductName;
	}

	public void setUproductName(String uproductName) {
		this.uproductName = uproductName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getFxPrice() {
		return fxPrice;
	}

	public void setFxPrice(BigDecimal fxPrice) {
		this.fxPrice = fxPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(Integer isOrder) {
		this.isOrder = isOrder;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Integer getRefundFeeType() {
		return refundFeeType;
	}

	public void setRefundFeeType(Integer refundFeeType) {
		this.refundFeeType = refundFeeType;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public BigDecimal getLockprice() {
		return lockprice;
	}

	public void setLockprice(BigDecimal lockprice) {
		this.lockprice = lockprice;
	}

	public Integer getPrintnum() {
		return printnum;
	}

	public void setPrintnum(Integer printnum) {
		this.printnum = printnum;
	}

	public Integer getLocknum() {
		return locknum;
	}

	public void setLocknum(Integer locknum) {
		this.locknum = locknum;
	}

	public Integer getClocknum() {
		return clocknum;
	}

	public void setClocknum(Integer clocknum) {
		this.clocknum = clocknum;
	}

	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreatedId() {
		return createdId;
	}

	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAttime() {
		return createdAttime;
	}

	public void setCreatedAttime(String createdAttime) {
		this.createdAttime = createdAttime;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getSintegral() {
		return sintegral;
	}

	public void setSintegral(Integer sintegral) {
		this.sintegral = sintegral;
	}

	public BigDecimal getFxSale() {
		return fxSale;
	}

	public void setFxSale(BigDecimal fxSale) {
		this.fxSale = fxSale;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "EnterpriseOrders{" +
				"id=" + id +
				", ordersId=" + ordersId +
				", orderno='" + orderno + '\'' +
				", enterpriseId=" + enterpriseId +
				", enterpriseName='" + enterpriseName + '\'' +
				", parentId=" + parentId +
				", parentName='" + parentName + '\'' +
				", paymentMethod=" + paymentMethod +
				", catid1=" + catid1 +
				", typeId=" + typeId +
				", sproductId=" + sproductId +
				", uproductId=" + uproductId +
				", uproductName='" + uproductName + '\'' +
				", num=" + num +
				", fxPrice=" + fxPrice +
				", price=" + price +
				", province=" + province +
				", city=" + city +
				", supplierId=" + supplierId +
				", supplierName='" + supplierName + '\'' +
				", shopId=" + shopId +
				", shopName='" + shopName + '\'' +
				", isOrder=" + isOrder +
				", isAudit=" + isAudit +
				", refundFeeType=" + refundFeeType +
				", refundFee=" + refundFee +
				", lockprice=" + lockprice +
				", printnum=" + printnum +
				", locknum=" + locknum +
				", clocknum=" + clocknum +
				", isRemind=" + isRemind +
				", status=" + status +
				", createdId=" + createdId +
				", createdBy='" + createdBy + '\'' +
				", createdAt=" + createdAt +
				", createdAttime='" + createdAttime + '\'' +
				", saleMode=" + saleMode +
				", integral=" + integral +
				", sintegral=" + sintegral +
				", fxSale=" + fxSale +
				", version=" + version +
				'}';
	}
}