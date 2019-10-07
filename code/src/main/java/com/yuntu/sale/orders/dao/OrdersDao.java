
package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单主表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersDao {

    Orders get(@Param("id") Long id);

    Orders getByNo(@Param("orderno") String orderno);

    List<Orders> findList(@Param("orders")Orders orders);

    void add(Orders orders);

    void update(Orders orders);

    void delete(@Param("status") Long status,@Param("id") Long id);

    List<Orders> queryList(@Param("childId") Long childId,
                           @Param("parentId") Long parentId,
                           @Param("productNo")String productNo,
                           @Param("productName")String productName,
                           @Param("startTime")String startTime,
                           @Param("endTime")String endTime,
                           @Param("status")Integer status);

    Orders queryThirdOrders(@Param("tuanname")String tuanname,  @Param("payId") String payId);
}