package com.yuntu.sale.report.service.impl;

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
 * @Description 商户-商品销售导出报表辅助类
 * @author snps
 * @date 2018年4月27日 下午1:45:27
 */
public class ShopProductSaleReportAssist {
	
	/**
	 * Sheet页集合 
	 */
	private Map<Integer, Map<String, Integer>> map_sheets_config = null ;
	
	/**
	 * 各Sheet页（与模板文件中的Sheet对应）
	 */
	private Map<String, Integer> map_sheet_0 = null ;
	
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
		ExcelUtils.writeData(sheet, 3, lstData, mapColumnIndexConfig) ;
	}
	
	/**
	 * 初始化对象，构造与导出模板对应的配置信息
	 */
	ShopProductSaleReportAssist() {
		map_sheets_config =  new HashMap<Integer, Map<String, Integer>>() ;
		
		map_sheet_0 = new HashMap<String, Integer>() ;
		map_sheet_0.put("no", 0) ;						// 序号
		map_sheet_0.put("productName", 1) ;				// 产品名称
		map_sheet_0.put("productTypeStr", 2) ;			// 产品类型
		map_sheet_0.put("unitPrice", 3) ;				// 销售单价
		map_sheet_0.put("saleAmount", 4) ;				// 销售数量
		map_sheet_0.put("saleTotalPrice", 5) ;			// 销售总额
		map_sheet_0.put("refundAmount", 6) ;			// 退款数量
		map_sheet_0.put("refundTotalPrice", 7) ;		// 退款总额
		map_sheet_0.put("refundFeeTotalPrice", 8) ;		// 退款手续费总额
		map_sheet_0.put("sumPrice", 9) ;				// 小计
		map_sheets_config.put(0, map_sheet_0) ;
		
	}
	
}