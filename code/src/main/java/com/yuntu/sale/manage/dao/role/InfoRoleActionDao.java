package com.yuntu.sale.manage.dao.role;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.role.InfoRoleAction;

/**
 * 类描述： 权限组_功能动作Dao
 * @author wj_tang
 * 创建时间：2017-1-11 下午1:45:29
 */
public interface InfoRoleActionDao {
	
	/**
	 * 得到给权限组分配的功能动作
	 * @param roleId
	 * @return List<InfoRoleAction>
	 */
	List<InfoRoleAction> queryAll(long roleId) ;

	/**
	 * 得到给权限组分配的功能动作
	 * @param mapCondition (roleId + menuId)
	 * @return List<InfoRoleAction>
	 */
	List<InfoRoleAction> queryByMenu(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过权限组Id+动作Id查询
	 * @param mapCondition(roleId, actionId)
	 * @return InfoRoleAction
	 */
	InfoRoleAction queryAction(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoRoleAction
	 */
	InfoRoleAction queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoRoleAction entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id) ;
	
	/**
	 * 通过roleId查询其下所有action
	 * @param RoleId
	 * @return
	 */
	List<FuncAction> queryAllByRoleId(Long RoleId);
	
	/**
	 * 
	 * @param mapCondition
	 * @return
	 */
	List<InfoRoleAction> queryByModuleAndMenu(Map<String, Object> mapCondition) ;
}