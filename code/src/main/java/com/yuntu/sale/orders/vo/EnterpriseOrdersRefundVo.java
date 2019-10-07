package com.yuntu.sale.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款Entity
 * @author zy
 * @version 2018-04-23
 */
public class EnterpriseOrdersRefundVo implements Serializable {

	private static final long serialVersionUID = -6485213168088682424L;
	private Long id;		// 子退款单id
	private Long orderId;		// 订单id
	private String orderNo;		// 订单编号
	private Long refundId;      // 退款单id
	private Long productId;		// 商品id
	private String productName;	// 商品名称
	private BigDecimal fxprise;		// 商品单价
	private Long enterpriseId;		// 商品供应商id
	private Long num;		// 退款数量
	private String notes;           // 退款原因
	private Long creaId;   //退款id
	private String creaName;    //退款人名称
	private Integer time;    //退款时间
	private Integer state;   //退款状态
	private Long auditId;   //审核id
	private String auditName;    //审核人名称
	private Integer audittime;    //审核时间


	public EnterpriseOrdersRefundVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getFxprise() {
		return fxprise;
	}

	public void setFxprise(BigDecimal fxprise) {
		this.fxprise = fxprise;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Integer getAudittime() {
		return audittime;
	}

	public void setAudittime(Integer audittime) {
		this.audittime = audittime;
	}

	public Long getCreaId() {
		return creaId;
	}

	public void setCreaId(Long creaId) {
		this.creaId = creaId;
	}

	public String getCreaName() {
		return creaName;
	}

	public void setCreaName(String creaName) {
		this.creaName = creaName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public String toString() {
		return "EnterpriseOrdersRefundVo{" +
				"id=" + id +
				", orderId=" + orderId +
				", orderNo='" + orderNo + '\'' +
				", refundId=" + refundId +
				", productId=" + productId +
				", productName='" + productName + '\'' +
				", fxprise=" + fxprise +
				", enterpriseId=" + enterpriseId +
				", num=" + num +
				", notes='" + notes + '\'' +
				", creaId=" + creaId +
				", creaName='" + creaName + '\'' +
				", time=" + time +
				", state=" + state +
				", auditId=" + auditId +
				", auditName='" + auditName + '\'' +
				", audittime=" + audittime +
				'}';
	}
}