package com.yuntu.sale.base.po.enterprise;

import java.io.Serializable;
import java.util.Date;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 企业_功能动作_关系
 * 
 * @Description
 * @Table info_enterprise_action
 * @author snps
 * @date 下午5:11:52
 */
public class InfoEnterpriseAction extends BasePo implements Serializable {

	private static final long serialVersionUID = 3378881249357885666L;
	
	private Long id;
	private Long enterpriseId;
	private Long moduleId;
	private Long menuId;
	private Long actionId;
	private Integer isDelete;
	private Integer sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**********************************************
	 * Constructor
	 */
	public InfoEnterpriseAction() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}

	public InfoEnterpriseAction(long enterpriseId, long moduleId, long menuId, long actionId, long currentUserId) {
		this.enterpriseId = enterpriseId;
		this.moduleId = moduleId;
		this.menuId = menuId;
		this.actionId = actionId;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.setCreateId(currentUserId);
		this.setCreateTime(new Date());
		this.setUpdateId(currentUserId);
		this.setUpdateTime(new Date());
	}
	
}