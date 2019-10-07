/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.yuntu.sale.product.dao;

import com.yuntu.sale.product.po.SaleGroupLog;
import org.apache.ibatis.annotations.Param;

/**
 * 分销组操作日志DAO接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupLogDao {

    /**
     * 通过Id查询
     * @return SaleGroupLog
     */
    SaleGroupLog queryById(@Param("id") Long id) ;

    /**
     * 保存
     * @param entity
     */
    void insert(SaleGroupLog entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(SaleGroupLog entity) ;

}