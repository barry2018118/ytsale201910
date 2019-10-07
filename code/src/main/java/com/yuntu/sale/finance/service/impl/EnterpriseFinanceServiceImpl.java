package com.yuntu.sale.finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.coolshow.util.DateUtils;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.finance.dao.FinanceConsumeReportDao;
import com.yuntu.sale.finance.dao.FinanceSaleReportDao;
import com.yuntu.sale.finance.dao.IEnterpriseFinanceDao;
import com.yuntu.sale.finance.service.IEnterpriseFinanceService;
import com.yuntu.sale.finance.vo.FinanceConsumeDetailsVo;
import com.yuntu.sale.finance.vo.FinanceConsumeSummaryVo;
import com.yuntu.sale.finance.vo.FinanceRefundDetailsVo;
import com.yuntu.sale.finance.vo.FinanceSaleDetailsVo;
import com.yuntu.sale.finance.vo.FinanceSaleSummaryVo;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;

/**
 * @Description 企业-财务Service实现类
 * @author snps
 * @date 2018年5月25日 上午10:54:14
 */
@Service("enterpriseFinanceService")
public class EnterpriseFinanceServiceImpl implements IEnterpriseFinanceService {

	public static final String STR_LINE = "-" ;
	public static final String STR_UNDER_LINE = "_" ;
	
	/**
	 * 根目录
	 */
	@Value("${root.path}")
	private String rootPath = "/data/yt_sale";

	/**
	 * 模板目录
	 */
	@Value("${template.path}")
	private String templatePath = "/template";

	/**
	 * 财务-汇总报表路径
	 */
	@Value("${template.finance.summary}")
	private String templateFinanceSummary = "/finance_summary_template.xlsx";

	/**
	 * 财务-明细报表路径
	 */
	@Value("${template.finance.details}")
	private String templateFinanceDetails = "/finance_details_template.xlsx";

	/**
	 * 报表导出路径
	 */
	@Value("${report.export.path}")
	private String reportExportPath;

	@Resource
	private IEnterpriseFinanceDao dao;
	
	@Resource
	private InfoEnterpriseService infoEnterpriseService;

	@Resource
	private FinanceSaleReportDao financeSaleReportDao;

	@Resource
	private FinanceConsumeReportDao financeConsumeReportDao;
	
	@Resource
	private ScenicService scenicService;
	

	@Override
	public Page<InfoEnterprise> getMyShop(Page<InfoEnterprise> pager, Long myId, Integer companyType, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoEnterprise> lstData = null;
		if (companyType == GrobalConstant.I_SHOP_SUPPLIER) {
			lstData = dao.queryMySupplier(myId, name);
		} else if (companyType == GrobalConstant.I_SHOP_DISTRIBUTOR) {
			lstData = dao.queryMyDistributor(myId, name);
		}

		// 获取分页后所需信息
		PageInfo<InfoEnterprise> pageInfo = new PageInfo<InfoEnterprise>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public String getReport(Integer searchType, long childId, long parentId, String startDate, String endDate,
			Integer searchMethod) {
		// 处理查询结束日期为参数日期+1天
		Date searchEndDate = DateUtil.addDays(DateUtil.parseToDate(endDate), 1);

		String filePath = "";
		if (searchMethod == 1) {
			// 按销售导出
			filePath = this.getSaleReport(searchType, childId, parentId, startDate, DateUtil.formatDate(searchEndDate));
		} else if (searchMethod == 2) {
			// 按消费导出
			filePath = this.getConsumeReport(searchType, childId, parentId, startDate, DateUtil.formatDate(searchEndDate));
		} else {
		}

		return filePath;
	}

	/**
	 * 得到销售报表
	 */
	public String getSaleReport(Integer searchType, long childId, long parentId, String startDate, String endDate) {
		// Step_1 查询订单销售数据
		List<FinanceSaleDetailsVo> lstOrderDetailVo = getOrderSaleData(childId, parentId, startDate, endDate);

		// Step_2 查询订单退款数据
		List<FinanceRefundDetailsVo> lstOrderRefundDetailVo = getOrderRefundData(childId, parentId, startDate, endDate);

		// Step_3 通过“销售数据”和“退款数据”生成汇总数据
		List<FinanceSaleSummaryVo> lstCheckOrderSumVo = getOrderSaleSummaryData(lstOrderDetailVo, lstOrderRefundDetailVo);

		// Step_4 对数据结果进行封装，并调用POI执行导出
		Map<Integer, List<?>> mapSheetData = new HashMap<Integer, List<?>>();
		mapSheetData.put(0, lstCheckOrderSumVo);
		mapSheetData.put(1, lstOrderDetailVo);
		mapSheetData.put(2, lstOrderRefundDetailVo);
		
		endDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseToDate(endDate), -1));
		// 构造Sheet页名称及标题
		Map<Integer, List<String>> mapSheetTitle = new HashMap<Integer, List<String>>();
		InfoEnterprise childEnterprise = infoEnterpriseService.getById(childId);
		InfoEnterprise parentEnterprise = infoEnterpriseService.getById(parentId);
		List<String> lstTitle_Sheet0 = new ArrayList<String>();
		lstTitle_Sheet0.add(childEnterprise.getName() + " 对 " + parentEnterprise.getName() + "-" + "下单总表");
		lstTitle_Sheet0.add("结算方式：销售结算           时间范围：" + startDate + " 到 " + endDate);
		mapSheetTitle.put(0, lstTitle_Sheet0);
		List<String> lstTitle_Sheet1 = new ArrayList<String>();
		lstTitle_Sheet1.add(childEnterprise.getName() + " 对 " + parentEnterprise.getName() + "-" + "销售明细表");
		lstTitle_Sheet1.add("结算方式：销售结算           时间范围：" + startDate + " 到 " + endDate);
		mapSheetTitle.put(1, lstTitle_Sheet1);
		List<String> lstTitle_Sheet2 = new ArrayList<String>();
		lstTitle_Sheet2.add(childEnterprise.getName() + " 对 " + parentEnterprise.getName() + "-" + "退款明细表");
		lstTitle_Sheet2.add("结算方式：销售结算           时间范围：" + startDate + " 到 " + endDate);
		mapSheetTitle.put(2, lstTitle_Sheet2);
		// 得到导出模板路径
		String templateFilepath = new StringBuffer("/data/yt_sale").append("/template").append("/finance-sale-report.xlsx").toString();
		// 得到文件导出路径
		StringBuffer sbuExportFilepath = new StringBuffer("/data/yt_sale").append("/report/");
		sbuExportFilepath.append(DateUtils.date2String(new Date()));
		sbuExportFilepath.append("/" + UUID.randomUUID()).append(".xlsx").toString();
		String exportFilepath = sbuExportFilepath.toString();
	
