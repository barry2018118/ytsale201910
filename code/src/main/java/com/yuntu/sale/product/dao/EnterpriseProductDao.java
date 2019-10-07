/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.yuntu.sale.product.dao;

import com.yuntu.sale.product.po.EnterpriseProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品管理DAO接口
 * @author zy
 * @version 2018-04-02
 */
public interface EnterpriseProductDao {

    /**
     * 通过Id查询
     * @return EnterpriseProduct
     */
    EnterpriseProduct queryById(@Param("id") Long id) ;

    /**
     * 通过priductId查询
     * @return count()
     */
    Integer queryByProductId(@Param("productId") Long productId) ;

    /**
     * 查询商品是否分销
     * @param productId
     * @return Integer
     */
    Integer queryProductDistributionNum(@Param("productId") Long productId) ;
    
    /**
     * 通过priductId查询
     * @return count()
     */
    Integer queryByParentId(@Param("parentId") Long parentId) ;
    
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
    EnterpriseProduct queryOne(@Param("productId")Long productId, @Param("parentId")Long parentId, @Param("childId")Long childId);

    /**
     *  具体 商户 - 商品 记录查询
     *  @param productId 商品 id
     *  @param childId 下级企业id
     *  @param parentId 上级Id
     *  @param groupId 分销组 id
     * @return EnterpriseProduct
     */
    List<EnterpriseProduct> getDelSearch(@Param("productId") Long productId,@Param("parentId") Long parentId, @Param("childId") Long childId, @Param("groupId") Long groupId);
    
    /**
     * 修改企业商品的分销状态-开启分销
     * @param groupId 商品组Id
     * @param sproductId 商品Id
     */
    void updateDescribution(@Param("groupId") Long groupId, @Param("sproductId") Long sproductId) ;
    
    /**
     * 修改企业商品的分销状态-停止分销
     * @param groupTracks 商品组Ids
     * @param sproductId 商品Id
     */
    void updateStopDescribution(@Param("groupTracks") String groupTracks, @Param("sproductId") Long sproductId) ;
    
}