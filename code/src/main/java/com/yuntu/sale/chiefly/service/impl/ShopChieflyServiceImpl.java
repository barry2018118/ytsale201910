package com.yuntu.sale.chiefly.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.chiefly.dao.ShopChieflyDao;
import com.yuntu.sale.chiefly.service.IShopChieflyService;
import com.yuntu.sale.chiefly.vo.ScenicSaleColumnChartVo;

/**
 * @Description 商户首页信息Service实现类
 * @author snps
 * @date 2018年5月9日 下午2:56:01
 */
@Service("shopChieflyService")
public class ShopChieflyServiceImpl implements IShopChieflyService {

	@Resource
	private ShopChieflyDao dao;
	
	
	@Override
	public List<ScenicSaleColumnChartVo> getTopScenicSaleData(Long enterpriseId, int topNum, Date startDate, Date endDate) {
		return dao.queryTopScenicSaleData(enterpriseId, topNum, startDate, endDate);
	}

	@Override
	public List<ScenicSaleColumnChartVo> getTopDistributionSaleData(Long enterpriseId, int topNum, Date startDate, Date endDate) {
		return dao.queryTopDistributionSaleData(enterpriseId, topNum, startDate, endDate);
	}

}