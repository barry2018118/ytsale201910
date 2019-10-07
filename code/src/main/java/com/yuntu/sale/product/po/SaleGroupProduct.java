package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 分销组商品Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroupProduct extends BasePo implements Serializable {

	private static final long serialVersionUID = -1071256903174673087L;
	private Long id;            //id
	private Long groupId;		// 分销组Id
	private String groupTracks;		// 组分销轨迹
	private Long createEnterpriseId;		// 分销组创建企业Id
	private Long productId;		// 商品Id
	private Integer isDelete;		// 是否删除（1：是，0：否）
	private Long createId;		// 创建人Id
	private Date createTime;		// 创建时间
	private Long updateId;		// 修改人Id
	private Date updateTime;		// 修改时间
	
	/**
	 * 商品分销状态（0：未分销，1：已分销）	at 2018-05-30 by snps
	 */
	private Integer isDistribution = 0;
	
	public SaleGroupProduct() {
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
	
	public String getGroupTracks() {
		return groupTracks;
	}

	public void setGroupTracks(String groupTracks) {
		this.groupTracks = groupTracks;
	}
	
	public Long getCreateEnterpriseId() {
		return createEnterpriseId;
	}

	public void setCreateEnterpriseId(Long createEnterpriseId) {
		this.createEnterpriseId = createEnterpriseId;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}

}