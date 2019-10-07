package com.yuntu.sale.ifconf.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 企业接口配置表
 * @Table t_platform_interface
 * @author Jack.jiang
 * @date 2018年3月23日 
 */
public class EnterprisePlatformInterfacePo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		
	
	/** 别名 */	
	private String name;
		
    /** 企业id */	
	private Long enterpriseId;		
		
    /** 类型： 1 渠道 2 供应商 */
	private Integer typeId;		
		
    /** 基础配置Id */	
	private Integer interfaceId;		
	
	/** 接口名称 */	
	private String interfaceName;
	
    /** 类型为供应商：接口URL访问后缀；渠道则为：渠道核销、异步发码标识 */	
	private String interfaceEname;		
		
    /** 第三方账号或唯一标识 */
	private String acountNo;		
		
    /** 所属公司 */	
	private String company;		
		
    /** 接口配置信息 */
	private String config;		
		
    /** 是否发码 1 不发码  2 发码 */	
	private Integer sendmes;		
		
    /** 排序 */	
	private Integer sort;		
		
    /** 状态 1 关闭   5正常 */
	private Integer status;		
		
    /** created_at */	
	private Date createdAt;		
		
    /** created_by */	
	private String createdBy;		
		
    /** created_id */	
	private Long createdId;		
		
    /** updated_at */	
	private Date updatedAt;		
		
    /** updated_id */	
	private Long updatedId;		
		
    /** updated_by */	
	private String updatedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}
	
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceEname() {
		return interfaceEname;
	}

	public void setInterfaceEname(String interfaceEname) {
		this.interfaceEname = interfaceEname;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Integer getSendmes() {
		return sendmes;
	}

	public void setSendmes(Integer sendmes) {
		this.sendmes = sendmes;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedId() {
		return createdId;
	}

	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(Long updatedId) {
		this.updatedId = updatedId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}		
		
}