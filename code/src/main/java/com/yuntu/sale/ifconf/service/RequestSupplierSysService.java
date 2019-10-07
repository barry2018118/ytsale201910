package com.yuntu.sale.ifconf.service;

import java.util.List;

import com.yuntu.sale.ifconf.vo.OrderResutlVo;
import com.yuntu.sale.ifconf.vo.ReqResutlVo;
import com.yuntu.sale.ifconf.vo.ThirdOrderNumVo;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.product.po.SupplierProduct;

/**
 * @Description 请求供应商数据接口 
 * @author Jack.jiang
 * @date 2018年5月25日 
 */
public interface RequestSupplierSysService {

	/**
	 * 下单
	 */
	OrderResutlVo order(SupplierProduct supplierProduct,Orders order,List<Code> codeList);
	
	/**
	 * 查询订单详情
	 */
	ThirdOrderNumVo queryOrder(Orders order);
	
	/**
	 * 申请退款
	 */
	ReqResutlVo refund(Orders order,OrdersRefund orderRefund);
	
	/**
	 * 重发短信
	 */
	ReqResutlVo resend(Orders order,String tel);
	
}
