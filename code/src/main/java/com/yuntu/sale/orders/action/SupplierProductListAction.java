package com.yuntu.sale.orders.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import com.yuntu.sale.ifconf.service.RequestSupplierSysService;
import com.yuntu.sale.ifconf.vo.OrderResutlVo;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.orders.service.*;
import com.yuntu.sale.orders.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.GenerateNo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;
import com.yuntu.sale.common.SmsManager;
import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.AreaService;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.ProductCategory;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.CalendarPriceService;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;

import net.sf.json.JSONArray;

/**
 * @Description 商品列表
 * @author zy
 * @date 2018年4月24日 下午3:11:08
 */
@Controller
@RequestMapping(value = "/orders/product")
public class SupplierProductListAction extends BaseAction {

	private final static Logger logger = LoggerFactory.getLogger(SupplierProductListAction.class);

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
	private CalendarPriceService calendarPriceService;

	@Resource
	private EnterprisePlatformInterfaceService enterprisePlatformInterfaceService;

	@Resource
	private RequestSupplierSysService requestSupplierSysService;

	@Resource
	private CodeService codeService;

	@Resource
	private OrdersRefundService ordersRefundService;

	@Resource
	private EnterpriseOrdersService enterpriseOrdersService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/orders/product/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("eid", super.getCurrentUser().getEnterpriseId()) ;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat
				("yyyy-MM-dd");
		model.addAttribute("time", bartDateFormat.format( new Date())) ;
		return "/orders/product/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/toSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo, String productName) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<SupplierProduct> pager = new Page<SupplierProduct>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<SupplierProduct> page = supplierProductService.getShopProduct(pager, super.getCurrentUser().getEnterpriseId(),productNo,productName);

