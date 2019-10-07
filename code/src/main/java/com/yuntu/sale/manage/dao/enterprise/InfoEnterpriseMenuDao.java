package com.yuntu.sale.manage.dao.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseMenu;

/**
 * 企业_功能菜单Dao
 * @Description 
 * @author snps
 * @date 下午6:44:41
 */
public interface InfoEnterpriseMenuDao {
	
	/**
	 * 得到给企业分配的功能菜单
	 * @param enterpriseId
	 * @return List<InfoEnterpriseMenu>
	 */
	List<InfoEnterpriseMenu> queryAll(long enterpriseId) ;

	/**
	 * 得到给企业分配的功能菜单
	 * @param mapCondition (enterpriseId + moduleId)
	 * @return List<InfoEnterpriseMenu>
	 */
	List<InfoEnterpriseMenu> queryByModule(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过企业Id+菜单Id查询
	 * @param mapCondition(enterpriseId, menuId)
	 * @return InfoEnterpriseMenu
	 */
	InfoEnterpriseMenu queryMenu(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseMenu
	 */
	InfoEnterpriseMenu queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoEnterpriseMenu entity) ;
	
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