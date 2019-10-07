package com.yuntu.sale.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 分页对象Vo
 * @author snps
 * @date 2018年2月22日 上午9:54:22
 */
public class PageJsonVo implements Serializable {

	private static final long serialVersionUID = -7801831046303364217L;

	private long total;
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}