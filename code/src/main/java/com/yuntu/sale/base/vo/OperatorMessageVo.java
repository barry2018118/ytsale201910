package com.yuntu.sale.base.vo;

import java.io.Serializable;

/**
 * @Description 操作信息Vo
 * @author snps
 * @date 2018年2月22日 上午10:14:37
 */
public class OperatorMessageVo implements Serializable {

	private static final long serialVersionUID = 8515708023427521841L;

	/**
	 * 操作标识
	 */
	private Boolean flag;

	/**
	 * 操作返回信息
	 */
	private Object message;

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}