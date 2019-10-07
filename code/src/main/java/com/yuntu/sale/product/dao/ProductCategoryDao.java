package com.yuntu.sale.product.dao;

import java.util.List;

import com.yuntu.sale.product.po.ProductCategory;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 产品类别Dao接口
 * @author snps
 * @date 2018年2月14日 下午4:12:49
 */
public interface ProductCategoryDao {

	/**
	 * 通过商品类别（模糊）查询
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryByName(@Param("name") String name) ;
	
	/**
	 * 通过Id查询
	 * @return ProductCategory
	 */
	ProductCategory queryById(@Param("id") Long id) ;
	
	/**
	 * 通过商品类别（唯一）查询	（使用：用于判断商品类别是否重复）
	 * @param name
	 * @return ProductCategory
	 */
	ProductCategory queryByUniqueName(@Param("name") String name) ;
	
	/**
	 * 保存
	 * @param entity
	 */
	void insert(ProductCategory entity) ;
	
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
    List<ProductCategory> query();
}