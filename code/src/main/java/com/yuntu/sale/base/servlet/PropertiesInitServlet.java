package com.yuntu.sale.base.servlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.coolshow.util.PropertiesUtil;
import com.yuntu.sale.base.GrobalSystemConstant;

public class PropertiesInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4002890611192603903L;
	
	private static Map<String, Map<String, String>> mapConfigProp ;
	
	public final void constructor() {
		mapConfigProp = new HashMap<String, Map<String, String>>() ;
	}
	
	public void init(ServletConfig config) throws ServletException {
		constructor() ;
		
		// 初始化：grobal.properties
		reload(GrobalSystemConstant.PROP_GROBAL) ;

		// 初始化：sms.properties
		reload(GrobalSystemConstant.PROP_SMS) ;
		
		// 初始化：resources.properties
		reload(GrobalSystemConstant.PROP_RESOURCES) ;
		
		
		// showPropContent(GrobalSystemConstant.PROP_SYS_URL) ;
		// showPropContent(GrobalSystemConstant.PROP_GROBAL) ;
	}
	
	/**
	 * 重新加载资源文件
	 * @param propertiesFileName
	 * @return Map<String, String>
	 */
	public synchronized static void reload(String propertiesFileName) {
		try {
			Properties prop = PropertiesUtil.loadProperties(propertiesFileName) ;
			Map<String, String> mapValues = new HashMap<String, String>() ; 
			Iterator<Object> itKeys = prop.keySet().iterator() ;
			while(itKeys.hasNext()) {
				String key = itKeys.next().toString() ;
				mapValues.put(key, (String) prop.get(key)) ;
			}
			mapConfigProp.put(propertiesFileName, mapValues) ;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("----------------使用错误的资源文件名，无法读取properties文件：" + propertiesFileName) ;
		}
	}
	
	/**
	 * 获取资源文件内容（供外部方法调用）
	 * @param propertiesFileName 资源文件名称（为避免编写错误，请在GrobalSystemConstant文件中声明为常量后使用）
	 * @return Map<String, String>
	 */
	public static Map<String, String> getPropContent(String propertiesFileName) {
		if(!mapConfigProp.containsKey(propertiesFileName)) {
			System.out.println("----------------您指定的资源文件不存在，请使用正确的资源文件名!") ;
			return null ;
		}
		
		return mapConfigProp.get(propertiesFileName) ;
	}
	
	/**
	 * 显示资源文件中的内容
	 * @param propertiesFileName
	 */
	public static void showPropContent(String propertiesFileName) {
		if(!mapConfigProp.containsKey(propertiesFileName)) {
			System.out.println("----------------您指定的资源文件不存在，请使用正确的资源文件名!") ;
			return ;
		}
		
		Map<String, String> mapPropContent = getPropContent(propertiesFileName) ;
		Iterator<String> itKeys = mapPropContent.keySet().iterator() ;
		while(itKeys.hasNext()) {
			String key = itKeys.next().toString() ;
			System.out.println(key + " = " + mapPropContent.get(key)) ;
		}
	}
}