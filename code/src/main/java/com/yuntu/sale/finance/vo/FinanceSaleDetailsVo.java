package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 财务对账单-销售-销售明细Vo
 * @author snps
 * @date 2018年5月31日 上午9:57:31
 */
public class FinanceSaleDetailsVo implements Serializable {

	private static final long serialVersionUID = 8118774772487969160L;

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
	 * 销售数量
	 */
	private int saleAmount;

	/**
	 * 销售总额
	 */
	private double saleTotalPrice;

	/**
	 * 联系人姓名
	 */
	private String customerName;

	/**
	 * 联系人手机号
	 */
	private String customerTel;

	/**
	 * 下单用户（下单的公司账号）
	 */
	private String orderUser;

	/**
	 * 下单时间
	 */
	private String orderTime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

	public double getSaleTotalPrice() {
		return saleTotalPrice;
	}

	public void setSaleTotalPrice(double saleTotalPrice) {
		this.saleTotalPrice = saleTotalPrice;
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

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/*******************************************************
	 * Constructor
	 */
	public FinanceSaleDetailsVo() {
		this.unitPrice = 0d;
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
	}

}