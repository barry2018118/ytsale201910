package com.yuntu.sale.product.service;

import java.util.Map;

/**
 * @Description 日历价格 Service接口
 * @author Jack.jiang
 * @version 2018-04-17
 */
public interface CalendarPriceService {
	
	/**
	 * 按区间设置成本价格
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addSectionCost(Map<String,String> params);
	
	/**
	 * 按天设置成本价格
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addDayCost(Map<String,String> params);
	
	/**
	 * 按区间设置利润
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addSectionProfit(Map<String,String> params);
	
	/**
	 * 按天设置利润
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addDayProfit(Map<String,String> params);
	
	/**
	 * 批量设置利润
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String batchAddProfit(Map<String,String> params);

	/**
	 * 按月份显示供应商日历成本价格
	 * @param sproductId 产品Id
	 * @param month 某月（日期格式yyyy-MM）
	 * @return Json 字符串
	 */
	String supplierProductCostByMonth(Long sproductId,String month);
	
	/**
	 * 按月份显示产品日历成本价格及利润
	 * @param sproductId 产品Id
	 * @param groupId 产品分销组Id
	 * @param month 某月（日期格式yyyy-MM）
	 * @return Json 字符串
	 */
	String productCostAndProfitByMonth(Long sproductId,Long groupId,String month,Long enterpriseId);
	
	/**
	 * 按月份显示分销商购买价格
	 * @param sproductId 产品Id
	 * @param month 某月（日期格式yyyy-MM）
	 * @return Json 字符串
	 */
	String productCostByMonth(Long sproductId,String month,Long enterpriseId);
	
	/**
	 * 查询分销商某天购买价格
	 * @param sproductId 产品Id
	 * @param date 某天（日期格式yyyy-MM-dd）
	 * @return Json 字符串
	 */
	String productCostByDate(Long sproductId,String date,Long enterpriseId);
	
	/**
	 * 按区间设置OTA售卖价格
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addSectionOta(Map<String,String> params);
	
	/**
	 * 按天设置OTA售卖价格
	 * @param params 设置参数
	 * @return Json 字符串
	 */
	String addDayOta(Map<String,String> params);
	
	/**
	 * 按月份显示OTA售卖价格
	 * @param sproductId 产品Id
	 * @param month 某月（日期格式yyyy-MM）
	 * @return Json 字符串
	 */
	String productOtaByMonth(Long sproductId,String month,Long enterpriseId);

	
	
	
}