		// 调用导出报表程序
		FinanceSaleReportAssist reportAssist = new FinanceSaleReportAssist();
		reportAssist.getReport(templateFilepath, mapSheetData, exportFilepath, mapSheetTitle);
		reportExportPath = "/report/";
		return exportFilepath.substring(exportFilepath.indexOf(reportExportPath) + reportExportPath.length(), exportFilepath.length());
	}

	/**
	 * 得到订单销售数据
	 */
	private List<FinanceSaleDetailsVo> getOrderSaleData(long childId, long parentId, String startDate, String endDate) {
		// 获取数据
		List<FinanceSaleDetailsVo> lstData = financeSaleReportDao.querySaleData(parentId, childId, startDate, endDate);
		int no = 1;
		for (FinanceSaleDetailsVo data : lstData) {
			data.setNo(no++);
		}
		return lstData;
	}

	/**
	 * 得到订单退款数据
	 */
	private List<FinanceRefundDetailsVo> getOrderRefundData(long childId, long parentId, String startDate,
			String endDate) {
		// 获取数据
		List<FinanceRefundDetailsVo> lstData = financeSaleReportDao.queryRefundData(parentId, childId, startDate,
				endDate);
		int no = 1;
		for (FinanceRefundDetailsVo data : lstData) {
			data.setNo(no++);
		}
		return lstData;
	}

	/**
	 * 构造销售汇总数据
	 * @param lstOrderSaleData
	 * @param lstOrderRefundData
	 * @return List<FinanceSaleSummaryVo>
	 */
	private List<FinanceSaleSummaryVo> getOrderSaleSummaryData(List<FinanceSaleDetailsVo> lstOrderSaleData, 
			List<FinanceRefundDetailsVo> lstOrderRefundData) {
		// 声明一个Map存放信息，用于去除重复
		Map<String, FinanceSaleSummaryVo> mapProductSummary = new HashMap<String, FinanceSaleSummaryVo>();
		
       	// 遍历订单销售数据
		for(FinanceSaleDetailsVo orderSaleData : lstOrderSaleData) {
       		// 根据“商品Id + 销售单价”做唯一，进行数据合并
       		String saleProductKey = new StringBuffer("" + orderSaleData.getProductId()).append(STR_LINE).append(orderSaleData.getUnitPrice()).toString() ;
       		if(mapProductSummary.containsKey(saleProductKey)) {
       			FinanceSaleSummaryVo summaryData = mapProductSummary.get(saleProductKey) ; 
       			// 设置同一产品的累计"销售数量"
       			summaryData.setSaleAmount(summaryData.getSaleAmount() + orderSaleData.getSaleAmount()) ;
       			// 设置同一产品的累计"销售总额"
       			summaryData.setSaleTotalPrice(new BigDecimal(summaryData.getSaleTotalPrice())
       					.add(new BigDecimal(orderSaleData.getSaleTotalPrice()))
       					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;

       			// 把产品汇总数据加入到集合中
       			mapProductSummary.put(saleProductKey, summaryData) ;
       		} else {
       			FinanceSaleSummaryVo summaryData = new FinanceSaleSummaryVo() ;
       			
       			// 景区名称
       			summaryData.setScenicName(orderSaleData.getScenicName());
       			// 商品名称
       			summaryData.setProductName(orderSaleData.getProductName()) ;
       			// 销售单价
       			summaryData.setUnitPrice(orderSaleData.getUnitPrice()) ;
       			// 销售数量（应理解为：此产品在统计周期内的销售总量）
       			summaryData.setSaleAmount(orderSaleData.getSaleAmount()) ;
       			// 销售总额（等于：销售单价 * 销售数量）
       			summaryData.setSaleTotalPrice(orderSaleData.getSaleTotalPrice()) ;
       			// 退款数量
       			summaryData.setRefundAmount(0) ;
       			// 退款总额
       			summaryData.setRefundTotalPrice(0d) ;
       			// 退款手续费总额
       			summaryData.setRefundFeeTotalPrice(0d) ;
       			
       			// 把产品汇总数据加入到集合中
       			mapProductSummary.put(saleProductKey, summaryData) ;
       		}
       	}
		
		// 遍历退款订单数据
       	for(FinanceRefundDetailsVo orderRefundData : lstOrderRefundData) {
       		// 根据“产品Id + 销售单价”做唯一，进行数据合并
       		String refundProductKey = new StringBuffer("" + orderRefundData.getProductId()).append(STR_LINE).append(orderRefundData.getUnitPrice()).toString() ;
       		if(mapProductSummary.containsKey(refundProductKey)) {
       			FinanceSaleSummaryVo summaryData = mapProductSummary.get(refundProductKey) ;
       			// 设置同一产品的累计"退款数量"
       			summaryData.setRefundAmount(summaryData.getRefundAmount() + orderRefundData.getRefundAmount()) ;
       			// 设置同一产品的累计"退款总额"
       			summaryData.setRefundTotalPrice(new BigDecimal(summaryData.getRefundTotalPrice())
       					.add(new BigDecimal(orderRefundData.getReturnTotalPrice()))
       					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
       			// 设置同一产品的累计"退款手续费总额"
       			summaryData.setRefundFeeTotalPrice(new BigDecimal(summaryData.getRefundFeeTotalPrice())
       					.add(new BigDecimal(orderRefundData.getRefundFeeTotalPrice()))
       					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
       			
       			// 把产品汇总数据加入到集合中
       			mapProductSummary.put(refundProductKey, summaryData) ;
       		} else {
       			FinanceSaleSummaryVo summaryData = new FinanceSaleSummaryVo() ;
       			
       			// 景区名称
       			summaryData.setScenicName(orderRefundData.getScenicName());
       			// 商品名称
       			summaryData.setProductName(orderRefundData.getProductName()) ;
       			// 销售单价
       			summaryData.setUnitPrice(orderRefundData.getUnitPrice()) ;
       			// 销售数量（应理解为：此产品在统计周期内的销售总量）
       			summaryData.setSaleAmount(0) ;
       			// 销售总额（等于：销售单价 * 销售数量）
       			summaryData.setSaleTotalPrice(0d) ;
       			// 退款数量
       			summaryData.setRefundAmount(orderRefundData.getRefundAmount()) ;
       			// 退款总额
       			summaryData.setRefundTotalPrice(orderRefundData.getReturnTotalPrice()) ;
       			// 退款手续费总额
       			summaryData.setRefundFeeTotalPrice(orderRefundData.getRefundFeeTotalPrice()) ;
       			
       			// 把产品汇总数据加入到集合中
       			mapProductSummary.put(refundProductKey, summaryData) ;
       		}
       	}
		
		// 数据格式转换：Map<String, FinanceSaleSummaryVo> 转换为 List<FinanceSaleSummaryVo>
        List<FinanceSaleSummaryVo> lstSummaryData = new ArrayList<FinanceSaleSummaryVo>() ;
        if(BaseUtil.isEmpty(mapProductSummary)) {
        	return lstSummaryData ;
        } else {
          	// 声明汇总数据对象
        	FinanceSaleSummaryVo total = new FinanceSaleSummaryVo() ;
            
        	lstSummaryData = new ArrayList<FinanceSaleSummaryVo>(mapProductSummary.size()) ;
        	Iterator<String> itKeys = mapProductSummary.keySet().iterator() ;
        	while(itKeys.hasNext()) {
        		String key = itKeys.next() ;
        		FinanceSaleSummaryVo data = mapProductSummary.get(key) ;
        		
        		// 计算"小计"（销售总额 - 退款总额 ）
            	data.setSumPrice(new BigDecimal(data.getSaleTotalPrice()).subtract(new BigDecimal(data.getRefundTotalPrice()))
            			// .add(new BigDecimal(data.getRefundFeeTotalPrice()))
            			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
            	
            	// 计算"合计"
            	total.setSaleAmount(total.getSaleAmount() + data.getSaleAmount()) ;
            	total.setSaleTotalPrice(new BigDecimal(total.getSaleTotalPrice())
            		.add(new BigDecimal(data.getSaleTotalPrice()))
            		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
            	total.setRefundAmount(total.getRefundAmount() + data.getRefundAmount()) ;
            	total.setRefundTotalPrice(new BigDecimal(total.getRefundTotalPrice())
            		.add(new BigDecimal(data.getRefundTotalPrice()))
            		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
            	total.setRefundFeeTotalPrice(new BigDecimal(total.getRefundFeeTotalPrice())
            		.add(new BigDecimal(data.getRefundFeeTotalPrice()))
            		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
            	total.setSumPrice(new BigDecimal(total.getSumPrice()).add(new BigDecimal(data.getSumPrice()))
            		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
            	lstSummaryData.add(data) ;
        	}
            
        	// 排序
            Collections.sort(lstSummaryData) ;
        	
            // 添加序号
            int no = 1 ;
            for (FinanceSaleSummaryVo vo : lstSummaryData) {
            	vo.setNo(no++) ;
    		}
            
            // 将合计添加到底行
            lstSummaryData.add(total) ;
    		return lstSummaryData ;
        }
	}

	
	/**
	 * 得到消费报表
	 */
	public String getConsumeReport(Integer searchType, long childId, long parentId, String startDate, String endDate) {
		// Step_1 查询订单消费数据
		List<FinanceConsumeDetailsVo> lstConsumeData = getOrderConsumeData(childId, parentId, startDate, endDate);
    	
		// Step_2 遍历添加序号
		if(!BaseUtil.isEmpty(lstConsumeData)) {
			int no = 1 ;
			for(FinanceConsumeDetailsVo vo : lstConsumeData) {
				vo.setNo(no++) ;
			}
		}
		
		// Step_3 合并生成汇总数据
    	List<FinanceConsumeSummaryVo> lstConsumeSumData = getConsumeSummaryData(lstConsumeData) ;
    	
    	// Step_4 对数据结果进行封装，并调用POI执行导出
    	Map<Integer, List<?>> mapSheetData = new HashMap<Integer, List<?>>() ;
		mapSheetData.put(0, lstConsumeSumData) ;
		mapSheetData.put(1, lstConsumeData) ;
		
		endDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseToDate(endDate), -1));
    	// 构造Sheet页名称及标题
		Map<Integer, List<String>> mapSheetTitle = new HashMap<Integer, List<String>>();
		InfoEnterprise childEnterprise = infoEnterpriseService.getById(childId);
		InfoEnterprise parentEnterprise = infoEnterpriseService.getById(parentId);
		List<String> lstTitle_Sheet0 = new ArrayList<String>();
		lstTitle_Sheet0.add(childEnterprise.getName() + " 对 " + parentEnterprise.getName() + "-" + "消费总表");
		lstTitle_Sheet0.add("结算方式：消费结算           时间范围：" + startDate + " 到 " + endDate);
		mapSheetTitle.put(0, lstTitle_Sheet0);
		List<String> lstTitle_Sheet1 = new ArrayList<String>();
		lstTitle_Sheet1.add(childEnterprise.getName() + " 对 " + parentEnterprise.getName() + "-" + "消费明细表");
		lstTitle_Sheet1.add("结算方式：消费结算           时间范围：" + startDate + " 到 " + endDate);
		mapSheetTitle.put(1, lstTitle_Sheet1);
    	// 得到导出模板路径
		String templateFilepath = new StringBuffer("/data/yt_sale").append("/template").append("/finance-consume-report.xlsx").toString();
		// 得到文件导出路径
		StringBuffer sbuExportFilepath = new StringBuffer("/data/yt_sale").append("/report/");
		sbuExportFilepath.append(DateUtils.date2String(new Date()));
		sbuExportFilepath.append("/" + UUID.randomUUID()).append(".xlsx").toString();
		String exportFilepath = sbuExportFilepath.toString();
    	// 调用导出报表程序
		FinanceConsumeReportAssist reportAssist = new FinanceConsumeReportAssist();
		reportAssist.getReport(templateFilepath, mapSheetData, exportFilepath, mapSheetTitle);
		reportExportPath = "/report/";
		return exportFilepath.substring(exportFilepath.indexOf(reportExportPath) + reportExportPath.length(), exportFilepath.length());
	}
	
	/**
	 * 得到订单消费数据
	 */
	private List<FinanceConsumeDetailsVo> getOrderConsumeData(long childId, long parentId, String startDate, String endDate) {
		// 获取数据
		List<FinanceConsumeDetailsVo> lstData = financeConsumeReportDao.queryConsumeData(parentId, childId, startDate, endDate);
		int no = 1;
		for (FinanceConsumeDetailsVo data : lstData) {
			data.setNo(no++);
		}
		return lstData;
	}
	
	/**
	 * 按消费汇总数据
	 * @param FinanceConsumeDetailsVo
	 * @return List<FinanceConsumeSummaryVo>
	 */
	private List<FinanceConsumeSummaryVo> getConsumeSummaryData(List<FinanceConsumeDetailsVo> lstConsumeData) {
    	// 声明一个Map存放信息，用于去除重复
    	Map<String, FinanceConsumeSummaryVo> consumeSummaryMap = new HashMap<String, FinanceConsumeSummaryVo>() ;
    	
    	// 遍历订单消费数据
       	for(FinanceConsumeDetailsVo consumeDetailVo : lstConsumeData){//map里面有数据，求和
       		// 根据“产品Id + 销售单价”做唯一，进行数据合并
       		String consumeProductKey = new StringBuffer("" + consumeDetailVo.getProductId()).append(STR_LINE).append(consumeDetailVo.getConsumePrice()).toString() ;
       		if(consumeSummaryMap.containsKey(consumeProductKey)){
       			FinanceConsumeSummaryVo consumeSummaryVo = consumeSummaryMap.get(consumeProductKey); 
       			// //结算金额显示全款
       			consumeSummaryVo.setConsumeTotalPrice(consumeSummaryVo.getConsumeTotalPrice()+ consumeDetailVo.getConsumeTotalPrice() );
       			consumeSummaryVo.setConsumeAmount(consumeSummaryVo.getConsumeAmount()+(int)consumeDetailVo.getConsumeAmount()) ;     			
       			consumeSummaryMap.put(consumeProductKey, consumeSummaryVo) ;
       		} else {
       			FinanceConsumeSummaryVo consumeSummaryVo = new FinanceConsumeSummaryVo() ;
       			consumeSummaryVo.setScenicName(consumeDetailVo.getScenicName());
       			consumeSummaryVo.setProductName(consumeDetailVo.getProductName());
       			consumeSummaryVo.setConsumePrice(consumeDetailVo.getConsumePrice());
    			consumeSummaryVo.setConsumeAmount((int) consumeDetailVo.getConsumeAmount());
           		consumeSummaryVo.setConsumeTotalPrice(consumeDetailVo.getConsumeTotalPrice());
           		consumeSummaryMap.put(consumeProductKey, consumeSummaryVo);
       		}
       	}
       	
        List<FinanceConsumeSummaryVo> list=new ArrayList<FinanceConsumeSummaryVo>();
        //合计
        FinanceConsumeSummaryVo total = new FinanceConsumeSummaryVo();
        for (String key : consumeSummaryMap.keySet()) {
        	FinanceConsumeSummaryVo vo = consumeSummaryMap.get(key);
        	//小计
        	list.add(vo);
        	//合计
        	total.setConsumeTotalPrice(vo.getConsumeTotalPrice()+total.getConsumeTotalPrice()) ;
        	total.setConsumeAmount(vo.getConsumeAmount() +total.getConsumeAmount()) ;
        }
        
        // 排序
        Collections.sort(list) ;
        
		// 设置序号
		int no = 1 ; 
		for (FinanceConsumeSummaryVo vo : list) {
			vo.setNo(no++);
		}
		
		// 将合计添加到底行
        list.add(total) ;
		return list ;
	}
	
}