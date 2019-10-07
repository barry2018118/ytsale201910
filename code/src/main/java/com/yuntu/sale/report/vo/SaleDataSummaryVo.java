package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 销售汇总报表Vo
 * @author snps
 * @date 2018年6月3日 下午7:43:19
 */
public class SaleDataSummaryVo implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = -6881209768066083881L;

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
	 * 销售单价
	 */
	private double unitPrice;

	/**
	 * 销售数量
	 */
	private int saleAmount;

	/**
	 * 销售金额
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
	public SaleDataSummaryVo() {
		this.unitPrice = 0d;
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
		this.refundAmount = 0;
		this.refundFeeTotalPrice = 0d;
		this.refundTotalPrice = 0d;
	}

	@Override
	public int compareTo(Object o) {
		 if(o instanceof SaleDataSummaryVo){
			 SaleDataSummaryVo s = (SaleDataSummaryVo) o;
			 
			 if(this.getScenicName().compareTo(s.getScenicName()) > 0) {
				 return 1 ;
			 } else if(this.getScenicName().compareTo(s.getScenicName()) == 0) {
				 if(this.getProductName().compareTo(s.getProductName()) > 0) {
					 return 1;
				 } else if(this.getProductName().compareTo(s.getProductName()) == 0) {
					 if(this.getUnitPrice() < s.getUnitPrice()) {
						 return 1;
					 } else if(this.getUnitPrice() == s.getUnitPrice()) {
						 return 0;
					 } else {
						 return -1;
					 }
				 } else {
					 return -1;
				 }
			 } else {
				 return -1;
			 }
		 }
		return 1;
	}
	
}