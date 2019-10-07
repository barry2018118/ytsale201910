/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.yuntu.sale.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.product.po.SaleGroupEnterprise;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;

/**
 * 分销组分销商DAO接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupEnterpriseDao  {

    /**
     * 通过Id查询
     * @return SaleGroupEnterprise
     */
    SaleGroupEnterprise queryById(@Param("id") Long id) ;

    /**
     * 保存
     * @param entity
     */
    void insert(SaleGroupEnterprise entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(SaleGroupEnterprise entity) ;

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") Long id) ;

    /**
     * 查询分销组 - 分销商 列表
     * @param name
     * @param groupId
     * @param createGroupId
     * @return
     */
    List<SaleGroupEnterpriseVo> querySaleGroupEnterprise(@Param("name")String name,@Param("groupId") Long groupId,@Param("createGroupId") Long createGroupId);

    /**
     * 根据条件单条记录查询
     * @param groupId   分销组id
     * @param createGroupId  创建分销组id
     * @param childEnterpriseId  分销商企业id
     * @return
     */
    List<SaleGroupEnterprise> queryOne(@Param("groupId") Long groupId,@Param("createGroupId") Long createGroupId,@Param("childEnterpriseId") Long childEnterpriseId);

    /**
     * 分销组 - 分销商列表
     * @param groupId 分销组 id
     * @param createGroupId 创建分销组企业 id
     * @return SaleGroupEnterpriseVo
     */
    List<SaleGroupEnterpriseVo> getEnterPriseList(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId) ;
    
    /**
     * 查询组中已存在的企业
     * @param groupId 商品组Id
     * @return List<SaleGroupEnterprise>
     */
    List<SaleGroupEnterprise> queryIsExistEnterprise(@Param("groupId") Long groupId);
    
    /**
     * 查询企业是否已属于商品组
     * @param enterpriseId 企业Id
     * @return int
     */
    int queryChildIsBePartGroup(@Param("enterpriseId") Long enterpriseId);
    
    /**
     * 查询组内可用的企业数量
     * @param groupId 商品组Id
     * @return int
     */
    int queryEnterpriseNUm(@Param("groupId") Long groupId);
    
}