package com.yuntu.sale.orders.service;

/**
 * 核销码
 * @author Jack.jiang
 * @version 2018-05-16
 */
public interface ConsumeService {
	
	/**
	 * 核销码
	 * @param code
	 * @param num
	 * @param shopId
	 * @param userId
	 * @return Json 字符串
	 */
	String consumeCode(String code,Integer num,Long shopId,Long userId);
}
