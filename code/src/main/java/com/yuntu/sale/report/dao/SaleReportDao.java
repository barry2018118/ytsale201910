package com.yuntu.sale.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.finance.vo.FinanceRefundDetailsVo;
import com.yuntu.sale.finance.vo.FinanceSaleDetailsVo;

/**
 * @Description 销售-报表Dao
 * @author snps
 * @date 2018年6月3日 下午8:19:56
 */
public interface SaleReportDao {

	/**
	 * 查询订单销售数据
	 * @param parentId 上级用户Id
	 * @param childId 下级用户Id
	 * @param productName 商品名称
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<FinanceSaleDetailsVo>
	 */
	List<FinanceSaleDetailsVo> querySaleData(@Param("parentId") Long parentId, @Param("childId") Long childId,  
			@Param("productName") String productName, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询订单退款数据
	 * @param parentId 上级用户Id
	 * @param childId 下级用户Id
	 * @param productName 商品名称
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<FinanceRefundDetailsVo>
	 */
	List<FinanceRefundDetailsVo> queryRefundData(@Param("parentId") Long parentId, @Param("childId") Long childId, 
			@Param("productName") String productName, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
}