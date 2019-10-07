package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 企业-预收款Po
 * @Table t_enterprise_storage_money
 * @author snps
 * @date 2018年3月8日 上午9:50:01
 */
public class EnterpriseStorageMoneyPo implements Serializable {

	private static final long serialVersionUID = 83185926719184702L;

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
	 * 当前预收款
	 */
	private BigDecimal storageMoney;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

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

	public BigDecimal getStorageMoney() {
		return storageMoney;
	}

	public void setStorageMoney(BigDecimal storageMoney) {
		this.storageMoney = storageMoney;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	
	/*************************************************
	 * Constructor
	 */
	public EnterpriseStorageMoneyPo() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}
	
}