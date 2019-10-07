package com.yuntu.sale.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.info.dao.AreaDao;
import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.service.AreaService;

/**
 * @Description 区域（省、市）信息Service实现类
 * @author snps
 * @date 2018年2月27日 上午9:39:21
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Resource
	public AreaDao dao;

	@Override
	public List<AreaPo> getProvince() {
		return dao.queryProvince();
	}

	@Override
	public List<AreaPo> getCityByProvinceId(Long id) {
		return dao.queryCityByProvinceId(id);
	}

	@Override
	public AreaPo getById(Long id) {
		return dao.queryById(id);
	}
	
}