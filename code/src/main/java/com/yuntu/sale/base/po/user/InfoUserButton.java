package com.yuntu.sale.base.po.user;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 用户_功能按钮_关系
 * 
 * @Description
 * @Table info_user_button
 * @author snps
 */
public class InfoUserButton extends BasePo implements Serializable {
	private static final long serialVersionUID = 5727953005090017127L;
	
	private Long id;
	private Long enterpriseId;
	private Long userId;
	private Long moduleId;
	private Long menuId;
	private Long actionId;
	private Long buttonId;
	private String buttonName;
	private String buttonUrl;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonUrl() {
		return buttonUrl;
	}

	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
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
	public InfoUserButton() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
}