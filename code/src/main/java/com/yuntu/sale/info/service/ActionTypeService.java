package com.yuntu.sale.info.service;

import java.util.List;

import com.yuntu.sale.info.po.ActionTypePo;

/**
 * @Description 业务动作类型Service接口 
 * @author snps
 * @date 2018年3月8日 上午9:45:47
 */
public interface ActionTypeService {

	/**
	 * 得到业务动作类型列表
	 * @return List<ActionTypePo>
	 */
	public List<ActionTypePo> getList();

	/**
	 * 根据Id获取业务动作类型
	 * @param id
	 * @return CapitalChannelPo
	 */
	public ActionTypePo getById(Long id);
	
}