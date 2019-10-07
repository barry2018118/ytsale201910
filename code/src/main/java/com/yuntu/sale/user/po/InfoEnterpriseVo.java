package com.yuntu.sale.user.po;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;

public class InfoEnterpriseVo extends BasePo implements Serializable {

	private static final long serialVersionUID = 4657690251759238445L;

	private Long id;
	private String name;// 企业名称
	private String domain;// 域名
	private String contacterName;// 联系人
	private String contacterTel;// 联系电话

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterTel() {
		return contacterTel;
	}

	public void setContacterTel(String contacterTel) {
		this.contacterTel = contacterTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String address;// 地址
	private String introduction;// 公司简介
	private int status;// 状态（1：开通，0：停用）

}