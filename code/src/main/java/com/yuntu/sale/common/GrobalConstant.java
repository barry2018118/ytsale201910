package com.yuntu.sale.common;

/**
 * @Description 公共常量类
 * @author snps
 * @date 2018年2月13日 上午8:41:19
 */
public class GrobalConstant {
	
	/**
	 * 符号
	 */
	public static final String S_SYMBOL_LINE = "-" ;
	public static final String S_SYMBOL_COMMA = "," ;
	public static final String S_SYMBOL_POINT = "." ;
	
	/**
	 * 数字
	 */
	public static final int I_NUM_0 = 0 ;
	public static final int I_NUM_1 = 1 ;
	public static final int I_NUM_9 = 9 ;
	public static final int I_NUM_99 = 99 ;
	
	/**
	 * 指标-是否平台管理（1：是，0：否）
	 */
	public static final int I_INDEX_PLATFORM_YES = 1 ;
	public static final int I_INDEX_PLATFORM_NO = 0 ;
	
	/**
	 * 指标-账号类型（0：平台、1：商户）
	 */
	public static final int I_INDEX_ACCOUNT_PLATFORM = 0 ;
	public static final int I_INDEX_ACCOUNT_SHOP = 1 ;
	
	/**
	 * 指标-是否主账户（1：是，0：否）
	 */
	public static final int I_INDEX_MASTER_YES = 1 ;
	public static final int I_INDEX_MASTER_NO = 0 ;
	
	/**
	 * 指标-是否删除（1：是，0：否）
	 */
	public static final int I_INDEX_DELETE_YES = 1 ;
	public static final int I_INDEX_DELETE_NO = 0 ;

	/**
	 * 指标-用户状态（1：启用，0：停用）
	 */
	public static final int I_INDEX_USER_STATUS_AVAILABLE = 1 ;
	public static final int I_INDEX_USER_STATUS_UNUSABLE = 0 ;
	
	/**
	 * 是否置顶
	 */
	public static final int I_INDEX_TOP_YES = 1 ;
	public static final int I_INDEX_TOP_NO = 0 ;
	
	/**
	 * 是否已读
	 */
	public static final int I_INDEX_READ_YES = 1 ;
	public static final int I_INDEX_READ_NO = 0 ;
	
	/**
	 * 商户类型标识
	 */
	public static final int I_SHOP_PLATFORM = 0 ;
	public static final int I_SHOP_OPERATION = 1 ;
	public static final int I_SHOP_SUPPLIER = 2 ;
	public static final int I_SHOP_DISTRIBUTOR = 3 ;
	/**
	 * 商品状态（1：启用，0：停用）
	 */
	public static final int I_SUPPLIER_PRODUCT_STATUS_AVAILABLE = 1 ;
	public static final int I_SUPPLIER_PRODUCT_STATUS_UNUSABLE = 0 ;
	/**
	 * 是否是商品供应商（1：是，0：否）
	 */
	public static final int I_ENTERPRISE_PRODUCT_IS_SUPPLIER_YES = 1 ;
	public static final int I_ENTERPRISE_PRODUCT_IS_SUPPLIER_NO = 0 ;
	/**
	 * 随机选取
	 */
	public static final String MATHROMDENSTRING = "abcdefghijklmnopqrstuvwxyz1234567890";
}