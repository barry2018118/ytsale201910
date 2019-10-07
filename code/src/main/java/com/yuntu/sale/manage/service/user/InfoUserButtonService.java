package com.yuntu.sale.manage.service.user;

import java.util.List;

import com.yuntu.sale.base.po.user.InfoUserButton;

public interface InfoUserButtonService {
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @param actionId
	 * @return List<InfoUserButton>
	 */
	List<InfoUserButton> getByUser(long userId, long menuId) ;
	
	/**
	 * 得到用户某功能动作下的按钮
	 * @param userId
	 * @param actionId
	 * @return List<InfoUserButton>
	 */
	List<InfoUserButton> getUserButton(long userId, long actionId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserButton
	 */
	InfoUserButton getById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long add(InfoUserButton entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void edit(InfoUserButton entity) ;
	
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
	 * 检查用户是否具有某Button权限
	 * @param userId
	 * @param buttonId
	 * @return boolean
	 */
	boolean checkUserButton(long userId, long buttonId) ;
}