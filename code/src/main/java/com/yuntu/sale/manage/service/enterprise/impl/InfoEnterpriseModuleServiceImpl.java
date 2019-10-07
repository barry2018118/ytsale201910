package com.yuntu.sale.manage.service.enterprise.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseModule;
import com.yuntu.sale.manage.dao.enterprise.InfoEnterpriseModuleDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseModuleService;

@Service
public class InfoEnterpriseModuleServiceImpl implements InfoEnterpriseModuleService {
	@Resource
	private InfoEnterpriseModuleDao dao;

	public List<InfoEnterpriseModule> getAll(long enterpriseId) {
		return dao.queryAll(enterpriseId);
	}

	public InfoEnterpriseModule getById(long id) {
		return dao.queryById(id);
	}

	public long add(InfoEnterpriseModule entity) {
		return dao.insert(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}

	public InfoEnterpriseModule getEnterpriseModule(long enterpriseId, long moduleId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>();
		mapCondition.put("enterpriseId", enterpriseId);
		mapCondition.put("moduleId", moduleId);
		return dao.queryModule(mapCondition);
	}

	public int getCountFromEnterprise(long moduleId) {
		return dao.queryCountFromEnterprise(moduleId);
	}

}