		// 遍历数据,处理当日价格显示
		if(!BaseUtil.isEmpty(page.getResult())) {
			for(SupplierProduct product : page.getResult()) {
				ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),bartDateFormat.format(new Date()),super.getCurrentUser().getEnterpriseId());
				if(productPriceVo.getType()){
					product.setTodayPrice("￥"+productPriceVo.getData().toString());
				} else{
					product.setTodayPrice("--");
				}
			}
		}

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
		// 商品
		SupplierProduct product = supplierProductService.getById(id) ;
		model.addAttribute("entity", product) ;
		//第三方接口
		List<EnterprisePlatformInterfacePo> list = enterprisePlatformInterfaceService.listOfAvailableGys(product.getEnterpriseId());
		model.addAttribute("thirdplatform", list) ;
		// 商品类别
		List<ProductCategory> entity = productCategoryService.getAll();
		model.addAttribute("ctategory", entity) ;
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(product.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;
		// 查询省信息
		AreaPo lstProvince = areaService.getById(infoScenicPo.getProvinceId());
		model.addAttribute("provinces", lstProvince) ;
		// 查询市信息
		AreaPo lstCitys = areaService.getById(infoScenicPo.getCityId());
		model.addAttribute("city", lstCitys) ;
		return "/orders/product/card" ;
	}

	/**
	 * 购买页
	 */
	@RequestMapping(value = "/{id}/toShop", method = RequestMethod.GET)
	public String toShop(HttpServletRequest request, @PathVariable Long id, Model model) {
		SupplierProduct product = supplierProductService.getById(id) ;
		model.addAttribute("product", product) ;
		model.addAttribute("eid", super.getCurrentUser().getEnterpriseId()) ;
		return "/orders/product/add" ;
	}

	/**
	 *获取日历价格
	 */
	@RequestMapping(value = "/{id}/{time}/getPrice", method = RequestMethod.GET)
	@ResponseBody
	public String getPrice(HttpServletRequest request, @PathVariable Long id,@PathVariable String time, Model model) {

		ProductPriceVo productPriceVo = this.getProductPrice(id,time,super.getCurrentUser().getEnterpriseId());
		OperatorSuccessVo msg;
		if(!productPriceVo.getType()){
			msg = new OperatorSuccessVo("");
		}else{
			msg = new OperatorSuccessVo(productPriceVo.getData());
		}
		return JSONObject.toJSONString(msg) ;
	}

	/**
	 * 查看价格页
	 */
	@RequestMapping(value = "/toPrice", method = RequestMethod.GET)
	public String toPrice(Long sproductId,Model model) {
		//产品
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		return "/orders/product/price" ;
	}

	/**
	 * 下订单
	 检查   取码  下单   改库存
	 1.检查“商品状态”	（是否可用）
	 2.检查“商品库存”	（商品库存 对比 购买数量）
	 3.检查“商品有效期”	（商品有效期 对比 预计游玩日期）
	 4.检查“用户余额”	（分别检查自下向上购买线路上用户的余额是否够用，“预收款”或“平台余额”有一个够用可以）
	 5.修改“码库表”（下单过程描述）中数据：从此表中取一个未使用的电子码，修改其状态为：1（已使用）
		 （1）SELECT id, card_pwd FROM t_code_provide WHERE status = 5 LIMIT 1 FOR UPDATE
		 （2）UPDATE t_code_provide SET status = 1 WHERE id = ${Id}
	 6.向“主订单表”（t_orders）插入数据
	 7.向“订单子表-企业订单表”（t_enterprise_orders）插入数据
	 8.向“变更企业资金余额和记录”，相关数据表：
		 t_enterprise_storage_money 或 t_enterprise_captial  （下单扣款，从“预收款表”或“平台资金表”中选择一个，不会同时扣款）
		 t_enterprise_account_log
	 9.向“订单-码信息表”（t_code）插入数据
	 10.改库存
	 11.调用第三方接口（暂不实现）
	 *****************************************************************************************
	  orders - code - t_enterprise_orders - t_enterprise_capital - t_enterprise_account_log
	                                      - t_enterprise_storage_money - t_enterprise_storage_log - t_enterprise_account_log
			 - code - t_enterprise_orders
			 - code
	 t_supplier_product
	 *****************************************************************************************
	 注意：
	 （1）为方便理解，将上述过程加“空行”进行区分，分别为：
	 检查：	1，2，3，4
	 取电子码：5
	 下单（包括资金变动）：6，7，8，9
	 改库存10
	 调用第三方变接口：11
	 （2）下单action调用service方法时，“取电子码”方法和“下单”方法分两个方法调用。
	 原因：取电子码的时候对数据加了锁，如一起提交事务效率太差。
	 备注：<1> 这个加锁的方法也不好，以后再改。现在加的方式和写法都不好。
	 <2> 步骤11，调用第三方接口先不要管。
	 *****************************************************************************************
	 *  资金问题
	 *  订单 当前企业 加钱
	 *  下级向上级付款 上级收到 在向上级付款 多 到 少
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, ProductOrdersVo entity,String[] consumerName,String[] consumerCard ) {

		SupplierProduct product = supplierProductService.getById(entity.getSproductId()) ;//购买商品的商品信息

		//判断是不是第三方商品
		if(!BaseUtil.isEmpty(product.getThirdPlatformId()) && product.getThirdPlatformId() > 0){
			//走向第三方下单流程
			return thirdProductAdd(entity, consumerName, consumerCard, product);
		}else{
			//走系统内下单流程
			Long myEnterpriceId = super.getCurrentUser().getEnterpriseId();
			InfoEnterprise infoEnterprise = infoEnterpriseService.getById(myEnterpriceId);


			InfoEnterprise productEnterprise = infoEnterpriseService.getById(product.getEnterpriseId());//商品供应商
			EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(entity.getSproductId(),null,myEnterpriceId);//商品 ,当前企业关系
			Date befor = null;                                        //验票前时
			Date aftor = null;                                        //验票结束时间
			RealCustomerVo realCustomerVo;
			String json;
			List<RealCustomerMessageVo> customerVoList = new ArrayList<RealCustomerMessageVo>(); //实名制 短信
		/*
		 *检查：	1，2，3，4
		 */
			//购买时间线
			/*（1）用户任意时间购买均可使用
				验票时间为：产品有效期,游玩
			（2）用户需提前1小时1分钟购买：			验票时间为：下单时间 + 1小时1分钟
			（3）用户需在游玩N天的N点N分前购买：
				下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/
		/*if(product.getPlayMode() != 1){
			if(entity.getConsumeTime() != product.getPlayDate()){
				OperatorFailureVo msg = new OperatorFailureVo("该商品已指定游玩日期! 请重新填写!");
				return JSONObject.toJSONString(msg) ;
			}
		}
		if(entity.getConsumeTime().getTime() < product.getValidStartDate().getTime() && entity.getConsumeTime().getTime() > product.getValidEndDate().getTime()){
			OperatorFailureVo msg = new OperatorFailureVo("指定日期不在商品有效日期内! 请重新填写!");
			return JSONObject.toJSONString(msg) ;
		}*/

			//检查“商品状态”	（是否可用）
			if(product.getStatus() == 0){
				OperatorFailureVo msg = new OperatorFailureVo("该商品已停用! 禁止下单!");
				return JSONObject.toJSONString(msg) ;
			}
			//用户需在游玩N天的N点N分前购买： 下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/

			if(product.getBuyOption() == 3){

				long consumeTime = entity.getConsumeTime().getTime();//11位
				consumeTime -=  product.getBuyUseDay() * 24 * 60 * 60 * 1000;
				consumeTime += product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000;
				long nowTime = new Date().getTime();
				if(nowTime > consumeTime){
					OperatorFailureVo msg = new OperatorFailureVo("请在游玩前"+product.getBuyUseDay()+"天"+product.getBuyUseHour()+"点"+product.getBuyUseMinute()+"分购买! 禁止下单!");
					return JSONObject.toJSONString(msg) ;
				}
			}

			//购买数量
			if(entity.getNum() < 1){
				OperatorFailureVo msg = new OperatorFailureVo("此产品最少购买1张！");
				return JSONObject.toJSONString(msg) ;
			}
			//检查“商品预订数量限制”	（商品预订数量限制 对比 购买数量）
			if(product.getBuyMinNumber() != null && (entity.getNum() < product.getBuyMinNumber())) {
				OperatorFailureVo msg = new OperatorFailureVo("此产品最少购买 " + product.getBuyMinNumber() + "张！");
				return JSONObject.toJSONString(msg) ;
			}
			if(product.getBuyMaxNumber() != null && (entity.getNum() > product.getBuyMaxNumber())) {
				OperatorFailureVo msg = new OperatorFailureVo("此产品最多购买 " + product.getBuyMaxNumber() + "张！");
				return JSONObject.toJSONString(msg) ;
			}
			//检查“商品库存”	（商品库存 对比 购买数量）
			if(product.getStoreMode() == 2){
				if(entity.getNum().compareTo(product.getStoreNum()) == 1){
					OperatorFailureVo msg = new OperatorFailureVo("该商品库存不足! 请检查!");
					return JSONObject.toJSONString(msg) ;
				}
			}
			//检查商品是否需要输入身份证
			if(product.getIsMustCard() == 1 && BaseUtil.isEmpty(entity.getIdcard())){
				OperatorFailureVo msg = new OperatorFailureVo("购买此商品必须提供身份证号！");
				return JSONObject.toJSONString(msg) ;
			}

			//检查“商品有效期”	（商品有效期 对比 预计游玩日期）
			if(product.getValidEndDate().compareTo(entity.getConsumeTime()) == -1 || product.getValidStartDate().compareTo(entity.getConsumeTime()) == 1){
				OperatorFailureVo msg = new OperatorFailureVo("游玩日期不在商品有效日期内! 禁止下单!");
				return JSONObject.toJSONString(msg) ;
			}
			EnterpriseProduct listEnterpriseProduct ;          //商品  -- 企业 关系
			EnterpriseStorageMoneyPo enterpriseStorageMoneyPo; //预收款
			EnterpriseCapitalPo enterpriseCapitalPo;           //平台资金
			//用户分销轨迹
			String[] ids = enterpriseProduct.getUserTracks().split(",");
			List<String> userIdsList = new ArrayList<String>(ids.length);
			for (int i = ids.length - 1; i >= 0; i--) {
				userIdsList.add(ids[i]);
			}
			if(Long.parseLong(userIdsList.get(0)) != myEnterpriceId){
				OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
				return JSONObject.toJSONString(msg) ;
			}else{
				//迭代分销轨迹
				Iterator<String> userId = userIdsList.iterator();
				while (userId.hasNext()){
					Long listUserId = Long.parseLong(userId.next());
					listEnterpriseProduct = enterpriseProductService.getOne(entity.getSproductId(),null,listUserId);
					//检查 检查“用户平台余额”	（分别检查自下向上购买线路上用户的余额是否够用，“预收款”或“平台余额”有一个够用可以）
					enterpriseCapitalPo = enterpriseCapitalService.getByEnterpriseId(listUserId);
					//查询日历价格
					BigDecimal toPrice = null;
					ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),DateUtil.formatDate(entity.getConsumeTime()),listUserId);
					if(!productPriceVo.getType()){
						OperatorFailureVo msg = new OperatorFailureVo("该商品没有设置今日价格! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}else{
						toPrice =productPriceVo.getData().multiply(new BigDecimal(entity.getNum()));
					}
					//判断是否是供应商
					if(!(listEnterpriseProduct.getIsSupplier()==1)){
						//不是 供应商 执行
						//是否有上级企业
						if(!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())){
							//检查预收款
							enterpriseStorageMoneyPo = enterpriseStorageMoneyService.querySearch(listUserId,listEnterpriseProduct.getParentId());

							if(!BaseUtil.isEmpty(enterpriseStorageMoneyPo) && enterpriseStorageMoneyPo.getStorageMoney().compareTo(toPrice) != -1){
								//检查预收款 : 预存款关系存在 &&  预存款金额充足
							}else if((enterpriseCapitalPo.getTotalMoney().subtract(enterpriseCapitalPo.getForzenMoney())).compareTo(toPrice) != -1){
								//检查频台资金 :  金额充足
							}else{
								OperatorFailureVo msg = new OperatorFailureVo("您的平台余额不足，请立即充值！");
								return JSONObject.toJSONString(msg) ;
							}

						}else{
							OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
							return JSONObject.toJSONString(msg) ;
						}
					}else{
						break;
					}
				}
			}
		/*
		 *取电子码：5
		 *电子码集 商品实名制 1 是 0 否 取购买数量 取电子吗
		 */
			List<CodeProvide> codeList =new  ArrayList<CodeProvide>();//码集
			Integer codeNum = 0;
			if(product.getIsRealname() == 0){
				codeNum = 1;
			}
			if(product.getIsRealname() == 1){
				codeNum = entity.getNum();
			}
			List<CodeProvide> codeProvide = codeProvideService.find(codeNum);//码池表
			try {
				if (product.getIsRealname() == 0) {
					if (BaseUtil.isEmpty(codeProvide.get(0))) {
						OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
						return JSONObject.toJSONString(msg);
					}
					codeList.add(codeProvide.get(0));
				} else if (product.getIsRealname() == 1) {
					for (int j = 0; j < entity.getNum(); j++) {
						if (BaseUtil.isEmpty(codeProvide.get(j))) {
							OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
							return JSONObject.toJSONString(msg);
						}
						codeList.add(codeProvide.get(j));
					}
				} else {
					OperatorFailureVo msg = new OperatorFailureVo("该商品实名制信息出错! 请检查!");
					return JSONObject.toJSONString(msg);
				}
			}catch (Exception e){
				OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
				return JSONObject.toJSONString(msg);
			}
		/*
		 *下单（包括资金变动）：6，7，8，9
		 * 一对一 下订单 t_orders 附加 t_enterprise_orders
		 * 分歧  下单
		 *     码信息表 对应 一张订单
		 *     向上订单 没有吗信息表
		 *     码 根据 码池 取码 ,一对一,还是一对多
		 */
			//向“主订单表”（t_orders）插入数据
			Orders orders = new Orders();
			orders.setOrderno(GenerateNo.getOrderNo());
			orders.setUser(super.getCurrentUser().getId());
			orders.setUserName(super.getCurrentUser().getUsername());
			orders.setEnterpriseId(myEnterpriceId);
			orders.setEnterpriseName(infoEnterprise.getName());
			orders.setSproductId(entity.getSproductId());
			orders.setCustomerName(entity.getCustomerName());
			orders.setTel(entity.getTel());
			orders.setIdcard(entity.getIdcard());
			orders.setConsumeTime((int)(entity.getConsumeTime().getTime()/1000));
			orders.setUnitPrice(entity.getUnitPrice());
			orders.setPrice(entity.getPrice());
			orders.setTuanname("PC");
			orders.setNum(entity.getNum());
			orders.setNotes(entity.getNotes());
			//向“订单-码信息表”（t_code）插入数据
			List<Code> listCode = new ArrayList<Code>(); //订单-码信息表 存 集
			Code code;
			//验票日期
		/*（1）用户任意时间购买均可使用
				验票时间为：产品有效期,游玩
			（2）用户需提前1小时1分钟购买：			验票时间为：下单时间 + 1小时1分钟
			（3）用户需在游玩N天的N点N分前购买：
				下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/
			if(product.getPlayMode() == 1){
				if(product.getBuyOption() == 1){
					befor =  product.getValidStartDate();
				}
				if(product.getBuyOption() == 2){
					befor =new Date( new Date().getTime() + product.getBuyTimeHour() * 60 * 60 * 1000 + product.getBuyTimeMinute() * 60 * 1000 );
				}
				if(product.getBuyOption() == 3){
					//befor =new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000+ product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000 );
					befor = new Date((new Date().getTime() + product.getBuyUseAfterHour() * 60 * 60 * 1000));
				}
				aftor =new Date( product.getValidEndDate().getTime() + 24 * 60 * 60 * 1000 );
			}
			if(product.getPlayMode() == 2){
				if(product.getBuyOption() == 1){
					befor =  entity.getConsumeTime();
				}
				if(product.getBuyOption() == 2){
					befor =new Date( new Date().getTime() + product.getBuyTimeHour() * 60 * 60 * 1000 + product.getBuyTimeMinute() * 60 * 1000 );
				}
				if(product.getBuyOption() == 3){
					//befor =new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000+ product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000 );
					befor = new Date((new Date().getTime() + product.getBuyUseAfterHour() * 60 * 60 * 1000));
				}
				aftor = new Date(entity.getConsumeTime().getTime() + 24 * 60 * 60 * 1000 );
			}
			if(product.getIsRealname()==0){
				code = new Code();
				code.setOrdersId(orders.getId());
				code.setCardPwd(codeList.get(0).getCardPwd());
				code.setCodeName(product.getName());//数据库默认
				code.setSproductId(orders.getSproductId());
				code.setEnterpriseId(product.getEnterpriseId());//产品所属企业id
				code.setShopId(product.getScenicId());// 景区id
				code.setNum(entity.getNum());
				code.setStarttim((int)(befor.getTime() / 1000));//开始验证时间
				code.setEndtim((int)(aftor.getTime() / 1000));// 验证时间截止
				code.setRealName(null);
				code.setStatus(orders.getStatus());
				code.setVersion(orders.getVersion());
				listCode.add(code);
			}else if(product.getIsRealname()==1){
				for (int e= 0; e < entity.getNum(); e++) {
					realCustomerVo = new RealCustomerVo();
					realCustomerVo.setConsumerName(consumerName[e]);
					realCustomerVo.setConsumerCard(consumerCard[e]);
					json = JSONObject.toJSONString(realCustomerVo);
					code = new Code();
					code.setOrdersId(orders.getId());
					code.setCardPwd(codeList.get(e).getCardPwd());
					code.setCodeName(product.getName());//数据库默认
					code.setSproductId(orders.getSproductId());
					code.setEnterpriseId(product.getEnterpriseId());//产品所属企业id
					code.setShopId(product.getScenicId());// 景区id
					code.setNum(1);
					code.setStarttim((int)(befor.getTime() / 1000));//开始验证时间
					code.setEndtim((int)(aftor.getTime() / 1000));// 验证时间截止
					code.setRealName(json);
					code.setStatus(orders.getStatus());
					code.setVersion(orders.getVersion());
					listCode.add(code);
					RealCustomerMessageVo realCustomerMessageVo = new RealCustomerMessageVo();
					realCustomerMessageVo.setName(consumerName[e]);
					realCustomerMessageVo.setCard(consumerCard[e]);
					realCustomerMessageVo.setCode(codeList.get(e).getCardPwd());
					customerVoList.add(realCustomerMessageVo);
				}
			}else{
				OperatorFailureVo msg = new OperatorFailureVo("该商品实名制信息出错! 请检查!");
				return JSONObject.toJSONString(msg) ;
			}
			//先判断 预存款 有 就取 没有就 取 平台资金
			//根据产品获取 游玩日期价格 根据价格 下单
			//上级对下级的产品下单 设置有 不同的价格 联立下单
			//向“订单子表-企业订单表”（t_enterprise_orders）插入数据

			EnterpriseProduct listEnterpriseProductOrder ;          //商品  -- 企业 关系
			EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder;   //预收款
			EnterpriseStorageLogPo enterpriseStorageLogOrder;       //预收款日志
			EnterpriseCapitalPo enterpriseCapitalOrder;             //平台资金
			EnterpriseAccountLogPo enterpriseAccountLogOrder;       //资金变动日志
			EnterpriseCapitalPo parentEnterpriseCapitalOrder;       //上级平台资金
			EnterpriseAccountLogPo parentEnterpriseAccountLogOrder = new EnterpriseAccountLogPo();       //上级资金变动日志
			EnterpriseOrders enterpriseOrders;                      //企业向上级订单
			BigDecimal before;                                      //变动前资金
			BigDecimal now;                                         //变动资金
			BigDecimal after ;                                      //变动后资金
			Integer type = 0;                                       //资金渠道
			Map<String,Object> mapEnterpriseOrders = new HashMap<String,Object>();//企业订单 单条 记录 数据 集
			List<Map> listEnterpriseOrders = new ArrayList<Map>() ;//企业订单 存 集
			//用户分销轨迹
			String[] idsorder = enterpriseProduct.getUserTracks().split(",");
			List<String> userIdsListorder = new ArrayList<String>(idsorder.length);
			for (int i = idsorder.length - 1; i >= 0; i--) {
				userIdsListorder.add(idsorder[i]);
			}
			if(Long.parseLong(userIdsListorder.get(0)) != myEnterpriceId){
				OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
				return JSONObject.toJSONString(msg) ;
			}else{
				//迭代分销轨迹
				Iterator<String> userIdOrder = userIdsListorder.iterator();
				while (userIdOrder.hasNext()){
					Long listUserIdOrder = Long.parseLong(userIdOrder.next());
					//预收款
					listEnterpriseProductOrder = enterpriseProductService.getOne(entity.getSproductId(),null,listUserIdOrder);
					enterpriseStorageMoneyOrder = enterpriseStorageMoneyService.querySearch(listUserIdOrder,listEnterpriseProductOrder.getParentId());
					enterpriseStorageLogOrder = new EnterpriseStorageLogPo();
					//查询日历价格
					BigDecimal toPrice = null;
					ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),DateUtil.formatDate(entity.getConsumeTime()),listUserIdOrder);
					if(!productPriceVo.getType()){
						OperatorFailureVo msg = new OperatorFailureVo("该商品没有设置今日价格! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}else{
						toPrice =productPriceVo.getData();
					}
					//判断是否是供应商
					if(!(listEnterpriseProductOrder.getIsSupplier()== 1)){
						//是否有上级企业
						if(!BaseUtil.isEmpty(listEnterpriseProductOrder.getParentId())){
							//平台资金
							enterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listUserIdOrder);
							//上级平台资金
							parentEnterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProductOrder.getParentId());
							//企业--订单
							enterpriseOrders = new EnterpriseOrders();
							enterpriseOrders.setOrdersId(orders.getId());//重写
							enterpriseOrders.setOrderno(orders.getOrderno());
							enterpriseOrders.setEnterpriseId(listEnterpriseProductOrder.getChildId());
							enterpriseOrders.setEnterpriseName(infoEnterpriseService.getById(listEnterpriseProductOrder.getChildId()).getName());
							enterpriseOrders.setParentId(listEnterpriseProductOrder.getParentId());// 上级id
							enterpriseOrders.setParentName(infoEnterpriseService.getById(listEnterpriseProductOrder.getParentId()).getName());// 上级名称
							enterpriseOrders.setSproductId(orders.getSproductId());
							enterpriseOrders.setUproductId(listEnterpriseProductOrder.getId());// 关系表
							enterpriseOrders.setUproductName(product.getName());
							enterpriseOrders.setNum(orders.getNum());
							enterpriseOrders.setFxPrice(toPrice);//我对上级的购买价
							enterpriseOrders.setPrice(enterpriseOrders.getFxPrice().multiply(new BigDecimal(entity.getNum())));//总价
							enterpriseOrders.setProvince(product.getProvinceId());
							enterpriseOrders.setCity(product.getCityId());
							enterpriseOrders.setSupplierId(product.getEnterpriseId());
							enterpriseOrders.setSupplierName(productEnterprise.getName());
							enterpriseOrders.setShopId(product.getScenicId());// 商品所属商户Id   景区
							enterpriseOrders.setShopName(scenicService.getById(product.getScenicId()).getName());// 商品所属商户名称  景区名
							enterpriseOrders.setStatus(orders.getStatus());
							enterpriseOrders.setCreatedId(super.getCurrentUser().getId());//下单人
							enterpriseOrders.setVersion(orders.getVersion());//询问
							System.out.println(enterpriseOrders.toString());
							//向“变更企业资金余额和记录”，相关数据表：
							//检查预收款 : 预存款关系存在 &&  预存款金额充足
							if(!BaseUtil.isEmpty(enterpriseStorageMoneyOrder) && enterpriseStorageMoneyOrder.getStorageMoney().compareTo(enterpriseOrders.getPrice()) != -1){
								type = 1;
								//预收款表
								before = enterpriseStorageMoneyOrder.getStorageMoney();
								now = enterpriseOrders.getPrice();
								enterpriseStorageMoneyOrder.setStorageMoney(before.subtract(now));
								after = enterpriseStorageMoneyOrder.getStorageMoney();
								// 日志
								enterpriseStorageLogOrder.setParentId(enterpriseStorageMoneyOrder.getParentId());
								enterpriseStorageLogOrder.setChildId(enterpriseStorageMoneyOrder.getChildId());
								enterpriseStorageLogOrder.setBeforeMoney(before);
								enterpriseStorageLogOrder.setStorageMoney(now.multiply(new BigDecimal(-1)));
								enterpriseStorageLogOrder.setAfterMoney(after);
								enterpriseStorageLogOrder.setCreateId(super.getCurrentUser().getId());
								enterpriseStorageLogOrder.setCreateTime(new Date());
								//资金变动日志
								enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
								enterpriseAccountLogOrder.setChildId(enterpriseStorageMoneyOrder.getChildId());
								enterpriseAccountLogOrder.setParentId(enterpriseStorageMoneyOrder.getParentId());
								enterpriseAccountLogOrder.setActionType(3L);
								enterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
								enterpriseAccountLogOrder.setCapitalType(0);
								enterpriseAccountLogOrder.setOldPrice(before);
								enterpriseAccountLogOrder.setPrice(now.multiply(new BigDecimal(-1)));
								enterpriseAccountLogOrder.setCurrentPrice(after);
								enterpriseAccountLogOrder.setCreateTime(new Date());
								enterpriseAccountLogOrder.setTerminal("PC");
							}else if((enterpriseCapitalOrder.getTotalMoney().subtract(enterpriseCapitalOrder.getForzenMoney())).compareTo(enterpriseOrders.getPrice()) != -1){
								type = 2;
								//检查平台金额--重写 资金有变动
								before = enterpriseCapitalOrder.getTotalMoney();
								now = enterpriseOrders.getPrice();
								enterpriseCapitalOrder.setTotalMoney(before.subtract(now));
								after = enterpriseCapitalOrder.getTotalMoney();
								BigDecimal usable = enterpriseCapitalOrder.getUsableMoney().subtract(now);
								enterpriseCapitalOrder.setUsableMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);
								enterpriseCapitalOrder.setUpdateTime(new Date());
								enterpriseCapitalOrder.setUpdateId(super.getCurrentUser().getId());
								//资金变动日志
								enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
								enterpriseAccountLogOrder.setChildId(listEnterpriseProductOrder.getChildId());
								enterpriseAccountLogOrder.setParentId(null);
								enterpriseAccountLogOrder.setActionType(3L);
								enterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
								enterpriseAccountLogOrder.setCapitalType(1);
								enterpriseAccountLogOrder.setOldPrice(before);
								enterpriseAccountLogOrder.setPrice(now);
								enterpriseAccountLogOrder.setCurrentPrice(after);
								enterpriseAccountLogOrder.setCreateTime(new Date());
								enterpriseAccountLogOrder.setTerminal("PC");

								//上级资金变动
								before = parentEnterpriseCapitalOrder.getTotalMoney();
								now = enterpriseOrders.getPrice();
								parentEnterpriseCapitalOrder.setTotalMoney(before.add(now));
								after = parentEnterpriseCapitalOrder.getTotalMoney();
								parentEnterpriseCapitalOrder.setUsableMoney(parentEnterpriseCapitalOrder.getUsableMoney().add(now));
								parentEnterpriseCapitalOrder.setUpdateTime(new Date());
								parentEnterpriseCapitalOrder.setUpdateId(super.getCurrentUser().getId());
								//日志
								parentEnterpriseAccountLogOrder = new EnterpriseAccountLogPo();
								parentEnterpriseAccountLogOrder.setChildId(listEnterpriseProductOrder.getParentId());
								parentEnterpriseAccountLogOrder.setParentId(null);
								parentEnterpriseAccountLogOrder.setActionType(3L);
								parentEnterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
								parentEnterpriseAccountLogOrder.setCapitalType(1);
								parentEnterpriseAccountLogOrder.setOldPrice(before);
								parentEnterpriseAccountLogOrder.setPrice(now);
								parentEnterpriseAccountLogOrder.setCurrentPrice(after);
								parentEnterpriseAccountLogOrder.setCreateTime(new Date());
								parentEnterpriseAccountLogOrder.setTerminal("PC");

							}else{
								OperatorFailureVo msg = new OperatorFailureVo("您的平台余额不足，请立即充值！");
								return JSONObject.toJSONString(msg) ;
							}
							mapEnterpriseOrders = new HashMap<String,Object>();
							mapEnterpriseOrders.put("type",type);
							mapEnterpriseOrders.put("orders",enterpriseOrders);
							mapEnterpriseOrders.put("money",enterpriseStorageMoneyOrder);
							mapEnterpriseOrders.put("moneyLog",enterpriseStorageLogOrder);
							mapEnterpriseOrders.put("capital",enterpriseCapitalOrder);
							mapEnterpriseOrders.put("accountLog",enterpriseAccountLogOrder);
							mapEnterpriseOrders.put("parentcapital",parentEnterpriseCapitalOrder);
							mapEnterpriseOrders.put("parentcapitallog",parentEnterpriseAccountLogOrder);
							listEnterpriseOrders.add(mapEnterpriseOrders);
						}else{
							OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
							return JSONObject.toJSONString(msg) ;
						}
					}else{
						break;
					}
				}
			}
		/*
		 *改库存10
		 *产品总库存 目前:只有产品表 分销组 不涉及
		 */
			if(product.getStoreMode() == 2){
				product.setStoreNum(product.getStoreNum() - entity.getNum());
				product.setUpdateId(super.getCurrentUser().getId());
				product.setUpdateTime(new Date());
			}
		/*
		 *调用第三方变接口11
		 */

		/*
		 *下单
		 * orders
		 * List<Code> codeList
		 * List<Map> listEnterpriseOrders
		 *     Map<String,Object> mapEnterpriseOrders
		 *			    mapEnterpriseOrders.put("type",type); //资金渠道
						mapEnterpriseOrders.put("orders",enterpriseOrders);  //企业下单
						mapEnterpriseOrders.put("money",enterpriseStorageMoneyOrder); //预存款
						mapEnterpriseOrders.put("moneyLog",enterpriseStorageLogOrder); //预存款日志
						mapEnterpriseOrders.put("capital",enterpriseCapitalOrder); //平台资金
						mapEnterpriseOrders.put("accountLog",enterpriseStorageLogOrder); //资金日志
						mapEnterpriseOrders.put("parentcapital",parentEnterpriseCapitalOrder);//上级平台资金
						mapEnterpriseOrders.put("parentcapitallog",parentEnterpriseAccountLogOrder);//上级资金日志
		 *product
		 */
			ordersService.save(orders,listCode,listEnterpriseOrders,product);

			OperatorSuccessVo message = new OperatorSuccessVo("商品购买成功！") ;

			if(product.getIsRealname()==0){
				message.setType(0);
				message.setCode(listCode.get(0).getCardPwd());
			}else if(product.getIsRealname()==1){
				message.setType(1);
				message.setCodeList(customerVoList);
			}

			return JSONObject.toJSONString(message) ;
		}

	}

	/**
	 * 第三方下单流程
	 * @param entity
	 * @param consumerName
	 * @param consumerCard
	 * @return
	 */
	private String thirdProductAdd(ProductOrdersVo entity,String[] consumerName,String[] consumerCard, SupplierProduct product){

		Long myEnterpriceId = super.getCurrentUser().getEnterpriseId();
		InfoEnterprise infoEnterprise = infoEnterpriseService.getById(myEnterpriceId);
		InfoEnterprise productEnterprise = infoEnterpriseService.getById(product.getEnterpriseId());//商品供应商
		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(entity.getSproductId(),null,myEnterpriceId);//商品 ,当前企业关系
		Date befor = null;                                        //验票前时
		Date aftor = null;                                        //验票结束时间
		RealCustomerVo realCustomerVo;
		String json;
		List<RealCustomerMessageVo> customerVoList = new ArrayList<RealCustomerMessageVo>(); //实名制 短信
		/*
		 *检查：	1，2，3，4
		 */
		//购买时间线
			/*（1）用户任意时间购买均可使用
				验票时间为：产品有效期,游玩
			（2）用户需提前1小时1分钟购买：			验票时间为：下单时间 + 1小时1分钟
			（3）用户需在游玩N天的N点N分前购买：
				下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/
		/*if(product.getPlayMode() != 1){
			if(entity.getConsumeTime() != product.getPlayDate()){
				OperatorFailureVo msg = new OperatorFailureVo("该商品已指定游玩日期! 请重新填写!");
				return JSONObject.toJSONString(msg) ;
			}
		}
		if(entity.getConsumeTime().getTime() < product.getValidStartDate().getTime() && entity.getConsumeTime().getTime() > product.getValidEndDate().getTime()){
			OperatorFailureVo msg = new OperatorFailureVo("指定日期不在商品有效日期内! 请重新填写!");
			return JSONObject.toJSONString(msg) ;
		}*/

		//检查“商品状态”	（是否可用）
		if(product.getStatus() == 0){
			OperatorFailureVo msg = new OperatorFailureVo("该商品已停用! 禁止下单!");
			return JSONObject.toJSONString(msg) ;
		}
		//用户需在游玩N天的N点N分前购买： 下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/

		if(product.getBuyOption() == 3){

			long consumeTime = entity.getConsumeTime().getTime();//11位
			consumeTime -=  product.getBuyUseDay() * 24 * 60 * 60 * 1000;
			consumeTime += product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000;
			long nowTime = new Date().getTime();
			if(nowTime > consumeTime){
				OperatorFailureVo msg = new OperatorFailureVo("请在游玩前"+product.getBuyUseDay()+"天"+product.getBuyUseHour()+"点"+product.getBuyUseMinute()+"分购买! 禁止下单!");
				return JSONObject.toJSONString(msg) ;
			}
		}

		//购买数量
		if(entity.getNum() < 1){
			OperatorFailureVo msg = new OperatorFailureVo("此产品最少购买1张！");
			return JSONObject.toJSONString(msg) ;
		}
		//检查“商品预订数量限制”	（商品预订数量限制 对比 购买数量）
		if(product.getBuyMinNumber() != null && (entity.getNum() < product.getBuyMinNumber())) {
			OperatorFailureVo msg = new OperatorFailureVo("此产品最少购买 " + product.getBuyMinNumber() + "张！");
			return JSONObject.toJSONString(msg) ;
		}
		if(product.getBuyMaxNumber() != null && (entity.getNum() > product.getBuyMaxNumber())) {
			OperatorFailureVo msg = new OperatorFailureVo("此产品最多购买 " + product.getBuyMaxNumber() + "张！");
			return JSONObject.toJSONString(msg) ;
		}
		//检查“商品库存”	（商品库存 对比 购买数量）
		if(product.getStoreMode() == 2){
			if(entity.getNum().compareTo(product.getStoreNum()) == 1){
				OperatorFailureVo msg = new OperatorFailureVo("该商品库存不足! 请检查!");
				return JSONObject.toJSONString(msg) ;
			}
		}
		//检查商品是否需要输入身份证
		if(product.getIsMustCard() == 1 && BaseUtil.isEmpty(entity.getIdcard())){
			OperatorFailureVo msg = new OperatorFailureVo("购买此商品必须提供身份证号！");
			return JSONObject.toJSONString(msg) ;
		}

		//检查“商品有效期”	（商品有效期 对比 预计游玩日期）
		if(product.getValidEndDate().compareTo(entity.getConsumeTime()) == -1 || product.getValidStartDate().compareTo(entity.getConsumeTime()) == 1){
			OperatorFailureVo msg = new OperatorFailureVo("游玩日期不在商品有效日期内! 禁止下单!");
			return JSONObject.toJSONString(msg) ;
		}
		EnterpriseProduct listEnterpriseProduct ;          //商品  -- 企业 关系
		EnterpriseStorageMoneyPo enterpriseStorageMoneyPo; //预收款
		EnterpriseCapitalPo enterpriseCapitalPo;           //平台资金
		//用户分销轨迹
		String[] ids = enterpriseProduct.getUserTracks().split(",");
		List<String> userIdsList = new ArrayList<String>(ids.length);
		for (int i = ids.length - 1; i >= 0; i--) {
			userIdsList.add(ids[i]);
		}
		if(Long.parseLong(userIdsList.get(0)) != myEnterpriceId){
			OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}else{
			//迭代分销轨迹
			Iterator<String> userId = userIdsList.iterator();
			while (userId.hasNext()){
				Long listUserId = Long.parseLong(userId.next());
				listEnterpriseProduct = enterpriseProductService.getOne(entity.getSproductId(),null,listUserId);
				//检查 检查“用户平台余额”	（分别检查自下向上购买线路上用户的余额是否够用，“预收款”或“平台余额”有一个够用可以）
				enterpriseCapitalPo = enterpriseCapitalService.getByEnterpriseId(listUserId);
				//查询日历价格
				BigDecimal toPrice = null;
				ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),DateUtil.formatDate(entity.getConsumeTime()),listUserId);
				if(!productPriceVo.getType()){
					OperatorFailureVo msg = new OperatorFailureVo("该商品没有设置今日价格! 请检查!");
					return JSONObject.toJSONString(msg) ;
				}else{
					toPrice =productPriceVo.getData().multiply(new BigDecimal(entity.getNum()));
				}
				//判断是否是供应商
				if(!(listEnterpriseProduct.getIsSupplier()==1)){
					//不是 供应商 执行
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())){
						//检查预收款
						enterpriseStorageMoneyPo = enterpriseStorageMoneyService.querySearch(listUserId,listEnterpriseProduct.getParentId());

						if(!BaseUtil.isEmpty(enterpriseStorageMoneyPo) && enterpriseStorageMoneyPo.getStorageMoney().compareTo(toPrice) != -1){
							//检查预收款 : 预存款关系存在 &&  预存款金额充足
						}else if((enterpriseCapitalPo.getTotalMoney().subtract(enterpriseCapitalPo.getForzenMoney())).compareTo(toPrice) != -1){
							//检查频台资金 :  金额充足
						}else{
							OperatorFailureVo msg = new OperatorFailureVo("您的平台余额不足，请立即充值！");
							return JSONObject.toJSONString(msg) ;
						}

					}else{
						OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}
				}else{
					break;
				}
			}
		}
		/*
		 *取电子码：5
		 *电子码集 商品实名制 1 是 0 否 取购买数量 取电子吗
		 */
		List<CodeProvide> codeList =new  ArrayList<CodeProvide>();//码集
		Integer codeNum = 0;
		if(product.getIsRealname() == 0){
			codeNum = 1;
		}
		if(product.getIsRealname() == 1){
			codeNum = entity.getNum();
		}
		List<CodeProvide> codeProvide = codeProvideService.find(codeNum);//码池表
		try {
			if (product.getIsRealname() == 0) {
				if (BaseUtil.isEmpty(codeProvide.get(0))) {
					OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
					return JSONObject.toJSONString(msg);
				}
				codeList.add(codeProvide.get(0));
			} else if (product.getIsRealname() == 1) {
				for (int j = 0; j < entity.getNum(); j++) {
					if (BaseUtil.isEmpty(codeProvide.get(j))) {
						OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
						return JSONObject.toJSONString(msg);
					}
					codeList.add(codeProvide.get(j));
				}
			} else {
				OperatorFailureVo msg = new OperatorFailureVo("该商品实名制信息出错! 请检查!");
				return JSONObject.toJSONString(msg);
			}
		}catch (Exception e){
			OperatorFailureVo msg = new OperatorFailureVo("码池空了! 请补充!");
			return JSONObject.toJSONString(msg);
		}
		/*
		 *下单（包括资金变动）：6，7，8，9
		 * 一对一 下订单 t_orders 附加 t_enterprise_orders
		 * 分歧  下单
		 *     码信息表 对应 一张订单
		 *     向上订单 没有吗信息表
		 *     码 根据 码池 取码 ,一对一,还是一对多
		 */
		//向“主订单表”（t_orders）插入数据
		Orders orders = new Orders();
		orders.setOrderno(GenerateNo.getOrderNo());
		orders.setUser(super.getCurrentUser().getId());
		orders.setUserName(super.getCurrentUser().getUsername());
		orders.setEnterpriseId(myEnterpriceId);
		orders.setEnterpriseName(infoEnterprise.getName());
		orders.setSproductId(entity.getSproductId());
		orders.setCustomerName(entity.getCustomerName());
		orders.setTel(entity.getTel());
		orders.setIdcard(entity.getIdcard());
		orders.setConsumeTime((int)(entity.getConsumeTime().getTime()/1000));
		orders.setUnitPrice(entity.getUnitPrice());
		orders.setPrice(entity.getPrice());
		orders.setTuanname("PC");
		orders.setNum(entity.getNum());
		orders.setNotes(entity.getNotes());
		orders.setStatus(2);
		//向“订单-码信息表”（t_code）插入数据
		List<Code> listCode = new ArrayList<Code>(); //订单-码信息表 存 集
		Code code;
		//验票日期
		/*（1）用户任意时间购买均可使用
				验票时间为：产品有效期,游玩
			（2）用户需提前1小时1分钟购买：			验票时间为：下单时间 + 1小时1分钟
			（3）用户需在游玩N天的N点N分前购买：
				下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/
		if(product.getPlayMode() == 1){
			if(product.getBuyOption() == 1){
				befor =  product.getValidStartDate();
			}
			if(product.getBuyOption() == 2){
				befor =new Date( new Date().getTime() + product.getBuyTimeHour() * 60 * 60 * 1000 + product.getBuyTimeMinute() * 60 * 1000 );
			}
			if(product.getBuyOption() == 3){
				//befor =new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000+ product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000 );
				befor = new Date((new Date().getTime() + product.getBuyUseAfterHour() * 60 * 60 * 1000));
			}
			aftor =new Date( product.getValidEndDate().getTime() + 24 * 60 * 60 * 1000 );
		}
		if(product.getPlayMode() == 2){
			if(product.getBuyOption() == 1){
				befor =  entity.getConsumeTime();
			}
			if(product.getBuyOption() == 2){
				befor =new Date( new Date().getTime() + product.getBuyTimeHour() * 60 * 60 * 1000 + product.getBuyTimeMinute() * 60 * 1000 );
			}
			if(product.getBuyOption() == 3){
				//befor =new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000+ product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000 );
				befor = new Date((new Date().getTime() + product.getBuyUseAfterHour() * 60 * 60 * 1000));
			}
			aftor = new Date(entity.getConsumeTime().getTime() + 24 * 60 * 60 * 1000 );
		}
		if(product.getIsRealname()==0){
			code = new Code();
			code.setOrdersId(orders.getId());
			code.setCardPwd(codeList.get(0).getCardPwd());
			code.setCodeName(product.getName());//数据库默认
			code.setSproductId(orders.getSproductId());
			code.setEnterpriseId(product.getEnterpriseId());//产品所属企业id
			code.setShopId(product.getScenicId());// 景区id
			code.setNum(entity.getNum());
			code.setStarttim((int)(befor.getTime() / 1000));//开始验证时间
			code.setEndtim((int)(aftor.getTime() / 1000));// 验证时间截止
			code.setRealName(null);
			code.setStatus(orders.getStatus());
			code.setVersion(orders.getVersion());
			listCode.add(code);
		}else if(product.getIsRealname()==1){
			for (int e= 0; e < entity.getNum(); e++) {
				realCustomerVo = new RealCustomerVo();
				realCustomerVo.setConsumerName(consumerName[e]);
				realCustomerVo.setConsumerCard(consumerCard[e]);
				json = JSONObject.toJSONString(realCustomerVo);
				code = new Code();
				code.setOrdersId(orders.getId());
				code.setCardPwd(codeList.get(e).getCardPwd());
				code.setCodeName(product.getName());//数据库默认
				code.setSproductId(orders.getSproductId());
				code.setEnterpriseId(product.getEnterpriseId());//产品所属企业id
				code.setShopId(product.getScenicId());// 景区id
				code.setNum(1);
				code.setStarttim((int)(befor.getTime() / 1000));//开始验证时间
				code.setEndtim((int)(aftor.getTime() / 1000));// 验证时间截止
				code.setRealName(json);
				code.setStatus(orders.getStatus());
				code.setVersion(orders.getVersion());
				listCode.add(code);
				RealCustomerMessageVo realCustomerMessageVo = new RealCustomerMessageVo();
				realCustomerMessageVo.setName(consumerName[e]);
				realCustomerMessageVo.setCard(consumerCard[e]);
				realCustomerMessageVo.setCode(codeList.get(e).getCardPwd());
				customerVoList.add(realCustomerMessageVo);
			}
		}else{
			OperatorFailureVo msg = new OperatorFailureVo("该商品实名制信息出错! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}
		//先判断 预存款 有 就取 没有就 取 平台资金
		//根据产品获取 游玩日期价格 根据价格 下单
		//上级对下级的产品下单 设置有 不同的价格 联立下单
		//向“订单子表-企业订单表”（t_enterprise_orders）插入数据

		EnterpriseProduct listEnterpriseProductOrder ;          //商品  -- 企业 关系
		EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder;   //预收款
		EnterpriseStorageLogPo enterpriseStorageLogOrder;       //预收款日志
		EnterpriseCapitalPo enterpriseCapitalOrder;             //平台资金
		EnterpriseAccountLogPo enterpriseAccountLogOrder;       //资金变动日志
		EnterpriseCapitalPo parentEnterpriseCapitalOrder;       //上级平台资金
		EnterpriseAccountLogPo parentEnterpriseAccountLogOrder = new EnterpriseAccountLogPo();       //上级资金变动日志
		EnterpriseOrders enterpriseOrders;                      //企业向上级订单
		BigDecimal before;                                      //变动前资金
		BigDecimal now;                                         //变动资金
		BigDecimal after ;                                      //变动后资金
		Integer type = 0;                                       //资金渠道
		Map<String,Object> mapEnterpriseOrders = new HashMap<String,Object>();//企业订单 单条 记录 数据 集
		List<Map> listEnterpriseOrders = new ArrayList<Map>() ;//企业订单 存 集
		//用户分销轨迹
		String[] idsorder = enterpriseProduct.getUserTracks().split(",");
		List<String> userIdsListorder = new ArrayList<String>(idsorder.length);
		for (int i = idsorder.length - 1; i >= 0; i--) {
			userIdsListorder.add(idsorder[i]);
		}
		if(Long.parseLong(userIdsListorder.get(0)) != myEnterpriceId){
			OperatorFailureVo msg = new OperatorFailureVo("您没有分销该商品权限! 请检查!");
			return JSONObject.toJSONString(msg) ;
		}else{
			//迭代分销轨迹
			Iterator<String> userIdOrder = userIdsListorder.iterator();
			while (userIdOrder.hasNext()){
				Long listUserIdOrder = Long.parseLong(userIdOrder.next());
				//预收款
				listEnterpriseProductOrder = enterpriseProductService.getOne(entity.getSproductId(),null,listUserIdOrder);
				enterpriseStorageMoneyOrder = enterpriseStorageMoneyService.querySearch(listUserIdOrder,listEnterpriseProductOrder.getParentId());
				enterpriseStorageLogOrder = new EnterpriseStorageLogPo();
				//查询日历价格
				BigDecimal toPrice = null;
				ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),DateUtil.formatDate(entity.getConsumeTime()),listUserIdOrder);
				if(!productPriceVo.getType()){
					OperatorFailureVo msg = new OperatorFailureVo("该商品没有设置今日价格! 请检查!");
					return JSONObject.toJSONString(msg) ;
				}else{
					toPrice =productPriceVo.getData();
				}
				//判断是否是供应商
				if(!(listEnterpriseProductOrder.getIsSupplier()== 1)){
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProductOrder.getParentId())){
						//平台资金
						enterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listUserIdOrder);
						//上级平台资金
						parentEnterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProductOrder.getParentId());
						//企业--订单
						enterpriseOrders = new EnterpriseOrders();
						enterpriseOrders.setOrdersId(orders.getId());//重写
						enterpriseOrders.setOrderno(orders.getOrderno());
						enterpriseOrders.setEnterpriseId(listEnterpriseProductOrder.getChildId());
						enterpriseOrders.setEnterpriseName(infoEnterpriseService.getById(listEnterpriseProductOrder.getChildId()).getName());
						enterpriseOrders.setParentId(listEnterpriseProductOrder.getParentId());// 上级id
						enterpriseOrders.setParentName(infoEnterpriseService.getById(listEnterpriseProductOrder.getParentId()).getName());// 上级名称
						enterpriseOrders.setSproductId(orders.getSproductId());
						enterpriseOrders.setUproductId(listEnterpriseProductOrder.getId());// 关系表
						enterpriseOrders.setUproductName(product.getName());
						enterpriseOrders.setNum(orders.getNum());
						enterpriseOrders.setFxPrice(toPrice);//我对上级的购买价
						enterpriseOrders.setPrice(enterpriseOrders.getFxPrice().multiply(new BigDecimal(entity.getNum())));//总价
						enterpriseOrders.setProvince(product.getProvinceId());
						enterpriseOrders.setCity(product.getCityId());
						enterpriseOrders.setSupplierId(product.getEnterpriseId());
						enterpriseOrders.setSupplierName(productEnterprise.getName());
						enterpriseOrders.setShopId(product.getScenicId());// 商品所属商户Id   景区
						enterpriseOrders.setShopName(scenicService.getById(product.getScenicId()).getName());// 商品所属商户名称  景区名
						enterpriseOrders.setStatus(orders.getStatus());
						enterpriseOrders.setCreatedId(super.getCurrentUser().getId());//下单人
						enterpriseOrders.setVersion(orders.getVersion());//询问
						System.out.println(enterpriseOrders.toString());
						//向“变更企业资金余额和记录”，相关数据表：
						//检查预收款 : 预存款关系存在 &&  预存款金额充足
						if(!BaseUtil.isEmpty(enterpriseStorageMoneyOrder) && enterpriseStorageMoneyOrder.getStorageMoney().compareTo(enterpriseOrders.getPrice()) != -1){
							type = 1;
							//预收款表
							before = enterpriseStorageMoneyOrder.getStorageMoney();
							now = enterpriseOrders.getPrice();
							enterpriseStorageMoneyOrder.setStorageMoney(before.subtract(now));
							after = enterpriseStorageMoneyOrder.getStorageMoney();
							// 日志
							enterpriseStorageLogOrder.setParentId(enterpriseStorageMoneyOrder.getParentId());
							enterpriseStorageLogOrder.setChildId(enterpriseStorageMoneyOrder.getChildId());
							enterpriseStorageLogOrder.setBeforeMoney(before);
							enterpriseStorageLogOrder.setStorageMoney(now.multiply(new BigDecimal(-1)));
							enterpriseStorageLogOrder.setAfterMoney(after);
							enterpriseStorageLogOrder.setCreateId(super.getCurrentUser().getId());
							enterpriseStorageLogOrder.setCreateTime(new Date());
							//资金变动日志
							enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
							enterpriseAccountLogOrder.setChildId(enterpriseStorageMoneyOrder.getChildId());
							enterpriseAccountLogOrder.setParentId(enterpriseStorageMoneyOrder.getParentId());
							enterpriseAccountLogOrder.setActionType(3L);
							enterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
							enterpriseAccountLogOrder.setCapitalType(0);
							enterpriseAccountLogOrder.setOldPrice(before);
							enterpriseAccountLogOrder.setPrice(now.multiply(new BigDecimal(-1)));
							enterpriseAccountLogOrder.setCurrentPrice(after);
							enterpriseAccountLogOrder.setCreateTime(new Date());
							enterpriseAccountLogOrder.setTerminal("PC");
						}else if((enterpriseCapitalOrder.getTotalMoney().subtract(enterpriseCapitalOrder.getForzenMoney())).compareTo(enterpriseOrders.getPrice()) != -1){
							type = 2;
							//检查平台金额--重写 资金有变动
							before = enterpriseCapitalOrder.getTotalMoney();
							now = enterpriseOrders.getPrice();
							enterpriseCapitalOrder.setTotalMoney(before.subtract(now));
							after = enterpriseCapitalOrder.getTotalMoney();
							BigDecimal usable = enterpriseCapitalOrder.getUsableMoney().subtract(now);
							enterpriseCapitalOrder.setUsableMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);
							enterpriseCapitalOrder.setUpdateTime(new Date());
							enterpriseCapitalOrder.setUpdateId(super.getCurrentUser().getId());
							//资金变动日志
							enterpriseAccountLogOrder = new EnterpriseAccountLogPo();
							enterpriseAccountLogOrder.setChildId(listEnterpriseProductOrder.getChildId());
							enterpriseAccountLogOrder.setParentId(null);
							enterpriseAccountLogOrder.setActionType(3L);
							enterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
							enterpriseAccountLogOrder.setCapitalType(1);
							enterpriseAccountLogOrder.setOldPrice(before);
							enterpriseAccountLogOrder.setPrice(now);
							enterpriseAccountLogOrder.setCurrentPrice(after);
							enterpriseAccountLogOrder.setCreateTime(new Date());
							enterpriseAccountLogOrder.setTerminal("PC");

							//上级资金变动
							before = parentEnterpriseCapitalOrder.getTotalMoney();
							now = enterpriseOrders.getPrice();
							parentEnterpriseCapitalOrder.setTotalMoney(before.add(now));
							after = parentEnterpriseCapitalOrder.getTotalMoney();
							parentEnterpriseCapitalOrder.setUsableMoney(parentEnterpriseCapitalOrder.getUsableMoney().add(now));
							parentEnterpriseCapitalOrder.setUpdateTime(new Date());
							parentEnterpriseCapitalOrder.setUpdateId(super.getCurrentUser().getId());
							//日志
							parentEnterpriseAccountLogOrder = new EnterpriseAccountLogPo();
							parentEnterpriseAccountLogOrder.setChildId(listEnterpriseProductOrder.getParentId());
							parentEnterpriseAccountLogOrder.setParentId(null);
							parentEnterpriseAccountLogOrder.setActionType(3L);
							parentEnterpriseAccountLogOrder.setActionId(enterpriseOrders.getId());//根据企业--订单表下
							parentEnterpriseAccountLogOrder.setCapitalType(1);
							parentEnterpriseAccountLogOrder.setOldPrice(before);
							parentEnterpriseAccountLogOrder.setPrice(now);
							parentEnterpriseAccountLogOrder.setCurrentPrice(after);
							parentEnterpriseAccountLogOrder.setCreateTime(new Date());
							parentEnterpriseAccountLogOrder.setTerminal("PC");

						}else{
							OperatorFailureVo msg = new OperatorFailureVo("您的平台余额不足，请立即充值！");
							return JSONObject.toJSONString(msg) ;
						}
						mapEnterpriseOrders = new HashMap<String,Object>();
						mapEnterpriseOrders.put("type",type);
						mapEnterpriseOrders.put("orders",enterpriseOrders);
						mapEnterpriseOrders.put("money",enterpriseStorageMoneyOrder);
						mapEnterpriseOrders.put("moneyLog",enterpriseStorageLogOrder);
						mapEnterpriseOrders.put("capital",enterpriseCapitalOrder);
						mapEnterpriseOrders.put("accountLog",enterpriseAccountLogOrder);
						mapEnterpriseOrders.put("parentcapital",parentEnterpriseCapitalOrder);
						mapEnterpriseOrders.put("parentcapitallog",parentEnterpriseAccountLogOrder);
						listEnterpriseOrders.add(mapEnterpriseOrders);
					}else{
						OperatorFailureVo msg = new OperatorFailureVo("商品分销没有上级企业! 请检查!");
						return JSONObject.toJSONString(msg) ;
					}
				}else{
					break;
				}
			}
		}
		/*
		 *改库存10
		 *产品总库存 目前:只有产品表 分销组 不涉及
		 */
		if(product.getStoreMode() == 2){
			product.setStoreNum(product.getStoreNum() - entity.getNum());
			product.setUpdateId(super.getCurrentUser().getId());
			product.setUpdateTime(new Date());
		}
		/*
		 *调用第三方变接口11
		 */

		/*
		 *下单
		 * orders
		 * List<Code> codeList
		 * List<Map> listEnterpriseOrders
		 *     Map<String,Object> mapEnterpriseOrders
		 *			    mapEnterpriseOrders.put("type",type); //资金渠道
						mapEnterpriseOrders.put("orders",enterpriseOrders);  //企业下单
						mapEnterpriseOrders.put("money",enterpriseStorageMoneyOrder); //预存款
						mapEnterpriseOrders.put("moneyLog",enterpriseStorageLogOrder); //预存款日志
						mapEnterpriseOrders.put("capital",enterpriseCapitalOrder); //平台资金
						mapEnterpriseOrders.put("accountLog",enterpriseStorageLogOrder); //资金日志
						mapEnterpriseOrders.put("parentcapital",parentEnterpriseCapitalOrder);//上级平台资金
						mapEnterpriseOrders.put("parentcapitallog",parentEnterpriseAccountLogOrder);//上级资金日志
		 *product
		 */


		//请求第三方接口
		OrderResutlVo orderResutlVo = requestSupplierSysService.order(product, orders, listCode);

		if(orderResutlVo.getSuccess()){
			//预存二维码地址
			OperatorSuccessVo message = new OperatorSuccessVo("商品购买成功！") ;

			if(product.getIsRealname()==0){
				message.setType(0);
				message.setCode(listCode.get(0).getCardPwd());
			}else if(product.getIsRealname()==1){
				message.setType(1);
				message.setCodeList(customerVoList);
			}

			orders.setQrcode(orderResutlVo.getQrcode());
			ordersService.save(orders,listCode,listEnterpriseOrders,product);
			return JSONObject.toJSONString(message);
		}else{

			String errorMessage = "";
			if(orderResutlVo.getStatusCode().equals("-5")){
				orders.setStatus(16);
				errorMessage = "商品购买失败！";

			}else if(orderResutlVo.getStatusCode().equals("0000")){
				//走定时任务处理
				errorMessage = "网络请求失败！系统将会自动处理该订单。";
			}
			ordersService.save(orders,listCode,listEnterpriseOrders,product);

			if(orderResutlVo.getStatusCode().equals("-5")){
				//直接进入退款流程
				orderRefund(orders);
			}

			OperatorSuccessVo message = new OperatorSuccessVo(errorMessage) ;
			return JSONObject.toJSONString(message);
		}
	}


	/**
	 * 进入退款流程
	 * @param entity
	 * @param box
	 */
	private void orderRefund(Orders entity){

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
			logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不能退款! 请检查!");
		}
		//边界检验
		if((orders.getNum() - orders.getLocknum() - orders.getPrintnum() - orders.getClocknum()) < entity.getNum()){
			logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不符合退款条件! 请检查!");
		}
		//退款时间判断
		//获取今天时间和订单时间差
		if(!BaseUtil.isEmpty(product.getRefundAfterDay())){
			int shijiancha =  (int)(System.currentTimeMillis()/1000) - orders.getCreatedAt();
			if(product.getRefundTime() == 2 &&  product.getRefundAfterDay() <= (shijiancha/60/60/24)){
				logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品已经超过退款限定时间，请与供应商联系！");
			}
		}

		if(!BaseUtil.isEmpty(product.getRefundAfterDay()) && !BaseUtil.isEmpty(product.getRefundAfterHour()) && !BaseUtil.isEmpty(product.getRefundAfterMinute())){

			long shijiandian = orders.getConsumeTime() - product.getRefundAfterDay()*24*60*60;
			shijiandian += product.getRefundAfterHour()*60*60 + product.getRefundAfterMinute()*60;

			long timenow = System.currentTimeMillis()/1000;

			if(product.getRefundTime() == 3 &&  timenow > shijiandian){
				logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品已经超过退款限定时间，请与供应商联系！");
			}
		}

		//检查可退款数量
		int refundNum = orders.getNum() - orders.getPrintnum() - orders.getLocknum() - orders.getClocknum() ;
		if(entity.getNum() > refundNum){
			logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:最大可退数量 "+refundNum+" ! 请检查!");
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
						logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品退款手续费用超出退款单费用! 请检查!");
					}
					if(!BaseUtil.isEmpty(enterpriseParentAccountLog)){
						if(enterpriseParentAccountLog.getCapitalType() == 1){
							if((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(refund ) == -1){
								logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:企业资金不足,暂不支持退款! 请联系!");
							}
						}
					}else{
						logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:尚未付款，不能退款！");
					}
				}else{
					logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:商品分销没有上级企业! 请检查!");
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
						logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品退款手续费用超出退款单费用! 请检查!");
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
//									for(String pk : box){
//										if(Long.parseLong(pk) == code.getId()){

											//边界检验
											if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
												logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不符合退款条件! 请检查!");
											}

											code.setStatus(orders.getStatus());
											code.setLocknum(1);
											code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
											Listcode.add(code);
