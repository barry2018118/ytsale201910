package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yuntu.sale.base.BasePo;

/**
 * @Description 企业-平台资金Po
 * @Table t_enterprise_capital
 * @author snps
 * @date 2018年2月26日 上午11:26:55
 */
public class EnterpriseCapitalPo extends BasePo implements Serializable {

	private static final long serialVersionUID = -8766274472011236154L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	/**
	 * 平台余额（应等于：可提现金额 + 冻结金额）
	 */
	private BigDecimal totalMoney;

	/**
	 * 可提现金额
	 */
	private BigDecimal usableMoney;

	/**
	 * 冻结金额
	 */
	private BigDecimal forzenMoney;

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

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getUsableMoney() {
		return usableMoney;
	}

	public void setUsableMoney(BigDecimal usableMoney) {
		this.usableMoney = usableMoney;
	}

	public BigDecimal getForzenMoney() {
		return forzenMoney;
	}

	public void setForzenMoney(BigDecimal forzenMoney) {
		this.forzenMoney = forzenMoney;
	}
	
	/************************************************************
	 * Constructor
	 */
	public EnterpriseCapitalPo() {
		this.totalMoney = new BigDecimal(0);
		this.usableMoney = new BigDecimal(0);
		this.forzenMoney = new BigDecimal(0);
	}

	public EnterpriseCapitalPo(Long enterpriseId) {
		new EnterpriseCapitalPo();
		this.enterpriseId = enterpriseId;
	}
	
}