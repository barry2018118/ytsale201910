package com.yuntu.sale.manage.dao.enterprise;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseButton;

/**
 * 企业_功能按钮Dao
 * @Description 
 * @author snps
 * @date 下午6:55:30
 */
public interface InfoEnterpriseButtonDao {
	
	/**
	 * 得到给企业分配的功能按钮
	 * @param enterpriseId
	 * @return List<InfoEnterpriseButton>
	 */
	List<InfoEnterpriseButton> queryAll(long enterpriseId) ;

	/**
	 * 得到给企业分配的功能按钮
	 * @param mapCondition (enterpriseId + actionId)
	 * @return List<InfoEnterpriseButton>
	 */
	List<InfoEnterpriseButton> queryByAction(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过企业Id+按钮Id查询
	 * @param mapCondition(enterpriseId, buttonId)
	 * @return InfoEnterpriseButton
	 */
	InfoEnterpriseButton queryButton(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoEnterpriseButton
	 */
	InfoEnterpriseButton queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoEnterpriseButton entity) ;
	
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