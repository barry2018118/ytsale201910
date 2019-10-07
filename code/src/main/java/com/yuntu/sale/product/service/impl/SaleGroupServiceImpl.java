package com.yuntu.sale.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.product.dao.SaleGroupDao;
import com.yuntu.sale.product.dao.SaleGroupEnterpriseDao;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.service.SaleGroupService;

/**
 * 分销组Service
 * @author zy
 * @version 2018-04-02
 */
@Service("saleGroupService")
public class SaleGroupServiceImpl implements SaleGroupService {
	
	@Resource
	private SaleGroupDao dao;
	
	@Resource
	private SaleGroupEnterpriseDao saleGroupEnterpriseDao;

	@Override
	public SaleGroup getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public SaleGroup getByName(String name,Long enterpriseId) {
		return dao.getName(name,enterpriseId);
	}

	@Override
	public Page<SaleGroup> queryByName(Page<SaleGroup> pager, String name, Long enterpriseId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		
		// 查询数据
		List<SaleGroup> lstData = dao.queryByName(name,enterpriseId);
		
		// 设置组内分销商数	add by snps at 0611 21:33
		if(!BaseUtil.isEmpty(lstData)) {
			for(SaleGroup data : lstData) {
				data.setUserNumber(saleGroupEnterpriseDao.queryEnterpriseNUm(data.getId()));
			}
		}
		
		// 获取分页后所需信息
		PageInfo<SaleGroup> pageInfo = new PageInfo<SaleGroup>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void save(SaleGroup entity) {
		dao.insert(entity);
	}

	@Override
	public void update(SaleGroup entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}


}