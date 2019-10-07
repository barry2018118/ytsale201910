package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 分销组Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroup extends BasePo implements Serializable {

	private static final long serialVersionUID = -3925739121181025526L;
	private Long id;            //id
	private Long enterpriseId;            //创建企业id
	private String name;		// 分销组名称
	private Integer userNumber;		// 分销商数
	private Integer productNumber;		// 商品数
	private Integer isDelete;		// 是否删除（1：是，0：否）
	private Long createId;		// 创建人Id
	private Date createTime;		// 创建时间
	private Long updateId;		// 修改人Id
	private Date updateTime;		// 修改时间

	private String enterpriseName;
	
	public SaleGroup() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}

	public Long getId() {
		return id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	
	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}
	
	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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