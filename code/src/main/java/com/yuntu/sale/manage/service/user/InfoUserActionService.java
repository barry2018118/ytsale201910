package com.yuntu.sale.manage.service.user;

import java.util.List;

import com.yuntu.sale.base.po.user.InfoUserAction;

public interface InfoUserActionService {
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @param menuId
	 * @return List<InfoUserAction>
	 */
	List<InfoUserAction> getByUser(long userId, long menuId) ;
	
	/**
	 * 得到用户某功能菜单下的动作
	 * @param userId
	 * @param menuId
	 * @return List<InfoUserAction>
	 */
	public List<InfoUserAction> getUserAction(long userId, long menuId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserAction
	 */
	InfoUserAction getById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long add(InfoUserAction entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void edit(InfoUserAction entity) ;
	
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
	 * 检查用户是否具有某Action权限
	 * @param userId
	 * @param actionId
	 * @return boolean
	 */
	boolean checkUserAction(long userId, long actionId) ;
}