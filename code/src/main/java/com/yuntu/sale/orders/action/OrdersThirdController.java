package com.yuntu.sale.orders.action;

import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.coolshow.util.DateUtil;
import com.yuntu.sale.base.GenerateNo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.orders.service.*;
import com.yuntu.sale.orders.vo.ProductCalendarPriceVo;
import com.yuntu.sale.orders.vo.ProductPriceVo;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.service.CalendarPriceService;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.vo.RealCustomerVo;
import com.yuntu.sale.orders.vo.third.ThirdOrdersMessageVo;
import com.yuntu.sale.orders.vo.third.ThirdOrdersRefundMessageVo;
import com.yuntu.sale.orders.vo.third.ThirdOrdersRefundVo;
import com.yuntu.sale.orders.vo.third.ThirdOrdersVo;
import com.yuntu.sale.orders.vo.third.TouristCardPwdVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;

/**
 * @Description 订单-第三方接口Controller
 * @author snps
 * @date 2018年5月22日 下午1:24:17
 */
@Controller
@RequestMapping(value = "/orders/third")
public class OrdersThirdController extends BaseAction {
	
	private static final Logger log = LoggerFactory.getLogger(OrdersThirdController.class);
	
	@Resource
	private OrdersService ordersService;
	
	@Resource
	private CodeService codeService;

	@Resource
	private SupplierProductService supplierProductService;

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
	private CalendarPriceService calendarPriceService;

	@Resource
	private EnterpriseOrdersService enterpriseOrdersService;

	@Resource
	private OrdersRefundService ordersRefundService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;

	@Resource
	private InfoUserService infoUserService;
	/**
	 * 重新发码
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Long orderId, Model model) {
		return "/orders/third/test";
	}

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String main1(HttpServletRequest request, Long orderId, Model model) {
		return "/orders/third/test1";
	}

	/**
	 * OTA下单
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	@ResponseBody
	public String buy(HttpServletRequest request, @RequestBody ThirdOrdersVo thirdOrders) {

		// 查看接收到的下单数据
		System.out.println(thirdOrders);
		
		// 检查第三方订单是否存在 （检查：t_orders 表中的：tuanname + payid 是否存在 ，如果存在则返回，报“第三方订单已存在”
		Orders ordersThird = ordersService.getThirdOrders(thirdOrders.getTuanname(),thirdOrders.getPayid());
		if(!BaseUtil.isEmpty(ordersThird)){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(405, "第三方订单已存在！"));
		}
		
		// 检查各项信息，不同错误返回不同的message(code, message)
		Long myEnterpriceId = thirdOrders.getEnterpriseId();
		InfoEnterprise infoEnterprise = infoEnterpriseService.getById(myEnterpriceId);
		SupplierProduct product = supplierProductService.getById(thirdOrders.getSproductId()) ;//购买商品的商品信息
		InfoEnterprise productEnterprise = infoEnterpriseService.getById(product.getEnterpriseId());//商品供应商
		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(thirdOrders.getSproductId(),null,myEnterpriceId);//商品 ,当前企业关系
		Date befor = null;                                        //验票前时
		Date aftor = null;                                        //验票结束时间
		RealCustomerVo realCustomerVo;
		String json;
		/*
		 *检查：	1，2，3，4
		 */
		//购买时间线
			/*（1）用户任意时间购买均可使用
				验票时间为：产品有效期,游玩
			（2）用户需提前1小时1分钟购买：			验票时间为：下单时间 + 1小时1分钟
			（3）用户需在游玩N天的N点N分前购买：
				下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/

		//检查“商品状态”	（是否可用）
		if(product.getStatus() == 0){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(406, "该商品已停用! 禁止下单!"));
		}
		//用户需在游玩N天的N点N分前购买： 下单验证，判断：（下单时间 + N天的N点N分） < 预计游玩时间，则能下单*/

