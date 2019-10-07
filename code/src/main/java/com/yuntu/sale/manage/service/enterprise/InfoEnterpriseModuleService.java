package com.yuntu.sale.manage.service.enterprise;

import java.util.List;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseModule;

public interface InfoEnterpriseModuleService {

	/**
	 *  得到给企业分配的功能模块
	 * @param enterpriseId
	 * @return List<InfoEnterpriseModule>
	 */
	List<InfoEnterpriseModule> getAll(long enterpriseId) ;
	
	/**
	 * 得到给企业分配的某功能模块
	 * @param enterpriseId
	 * @param moduleId
	 * @return InfoEnterpriseModule
	 */
	InfoEnterpriseModule getEnterpriseModule(long enterpriseId, long moduleId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseModule
	 */
	InfoEnterpriseModule getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long add(InfoEnterpriseModule entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void remove(long id) ;
	
	/**
	 * 查询此功能模块是否与分配给企业
	 * @param moduleId
	 * @return int
	 */
	int getCountFromEnterprise(long moduleId) ;
	
}