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
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.orders.service.CodeProvideService;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.EnterpriseOrdersRefundService;
import com.yuntu.sale.orders.service.EnterpriseOrdersService;
import com.yuntu.sale.orders.service.OrdersRefundService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.EnterpriseOrdersRefundVo;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * 退款主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/refund/other")
public class OrdersRefundOtherEnterpriseController extends BaseAction {

	@Resource
	private OrdersRefundService ordersRefundService;

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
	private EnterpriseOrdersRefundService enterpriseOrdersRefundService;

	@Resource
	private InfoUserService infoUserService;


	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/refund/other/main";
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("id", super.getCurrentUser().getEnterpriseId()) ;
		return "/orders/refund/other/list";
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value = "/getSearch", method = RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo, String productName, String starttime, String endtime, Integer state) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseOrdersRefundVo> pager = new Page<EnterpriseOrdersRefundVo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<EnterpriseOrdersRefundVo> page = ordersRefundService.getOrdersList(pager, null,super.getCurrentUser().getEnterpriseId(), productNo, productName, starttime, endtime, state);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 详细页
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String getcard(HttpServletRequest request, @PathVariable Long id, Model model) {

		EnterpriseOrdersRefund enterpriseOrders = enterpriseOrdersRefundService.get(id);
		model.addAttribute("refund", enterpriseOrders) ;

		OrdersRefund ordersRefund = ordersRefundService.get(enterpriseOrders.getRefundId());
		model.addAttribute("fund", ordersRefund) ;

		Orders orders = ordersService.get(enterpriseOrders.getOrdersId()) ;
		model.addAttribute("order", orders) ;

		SupplierProduct supplierProduct = supplierProductService.getById(enterpriseOrders.getSproductId());
		model.addAttribute("product", supplierProduct) ;

		InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;

		InfoUser infoUser = infoUserService.getById(ordersRefund.getCreateId());
		model.addAttribute("cname", infoUser) ;

		InfoUser iUser = infoUserService.getById(ordersRefund.getAuditId());
		model.addAttribute("aname", iUser) ;
		return "/orders/refund/other/card" ;
	}

	/**
	 * Audi
	 * 审核页
	 */
	@RequestMapping(value = "/{id}/toSet", method = RequestMethod.GET)
	public String toAudi(HttpServletRequest request, @PathVariable Long id, Model model) {
		EnterpriseOrdersRefund enterpriseOrders = enterpriseOrdersRefundService.get(id);
		model.addAttribute("refund", enterpriseOrders) ;

		OrdersRefund ordersRefund = ordersRefundService.get(enterpriseOrders.getRefundId());
		model.addAttribute("fund", ordersRefund) ;

		Orders orders = ordersService.get(enterpriseOrders.getOrdersId()) ;
		model.addAttribute("order", orders) ;

		SupplierProduct supplierProduct = supplierProductService.getById(enterpriseOrders.getSproductId());
		model.addAttribute("product", supplierProduct) ;

		InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;

		InfoUser infoUser = ordersRefund.getCreateId() != null ? infoUserService.getById(ordersRefund.getCreateId()) : new InfoUser();
		model.addAttribute("cname", infoUser) ;

		InfoUser iUser =ordersRefund.getAuditId() != null ? infoUserService.getById(ordersRefund.getAuditId()) : new InfoUser();
		model.addAttribute("aname", iUser) ;
		return "/orders/refund/other/add";
	}

	/**
	 * 退款单审核操作 id 退款单id,notes退款原因
	 * 商品供应商 才能退款
	 */
	@RequestMapping(value = "/{id}/{status}/setAudi", method = RequestMethod.POST)
	@ResponseBody
	public String setAudi(HttpServletRequest request, @PathVariable Long id, @PathVariable Long status) {
		//定义
		Long myEnterpriseId = super.getCurrentUser().getEnterpriseId();
		EnterpriseOrdersRefund enterpriseOrdersRefund = enterpriseOrdersRefundService.get(id);
		OrdersRefund ordersRefund = ordersRefundService.get(enterpriseOrdersRefund.getRefundId());
		Orders orders = ordersService.get(enterpriseOrdersRefund.getOrdersId());
		List<Code> codeList = codeService.findOrderList(orders.getId());                          //码信息集合
		List<Code> Listcode = new ArrayList<Code>();                          //码信息集合
		Iterator<Code> codeit = codeList.iterator();
		SupplierProduct product = supplierProductService.getById(enterpriseOrdersRefund.getSproductId());
		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(product.getId(), null, ordersRefund.getEnterpriseId());

		EnterpriseProduct listEnterpriseProduct; //商品 企业关系
		EnterpriseCapitalPo enterpriseCapitalPrent;//上级资金平台
		EnterpriseCapitalPo enterpriseCapitalChild;//下级资金平台
		EnterpriseOrders enterpriseOrders;         //我对上级的订单
		EnterpriseOrdersRefund myenterpriseOrdersRefund;         //我对上级的退款单单
		List<EnterpriseOrders> enterpriseOrdersList = new ArrayList<EnterpriseOrders>();        //订单子表集合
		BigDecimal deduct = new BigDecimal(0);                                                //手续费率
		BigDecimal cose = new BigDecimal(0);                                                  //手续费用
		BigDecimal refund = new BigDecimal(0);                                                //实际退款费用
		EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder;   //预收款
		EnterpriseStorageLogPo enterpriseStorageLogOrder;       //预收款日志
		EnterpriseAccountLogPo enterpriseAccountLogOrder;       //资金变动日志
		EnterpriseAccountLogPo enterpriseParentAccountLog;       //我对上级资金变动日志

		BigDecimal before = new BigDecimal(0);              //变动前资金
		BigDecimal price = new BigDecimal(0);               //变动
		BigDecimal after = new BigDecimal(0);               //变动后
		BigDecimal usable = new BigDecimal(0);               // 平台可提现金额

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map> mapList = new ArrayList<Map>();

		//检查
		if (product.getEnterpriseId() != myEnterpriseId) {
			OperatorFailureVo msg = new OperatorFailureVo("您没有审核商品退款审核权限! 请检查!");
			return JSONObject.toJSONString(msg);
		}
		//检查企业资金是否充足
		String[] trckIds = enterpriseProduct.getUserTracks().split(",");
		List<String> userId = new ArrayList<String>(trckIds.length);
		for (int i = trckIds.length - 1; i >= 0; i--) {
			userId.add(trckIds[i]);
		}
		if (Long.parseLong(userId.get(userId.size() - 1)) != myEnterpriseId) {
			OperatorFailureVo msg = new OperatorFailureVo("您没有审核商品退款审核权限! 请检查!");
			return JSONObject.toJSONString(msg);
		} else {
			//迭代分销轨迹
			Iterator<String> uid = userId.iterator();
			while (uid.hasNext()) {
				Long listUserId = Long.parseLong(uid.next());
				listEnterpriseProduct = enterpriseProductService.getOne(product.getId(), null, listUserId); //商品 企业关系

				//判断是否是供应商
				if (!(listEnterpriseProduct.getIsSupplier() == 1 )) {
					//是否有上级企业
					if (!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())) {
						enterpriseCapitalPrent = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProduct.getParentId());
						myenterpriseOrdersRefund = enterpriseOrdersRefundService.getOne(ordersRefund.getId(), listUserId, listEnterpriseProduct.getParentId());//我对上级的退款单

						enterpriseOrders =  enterpriseOrdersService.getOne(orders.getId(),listUserId,listEnterpriseProduct.getParentId());//我对上级的订单
						enterpriseParentAccountLog = enterpriseAccountLogService.getOrder(listUserId,null,3,enterpriseOrders.getId());//我对上级的订单资金日志

						if(enterpriseParentAccountLog.getCapitalType() == 1){
							if ((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(myenterpriseOrdersRefund.getRefundPrice()) == -1) {
								OperatorFailureVo msg = new OperatorFailureVo("交易链资金不足,暂不支持退款! 请联系!");
								return JSONObject.toJSONString(msg);
							}
						}

					} else {
						OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
						return JSONObject.toJSONString(msg);
					}
				} else {
					break;
				}
			}
		}
		//审核通过
		if (status == 1) {
			//退款单修正
			ordersRefund.setStatus(1);
			ordersRefund.setAuditId(super.getCurrentUser().getId());
			ordersRefund.setAuditTime((int) (System.currentTimeMillis() / 1000));
			//订单表
			orders.setLocknum(orders.getLocknum() + ordersRefund.getNum());
			orders.setClocknum(orders.getClocknum() - ordersRefund.getNum());
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
			while (codeit.hasNext()) {
				Code code = codeit.next();
				if(product.getIsRealname()== 1){
						if(code.getClocknum() == 1){
							code.setStatus(orders.getStatus());
							code.setClocknum(0);
							code.setLocknum(1);
							code.setUpdatedAt((int) (System.currentTimeMillis() / 1000));
							Listcode.add(code);
						}
				}
				if(product.getIsRealname()== 0){
					code.setStatus(orders.getStatus());
					code.setClocknum(code.getClocknum() - ordersRefund.getNum());
					code.setLocknum(code.getLocknum() + ordersRefund.getNum());
					code.setUpdatedAt((int) (System.currentTimeMillis() / 1000));
					Listcode.add(code);
				}
			}

			//用户分销轨迹
			String[] ids = enterpriseProduct.getUserTracks().split(",");
			List<String> userIdsList = new ArrayList<String>(ids.length);
			for (int i = ids.length - 1; i >= 0; i--) {
				userIdsList.add(ids[i]);
			}
			if (Long.parseLong(userIdsList.get(userIdsList.size() - 1)) != myEnterpriseId) {
				OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品审核权限! 请检查!");
				return JSONObject.toJSONString(msg);
			} else {

				//迭代分销轨迹
				Iterator<String> useId = userIdsList.iterator();
				while (useId.hasNext()) {
					map = new HashMap<String, Object>();
					Long listUserId = Long.parseLong(useId.next());                                                    //分销当前id
					listEnterpriseProduct = enterpriseProductService.getOne(product.getId(), null, listUserId); //商品 企业关系
					//判断是否是供应商
					if (!(listEnterpriseProduct.getIsSupplier() == 1 )) {
						//是否有上级企业
						if (!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())) {

							enterpriseStorageMoneyOrder = enterpriseStorageMoneyService.querySearch(listUserId, listEnterpriseProduct.getParentId());    //预收款

							enterpriseCapitalChild = enterpriseCapitalService.getByEnterpriseId(listUserId);                        //本级平台资金
							enterpriseCapitalPrent = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProduct.getParentId());//上级平台资金

							myenterpriseOrdersRefund = enterpriseOrdersRefundService.getOne(ordersRefund.getId(), listUserId, listEnterpriseProduct.getParentId());//我对上级的退款单
							enterpriseOrders = enterpriseOrdersService.getOne(orders.getId(), listUserId, listEnterpriseProduct.getParentId());//我对上级的订单
							enterpriseParentAccountLog = enterpriseAccountLogService.getOrder(listUserId, null, 3, enterpriseOrders.getId());//我对上级的订单资金日志

							//子订单修正
							enterpriseOrders.setLocknum(enterpriseOrders.getLocknum() + ordersRefund.getNum());
							enterpriseOrders.setClocknum(enterpriseOrders.getClocknum() - ordersRefund.getNum());
							enterpriseOrders.setStatus(orders.getStatus());
							enterpriseOrdersList.add(enterpriseOrders);
							//退款金额
							refund = myenterpriseOrdersRefund.getRefundPrice();
							if (enterpriseParentAccountLog.getCapitalType() == 0) {
								map.put("type", 1);
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
								enterpriseStorageLogOrder.setCreateId(super.getCurrentUser().getId());
								enterpriseStorageLogOrder.setCreateTime(new Date());

								enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
								enterpriseAccountLogOrder.setChildId(listUserId);
								enterpriseAccountLogOrder.setParentId(listEnterpriseProduct.getParentId());
								enterpriseAccountLogOrder.setActionType(4L);
								enterpriseAccountLogOrder.setActionId(myenterpriseOrdersRefund.getId());
								enterpriseAccountLogOrder.setCapitalType(0);
								enterpriseAccountLogOrder.setOldPrice(before);
								enterpriseAccountLogOrder.setPrice(price);
								enterpriseAccountLogOrder.setCurrentPrice(after);
								enterpriseAccountLogOrder.setCreateTime(new Date());
								enterpriseAccountLogOrder.setTerminal("PC");

								map.put("enterpriseStorageMoney", enterpriseStorageMoneyOrder);
								map.put("enterpriseStorageMoneylog", enterpriseStorageLogOrder);
								map.put("childCapitalLog", enterpriseAccountLogOrder);
							} else if (enterpriseParentAccountLog.getCapitalType() == 1) {
								map.put("type", 2);
								//本级平台资金
								before = enterpriseCapitalChild.getTotalMoney();
								price = refund;
								enterpriseCapitalChild.setTotalMoney(before.add(refund));
								after = enterpriseCapitalChild.getTotalMoney();
								enterpriseCapitalChild.setUsableMoney(enterpriseCapitalChild.getUsableMoney().add(refund));

								enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
								enterpriseAccountLogOrder.setChildId(listUserId);
								enterpriseAccountLogOrder.setParentId(null);
								enterpriseAccountLogOrder.setActionType(4L);
								enterpriseAccountLogOrder.setActionId(myenterpriseOrdersRefund.getId());
								enterpriseAccountLogOrder.setCapitalType(1);
								enterpriseAccountLogOrder.setOldPrice(before);
								enterpriseAccountLogOrder.setPrice(price);
								enterpriseAccountLogOrder.setCurrentPrice(after);
								enterpriseAccountLogOrder.setCreateTime(new Date());
								enterpriseAccountLogOrder.setTerminal("PC");

								map.put("childCapital", enterpriseCapitalChild);
								map.put("childCapitalLog", enterpriseAccountLogOrder);
								//上级
								if((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(refund ) == 1) {
									before = enterpriseCapitalPrent.getTotalMoney();
									price = refund;
									enterpriseCapitalPrent.setTotalMoney(before.subtract(refund));
									after = enterpriseCapitalPrent.getTotalMoney();
									usable = enterpriseCapitalPrent.getForzenMoney().subtract(refund);
									enterpriseCapitalPrent.setForzenMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);

									enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
									enterpriseAccountLogOrder.setChildId(listEnterpriseProduct.getParentId());
									enterpriseAccountLogOrder.setParentId(null);
									enterpriseAccountLogOrder.setActionType(4L);
									enterpriseAccountLogOrder.setActionId(myenterpriseOrdersRefund.getId());
									enterpriseAccountLogOrder.setCapitalType(1);
									enterpriseAccountLogOrder.setOldPrice(before);
									enterpriseAccountLogOrder.setPrice(price.multiply(new BigDecimal(-1)));
									enterpriseAccountLogOrder.setCurrentPrice(after);
									enterpriseAccountLogOrder.setCreateTime(new Date());
									enterpriseAccountLogOrder.setTerminal("PC");
									map.put("enterpriseCapitalPrent", enterpriseCapitalPrent);
									map.put("enterpriseAccountLogOrderParent", enterpriseAccountLogOrder);
								}else{
									OperatorFailureVo msg = new OperatorFailureVo("企业资金不足,暂不支持退款! 请联系!");
									return JSONObject.toJSONString(msg) ;
								}
							}
						} else {
							OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
							return JSONObject.toJSONString(msg);
						}
					} else {
						break;
					}
					mapList.add(map);
				}

			}
			/**
			 * status ,1
			 *        ordersRefund
			 *        orders
			 *        Listcode
			 *        enterpriseOrdersList
			 *        mapList
			 *             map
			 *               type ,1
			 *                   enterpriseStorageMoney,enterpriseStorageMoneyOrder
			 *                   enterpriseStorageMoneylog,enterpriseStorageLogOrder
			 *               type ,2
			 *                   childCapital,enterpriseCapitalChild
			 *                   enterpriseCapitalPrent,enterpriseCapitalPrent
			 *                   enterpriseAccountLogOrderParent,enterpriseAccountLogOrder
			 *               childCapitalLog,enterpriseAccountLogOrder
			 *               product
			 * status ,2
			 *        ordersRefund
			 *        orders
			 *        Listcode
			 *        enterpriseOrdersList
			 *        mapList
			 *            map
			 *               type ,1
			 *               type ,2
			 *                   parentCapital,enterpriseCapitalPrent
			 * product
			 */
			enterpriseOrdersRefundService.save(status,ordersRefund,orders,Listcode,enterpriseOrdersList,mapList,product);
			OperatorSuccessVo message = new OperatorSuccessVo("退款审核设置成功！该退款单:审核通过");
			return JSONObject.toJSONString(message);
		}
		//审核不通过
		if (status == 2) {
			ordersRefund.setStatus(2);
			ordersRefund.setAuditId(super.getCurrentUser().getId());
			ordersRefund.setAuditTime((int) (System.currentTimeMillis() / 1000));
			//订单表
			orders.setClocknum(orders.getClocknum() - ordersRefund.getNum());
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
			while (codeit.hasNext()) {
				Code code = codeit.next();
				if(product.getIsRealname()== 1){
					if(code.getClocknum() == 1){
						code.setStatus(orders.getStatus());
						code.setClocknum(0);
						code.setUpdatedAt((int) (System.currentTimeMillis() / 1000));
						Listcode.add(code);
					}
				}
				if(product.getIsRealname()== 0){
					code.setStatus(orders.getStatus());
					code.setClocknum(code.getClocknum() - ordersRefund.getNum());
					code.setUpdatedAt((int) (System.currentTimeMillis() / 1000));
					Listcode.add(code);
				}
			}
			//用户分销轨迹
			String[] ids = enterpriseProduct.getUserTracks().split(",");
			List<String> userIdsList = new ArrayList<String>(ids.length);
			for (int i = ids.length - 1; i >= 0; i--) {
				userIdsList.add(ids[i]);
			}
			if (Long.parseLong(userIdsList.get(userIdsList.size() - 1)) != myEnterpriseId) {
				OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品审核权限! 请检查!");
				return JSONObject.toJSONString(msg);
			} else {

				//迭代分销轨迹
				Iterator<String> useId = userIdsList.iterator();
				while (useId.hasNext()) {
					map = new HashMap<String, Object>();
					Long listUserId = Long.parseLong(useId.next());                                                    //分销当前id
					listEnterpriseProduct = enterpriseProductService.getOne(product.getId(), null, listUserId); //商品 企业关系
					//判断是否是供应商
					if (!(listEnterpriseProduct.getIsSupplier() == 1 )) {
						//是否有上级企业
						if (!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())) {
							enterpriseCapitalPrent = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProduct.getParentId());//上级平台资金
							enterpriseOrders = enterpriseOrdersService.getOne(orders.getId(), listUserId, listEnterpriseProduct.getParentId());//我对上级的订单
							enterpriseParentAccountLog = enterpriseAccountLogService.getOrder(listUserId, null, 3, enterpriseOrders.getId());//我对上级的订单资金日志
							myenterpriseOrdersRefund = enterpriseOrdersRefundService.getOne(ordersRefund.getId(), listUserId, listEnterpriseProduct.getParentId());//我对上级的退款单
							refund = myenterpriseOrdersRefund.getRefundPrice();
							//子订单修正
							enterpriseOrders.setClocknum(enterpriseOrders.getClocknum() - ordersRefund.getNum());
							enterpriseOrders.setStatus(orders.getStatus());
							enterpriseOrdersList.add(enterpriseOrders);
							if (enterpriseParentAccountLog.getCapitalType() == 0) {
								map.put("type",1);
								//预收款
							} else if (enterpriseParentAccountLog.getCapitalType() == 1) {
								map.put("type",2);
								enterpriseCapitalPrent.setUsableMoney(enterpriseCapitalPrent.getUsableMoney().add(refund));
								enterpriseCapitalPrent.setForzenMoney(enterpriseCapitalPrent.getForzenMoney().subtract(refund));
								map.put("parentCapital", enterpriseCapitalPrent);
							}
						} else {
							OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
							return JSONObject.toJSONString(msg);
						}
					} else {
						break;
					}
					mapList.add(map);
				}
			}
			//库存
			if (product.getStoreMode() == 2) {
				product.setStoreNum(product.getStoreNum() - ordersRefund.getNum());
				product.setUpdateId(super.getCurrentUser().getId());
				product.setUpdateTime(new Date());
			}
			/**
			 * status ,1
			 *        ordersRefund
			 *        orders
			 *        Listcode
			 *        enterpriseOrdersList
			 *        mapList
			 *             map
			 *               type ,1
			 *                   enterpriseStorageMoney,enterpriseStorageMoneyOrder
			 *                   enterpriseStorageMoneylog,enterpriseStorageLogOrder
			 *               type ,2
			 *                   childCapital,enterpriseCapitalChild
			 *                   enterpriseCapitalPrent,enterpriseCapitalPrent
			 *                   enterpriseAccountLogOrderParent,enterpriseAccountLogOrder
			 *               childCapitalLog,enterpriseAccountLogOrder
			 *               product
			 * status ,2
			 *        ordersRefund
			 *        orders
			 *        Listcode
			 *        enterpriseOrdersList
			 *        mapList
			 *            map
			 *               type ,1
			 *               type ,2
			 *                   parentCapital,enterpriseCapitalPrent
			 * product
			 */
			enterpriseOrdersRefundService.save(status,ordersRefund,orders,Listcode,enterpriseOrdersList,mapList,product);
			OperatorSuccessVo message = new OperatorSuccessVo("退款审核设置成功！该退款单:审核不通过");
			return JSONObject.toJSONString(message);
		}
		return JSONObject.toJSONString("");
	}
}
