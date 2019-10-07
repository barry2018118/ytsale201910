/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.yuntu.sale.product.dao;

import com.yuntu.sale.product.po.SaleGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销组DAO接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupDao {

    /**
     * 通过Id查询
     * @return SaleGroup
     */
    SaleGroup queryById(@Param("id") Long id) ;

    /**
     * 通过Id查询
     * @return SaleGroup
     */
    SaleGroup getName(@Param("name") String name,@Param("enterpriseId") Long enterpriseId) ;

    /**
     * 通过name查询
     * @return SaleGroup
     */
    List<SaleGroup> queryByName(@Param("name") String name,@Param("enterpriseId") Long enterpriseId) ;

    /**
     * 保存
     * @param entity
     */
    void insert(SaleGroup entity) ;

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