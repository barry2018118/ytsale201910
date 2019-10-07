package com.yuntu.sale.manage.vo.user;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;

public class InfoUserVo extends BasePo implements Serializable {
	private static final long serialVersionUID = -4138920796901576788L;

	private Long id;//

	private String username;// 用户名

	private String password;// 密码

	private Integer typeId;// 用户类型
	
	private String name;// 姓名

	private String phone;// 移动电话

	private Integer isDelete;// 是否删除（1：是，0：否）

	private Long roleId;// 角色id
	
	private String title;//角色名

	private Long enterpriseId;// 所属企业id
	
	private String enterpriseName;//企业名称
	
	private String address;//机构地址

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}