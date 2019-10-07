package com.yuntu.sale.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.report.vo.DistributorSaleDataVo;
import com.yuntu.sale.report.vo.SimpleEnterpriseVo;

/**
 * @Description 分销商销售报表Dao接口
 * @author snps
 * @date 2018年4月27日 上午11:50:55
 */
public interface DistributorSaleReportDao {

	/**
	 * 查询我的下级分销商
	 * @param parentId 上级企业Id
	 * @param name 下级分销商名称
	 * @return List<SimpleEnterpriseVo>
	 */
	List<SimpleEnterpriseVo> queryMyChild(@Param("parentId") Long parentId, @Param("childName")String childName);
	
	/**
	 * 查询销售数据
	 * @param parentId 上级Id
	 * @param childId 下级Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return
	 */
	DistributorSaleDataVo queryOrderSaleData(@Param("parentId") Long parentId, @Param("childId") Long childId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询消费数据
	 * @param parentId 上级Id
	 * @param childId 下级Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return DistributorSaleDataVo
	 */
	DistributorSaleDataVo queryOrderConsumeData(@Param("parentId") Long parentId, @Param("childId") Long childId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询退换数据
	 * @param parentId 上级Id
	 * @param childId 下级Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return DistributorSaleDataVo
	 */
	DistributorSaleDataVo queryOrderRefundData(@Param("parentId") Long parentId, @Param("childId") Long childId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
}