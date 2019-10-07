package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 第三方订单-操作信息Vo
 * @author snps
 * @date 2018年5月22日 下午2:04:05
 */
public class ThirdOrdersMessageVo implements Serializable {

	private static final long serialVersionUID = -579174640174585798L;

	/**
	 * 操作返回信息-编号
	 */
	private Integer code;

	/**
	 * 操作返回信息-文字信息
	 */
	private String message;

	/**
	 * 非实名制商品-电子码
	 */
	private String cardPwd;

	/**
	 * 实名制商品-电子码
	 */
	private List<TouristCardPwdVo> touristCardPwd;

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

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public List<TouristCardPwdVo> getTouristCardPwd() {
		return touristCardPwd;
	}

	public void setTouristCardPwd(List<TouristCardPwdVo> touristCardPwd) {
		this.touristCardPwd = touristCardPwd;
	}

	/****************************************
	 * Constructor
	 */
	public ThirdOrdersMessageVo() {
		this.touristCardPwd = new ArrayList<TouristCardPwdVo>();
	}

	public ThirdOrdersMessageVo(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.touristCardPwd = new ArrayList<TouristCardPwdVo>();
	}

}