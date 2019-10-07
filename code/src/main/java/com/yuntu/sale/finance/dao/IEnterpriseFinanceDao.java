package com.yuntu.sale.finance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.finance.vo.FinanceDetailsReportVo;
import com.yuntu.sale.finance.vo.FinanceSummaryReportVo;
import com.yuntu.sale.user.po.InfoEnterprise;

/**
 * @Description 企业-财务Dao接口
 * @author snps
 * @date 2018年5月25日 上午10:56:12
 */
public interface IEnterpriseFinanceDao {

	/**
	 * 查询我的供应商
	 * @param myId 我的企业Id
	 * @param name 供应商名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryMySupplier(@Param("myId") Long myId, @Param("name") String name);
	
	/**
	 * 查询我的分销商
	 * @param myId 我的企业Id
	 * @param name 分销商名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryMyDistributor(@Param("myId") Long myId, @Param("name") String name);
	
	/**
	 * 查询时间范围内的销售订单汇总数据
	 * @param childId 下级企业Id
	 * @param parentId 上级企业Id
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @return List<FinanceSummaryReportVo>
	 */
	List<FinanceSummaryReportVo> querySaleOrdersBySummary(@Param("childId") Long childId, @Param("parentId") Long parentId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询时间范围内的消费订单汇总数据
	 * @param childId 下级企业Id
	 * @param parentId 上级企业Id
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @return List<FinanceSummaryReportVo>
	 */
	List<FinanceSummaryReportVo> queryConsumeOrdersBySummary(@Param("childId") Long childId, @Param("parentId") Long parentId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);

	
	/**
	 * 查询时间范围内的销售订单明细数据
	 * @param childId 下级企业Id
	 * @param parentId 上级企业Id
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @return List<FinanceDetailsReportVo>
	 */
	List<FinanceDetailsReportVo> querySaleOrdersByDetails(@Param("childId") Long childId, @Param("parentId") Long parentId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询时间范围内的消费订单明细数据
	 * @param childId 下级企业Id
	 * @param parentId 上级企业Id
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @return List<FinanceDetailsReportVo>
	 */
	List<FinanceDetailsReportVo> queryConsumeOrdersByDetails(@Param("childId") Long childId, @Param("parentId") Long parentId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
}