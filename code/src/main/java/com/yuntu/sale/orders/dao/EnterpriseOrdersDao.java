
package com.yuntu.sale.orders.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.vo.EnterpriseOrdersConsumeVo;

/**
 * 订单表子-企业订单表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface EnterpriseOrdersDao {

    EnterpriseOrders get(@Param("id") Long id);

    List<EnterpriseOrders> findList(@Param("enterpriseOrders") EnterpriseOrders enterpriseOrders);

    void save(EnterpriseOrders enterpriseOrders);

    void update(EnterpriseOrders enterpriseOrders);

    /**
     * 查询子订单
     *
     * @param id       订单 id
     * @param childId  发起订单 企业id
     * @param parentId 上级企业id
     * @return
     */
    EnterpriseOrders queryOne(@Param("id") Long id, @Param("childId") Long childId, @Param("parentId") Long parentId);

    List<EnterpriseOrders> queryList(@Param("childId") Long childId,
                                     @Param("parentId") Long parentId,
                                     @Param("productNo") String productNo,
                                     @Param("productName") String productName,
                                     @Param("linkMan") String linkMan,
                                     @Param("linkPhone") String linkPhone,
                                     @Param("startTime") String startTime,
                                     @Param("endTime") String endTime,
                                     @Param("status") Integer status);

    /**
     * 消费列表
     *
     * @param enterpriseId
     * @param productNo
     * @param productName
     * @return
     */
    List<EnterpriseOrdersConsumeVo> queryConsumeList(@Param("enterpriseId") Long enterpriseId,
                                                     @Param("productNo") String productNo,
                                                     @Param("productName") String productName);
}