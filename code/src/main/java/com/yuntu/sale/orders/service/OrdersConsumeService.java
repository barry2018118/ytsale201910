
package com.yuntu.sale.orders.service;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.OrdersConsume;

import java.util.List;

/**
 * 码核销记录表Service
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersConsumeService  {

	public OrdersConsume get(Long id);
	
	public List<OrdersConsume> findList(OrdersConsume ordersConsume);
	
	public Page<OrdersConsume> findPage(Page<OrdersConsume> page, OrdersConsume ordersConsume) ;
	
	public void save(OrdersConsume ordersConsume);
	
	public void delete(OrdersConsume ordersConsume);

	List<OrdersConsume> getOrderId(Long ordersId);

	List<OrdersConsume> getCodeId(Long id);
}