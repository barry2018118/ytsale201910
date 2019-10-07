package com.yuntu.sale.finance.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.coolshow.util.ExcelUtils;
import com.coolshow.util.JavabeanUtils;

/**
 * @Description 财务对账单-销售-报表导出辅助类 
 * @author snps
 * @date 2018年5月31日 上午11:02:18
 */
public class FinanceSaleReportAssist {

	/**
	 * Sheet页集合 
	 */
	private Map<Integer, Map<String, Integer>> map_sheets_config = null ;

	/**
	 * 各Sheet页（与模板文件中的Sheet对应）
	 */
	private Map<String, Integer> map_sheet_0 = null ;
	private Map<String, Integer> map_sheet_1 = null ;
	private Map<String, Integer> map_sheet_2 = null ;
	
	private Map<String, Integer> getSheetConfig(int sheetIndex) {
		if(sheetIndex >= map_sheets_config.size()) {
			return null ;
		}
		
		return map_sheets_config.get(sheetIndex) ;
	}
	
	/**
	 * 导出文件
	 * @param templateFilePath 模板文件位置
	 * @param mapData 要导出的数据
	 * @param exportFilePath 导出文件要保存的位置
	 * @param titleMap 标题和sheet页名称
	 * @return 导出后的文件路径
	 */
	public String getReport(String templateFilePath, Map<Integer, List<? extends Object>> mapData, String exportFilePath, Map<Integer,List<String>> titleMap) {
		try {
			Workbook wb = ExcelUtils.getNewWorkBook(templateFilePath) ;
			
			// 导出第0个Sheet页内容
			Sheet sheet_0 = wb.getSheetAt(0) ;
			List<Map<String, Object>> mapSheet0Data = JavabeanUtils.listBean2ListMap(mapData.get(0)) ;
			this.exportSheet0Data(sheet_0, mapSheet0Data, this.getSheetConfig(0), titleMap.get(0)) ;
			
			// 导出第1个Sheet页内容
			Sheet sheet_1 = wb.getSheetAt(1) ;
			List<Map<String, Object>> mapSheet1Data = JavabeanUtils.listBean2ListMap(mapData.get(1)) ;
			this.exportSheet1Data(sheet_1, mapSheet1Data, this.getSheetConfig(1), titleMap.get(1)) ;
			
			// 导出第2个Sheet页内容
			Sheet sheet_2 = wb.getSheetAt(2) ;
			List<Map<String, Object>> mapSheet2Data = JavabeanUtils.listBean2ListMap(mapData.get(2)) ;
			this.exportSheet2Data(sheet_2, mapSheet2Data, this.getSheetConfig(2), titleMap.get(2)) ;
			
			//创建文件夹
			String filepath = exportFilePath.substring(0,exportFilePath.lastIndexOf("/"));
			File file = new File(filepath);
			file.mkdirs();
			// 导出文件
			FileOutputStream fos = new FileOutputStream(exportFilePath) ;
			ExcelUtils.writeAndRelease(wb, fos) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return exportFilePath ;
	}
	
	/**
	 * 导出第一个Sheet页的数据
	 * @param sheet
	 * @param lstData
	 * @param mapColumnIndexConfig
	 * @param lstTitle 
	 */
	private void exportSheet0Data(Sheet sheet, List<Map<String, Object>> lstData, Map<String, Integer> mapColumnIndexConfig, List<String> lstTitle) {
		// 写表头
		sheet.getRow(0).getCell(0).setCellValue(lstTitle.get(0)) ;
		sheet.getRow(1).getCell(0).setCellValue(lstTitle.get(1)) ;
		
		// 写数据
		int endrow = ExcelUtils.writeData(sheet, 3, lstData, mapColumnIndexConfig) ;
		sheet.getRow(endrow-1).getCell(0).setCellValue("合计") ;
	}
	
	private void exportSheet1Data(Sheet sheet, List<Map<String, Object>> lstData, Map<String, Integer> mapColumnIndexConfig, List<String> lstTitle) {
		// 写表头
		sheet.getRow(0).getCell(0).setCellValue(lstTitle.get(0)) ;
		sheet.getRow(1).getCell(0).setCellValue(lstTitle.get(1)) ;
		
		// 写数据
		ExcelUtils.writeData(sheet, 3, lstData, mapColumnIndexConfig) ;
	}
	
	private void exportSheet2Data(Sheet sheet, List<Map<String, Object>> lstData, Map<String, Integer> mapColumnIndexConfig, List<String> lstTitle) {
		// 写表头
		sheet.getRow(0).getCell(0).setCellValue(lstTitle.get(0)) ;
		sheet.getRow(1).getCell(0).setCellValue(lstTitle.get(1)) ;
		
		// 写数据
		ExcelUtils.writeData(sheet, 3, lstData, mapColumnIndexConfig) ;
	}
	
	/**
	 * 初始化对象，构造与导出模板对应的配置信息
	 */
	FinanceSaleReportAssist() {
		map_sheets_config =  new HashMap<Integer, Map<String, Integer>>() ;
		
		map_sheet_0 = new HashMap<String, Integer>() ;
		map_sheet_0.put("no", 0) ;						// 序号
		map_sheet_0.put("scenicName", 1) ;				// 景区名称
		map_sheet_0.put("productName", 2) ;				// 商品名称
		map_sheet_0.put("unitPrice", 3) ;				// 销售单价
		map_sheet_0.put("saleAmount", 4) ;				// 销售数量
		map_sheet_0.put("saleTotalPrice", 5) ;			// 销售总额
		map_sheet_0.put("refundAmount", 6) ;			// 退款数量
		map_sheet_0.put("refundTotalPrice", 7) ;		// 退款总额
		map_sheet_0.put("refundFeeTotalPrice", 8) ;		// 退款手续费总额
		map_sheet_0.put("sumPrice", 9) ;				// 小计
		map_sheets_config.put(0, map_sheet_0) ;
		
		map_sheet_1 = new HashMap<String, Integer>() ;
		map_sheet_1.put("no", 0) ;						// 序号
		map_sheet_1.put("orderNo", 1) ;					// 订单号
		map_sheet_1.put("supplierOrderNo", 2) ;			// 供应方订单号
		map_sheet_1.put("buyerOrderNo", 3) ;			// 采购方订单号
		map_sheet_1.put("scenicName", 4) ;				// 景区名称
		map_sheet_1.put("productName", 5) ;				// 商品名称
		map_sheet_1.put("unitPrice", 6) ;				// 销售单价
		map_sheet_1.put("saleAmount", 7) ;				// 销售数量
		map_sheet_1.put("saleTotalPrice", 8) ;			// 销售总额
		map_sheet_1.put("customerName", 9) ;			// 联系人姓名
		map_sheet_1.put("customerTel", 10) ;			// 联系人手机号
		map_sheet_1.put("orderUser", 11) ;				// 下单用户
		map_sheet_1.put("orderTime", 12) ;				// 下单时间
		map_sheets_config.put(1, map_sheet_1) ;
		
		map_sheet_2 = new HashMap<String, Integer>() ;
		map_sheet_2.put("no", 0) ;						// 序号
		map_sheet_2.put("orderNo", 1) ;					// 订单号
		map_sheet_2.put("supplierOrderNo", 2) ;			// 供应方订单号
		map_sheet_2.put("buyerOrderNo", 3) ;			// 采购方订单号
		map_sheet_2.put("scenicName", 4) ;				// 景区名称
		map_sheet_2.put("productName", 5) ;				// 商品名称
		map_sheet_2.put("unitPrice", 6) ;				// 销售单价
		map_sheet_2.put("refundFeePrice", 7) ;			// 退款手续费
		map_sheet_2.put("refundAmount", 8) ;			// 退款数量
		map_sheet_2.put("refundFeeTotalPrice", 9) ;		// 退款手续费总额
		map_sheet_2.put("returnTotalPrice", 10) ;		// 返款金额
		map_sheet_2.put("customerName", 11) ;			// 游客姓名
		map_sheet_2.put("customerTel", 12) ;			// 游客手机号
		map_sheet_2.put("refundUser", 13) ;				// 退款用户
		map_sheet_2.put("refundTime", 14) ;				// 退款申请时间
		map_sheet_2.put("refundApplyTime", 15) ;		// 退款审核时间
		map_sheets_config.put(2, map_sheet_2) ;
	}
	
}