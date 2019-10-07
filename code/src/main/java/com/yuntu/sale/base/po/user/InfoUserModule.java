package com.yuntu.sale.base.po.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 用户_功能模块关系
 * 
 * @Description
 * @Table info_user_module
 * @author snps
 * @date 下午2:00:12
 */
public class InfoUserModule extends BasePo implements Serializable {
	private static final long serialVersionUID = 4384727672130208550L;
	
	private Long id;
	private Long enterpriseId;
	private Long userId;
	private Long categoryId;
	private Long moduleId;
	private String moduleName;
	private String moduleIcon;
	private String moduleUrl;
	private Integer isDelete;
	private Integer sortNo;
	private List<InfoUserMenu> userMenu;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
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

	public List<InfoUserMenu> getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(List<InfoUserMenu> userMenu) {
		this.userMenu = userMenu;
	}

	/******************************************
	 * Constructor
	 */
	public InfoUserModule() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
	public InfoUserModule(long enterpriseId, long userId, long moduleId, long currentUserId) {
		this.enterpriseId = enterpriseId;
		this.userId = userId;
		this.categoryId = 0L;
		this.moduleId = moduleId;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.setCreateId(currentUserId);
		this.setCreateTime(new Date());
		this.setUpdateId(currentUserId);
		this.setUpdateTime(new Date());
	}
	
}