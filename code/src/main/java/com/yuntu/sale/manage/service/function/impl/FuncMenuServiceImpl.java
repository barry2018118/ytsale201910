package com.yuntu.sale.manage.service.function.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.manage.dao.function.FuncMenuDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseMenuService;
import com.yuntu.sale.manage.service.function.FuncMenuService;

@Service
public class FuncMenuServiceImpl implements FuncMenuService {
	@Resource
	private FuncMenuDao dao ;
	
	@Resource
	private InfoEnterpriseMenuService enterpriseMenuService ;
	
	
	public List<FuncMenu> getByModule(long moduleId) {
		return dao.queryByModule(moduleId) ;
	}

	public FuncMenu getById(long id) {
		return dao.queryById(id) ;
	}

	public long save(FuncMenu entity) {
		return dao.insert(entity) ;
	}

	public void edit(FuncMenu entity) {
		dao.update(entity) ;
	}
	
	public int delete(long menuId) {
		int count = enterpriseMenuService.getCountFromEnterprise(menuId) ;
		if(count!=0) {
			return count ;
		} else {
			dao.delete(menuId) ;
			return 0 ;
		}
	}

}