package com.yuntu.sale.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 商品日历价格Vo 
 * @author snps
 * @date 2018年5月10日 下午5:59:21
 */
public class ProductPriceVo implements Serializable {

	private static final long serialVersionUID = -7818758537667922657L;
	/**
	 * 操作成功标识
	 */
	private Boolean type;
	
	/**
	 * 价格
	 */
	private BigDecimal data;

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}
}