package com.yuntu.sale.base.vo;

import java.io.Serializable;

public class OperatorExceptionVo extends OperatorMessageVo implements Serializable {

	private static final long serialVersionUID = -6275770649766636412L;

	/*************************************
	 * Constructor
	 */
	public OperatorExceptionVo() {
		this.setFlag(Boolean.FALSE);
	}

	public OperatorExceptionVo(Object message) {
		this.setFlag(Boolean.FALSE);
		this.setMessage(message);
	}

}