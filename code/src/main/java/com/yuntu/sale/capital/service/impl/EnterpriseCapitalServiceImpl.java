package com.yuntu.sale.capital.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.capital.dao.EnterpriseCapitalDao;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;

import java.math.BigDecimal;

/**
 * 企业-平台资金Service实现类
 * @Description 
 * @author snps
 * @date 2018年3月8日 下午2:43:01
 */
@Service("enterpriseCapitalService")
public class EnterpriseCapitalServiceImpl implements EnterpriseCapitalService {

	@Resource
	private EnterpriseCapitalDao capitalDao;
	
	@Override
	public EnterpriseCapitalPo getByEnterpriseId(Long enterpriseId) {
		return capitalDao.queryByEnterpriseId(enterpriseId);
	}

	@Override
	public void save(EnterpriseCapitalPo entity) {
		capitalDao.insert(entity);
	}

	@Override
	public EnterpriseCapitalPo getSum() {
		return capitalDao.querySum();
	}

}