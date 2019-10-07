package com.yuntu.sale.manage.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.user.InfoUserMenu;

/**
 * 用户_功能菜单_Dao
 * @Description 
 * @author snps
 * @date 下午2:26:49
 */
public interface InfoUserMenuDao {
	
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @return List<InfoUserMenu>
	 */
	List<InfoUserMenu> queryByUserId(long userId) ;

	/**
	 * 得到用户的功能菜单
	 * @param mapCondition (userId + moduleId)
	 * @return
	 */
	List<InfoUserMenu> queryByUser(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserMenu
	 */
	InfoUserMenu queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(InfoUserMenu entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUserMenu entity) ;
	
	/**
	 * 删除用户下所有菜单
	 * @param entity
	 */
	void deleteByUser(Long userId);
	
	/**
	 * 删除用户下指定菜单
	 * @param map
	 */
	void delete(HashMap<String, Long> map);
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteById(long id) ;

	/**
	 * 通过用户Id + menuId查询
	 * @param mapCondition
	 * @return InfoUserMenu
	 */
	InfoUserMenu queryByUserIdAndMenuId(Map<String, Long> mapCondition) ;
}