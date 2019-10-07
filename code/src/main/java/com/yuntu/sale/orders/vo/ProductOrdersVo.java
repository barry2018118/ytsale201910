package com.yuntu.sale.orders.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表Entity
 * @author zy
 * @version 2018-04-23
 */
public class ProductOrdersVo implements Serializable {

	private static final long serialVersionUID = 1817548239948874825L;
	private Long sproductId;		// 产品Id
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date consumeTime;		// 预订游玩日期
	private Integer num;		// 数量
	private BigDecimal unitPrice;		// 产品单价
	private BigDecimal price;		// 订单总价
	private String customerName;		// 取票人
	private String tel;		// 取票人手机（用于接收短信）
	private String idcard;		// 身份证
	private String notes;		// 客户备注

	public ProductOrdersVo() {
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "ProductOrdersVo{" +
				"sproductId=" + sproductId +
				", consumeTime=" + consumeTime +
				", num=" + num +
				", unitPrice=" + unitPrice +
				", price=" + price +
				", customerName='" + customerName + '\'' +
				", tel='" + tel + '\'' +
				", idcard='" + idcard + '\'' +
				", notes='" + notes + '\'' +
				'}';
	}
}