package com.yuntu.sale.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 财务-明细表Vo
 * @author snps
 * @date 2018年5月26日 下午5:35:35
 */
public class FinanceDetailsReportVo implements Serializable {

	private static final long serialVersionUID = -7633095510871852944L;

	/**
	 * 序号
	 */
	private Integer no;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 订单来源
	 */
	private String orderSoruce;

	/**
	 * 第三方订单号
	 */
	private String thirdOrderNo;

	/**
	 * 联系人（取票人）
	 */
	private String linkman;

	/**
	 * 身份证号
	 */
	private String linkmanNo;

	/**
	 * 联系方式
	 */
	private String linkmanPhone;

	/**
	 * 订单总数（实际应为：下单数量）
	 */
	private Integer saleNum;

	/**
	 * 商品单价
	 */
	private BigDecimal price;

	/**
	 * 检票数量
	 */
	private Integer consumeNum;

	/**
	 * 检票金额
	 */
	private BigDecimal consumePrice;

	/**
	 * 创建人（实际下单企业）
	 */
	private String createEnterprise;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderSoruce() {
		return orderSoruce;
	}

	public void setOrderSoruce(String orderSoruce) {
		this.orderSoruce = orderSoruce;
	}

	public String getThirdOrderNo() {
		return thirdOrderNo;
	}

	public void setThirdOrderNo(String thirdOrderNo) {
		this.thirdOrderNo = thirdOrderNo;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanNo() {
		return linkmanNo;
	}

	public void setLinkmanNo(String linkmanNo) {
		this.linkmanNo = linkmanNo;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
	}

	public BigDecimal getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(BigDecimal consumePrice) {
		this.consumePrice = consumePrice;
	}

	public String getCreateEnterprise() {
		return createEnterprise;
	}

	public void setCreateEnterprise(String createEnterprise) {
		this.createEnterprise = createEnterprise;
	}

}