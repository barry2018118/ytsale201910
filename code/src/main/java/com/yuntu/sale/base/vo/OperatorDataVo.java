package com.yuntu.sale.base.vo;

import java.io.Serializable;

/**
 * @Description 操作返回数据Vo
 * @author snps
 * @date 2018年2月27日 上午9:26:44
 */
public class OperatorDataVo implements Serializable {

	private static final long serialVersionUID = -5478561415062321896L;

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
	
	/************************************************
	 * Constructor
	 */
	public OperatorDataVo(boolean flag) {
		this.flag = flag;
	}

}