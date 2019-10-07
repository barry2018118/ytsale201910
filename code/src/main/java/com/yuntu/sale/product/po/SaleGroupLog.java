package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import java.io.Serializable;
import java.util.Date;

/**
 * 分销组操作日志Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroupLog extends BasePo implements Serializable {

	private static final long serialVersionUID = -8220422799917343021L;
	private Long id;            //id
	private Integer operateFlag;		// 操作标识
	private String userIds;		// 操作影响的用户Id
	private String productIds;		// 操作影响的商品Id
	private Long createId;		// 创建人Id
	private Date createTime;		// 创建时间
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(Integer operateFlag) {
		this.operateFlag = operateFlag;
	}
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
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
	
}