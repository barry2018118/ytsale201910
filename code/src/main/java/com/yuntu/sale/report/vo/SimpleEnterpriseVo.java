package com.yuntu.sale.report.vo;

import java.io.Serializable;

/**
 * @Description 简单的企业Vo
 * @author snps
 * @date 2018年5月8日 上午9:50:58
 */
public class SimpleEnterpriseVo implements Serializable {

	private static final long serialVersionUID = 827253986342635802L;

	/**
	 * 企业Id
	 */
	private Long id;

	/**
	 * 企业名称
	 */
	private String name;

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

}