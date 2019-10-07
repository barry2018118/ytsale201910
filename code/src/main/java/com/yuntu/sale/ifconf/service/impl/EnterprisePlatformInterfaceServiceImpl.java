package com.yuntu.sale.ifconf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yuntu.sale.ifconf.dao.EnterprisePlatformInterfaceDao;
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;

/**
 * @Description 接口基础配置Service实现类 
 * @author Jack.jiang
 * @date 2018年3月23日
 */
@Service("enterprisePlatformInterfaceService")
public class EnterprisePlatformInterfaceServiceImpl implements EnterprisePlatformInterfaceService{

	@Autowired
	private EnterprisePlatformInterfaceDao dao;

	@Override
	public Page<EnterprisePlatformInterfacePo> listPage(Page<EnterprisePlatformInterfacePo> pager, Map<String, Object> param) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterprisePlatformInterfacePo> lstData = dao.listBy(param);
		// 获取分页后所需信息
		PageInfo<EnterprisePlatformInterfacePo> pageInfo = new PageInfo<EnterprisePlatformInterfacePo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public EnterprisePlatformInterfacePo getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void save(EnterprisePlatformInterfacePo entity) {
		Date curdate=new Date();
		entity.setCreatedAt(curdate);
		entity.setUpdatedAt(curdate);
		entity.setStatus(5);
		entity.setSort(0);
		dao.insertDynamic(entity);
	}

	@Override
	public void update(EnterprisePlatformInterfacePo entity) {
		Date curdate=new Date();
		entity.setUpdatedAt(curdate);
		dao.updateDynamic(entity);
	}

	@Override
	public List<EnterprisePlatformInterfacePo> listOfAvailableGys(Long enterpriseId) {
		Map<String,Object> mapParam=Maps.newHashMap();
		mapParam.put("enterpriseId", enterpriseId);
		mapParam.put("typeId", 2);
		mapParam.put("status", 5);
		return dao.listBy(mapParam);
	}

}
