
package com.yuntu.sale.orders.service;

import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.product.po.SupplierProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单主表Service
 * @author zy
 * @version 2018-04-23
 */

public interface OrdersService  {

	Orders get(@Param("id") Long id);

	Orders getByNo(@Param("orderno") String orderno);

	List<Orders> findList(@Param("orders")Orders orders);

	void thirdSave(@Param("orders") Orders orders,
			  @Param("codeList") List<Code> codeList,
			  @Param("listEnterpriseOrders") List<EnterpriseOrders> listEnterpriseOrders,
			  @Param("product") SupplierProduct product);

	void thirdUpdate(@Param("orders") Orders orders,
			  @Param("codeList") List<Code> codeList,
			  @Param("listEnterpriseOrders") List<Map> listEnterpriseOrders);

	void save(@Param("orders") Orders orders,
			  @Param("codeList") List<Code> codeList,
			  @Param("listEnterpriseOrders") List<Map> listEnterpriseOrders,
			  @Param("product") SupplierProduct product);

	void update(@Param("orders")Orders orders);

	void delete(@Param("status")Long status,@Param("id") Long id);

	Page<Orders> getList(Page<Orders> pager,
										 @Param("childId") Long childId,
										 @Param("parentId") Long parentId,
										 @Param("productNo")String productNo,
										 @Param("productName")String productName,
										 @Param("startTime")String startTime,
										 @Param("endTime")String endTime,
										 @Param("status")Integer status);

    Page<EnterpriseOrders> getOrdersList(Page<EnterpriseOrders> pager,
							    @Param("childId") Long childId,
								@Param("parentId") Long parentId,
							   @Param("productNo")String productNo,
							   @Param("productName")String productName,
							   @Param("startTime")String startTime,
							   String linkMan,
							   String linkPhone,
							   @Param("endTime")String endTime,
							   @Param("status")Integer status);

    Orders getThirdOrders( @Param("tuanname")String tuanname, @Param("payId") String payId);
}