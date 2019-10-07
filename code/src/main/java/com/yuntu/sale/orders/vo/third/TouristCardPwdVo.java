package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;

/**
 * @Description 实名制游客信息-码信息
 * @author snps
 * @date 2018年5月22日 下午3:55:18
 */
public class TouristCardPwdVo implements Serializable {

	private static final long serialVersionUID = 1288504439591525480L;

	/**
	 * 游客-姓名
	 */
	private String name;

	/**
	 * 游客-身份证
	 */
	private String card;

	/**
	 * 电子码
	 */
	private String cardPwd;

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

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	
	/************************************************
	 * Constructor
	 */
	public TouristCardPwdVo() {
	}
	
	public TouristCardPwdVo(String name, String card, String cardPwd) {
		this.name = name;
		this.card = card;
		this.cardPwd = cardPwd;
	}
	
}