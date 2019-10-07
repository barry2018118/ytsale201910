package com.yuntu.sale.ifconf.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 接口基础配置需求
 * @Table t_platform_interface
 * @author Jack.jiang
 * @date 2018年3月22日 
 */
public class PlatformInterfacePo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	 /** 接口名称 */
	private String name;		
		
    /** 类型为供应商：接口URL访问后缀；渠道则为：渠道英文名 */
	private String ename;		
		
    /** 类型： 1 渠道 2 供应商 */
	private Integer typeId;		
		
    /** 接口所需的配置信息 */	
	private String interfaceConfig;		
		
    /** 固定参数 */	
	private String data;			
		
    /** 排序 */
	private Integer sort;		
		
    /** 状态 1 关闭   5正常 */
	private Integer status;		
		
    /** created_at */
	private Date createdAt;		
		
    /** created_id */
	private Long createdId;		
		
    /** created_by */
	private String createdBy;		
		
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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getInterfaceConfig() {
		return interfaceConfig;
	}

	public void setInterfaceConfig(String interfaceConfig) {
		this.interfaceConfig = interfaceConfig;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Long getCreatedId() {
		return createdId;
	}

	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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