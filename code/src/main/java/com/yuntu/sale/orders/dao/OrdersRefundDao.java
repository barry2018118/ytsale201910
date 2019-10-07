
package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.orders.vo.OrdersRefundListVo;
import com.yuntu.sale.product.po.SupplierProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退款主表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersRefundDao {

    public OrdersRefund get(@Param("id")Long id);

    public OrdersRefund getByOrderAndSerial(@Param("orderId")Long orderId, @Param("serialNumber")String serialNumber);

    public List<OrdersRefund> findList(OrdersRefund ordersRefund);

    void save(OrdersRefund ordersRefund);

    void update(OrdersRefund ordersRefund);

    void delete(@Param("status")Long status,@Param("id") Long id);

    List<OrdersRefundListVo> queryList(@Param("childId") Long childId,
                                       @Param("productNo")String productNo,
                                       @Param("productName")String productName,
                                       @Param("startTime")String startTime,
                                       @Param("endTime")String endTime,
                                       @Param("status")Integer status);
}