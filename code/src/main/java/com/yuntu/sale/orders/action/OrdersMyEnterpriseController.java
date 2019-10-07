package com.yuntu.sale.orders.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.po.EnterpriseOrdersRefund;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersConsume;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.orders.service.CodeProvideService;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.EnterpriseOrdersRefundService;
import com.yuntu.sale.orders.service.EnterpriseOrdersService;
import com.yuntu.sale.orders.service.OrdersConsumeService;
import com.yuntu.sale.orders.service.OrdersRefundService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.OrderRefundAddCardVo;
import com.yuntu.sale.orders.vo.OrdersCardConsumeVo;
import com.yuntu.sale.orders.vo.OrdersMyEnterpriseCardVo;
import com.yuntu.sale.orders.vo.OrdersRefundVo;
import com.yuntu.sale.orders.vo.RealCustomerMessageVo;
import com.yuntu.sale.orders.vo.RealCustomerVo;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;

import net.sf.json.JSONArray;

/**
 * 订单主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/my")
public class OrdersMyEnterpriseController extends BaseAction {

	@Resource
	private SupplierProductService supplierProductService;

	@Resource
	private ProductCategoryService productCategoryService;

	@Resource
	private AreaService areaService;

	@Resource
	private ScenicService scenicService;

	@Resource
	private EnterpriseCapitalService enterpriseCapitalService;

	@Resource
	private EnterpriseProductService enterpriseProductService;

	@Resource
	private EnterpriseStorageMoneyService enterpriseStorageMoneyService;

	@Resource
	private CodeProvideService codeProvideService;

	@Resource
	private InfoEnterpriseService infoEnterpriseService;

	@Resource
	private OrdersService ordersService;

	@Resource
	private EnterpriseOrdersService enterpriseOrdersService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;

	@Resource
	private CodeService codeService;

	@Resource
	private OrdersRefundService ordersRefundService;

	@Resource
	private EnterpriseOrdersRefundService enterpriseOrdersRefundService;

	@Resource
	private OrdersConsumeService ordersConsumeService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/orders/my/main";
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("enterId", super.getCurrentUser().getEnterpriseId()) ;
		model.addAttribute("userId", super.getCurrentUser().getId()) ;
		return "/orders/orders/my/list";
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
		Page<EnterpriseOrders> page = ordersService.getOrdersList(pager,super.getCurrentUser().getEnterpriseId(),null,
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

		return "/orders/orders/my/card";
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

		// 根据订单退款方式，判断可退数量
		SupplierProduct product = supplierProductService.getById(enterpriseOrders.getSproductId());
		model.addAttribute("refundMethod", product.getRefundMethod()) ;
		if(product.getRefundMethod() == 1) {
			model.addAttribute("num", orders.getNum()) ;
		} else if(product.getRefundMethod() == 2) {
			model.addAttribute("num", orders.getNum() - orders.getPrintnum() - orders.getLocknum() - orders.getClocknum()) ;
		} else {
			model.addAttribute("num", orders.getNum()) ;
		}

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
	 *  退款
	 *  检查  退款
	 *   1 待审核 2 审核
	 *  检查“商品是否可退款  1 随时退 2 .不可退
	 检查“退款数量”可退款数量
	 检查“商品退款是否需要审核  1 随时退 2.要审核
	 商品退款手续扣费 1. 2. 3.
	 6.向“主订单表”（t_orders）插入数据
	 6.向“主订单表”（t_code）插入数据
	 6.向“主订单表”（t_enterprise_orders）插入数据
	 6.向“主订单表”（t_enterprise_orders_refund）插入数据
	 7.向“订单子表”（t_orders_refund）插入数据

	 8.库存
	 11.调用第三方接口（暂不实现）
	 *****************************************************************************************
	 orders - code - t_enterprise_orders - t_enterprise_capital - t_enterprise_account_log
	 									- t_enterprise_storage_money - t_enterprise_storage_log - t_enterprise_account_log
			 - code - t_enterprise_orders
			 - code

	 t_orders_refund	 - t_enterprise_orders_refund

	 t_supplier_product
	 *****************************************************************************************
	 *  退款资金问题
	 *  订单 当前企业 扣钱
	 *  供应商 向下级付款 下级收到钱 在向下级付款 少到多
	 *  **************************************************************************************
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, OrdersRefundVo entity,String[] box) {
		//定义
		//Long myEnterpriceId = super.getCurrentUser().getEnterpriseId();                           //当前 企业
		//InfoEnterprise infoEnterprise = infoEnterpriseService.getById(myEnterpriceId);            //当前 企业
		Orders orders = ordersService.get(entity.getId());
		Long myEnterpriceId = orders.getEnterpriseId(); //订单信息
		InfoEnterprise infoEnterprise = infoEnterpriseService.getById(myEnterpriceId);
		List<Code> codeList = codeService.findOrderList(orders.getId());                          //码信息集合
		List<Code> Listcode =new ArrayList<Code>();                          //码信息集合
		Iterator<Code> codeit = codeList.iterator();                                              //码集合迭代
		SupplierProduct product = supplierProductService.getById(orders.getSproductId());         //商品信息

		InfoEnterprise productEnterprise = infoEnterpriseService.getById(product.getEnterpriseId());//商品供应商
		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(product.getId(),null,myEnterpriceId);//商品 ,当前企业关系

		BigDecimal deduct = new BigDecimal(0);                                                //手续费率
		BigDecimal cose = new BigDecimal(0);                                                  //手续费用
		BigDecimal refund = new BigDecimal(0);                                                //实际退款费用

		OrdersRefund ordersRefund = new OrdersRefund();                                            //主退款单
		EnterpriseOrdersRefund enterpriseOrdersRefund ;//  子退款单
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map> mapList = new ArrayList<Map>();

		EnterpriseProduct listEnterpriseProduct ;          //商品  -- 企业 关系

		EnterpriseCapitalPo enterpriseCapitalChild;           //本级平台资金
		EnterpriseCapitalPo enterpriseCapitalPrent;           //上级平台资金

		EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder;   //预收款
		EnterpriseStorageLogPo enterpriseStorageLogOrder;       //预收款日志

		EnterpriseAccountLogPo enterpriseAccountLogOrder;       //资金变动日志
		EnterpriseAccountLogPo enterpriseParentAccountLog;       //我对上级资金变动日志

		EnterpriseOrders enterpriseOrders;                  //订单子表
		List<EnterpriseOrders> enterpriseOrdersList = new ArrayList<EnterpriseOrders>();        //订单子表集合

		BigDecimal before = new BigDecimal(0);              //变动前资金
		BigDecimal price = new BigDecimal(0);               //变动
		BigDecimal after = new BigDecimal(0);               //变动后

		BigDecimal usable = new BigDecimal(0);               // 平台可提现金额

		boolean fig = true;

		//检查商品是否可退
		if(product.getRefundMode() == 2){
			OperatorFailureVo msg = new OperatorFailureVo("该商品不能退款! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}
		//边界检验
		if((orders.getNum() - orders.getLocknum() - orders.getPrintnum() - orders.getClocknum()) < entity.getNum()){
			OperatorFailureVo msg = new OperatorFailureVo("该商品不符合退款条件! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}
		//退款时间判断
		//获取今天时间和订单时间差
		if(!BaseUtil.isEmpty(product.getRefundAfterDay())){
			int shijiancha =  (int)(System.currentTimeMillis()/1000) - orders.getCreatedAt();
			if(product.getRefundTime() == 2 &&  product.getRefundAfterDay() <= (shijiancha/60/60/24)){
				OperatorFailureVo msg = new OperatorFailureVo("该商品已经超过退款限定时间，请与供应商联系！");
				return JSONObject.toJSONString(msg);		
			}
		}

		if(!BaseUtil.isEmpty(product.getRefundAfterDay()) && !BaseUtil.isEmpty(product.getRefundAfterHour()) && !BaseUtil.isEmpty(product.getRefundAfterMinute())){

			long shijiandian = orders.getConsumeTime() - product.getRefundAfterDay()*24*60*60;
			shijiandian += product.getRefundAfterHour()*60*60 + product.getRefundAfterMinute()*60;

			long timenow = System.currentTimeMillis()/1000;

			if(product.getRefundTime() == 3 &&  timenow > shijiandian){
				OperatorFailureVo msg = new OperatorFailureVo("该商品已经超过退款限定时间，请与供应商联系！");
				return JSONObject.toJSONString(msg) ;
			}
		}

		//检查可退款数量
		int refundNum = orders.getNum() - orders.getPrintnum() - orders.getLocknum() - orders.getClocknum() ;
		if(entity.getNum() > refundNum){
			OperatorFailureVo msg = new OperatorFailureVo("最大可退数量 "+refundNum+" ! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}
		//检查上级企业去资金是否充足
		String[] id = enterpriseProduct.getUserTracks().split(",");
		List<String> user = new ArrayList<String>(id.length);
		for (int i = id.length - 1; i >= 0; i--) {
			user.add(id[i]);
		}
		//if(Long.parseLong(user.get(0)) != myEnterpriceId){
		//	OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
		//	return JSONObject.toJSONString(msg) ;
		//}else {
			//迭代分销轨迹
			Iterator<String> uid = user.iterator();
			while (uid.hasNext()) {
				Long listUserId = Long.parseLong(uid.next());
				listEnterpriseProduct = enterpriseProductService.getOne(product.getId(),null,listUserId); //商品 企业关系

				//判断是否是供应商
				if(!(listEnterpriseProduct.getIsSupplier()== 1 )){
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())){
						enterpriseCapitalPrent = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProduct.getParentId());
						enterpriseOrders =  enterpriseOrdersService.getOne(orders.getId(),listUserId,listEnterpriseProduct.getParentId());//我对上级的订单
						enterpriseParentAccountLog = enterpriseAccountLogService.getOrder(listUserId,null,3,enterpriseOrders.getId());//我对上级的订单资金日志

						if (product.getServiceMode() == 1){//无手续费用
							deduct = new BigDecimal(0);
							cose = new BigDecimal(0);
						}
						if (product.getServiceMode() == 2){//按票扣费
							deduct =product.getServiceProductCost() ;
							cose = new BigDecimal(entity.getNum()).multiply(deduct);
						}
						if (product.getServiceMode() == 3){//按单扣费
							deduct =  product.getServiceOrderCost() ;
							cose =  product.getServiceOrderCost() ;
						}
						//隐藏 手续费 比 订单 大 ---
						refund = (new BigDecimal(entity.getNum()).multiply(enterpriseOrders.getFxPrice())).subtract(cose);
						if(refund.compareTo(new BigDecimal(0)) == -1){
							OperatorFailureVo msg = new OperatorFailureVo("该商品退款手续费用超出退款单费用! 请检查!");
							return JSONObject.toJSONString(msg) ;
						}
						if(!BaseUtil.isEmpty(enterpriseParentAccountLog)){
							if(enterpriseParentAccountLog.getCapitalType() == 1){
								if((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(refund ) == -1){
									OperatorFailureVo msg = new OperatorFailureVo("企业资金不足,暂不支持退款! 请联系!");
									return JSONObject.toJSONString(msg) ;
								}
							}
						}else{
							OperatorFailureVo msg = new OperatorFailureVo("尚未付款，不能退款！");
							return JSONObject.toJSONString(msg) ;
						}
					}else{
						OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}
				}else{
					break;
				}
			//}
		}
		//主退款单
		//ordersRefund.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
		ordersRefund.setEnterpriseId(orders.getEnterpriseId());
		ordersRefund.setOrdersId(orders.getId());
		ordersRefund.setSproductId(product.getId());
		ordersRefund.setNum(entity.getNum());
		ordersRefund.setNotes(entity.getNotes());
		ordersRefund.setStatus(0);
		//ordersRefund.setCreateId(super.getCurrentUser().getId());
		ordersRefund.setCreateId(orders.getUser());

		//用户分销轨迹
		String[] ids = enterpriseProduct.getUserTracks().split(",");
		List<String> userIdsList = new ArrayList<String>(ids.length);
		for (int i = ids.length - 1; i >= 0; i--) {
			userIdsList.add(ids[i]);
		}
//		if(Long.parseLong(userIdsList.get(0)) != myEnterpriceId){
//			OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
//			return JSONObject.toJSONString(msg) ;
//		}else{

			//迭代分销轨迹
			Iterator<String> userId = userIdsList.iterator();
			while (userId.hasNext()){
				map = new HashMap<String,Object>();
				Long listUserId = Long.parseLong(userId.next());                                                    //分销当前id
				listEnterpriseProduct = enterpriseProductService.getOne(product.getId(),null,listUserId); //商品 企业关系
				//判断是否是供应商
				if(!(listEnterpriseProduct.getIsSupplier()== 1 )){
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())){

						enterpriseStorageMoneyOrder = enterpriseStorageMoneyService.querySearch(listUserId,listEnterpriseProduct.getParentId());	//预收款

						enterpriseCapitalChild = enterpriseCapitalService.getByEnterpriseId(listUserId);                        //本级平台资金
						enterpriseCapitalPrent = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProduct.getParentId());//上级平台资金

						enterpriseOrders =  enterpriseOrdersService.getOne(orders.getId(),listUserId,listEnterpriseProduct.getParentId());//我对上级的订单
						enterpriseParentAccountLog = enterpriseAccountLogService.getOrder(listUserId,null,3,enterpriseOrders.getId());//我对上级的订单资金日志
						//子退款单
						enterpriseOrdersRefund = new EnterpriseOrdersRefund();
						enterpriseOrdersRefund.setRefundId(ordersRefund.getId());//都相同 ,重写
						enterpriseOrdersRefund.setOrdersId(orders.getId());
						enterpriseOrdersRefund.setEnterpriseOrdersId(enterpriseOrders.getId());
						enterpriseOrdersRefund.setSproductId(product.getId());
						enterpriseOrdersRefund.setChildId(listUserId);
						enterpriseOrdersRefund.setParent(listEnterpriseProduct.getParentId());
						enterpriseOrdersRefund.setNum(entity.getNum());
						enterpriseOrdersRefund.setUnitPrice(enterpriseOrders.getFxPrice());//单价
						enterpriseOrdersRefund.setRefundFeeType(product.getServiceMode());//扣款模式
						if (product.getServiceMode() == 1){//无手续费用
							deduct = new BigDecimal(0);
							cose = new BigDecimal(0);
						}
						if (product.getServiceMode() == 2){//按票扣费
							deduct =product.getServiceProductCost() ;
							cose = new BigDecimal(entity.getNum()).multiply(deduct);
						}
						if (product.getServiceMode() == 3){//按单扣费
							deduct =  product.getServiceOrderCost() ;
							cose =  product.getServiceOrderCost() ;
						}

						refund = (new BigDecimal(entity.getNum()).multiply(enterpriseOrdersRefund.getUnitPrice())).subtract(cose);
						//隐藏 手续费 比 订单 大 ---
						if(refund.compareTo(new BigDecimal(0)) == -1){
							OperatorFailureVo msg = new OperatorFailureVo("该商品退款手续费用超出退款单费用! 请检查!");
							return JSONObject.toJSONString(msg) ;
						}
						enterpriseOrdersRefund.setRefundFee(deduct); //手续费
						enterpriseOrdersRefund.setDeductPrice(cose);//扣除费用
						enterpriseOrdersRefund.setRefundPrice(refund);//实际
						map.put("enterpriseOrdersRefund",enterpriseOrdersRefund);

						//检查“商品退款"是否需要审核
						if(product.getAuditMode() == 1){//不需要审核
							map.put("type",1);
							if(fig){
								ordersRefund.setStatus(1);
								//ordersRefund.setAuditId(super.getCurrentUser().getId());
								ordersRefund.setAuditId(orders.getUser());
								ordersRefund.setAuditTime((int)(System.currentTimeMillis()/1000));
								//订单表
								orders.setLocknum(orders.getLocknum() + entity.getNum());
								if(orders.getPrintnum()== 0 &&orders.getLocknum()>0 &&orders.getLocknum()<orders.getNum()){
									orders.setStatus(11);
								}
								if(orders.getPrintnum()> 0 && orders.getPrintnum() < orders.getNum() && orders.getLocknum()>0 &&orders.getLocknum()<orders.getNum()){
									orders.setStatus(13);
								}
								if(orders.getNum() == orders.getLocknum()){
									orders.setStatus(3);
								}

								//码信息表
								while (codeit.hasNext()){
									Code code =codeit.next();
									if(product.getIsRealname()== 1){
										for(String pk : box){
											if(Long.parseLong(pk) == code.getId()){

												//边界检验
												if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
													OperatorFailureVo msg = new OperatorFailureVo("该商品不符合退款条件! 请检查!");
													return JSONObject.toJSONString(msg) ;
												}

												code.setStatus(orders.getStatus());
												code.setLocknum(1);
												code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
												Listcode.add(code);
											}
										}
									}
									if(product.getIsRealname()== 0){

										//边界检验
										if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < entity.getNum()){
											OperatorFailureVo msg = new OperatorFailureVo("该商品不符合退款条件! 请检查!");
											return JSONObject.toJSONString(msg) ;
										}

										code.setStatus(orders.getStatus());
										code.setLocknum(code.getLocknum()+entity.getNum());
										code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
										Listcode.add(code);
									}
								}
								fig = false;
							}
							enterpriseOrders.setStatus(orders.getStatus());
							enterpriseOrders.setLocknum(enterpriseOrders.getLocknum() +ordersRefund.getNum());
							enterpriseOrdersList.add(enterpriseOrders);

							if(!BaseUtil.isEmpty(enterpriseParentAccountLog)){
								if(enterpriseParentAccountLog.getCapitalType() == 0){
									map.put("action",1);
									//预收款
									before = enterpriseStorageMoneyOrder.getStorageMoney();
									price = refund;
									enterpriseStorageMoneyOrder.setStorageMoney(before.add(refund));
									after = enterpriseStorageMoneyOrder.getStorageMoney();
									// 日志
									enterpriseStorageLogOrder = new EnterpriseStorageLogPo();
									enterpriseStorageLogOrder.setParentId(enterpriseStorageMoneyOrder.getParentId());
									enterpriseStorageLogOrder.setChildId(enterpriseStorageMoneyOrder.getChildId());
									enterpriseStorageLogOrder.setBeforeMoney(before);
									enterpriseStorageLogOrder.setStorageMoney(price);
									enterpriseStorageLogOrder.setAfterMoney(after);
									//enterpriseStorageLogOrder.setCreateId(super.getCurrentUser().getId());
									enterpriseStorageLogOrder.setCreateId(orders.getUser());
									enterpriseStorageLogOrder.setCreateTime(new Date());
									enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
									enterpriseAccountLogOrder.setChildId(listUserId);
									enterpriseAccountLogOrder.setParentId(listEnterpriseProduct.getParentId());
									enterpriseAccountLogOrder.setActionType(4L);
									enterpriseAccountLogOrder.setActionId(enterpriseOrdersRefund.getId());
									enterpriseAccountLogOrder.setCapitalType(0);
									enterpriseAccountLogOrder.setOldPrice(before);
									enterpriseAccountLogOrder.setPrice(price);
									enterpriseAccountLogOrder.setCurrentPrice(after);
									enterpriseAccountLogOrder.setCreateTime(new Date());
									enterpriseAccountLogOrder.setTerminal("PC");
									map.put("enterpriseStorageMoney",enterpriseStorageMoneyOrder);
									map.put("enterpriseStorageMoneylog",enterpriseStorageLogOrder);
									map.put("childCapitalLog",enterpriseAccountLogOrder);
								}else if(enterpriseParentAccountLog.getCapitalType() == 1){
									map.put("action",2);
									//本级平台资金 -- 资金变动 -重写
									before = enterpriseCapitalChild.getTotalMoney();
									price = refund;
									enterpriseCapitalChild.setTotalMoney(before.add(refund));
									after = enterpriseCapitalChild.getTotalMoney();
									enterpriseCapitalChild.setUsableMoney(enterpriseCapitalChild.getUsableMoney().add(refund));
									//enterpriseCapitalChild.setUpdateId(super.getCurrentUser().getId());
									enterpriseCapitalChild.setUpdateId(orders.getUser());
									enterpriseCapitalChild.setUpdateTime(new Date());

									enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
									enterpriseAccountLogOrder.setChildId(listUserId);
									enterpriseAccountLogOrder.setParentId(null);
									enterpriseAccountLogOrder.setActionType(4L);
									enterpriseAccountLogOrder.setActionId(enterpriseOrdersRefund.getId());
									enterpriseAccountLogOrder.setCapitalType(1);
									enterpriseAccountLogOrder.setOldPrice(before);
									enterpriseAccountLogOrder.setPrice(price);
									enterpriseAccountLogOrder.setCurrentPrice(after);
									enterpriseAccountLogOrder.setCreateTime(new Date());
									enterpriseAccountLogOrder.setTerminal("PC");
									map.put("childCapital",enterpriseCapitalChild);
									map.put("childCapitalLog",enterpriseAccountLogOrder);
									//上级平台资金
									if((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(refund ) == 1) {

										before = enterpriseCapitalPrent.getTotalMoney();
										price = refund;
										enterpriseCapitalPrent.setTotalMoney(before.subtract(refund));
										after = enterpriseCapitalPrent.getTotalMoney();
										usable = enterpriseCapitalPrent.getUsableMoney().subtract(refund);
										enterpriseCapitalPrent.setUsableMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);
										//enterpriseCapitalPrent.setUpdateId(super.getCurrentUser().getId());
										enterpriseCapitalPrent.setUpdateId(orders.getUser());
										enterpriseCapitalPrent.setUpdateTime(new Date());

										enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
										enterpriseAccountLogOrder.setChildId(listEnterpriseProduct.getParentId());
										enterpriseAccountLogOrder.setParentId(null);
										enterpriseAccountLogOrder.setActionType(4L);
										enterpriseAccountLogOrder.setActionId(enterpriseOrdersRefund.getId());
										enterpriseAccountLogOrder.setCapitalType(1);
										enterpriseAccountLogOrder.setOldPrice(before);
										enterpriseAccountLogOrder.setPrice(price.multiply(new BigDecimal(-1)));
										enterpriseAccountLogOrder.setCurrentPrice(after);
										enterpriseAccountLogOrder.setCreateTime(new Date());
										enterpriseAccountLogOrder.setTerminal("PC");
										map.put("parentCapital", enterpriseCapitalPrent);
										map.put("parentCapitalLog", enterpriseAccountLogOrder);
									}else{
										OperatorFailureVo msg = new OperatorFailureVo("企业资金不足,暂不支持退款! 请联系!");
										return JSONObject.toJSONString(msg) ;
									}
								}
							}else{
								OperatorFailureVo msg = new OperatorFailureVo("尚未付款，不能退款！");
								return JSONObject.toJSONString(msg) ;
							}
						}
						if(product.getAuditMode() == 2){//需要审核
							map.put("type",2);
							if(fig){
								ordersRefund.setStatus(0);
								//订单表
								orders.setClocknum(orders.getClocknum() + entity.getNum());
								if(orders.getPrintnum()== 0 && orders.getPrintnum()<orders.getNum()){
									orders.setStatus(14);
								}
								if(orders.getPrintnum()> 0 && orders.getPrintnum() < orders.getNum()){
									orders.setStatus(15);
								}
								//码信息表
								while (codeit.hasNext()){
									Code code =codeit.next();
									if(product.getIsRealname()== 1){
										for(String pk : box){
											if(Long.parseLong(pk) == code.getId()){

												//边界检验
												if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
													OperatorFailureVo msg = new OperatorFailureVo("该商品不符合退款条件! 请检查!");
													return JSONObject.toJSONString(msg) ;
												}
												code.setStatus(orders.getStatus());
												code.setClocknum(1);
												code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
												Listcode.add(code);
											}
										}
									}
									if(product.getIsRealname()== 0){

										//边界检验
										if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < entity.getNum()){
											OperatorFailureVo msg = new OperatorFailureVo("该商品不符合退款条件! 请检查!");
											return JSONObject.toJSONString(msg) ;
										}

										code.setStatus(orders.getStatus());
										code.setClocknum(code.getClocknum()+entity.getNum());
										code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
										Listcode.add(code);
									}
								}
								fig = false;
							}
							enterpriseOrders.setStatus(orders.getStatus());
							enterpriseOrders.setClocknum(enterpriseOrders.getClocknum() + entity.getNum());
							enterpriseOrdersList.add(enterpriseOrders);

							if(enterpriseParentAccountLog.getCapitalType() == 0){
								map.put("action",1);
								//预收款
							}else if(enterpriseParentAccountLog.getCapitalType() == 1){
								map.put("action",2);
								//本级平台资金

								//上级平台资金
								enterpriseCapitalPrent.setUsableMoney(enterpriseCapitalPrent.getUsableMoney().subtract(refund));
								enterpriseCapitalPrent.setForzenMoney(enterpriseCapitalPrent.getForzenMoney().add(refund));
								map.put("parentCapital",enterpriseCapitalPrent);
							}

						}
						mapList.add(map);
					}else{
						OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}
				}else{
					break;
				}
			//}
		}

			//库存
			if(product.getStoreMode() == 2 && !BaseUtil.isEmpty(product.getStoreNum())){
				product.setStoreNum(product.getStoreNum() + entity.getNum());
				//product.setUpdateId(super.getCurrentUser().getId());
				product.setUpdateId(orders.getUser());
				product.setUpdateTime(new Date());
			}

		/**
		 * ordersRefund
		 * orders
		 * Listcode
		 * enterpriseOrdersList
		 * mapList
		 *         Map
		 *              enterpriseOrdersRefund,enterpriseOrdersRefund
		 *              不审核
		 *                    type,1
		 *                    预收款
		 *                           action, 1
		 *                           enterpriseStorageMoney,enterpriseStorageMoneyOrder
		 *                           enterpriseStorageMoneylog,enterpriseStorageLogOrder
		 *                    平台资金
		 *                            action, 2
		 *                            childCapital,enterpriseCapitalChild
		 *                             parentCapital,enterpriseCapitalPrent
		 *                              parentCapitalLog,enterpriseAccountLogOrder
		 *                     childCapitalLog,enterpriseAccountLogOrder
		 *                     商品 ^-\
		 *              审核
		 *                    type,2
		 *                    预收款
		 *                             action, 1
		 *                    平台资金
		 *                             action, 2
		 *                    parentCapital,enterpriseCapitalPrent
		 * product _/^
		 */
		ordersRefundService.save(ordersRefund,orders,Listcode,enterpriseOrdersList,mapList,product);
		OperatorSuccessVo message = new OperatorSuccessVo("订单退款申请成功！") ;
		return JSONObject.toJSONString(message) ;
	}

}