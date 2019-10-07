package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 下级分销商销售汇总数据Vo
 * @author snps
 * @date 2018年6月3日 下午10:21:08
 */
public class ChildSaleDataVo implements Serializable {

	private static final long serialVersionUID = -1878421988966988484L;

	/**
	 * 分销商企业Id
	 */
	private Long childId;

	/**
	 * 分销商企业名称
	 */
	private String childName;

	/**
	 * 销售数量
	 */
	private int saleAmount;

	/**
	 * 销售金额
	 */
	private double saleTotalPrice;

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

	/*******************************************************
	 * Constructor
	 */
	public ChildSaleDataVo() {
		this.saleAmount = 0;
		this.saleTotalPrice = 0d;
	}
	
}