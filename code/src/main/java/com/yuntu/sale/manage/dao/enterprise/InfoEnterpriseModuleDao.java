package com.yuntu.sale.manage.dao.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseModule;

/**
 * 企业_功能模块Dao
 * @Description 
 * @author snps
 * @date 下午6:23:57
 */
public interface InfoEnterpriseModuleDao {

	/**
	 * 得到给企业分配的功能模块
	 * @param enterpriseId
	 * @return List<InfoEnterpriseModule>
	 */
	List<InfoEnterpriseModule> queryAll(long enterpriseId) ;
	
	/**
	 * 通过企业Id+模块Id查询
	 * @param mapCondition(enterpriseId, moduleId)
	 * @return InfoEnterpriseModule
	 */
	InfoEnterpriseModule queryModule(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseModule
	 */
	InfoEnterpriseModule queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoEnterpriseModule entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id) ;
	
	/**
	 * 查询此功能模块是否与分配给企业（select count(id)）
	 * @param id
	 */
	int queryCountFromEnterprise(long id) ;
	
}