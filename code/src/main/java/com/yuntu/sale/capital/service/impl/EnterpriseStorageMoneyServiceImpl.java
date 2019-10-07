package com.yuntu.sale.capital.service.impl;

import javax.annotation.Resource;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.capital.dao.EnterpriseAccountLogDao;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyChasePo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.yuntu.sale.capital.dao.EnterpriseStorageLogDao;
import com.yuntu.sale.capital.dao.EnterpriseStorageMoneyDao;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;

import java.util.List;

/**
 * @Description 企业预收款Service实现类
 * @author snps
 * @date 2018年3月8日 下午2:19:00
 */
@Service("enterpriseStorageMoneyService")
public class EnterpriseStorageMoneyServiceImpl implements EnterpriseStorageMoneyService {

	@Resource
	private EnterpriseStorageMoneyDao storageMoneyDao;

	@Resource
	private EnterpriseStorageLogDao storageLogDao;

	@Resource
	private EnterpriseAccountLogDao accountLogDao;
	
	@Override
	public EnterpriseStorageMoneyPo queryById(Long id) {return storageMoneyDao.queryById(id);
	}

	@Override
	public Page<EnterpriseStorageMoneyPo> queryByParentId(Page<EnterpriseStorageMoneyPo> pager, Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageMoneyPo> lstData = storageMoneyDao.queryByParentId(parentId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageMoneyPo> pageInfo = new PageInfo<EnterpriseStorageMoneyPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseStorageMoneyPo> queryByChildId(Page<EnterpriseStorageMoneyPo> pager, Long childId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageMoneyPo> lstData = storageMoneyDao.queryByChildId(childId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageMoneyPo> pageInfo = new PageInfo<EnterpriseStorageMoneyPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public EnterpriseStorageMoneyPo querySearch(Long childId, Long parentId) {
		return storageMoneyDao.querySearch(childId,parentId);
	}

	@Override
	public void update(EnterpriseStorageMoneyPo entity, EnterpriseStorageLogPo newLog,EnterpriseAccountLogPo enterpriseAccountLogPo) {
		//预收款
		storageMoneyDao.save(entity);
		//预收款日志
		storageLogDao.insert(newLog);
		//企业资金变动日志
		enterpriseAccountLogPo.setActionId(entity.getId());
		accountLogDao.insert(enterpriseAccountLogPo);
	}

	@Override
	public void save(EnterpriseStorageMoneyPo entity, EnterpriseStorageLogPo newLog,EnterpriseAccountLogPo enterpriseAccountLogPo) {
		//预收款
		storageMoneyDao.insert(entity);
		//预收款日志
		storageLogDao.insert(newLog);
		//企业资金变动日志
		enterpriseAccountLogPo.setActionId(entity.getId());
		accountLogDao.insert(enterpriseAccountLogPo);
	}

	@Override
	public Page<EnterpriseStorageMoneyChasePo> getMySupplier(Page<EnterpriseStorageMoneyChasePo> pager, String name,Long childId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageMoneyChasePo> lstData = storageMoneyDao.queryMySupplier(name,childId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageMoneyChasePo> pageInfo = new PageInfo<EnterpriseStorageMoneyChasePo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseStorageMoneyChasePo> getMyDistributor(Page<EnterpriseStorageMoneyChasePo> pager, String name,Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageMoneyChasePo> lstData = storageMoneyDao.queryMyDistributor(name,parentId);

		PageInfo<EnterpriseStorageMoneyChasePo> pageInfo = new PageInfo<EnterpriseStorageMoneyChasePo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
}