package com.yuntu.sale.orders.vo;

import java.io.Serializable;

/**
 * 消费Entity
 * @author zy
 * @version 2018-04-23
 */
public class OrdersCardConsumeVo implements Serializable {

	private static final long serialVersionUID = 2443526990455828151L;
	private String code;		// 码
	private String time;	// 数量
	private Integer num;	// 时间

	public OrdersCardConsumeVo() {
	}

	public OrdersCardConsumeVo(String code, String time, Integer num) {
		this.code = code;
		this.time = time;
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}