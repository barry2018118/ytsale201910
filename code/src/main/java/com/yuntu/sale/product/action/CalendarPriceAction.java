package com.yuntu.sale.product.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.CalendarPriceService;
import com.yuntu.sale.product.service.SaleGroupService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;

@Controller
@RequestMapping(value = {"/product/price"})
public class CalendarPriceAction extends BaseAction {
	
	@Autowired
	private SupplierProductService supplierProductService;
	
	@Autowired
	private SaleGroupService saleGroupService;
	
	@Autowired
	private CalendarPriceService calendarPriceService;
	
	/**
	 * 进入查询成本价页面
	 */
	@RequestMapping(value = "/query/cost", method = RequestMethod.GET)
	public String costQuery(Long sproductId,Model model) {
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		return "/product/price/cost_query" ;
	}
	
	/**
	 * 进入设置成本价页面
	 */
	@RequestMapping(value = "/setting/cost", method = RequestMethod.GET)
	public String costSetting(Long sproductId,Model model) {
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		return "/product/price/cost_setting" ;
	}
	
	/**
	 * 按月输出日历成本价
	 */
	@RequestMapping(value = "/load/cost/month", method = RequestMethod.GET)
	@ResponseBody
	public String getCostByMonth(Long sproductId,String month,Model model) {
		String priceData=calendarPriceService.supplierProductCostByMonth(sproductId, month);
		return priceData ;
	}
	
	/**
	 * 按月输出日历成本价
	 */
	@RequestMapping(value = "/load/enterprise/cost/month", method = RequestMethod.GET)
	@ResponseBody
	public String getEnterpriseCostByMonth(Long sproductId,String month,Model model) {
		InfoUser user=super.getCurrentUser();
		String priceData=calendarPriceService.productCostByMonth(sproductId, month,user.getEnterpriseId());
		return priceData ;
	}
	
	/**
	 * 某天购买成本
	 */
	@RequestMapping(value = "/load/cost/day", method = RequestMethod.GET)
	@ResponseBody
	public String getCostByDay(Long sproductId,String date,Model model) {
		InfoUser user=super.getCurrentUser();
		String priceData=calendarPriceService.productCostByDate(sproductId, date, user.getEnterpriseId());
		return priceData ;
	}
	
