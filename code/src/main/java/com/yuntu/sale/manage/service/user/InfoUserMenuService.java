package com.yuntu.sale.manage.service.user;

import java.util.List;

import com.yuntu.sale.base.po.user.InfoUserMenu;

public interface InfoUserMenuService {
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @param moduleId
	 * @return List<InfoUserMenu>
	 */
	List<InfoUserMenu> getByUser(long userId, long moduleId) ;
	
	/**
	 * 得到用户某功能模块下的菜单
	 * @param userId
	 * @param moduleId
	 * @return List<InfoUserMenu>
	 */
	List<InfoUserMenu> getUserMenu(long userId, long moduleId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserMenu
	 */
	InfoUserMenu getById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long add(InfoUserMenu entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void edit(InfoUserMenu entity) ;
	
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
	 * 检查用户是否具有某Menu权限
	 * @param userId
	 * @param menuId
	 * @return boolean
	 */
	boolean checkUserMenu(long userId, long menuId) ;
}