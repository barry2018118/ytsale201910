package com.yuntu.sale.base.po.role;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;

public class InfoRole extends BasePo implements Serializable{

	private static final long serialVersionUID = -5276547190121774593L;

	private Long  id;
	
	/**
	 * 角色名称
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String descript;
	
	/**
	 * 是否删除（1：是，0：否）
	 */
	private int isDelete;
	
	/**
	 * 排序号
	 */
	private int sortNo;
	
	/**
	 * 企业Ide
	 */
	private Long enterpriseId;
	
	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}