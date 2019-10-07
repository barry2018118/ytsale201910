package com.yuntu.sale.manage.service.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncModule;

/**
 * 功能Service
 * @Description 
 * @author snps
 * @date 下午8:39:35
 */
public interface FunctionService {

	/**
	 * 得到分配的功能（模块-菜单-功能-按钮<子功能>）
	 * @param id (NULL, enterpriseId, employeeId, roleId)
	 * @return List<FuncModule>
	 */
	List<FuncModule> getFunction(Long id) ;
	
	/**
	 * 设置功能
	 * @param entityId (NULL, enterpriseId, employeeId, roleId)
	 * @param lstModuleId
	 * @param lstMenuId
	 * @param lstActionid
	 * @param lstButtonId
	 * @param currentUserId 当前登录用户Id
	 */
	void setFunction(Long entityId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) ;
	
	/**
	 * 是否有此模块
	 * @param entityId
	 * @param moduleId
	 * @return boolean
	 */
	boolean haveModule(Long entityId, long moduleId) ;
	
	/**
	 * 是否有此菜单
	 * @param entityId
	 * @param menuId
	 * @return boolean
	 */
	boolean haveMenu(Long entityId, long menuId) ;
	
	/**
	 * 是否有此功能
	 * @param entityId
	 * @param actionId
	 * @return boolean
	 */
	boolean haveAction(Long entityId, long actionId) ;
	
	/**
	 * 是否有此按钮<子功能>
	 * @param entityId
	 * @param buttonId
	 * @return boolean
	 */
	boolean haveButton(Long entityId, long buttonId) ;
}