//										}
//									}
								}
								if(product.getIsRealname()== 0){

									//边界检验
									if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < entity.getNum()){
										logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不符合退款条件! 请检查!");
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
									logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:企业资金不足,暂不支持退款! 请联系!");
								}
							}
						}else{
							logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:尚未付款，不能退款！");
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
//									for(String pk : box){
//										if(Long.parseLong(pk) == code.getId()){

											//边界检验
											if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
												logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不符合退款条件! 请检查!");
											}
											code.setStatus(orders.getStatus());
											code.setClocknum(1);
											code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
											Listcode.add(code);
//										}
//									}
								}
								if(product.getIsRealname()== 0){

									//边界检验
									if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < entity.getNum()){
										logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:该商品不符合退款条件! 请检查!");
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
					logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:商品分销没有上级企业! 请检查!");
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
		logger.info("productID:"+product.getId()+",orderId:"+ orders.getId()+",Message:订单退款申请成功！");
	}

	
	/**
	 * 显示实名制信息
	 */
	@RequestMapping(value = "/showRealConsumerInfo", method = RequestMethod.GET)
	public String showRealConsumerInfo(HttpServletRequest request, Integer buyNum, Model model) {
		model.addAttribute("buyNum", buyNum);
		return "/orders/product/consumer_info";
	}

	/**
	 * 给手机端发送验证码短信  非实名制
	 */
	@RequestMapping(value = "/{phone}/{name}/{num}/{code}/sendOrderMessageNo", method = RequestMethod.GET)
	@ResponseBody
	public void sendOrderMessageNo( @PathVariable String phone, @PathVariable String name, @PathVariable String num, @PathVariable String code) {
		String content = "【云端行】您所购买的“{product}”产品，购买数量：{num}。产品验证码为：{code}。请使用此验证码进行消费！严防诈骗！不要告诉任何人！";
		content = content.replace("{product}", name);
		content = content.replace("{num}", num);
		content = content.replace("{code}", code);
		SmsManager.sendMessage(phone, content);
		System.out.println("短信  一  发送 ");
	}

	/**
	 * 给手机端发送验证码短信  实名制
	 */
	@RequestMapping(value = "/{phone}/{name}/{card}/{productname}/{code}/sendOrderMessageis", method = RequestMethod.GET)
	@ResponseBody
	public void sendOrderMessageis( @PathVariable String phone,@PathVariable String name, @PathVariable String card, @PathVariable String productname, @PathVariable String code) {
		String content = "【云端行】{name}({idCard})，您好。您所购买的“{product}”产品，购买数量：{num}。产品验证码为：{code}。请使用此验证码进行消费！严防诈骗！不要告诉任何人！";
		content = content.replace("{name}", name);
		content = content.replace("{idCard}", card);
		content = content.replace("{product}", productname);
		content = content.replace("{num}", "1张");
		content = content.replace("{code}", code);
		SmsManager.sendMessage(phone, content);

		System.out.println("短信  多  发送 ");
	}

	/**
	 * 处理日历价格
	 */
	private ProductPriceVo getProductPrice(Long sproductId, String date, Long enterpriseId){
		ProductPriceVo productPriceVo = new ProductPriceVo();
		String capital =  "["+calendarPriceService.productCostByDate(sproductId,date,enterpriseId)+"]";
		JSONArray jsonArray=JSONArray.fromObject(capital);
		Object o=jsonArray.get(0);
		net.sf.json.JSONObject jsonObject2= net.sf.json.JSONObject.fromObject(o);
		ProductCalendarPriceVo productCalendarPriceVo=(ProductCalendarPriceVo) net.sf.json.JSONObject.toBean(jsonObject2, ProductCalendarPriceVo.class);
		if(productCalendarPriceVo.getSuccess() == "false" || productCalendarPriceVo.getData()==null){
			productPriceVo.setType(false);
			productPriceVo.setData(null);
			return productPriceVo;
		} else{
			productPriceVo.setType(true);
			productPriceVo.setData(new BigDecimal(productCalendarPriceVo.getData()));
			return productPriceVo;
		}
	}
}