package com.yuntu.sale.capital.service.impl;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.capital.dao.EnterpriseStorageLogDao;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.service.EnterpriseStorageLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 企业预收款日志Service实现类
 * @author snps
 * @date 2018年3月8日 下午2:19:00
 */
@Service("enterpriseStorageLogService")
public class EnterpriseStorageLogServiceImpl implements EnterpriseStorageLogService {

	@Resource
	private EnterpriseStorageLogDao storageLogDao;

	@Override
	public EnterpriseStorageLogPo queryById(Long id) {
		return storageLogDao.queryById(id);
	}

	@Override
	public Page<EnterpriseStorageLogPo> queryByParentId(Page<EnterpriseStorageLogPo> pager, Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageLogPo> lstData = storageLogDao.queryByParentId(parentId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageLogPo> pageInfo = new PageInfo<EnterpriseStorageLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseStorageLogPo> queryByChildId(Page<EnterpriseStorageLogPo> pager, Long childId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageLogPo> lstData = storageLogDao.queryByChildId(childId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageLogPo> pageInfo = new PageInfo<EnterpriseStorageLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseStorageLogPo> querySearch(Page<EnterpriseStorageLogPo> pager,Long childId, Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseStorageLogPo> lstData = storageLogDao.querySearch(childId,parentId);
		// 获取分页后所需信息
		PageInfo<EnterpriseStorageLogPo> pageInfo = new PageInfo<EnterpriseStorageLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void save(EnterpriseStorageLogPo entity) {
		storageLogDao.insert(entity);
	}

	@Override
	public void update(EnterpriseStorageLogPo entity) {
		storageLogDao.save(entity);
	}
}