package com.yuntu.sale.product.service;

import com.yuntu.sale.product.po.EnterpriseProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品管理Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface EnterpriseProductService {
	
	/**
	 * 通过Id查询
	 * @return EnterpriseProduct
	 */
	EnterpriseProduct getById(@Param("id") Long id) ;

	/**
	 * 通过priductId查询
	 * @return count()
	 */
	Integer getByProductId(@Param("productId") Long productId) ;
	
	/**
	 * 查询商品是否分销
	 * @param productId
	 * @return Integer
	 */
	Integer getProductDistributionNum(Long productId) ;
	
	/**
	 * 通过parentId查询
	 * @return count()
	 */
	Integer getByParentId(@Param("parentId")Long parentId);
	/**
	 * 保存
	 * @param entity
	 */
	void save(EnterpriseProduct entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(EnterpriseProduct entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(@Param("id") Long id) ;

	/**
	 *  具体 商户 - 商品 记录查询
	 *  @param productId 商品id
	 *  @param childId 下级企业id
	 *  @param groupId 分销组 id
	 * @return EnterpriseProduct
	 */
    List<EnterpriseProduct> getSearch(@Param("productId") Long productId, @Param("childId") Long childId, @Param("groupId") Long groupId);

	/**
	 * 查询分销商 商品组 轨迹
	 * @param productId 商品id
	 * @param parentId 上级Id
	 * @param childId 下级id
	 * @return List<InfoEnterprise>
	 */
	EnterpriseProduct getOne(@Param("productId")Long productId, @Param("parentId")Long parentId, @Param("childId")Long childId);

	/**
	 *  具体 商户 - 商品 记录查询
	 *  @param productId 商品 id
	 *  @param childId 下级企业id
	 *  @param parentId 上级Id
	 *  @param groupId 分销组 id
	 * @return EnterpriseProduct
	 */
	List<EnterpriseProduct> getDelSearch(@Param("productId") Long productId,@Param("parentId") Long parentId, @Param("childId") Long childId, @Param("groupId") Long groupId);


}