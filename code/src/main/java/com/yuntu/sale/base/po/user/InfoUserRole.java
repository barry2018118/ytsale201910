package com.yuntu.sale.base.po.user;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;

/**
 * 用户_权限_关系表
 */
public class InfoUserRole extends BasePo implements Serializable {
	private static final long serialVersionUID = -1131103944991166240L;
	private Long id;	
	private Long userId;//用户id	
	private Long roleId;//权限id	
	private Long sortNo;//排序号	
	private Long enterpriseId;//所属企业id	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getSortNo() {
		return sortNo;
	}
	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
}