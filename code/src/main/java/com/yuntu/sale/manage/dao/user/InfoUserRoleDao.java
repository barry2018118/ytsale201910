package com.yuntu.sale.manage.dao.user;

import java.util.HashMap;

public interface InfoUserRoleDao {

	//int insert(InfoUserRole userRole);
	
	/**
	 * 通过用户id和权限id删除
	 * @param param
	 * @return
	 */
	int delete(HashMap<String, Long> param);
	
	/**
	 * 通过用户id查询其下所有的权限组
	 * @param userId
	 * @return
	 */
	//List<InfoRole> query(Long userId);
	
	/**
	 * 通过主键删除
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	
	/**
	 * 通过用户id删除
	 * @param id
	 * @return
	 */
	int deleteByUserId(Long id);
	
	/**
	 * 检查权限组是否与用户有关联 
	 * @param roleId
	 * @return
	 */
	int queryCount(Long roleId);
	
}
