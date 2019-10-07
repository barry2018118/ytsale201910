package com.yuntu.sale.info.dao;

import java.util.List;

import com.yuntu.sale.info.po.CapitalChannelPo;

/**
 * @Description 资金渠道Dao接口
 * @author snps
 * @date 2018年3月8日 上午9:16:49
 */
public interface CapitalChannelDao {

	/**
	 * 得到资金渠道列表
	 * @return List<CapitalChannelPo>
	 */
	public List<CapitalChannelPo> queryList();

	/**
	 * 根据Id获取资金渠道
	 * @param id
	 * @return CapitalChannelPo
	 */
	public CapitalChannelPo queryById(Long id);
	
}