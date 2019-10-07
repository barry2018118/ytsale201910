package com.yuntu.sale.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.finance.vo.FinanceRefundDetailsVo;
import com.yuntu.sale.finance.vo.FinanceSaleDetailsVo;
import com.yuntu.sale.report.dao.ChildReportDao;
import com.yuntu.sale.report.dao.SaleReportDao;
import com.yuntu.sale.report.service.ISaleReportService;
import com.yuntu.sale.report.vo.ChildRefundDataVo;
import com.yuntu.sale.report.vo.ChildSaleDataVo;
import com.yuntu.sale.report.vo.DistributorSaleDataSummaryVo;
import com.yuntu.sale.report.vo.SaleDataSummaryVo;

/**
 * @Description 销售-报表Service实现类
 * @author snps
 * @date 2018年6月3日 下午8:05:35
 */
@Service("saleReportService")
public class SaleReportServiceImpl implements ISaleReportService {
	
	public static final String STR_LINE = "-" ;
	public static final String STR_UNDER_LINE = "_" ;
	
	@Resource
	private SaleReportDao saleReportDao;
	
	@Resource
	private ChildReportDao childReportDao;
	

	@Override
	public List<SaleDataSummaryVo> getSaleSummaryData(Long parentId, String productName, String startDate, String endDate) {
		// Step_1 查询订单销售数据
		List<FinanceSaleDetailsVo> lstOrderDetailVo = getOrderSaleData(parentId, null, productName, startDate, endDate);

		// Step_2 查询订单退款数据
		List<FinanceRefundDetailsVo> lstOrderRefundDetailVo = getOrderRefundData(parentId, null, productName, startDate, endDate);

		// Step_3 通过“销售数据”和“退款数据”生成汇总数据
		List<SaleDataSummaryVo> lstSummaryData = getOrderSaleSummaryData(lstOrderDetailVo, lstOrderRefundDetailVo);
		return lstSummaryData;
	}

	@Override
	public List<DistributorSaleDataSummaryVo> getDistributorSaleData(Long parentId, String childName, String startDate, String endDate) {
		// Step_1 查询销售汇总数据
		List<ChildSaleDataVo> lstSaleData = childReportDao.querySaleData(parentId, childName, startDate, endDate);
		
		// Step_1 查询退款汇总数据
		List<ChildRefundDataVo> lstRefundData = childReportDao.queryRefundData(parentId, childName, startDate, endDate);
		
		// Step_3 通过“销售汇总数据”和“退款汇总数据”生成汇总数据
		List<DistributorSaleDataSummaryVo> lstSummaryData = getChildSummaryData(lstSaleData, lstRefundData);
		return lstSummaryData;
	}

	@Override
	public List<SaleDataSummaryVo> getBuySummaryData(Long childId, String productName, String startDate, String endDate) {
		// Step_1 查询订单销售数据
		List<FinanceSaleDetailsVo> lstOrderDetailVo = getOrderSaleData(null, childId, productName, startDate, endDate);

		// Step_2 查询订单退款数据
		List<FinanceRefundDetailsVo> lstOrderRefundDetailVo = getOrderRefundData(null, childId, productName, startDate, endDate);

		// Step_3 通过“销售数据”和“退款数据”生成汇总数据
		List<SaleDataSummaryVo> lstSummaryData = getOrderSaleSummaryData(lstOrderDetailVo, lstOrderRefundDetailVo);
		return lstSummaryData;
	}
	
	/**
	 * 得到订单销售数据
	 */
	private List<FinanceSaleDetailsVo> getOrderSaleData(Long parentId, Long childId, String productName, String startDate, String endDate) {
		// 获取数据
		List<FinanceSaleDetailsVo> lstData = saleReportDao.querySaleData(parentId, childId, productName, startDate, endDate);
		int no = 1;
		for (FinanceSaleDetailsVo data : lstData) {
			data.setNo(no++);
		}
		return lstData;
	}

