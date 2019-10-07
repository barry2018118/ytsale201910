package com.yuntu.sale.manage.service.function.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.manage.dao.function.FuncButtonDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseButtonService;
import com.yuntu.sale.manage.service.function.FuncButtonService;

@Service
public class FuncButtonServiceImpl implements FuncButtonService {
	@Resource
	private FuncButtonDao dao ;
	
	@Resource
	private InfoEnterpriseButtonService enterpriseButtonService ;
	
	
	public List<FuncButton> getByAction(long actionId) {
		return dao.queryByAction(actionId) ;
	}

	public FuncButton getById(long id) {
		return dao.queryById(id) ;
	}

	public long save(FuncButton entity) {
		return dao.insert(entity) ;
	}

	public void edit(FuncButton entity) {
		dao.update(entity) ;
	}
	
	public int delete(long buttonId) {
		int count = enterpriseButtonService.getCountFromEnterprise(buttonId) ;
		if(count!=0) {
			return count ;
		} else {
			dao.delete(buttonId) ;
			return 0 ;
		}
	}

}