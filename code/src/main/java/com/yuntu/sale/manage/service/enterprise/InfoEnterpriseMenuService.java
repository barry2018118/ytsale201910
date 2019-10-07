package com.yuntu.sale.manage.service.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseMenu;

public interface InfoEnterpriseMenuService {
	
	/**
	 *  得到给企业分配的功能菜单
	 * @param enterpriseId
	 * @return List<InfoEnterpriseMenu>
	 */
	List<InfoEnterpriseMenu> getAll(long enterpriseId) ;
	
	/**
	 * 得到给企业分配的功能菜单
	 * @param mapCondition (enterpriseId + moduleId)
	 * @return List<InfoEnterpriseMenu>
	 */
	List<InfoEnterpriseMenu> getByModule(Map<String, Object> mapCondition) ;
	
	/**
	 * 得到给企业分配的某功能菜单
	 * @param enterpriseId
	 * @param menuId
	 * @return InfoEnterpriseMenu
	 */
	InfoEnterpriseMenu getEnterpriseMenu(long enterpriseId, long menuId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseMenu
	 */
	InfoEnterpriseMenu getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long add(InfoEnterpriseMenu entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void remove(long id) ;
	
	/**
	 * 查询此功能菜单是否与分配给企业
	 * @param menuId
	 * @return int
	 */
	int getCountFromEnterprise(long menuId) ;
	
}