package com.yuntu.sale.manage.dao.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncMenu;

/**
 * @Description 基础功能_菜单Dao
 * @author snps
 * @date 下午9:05:00
 */
public interface FuncMenuDao {
	
	/**
	 * 通过所属模块Id获取
	 * @param moduleId
	 * @return List<FuncMenu>
	 */
	List<FuncMenu> queryByModule(long moduleId) ;

	/**
	 * 通过id获取
	 * @param id
	 * @return FuncMenu
	 */
	FuncMenu queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(FuncMenu entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(FuncMenu entity) ;
	
	void delete(long id) ;
	
}