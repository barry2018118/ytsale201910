package com.yuntu.sale.info.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 资金渠道Po
 * @author snps
 * @date 2018年3月8日 上午9:06:57
 */
public class CapitalChannelPo implements Serializable {

	private static final long serialVersionUID = 4724818351460007529L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 资金渠道名称
	 */
	private String name;

	/**
	 * 费率
	 */
	private BigDecimal rate;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 请求地址
	 */
	private String requestUrl;

	/**
	 * 回调地址
	 */
	private String callbackUrl;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private String isDelete;

	/**
	 * 排序号
	 */
	private String sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

}