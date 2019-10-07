
package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.OrdersConsume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 码核销记录表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersConsumeDao {

    public OrdersConsume get(@Param("id") Long id);

    public List<OrdersConsume> findList(OrdersConsume ordersConsume);

    public Page<OrdersConsume> findPage(Page<OrdersConsume> page, OrdersConsume ordersConsume) ;

    public void save(OrdersConsume ordersConsume);

    public void delete(OrdersConsume ordersConsume);

    List<OrdersConsume> queryOrderId(@Param("id") Long id);

    List<OrdersConsume> queryCodeId(@Param("id") Long id);
}