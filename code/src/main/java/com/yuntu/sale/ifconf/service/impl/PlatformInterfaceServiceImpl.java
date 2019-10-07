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
import com.yuntu.sale.ifconf.dao.PlatformInterfaceDao;
import com.yuntu.sale.ifconf.po.PlatformInterfacePo;
import com.yuntu.sale.ifconf.service.PlatformInterfaceService;

/**
 * @Description 接口基础配置Service实现类 
 * @author Jack.jiang
 * @date 2018年3月23日
 */
@Service
public class PlatformInterfaceServiceImpl implements PlatformInterfaceService{

	@Autowired
	private PlatformInterfaceDao dao;

	@Override
	public Page<PlatformInterfacePo> listPage(Page<PlatformInterfacePo> pager, Map<String, Object> param) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<PlatformInterfacePo> lstData = dao.listBy(param);
		// 获取分页后所需信息
		PageInfo<PlatformInterfacePo> pageInfo = new PageInfo<PlatformInterfacePo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	@Override
	public List<PlatformInterfacePo> findByType(Integer typeId) {
		Map<String,Object> mapParam=Maps.newHashMap();
		mapParam.put("typeId", typeId);
		mapParam.put("status", 5);//正常状态
		return dao.listBy(mapParam);
	}

	@Override
	public PlatformInterfacePo getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void save(PlatformInterfacePo entity) {
		Date curdate=new Date();
		entity.setCreatedAt(curdate);
		entity.setUpdatedAt(curdate);
		entity.setStatus(5);
		entity.setSort(0);
		dao.insertDynamic(entity);
	}

	@Override
	public void update(PlatformInterfacePo entity) {
		Date curdate=new Date();
		entity.setUpdatedAt(curdate);
		dao.updateDynamic(entity);
	}

}
