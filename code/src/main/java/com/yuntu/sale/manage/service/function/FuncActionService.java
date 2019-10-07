package com.yuntu.sale.manage.service.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncAction;

/**
 * 基础功能_动作Service
 * @Description 
 * @author snps
 * @date 下午3:45:26
 */
public interface FuncActionService {

	/**
	 * 通过所属菜单Id获取
	 * @param menuId
	 * @return List<FuncAction>
	 */
	List<FuncAction> getByMenu(long menuId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncAction
	 */
	FuncAction getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long save(FuncAction entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	public void edit(FuncAction entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @return int（0：删除成功，!0：已分配给企业使用不能删除）
	 */
	int delete(long id) ;
	
}