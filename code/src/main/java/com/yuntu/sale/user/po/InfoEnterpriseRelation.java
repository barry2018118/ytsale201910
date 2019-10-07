package com.yuntu.sale.user.po;

import java.io.Serializable;

/**
 * @Description 企业关系Po
 * @Table t_enterprise_relation
 * @author snps
 * @date 2018年4月15日 下午1:46:19
 */
public class InfoEnterpriseRelation implements Serializable {

	private static final long serialVersionUID = 1505726672622702693L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 下级Id
	 */
	private Long childId;

	/**
	 * 上级Id
	 */
	private Long parentId;
	
	/**
	 * 创建企业Id
	 */
	private Long createEnterprise;
	
	/**
	 * 创建类型（2：创建供应商，3：创建分销商）
	 */
	private Integer createType;

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

	public Long getCreateEnterprise() {
		return createEnterprise;
	}

	public void setCreateEnterprise(Long createEnterprise) {
		this.createEnterprise = createEnterprise;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

}