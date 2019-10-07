package com.yuntu.sale.info.dao;

import java.util.List;

import com.yuntu.sale.info.po.ActionTypePo;

/**
 * @Description 业务动作类型Dao
 * @author snps
 * @date 2018年3月8日 上午9:42:54
 */
public interface ActionTypeDao {

	/**
	 * 得到业务动作类型列表
	 * @return List<ActionTypePo>
	 */
	public List<ActionTypePo> queryList();

	/**
	 * 根据Id获取业务动作类型
	 * @param id
	 * @return ActionTypePo
	 */
	public ActionTypePo queryById(Long id);
	
}