		//游玩时间
		if(product.getBuyOption() == 3){
			befor = new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000 + product.getBuyUseHour() * 60 * 60 * 1000  + product.getBuyUseMinute() * 60 * 1000);
			if(befor.getTime()/1000 > thirdOrders.getConsumeTim()){
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(407, "请在游玩前"+product.getBuyUseDay()+"天"+product.getBuyUseHour()+"小时"+product.getBuyUseMinute()+"分提前购买! 禁止下单!"));
			}
		}

		//购买数量
		if(thirdOrders.getOrderNum() < 1){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(408, "此产品最少购买1张！"));
		}
		//检查“商品预订数量限制”	（商品预订数量限制 对比 购买数量）
		if(product.getBuyMinNumber() != null && (thirdOrders.getOrderNum()  < product.getBuyMinNumber())) {
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(408, "此产品最少购买 " + product.getBuyMinNumber() + "张！"));
		}
		if(product.getBuyMaxNumber() != null && (thirdOrders.getOrderNum() > product.getBuyMaxNumber())) {
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(408, "此产品最多购买 " + product.getBuyMaxNumber() + "张！"));
		}
		//检查“商品库存”	（商品库存 对比 购买数量）
		if(product.getStoreMode() == 2){
			if(thirdOrders.getOrderNum().compareTo(product.getStoreNum()) == 1){
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(409, "该商品库存不足! 请检查!"));
			}
		}

		//检查“商品有效期”	（商品有效期 对比 预计游玩日期）
		if((product.getValidEndDate().getTime()/24/60/60/1000) < (thirdOrders.getConsumeTim()/24/60/60) || (product.getValidStartDate().getTime()/24/60/60/1000) > (thirdOrders.getConsumeTim()/24/60/60)){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(410, "游玩日期不在商品有效日期内! 禁止下单!"));
		}

		// 保存订单-码信息
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
			codeNum = thirdOrders.getOrderNum();
		}
		List<CodeProvide> codeProvide = codeProvideService.find(codeNum);//码池表
		try {
			if (product.getIsRealname() == 0) {
				if (BaseUtil.isEmpty(codeProvide.get(0))) {
					return JSONObject.toJSONString(new ThirdOrdersMessageVo(411, "码池空了! 请补充!"));
				}
				codeList.add(codeProvide.get(0));
			} else if (product.getIsRealname() == 1) {
				for (int j = 0; j < thirdOrders.getOrderNum(); j++) {
					if (BaseUtil.isEmpty(codeProvide.get(j))) {
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(411, "码池空了! 请补充!"));
					}
					codeList.add(codeProvide.get(j));
				}
			} else {
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(412, "该商品实名制信息出错! 请检查!"));
			}
		}catch (Exception e){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(411, "码池空了! 请补充!"));
		}

		InfoUser infoUser = infoUserService.getEnterpriseMasterUser(myEnterpriceId);
		
		// 保存订单信息，订单状态为：2（等待发码）
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
		orders.setUser(infoUser.getId());
		orders.setUserName(infoUser.getUsername());
		orders.setEnterpriseId(myEnterpriceId);
		orders.setEnterpriseName(infoEnterprise.getName());
		orders.setSproductId(thirdOrders.getSproductId());
		orders.setCustomerName(thirdOrders.getBuyUser());
		orders.setTel(thirdOrders.getTel());
		orders.setIdcard(thirdOrders.getIdcard());
		orders.setConsumeTime(Integer.parseInt(Long.toString(thirdOrders.getConsumeTim())));
		orders.setUnitPrice(thirdOrders.getUnitPrice());
		orders.setPrice(thirdOrders.getPrice());
		orders.setPayid(thirdOrders.getPayid());
		orders.setPayApiId(thirdOrders.getPayApiId());
		orders.setTuanname(thirdOrders.getTuanname());
		orders.setNum(thirdOrders.getOrderNum());
		orders.setNotes(thirdOrders.getNote());
		orders.setSendtype(thirdOrders.getSendtype());
		orders.setStatus(1);
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
				befor =  new Date(thirdOrders.getConsumeTim() * 1000);
			}
			if(product.getBuyOption() == 2){
				befor = new Date( new Date().getTime() + product.getBuyTimeHour() * 60 * 60 * 1000 + product.getBuyTimeMinute() * 60 * 1000 );
			}
			if(product.getBuyOption() == 3){
				//befor =new Date( new Date().getTime() + product.getBuyUseDay() * 24 * 60 * 60 * 1000+ product.getBuyUseHour() * 60 * 60 * 1000 + product.getBuyUseMinute() * 60 * 1000 );
				befor = new Date((new Date().getTime() + product.getBuyUseAfterHour() * 60 * 60 * 1000));
			}
			aftor = new Date((thirdOrders.getConsumeTim() + 24 * 60 * 60 ) * 1000 );
		}
		if(product.getIsRealname()==0){
			code = new Code();
			code.setOrdersId(orders.getId());
			code.setCardPwd(codeList.get(0).getCardPwd());
			code.setCodeName(product.getName());//数据库默认
			code.setSproductId(orders.getSproductId());
			code.setEnterpriseId(product.getEnterpriseId());//产品所属企业id
			code.setShopId(product.getScenicId());// 景区id
			code.setNum(thirdOrders.getOrderNum());
			code.setStarttim((int)(befor.getTime() / 1000));//开始验证时间
			code.setEndtim((int)(aftor.getTime() / 1000));// 验证时间截止
			code.setRealName(null);
			code.setStatus(orders.getStatus());
			code.setVersion(orders.getVersion());
			listCode.add(code);
		}else if(product.getIsRealname()==1){

			if(thirdOrders.getIdcards().size() == 0 || thirdOrders.getIdcards().size() != thirdOrders.getOrderNum()){
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(416, "实名制商品下单Idcards不能为空，并且数量需要和OrderNum一致! 请检查!"));
			}

			for (int e= 0; e < thirdOrders.getOrderNum(); e++) {
				realCustomerVo = new RealCustomerVo();
				realCustomerVo.setConsumerName(thirdOrders.getIdcards().get(e).getName());
				realCustomerVo.setConsumerCard(thirdOrders.getIdcards().get(e).getCard());
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
				code.setStatus(orders.getStatus());
				listCode.add(code);
			}
		}else{
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(412, "该商品实名制信息出错! 请检查!"));
		}

		EnterpriseProduct listEnterpriseProductOrder ;          //商品  -- 企业 关系
		EnterpriseOrders enterpriseOrders;                      //企业向上级订单

		List<EnterpriseOrders> listEnterpriseOrders = new ArrayList<EnterpriseOrders>() ;//企业订单 存 集
		//用户分销轨迹
		String[] idsorder = enterpriseProduct.getUserTracks().split(",");
		List<String> userIdsListorder = new ArrayList<String>(idsorder.length);
		for (int i = idsorder.length - 1; i >= 0; i--) {
			userIdsListorder.add(idsorder[i]);
		}
		if(Long.parseLong(userIdsListorder.get(0)) != myEnterpriceId){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(413, "您没有分销该商品权限! 请检查!"));
		}else{
			//迭代分销轨迹
			Iterator<String> userIdOrder = userIdsListorder.iterator();
			while (userIdOrder.hasNext()){
				Long listUserIdOrder = Long.parseLong(userIdOrder.next());
				//预收款
				listEnterpriseProductOrder = enterpriseProductService.getOne(thirdOrders.getSproductId(),null,listUserIdOrder);
				//查询日历价格
				BigDecimal toPrice = null;
				ProductPriceVo productPriceVo = this.getProductPrice(product.getId(),DateUtil.formatDate(new Date(thirdOrders.getConsumeTim()*1000)),listUserIdOrder);
				if(!productPriceVo.getType()){
					return JSONObject.toJSONString(new ThirdOrdersMessageVo(414, "该商品没有设置价格! 请检查!"));
				}else{
					toPrice =productPriceVo.getData();
				}
				//判断是否是供应商
				if(!(listEnterpriseProductOrder.getIsSupplier()== 1)){
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProductOrder.getParentId())){

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
						enterpriseOrders.setPrice(enterpriseOrders.getFxPrice().multiply(new BigDecimal(thirdOrders.getOrderNum())));//总价
						enterpriseOrders.setProvince(product.getProvinceId());
						enterpriseOrders.setCity(product.getCityId());
						enterpriseOrders.setSupplierId(product.getEnterpriseId());
						enterpriseOrders.setSupplierName(productEnterprise.getName());
						enterpriseOrders.setShopId(product.getScenicId());// 商品所属商户Id   景区
						enterpriseOrders.setShopName(scenicService.getById(product.getScenicId()).getName());// 商品所属商户名称  景区名
						enterpriseOrders.setStatus(orders.getStatus());
						enterpriseOrders.setCreatedId(infoUser.getId());//下单人
						enterpriseOrders.setVersion(orders.getVersion());//询问
						System.out.println(enterpriseOrders.toString());
						//向“变更企业资金余额和记录”，相关数据表：

						listEnterpriseOrders.add(enterpriseOrders);
					}else{
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(415, "商品分销没有上级企业! 请检查!"));
					}
				}else{
					break;
				}
			}
		}
		
		// 修改库存
		if(product.getStoreMode() == 2){
			product.setStoreNum(product.getStoreNum() - thirdOrders.getOrderNum());
			product.setUpdateId(infoUser.getId());
			product.setUpdateTime(new Date());
		}
		/*
		 *调用第三方变接口11
		 */

		/*
		 *下单
		 * orders
		 * List<Code> codeList
		 * List<EnterpriseOrders> listEnterpriseOrders
		 *product
		 */
		ordersService.thirdSave(orders,listCode,listEnterpriseOrders,product);

		ThirdOrdersMessageVo message = new ThirdOrdersMessageVo(200, "下单成功");
		return JSONObject.toJSONString(message);
	}

	/**
	 * OTA支付
	 */
	@RequestMapping(value = "/{orderId}/pay", method = RequestMethod.POST)
	@ResponseBody
	public String pay(HttpServletRequest request, @PathVariable Long orderId) {

		// 获得订单信息
		Orders orders = ordersService.get(orderId);
		InfoUser infoUser = infoUserService.getEnterpriseMasterUser(orders.getEnterpriseId());
		if(BaseUtil.isEmpty(orders)) {
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(506, "支付失败-订单信息为空！"));
		}

		// 检查订单状态是否为10（已支付），如已经支付直接返回，提示：订单已支付！
		if(orders.getStatus() == 10){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(511, "支付失败-订单已支付！"));
		}
		
		// 获得订单对应的码信息
		List<Code> lstOrderCode = codeService.findOrderList(orderId);
		if(BaseUtil.isEmpty(lstOrderCode)) {
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(507, "支付失败-码信息为空！"));
		}
		
		// 获得商品信息
		SupplierProduct product = supplierProductService.getById(orders.getSproductId());
		if(BaseUtil.isEmpty(product)) {
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(508, "支付失败-商品信息为空！"));
		}
		
		// 检查金额是否可以支付
		// 检查各项信息，不同错误返回不同的message(code, message)
		Long myEnterpriceId = orders.getEnterpriseId();
		EnterpriseProduct enterpriseProduct = enterpriseProductService.getOne(orders.getSproductId(),null,myEnterpriceId);//商品 ,当前企业关系

		EnterpriseProduct listEnterpriseProduct ;          //商品  -- 企业 关系
		EnterpriseStorageMoneyPo enterpriseStorageMoneyPo; //预收款
		EnterpriseCapitalPo enterpriseCapitalPo;           //平台资金
		//用户分销轨迹

		orders.setStatus(10);

		for (Code code : lstOrderCode) {
			code.setStatus(orders.getStatus());
		}

		String[] ids = enterpriseProduct.getUserTracks().split(",");
		List<String> userIdsList = new ArrayList<String>(ids.length);
		for (int i = ids.length - 1; i >= 0; i--) {
			userIdsList.add(ids[i]);
		}

			//迭代分销轨迹
			Iterator<String> userId = userIdsList.iterator();
			while (userId.hasNext()){
				Long listUserId = Long.parseLong(userId.next());
				listEnterpriseProduct = enterpriseProductService.getOne(orders.getSproductId(),null,listUserId);
				//检查 检查“用户平台余额”	（分别检查自下向上购买线路上用户的余额是否够用，“预收款”或“平台余额”有一个够用可以）
				enterpriseCapitalPo = enterpriseCapitalService.getByEnterpriseId(listUserId);

				//判断是否是供应商
				if(!(listEnterpriseProduct.getIsSupplier()==1)){
					//不是 供应商 执行
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProduct.getParentId())){
						//检查预收款
						enterpriseStorageMoneyPo = enterpriseStorageMoneyService.querySearch(listUserId,listEnterpriseProduct.getParentId());

						//查询日历价格
						EnterpriseOrders enterpriseOrders = enterpriseOrdersService.getOne(orderId, listUserId, listEnterpriseProduct.getParentId());

						if(!BaseUtil.isEmpty(enterpriseStorageMoneyPo) && enterpriseStorageMoneyPo.getStorageMoney().compareTo(enterpriseOrders.getPrice()) != -1){
							//检查预收款 : 预存款关系存在 &&  预存款金额充足
						}else if((enterpriseCapitalPo.getTotalMoney().subtract(enterpriseCapitalPo.getForzenMoney())).compareTo(enterpriseOrders.getPrice()) != -1){
							//检查频台资金 :  金额充足
						}else{
							return JSONObject.toJSONString(new ThirdOrdersMessageVo(509, "支付失败-您的平台余额不足，请立即充值！"));
						}
					}else{
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(510, "支付失败-商品分销没有上级企业! 请检查!"));
					}
				}else{
					break;
				}
			}
		
		// 保存资金变动信息
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

			//迭代分销轨迹
			Iterator<String> userIdOrder = userIdsListorder.iterator();
			while (userIdOrder.hasNext()){
				Long listUserIdOrder = Long.parseLong(userIdOrder.next());

				listEnterpriseProductOrder = enterpriseProductService.getOne(orders.getSproductId(),null,listUserIdOrder);
				//预收款
				enterpriseStorageMoneyOrder = enterpriseStorageMoneyService.querySearch(listUserIdOrder,listEnterpriseProductOrder.getParentId());
				enterpriseStorageLogOrder = new EnterpriseStorageLogPo();
				//判断是否是供应商
				if(!(listEnterpriseProductOrder.getIsSupplier()== 1)){
					//是否有上级企业
					if(!BaseUtil.isEmpty(listEnterpriseProductOrder.getParentId())){
						//平台资金
						enterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listUserIdOrder);
						//上级平台资金
						parentEnterpriseCapitalOrder = enterpriseCapitalService.getByEnterpriseId(listEnterpriseProductOrder.getParentId());

						enterpriseOrders = enterpriseOrdersService.getOne(orderId, listUserIdOrder, listEnterpriseProductOrder.getParentId());
						enterpriseOrders.setStatus(orders.getStatus());
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
							enterpriseStorageLogOrder.setCreateId(infoUser.getId());
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
							enterpriseCapitalOrder.setUpdateId(infoUser.getId());
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
							parentEnterpriseCapitalOrder.setUpdateId(infoUser.getId());
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
							return JSONObject.toJSONString(new ThirdOrdersMessageVo(509, "支付失败-您的平台余额不足，请立即充值！"));
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
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(510, "支付失败-商品分销没有上级企业! 请检查!"));
					}
				}else{
					break;
				}
			}
			ordersService.thirdUpdate(orders, lstOrderCode, listEnterpriseOrders);
		
		// 修改订单状态为：10（未消费）

		// 根据 orders.getSendtype() 判断是否发短信
		if(orders.getSendtype() == 2) {
			this.sendOrdersCode(orderId);
		}
		
		// 返回码信息
		ThirdOrdersMessageVo message = new ThirdOrdersMessageVo(201, "支付成功");
		if(product.getIsRealname() == 1) {
			// 实名制码信息
			for(Code code : lstOrderCode) {
				RealCustomerVo realCustomer = JSONObject.parseObject(code.getRealName(), RealCustomerVo.class);
				message.getTouristCardPwd().add(
					new TouristCardPwdVo(realCustomer.getConsumerName(), realCustomer.getConsumerCard(), code.getCardPwd()
                ));
			}
		} else {
			// 非实名制码信息
			message.setCardPwd(lstOrderCode.get(0).getCardPwd());
		}
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * OTA退款
	 */
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	@ResponseBody
	public String refund(HttpServletRequest request, @RequestBody ThirdOrdersRefundVo thirdOrdersRefund) {
		// 判断订单是否已退款，使用 t_orders_refund 表的 orders_id + serial_number 判断 ，如果存在则返回，报“退款信息已存在！”
		Orders orders = ordersService.getByNo(thirdOrdersRefund.getOrderno());
		if(BaseUtil.isEmpty(orders)){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(610, "该订单不存在！"));
		}

		OrdersRefund ordersRefundTemp = ordersRefundService.getByOrderAndSerial(orders.getId(), thirdOrdersRefund.getRefundSeq().toString());
		if(!BaseUtil.isEmpty(ordersRefundTemp)){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(603, "退款信息已存在！"));
		}
		//定义
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
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(604, "该商品不能退款!"));
		}
		//获取今天时间和订单时间差
		if(!BaseUtil.isEmpty(product.getRefundAfterDay())){
			int shijiancha =  (int)(System.currentTimeMillis()/1000) - orders.getCreatedAt();
			if(product.getRefundTime() == 2 &&  product.getRefundAfterDay() <= (shijiancha/60/60/24)){
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(605, "该商品已过退款时间，不能退款!"));
			}
		}

		if(!BaseUtil.isEmpty(product.getRefundAfterDay()) && !BaseUtil.isEmpty(product.getRefundAfterHour()) && !BaseUtil.isEmpty(product.getRefundAfterMinute())){
			int youwanshijiancha =  (orders.getConsumeTime() - (int)(System.currentTimeMillis()/1000))/60;//获取当前时间和游玩时间差
			int shjianxianzhi = product.getRefundAfterDay()*24*60 + product.getRefundAfterHour()*60 + product.getRefundAfterMinute();//规定可退款时间差
			if(product.getRefundTime() == 3 &&  shjianxianzhi > youwanshijiancha){
				return JSONObject.toJSONString(new ThirdOrdersMessageVo(605, "该商品已过退款时间，不能退款!"));
			}
		}

		//检查可退款数量
		int refundNum = orders.getNum() - orders.getPrintnum() - orders.getLocknum() - orders.getClocknum() ;
		if(thirdOrdersRefund.getRefundQuantity() > refundNum){
			return JSONObject.toJSONString(new ThirdOrdersMessageVo(606, "最多可退数量 "+refundNum+" !"));
		}
		//边界检验
