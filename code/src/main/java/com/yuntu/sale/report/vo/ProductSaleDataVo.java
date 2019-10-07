package com.yuntu.sale.report.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 商品销售数据Vo
 * @author snps
 * @date 2018年5月4日 上午10:40:21
 */
public class ProductSaleDataVo implements Serializable {

	private static final long serialVersionUID = 63507525526938186L;

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
	private String productCategory;

	/**
	 * 购买单价
	 */
	private BigDecimal buyPrice;

	/**
	 * 订单数
	 */
	private Integer ordersNum;

	/**
	 * 销售数量
	 */
	private Integer saleNum;

	/**
	 * 已消费数量
	 */
	private Integer consumeNum;

	/**
	 * 退款数量
	 */
	private Integer refundNum;

	/**
	 * 销售金额
	 */
	private BigDecimal saleMoney;

	/**
	 * 已消费金额
	 */
	private BigDecimal consumeMoney;

	/**
	 * 退款手续费
	 */
	private BigDecimal refundDeductMoney;

	/**
	 * 退款金额
	 */
	private BigDecimal refundMoney;

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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getOrdersNum() {
		return ordersNum;
	}

	public void setOrdersNum(Integer ordersNum) {
		this.ordersNum = ordersNum;
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

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}

	public BigDecimal getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}

	public BigDecimal getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(BigDecimal consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public BigDecimal getRefundDeductMoney() {
		return refundDeductMoney;
	}

	public void setRefundDeductMoney(BigDecimal refundDeductMoney) {
		this.refundDeductMoney = refundDeductMoney;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	/***************************************************
	 * Constructor
	 */
	public ProductSaleDataVo() {
		this.ordersNum = 0;
		this.saleNum = 0;
		this.consumeNum = 0;
		this.refundNum = 0;
		this.saleMoney = new BigDecimal(new Integer(0));
		this.consumeMoney = new BigDecimal(new Integer(0));
		this.refundDeductMoney = new BigDecimal(new Integer(0));
		this.refundMoney = new BigDecimal(new Integer(0));
	}

}