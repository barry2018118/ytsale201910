package com.yuntu.sale.base.po.enterprise;

import java.io.Serializable;
import java.util.Date;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 企业_功能菜单_关系
 * 
 * @Description
 * @Table info_enterprise_menu
 * @author snps
 * @date 下午5:07:54
 */
public class InfoEnterpriseMenu extends BasePo implements Serializable {

	private static final long serialVersionUID = 1316157003171249988L;
	private Long id;
	private Long enterpriseId;
	private Long moduleId;
	private Long menuId;
	private String menuName;
	private String menuIcon;
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
	public InfoEnterpriseMenu() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
	public InfoEnterpriseMenu(long enterpriseId, long moduleId, long menuId, long currentUserId) {
		this.enterpriseId = enterpriseId;
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