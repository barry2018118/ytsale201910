package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 充值Po
 * @Table t_recharge
 * @author snps
 * @date 2018年3月8日 上午10:13:45
 */
public class RechargePo implements Serializable {

	private static final long serialVersionUID = -3206242978878978324L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;
	
	/**
	 * 充值终端（手机、PC）
	 */
	private String terminal;
	
	/**
	 * 充值渠道（支付宝、微信、银行卡）
	 */
	private String channel;

	/**
	 * 充值金额
	 */
	private BigDecimal rechargeMoney;

	/**
	 * 费率
	 */
	private BigDecimal rate;

	/**
	 * 费率扣除金额
	 */
	private BigDecimal rateMoney;

	/**
	 * 实际充值金额
	 */
	private BigDecimal actualMoney;

	/**
	 * 创建人Id
	 */
	private Long createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

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
	
	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*****************************************************
	 * Constructor
	 */
	public RechargePo() {
		this.createTime = new Date();
	}
	
	public RechargePo(Long enterpriseId) {
		new RechargePo();
		this.enterpriseId = enterpriseId;
	}
	
}