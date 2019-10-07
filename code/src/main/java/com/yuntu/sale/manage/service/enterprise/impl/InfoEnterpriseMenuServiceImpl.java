package com.yuntu.sale.manage.service.enterprise.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseMenu;
import com.yuntu.sale.manage.dao.enterprise.InfoEnterpriseMenuDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseMenuService;

@Service
public class InfoEnterpriseMenuServiceImpl implements InfoEnterpriseMenuService {
	@Resource
	private InfoEnterpriseMenuDao dao ;
	
	public List<InfoEnterpriseMenu> getAll(long enterpriseId) {
		return dao.queryAll(enterpriseId) ;
	}
	
	public List<InfoEnterpriseMenu> getByModule(Map<String, Object> mapCondition) {
		return dao.queryByModule(mapCondition) ;
	}

	public InfoEnterpriseMenu getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoEnterpriseMenu entity) {
		return dao.insert(entity) ;
	}

	public void remove(long id) {
		dao.delete(id) ;
	}

	public InfoEnterpriseMenu getEnterpriseMenu(long enterpriseId, long menuId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("enterpriseId", enterpriseId) ;
		mapCondition.put("menuId", menuId) ;
		return dao.queryMenu(mapCondition) ;
	}

	public int getCountFromEnterprise(long menuId) {
		return dao.queryCountFromEnterprise(menuId) ;
	}
	
}