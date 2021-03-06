package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 分销商销售报表Vo
 * @author snps
 * @date 2018年6月3日 下午7:48:05
 */
public class DistributorSaleDataSummaryVo implements Serializable {

	private static final long serialVersionUID = 1660223683735342L;

	/**
	 * 序号
	 */
	private int no;

	/**
	 * 分销商名称
	 */
	private String enterpriseName;

	/**
	 * 分销商企业Id
	 */
	private Long enterpriseId;

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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	public DistributorSaleDataSummaryVo() {
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
		this.refundAmount = 0;
		this.refundFeeTotalPrice = 0d;
		this.refundTotalPrice = 0d;
	}

}