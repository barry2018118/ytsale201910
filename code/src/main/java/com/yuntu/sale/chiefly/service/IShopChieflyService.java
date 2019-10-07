package com.yuntu.sale.chiefly.service;

import java.util.Date;
import java.util.List;

import com.yuntu.sale.chiefly.vo.ScenicSaleColumnChartVo;

/**
 * @Description 商户首页信息Service接口
 * @author snps
 * @date 2018年5月9日 下午2:38:26
 */
public interface IShopChieflyService {

	/**
	 * 得到销量最好的前N个景区
	 * @param enterpriseId 我的企业Id
	 * @param topNum 查询数量
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return List<ScenicSaleColumnChartVo>
	 */
	List<ScenicSaleColumnChartVo> getTopScenicSaleData(Long enterpriseId, int topNum, Date startDate, Date endDate);
	
	/**
	 * 得到销量最好的前N个分销商
	 * @param enterpriseId 我的企业Id
	 * @param topNum 查询数量
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return List<ScenicSaleColumnChartVo>
	 */
	List<ScenicSaleColumnChartVo> getTopDistributionSaleData(Long enterpriseId, int topNum, Date startDate, Date endDate);
	
}