package com.yuntu.sale.base.po.role;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 权限_功能按钮_关系表
 */
public class InfoRoleAction extends BasePo implements Serializable {

	private static final long serialVersionUID = -300592463351507118L;
	
	private Long id;	
	private Long roleId;//权限组id	
	private Long moduleId;//功能模块id	
	private Long menuId;//功能菜单id	
	private Long actionId;//功能（动作）id	
	private Integer sortNo;//排序号（针对button）	
	private Long enterpriseId;//所属公司id	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ( !(other instanceof InfoRoleAction) ) return false;
		InfoRoleAction castOther = (InfoRoleAction) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
    }
	
	/**********************************************
	 * Constructor
	 */
	public InfoRoleAction() {
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
	public InfoRoleAction(long enterpriseId, long roleId, long moduleId, long menuId, long actionId, long currentUserId) {
		this.enterpriseId = enterpriseId;
		this.roleId = roleId;
		this.moduleId = moduleId;
		this.menuId = menuId;
		this.actionId = actionId;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.setCreateId(currentUserId);
		this.setCreateTime(new Date());
		this.setUpdateId(currentUserId);
		this.setUpdateTime(new Date());
	}
}