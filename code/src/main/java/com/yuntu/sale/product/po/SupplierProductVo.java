package com.yuntu.sale.product.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yuntu.sale.base.BasePo;

/**
 * 供应商商品-商品信息关系Entity
 * @author zy
 * @version 2018-04-02
 */
public class SupplierProductVo extends BasePo implements Serializable {

	private static final long serialVersionUID = -2513473681725287658L;

	private Long id;            //关系id
	private Integer isSupplier;		// 是否商品供应商（1：是，0：否）
	private Long productId;    //产品id
	private String no;		// 产品编号
	private Long enterpriseId;	//供应商企业id
	private String name;		// 商品名称
	private BigDecimal marketPrice;		// 市场价
	private Long categoryId;		// 商品类别
	private Integer status;		// 商品状态（1：启用，0：停用）
	private Integer thirdPlatformId;//第三方平台id
	private String enterpriseName; //创建企业名称
	private String starttime; //有效期
	private String endtime; //
	private String stronum; // 库存

	/**
	 * 组中是否已存在商品
	 */
	private String isExist;

	public SupplierProductVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(Integer isSupplier) {
		this.isSupplier = isSupplier;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getThirdPlatformId() {
		return thirdPlatformId;
	}

	public void setThirdPlatformId(Integer thirdPlatformId) {
		this.thirdPlatformId = thirdPlatformId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStronum() {
		return stronum;
	}

	public void setStronum(String stronum) {
		this.stronum = stronum;
	}

}