package com.yuntu.sale.orders.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersConsume;
import com.yuntu.sale.orders.service.EnterpriseOrdersService;
import com.yuntu.sale.orders.service.OrdersConsumeService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.EnterpriseOrdersConsumeVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 消费列表
 * @author zy
 * @date 2018年5月14日
 */
@Controller
@RequestMapping(value = "/orders/platform/consume")
public class PlatformOrdersConsumeAction extends BaseAction {

	@Resource
	private SupplierProductService supplierProductService;

	@Resource
	private ScenicService scenicService;

	@Resource
	private EnterpriseOrdersService enterpriseOrdersService;

	@Resource
	private OrdersService ordersService;

	@Resource
	private OrdersConsumeService ordersConsumeService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/platform/consume/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("eid", super.getCurrentUser().getEnterpriseId()) ;
		return "/orders/platform/consume/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/toSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo,String productName) {

		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<EnterpriseOrdersConsumeVo> pager = new Page<EnterpriseOrdersConsumeVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<EnterpriseOrdersConsumeVo> page = enterpriseOrdersService.getConsumeList(pager, null,productNo,productName);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}

	/**
	 * 详细页
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String getcard(HttpServletRequest request, @PathVariable Long id, Model model) {
		OrdersConsume ordersConsume = ordersConsumeService.get(id);
		model.addAttribute("entity", ordersConsume) ;
		//订单
		Orders orders = ordersService.get(ordersConsume.getOrdersId());
		model.addAttribute("orders", orders) ;
		// 商品
		SupplierProduct product = supplierProductService.getById(ordersConsume.getSproductId()) ;
		model.addAttribute("product", product) ;
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(product.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;
		return "/orders/platform/consume/card" ;
	}
}