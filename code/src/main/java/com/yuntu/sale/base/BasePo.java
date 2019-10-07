package com.yuntu.sale.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description BasePo
 * @author snps
 * @date 2018年2月13日 上午8:39:30
 */
public class BasePo implements Serializable {

	private static final long serialVersionUID = -7347380313284059330L;

	/**
	 * 创建人Id
	 */
	private Long createId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人Id
	 */
	private Long updateId;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;

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

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/********************************************
	 * Constructor
	 */
	public BasePo() {
		this.createTime = new Date();
		this.updateTime = new Date();
	}

}