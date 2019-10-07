package com.yuntu.sale.manage.dao.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncAction;

/**
 * @Description 基础功能_动作Dao
 * @author snps
 * @date 下午9:05:00
 */
public interface FuncActionDao {
	
	/**
	 * 通过所属菜单Id获取
	 * @param menuId
	 * @return List<FuncAction>
	 */
	List<FuncAction> queryByMenu(long menuId) ;

	/**
	 * 通过id获取
	 * @param id
	 * @return FuncAction
	 */
	FuncAction queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(FuncAction entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(FuncAction entity) ;
	
	void delete(long id) ;
	
}