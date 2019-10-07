package com.yuntu.sale.base.po.enterprise;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 企业_功能按钮_关系
 * 
 * @Description
 * @Table info_enterprise_button
 * @author snps
 * @date 下午5:07:54
 */
public class InfoEnterpriseButton extends BasePo implements Serializable {

	private static final long serialVersionUID = 7478733866769326263L;
	
	private Long id;
	private Long enterpriseId;
	private Long moduleId;
	private Long menuId;
	private Long actionId;
	private Long buttonId;
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

	public Long getButtonId() {
		return buttonId;
	}

	public void setButtonId(Long buttonId) {
		this.buttonId = buttonId;
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
	public InfoEnterpriseButton() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
}