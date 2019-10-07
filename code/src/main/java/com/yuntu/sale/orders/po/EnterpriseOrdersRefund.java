package com.yuntu.sale.orders.po;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款子表-企业退款表Entity
 * @author zy
 * @version 2018-04-23
 */
public class EnterpriseOrdersRefund implements Serializable {
	private static final long serialVersionUID = 7567372899574329691L;
	private Long id;            //id
	private Long refundId;        // 主退款单Id
	private Long ordersId;        // 主订单Id
	private Long enterpriseOrdersId;	// 企业订单Id
	private Long sproductId;        // 退款产品Id
	private Integer refundFeeType;        // 退款扣款方式（1：按每张扣款，2：按订单扣款）
	private BigDecimal refundFee;        // 退款手续费（每张、每单）
	private Long childId;        // 下级企业Id（发起退款的）
	private Long parent;        // 上级企业Id
	private Integer num;        // 退款产品数量
	private BigDecimal unitPrice;        // 申请退款的产品单价
	private BigDecimal deductPrice;        // 退款扣除费用（根据退款类型和退款手续费计算）
	private BigDecimal refundPrice;        // 实际退款费用

	public EnterpriseOrdersRefund() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	
	public Long getEnterpriseOrdersId() {
		return enterpriseOrdersId;
	}

	public void setEnterpriseOrdersId(Long enterpriseOrdersId) {
		this.enterpriseOrdersId = enterpriseOrdersId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
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

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDeductPrice() {
		return deductPrice;
	}

	public void setDeductPrice(BigDecimal deductPrice) {
		this.deductPrice = deductPrice;
	}

	public BigDecimal getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
}