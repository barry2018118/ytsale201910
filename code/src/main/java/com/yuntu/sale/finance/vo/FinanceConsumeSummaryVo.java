package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 财务对账单-消费-汇总信息Vo
 * @author snps
 * @date 2018年5月31日 上午9:58:40
 */
public class FinanceConsumeSummaryVo implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = 4441605037834912477L;

	/**
	 * 序号
	 */
	private int no;

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
	 * 商品类型
	 */
	private int productType;

	/**
	 * 消费单价
	 */
	private double consumePrice;

	/**
	 * 消费数量
	 */
	private int consumeAmount;

	/**
	 * 消费总额
	 */
	private double consumeTotalPrice;

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

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public double getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(double consumePrice) {
		this.consumePrice = consumePrice;
	}

	public int getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(int consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public double getConsumeTotalPrice() {
		return consumeTotalPrice;
	}

	public void setConsumeTotalPrice(double consumeTotalPrice) {
		this.consumeTotalPrice = consumeTotalPrice;
	}

	/*******************************************************
	 * Constructor
	 */
	public FinanceConsumeSummaryVo() {
		this.consumePrice = 0d;
		this.consumeAmount = 0;
		this.consumeTotalPrice = 0d;
	}

	
	@Override
	public int compareTo(Object o) {
		 if(o instanceof FinanceConsumeSummaryVo){
			 FinanceConsumeSummaryVo s = (FinanceConsumeSummaryVo) o;
			 
			 if(this.getScenicName().compareTo(s.getScenicName()) > 0) {
				 return 1 ;
			 } else if(this.getScenicName().compareTo(s.getScenicName()) == 0) {
				 if(this.getProductName().compareTo(s.getProductName()) > 0) {
					 return 1;
				 } else if(this.getProductName().compareTo(s.getProductName()) == 0) {
					 if(this.getConsumePrice() < s.getConsumePrice()) {
						 return 1;
					 } else if(this.getConsumePrice() == s.getConsumePrice()) {
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