package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 企业-预收款日志Po
 * @Table t_enterprise_storage_log
 * @author snps
 * @date 2018年3月8日 上午9:55:47
 */
public class EnterpriseStorageLogPo implements Serializable {

	private static final long serialVersionUID = -8230573767898391363L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 上级企业Id
	 */
	private Long parentId;

	/**
	 * 下级企业Id
	 */
	private Long childId;

	/**
	 * 之前预收款
	 */
	private BigDecimal beforeMoney;

	/**
	 * 本次预收款
	 */
	private BigDecimal storageMoney;

	/**
	 * 之后预收款
	 */
	private BigDecimal afterMoney;

	/**
	 * 创建人Id
	 */
	private Long createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public BigDecimal getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(BigDecimal beforeMoney) {
		this.beforeMoney = beforeMoney;
	}

	public BigDecimal getStorageMoney() {
		return storageMoney;
	}

	public void setStorageMoney(BigDecimal storageMoney) {
		this.storageMoney = storageMoney;
	}

	public BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	/*****************************************************
	 * Constructor
	 */
	public EnterpriseStorageLogPo() {
		this.createTime = new Date();
	}

}