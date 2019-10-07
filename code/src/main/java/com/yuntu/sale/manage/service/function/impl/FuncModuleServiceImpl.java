package com.yuntu.sale.manage.service.function.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.dao.function.FuncModuleDao;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseModuleService;
import com.yuntu.sale.manage.service.function.FuncModuleService;

@Service
public class FuncModuleServiceImpl implements FuncModuleService {
	@Resource
	private FuncModuleDao dao;
	
	@Resource
	private InfoEnterpriseModuleService enterpriseModuleService ;
	

	/**
	 * 得到全部功能模块（调用场景：系统管理员使用）
	 * @Description 返回所有信息，不区别是否删除
	 * @return List<FuncModule>
	 */
	public List<FuncModule> getAll() {
		return dao.queryAll();
	}

	/**
	 * 获得某分类下的功能模块（预留扩展，暂未使用）
	 * @Description 返回类别下所有信息，不区别是否删除
	 * @param categoryId 所属类别Id
	 * @return List<FuncModule>
	 */
	public List<FuncModule> getByCategory(int categoryId) {
		return dao.queryByCategory(categoryId);
	}

	/**
	 * 通过id获取
	 * @param id
	 * @return FuncModule
	 */
	public FuncModule getById(long id) {
		return dao.queryById(id);
	}

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	public long save(FuncModule entity) {
		return dao.insert(entity) ;
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void edit(FuncModule entity) {
		dao.update(entity) ;
	}
	
	public int delete(long moduleId) {
		int count = enterpriseModuleService.getCountFromEnterprise(moduleId) ;
		if(count!=0) {
			return count ;
		} else {
			dao.delete(moduleId) ;
			return 0 ;
		}
	}
	
}