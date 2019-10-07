package com.yuntu.sale.chiefly.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.chiefly.vo.ScenicSaleColumnChartVo;

/**
 * @Description 商户首页信息Dao
 * @author snps
 * @date 2018年5月9日 下午2:58:23
 */
public interface ShopChieflyDao {

	/**
	 * 查询销量最好的前N个景区
	 * @param enterpriseId 我的企业Id
	 * @param topNum 查询数量
	 * @param startDate 开始日期
	 * @param endDate 截止日期
	 * @return List<ScenicSaleColumnChartVo>
	 */
	List<ScenicSaleColumnChartVo> queryTopScenicSaleData(@Param("enterpriseId") Long enterpriseId, @Param("topNum") int topNum,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * 查询销量最好的前N个分销商
	 * @param enterpriseId 我的企业Id
	 * @param topNum 查询数量
	 * @param startDate 开始日期
	 * @param endDate 截止日期
	 * @return List<ScenicSaleColumnChartVo>
	 */
	List<ScenicSaleColumnChartVo> queryTopDistributionSaleData(@Param("enterpriseId") Long enterpriseId, @Param("topNum") int topNum,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}