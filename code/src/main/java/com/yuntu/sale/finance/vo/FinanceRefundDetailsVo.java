package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 财务对账单-销售-退款明细Vo
 * @author snps
 * @date 2018年5月31日 上午9:57:53
 */
public class FinanceRefundDetailsVo implements Serializable {

	private static final long serialVersionUID = -1350703388465929043L;

	/**
	 * 序号
	 */
	private int no;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 供应方订单号
	 */
	private String supplierOrderNo;

	/**
	 * 采购方订单号
	 */
	private String buyerOrderNo;

	/**
	 * 景区名称
	 */
	private String scenicName;

	/**
	 * 商品Id
	 */
	private Long productId;
	
	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 销售单价
	 */
	private double unitPrice;

	/**
	 * 退款手续费
	 */
	private double refundFeePrice;

	/**
	 * 退款数量
	 */
	private int refundAmount;

	/**
	 * 退款手续费总额
	 */
	private double refundFeeTotalPrice;

	/**
	 * 返款金额（发生退款业务后，实际返回给用户的金额）
	 */
	private double returnTotalPrice;

	/**
	 * 联系人姓名
	 */
	private String customerName;

	/**
	 * 联系人手机号
	 */
	private String customerTel;

	/**
	 * 退款用户
	 */
	private String refundUser;

	/**
	 * 退款申请时间
	 */
	private String refundTime;

	/**
	 * 退款审核用户
	 */
	private String refundApplyUser;

	/**
	 * 退款审核时间（审核时间即实际退款时间）
	 */
	private String refundApplyTime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSupplierOrderNo() {
		return supplierOrderNo;
	}

	public void setSupplierOrderNo(String supplierOrderNo) {
		this.supplierOrderNo = supplierOrderNo;
	}

	public String getBuyerOrderNo() {
		return buyerOrderNo;
	}

	public void setBuyerOrderNo(String buyerOrderNo) {
		this.buyerOrderNo = buyerOrderNo;
	}

	public String getScenicName() {
		return scenicName;
	}

	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getRefundFeePrice() {
		return refundFeePrice;
	}

	public void setRefundFeePrice(double refundFeePrice) {
		this.refundFeePrice = refundFeePrice;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public double getRefundFeeTotalPrice() {
		return refundFeeTotalPrice;
	}

	public void setRefundFeeTotalPrice(double refundFeeTotalPrice) {
		this.refundFeeTotalPrice = refundFeeTotalPrice;
	}

	public double getReturnTotalPrice() {
		return returnTotalPrice;
	}

	public void setReturnTotalPrice(double returnTotalPrice) {
		this.returnTotalPrice = returnTotalPrice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getRefundUser() {
		return refundUser;
	}

	public void setRefundUser(String refundUser) {
		this.refundUser = refundUser;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundApplyUser() {
		return refundApplyUser;
	}

	public void setRefundApplyUser(String refundApplyUser) {
		this.refundApplyUser = refundApplyUser;
	}

	public String getRefundApplyTime() {
		return refundApplyTime;
	}

	public void setRefundApplyTime(String refundApplyTime) {
		this.refundApplyTime = refundApplyTime;
	}

	/*******************************************************
	 * Constructor
	 */
	public FinanceRefundDetailsVo() {
		this.unitPrice = 0d;
		this.refundFeePrice = 0d;
		this.refundAmount = 0;
		this.refundFeeTotalPrice = 0d;
		this.returnTotalPrice = 0d;
	}

}