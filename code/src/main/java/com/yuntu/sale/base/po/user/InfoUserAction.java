package com.yuntu.sale.base.po.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 用户_功能动作_关系
 * 
 * @Description
 * @Table info_user_action
 * @author snps
 * @date 下午2:05:24
 */
public class InfoUserAction extends BasePo implements Serializable {
	private static final long serialVersionUID = -1828039304726982756L;
	private Long id;
	private Long enterpriseId;
	private Long userId;
	private Long moduleId;
	private Long menuId;
	private Long actionId;
	private String actionName;
	private String actionUrl;
	private Integer isDelete;
	private Integer sortNo;
	private List<InfoUserButton> userButton;

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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
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

	public List<InfoUserButton> getUserButton() {
		return userButton;
	}

	public void setUserButton(List<InfoUserButton> userButton) {
		this.userButton = userButton;
	}

	/**********************************************
	 * Constructor
	 */
	public InfoUserAction() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
	public InfoUserAction(long enterpriseId, long userId, long moduleId, long menuId, long actionId, long currentUserId) {
		this.enterpriseId = enterpriseId;
		this.userId = userId;
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