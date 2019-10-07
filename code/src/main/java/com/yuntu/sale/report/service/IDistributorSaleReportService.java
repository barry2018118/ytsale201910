package com.yuntu.sale.report.service;

import com.coolshow.util.Page;
import com.yuntu.sale.report.vo.DistributorSaleDataVo;

/**
 * @Description 分销商销售报表Service接口 
 * @author snps
 * @date 2018年4月27日 上午11:37:17
 */
public interface IDistributorSaleReportService {

	/**
	 * 得到分销商销售信息
	 * @param pager 分页对象 
	 * @param enterpriseId 我的企业Id
	 * @param childName 分销商名称
	 * @param startDate 统计开始日期
	 * @param endDate 统计结束日期
	 * @return Page<DistributorSaleDataVo>
	 */
	Page<DistributorSaleDataVo> getSaleData(Page<DistributorSaleDataVo> pager, Long enterpriseId, 
			String childName, String startDate, String endDate);
	
}