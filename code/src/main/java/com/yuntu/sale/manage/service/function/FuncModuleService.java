package com.yuntu.sale.manage.service.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncModule;

/**
 * 基础功能_模块Service
 * @Description  
 * @author snps
 * @date 下午2:05:11
 */
public interface FuncModuleService {
	
	/**
	 * 得到全部功能模块（调用场景：系统管理员使用）
	 * @Description 返回所有信息，不区别是否删除
	 * @return List<FuncModule>
	 */
	List<FuncModule> getAll() ;
	
	/**
	 * 获得某分类下的功能模块（预留扩展，暂未使用）
	 * @Description 返回类别下所有信息，不区别是否删除
	 * @param categoryId 所属类别Id
	 * @return List<FuncModule>
	 */
	List<FuncModule> getByCategory(int categoryId) ;
	
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncModule
	 */
	FuncModule getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long save(FuncModule entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	void edit(FuncModule entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @return int（0：删除成功，!0：已分配给企业使用不能删除）
	 */
	int delete(long id) ;
	
}