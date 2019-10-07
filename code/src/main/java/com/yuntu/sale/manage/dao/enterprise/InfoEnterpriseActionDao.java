package com.yuntu.sale.manage.dao.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseAction;

/**
 * 企业_功能动作Dao
 * @Description 
 * @author snps
 * @date 下午6:50:46
 */
public interface InfoEnterpriseActionDao {
	
	/**
	 * 得到给企业分配的功能动作
	 * @param enterpriseId
	 * @return List<InfoEnterpriseAction>
	 */
	List<InfoEnterpriseAction> queryAll(long enterpriseId) ;

	/**
	 * 得到给企业分配的功能动作
	 * @param mapCondition (enterpriseId + menuId)
	 * @return List<InfoEnterpriseAction>
	 */
	List<InfoEnterpriseAction> queryByMenu(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过企业Id+动作Id查询
	 * @param mapCondition(enterpriseId, actionId)
	 * @return InfoEnterpriseAction
	 */
	InfoEnterpriseAction queryAction(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseAction
	 */
	InfoEnterpriseAction queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoEnterpriseAction entity) ;
	
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