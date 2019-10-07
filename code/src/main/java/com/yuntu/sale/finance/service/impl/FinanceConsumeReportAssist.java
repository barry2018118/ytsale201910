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
 * @Description 财务对账单-消费-报表导出辅助类 
 * @author snps
 * @date 2018年5月31日 上午11:18:19
 */
public class FinanceConsumeReportAssist {

	/**
	 * Sheet页集合 
	 */
	private Map<Integer, Map<String, Integer>> map_sheets_config = null ;
	
	/**
	 * 各Sheet页（与模板文件中的Sheet对应）
	 */
	private Map<String, Integer> map_sheet_0 = null ;
	private Map<String, Integer> map_sheet_1 = null ;
	
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
	 * @return 导出后的文件路径
	 */
	public String getReport(String templateFilePath, Map<Integer, List<? extends Object>> mapData, String exportFilePath,Map<Integer,List<String>> titleMap) {
		try {
			Workbook wb = ExcelUtils.getNewWorkBook(templateFilePath) ;
			
			// 导出第0个Sheet页内容
			Sheet sheet_0 = wb.getSheetAt(0) ;
			List<Map<String, Object>> mapSheet0Data = JavabeanUtils.listBean2ListMap(mapData.get(0)) ;
			this.exportSheet0Data(sheet_0, mapSheet0Data, this.getSheetConfig(0),titleMap.get(0)) ;
			
			// 导出第1个Sheet页内容
			Sheet sheet_1 = wb.getSheetAt(1) ;
			List<Map<String, Object>> mapSheet1Data = JavabeanUtils.listBean2ListMap(mapData.get(1)) ;
			this.exportSheet1Data(sheet_1, mapSheet1Data, this.getSheetConfig(1),titleMap.get(1)) ;
			
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
	
	/**
	 * 初始化对象，构造与导出模板对应的配置信息
	 */
	FinanceConsumeReportAssist() {
		map_sheets_config =  new HashMap<Integer, Map<String, Integer>>() ;
		
		map_sheet_0 = new HashMap<String, Integer>() ;
		map_sheet_0.put("no", 0) ;						// 序号
		map_sheet_0.put("scenicName", 1) ;				// 景区名称
		map_sheet_0.put("productName", 2) ;				// 商品名称
		map_sheet_0.put("consumePrice", 3) ;			// 消费单价
		map_sheet_0.put("consumeAmount", 4) ;			// 消费数量
		map_sheet_0.put("consumeTotalPrice", 5) ;		// 消费总额
		map_sheets_config.put(0, map_sheet_0) ;
		
		map_sheet_1 = new HashMap<String, Integer>() ;
		map_sheet_1.put("no", 0) ;						// 序号
		map_sheet_1.put("orderNo", 1) ;					// 订单号
		map_sheet_1.put("supplierOrderNo", 2) ;			// 供应方订单号
		map_sheet_1.put("buyerOrderNo", 3) ;			// 采购方订单号
		map_sheet_1.put("code", 4) ;					// 电子码
		map_sheet_1.put("scenicName", 5) ;				// 景区
		map_sheet_1.put("productName", 6) ;				// 商品名称
		map_sheet_1.put("consumePrice", 7) ;			// 消费单价
		map_sheet_1.put("consumeAmount", 8) ;			// 消费数量
		map_sheet_1.put("consumeTotalPrice", 9) ;		// 消费总额
		map_sheet_1.put("customerName", 10) ;			// 联系人姓名
		map_sheet_1.put("customerTel", 11) ;			// 联系人手机号
		map_sheet_1.put("idCard", 12) ;					// 联系人身份证号
		map_sheet_1.put("consumeTime", 13) ;			// 消费时间
		map_sheets_config.put(1, map_sheet_1) ;
		
	}
	
}