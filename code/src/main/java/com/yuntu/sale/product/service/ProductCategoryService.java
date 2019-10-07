package com.yuntu.sale.product.service;

import com.coolshow.util.Page;
import com.yuntu.sale.product.po.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品类别Service接口
 * @author snps
 * @date 2018年2月14日 下午4:15:23
 */
public interface ProductCategoryService {

	/**
	 * 通过商品类别（模糊）查询
	 * @param pager 分页对象
	 * @param name 商品类别
	 * @return Page<ProductCategory>
	 */
	Page<ProductCategory> getByName(Page<ProductCategory> pager, String name) ;
	
	/**
	 * 通过Id查询
	 * @return ProductCategory
	 */
	ProductCategory getById(@Param("id") Long id) ;
	
	/**
	 * 通过商品类别（唯一）查询	（使用：用于判断商品类别是否重复）
	 * @param name
	 * @return ProductCategory
	 */
	ProductCategory getByUniqueName(@Param("name") String name) ;
	
	/**
	 * 保存
	 * @param entity
	 */
	void save(ProductCategory entity) ;
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(ProductCategory entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(@Param("id") Long id) ;

	/**
	 * 查询全部
	 */

	List<ProductCategory> getAll();
	
}