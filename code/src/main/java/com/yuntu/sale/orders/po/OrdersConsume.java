
package com.yuntu.sale.orders.po;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 码核销记录表Entity
 * @author zy
 * @version 2018-04-23
 */
public class OrdersConsume implements Serializable {

	private static final long serialVersionUID = 1865399466439258472L;
	private Long id;            //id
	private Long ordersId;		// 订单Id
	private Long codeId;		// 码Id
	private Long sproductId;		// 产品Id
	private String productSortname;		// 产品名称
	private Long enterpriseId;		// 供应商企业Id
	private Long shopId;		// 商户Id
	private String shopName;		// 商户名称
	private BigDecimal unitPrice;		// 核销单价
	private BigDecimal price;		// 核销总价
	private Integer printnum;		// 核销数
	private Integer status;		// 状态默认4
	private String tuanname;		// 核销源
	private String cardPwd;		// 码
	private Integer isprint;		// 是否打印
	private Integer printtim;		// 核销时间
	private Long operatorUid;		// 操作用户Id
	private String operator;		// 操作用户名
	private String serialNum;		// 流水号
	private String machineId;		// 机器号

	private String printtimName;		// 核销时间

	public OrdersConsume() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public String getProductSortname() {
		return productSortname;
	}

	public void setProductSortname(String productSortname) {
		this.productSortname = productSortname;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPrintnum() {
		return printnum;
	}

	public void setPrintnum(Integer printnum) {
		this.printnum = printnum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTuanname() {
		return tuanname;
	}

	public void setTuanname(String tuanname) {
		this.tuanname = tuanname;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public Integer getIsprint() {
		return isprint;
	}

	public void setIsprint(Integer isprint) {
		this.isprint = isprint;
	}

	public Integer getPrinttim() {
		return printtim;
	}

	public void setPrinttim(Integer printtim) {
		this.printtim = printtim;
	}

	public Long getOperatorUid() {
		return operatorUid;
	}

	public void setOperatorUid(Long operatorUid) {
		this.operatorUid = operatorUid;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getPrinttimName() {
		return printtimName;
	}

	public void setPrinttimName(String printtimName) {
		this.printtimName = printtimName;
	}
}