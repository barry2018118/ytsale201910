package com.yuntu.sale.manage.service.enterprise.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseAction;
import com.yuntu.sale.manage.dao.enterprise.InfoEnterpriseActionDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseActionService;

@Service
public class InfoEnterpriseActionServiceImpl implements InfoEnterpriseActionService {
	@Resource
	private InfoEnterpriseActionDao dao ;
	
	public List<InfoEnterpriseAction> getAll(long enterpriseId) {
		return dao.queryAll(enterpriseId) ;
	}
	
	public List<InfoEnterpriseAction> getByMenu(Map<String, Object> mapCondition) {
		return dao.queryByMenu(mapCondition) ;
	}

	public InfoEnterpriseAction getyById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoEnterpriseAction entity) {
		return dao.insert(entity) ;
	}

	public void remove(long id) {
		dao.delete(id) ;
	}

	public InfoEnterpriseAction getEnterpriseAction(long enterpriseId, long actionId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("enterpriseId", enterpriseId) ;
		mapCondition.put("actionId", actionId) ;
		return dao.queryAction(mapCondition) ;
	}

	public int getCountFromEnterprise(long actionId) {
		return dao.queryCountFromEnterprise(actionId) ;
	}
	
}