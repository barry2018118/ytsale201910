package com.yuntu.sale.base;

import java.io.Serializable;

/**
 * 全局系统常量
 * @Description 
 * @author snps
 * @date 上午9:36:43
 */
public class GrobalSystemConstant implements Serializable {

	private static final long serialVersionUID = -3670022978967303152L;

	public static final String ROOT_PATH = "/";
	public static final String STRING_EMPTY = "" ;
	public static final String S_SLASH = "/" ;

	/**
	 * int
	 */
	public static final int INT_0 = 0;
	public static final int INT_1 = 1;

	/**
	 * 资源文件_全局配置
	 */
	public static final String PROP_GROBAL = "conf/grobal.properties";
	public static final String PROP_SMS = "conf/sms.properties";
	public static final String PROP_RESOURCES = "conf/resources.properties";
	
	/**
	 * 权限相关
	 */
	public static final String FUNC_MODULE = "module" ;
	public static final String FUNC_MENU = "menu" ;
	public static final String FUNC_ATION = "action" ;
	public static final String FUNC_BUTTON = "button" ;

}