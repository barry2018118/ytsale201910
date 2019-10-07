package com.yuntu.sale.product.service;

import com.coolshow.util.Page;
import com.yuntu.sale.product.po.SaleGroup;
import org.apache.ibatis.annotations.Param;

/**
 *分销组Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupService {

	/**
	 * 通过Id查询
	 * @return SaleGroup
	 */
	SaleGroup getById(@Param("id")Long id) ;

	/**
	 * 通过Id查询
	 * @return SaleGroup
	 */
	SaleGroup getByName(@Param("name")String name,@Param("enterpriseId")Long enterpriseId) ;

	/**
	 * 通过name检索
	 * @return SaleGroup
	 */
	Page<SaleGroup> queryByName(Page<SaleGroup> pager, @Param("name") String name,@Param("enterpriseId") Long enterpriseId) ;

	/**
	 * 保存
	 * @param entity
	 */
	void save(SaleGroup entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(SaleGroup entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(@Param("id") Long id) ;
	
}