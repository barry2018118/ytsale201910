package com.yuntu.sale.info.po;

import java.io.Serializable;

/**
 * @Description 区域（省、市）信息Po
 * @Table t_city
 * @author snps
 * @date 2018年2月27日 下午2:33:43
 */
public class AreaPo implements Serializable {

	private static final long serialVersionUID = 5558224108075115069L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 所属省Id
	 */
	private Long parentId;

	/**
	 * 省/市名称
	 */
	private String name;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 排序号
	 */
	private Integer sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}