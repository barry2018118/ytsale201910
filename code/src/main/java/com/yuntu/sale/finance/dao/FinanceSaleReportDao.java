package com.yuntu.sale.finance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.finance.vo.FinanceRefundDetailsVo;
import com.yuntu.sale.finance.vo.FinanceSaleDetailsVo;

/**
 * @Description 财务对账单-销售-Dao
 * @author snps
 * @date 2018年5月31日 上午11:38:13
 */
public interface FinanceSaleReportDao {

	/**
	 * 查询订单销售数据
	 * @param parentId 上级用户Id
	 * @param childId 下级用户Id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<OrderDetailVo>
	 */
	List<FinanceSaleDetailsVo> querySaleData(@Param("parentId") long parentId, @Param("childId") long childId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate) ;
	
	/**
	 * 查询订单退款数据
	 * @param parentId 上级用户Id
	 * @param childId 下级用户Id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<OrderRefundDetailVo>
	 */
	List<FinanceRefundDetailsVo> queryRefundData(@Param("parentId") long parentId, @Param("childId") long childId,
			@Param("startDate") String startDate, @Param("endDate") String endDate) ;
	
	
}