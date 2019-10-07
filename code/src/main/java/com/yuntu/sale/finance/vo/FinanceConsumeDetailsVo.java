package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 财务对账单-消费-消费明细Vo
 * @author snps
 * @date 2018年5月31日 上午9:59:25
 */
public class FinanceConsumeDetailsVo implements Serializable {

	private static final long serialVersionUID = 8201064495198464333L;

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
	 * 电子码
	 */
	private String code;

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
	 * 消费单价
	 */
	private double consumePrice;

	/**
	 * 消费数量
	 */
	private double consumeAmount;

	/**
	 * 消费总额
	 */
	private double consumeTotalPrice;

	/**
	 * 联系人姓名
	 */
	private String customerName;

	/**
	 * 联系人手机号
	 */
	private String customerTel;

	/**
	 * 联系人身份证号
	 */
	private String idCard;

	/**
	 * 消费时间
	 */
	private String consumeTime;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public double getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(double consumePrice) {
		this.consumePrice = consumePrice;
	}

	public double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public double getConsumeTotalPrice() {
		return consumeTotalPrice;
	}

	public void setConsumeTotalPrice(double consumeTotalPrice) {
		this.consumeTotalPrice = consumeTotalPrice;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}

	/*******************************************************
	 * Constructor
	 */
	public FinanceConsumeDetailsVo() {
		this.consumePrice = 0d;
		this.consumeAmount = 0;
		this.consumeTotalPrice = 0d;
	}
	
}