package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 采购汇总报表Vo
 * @author snps
 * @date 2018年6月3日 下午7:52:02
 */
public class BuyDataSummaryVo implements Serializable {

	private static final long serialVersionUID = 7462610544298885774L;

	/**
	 * 序号
	 */
	private int no;

	/**
	 * 景区名称
	 */
	private String scenicName;

	/**
	 * 商品名称
	 */
	private String productName;
	
	/**
	 * 采购单价
	 */
	private double unitPrice;

	/**
	 * 采购数量
	 */
	private int saleAmount;

	/**
	 * 采购金额
	 */
	private double saleTotalPrice;
	
	/**
	 * 退款数量
	 */
	private int refundAmount;

	/**
	 * 退款手续费
	 */
	private double refundFeeTotalPrice;
	
	/**
	 * 退款金额
	 */
	private double refundTotalPrice;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public double getRefundTotalPrice() {
		return refundTotalPrice;
	}

	public void setRefundTotalPrice(double refundTotalPrice) {
		this.refundTotalPrice = refundTotalPrice;
	}

	/*******************************************************
	 * Constructor
	 */
	public BuyDataSummaryVo() {
		this.unitPrice = 0d;
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
		this.refundAmount = 0;
		this.refundFeeTotalPrice = 0d;
		this.refundTotalPrice = 0d;
	}
	
}