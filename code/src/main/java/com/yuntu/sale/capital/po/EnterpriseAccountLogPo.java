package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Table t_enterprise_account_log
 * @author snps
 * @date 2018年3月8日 下午1:58:16
 */
public class EnterpriseAccountLogPo implements Serializable {

	private static final long serialVersionUID = 5408968743003400059L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 下级企业Id
	 */
	private Long childId;

	/**
	 * 上级企业Id
	 */
	private Long parentId;

	/**
	 * 业务行为类型
	 */
	private Long actionType;

	/**
	 * 具体的行为id
	 */
	private Long actionId;

	/**
	 * 资金变动方式（0：预收款、1：平台资金）
	 */
	private Integer capitalType;

	/**
	 * 变动前金额
	 */
	private BigDecimal oldPrice;
	
	/**
	 * 本次变动金额
	 */
	private BigDecimal price;
	
	/**
	 * 变动后金额
	 */
	private BigDecimal currentPrice;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 使用终端
	 */
	private String terminal;
	
	/**
	 * 移动电话系统
	 */
	private String phoneSystem;

	/**
	 * 具体的行为描述
	 */
	private String actionDesc;

	private String enterpriseName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getActionType() {
		return actionType;
	}

	public void setActionType(Long actionType) {
		this.actionType = actionType;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(Integer capitalType) {
		this.capitalType = capitalType;
	}
	
	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getPhoneSystem() {
		return phoneSystem;
	}

	public void setPhoneSystem(String phoneSystem) {
		this.phoneSystem = phoneSystem;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/****************************************************
	 * Constructor
	 */
	public EnterpriseAccountLogPo() {
		this.createTime = new Date();
		this.phoneSystem = null;
		this.actionDesc = null;
	}
	
}