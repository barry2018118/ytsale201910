package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 提现Po
 * @Table t_extract
 * @author snps
 * @date 2018年3月8日 下午1:47:44
 */
public class ExtractPo implements Serializable {

	private static final long serialVersionUID = 6378541586417793292L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	/**
	 * 提现卡号
	 */
	private Long bankCardId;

	/**
	 * 申请提现金额
	 */
	private BigDecimal extractMoney;

	/**
	 * 费率
	 */
	private BigDecimal rate;

	/**
	 * 费率扣除金额
	 */
	private BigDecimal rateMoney;

	/**
	 * 实际提现金额
	 */
	private BigDecimal actualMoney;

	/**
	 * 审核状态（0：待审核，1：通过，2：不通过）
	 */
	private Integer status;

	/**
	 * 打款凭证
	 */
	private String voucher;

	/**
	 * 决绝原因
	 */
	private String reason;

	/**
	 * 创建人Id
	 */
	private Long createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 审核人Id
	 */
	private Long auditId;

	/**
	 * 审核时间
	 */
	private Date auditTime;

	/**
	 * 企业name
	 */
	private String name;

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

	public Long getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(Long bankCardId) {
		this.bankCardId = bankCardId;
	}

	public BigDecimal getExtractMoney() {
		return extractMoney;
	}

	public void setExtractMoney(BigDecimal extractMoney) {
		this.extractMoney = extractMoney;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getRateMoney() {
		return rateMoney;
	}

	public void setRateMoney(BigDecimal rateMoney) {
		this.rateMoney = rateMoney;
	}

	public BigDecimal getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String enterpriseName) {
		this.name = enterpriseName;
	}

	/*****************************************************
	 * Constructor
	 */
	public ExtractPo() {
		this.status = GrobalConstant.I_NUM_0;
		this.createTime = new Date();
	}

	public ExtractPo(Long enterpriseId) {
		new ExtractPo();
		this.enterpriseId = enterpriseId;
	}

}