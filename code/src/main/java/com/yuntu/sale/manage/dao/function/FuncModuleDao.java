package com.yuntu.sale.manage.dao.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncModule;

/**
 * @Description 基础功能_模块Dao
 * @author snps
 * @date 下午9:05:00
 */
public interface FuncModuleDao {
	
	/**
	 * 得到全部功能模块
	 * @return List<FuncModule>
	 */
	List<FuncModule> queryAll() ;
	
	/**
	 * 获得某分类下的功能模块
	 * @param categoryId
	 * @return List<FuncModule>
	 */
	List<FuncModule> queryByCategory(int categoryId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncModule
	 */
	FuncModule queryById(Long id) ;
	
	long insert(FuncModule entity) ;
	
	void update(FuncModule entity) ;
	
	void delete(long id) ;
	
}