
package com.yuntu.sale.orders.service.impl;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.dao.OrdersConsumeDao;
import com.yuntu.sale.orders.po.OrdersConsume;
import com.yuntu.sale.orders.service.OrdersConsumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 码核销记录表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("ordersConsumeService")
public class OrdersConsumeServiceImpl implements OrdersConsumeService {

	@Resource
	private OrdersConsumeDao ordersConsumeDao;

	public OrdersConsume get(Long id) {
		return ordersConsumeDao.get(id);
	}
	
	public List<OrdersConsume> findList(OrdersConsume ordersConsume) {
		return ordersConsumeDao.findList(ordersConsume);
	}
	
	public Page<OrdersConsume> findPage(Page<OrdersConsume> page, OrdersConsume ordersConsume) {
		return ordersConsumeDao.findPage(page, ordersConsume);
	}
	
	public void save(OrdersConsume ordersConsume) {
		ordersConsumeDao.save(ordersConsume);
	}

	public void delete(OrdersConsume ordersConsume) {
		ordersConsumeDao.delete(ordersConsume);
	}

	@Override
	public List<OrdersConsume> getOrderId(Long ordersId) {
		return  ordersConsumeDao.queryOrderId(ordersId);
	}

	@Override
	public List<OrdersConsume> getCodeId(Long id) {
		return ordersConsumeDao.queryCodeId(id);
	}


}