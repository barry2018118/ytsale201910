package com.yuntu.sale.user.po;

import com.yuntu.sale.base.BasePo;

import java.io.Serializable;
import java.math.BigDecimal;

public class InfoEnterprisePo extends BasePo implements Serializable {

	private static final long serialVersionUID = 4657690251759238445L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * parentId 查询结果中  预收款id
	 */
	private Long moneyId;
	/**
	 * 企业类型（0：平台管理、1：运营商、2：供应商、3：分销商）
	 */
	private Integer companyType;

	/**
	 * 上级企业Id（创建企业Id）
	 */
	private Long parentId;

	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * 公司手机号
	 */
	private String phone;
	/**
	 * 状态（1：可用，0：停用）
	 */
	private Integer status;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;
	/**
	 * 预收款
	 */
	private BigDecimal storageMoney;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMoneyId() {
		return moneyId;
	}

	public void setMoneyId(Long moneyId) {
		this.moneyId = moneyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public BigDecimal getStorageMoney() {
		return storageMoney;
	}

	public void setStorageMoney(BigDecimal storageMoney) {
		this.storageMoney = storageMoney;
	}
}