package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 分销组分销商Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroupEnterprise extends BasePo implements Serializable {

	private static final long serialVersionUID = -1832301030288059408L;
	private Long id;            //id
	private Long groupId;		// 分销组Id
	private Long createEnterpriseId;		// 分销组创建企业Id
	private Long childEnterpriseId;		// 分销组分销商企业Id
	private Integer isDelete;		// 是否删除（1：是，0：否）
	private Long createId;		// 创建人Id
	private Date createTime;		// 创建时间
	private Long updateId;		// 修改人Id
	private Date updateTime;		// 修改时间
	
	public SaleGroupEnterprise() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Long getCreateEnterpriseId() {
		return createEnterpriseId;
	}

	public void setCreateEnterpriseId(Long createEnterpriseId) {
		this.createEnterpriseId = createEnterpriseId;
	}
	
	public Long getChildEnterpriseId() {
		return childEnterpriseId;
	}

	public void setChildEnterpriseId(Long childEnterpriseId) {
		this.childEnterpriseId = childEnterpriseId;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
	
}