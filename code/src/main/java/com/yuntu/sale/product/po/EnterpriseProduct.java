package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品管理Entity
 * @author zy
 * @version 2018-04-02
 */
public class EnterpriseProduct extends BasePo implements Serializable {

	private static final long serialVersionUID = 6765986414123598284L;
	private Long id;            //id
	private Long enterpriseId;    //商品供应商企业Id
	private Long productId;		// 商品Id
	private Long parentId;		// 上级企业Id
	private Long childId;		// 下级企业Id
	private Integer isSupplier;		// 是否商品供应商（1：是，0：否）
	private Long groupId;		// 获取商品的分销组Id
	private String groupTracks;		// 组分销轨迹
	private String userTracks;		// 用户分销轨迹
	private Integer isDelete;		// 是否删除（1：是，0：否）
	private Long createId;		// 创建人Id
	private Date createTime;		// 创建时间
	private Long updateId;		// 修改人Id
	private Date updateTime;		// 修改时间
	
	/**
	 * 商品分销状态（0：未分销，1：已分销）	at 2018-05-30 by snps
	 */
	private Integer isDistribution = 0;
	
	public EnterpriseProduct(){
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}
	
	public Integer getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(Integer isSupplier) {
		this.isSupplier = isSupplier;
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
	
	public String getUserTracks() {
		return userTracks;
	}

	public void setUserTracks(String userTracks) {
		this.userTracks = userTracks;
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