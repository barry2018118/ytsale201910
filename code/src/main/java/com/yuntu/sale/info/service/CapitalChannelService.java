package com.yuntu.sale.info.service;

import java.util.List;

import com.yuntu.sale.info.po.CapitalChannelPo;

/**
 * @Description 资金渠道Service接口
 * @author snps
 * @date 2018年3月8日 上午9:12:16
 */
public interface CapitalChannelService {

	/**
	 * 得到资金渠道列表
	 * @return List<CapitalChannelPo>
	 */
	public List<CapitalChannelPo> getList();

	/**
	 * 根据Id获取资金渠道
	 * @param id
	 * @return CapitalChannelPo
	 */
	public CapitalChannelPo getById(Long id);
	
}