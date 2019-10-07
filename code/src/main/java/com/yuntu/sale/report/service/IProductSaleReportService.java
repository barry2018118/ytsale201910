package com.yuntu.sale.report.service;

import com.coolshow.util.Page;
import com.yuntu.sale.report.vo.ProductSaleDataVo;

/**
 * @Description 商品销售报表Service接口
 * @author snps
 * @date 2018年4月27日 上午11:34:59
 */
public interface IProductSaleReportService {

	/**
	 * 得到商品销售信息
	 * @param pager 分页对象 
	 * @param enterpriseId 企业Id
	 * @param productName 商品名称
	 * @param productCategoryId 商品类型Id
	 * @param startDate 统计开始日期
	 * @param endDate 统计结束日期
	 * @return Page<ProductSaleDataVo>
	 */
	Page<ProductSaleDataVo> getProductSaleData(Page<ProductSaleDataVo> pager, 
			Long enterpriseId, String productName, Long productCategoryId, String startDate, String endDate);
	
}