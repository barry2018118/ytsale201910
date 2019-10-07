package com.yuntu.sale.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.coolshow.util.BaseUtil;

/**
 * 消费Entity
 * @author zy
 * @version 2018-04-23
 */
public class EnterpriseOrdersConsumeVo implements Serializable {

	private static final long serialVersionUID = 3035340463656178908L;
	private Long id;           //消费id
	private Long orderId;		// 订单id
	private String orderNo;		// 订单编号
	private String createdTime; //下单时间
	private Long productId;		// 商品id
	private String productName;	// 商品名称
	private Integer num;	// 购买数量
	private String consumeTime;	// 消费时间
	private Integer consumenum;	// 消费数量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getConsumeTime() {
		if(BaseUtil.isEmpty(this.consumeTime)) {
			return "";
		} else {
			return this.consumeTime.substring(0, 19);
		}
	}

	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Integer getConsumenum() {
		return consumenum;
	}

	public void setConsumenum(Integer consumenum) {
		this.consumenum = consumenum;
	}
}