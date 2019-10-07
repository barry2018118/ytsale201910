package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 下级分销商退款汇总数据Vo
 * @author snps
 * @date 2018年6月3日 下午10:26:09
 */
public class ChildRefundDataVo implements Serializable {

	private static final long serialVersionUID = -2870070412187015521L;

	/**
	 * 分销商企业Id
	 */
	private Long childId;

	/**
	 * 分销商企业名称
	 */
	private String childName;

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
	
	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
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
	public ChildRefundDataVo() {
		this.refundAmount = 0;
		this.refundFeeTotalPrice = 0d;
		this.refundTotalPrice = 0d;
	}

}