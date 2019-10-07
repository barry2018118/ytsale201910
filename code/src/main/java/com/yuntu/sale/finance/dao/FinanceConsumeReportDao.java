package com.yuntu.sale.finance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.finance.vo.FinanceConsumeDetailsVo;

/**
 * @Description 财务对账单-销售-Dao 
 * @author snps
 * @date 2018年5月31日 上午11:38:34
 */
public interface FinanceConsumeReportDao {

	/**
	 * 查询订单消费数据
	 * @param parentId 上级用户Id
	 * @param childId 下级用户Id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<FinanceConsumeDetailsVo>
	 */
	List<FinanceConsumeDetailsVo> queryConsumeData(@Param("parentId") long parentId, @Param("childId") long childId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate) ;
	
}