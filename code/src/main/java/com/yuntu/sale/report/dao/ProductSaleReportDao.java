package com.yuntu.sale.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.report.vo.ProductSaleDataVo;
import com.yuntu.sale.report.vo.SimpleProductVo;

/**
 * @Description 商品销售报表Dao接口
 * @author snps
 * @date 2018年4月27日 上午11:50:29
 */
public interface ProductSaleReportDao {

	/**
	 * 查询我的商品信息
	 * @param enterpriseId 我的公司Id
	 * @param productName 商品名称
	 * @param productCategory 商品类别Id
	 * @return
	 */
	List<SimpleProductVo> queryMyProduct(@Param("enterpriseId") Long enterpriseId, @Param("productName") String productName, 
			@Param("productCategoryId") Long productCategoryId);

	/**
	 * 查询商品销售数据
	 * @param enterpriseId 我的企业Id
	 * @param productName 商品名称
	 * @param productCategoryId 商品类别Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return List<ProductSaleDataVo>
	 */
	List<ProductSaleDataVo> queryOrderSaleData(@Param("enterpriseId") Long enterpriseId, @Param("productName") String productName,
			@Param("productCategoryId") Long productCategoryId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询商品消费数据
	 * @param enterpriseId 购买商品的企业Id
	 * @param productId 商品Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return ProductSaleDataVo
	 */
	ProductSaleDataVo queryOrderConsumeData(@Param("enterpriseId") Long enterpriseId, @Param("productId") Long productId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询商品退换数据
	 * @param enterpriseId 购买商品的企业Id
	 * @param productId 商品Id
	 * @param startDate 查询起始时间
	 * @param endDate 查询截止时间
	 * @return ProductSaleDataVo
	 */
	ProductSaleDataVo queryOrderRefundData(@Param("enterpriseId") Long enterpriseId, @Param("productId") Long productId, 
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
}