	/**
	 * 得到订单退款数据
	 */
	private List<FinanceRefundDetailsVo> getOrderRefundData(Long parentId, Long childId, String productName, String startDate, String endDate) {
		// 获取数据
		List<FinanceRefundDetailsVo> lstData = saleReportDao.queryRefundData(parentId, childId, productName, startDate, endDate);
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
	private List<SaleDataSummaryVo> getOrderSaleSummaryData(List<FinanceSaleDetailsVo> lstOrderSaleData, List<FinanceRefundDetailsVo> lstOrderRefundData) {
		// 声明一个Map存放信息，用于去除重复
		Map<String, SaleDataSummaryVo> mapProductSummary = new HashMap<String, SaleDataSummaryVo>();
		
       	// 遍历订单销售数据
		for(FinanceSaleDetailsVo orderSaleData : lstOrderSaleData) {
       		// 根据“商品Id + 销售单价”做唯一，进行数据合并
       		String saleProductKey = new StringBuffer("" + orderSaleData.getProductId()).append(STR_LINE).append(orderSaleData.getUnitPrice()).toString() ;
       		if(mapProductSummary.containsKey(saleProductKey)) {
       			SaleDataSummaryVo summaryData = mapProductSummary.get(saleProductKey) ; 
       			// 设置同一产品的累计"销售数量"
       			summaryData.setSaleAmount(summaryData.getSaleAmount() + orderSaleData.getSaleAmount()) ;
       			// 设置同一产品的累计"销售总额"
       			summaryData.setSaleTotalPrice(new BigDecimal(summaryData.getSaleTotalPrice())
       					.add(new BigDecimal(orderSaleData.getSaleTotalPrice()))
       					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;

       			// 把产品汇总数据加入到集合中
       			mapProductSummary.put(saleProductKey, summaryData) ;
       		} else {
       			SaleDataSummaryVo summaryData = new SaleDataSummaryVo() ;
       			
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
       			SaleDataSummaryVo summaryData = mapProductSummary.get(refundProductKey) ;
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
       			SaleDataSummaryVo summaryData = new SaleDataSummaryVo() ;
       			
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
        List<SaleDataSummaryVo> lstSummaryData = new ArrayList<SaleDataSummaryVo>() ;
        if(BaseUtil.isEmpty(mapProductSummary)) {
        	return lstSummaryData ;
        } else {
        	lstSummaryData = new ArrayList<SaleDataSummaryVo>(mapProductSummary.size()) ;
        	Iterator<String> itKeys = mapProductSummary.keySet().iterator() ;
        	while(itKeys.hasNext()) {
        		String key = itKeys.next() ;
        		SaleDataSummaryVo data = mapProductSummary.get(key) ;
            	lstSummaryData.add(data) ;
        	}
            
            // 添加序号
            int no = 1 ;
            for (SaleDataSummaryVo vo : lstSummaryData) {
            	vo.setNo(no++) ;
    		}
    		return lstSummaryData ;
        }
	}
	
	
	/**
	 * 构造分销商销售&退款汇总数据
	 * @param lstOrderSaleData
	 * @param lstOrderRefundData
	 * @return List<DistributorSaleDataSummaryVo>
	 */
	private List<DistributorSaleDataSummaryVo> getChildSummaryData(List<ChildSaleDataVo> lstSaleData, List<ChildRefundDataVo> lstRefundData) {
		// 声明一个Map存放信息，用于去除重复
		Map<String, DistributorSaleDataSummaryVo> mapProductSummary = new HashMap<String, DistributorSaleDataSummaryVo>();
		
       	// 遍历订单销售数据
		for(ChildSaleDataVo orderSaleData : lstSaleData) {
			if(!BaseUtil.isEmpty(orderSaleData)) {
				// 根据“商品Id + 销售单价”做唯一，进行数据合并
	       		String saleChildKey = new StringBuffer("" + orderSaleData.getChildId()).toString() ;
	       		if(mapProductSummary.containsKey(saleChildKey)) {
	       			DistributorSaleDataSummaryVo summaryData = mapProductSummary.get(saleChildKey) ; 
	       			// 设置同一产品的累计"销售数量"
	       			summaryData.setSaleAmount(summaryData.getSaleAmount() + orderSaleData.getSaleAmount()) ;
	       			// 设置同一产品的累计"销售总额"
	       			summaryData.setSaleTotalPrice(new BigDecimal(summaryData.getSaleTotalPrice())
	       					.add(new BigDecimal(orderSaleData.getSaleTotalPrice()))
	       					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;

	       			// 把产品汇总数据加入到集合中
	       			mapProductSummary.put(saleChildKey, summaryData) ;
	       		} else {
	       			DistributorSaleDataSummaryVo summaryData = new DistributorSaleDataSummaryVo() ;
	       			
	       			// 分销商
	       			summaryData.setEnterpriseName(orderSaleData.getChildName());
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
	       			mapProductSummary.put(saleChildKey, summaryData) ;
	       		}
			}
       	}
		
		// 遍历退款订单数据
       	for(ChildRefundDataVo orderRefundData : lstRefundData) {
       		if(!BaseUtil.isEmpty(orderRefundData)) {
       		// 根据“产品Id + 销售单价”做唯一，进行数据合并
           		String refundChildKey = new StringBuffer("" + orderRefundData.getChildId()).toString() ;
           		if(mapProductSummary.containsKey(refundChildKey)) {
           			DistributorSaleDataSummaryVo summaryData = mapProductSummary.get(refundChildKey) ;
           			// 设置同一产品的累计"退款数量"
           			summaryData.setRefundAmount(summaryData.getRefundAmount() + orderRefundData.getRefundAmount()) ;
           			// 设置同一产品的累计"退款总额"
           			summaryData.setRefundTotalPrice(new BigDecimal(summaryData.getRefundTotalPrice())
           					.add(new BigDecimal(orderRefundData.getRefundTotalPrice()))
           					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
           			// 设置同一产品的累计"退款手续费总额"
           			summaryData.setRefundFeeTotalPrice(new BigDecimal(summaryData.getRefundFeeTotalPrice())
           					.add(new BigDecimal(orderRefundData.getRefundFeeTotalPrice()))
           					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) ;
           			
           			// 把产品汇总数据加入到集合中
           			mapProductSummary.put(refundChildKey, summaryData) ;
           		} else {
           			DistributorSaleDataSummaryVo summaryData = new DistributorSaleDataSummaryVo() ;
           			
           			// 分销商
           			summaryData.setEnterpriseName(orderRefundData.getChildName());
           			// 销售数量（应理解为：此产品在统计周期内的销售总量）
           			summaryData.setSaleAmount(0) ;
           			// 销售总额（等于：销售单价 * 销售数量）
           			summaryData.setSaleTotalPrice(0d) ;
           			// 退款数量
           			summaryData.setRefundAmount(orderRefundData.getRefundAmount()) ;
           			// 退款总额
           			summaryData.setRefundTotalPrice(orderRefundData.getRefundTotalPrice()) ;
           			// 退款手续费总额
           			summaryData.setRefundFeeTotalPrice(orderRefundData.getRefundFeeTotalPrice()) ;
           			
           			// 把产品汇总数据加入到集合中
           			mapProductSummary.put(refundChildKey, summaryData) ;
           		}
       		}
       	}
		
		// 数据格式转换：Map<String, FinanceSaleSummaryVo> 转换为 List<FinanceSaleSummaryVo>
        List<DistributorSaleDataSummaryVo> lstSummaryData = new ArrayList<DistributorSaleDataSummaryVo>() ;
        if(BaseUtil.isEmpty(mapProductSummary)) {
        	return lstSummaryData ;
        } else {
        	lstSummaryData = new ArrayList<DistributorSaleDataSummaryVo>(mapProductSummary.size()) ;
        	Iterator<String> itKeys = mapProductSummary.keySet().iterator() ;
        	while(itKeys.hasNext()) {
        		String key = itKeys.next() ;
        		DistributorSaleDataSummaryVo data = mapProductSummary.get(key) ;
            	lstSummaryData.add(data) ;
        	}
            
            // 添加序号
            int no = 1 ;
            for (DistributorSaleDataSummaryVo vo : lstSummaryData) {
            	vo.setNo(no++) ;
    		}
    		return lstSummaryData ;
        }
	}
	
}