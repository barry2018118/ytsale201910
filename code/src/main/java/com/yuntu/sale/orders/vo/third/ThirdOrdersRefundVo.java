package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;

/**
 * @Description 第三方退款Vo
 * @author snps
 * @date 2018年5月22日 下午1:32:07
 */
public class ThirdOrdersRefundVo implements Serializable {

	private static final long serialVersionUID = 633907045850673097L;

	/**
	 * 发起退款企业Id（最底层的购买企业Id）
	 */
	private Long enterpriseId;

	/**
	 * 订单编号（通过这个在t_orders表中查询唯一订单信息，获取订单Id）
	 */
	private String orderno;

	/**
	 * 退款数量
	 */
	private Integer refundQuantity;

	/**
	 * 退款原因
	 */
	private String refundReason;

	/**
	 * 退款流水号（第三方系统的）
	 */
	private String refundSeq;

	public String[] getCodeIds() {
		return codeIds;
	}

	public void setCodeIds(String[] codeIds) {
		this.codeIds = codeIds;
	}

	/**
	 * 实名制退款CodeIds
	 * @return
	 */
	private String[] codeIds;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Integer getRefundQuantity() {
		return refundQuantity;
	}

	public void setRefundQuantity(Integer refundQuantity) {
		this.refundQuantity = refundQuantity;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getRefundSeq() {
		return refundSeq;
	}

	public void setRefundSeq(String refundSeq) {
		this.refundSeq = refundSeq;
	}

}