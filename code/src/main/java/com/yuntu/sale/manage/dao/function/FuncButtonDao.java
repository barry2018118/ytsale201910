package com.yuntu.sale.manage.dao.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncButton;

/**
 * @Description 基础功能_按钮Dao
 * @author snps
 * @date 下午9:05:00
 */
public interface FuncButtonDao {

	/**
	 * 通过所属动作Id获取
	 * @param actionId
	 * @return List<FuncButton>
	 */
	List<FuncButton> queryByAction(long actionId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncButton
	 */
	FuncButton queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(FuncButton entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	public void update(FuncButton entity) ;
	
	void delete(long id) ;
	
}