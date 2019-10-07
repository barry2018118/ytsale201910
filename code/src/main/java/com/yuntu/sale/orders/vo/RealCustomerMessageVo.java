package com.yuntu.sale.orders.vo;

import java.io.Serializable;

/**
 * @Description 短信发送类Vo
 * @author snps
 * @date 2018年5月12日 上午10:39:54
 */
public class RealCustomerMessageVo implements Serializable {

	private static final long serialVersionUID = 3399460429712256437L;
	/**
	 * 游客姓名
	 */
	private String name;

	/**
	 * 游客
	 */
	private String card;

	/**
	 * 游客
	 */
	private String code;

	public RealCustomerMessageVo() {
	}

	public RealCustomerMessageVo(String name, String card, String code) {
		this.name = name;
		this.card = card;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}