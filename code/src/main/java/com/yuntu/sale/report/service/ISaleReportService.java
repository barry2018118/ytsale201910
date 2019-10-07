package com.yuntu.sale.report.service;

import java.util.List;

import com.yuntu.sale.report.vo.DistributorSaleDataSummaryVo;
import com.yuntu.sale.report.vo.SaleDataSummaryVo;

/**
 * @Description 销售-报表Service接口 
 * @author snps
 * @date 2018年6月3日 下午7:40:00
 */
public interface ISaleReportService {

	/**
	 * 得到销售汇总报表数据（下级向我购买的）
	 * @param parentId 上级企业Id
	 * @param productName 商品名称
	 * @param startDate 统计开始时间
	 * @param endDate 统计截止时间
	 * @return List<SaleDataSummaryVo>
	 */
	List<SaleDataSummaryVo> getSaleSummaryData(Long parentId, String productName, String startDate, String endDate);
	
	/**
	 * 得到分销商销售报表（我的下级分销商相关的）
	 * @param parentId 上级企业Id
	 * @param childName 下级企业名称
	 * @param startDate 统计开始时间
	 * @param endDate 统计截止时间
	 * @return List<DistributorSaleDataSummaryVo>
	 */
	List<DistributorSaleDataSummaryVo> getDistributorSaleData(Long parentId, String childName, String startDate, String endDate);
	
	/**
	 * 得到采购汇总表表（我采购的）
	 * @param childId 下级企业Id
	 * @param productName 商品名称
	 * @param startDate 统计开始时间
	 * @param endDate 统计截止时间
	 * @return List<SaleDataSummaryVo>
	 */
	List<SaleDataSummaryVo> getBuySummaryData(Long childId, String productName, String startDate, String endDate);
	
}