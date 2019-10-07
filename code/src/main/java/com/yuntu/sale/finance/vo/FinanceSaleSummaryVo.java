package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 财务对账单-销售-汇总信息Vo
 * @author snps
 * @date 2018年5月31日 上午9:55:33
 */
public class FinanceSaleSummaryVo implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = -3487493842170375361L;

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
	 * 销售总额
	 */
	private double saleTotalPrice;

	/**
	 * 退款数量
	 */
	private int refundAmount;

	/**
	 * 退款总额
	 */
	private double refundTotalPrice;

	/**
	 * 退款手续费总额
	 */
	private double refundFeeTotalPrice;

	/**
	 * 小计（销售总额 - 退款总额 + 退款手续费总额）
	 */
	private double sumPrice;

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

	public double getRefundTotalPrice() {
		return refundTotalPrice;
	}

	public void setRefundTotalPrice(double refundTotalPrice) {
		this.refundTotalPrice = refundTotalPrice;
	}

	public double getRefundFeeTotalPrice() {
		return refundFeeTotalPrice;
	}

	public void setRefundFeeTotalPrice(double refundFeeTotalPrice) {
		this.refundFeeTotalPrice = refundFeeTotalPrice;
	}

	public double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}

	/*******************************************************
	 * Constructor
	 */
	public FinanceSaleSummaryVo() {
		this.unitPrice = 0d;
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
		this.refundAmount = 0;
		this.refundTotalPrice = 0d;
		this.refundFeeTotalPrice = 0d;
		this.sumPrice = 0d;
	}

	@Override
	public int compareTo(Object o) {
		 if(o instanceof FinanceSaleSummaryVo){
			 FinanceSaleSummaryVo s = (FinanceSaleSummaryVo) o;
			 
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