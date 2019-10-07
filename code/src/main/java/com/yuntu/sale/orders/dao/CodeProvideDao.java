package com.yuntu.sale.orders.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.orders.po.CodeProvide;

/**
 * 码库表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface CodeProvideDao{

    CodeProvide get(@Param("id")Long id);

    List<CodeProvide> find(@Param("num")Integer num) ;

    void save(CodeProvide codeProvide);

    void update(@Param("id")Long id);

    void begin();

    void commit();
    
    /**
	 * 根据码号和状态查询
	 * @param code
	 * @return Integer
	 */
	public Integer quertCountByCodeAndStatus5(@Param("code") String code) ;
	
}