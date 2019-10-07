package com.yuntu.sale.chiefly.vo;

import java.io.Serializable;

/**
 * @Description 简单的柱图Vo
 * @author snps
 * @date 2018年5月9日 下午4:01:21
 */
public class SimpleColumnChartVo implements Serializable {

	private static final long serialVersionUID = 8720941278515786410L;

	/**
	 * categories
	 */
	private String categories;

	/**
	 * name
	 */
	private String name;

	/**
	 * data
	 */
	private String data;

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}