package com.yuntu.sale.orders.vo;

import java.io.Serializable;

/**
 * @Description 退款添加展示
 * @author snps
 * @date 2018年5月12日 上午10:39:54
 */
public class OrderRefundAddCardVo implements Serializable {

	private static final long serialVersionUID = -313071562805404391L;
	private Long id;
	private String name;
	private String card;
	private String code;

	public OrderRefundAddCardVo() {
	}

	public OrderRefundAddCardVo(Long id, String name, String card, String code) {
		this.id = id;
		this.name = name;
		this.card = card;
		this.code = code;
	}

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