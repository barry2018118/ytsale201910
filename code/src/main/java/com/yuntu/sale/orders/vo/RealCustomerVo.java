package com.yuntu.sale.orders.vo;

import java.io.Serializable;

/**
 * @Description 实名制游客Vo
 * @author snps
 * @date 2018年5月12日 上午10:39:54
 */
public class RealCustomerVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034919481013027875L;

	/**
	 * 游客
	 */
	private String consumerCard;

	/**
	 * 游客姓名
	 */
	private String consumerName;

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerCard() {
		return consumerCard;
	}

	public void setConsumerCard(String consumerCard) {
		this.consumerCard = consumerCard;
	}

}