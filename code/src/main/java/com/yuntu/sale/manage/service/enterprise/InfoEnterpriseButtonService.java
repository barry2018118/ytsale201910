package com.yuntu.sale.manage.service.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseButton;

public interface InfoEnterpriseButtonService {
	
	/**
	 *  得到给企业分配的功能按钮
	 * @param enterpriseId
	 * @return List<InfoEnterpriseButton>
	 */
	List<InfoEnterpriseButton> getAll(long enterpriseId) ;

	/**
	 * 得到给企业分配的功能按钮
	 * @param mapCondition (enterpriseId + actionId)
	 * @return List<InfoEnterpriseButton>
	 */
	List<InfoEnterpriseButton> getByAction(Map<String, Object> mapCondition) ;
	
	/**
	 * 得到给企业分配的某功能按钮
	 * @param enterpriseId
	 * @param buttonId
	 * @return InfoEnterpriseButton
	 */
	InfoEnterpriseButton getEnterpriseButton(long enterpriseId, long buttonId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseButton
	 */
	InfoEnterpriseButton getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long add(InfoEnterpriseButton entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void remove(long id) ;
	
	/**
	 * 查询此子功能模块是否与分配给企业
	 * @param buttonId
	 * @return
	 */
	int getCountFromEnterprise(long buttonId) ;
	
}