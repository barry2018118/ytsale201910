package com.yuntu.sale.orders.vo;

import java.io.Serializable;

/**
 * @Description 商品日历价格Vo 
 * @author snps
 * @date 2018年5月10日 下午5:59:21
 */
public class ProductCalendarPriceVo implements Serializable {

	private static final long serialVersionUID = -5115056462683517124L;

	/**
	 * 操作成功标识
	 */
	private String success;
	
	/**
	 * 操作返回信息
	 */
	private String msg;
	
	/**
	 * 价格
	 */
	private String data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}