package com.yuntu.sale.user.po;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 用户信息Po
 * @Table info_user 
 * @author snps
 * @date 2018年2月26日 上午11:54:58
 */
public class InfoUser extends BasePo implements Serializable {

	private static final long serialVersionUID = -6540108277357596807L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 账号类型
	 */
	private Integer accountType;

	/**
	 * 是否主账户
	 */
	private Integer isMaster;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 移动电话
	 */
	private String phone;

	/**
	 * 状态（1：启用，0：停用）
	 */
	private Integer status;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 角色Id
	 */
	private Long roleId;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**********************************************
	 * Constructor
	 */
	public InfoUser() {
		this.status = GrobalConstant.I_INDEX_USER_STATUS_AVAILABLE;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}
	
}