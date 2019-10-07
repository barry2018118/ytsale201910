package com.yuntu.sale.manage.service.enterprise.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseButton;
import com.yuntu.sale.manage.dao.enterprise.InfoEnterpriseButtonDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseButtonService;

@Service
public class InfoEnterpriseButtonServiceImpl implements InfoEnterpriseButtonService {
	@Resource
	private InfoEnterpriseButtonDao dao ;
	
	@Override
	public List<InfoEnterpriseButton> getAll(long enterpriseId) {
		return dao.queryAll(enterpriseId) ;
	}
	
	@Override
	public List<InfoEnterpriseButton> getByAction(Map<String, Object> mapCondition) {
		return dao.queryByAction(mapCondition) ;
	}

	public InfoEnterpriseButton getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoEnterpriseButton entity) {
		return dao.insert(entity) ;
	}

	public void remove(long id) {
		dao.delete(id) ;
	}

	public InfoEnterpriseButton getEnterpriseButton(long enterpriseId, long buttonId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("enterpriseId", enterpriseId) ;
		mapCondition.put("buttonId", buttonId) ;
		return dao.queryButton(mapCondition) ;
	}

	public int getCountFromEnterprise(long buttonId) {
		return dao.queryCountFromEnterprise(buttonId) ;
	}
	
}