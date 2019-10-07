package com.yuntu.sale.orders.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.ConsumeService;
import com.yuntu.sale.orders.service.EnterpriseOrdersService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.RealCustomerMessageVo;
import com.yuntu.sale.orders.vo.RealCustomerVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;

import net.sf.json.JSONObject;


/**
 * 码核销
 * @author Jack.jiang
 * @version 2018-05-16
 */
@Controller
@RequestMapping(value = {"/orders/consume"})
public class ConsumeController extends BaseAction{
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private SupplierProductService supplierProductService;

	@Autowired
	private ScenicService scenicService;
	
	@Autowired
	private ConsumeService consumeService;
	
	@Autowired
	private EnterpriseOrdersService enterpriseOrdersService;
	

	/**
	 * 进入查询核销页面
	 */
	@RequestMapping(value = "/query/code", method = RequestMethod.GET)
	public String codeQuery(Long enterpriseOrderId,Model model) {
		//子订单
		EnterpriseOrders enterpriseOrders = enterpriseOrdersService.get(enterpriseOrderId);
		//订单
		Orders order = ordersService.get(enterpriseOrders.getOrdersId());
		model.addAttribute("order", order) ;
		//码
		List<Code> codes=codeService.findOrderList(enterpriseOrders.getOrdersId());
		List<Map<String,Object>> realNames =Lists.newArrayListWithCapacity(codes.size());
		for (Code cc:codes) {
			Map<String, Object> mapObj=Maps.newHashMap();
			try {
				mapObj = BeanUtils.describe(cc);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			}
			if(!StringUtils.isEmpty(cc.getRealName())){
				//try {
					JSONObject jsonObject=JSONObject.fromObject(cc.getRealName());
					mapObj.put("consumerName", jsonObject.get("consumerName"));
					mapObj.put("consumerCard", jsonObject.get("consumerCard"));
				//}catch(Exception e) {
				//	e.printStackTrace();
				//}
			}
			realNames.add(mapObj);
		}
		model.addAttribute("realNames", realNames) ;
		//model.addAttribute("codes", codes) ;
		//商品
		SupplierProduct product = supplierProductService.getById(order.getSproductId()) ;
		model.addAttribute("product", product) ;
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(product.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;
		
		
		
		return "/orders/consume/code_query" ;
	}
	
	/**
	 * 进入验码
	 */
	@RequestMapping(value = "/check/code", method = RequestMethod.POST)
	@ResponseBody
	public String checkCode(Long orderId,String codes,Integer num) {
		InfoUser user=super.getCurrentUser();
		//订单
		Orders order = ordersService.get(orderId);
		//商品
		SupplierProduct product = supplierProductService.getById(order.getSproductId());
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(product.getScenicId());
		
		String [] codeArr=StringUtils.split(codes, ",");
		if(codeArr.length>0) {
			boolean sucFlag=false;
			for(String cc:codeArr) {
				String result=consumeService.consumeCode(cc,num,infoScenicPo.getId(),user.getId());
				if(!StringUtils.isEmpty(result)) {
					JSONObject jsonObject=JSONObject.fromObject(result);
					 if (jsonObject.has("status")) {  
						 if(jsonObject.getInt("status")==200) {
							 sucFlag=true;
						 }else {
							 String errMsg=cc+":"+jsonObject.getString("msg");
							 jsonObject.put("msg", errMsg);
							 return jsonObject.toString();
						 }
				      }  
				}
				if(!sucFlag) {
					 return result;
				}
				sucFlag=false;
			}
			return "ok";
		}
		return "err";
	} 
	
}
