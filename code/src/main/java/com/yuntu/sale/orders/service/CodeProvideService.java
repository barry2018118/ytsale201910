package com.yuntu.sale.orders.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.orders.po.CodeProvide;

/**
 * 码库表Service
 * @author zy
 * @version 2018-04-23
 */

public interface CodeProvideService  {

	CodeProvide get(@Param("id")Long id);

	List<CodeProvide> find(@Param("num")Integer num) ;

	void save(@Param("codeProvide")CodeProvide codeProvide);

	void update(@Param("id")Long id);
	
	/**
	 * 根据码号和状态查询
	 * @param code
	 * @return Integer
	 */
	public Integer getCountByCodeAndStatus5(String code) ;
	
}