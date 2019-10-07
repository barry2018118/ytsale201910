package com.yuntu.sale.base.po.enterprise;

import java.io.Serializable;
import java.util.Date;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * 企业_功能模块关系
 * 
 * @Description
 * @Table info_enterprise_module
 * @author snps
 * @date 下午4:58:39
 */
public class InfoEnterpriseModule extends BasePo implements Serializable {

	private static final long serialVersionUID = 5650805660169535203L;

	private Long id;
	private Long enterpriseId;
	private Long categoryId;
	private Long moduleId;
	private String moduleName;
	private String moduleIcon;
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

	/******************************************
	 * Constructor
	 */
	public InfoEnterpriseModule() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}

	public InfoEnterpriseModule(long enterpriseId, long moduleId, long currentUserId) {
		this.enterpriseId = enterpriseId;
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