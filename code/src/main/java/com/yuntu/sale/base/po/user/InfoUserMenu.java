package com.yuntu.sale.base.po.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 用户_功能菜单_关系
 * 
 * @Description
 * @Table info_user_menu
 * @author snps
 * @date 下午2:02:50
 */
public class InfoUserMenu extends BasePo implements Serializable {
	private static final long serialVersionUID = 2810876257738240095L;
	
	private Long id;
	private Long enterpriseId;
	private Long userId;
	private Long moduleId;
	private Long menuId;
	private String menuName;
	private String menuIcon;
	private String menuUrl;
	private Integer isDelete;
	private Integer sortNo;
	private List<InfoUserAction> userAction;

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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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

	public List<InfoUserAction> getUserAction() {
		return userAction;
	}

	public void setUserAction(List<InfoUserAction> userAction) {
		this.userAction = userAction;
	}

	/**********************************************
	 * Constructor
	 */
	public InfoUserMenu() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
	public InfoUserMenu(long enterpriseId, long userId, long moduleId, long menuId, long currentUserId) {
		this.enterpriseId = enterpriseId;
		this.userId = userId;
		this.moduleId = moduleId;
		this.menuId = menuId;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.setCreateId(currentUserId);
		this.setCreateTime(new Date());
		this.setUpdateId(currentUserId);
		this.setUpdateTime(new Date());
	}
	
}