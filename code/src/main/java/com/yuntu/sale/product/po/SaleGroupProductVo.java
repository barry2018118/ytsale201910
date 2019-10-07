package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分销组商品关系Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroupProductVo extends BasePo implements Serializable {

	private static final long serialVersionUID = 6748066125320780942L;
	private Long id;            //id
	private Long productId;      //商品id
	private String no;		// 产品编号
	private String name;		// 商品名称
	private BigDecimal marketPrice;		// 市场价
	
	/**
	 * 商品分销状态（0：未分销，1：已分销）	at 2018-05-30 by snps
	 */
	private Integer isDistribution;

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}
	
}