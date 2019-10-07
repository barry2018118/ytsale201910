package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 简单的商品Vo
 * @author snps
 * @date 2018年5月4日 上午11:17:39
 */
public class SimpleProductVo implements Serializable {

	private static final long serialVersionUID = -9011124701963828151L;

	/**
	 * 商品Id
	 */
	private Long productId;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品类型
	 */
	private String productCategory;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

}