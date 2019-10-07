
package com.yuntu.sale.orders.service;

import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.product.po.SupplierProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退款子表-企业退款表Service
 * @author zy
 * @version 2018-04-23
 */


public interface EnterpriseOrdersRefundService  {

	public EnterpriseOrdersRefund get(@Param("id")Long id);
	
	public List<EnterpriseOrdersRefund> findList(EnterpriseOrdersRefund enterpriseOrdersRefund) ;

	public void delete(Long id);

	/**
	 * 查询子退款单
	 * @param id 主退款单id
	 * @param childId
	 * @param parentId
	 * @return
	 */
    EnterpriseOrdersRefund getOne(@Param("id")Long id, @Param("childId")Long childId, @Param("parentId")Long parentId);

	List<EnterpriseOrdersRefund> getorder(@Param("id")Long id, @Param("childId")Long childId, @Param("parentId")Long parentId);

	void save( @Param("status")Long status,
			   @Param("ordersRefund")OrdersRefund ordersRefund,
			   @Param("orders")Orders orders,
			   @Param("listcode")List<Code> listcode,
			   @Param("enterpriseOrdersList")List<EnterpriseOrders> enterpriseOrdersList,
			   @Param("mapList")List<Map> mapList,
			   @Param("product")SupplierProduct product);
}