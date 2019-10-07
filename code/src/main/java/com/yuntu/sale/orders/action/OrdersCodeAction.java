package com.yuntu.sale.orders.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.common.SmsManager;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.RealCustomerVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;

/**
 * @Description 订单电子码Action（提供重新发码等功能）
 * @author snps
 * @date 2018年5月22日 上午10:57:43
 */
@Controller
@RequestMapping(value = "/orders/code")
public class OrdersCodeAction extends BaseAction {

	@Resource
	private OrdersService ordersService;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	private SupplierProductService supplierProductService;
	
	
	/**
	 * 重新发码
	 */
	@RequestMapping(value = "/repeatSendCode", method = RequestMethod.GET)
	@ResponseBody
	public String main(HttpServletRequest request, Long orderId, Model model) {
		
		// 获得订单信息
		Orders orders = ordersService.get(orderId);
		if(BaseUtil.isEmpty(orders)) {
			OperatorFailureVo message = new OperatorFailureVo("重新发码失败-订单信息为空！") ;
			return JSONObject.toJSONString(message) ;
		}
		
		// 获得订单对应的码信息
		List<Code> lstOrderCode = codeService.findOrderList(orderId);
		if(BaseUtil.isEmpty(lstOrderCode)) {
			OperatorFailureVo message = new OperatorFailureVo("重新发码失败-码信息为空！") ;
			return JSONObject.toJSONString(message) ;
		}
		
		// 获得商品信息
		SupplierProduct product = supplierProductService.getById(orders.getSproductId());
		if(BaseUtil.isEmpty(product)) {
			OperatorFailureVo message = new OperatorFailureVo("重新发码失败-产品信息为空！") ;
			return JSONObject.toJSONString(message) ;
		}
		
		// 根据商品-发码方式（实名制 & 非实名制）进行重新发码
		if(product.getIsRealname() == 1) {
			// 实名制发码
			OrdersCodeAction.sendOrdersCodeByRealnameProduct(orders.getTel(), lstOrderCode);
			
			OperatorSuccessVo message = new OperatorSuccessVo("重新发码成功！") ;
			return JSONObject.toJSONString(message) ;
		} else {
			// 非实名制发码
			OrdersCodeAction.sendOrdersCodeBySimpleProduct(orders.getTel(), lstOrderCode.get(0));
			
			OperatorSuccessVo message = new OperatorSuccessVo("重新发码成功！") ;
			return JSONObject.toJSONString(message) ;
		}
	}
	
	/**
	 * 实名制商品发码方法
	 * @param phone 接收短信的手机号
	 * @param lstOrdersCode 码信息集合
	 */
	public static void sendOrdersCodeByRealnameProduct(final String phone, final List<Code> lstOrdersCode) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(BaseUtil.isEmpty(lstOrdersCode)) {
					return;
				} else {
					for(Code code : lstOrdersCode) {
						// 处理实名制信息
						if(BaseUtil.isEmpty(code.getRealName())) {
							break;
						} else {
							RealCustomerVo realCustomer = JSONObject.parseObject(code.getRealName(), RealCustomerVo.class);

							String content = "【云端行】{name}({idCard})，您好。您所购买的“{product}”产品，购买数量：{num}。产品验证码为：{code}。请使用此验证码进行消费！严防诈骗！不要告诉任何人！";
							content = content.replace("{name}", realCustomer.getConsumerName());
							content = content.replace("{idCard}", realCustomer.getConsumerCard());
							content = content.replace("{product}", code.getCodeName());
							content = content.replace("{num}", "1张");
							content = content.replace("{code}", code.getCardPwd());
							SmsManager.sendMessage(phone, content);
						}
					}
				}
			}
		}).start();
	}
	
	/**
	 * 普通商品发码方法
	 * @param phone 接收短信的手机号
	 * @param ordersCode 码信息对象
	 */
	public static void sendOrdersCodeBySimpleProduct(final String phone, final Code ordersCode) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(BaseUtil.isEmpty(ordersCode)) {
					return;
				} else {
					String content = "【云端行】您所购买的“{product}”产品，购买数量：{num}。产品验证码为：{code}。请使用此验证码进行消费！严防诈骗！不要告诉任何人！";
					content = content.replace("{product}", ordersCode.getCodeName());
					content = content.replace("{num}", ordersCode.getNum().toString());
					content = content.replace("{code}", ordersCode.getCardPwd());
					SmsManager.sendMessage(phone, content);
				}
			}
		}).start();
	}
	
}