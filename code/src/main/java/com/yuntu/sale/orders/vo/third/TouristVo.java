package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;

/**
 * @Description 实名制游客信息
 * @author snps
 * @date 2018年5月22日 下午1:53:57
 */
public class TouristVo implements Serializable {

	private static final long serialVersionUID = -6141805807357991190L;

	/**
	 * 游客-姓名
	 */
	private String name;
	
	/**
	 * 游客-身份证
	 */
	private String card;

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
	
}