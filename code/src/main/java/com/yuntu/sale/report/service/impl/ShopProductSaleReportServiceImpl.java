package com.yuntu.sale.report.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtils;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.report.dao.ProductSaleReportDao;
import com.yuntu.sale.report.service.IProductSaleReportService;
import com.yuntu.sale.report.vo.ProductSaleDataVo;

/**
 * @Description 商户-商品销售报表Service接口
 * @author snps
 * @date 2018年4月27日 上午11:39:18
 */
@Service("shopProductSaleReportService")
public class ShopProductSaleReportServiceImpl implements IProductSaleReportService {

	private static final Logger log = LoggerFactory.getLogger(ShopProductSaleReportServiceImpl.class);

	@Resource
	private ProductSaleReportDao dao;
	

	/**
	 * 根目录
	 */
	@Value("${root.path}")
	private String rootPath;

	/**
	 * 模板文件路径
	 */
	@Value("${template.path}")
	private String templatePath;

	/**
	 * 按销售导出报表模板文件路径
	 */
	@Value("${template.path}")
	private String templateSaleReport;

	/**
	 * 报表导出路径
	 */
	@Value("${report.export.path}")
	private String reportExportPath;

	/**
	 * 导入报表
	 * 
	 * @return
	 */
	public String getReport(String startDate, String endDate) {
		// 打印报表导出信息
		long startTimestamp, endTimestamp;
		log.info("开始执行商品销售报表导出----------------------------------------");
		
		// Step_1 查询下单数据
		startTimestamp = System.currentTimeMillis();
		List<Object> lstData = null;
		endTimestamp = System.currentTimeMillis();
		int orderAmount = (BaseUtil.isEmpty(lstData))?0:lstData.size() ;
		log.info("得到数据用时：" + (endTimestamp - startTimestamp) + "ms，相关数据：" + orderAmount + "条！") ;
		
		// Step_2 查询退款数据
		
		// Step_3 查询消费数据
		
		// Step_4 对数据结果进行封装，并调用POI执行导出
		Map<Integer, List<?>> mapSheetData = new HashMap<Integer, List<?>>() ;
		mapSheetData.put(0, lstData) ;
		// 构造Sheet页名称及标题
		Map<Integer, List<String>> mapSheetTitle = new HashMap<Integer, List<String>>() ;
		List<String> lstTitle_Sheet0 = new ArrayList<String>() ;
    	// lstTitle_Sheet0.add(customer.getCompany() + OrderConstants.EXCEL_MINUS + reportType) ;
    	lstTitle_Sheet0.add(startDate + " 到 " + endDate) ;
    	// lstTitle_Sheet0.add(reportType) ;
    	mapSheetTitle.put(0, lstTitle_Sheet0) ;
    	// 得到导出模板路径
    	String templateFilepath = new StringBuffer(rootPath).append(templatePath).append(templateSaleReport).toString() ;
    	// 得到文件导出路径
    	StringBuffer sbuExportFilepath = new StringBuffer(rootPath).append(reportExportPath).append("/shop/sale/") ;
    	//sbuExportFilepath.append(DateUtils.date2String(new Date())) ;
    	sbuExportFilepath.append("/"+UUID.randomUUID()).append(".xlsx").toString() ;
    	String exportFilepath = sbuExportFilepath.toString() ;
    	// 调用导出报表程序
    	ShopProductSaleReportAssist reportAssist = new ShopProductSaleReportAssist() ;
    	reportAssist.getReport(templateFilepath, mapSheetData, exportFilepath, mapSheetTitle) ;
		return exportFilepath.substring(exportFilepath.indexOf(reportExportPath) + reportExportPath.length(), exportFilepath.length()) ;
	}

	@Override
	public Page<ProductSaleDataVo> getProductSaleData(Page<ProductSaleDataVo> pager, Long enterpriseId,
			String productName, Long productCategoryId, String startDate, String endDate) {

		// Step_1：查询用户的商品信息
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		
		// 查询数据
		List<ProductSaleDataVo> lstData = dao.queryOrderSaleData(enterpriseId, productName, productCategoryId, startDate, endDate);
		
		// 获取分页后所需信息
		PageInfo<ProductSaleDataVo> pageInfo = new PageInfo<ProductSaleDataVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}


}