package com.yuntu.sale.product.po;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 商品类别Po
 * @Table t_product_category
 * @author snps
 * @date 2018年2月14日 下午3:49:05
 */
public class ProductCategory extends BasePo implements Serializable {

	private static final long serialVersionUID = 6372925292973689784L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 产品类别名称
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

	/**********************************************
	 * Constructor
	 */
	public ProductCategory() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_99;
	}

}