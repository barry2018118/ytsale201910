package com.yuntu.sale.product.service.impl;

import com.yuntu.sale.product.dao.SaleGroupLogDao;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.service.SaleGroupLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分销组操作日志Service
 * @author zy
 * @version 2018-04-02
 */
@Service("saleGroupLogService")
public class SaleGroupLogServiceImpl implements SaleGroupLogService {

	@Resource
	private SaleGroupLogDao dao;

	@Override
	public SaleGroupLog getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public void save( SaleGroupLog entity) {
		dao.insert(entity);
	}

	@Override
	public void update( SaleGroupLog entity) {
		dao.update(entity);
	}

}