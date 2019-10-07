package com.yuntu.sale.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 财务-汇总表Vo
 * @author snps
 * @date 2018年5月26日 下午5:26:01
 */
public class FinanceSummaryReportVo implements Serializable {

	private static final long serialVersionUID = 7691514919486406775L;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品单价
	 */
	private BigDecimal price;

	/**
	 * 订单总数（实际应为：下单数量）
	 */
	private Integer saleNum;

	/**
	 * 已验数量
	 */
	private Integer consumeNum;

	/**
	 * 已验金额
	 */
	private BigDecimal consumeMoney;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
	}

	public BigDecimal getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(BigDecimal consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

}