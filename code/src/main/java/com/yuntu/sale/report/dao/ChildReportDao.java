package com.yuntu.sale.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.report.vo.ChildRefundDataVo;
import com.yuntu.sale.report.vo.ChildSaleDataVo;

/**
 * @Description 分销商销售-报表Dao
 * @author snps
 * @date 2018年6月3日 下午10:28:33
 */
public interface ChildReportDao {

	/**
	 * 查询分销商销售汇总数据
	 * @param parentId 上级企业Id
	 * @param childName 下级企业名称
	 * @param startDate 统计开始时间
	 * @param endDate 统计截止时间
	 * @return List<ChildSaleDataVo>
	 */
	List<ChildSaleDataVo> querySaleData(@Param("parentId") Long parentId, @Param("childName") String childName, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询分销商退款汇总数据
	 * @param parentId 上级企业Id
	 * @param childName 下级企业名称
	 * @param startDate 统计开始时间
	 * @param endDate 统计截止时间
	 * @return List<ChildRefundDataVo>
	 */
	List<ChildRefundDataVo> queryRefundData(@Param("parentId") Long parentId, @Param("childName") String childName, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
}