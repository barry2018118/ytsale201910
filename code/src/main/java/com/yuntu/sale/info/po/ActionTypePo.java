package com.yuntu.sale.info.po;

import java.io.Serializable;

/**
 * @Description 业务动作类型Po
 * @author snps
 * @date 2018年3月8日 上午9:39:30
 */
public class ActionTypePo implements Serializable {

	private static final long serialVersionUID = 2491718864931810788L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 业务动作类型
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
	
	/*********************************************
	 * Constructor
	 */
	public ActionTypePo() {
	}
	
	public ActionTypePo(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}