package com.yuntu.sale.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单详细页 退款展示vo
 * @author zy
 * @version 2018-04-23
 */
public class OrdersMyEnterpriseCardVo implements Serializable {

	private static final long serialVersionUID = 2543855998682648796L;
	private String refundTime;//退款时间
	private String refundstatus;//退款状态
	private String refundnum;//退款数量

	public OrdersMyEnterpriseCardVo() {
	}

	public OrdersMyEnterpriseCardVo(String refundTime, String refundstatus, String refundnum) {
		this.refundTime = refundTime;
		this.refundstatus = refundstatus;
		this.refundnum = refundnum;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}

	public String getRefundnum() {
		return refundnum;
	}

	public void setRefundnum(String refundnum) {
		this.refundnum = refundnum;
	}
}