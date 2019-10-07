package com.yuntu.sale.report.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.report.dao.OtaSaleReportDao;
import com.yuntu.sale.report.service.IOtaSaleReportService;

/**
 * @Description 商户-OTA销售报表Service接口
 * @author snps
 * @date 2018年4月27日 上午11:42:39
 */
@Service("shopOtaSaleReportService")
public class ShopOtaSaleReportServiceImpl implements IOtaSaleReportService {

	@Resource
	private OtaSaleReportDao dao;
	
	
	
}