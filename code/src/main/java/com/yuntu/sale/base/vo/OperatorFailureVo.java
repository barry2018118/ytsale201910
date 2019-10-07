package com.yuntu.sale.base.vo;

import java.io.Serializable;

public class OperatorFailureVo extends OperatorMessageVo implements Serializable {

	private static final long serialVersionUID = -6275770649766636412L;

	/*************************************
	 * Constructor
	 */
	public OperatorFailureVo() {
		this.setFlag(Boolean.FALSE);
	}

	public OperatorFailureVo(Object message) {
		this.setFlag(Boolean.FALSE);
		this.setMessage(message);
	}

}