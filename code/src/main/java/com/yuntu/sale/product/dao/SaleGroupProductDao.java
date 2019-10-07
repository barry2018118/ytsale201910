/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.yuntu.sale.product.dao;

import com.yuntu.sale.product.po.SaleGroupProduct;
import com.yuntu.sale.product.po.SaleGroupProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销组商品DAO接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupProductDao {

    /**
     * 通过Id查询
     * @return SaleGroupProduct
     */
    SaleGroupProduct queryById(@Param("id") Long id) ;

    /**
     * 保存
     * @param entity
     */
    void insert(SaleGroupProduct entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(SaleGroupProduct entity) ;

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") Long id) ;

    /**
     * 查询分销组 - 商品 列表
     * @param no
     * @param name
     * @param groupId
     * @param createGroupId
     * @return
     */
    List<SaleGroupProductVo> querySaleGroupProduct(@Param("no")String no, @Param("name")String name, @Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId);

    /**
     * 根据条件单条记录查询
     * @param groupId   分销组id
     * @param createGroupId  创建分销组id
     * @param productId  商品id
     * @return
     */
    List<SaleGroupProduct> queryOne(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId, @Param("productId") Long productId);

    /**
     * 分销组 - 商品列表
     * @param groupId 分销组 id
     * @param createGroupId 创建分销组企业 id
     * @return SaleGroupEnterpriseVo
     */
    List<SaleGroupProductVo> getProductList(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId);
    
    /**
     * 查询组中已存在的商品
     * @param groupId 商品组Id
     * @return List<SaleGroupProduct>
     */
    List<SaleGroupProduct> queryIsExistProduct(@Param("groupId") Long groupId);
    
    /**
     * 修改商品组中商品的分销状态-开启分销
     * @param groupId 商品组Id
     * @param sproductId 商品Id
     */
    void updateDescribution(@Param("groupId") Long groupId, @Param("sproductId") Long sproductId) ;
    
    /**
     * 设置商品组中我的商品的分销状态-停止分销
     * @param groupId 商品组Id
     * @param sproductId 商品Id
     */
    void updateStopDescribution(@Param("groupId") Long groupId, @Param("sproductId") Long sproductId) ;
    
    /**
     * 修改商品组中商品的分销状态-停止分销
     * @param groupTracks 商品组Ids
     * @param sproductId 商品Id
     */
    void deleteChildDescribution(@Param("groupTracks") String groupTracks, @Param("sproductId") Long sproductId) ;
    
}