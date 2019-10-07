package com.yuntu.sale.base;

import java.io.Serializable;

import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 功能基类Po
 * @author snps
 * @date 2018年2月13日 上午8:39:43
 */
public class BaseFunctionPo extends BasePo implements Serializable {

	private static final long serialVersionUID = 4685387577461364805L;
	

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 请求地址
	 */
	private String url;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 排序号
	 */
	private Integer sortNo;

	/**
	 * 是否可分配标识（1：是，0：否）
	 */
	private Integer isDistribution;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}

	/*****************************************************
	 * Constructor
	 */
	public BaseFunctionPo() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.isDistribution = GrobalConstant.I_NUM_1;
	}

    public static class AlipayConfig {
    }
}