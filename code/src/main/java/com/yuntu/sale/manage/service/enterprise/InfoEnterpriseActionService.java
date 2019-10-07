package com.yuntu.sale.manage.service.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseAction;

public interface InfoEnterpriseActionService {
	
	/**
	 *  得到给企业分配的功能动作
	 * @param enterpriseId
	 * @return List<InfoEnterpriseAction>
	 */
	List<InfoEnterpriseAction> getAll(long enterpriseId) ;

	/**
	 * 得到给企业分配的功能动作
	 * @param mapCondition (enterpriseId + menuId)
	 * @return List<InfoEnterpriseAction>
	 */
	List<InfoEnterpriseAction> getByMenu(Map<String, Object> mapCondition) ;
	
	/**
	 * 得到给企业分配的某功能动作
	 * @param enterpriseId
	 * @param actionId
	 * @return InfoEnterpriseAction
	 */
	InfoEnterpriseAction getEnterpriseAction(long enterpriseId, long actionId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseAction
	 */
	InfoEnterpriseAction getyById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long add(InfoEnterpriseAction entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void remove(long id) ;
	
	/**
	 * 查询此功能是否与分配给企业
	 * @param actionId
	 * @return int
	 */
	int getCountFromEnterprise(long actionId) ;
	
}