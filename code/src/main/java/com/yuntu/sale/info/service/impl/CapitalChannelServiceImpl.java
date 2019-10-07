package com.yuntu.sale.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.info.dao.CapitalChannelDao;
import com.yuntu.sale.info.po.CapitalChannelPo;
import com.yuntu.sale.info.service.CapitalChannelService;

/**
 * @Description 资金渠道Service接口
 * @author snps
 * @date 2018年3月8日 上午9:14:30
 */
@Service("capitalChannelService")
public class CapitalChannelServiceImpl implements CapitalChannelService {
	
	@Resource
	public CapitalChannelDao dao;

	@Override
	public List<CapitalChannelPo> getList() {
		return dao.queryList();
	}

	@Override
	public CapitalChannelPo getById(Long id) {
		return dao.queryById(id);
	}

}