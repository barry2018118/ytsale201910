package com.yuntu.sale.capital.po;

import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 企业-预收款- 企业信息Po 供应商 - 分销商
 * @Table t_enterprise_storage_money , info_enterprise
 * @author zy
 * @date 2018年3月8日 上午9:50:01
 */
public class EnterpriseStorageMoneyChasePo implements Serializable {

	private static final long serialVersionUID = 1675816182484536109L;
	private Long id;  //企业 id
	private BigDecimal storageMoney; // 当前预收款
	private String name;  //企业名称
	private String phone; //公司负责人手机号
	private Integer status; //企业状态（1：可用，0：停用）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getStorageMoney() {
		return storageMoney;
	}

	public void setStorageMoney(BigDecimal storageMoney) {
		this.storageMoney = storageMoney;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public EnterpriseStorageMoneyChasePo() {
	}
}