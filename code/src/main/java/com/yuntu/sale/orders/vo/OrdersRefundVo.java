package com.yuntu.sale.orders.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款Entity
 * @author zy
 * @version 2018-04-23
 */
public class OrdersRefundVo implements Serializable {

	private static final long serialVersionUID = 5292338805021596236L;
	private Long id;		// 订单Id
	private Integer num;		// 退款数量
	private BigDecimal money;		// 退款单价
	private BigDecimal price;		// 退款总价
	private String notes;           // 退款原因

	public OrdersRefundVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "OrdersRefundVo{" +
				"id=" + id +
				", num=" + num +
				", money=" + money +
				", price=" + price +
				", notes='" + notes + '\'' +
				'}';
	}
}