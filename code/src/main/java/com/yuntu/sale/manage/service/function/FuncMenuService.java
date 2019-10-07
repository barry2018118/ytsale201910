package com.yuntu.sale.manage.service.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncMenu;

/**
 * 基础功能_菜单Service
 * @Description 
 * @author snps
 * @date 下午3:17:45
 */
public interface FuncMenuService {
	
	/**
	 * 通过所属模块Id获取
	 * @param moduleId
	 * @return List<FuncMenu>
	 */
	List<FuncMenu> getByModule(long moduleId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncMenu
	 */
	FuncMenu getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long save(FuncMenu entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	public void edit(FuncMenu entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @return int（0：删除成功，!0：已分配给企业使用不能删除）
	 */
	int delete(long id) ;
	
}