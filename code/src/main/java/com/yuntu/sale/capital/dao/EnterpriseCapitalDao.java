package com.yuntu.sale.capital.dao;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.EnterpriseCapitalPo;

/**
 * @Description 企业-平台资金Dao接口
 * @author snps
 * @date 2018年2月26日 上午11:38:47
 */
public interface EnterpriseCapitalDao {
	
	/**
	 * 通过企业Id获取企业平台余额
	 * @param enterpriseId 企业Id
	 * @return EnterpriseCapitalPo
	 */
	EnterpriseCapitalPo queryByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 保存
	 * @param enterpriseCapital
	 */
	void insert(EnterpriseCapitalPo enterpriseCapital);

	/**
	 * 保存
	 * @param enterpriseCapital
	 */
	void update(EnterpriseCapitalPo enterpriseCapital);

	/**
	 * 平台资金总额
	 * @param
	 */
    EnterpriseCapitalPo querySum();
}