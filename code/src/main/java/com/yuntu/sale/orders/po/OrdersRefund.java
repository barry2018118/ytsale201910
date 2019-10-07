
package com.yuntu.sale.orders.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 退款主表Entity
 * @author zy
 * @version 2018-04-23
 */
public class OrdersRefund implements Serializable {

	private static final long serialVersionUID = -909038587362310895L;
	private Long id ; //id
	private Long enterpriseId;		// 发起退款的企业Id
	private Long ordersId;		// 主订单Id
	private Long sproductId;		// 退款产品Id
	private Integer num;		// 退款产品数量
	private String notes;		// 退款原因（备注）
	private Integer status;		// 状态（0：待审核，1：审核通过，2：审核不通过）
	private Long createId;		// 发起退款人（操作人）
	private Integer createTime;		// 发起退款时间
	private String createTimetime;		// 发起退款时间
	private Long auditId;		// 退款审核人
	private Integer auditTime;		// 退款审核时间
	private String audiTimetime;
	private String serialNumber;		// 流水号（避免第三方重复退款使用）
	
	public OrdersRefund() {
		this.createTime = (int) (System.currentTimeMillis() /1000);
		this.serialNumber = "11";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimetime() {
		return createTimetime;
	}

	public void setCreateTimetime(String createTimetime) {
		this.createTimetime = createTimetime;
	}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public Integer getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Integer auditTime) {
		this.auditTime = auditTime;
	}

	public String getAudiTimetime() {
		return audiTimetime;
	}

	public void setAudiTimetime(String audiTimetime) {
		this.audiTimetime = audiTimetime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "OrdersRefund{" +
				"id=" + id +
				", enterpriseId=" + enterpriseId +
				", ordersId=" + ordersId +
				", sproductId=" + sproductId +
				", num=" + num +
				", notes='" + notes + '\'' +
				", status=" + status +
				", createId=" + createId +
				", createTime=" + createTime +
				", createTimetime='" + createTimetime + '\'' +
				", auditId=" + auditId +
				", auditTime=" + auditTime +
				", audiTimetime='" + audiTimetime + '\'' +
				", serialNumber='" + serialNumber + '\'' +
				'}';
	}
}