package com.yuntu.sale.orders.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.AreaService;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.orders.service.*;
import com.yuntu.sale.orders.vo.*;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/other")
public class OrdersOtherEnterpriseController extends BaseAction {

	@Resource
	private SupplierProductService supplierProductService;

	@Resource
	private ScenicService scenicService;

	@Resource
	private OrdersService ordersService;

	@Resource
	private EnterpriseOrdersService enterpriseOrdersService;


	@Resource
	private OrdersRefundService ordersRefundService;

	@Resource
	private EnterpriseOrdersRefundService enterpriseOrdersRefundService;

	@Resource
	private OrdersConsumeService ordersConsumeService;

	@Resource
	private CodeService codeService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/orders/other/main";
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("enterId", super.getCurrentUser().getEnterpriseId()) ;
		model.addAttribute("userId", super.getCurrentUser().getId()) ;
		return "/orders/orders/other/list";
	}

	/**
	 *退款页
	 */
	@RequestMapping(value = "/{id}/toexit", method = RequestMethod.GET)
	public String toShop(HttpServletRequest request, @PathVariable Long id, Model model) {

		EnterpriseOrders enterpriseOrders = enterpriseOrdersService.get(id);
		model.addAttribute("orders", enterpriseOrders) ;

		Orders orders = ordersService.get(enterpriseOrders.getOrdersId()) ;
		model.addAttribute("order", orders) ;

		model.addAttribute("num", orders.getNum() - orders.getPrintnum() - orders.getLocknum() - orders.getClocknum()) ;

		SupplierProduct supplierProduct = supplierProductService.getById(enterpriseOrders.getSproductId());
		model.addAttribute("product", supplierProduct) ;

		InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;

		//具体码列表
		List<OrderRefundAddCardVo> msg = new ArrayList<OrderRefundAddCardVo>();
		if(supplierProduct.getIsRealname() == 1){
			List<Code> codes = codeService.findOrderList(orders.getId());
			for(Code c : codes){
				if(c.getPrintnum() == 0 && c.getLocknum() == 0 && c.getClocknum() == 0) {
					String capital = "[" + c.getRealName() + "]";
					JSONArray jsonArray = JSONArray.fromObject(capital);
					Object o = jsonArray.get(0);
					net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject.fromObject(o);
					RealCustomerVo realCustomerVo = (RealCustomerVo) net.sf.json.JSONObject.toBean(jsonObject2, RealCustomerVo.class);
					OrderRefundAddCardVo messageVo = new OrderRefundAddCardVo(c.getId(),realCustomerVo.getConsumerName(), realCustomerVo.getConsumerCard(), c.getCardPwd());
					msg.add(messageVo);
				}
			}
			model.addAttribute("cardlist", msg) ;
		}
		return "/orders/orders/my/add";
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo, String productName, String linkMan, String linkPhone, 
			String starttime, String endtime, Integer state) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<EnterpriseOrders> pager = new Page<EnterpriseOrders>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<EnterpriseOrders> page = ordersService.getOrdersList(pager,null,super.getCurrentUser().getEnterpriseId(),
				productNo, productName, linkMan, linkPhone, starttime, endtime, state);

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
		//子订单
		EnterpriseOrders enterpriseOrders = enterpriseOrdersService.get(id);
		model.addAttribute("orders", enterpriseOrders) ;
		//订单
		Orders orders = ordersService.get(enterpriseOrders.getOrdersId()) ;
		model.addAttribute("order", orders) ;
		//商品
		SupplierProduct supplierProduct = supplierProductService.getById(enterpriseOrders.getSproductId());
		model.addAttribute("product", supplierProduct) ;
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;
		//子退款单 - 主退款单
		String statusName = "";
		Integer type  = 2;
		List<OrdersMyEnterpriseCardVo> ordersMyEnterpriseCardVos = new ArrayList<OrdersMyEnterpriseCardVo>();
		List<EnterpriseOrdersRefund> enterpriseOrdersRefund = enterpriseOrdersRefundService.getorder(enterpriseOrders.getOrdersId(),enterpriseOrders.getEnterpriseId(),enterpriseOrders.getParentId());
		if(!BaseUtil.isEmpty(enterpriseOrdersRefund)){
			type = 1;
			for (EnterpriseOrdersRefund e:enterpriseOrdersRefund ) {
				OrdersRefund ordersRefund = ordersRefundService.get(e.getRefundId());
				if(ordersRefund.getStatus() == 0) statusName ="待审核";
				if(ordersRefund.getStatus() == 1) statusName ="审核通过";
				if(ordersRefund.getStatus() == 2) statusName ="审核不通过";
				OrdersMyEnterpriseCardVo ordersMyEnterpriseCardVo = new OrdersMyEnterpriseCardVo(ordersRefund.getCreateTimetime(),statusName,e.getNum().toString());
				ordersMyEnterpriseCardVos.add(ordersMyEnterpriseCardVo);
			}
		}
		model.addAttribute("hasfund", type);
		model.addAttribute("fund", ordersMyEnterpriseCardVos);
		//实名制信息
		if(supplierProduct.getIsRealname() == 1) {
			List<Code> code = codeService.findOrderList(enterpriseOrders.getOrdersId());
			List<RealCustomerMessageVo> msg = new ArrayList<RealCustomerMessageVo>();
			for (Code i : code) {
				String capital = "[" + i.getRealName() + "]";
				JSONArray jsonArray = JSONArray.fromObject(capital);
				Object o = jsonArray.get(0);
				net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject.fromObject(o);
				RealCustomerVo realCustomerVo = (RealCustomerVo) net.sf.json.JSONObject.toBean(jsonObject2, RealCustomerVo.class);
				RealCustomerMessageVo messageVo = new RealCustomerMessageVo(realCustomerVo.getConsumerName(), realCustomerVo.getConsumerCard(), i.getCardPwd());
				msg.add(messageVo);
			}
			model.addAttribute("code", msg);
		}
		//消费信息 --列出码  --看消费记录
		List<Code> codes =  codeService.findOrderList(orders.getId());
		List<OrdersConsume> ordersConsumes = new ArrayList<OrdersConsume>();
		if(supplierProduct.getIsRealname() == 1){
			//实名制
			List<OrdersCardConsumeVo> ordersCardConsumeVos = new ArrayList<OrdersCardConsumeVo>();
			for(Code c :codes){
				ordersConsumes = ordersConsumeService.getCodeId(c.getId());
				if(!BaseUtil.isEmpty(ordersConsumes)){
					OrdersCardConsumeVo vo = new OrdersCardConsumeVo(c.getCardPwd(),ordersConsumes.get(0).getPrinttimName(),ordersConsumes.get(0).getPrintnum());
					ordersCardConsumeVos.add(vo);
				}else{
					OrdersCardConsumeVo vo = new OrdersCardConsumeVo(c.getCardPwd(),"------",0);
					ordersCardConsumeVos.add(vo);
				}
			}
			model.addAttribute("consumeType", 1) ;
			model.addAttribute("consume", ordersCardConsumeVos) ;
		}else{
			//非实名制
			for(Code c :codes){
				ordersConsumes = ordersConsumeService.getCodeId(c.getId());
				if(BaseUtil.isEmpty(ordersConsumes)){
					OrdersConsume consume = new OrdersConsume();
					consume.setPrintnum(0);
					consume.setPrinttimName("------");
					ordersConsumes.add(consume);
				}
			}
			model.addAttribute("consumeType", 2) ;
			model.addAttribute("consumeCode", codes.get(0).getCardPwd()) ;
			model.addAttribute("consume", ordersConsumes) ;
		}


		return "/orders/orders/other/card";
	}

}