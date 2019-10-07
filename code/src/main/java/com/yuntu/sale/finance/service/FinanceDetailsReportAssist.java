package com.yuntu.sale.finance.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.coolshow.util.ExcelUtils;
import com.coolshow.util.JavabeanUtils;

/**
 * @Description 财务-明细报表导出辅助类
 * @author snps
 * @date 2018年5月27日 下午10:43:53
 */
public class FinanceDetailsReportAssist {
	
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
		sheet.getRow(1).getCell(2).setCellValue(lstTitle.get(0)) ;
		sheet.getRow(1).getCell(13).setCellValue(lstTitle.get(1)) ;
		
		// 写数据
		int endrow = ExcelUtils.writeData(sheet, 3, lstData, mapColumnIndexConfig) ;
		// sheet.getRow(endrow-1).getCell(0).setCellValue("合计") ;
	}
	
	
	/**
	 * 初始化对象，构造与导出模板对应的配置信息
	 */
	public FinanceDetailsReportAssist() {
		map_sheets_config =  new HashMap<Integer, Map<String, Integer>>() ;
		
		map_sheet_0 = new HashMap<String, Integer>() ;
		map_sheet_0.put("no", 0) ;						// 序号
		map_sheet_0.put("enterpriseName", 1) ;			// 企业名称
		map_sheet_0.put("productName", 2) ;				// 票型名称
		map_sheet_0.put("orderNo", 3) ;					// 订单号
		map_sheet_0.put("orderSoruce", 4) ;				// 订单来源
		map_sheet_0.put("thirdOrderNo", 5) ;			// 第三方订单号
		map_sheet_0.put("linkman", 6) ;					// 联系人
		map_sheet_0.put("linkmanNo", 7) ;				// 联系人-身份证号
		map_sheet_0.put("linkmanPhone", 8) ;			// 联系人-联系方式
		map_sheet_0.put("saleNum", 9) ;					// 订单总数
		map_sheet_0.put("price", 10) ;					// 单价
		map_sheet_0.put("consumeNum", 11) ;				// 验票数量
		map_sheet_0.put("consumePrice", 12) ;			// 验票数量
		map_sheet_0.put("createEnterprise", 13) ;		// 创建人
		
		map_sheets_config.put(0, map_sheet_0) ;
	}
	
}