//		if((orders.getNum() - orders.getLocknum() - orders.getPrintnum() - orders.getClocknum()) < thirdOrdersRefund.getRefundQuantity()){
//			return JSONObject.toJSONString(new ThirdOrdersMessageVo(605, "该商品不符合退款条件!"));
//		}
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
						cose = new BigDecimal(thirdOrdersRefund.getRefundQuantity() ).multiply(deduct);
					}
					if (product.getServiceMode() == 3){//按单扣费
						deduct =  product.getServiceOrderCost() ;
						cose =  product.getServiceOrderCost() ;
					}
					//隐藏 手续费 比 订单 大 ---
					refund = (new BigDecimal(thirdOrdersRefund.getRefundQuantity() ).multiply(enterpriseOrders.getFxPrice())).subtract(cose);
					if(refund.compareTo(new BigDecimal(0)) == -1){
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(607, "该商品退款手续费用超出退款单费用!"));
					}
					//if(!BaseUtil.isEmpty(enterpriseParentAccountLog)){
						if(enterpriseParentAccountLog.getCapitalType() == 1){
							if((enterpriseCapitalPrent.getTotalMoney().subtract(enterpriseCapitalPrent.getForzenMoney())).compareTo(refund ) == -1){
								return JSONObject.toJSONString(new ThirdOrdersMessageVo(608, "企业资金不足,暂不支持退款!"));
							}
						}
					//}
				}else{
					return JSONObject.toJSONString(new ThirdOrdersMessageVo(609, "商品没有上级企业!"));
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
		ordersRefund.setNum(thirdOrdersRefund.getRefundQuantity() );
		ordersRefund.setNotes(thirdOrdersRefund.getRefundReason() );
		ordersRefund.setSerialNumber(thirdOrdersRefund.getRefundSeq());
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
					enterpriseOrdersRefund.setNum(thirdOrdersRefund.getRefundQuantity() );
					enterpriseOrdersRefund.setUnitPrice(enterpriseOrders.getFxPrice());//单价
					enterpriseOrdersRefund.setRefundFeeType(product.getServiceMode());//扣款模式
					if (product.getServiceMode() == 1){//无手续费用
						deduct = new BigDecimal(0);
						cose = new BigDecimal(0);
					}
					if (product.getServiceMode() == 2){//按票扣费
						deduct =product.getServiceProductCost() ;
						cose = new BigDecimal(thirdOrdersRefund.getRefundQuantity() ).multiply(deduct);
					}
					if (product.getServiceMode() == 3){//按单扣费
						deduct =  product.getServiceOrderCost() ;
						cose =  product.getServiceOrderCost() ;
					}

					refund = (new BigDecimal(thirdOrdersRefund.getRefundQuantity() ).multiply(enterpriseOrdersRefund.getUnitPrice())).subtract(cose);
					//隐藏 手续费 比 订单 大 ---
					if(refund.compareTo(new BigDecimal(0)) == -1){
						return JSONObject.toJSONString(new ThirdOrdersMessageVo(607, "该商品退款手续费用超出退款单费用!"));
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
							orders.setLocknum(orders.getLocknum() + thirdOrdersRefund.getRefundQuantity() );
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
							//实名制先看看是不是有退款的，有的话直接终止退款
							if(product.getIsRealname()== 1){
								while (codeit.hasNext()) {
									Code code = codeit.next();
									if(code.getLocknum() != 0){
										return JSONObject.toJSONString(new ThirdOrdersMessageVo(611, "该实名制订单已有退款在处理中，不能退款!"));
									}
								}
							}


							while (codeit.hasNext()){
								Code code =codeit.next();
								if(product.getIsRealname()== 1){


									code.setStatus(orders.getStatus());
									code.setLocknum(1);
									code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
									Listcode.add(code);


									/*for(String pk : thirdOrdersRefund.getCodeIds()){
										if(Long.parseLong(pk) == code.getId()){

											//边界检验
											if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
												return JSONObject.toJSONString(new ThirdOrdersMessageVo(610, "该商品不符合退款条件!"));
											}

											code.setStatus(orders.getStatus());
											code.setLocknum(1);
											code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
											Listcode.add(code);
										}
									}*/
								}
								if(product.getIsRealname()== 0){

									//边界检验
									if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < thirdOrdersRefund.getRefundQuantity() ){
										return JSONObject.toJSONString(new ThirdOrdersMessageVo(610, "该商品不符合退款条件!"));
									}

									code.setStatus(orders.getStatus());
									code.setLocknum(code.getLocknum()+thirdOrdersRefund.getRefundQuantity() );
									code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
									Listcode.add(code);
								}
							}
							fig = false;
						}
						enterpriseOrders.setStatus(orders.getStatus());
						enterpriseOrders.setLocknum(enterpriseOrders.getLocknum() +ordersRefund.getNum());
						enterpriseOrdersList.add(enterpriseOrders);

						//if(!BaseUtil.isEmpty(enterpriseParentAccountLog)){
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
									return JSONObject.toJSONString(new ThirdOrdersMessageVo(608, "企业资金不足,暂不支持退款!"));
								}
							}
						//}
					}
					if(product.getAuditMode() == 2){//需要审核
						map.put("type",2);
						if(fig){
							ordersRefund.setStatus(0);
							//订单表
							orders.setClocknum(orders.getClocknum() + thirdOrdersRefund.getRefundQuantity() );
							if(orders.getPrintnum()== 0 && orders.getPrintnum()<orders.getNum()){
								orders.setStatus(14);
							}
							if(orders.getPrintnum()> 0 && orders.getPrintnum() < orders.getNum()){
								orders.setStatus(15);
							}
							//码信息表

							//实名制先看看是不是有退款的，有的话直接终止退款
							if(product.getIsRealname()== 1){
								while (codeit.hasNext()) {
									Code code = codeit.next();
									if(code.getLocknum() != 0){
										return JSONObject.toJSONString(new ThirdOrdersMessageVo(611, "该实名制订单已有退款在处理中，不能退款!"));
									}
								}
							}
							while (codeit.hasNext()){
								Code code =codeit.next();

								if(product.getIsRealname()== 1){
									code.setStatus(orders.getStatus());
									code.setLocknum(1);
									code.setUpdatedAt((int) (System.currentTimeMillis() / 1000));
									Listcode.add(code);

									/*for(String pk : thirdOrdersRefund.getCodeIds()){
										if(Long.parseLong(pk) == code.getId()){

											//边界检验
											if(!(code.getPrintnum() == 0 && code.getLocknum() == 0 && code.getClocknum() == 0)){
												return JSONObject.toJSONString(new ThirdOrdersMessageVo(610, "该商品不符合退款条件!"));
											}
											code.setStatus(orders.getStatus());
											code.setClocknum(1);
											code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
											Listcode.add(code);
										}
									}*/

								}
								if(product.getIsRealname()== 0){

									//边界检验
									if((code.getNum() - code.getLocknum() - code.getPrintnum() - code.getClocknum()) < thirdOrdersRefund.getRefundQuantity() ){
										return JSONObject.toJSONString(new ThirdOrdersMessageVo(610, "该商品不符合退款条件!"));
									}

									code.setStatus(orders.getStatus());
									code.setClocknum(code.getClocknum()+thirdOrdersRefund.getRefundQuantity() );
									code.setUpdatedAt((int) (System.currentTimeMillis()/1000));
									Listcode.add(code);
								}
							}
							fig = false;
						}
						enterpriseOrders.setStatus(orders.getStatus());
						enterpriseOrders.setClocknum(enterpriseOrders.getClocknum() + thirdOrdersRefund.getRefundQuantity() );
						enterpriseOrdersList.add(enterpriseOrders);
						//if(!BaseUtil.isEmpty(enterpriseParentAccountLog))
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
					return JSONObject.toJSONString(new ThirdOrdersMessageVo(609, "商品没有上级企业!"));
				}
			}else{
				break;
			}
			//}
		}
			//库存
			if(product.getStoreMode() == 2){
				product.setStoreNum(product.getStoreNum() + thirdOrdersRefund.getRefundQuantity() );
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
		ThirdOrdersRefundMessageVo message = new ThirdOrdersRefundMessageVo();
		if(product.getAuditMode() == 1){//需要审核
			message = new ThirdOrdersRefundMessageVo(601, "退款成功！");
		}
		if(product.getAuditMode() == 2){//需要审核
			message = new ThirdOrdersRefundMessageVo(602, "退款审核中！");
		}
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 发码（发短信）
	 * @param orderId
	 */
	public void sendOrdersCode(Long orderId) {
		// 获得订单信息
		Orders orders = ordersService.get(orderId);
		if(BaseUtil.isEmpty(orders)) {
			log.error("发码失败-订单信息为空！");
			return;
		}
				
		// 获得订单对应的码信息
		List<Code> lstOrderCode = codeService.findOrderList(orderId);
		if(BaseUtil.isEmpty(lstOrderCode)) {
			log.error("发码失败-码信息为空！");
			return;
		}
		
		// 获得产品信息
		SupplierProduct product = supplierProductService.getById(orders.getSproductId());
		if(BaseUtil.isEmpty(product)) {
			log.error("发码失败-产品信息为空！");
			return;
		}
		
		// 根据产品-发码方式（实名制 & 非实名制）进行重新发码
		if(product.getIsRealname() == 1) {
			// 实名制发码
			OrdersCodeAction.sendOrdersCodeByRealnameProduct(orders.getTel(), lstOrderCode);
			log.info("发码成功！");
			return;
		} else {
			// 非实名制发码
			OrdersCodeAction.sendOrdersCodeBySimpleProduct(orders.getTel(), lstOrderCode.get(0));
			log.info("发码成功！");
			return;
		}
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