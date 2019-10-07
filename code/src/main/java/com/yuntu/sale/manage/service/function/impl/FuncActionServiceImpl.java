package com.yuntu.sale.manage.service.function.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.manage.dao.function.FuncActionDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseActionService;
import com.yuntu.sale.manage.service.function.FuncActionService;

@Service
public class FuncActionServiceImpl implements FuncActionService {
	@Resource
	private FuncActionDao dao ;
	
	@Resource
	private InfoEnterpriseActionService enterpriseActionService ;
	
	
	public List<FuncAction> getByMenu(long menuId) {
		return dao.queryByMenu(menuId) ;
	}

	public FuncAction getById(long id) {
		return dao.queryById(id) ;
	}

	public long save(FuncAction entity) {
		return dao.insert(entity) ;
	}

	public void edit(FuncAction entity) {
		dao.update(entity) ;
	}
	
	public int delete(long actionId) {
		int count = enterpriseActionService.getCountFromEnterprise(actionId) ;
		if(count!=0) {
			return count ;
		} else {
			dao.delete(actionId) ;
			return 0 ;
		}
	}

}