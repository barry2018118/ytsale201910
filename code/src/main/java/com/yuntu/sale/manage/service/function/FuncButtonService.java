package com.yuntu.sale.manage.service.function;

import java.util.List;

import com.yuntu.sale.base.po.function.FuncButton;

/**
 * 基础功能_按钮Service
 * @Description 
 * @author snps
 * @date 下午3:55:35
 */
public interface FuncButtonService {

	/**
	 * 通过所属动作Id获取
	 * @param actionId
	 * @return List<FuncButton>
	 */
	List<FuncButton> getByAction(long actionId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return FuncButton
	 */
	FuncButton getById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long save(FuncButton entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	public void edit(FuncButton entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @return int（0：删除成功，!0：已分配给企业使用不能删除）
	 */
	int delete(long id) ;
	
}