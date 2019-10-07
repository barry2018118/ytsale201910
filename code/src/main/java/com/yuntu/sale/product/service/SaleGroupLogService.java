package com.yuntu.sale.product.service;

import com.yuntu.sale.product.po.SaleGroupLog;
import org.apache.ibatis.annotations.Param;

/**
 * 分销组操作日志Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupLogService {

	/**
	 * 通过Id查询
	 *
	 * @return SaleGroupLog
	 */
	SaleGroupLog getById(@Param("id") Long id);

	/**
	 * 保存
	 *
	 * @param entity
	 */
	void save(SaleGroupLog entity);

	/**
	 * 修改
	 *
	 * @param entity
	 */
	void update(SaleGroupLog entity);

}