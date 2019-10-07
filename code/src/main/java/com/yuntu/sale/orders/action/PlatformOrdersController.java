package com.yuntu.sale.orders.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
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
import com.yuntu.sale.user.service.InfoEnterpriseService;
import net.sf.json.JSONArray;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/platform/my")
public class PlatformOrdersController extends BaseAction {

	@Resource
	private SupplierProductService supplierProductService;

	@Resource
	private ScenicService scenicService;

	@Resource
	private EnterpriseProductService enterpriseProductService;

	@Resource
	private OrdersService ordersService;

	@Resource
	private EnterpriseOrdersRefundService enterpriseOrdersRefundService;

	@Resource
	private CodeService codeService;

	@Resource
	private OrdersRefundService ordersRefundService;

	@Resource
	private OrdersConsumeService ordersConsumeService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/platform/orders/my/main";
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/orders/platform/orders/my/list";
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo,String productName,String starttime,String endtime,Integer state) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<Orders> pager = new Page<Orders>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<Orders> page = ordersService.getList(pager,null,null,productNo,productName,starttime,endtime,state);

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
		Orders orders = ordersService.get(id) ;
		model.addAttribute("orders", orders) ;

		SupplierProduct supplierProduct = supplierProductService.getById(orders.getSproductId());
		model.addAttribute("product", supplierProduct) ;

		InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;

		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(orders.getSproductId(),null,orders.getEnterpriseId());
		String statusName = "";
		Integer type  = 2;
		List<OrdersMyEnterpriseCardVo> ordersMyEnterpriseCardVos = new ArrayList<OrdersMyEnterpriseCardVo>();
		List<EnterpriseOrdersRefund> enterpriseOrdersRefund = enterpriseOrdersRefundService.getorder(orders.getId(),orders.getEnterpriseId(),enterpriseProduct.getParentId());
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
			List<Code> code = codeService.findOrderList(orders.getId());
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


		return "/orders/platform/orders/my/card";
	}
}