	/**
	 * 按天设置成本价
	 */
	@RequestMapping(value = "/setting/cost/day", method = RequestMethod.POST)
	@ResponseBody
	public String addCostByDay(Long sproductId,
								Double costPrice,
								String date,
										Model model) {
		Map<String,String> params=Maps.newHashMap();
		params.put("cost_price", costPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("date", date);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		String priceData=calendarPriceService.addDayCost(params);
		return priceData ;
	}
	
	/**
	 * 按区间设置成本价
	 */
	@RequestMapping(value = "/setting/cost/section", method = RequestMethod.POST)
	@ResponseBody
	public String addCostBySection(Long sproductId,
								String week,
								Double costPrice,
								Integer type,
								String startDate,
								String endDate,
								Model model) {
		String ckMsg=check( startDate, endDate);
		if(!StringUtils.isEmpty(ckMsg)) {
			return ckMsg;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("week", week);
		params.put("type", type+"");
		params.put("cost_price", costPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("start_date", startDate);
		params.put("end_date", endDate);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		
		String priceData=calendarPriceService.addSectionCost(params);
		return priceData ;
	}
	
	/**
	 * 进入查询利润价页面
	 */
	@RequestMapping(value = "/query/profit", method = RequestMethod.GET)
	public String profitQuery(Long sproductId,Long groupId,Model model) {
		//产品
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		//商品组
		SaleGroup saleGroup =saleGroupService.getById(groupId);
		model.addAttribute("saleGroup", saleGroup);
		return "/product/price/profit_query" ;
	}
	
	/**
	 * 进入设置利润价页面
	 */
	@RequestMapping(value = "/setting/profit", method = RequestMethod.GET)
	public String profitSetting(Long sproductId,Long groupId,Model model) {
		//产品
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		//商品组
		SaleGroup saleGroup =saleGroupService.getById(groupId);
		model.addAttribute("saleGroup", saleGroup);
		return "/product/price/profit_setting" ;
	}
	
	/**
	 * 按月输出成本价&利润价
	 */
	@RequestMapping(value = "/load/profit/month", method = RequestMethod.GET)
	@ResponseBody
	public String getProfitByMonth(Long sproductId,Long groupId,String month,Model model) {
		InfoUser user=super.getCurrentUser();
		String priceData=calendarPriceService.productCostAndProfitByMonth(sproductId, groupId, month,user.getEnterpriseId());
		return priceData ;
	}
	
	/**
	 * 按天设置利润价
	 */
	@RequestMapping(value = "/setting/profit/day", method = RequestMethod.POST)
	@ResponseBody
	public String addProfitByDay(Long sproductId,
								Long groupId,
								Double profitPrice,
								String date,
								Model model) {
		Map<String,String> params=Maps.newHashMap();
		params.put("profit_price", profitPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("group_id", groupId+"");
		params.put("date", date);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		String priceData=calendarPriceService.addDayProfit(params);
		return priceData ;
	}
	
	/**
	 * 按区间设置利润价
	 */
	@RequestMapping(value = "/setting/profit/section", method = RequestMethod.POST)
	@ResponseBody
	public String addProfitBySection(Long sproductId,
								Long groupId,
								String week,
								Double profitPrice,
								Integer type,
								String startDate,
								String endDate,
								Model model) {
		String ckMsg=check( startDate, endDate);
		if(!StringUtils.isEmpty(ckMsg)) {
			return ckMsg;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("week", week);
		params.put("type", type+"");
		params.put("profit_price", profitPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("group_id", groupId+"");
		params.put("start_date", startDate);
		params.put("end_date", endDate);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		
		String priceData=calendarPriceService.addSectionProfit(params);
		return priceData ;
	}
	
	private String check(String startDate,String endDate) {
		if(StringUtils.isEmpty(startDate)||StringUtils.isEmpty(endDate)) {
			return "{\"success\":\"false\",\"msg\":\"结束日期必须大于开始日期！\"}";
		}
		if(startDate.compareTo(endDate)>0) {
			return "{\"success\":\"false\",\"msg\":\"结束日期必须大于开始日期！\"}";
		}
		return null;
	}
	
	
	/**
	 * 进入查询OTA售卖价页面
	 */
	@RequestMapping(value = "/query/ota", method = RequestMethod.GET)
	public String otaQuery(Long sproductId,Model model) {
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		return "/product/price/ota_query" ;
	}
	
	/**
	 * 进入设置OTA售卖价页面
	 */
	@RequestMapping(value = "/setting/ota", method = RequestMethod.GET)
	public String otaSetting(Long sproductId,Model model) {
		SupplierProduct supplierProduct=supplierProductService.getById(sproductId);
		model.addAttribute("supplierProduct", supplierProduct);
		return "/product/price/ota_setting" ;
	}
	
	/**
	 * 按月输出OTA售卖价
	 */
	@RequestMapping(value = "/load/enterprise/ota/month", method = RequestMethod.GET)
	@ResponseBody
	public String getEnterpriseOtaByMonth(Long sproductId,String month,Model model) {
		InfoUser user=super.getCurrentUser();
		String priceData=calendarPriceService.productOtaByMonth(sproductId, month,user.getEnterpriseId());
		return priceData ;
	}
	
	
	/**
	 * 按区间设置OTA售卖价
	 */
	@RequestMapping(value = "/setting/ota/section", method = RequestMethod.POST)
	@ResponseBody
	public String addOtaBySection(Long sproductId,
								String week,
								Double otaPrice,
								Integer type,
								String startDate,
								String endDate,
								Model model) {
		String ckMsg=check( startDate, endDate);
		if(!StringUtils.isEmpty(ckMsg)) {
			return ckMsg;
		}
		Map<String,String> params=Maps.newHashMap();
		params.put("week", week);
		params.put("type", type+"");
		params.put("ota_price", otaPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("start_date", startDate);
		params.put("end_date", endDate);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		params.put("enterprise_id", user.getEnterpriseId()+"");
		String priceData=calendarPriceService.addSectionOta(params);
		return priceData ;
	}
	
	/**
	 * 按天设置OTA售卖价
	 */
	@RequestMapping(value = "/setting/ota/day", method = RequestMethod.POST)
	@ResponseBody
	public String addOtaByDay(Long sproductId,
								Double otaPrice,
								String date,
										Model model) {
		Map<String,String> params=Maps.newHashMap();
		params.put("ota_price", otaPrice+"");
		params.put("sproduct_id", sproductId+"");
		params.put("date", date);
		
		InfoUser user=super.getCurrentUser();
		params.put("created_id", user.getId()+"");
		params.put("enterprise_id", user.getEnterpriseId()+"");
		String priceData=calendarPriceService.addDayOta(params);
		return priceData ;
	}
}
