
package com.yuntu.sale.orders.service;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.orders.vo.EnterpriseOrdersRefundVo;
import com.yuntu.sale.orders.vo.OrdersRefundListVo;
import com.yuntu.sale.product.po.SupplierProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退款主表Service
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersRefundService  {

	public OrdersRefund get(@Param("id")Long id);

	public OrdersRefund getByOrderAndSerial(@Param("orderId")Long orderId, @Param("serialNumber")String serialNumber);
	
	public List<OrdersRefund> findList(OrdersRefund ordersRefund);
	
	void update(@Param("orders")OrdersRefund ordersRefund);

	void delete(@Param("status")Long status,@Param("id") Long id);

	Page<OrdersRefundListVo> getList(Page<OrdersRefundListVo> pager,
									 @Param("childId") Long childId,
									 @Param("productNo")String productNo,
									 @Param("productName")String productName,
									 @Param("startTime")String startTime,
									 @Param("endTime")String endTime,
									 @Param("status")Integer status);

	Page<EnterpriseOrdersRefundVo> getOrdersList(Page<EnterpriseOrdersRefundVo> pager,
											   @Param("childId") Long childId,
											   @Param("parentId") Long parentId,
											   @Param("productNo")String productNo,
											   @Param("productName")String productName,
											   @Param("startTime")String startTime,
											   @Param("endTime")String endTime,
											   @Param("status")Integer status);

	/**
	 * 下  退款单
	 * @param ordersRefund
	 * @param orders
	 * @param codeList
	 * @param enterpriseOrdersList
	 * @param mapList
	 * @param product
	 */
	void save(@Param("ordersRefund")OrdersRefund ordersRefund,
			  @Param("orders")Orders orders,
			  @Param("codeList")List<Code> codeList,
			  @Param("enterpriseOrdersList")List<EnterpriseOrders> enterpriseOrdersList,
			  @Param("mapList")List<Map> mapList,
			  @Param("product")SupplierProduct product
			  );
}