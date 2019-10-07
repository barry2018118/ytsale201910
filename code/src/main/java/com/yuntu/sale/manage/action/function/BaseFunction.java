package com.yuntu.sale.manage.action.function;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 功能基类
 * @author snps
 * @date 下午8:29:32
 */
public class BaseFunction extends BasePo implements Serializable {

	private static final long serialVersionUID = 5588749426187464059L;
	private Long id;
	private String name;
	private String icon;
	private String url;
	private String description;
	private Integer isDelete;
	private Integer sortNo;
	private Integer isDistribution;

	/**
	 * 标识字段:是否分配功能
	 */
	private String isHave;

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

	public String getIsHave() {
		return isHave;
	}

	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}

	/********************************
	 * Constructor
	 */
	public BaseFunction() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.isDistribution = GrobalConstant.I_NUM_1;
	}
}