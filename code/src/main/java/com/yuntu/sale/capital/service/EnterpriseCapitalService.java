package com.yuntu.sale.capital.service;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.EnterpriseCapitalPo;

/**
 * @Description 企业-平台资金Service接口
 * @author snps
 * @date 2018年3月8日 下午2:42:02
 */
public interface EnterpriseCapitalService {

	/**
	 * 通过企业Id获取企业平台余额
	 * @param enterpriseId 企业Id
	 * @return EnterpriseCapitalPo
	 */
	EnterpriseCapitalPo getByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 保存
	 * @param entity
	 */
	void save(EnterpriseCapitalPo entity);

	/**
	 * 平台资金总额
	 * @param
	 */
	EnterpriseCapitalPo getSum();
}