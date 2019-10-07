package com.yuntu.sale.ifconf.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.google.common.collect.Maps;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.ifconf.po.PlatformInterfacePo;
import com.yuntu.sale.ifconf.service.PlatformInterfaceService;
import com.yuntu.sale.ifconf.service.RequestSupplierSysService;
import com.yuntu.sale.ifconf.vo.OrderResutlVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.OrdersRefundService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 请求供应商接口
 * @author Jack.jiang
 * @date 2018年6月6日
 */
@Controller
@RequestMapping(value = {"/ifconf/req"})
public class RequestSupplierSysAction extends BaseAction{

	@Autowired
	private RequestSupplierSysService requestSupplierSysService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private SupplierProductService supplierProductService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private OrdersRefundService ordersRefundService;

	/**
	 * 下单
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public String order(String orderno) {
		if(StringUtils.isEmpty(orderno)) {
			return "";
		}
		Orders order=ordersService.getByNo(orderno);
		if(order==null) {
			return "";
		}
		SupplierProduct supplierProduct=supplierProductService.getById(order.getSproductId());
		List<Code> codeList=codeService.findOrderList(order.getId());
		OrderResutlVo orderResutlVo=requestSupplierSysService.order(supplierProduct, order, codeList);
		if(orderResutlVo==null) {
			return "";
		}
		String json = JSONObject.toJSON(orderResutlVo).toString() ;
		return json ;
	}
	
	/**
	 * 退款
	 */
	/*
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	@ResponseBody
	public String refund(String orderno) {
		if(StringUtils.isEmpty(orderno)) {
			return "";
		}
		Orders order=ordersService.getByNo(orderno);
		if(order==null) {
			return "";
		}
		OrderResutlVo orderResutlVo=requestSupplierSysService.refund(order, orderRefund);
		if(orderResutlVo==null) {
			return "";
		}
		String json = JSONObject.toJSON(orderResutlVo).toString() ;
		return json ;
	}
	*/
	
}
