package com.yuntu.sale.user.po;

import java.io.Serializable;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 企业信息Po
 * @Table info_enterprise
 * @author snps
 * @date 2018年2月24日 下午3:29:59
 */
public class InfoEnterprise extends BasePo implements Serializable {

	private static final long serialVersionUID = 8141660406513311387L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 二级域名
	 */
	private String domain;
	
	/**
	 * 企业类型（0：平台管理、1：运营商、2：供应商、3：分销商）
	 */
	private Integer companyType;
	
	/**
	 * 上级企业Id（创建企业Id）
	 */
	private Long parentId;

	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * 公司负责人
	 */
	private String contacterName;

	/**
	 * 负责人手机
	 */
	private String contacterPhone;
	
	/**
	 * 企业邮箱
	 */
	private String email;

	/**
	 * 公司地址
	 */
	private String address;

	/**
	 * 公司简介
	 */
	private String introduction;

	/**
	 * Logo
	 */
	private String logo;

	/**
	 * Banner
	 */
	private String banner;
	
	/**
	 * 平台名称
	 */
	private String platformName;
	
	/**
	 * 客服电话
	 */
	private String customerPhone;

	/**
	 * 省
	 */
	private Long province;
	
	/**
	 * 市
	 */
	private Long city;
	
	/**
	 * 状态（1：可用，0：停用）
	 */
	private Integer status;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;
	
	/**
	 * 用户账号（仅用于为user设值）
	 */
	private String username;
	
	/**
	 * 账号密码（仅用于为user设值）
	 */
	private String password;

	/**
	 * 角色Id
	 */
	private Long roleId;
	
	/**
	 * 组中是否已存在商品
	 */
	private String isExist;
	
	/**
	 * 创建企业Id		(add by snps, at 2018-06-05 21:21)
	 */
	private Long createEnterprise ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterPhone() {
		return contacterPhone;
	}

	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}

	public Long getCreateEnterprise() {
		return createEnterprise;
	}

	public void setCreateEnterprise(Long createEnterprise) {
		this.createEnterprise = createEnterprise;
	}

	/**********************************************
	 * Constructor
	 */
	public InfoEnterprise() {
		this.status = GrobalConstant.I_INDEX_USER_STATUS_AVAILABLE;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}

}