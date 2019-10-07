package com.yuntu.sale.manage.service.user;

import java.util.List;

import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.base.po.user.InfoUserModule;

public interface InfoUserModuleService {
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @return List<InfoUserModule>
	 */
	List<InfoUserModule> getByUser(long userId) ;
	
	/**
	 * 得到用户的功能权限
	 * @param userId
	 * @return List<InfoUserModule>
	 */
	List<InfoUserModule> getUserFunction(long userId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserModule
	 */
	InfoUserModule getById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long add(InfoUserModule entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void edit(InfoUserModule entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void remove(long id) ;
	
	/**
	 * 删除用户的模块
	 * @param id
	 */
	void removeByUser(long userId) ;

	/**
	 * 检查用户是否具有某Module权限
	 * @param userId
	 * @param moduleId
	 * @return boolean
	 */
	boolean checkUserModule(long userId, long moduleId) ;
	
	/**
	 * 获得权限相关的模块
	 * @param roleId 权限Id
	 * @return List<InfoRoleModule>
	 */
	List<InfoRoleModule> getByRole(long roleId) ;
	
}