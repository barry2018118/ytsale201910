package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;

/**
 * @Description
 * @author snps
 * @date 2018年5月22日 下午4:18:59
 */
public class ThirdOrdersRefundMessageVo implements Serializable {

	private static final long serialVersionUID = -2499753574387168519L;

	/**
	 * 操作返回信息-编号
	 */
	private Integer code;

	/**
	 * 操作返回信息-文字信息
	 */
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/****************************************
	 * Constructor
	 * 		1、直接退款成功？
			2、申请退款，已在审核中？
			3、已经退款？
			4、审核中？
			5、不能退款？
			6、退款数量超量？
	 */
	public ThirdOrdersRefundMessageVo() {
	}
	
	public ThirdOrdersRefundMessageVo(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}