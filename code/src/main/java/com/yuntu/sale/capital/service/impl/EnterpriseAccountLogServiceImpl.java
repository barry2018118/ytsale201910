package com.yuntu.sale.capital.service.impl;

import javax.annotation.Resource;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import org.springframework.stereotype.Service;

import com.yuntu.sale.capital.dao.EnterpriseAccountLogDao;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;

import java.util.List;

/**
 * @Description 企业资金变动Service实现类 
 * @author snps
 * @date 2018年3月8日 下午3:09:23
 */
@Service("enterpriseAccountLogService")
public class EnterpriseAccountLogServiceImpl implements EnterpriseAccountLogService {

	@Resource
	private EnterpriseAccountLogDao accountLogDao;


	/**
	 * 通过业务行为类型ActionType获取资金变动
	 * @param actionType 业务行为类型Id
	 * @return List<EnterpriseAccountLogPo>
	 */
	@Override
	public Page<EnterpriseAccountLogPo> queryByActionType(Page<EnterpriseAccountLogPo> pager, Long enterpriseId, Long actionType,String startTime,String endTime){

		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseAccountLogPo> lstData = null;

		if(actionType == 100){
			 lstData = accountLogDao.queryAllByEnterpriseIdTrans(enterpriseId,startTime,endTime);
		}else if(actionType == 0){
			 lstData = accountLogDao.queryAllByEnterpriseId(enterpriseId, null,startTime,endTime);
		}else{
			 lstData = accountLogDao.queryByActionType(enterpriseId, actionType, null,startTime,endTime);
		}

		// 获取分页后所需信息
		PageInfo<EnterpriseAccountLogPo> pageInfo = new PageInfo<EnterpriseAccountLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseAccountLogPo> querySeach(Page<EnterpriseAccountLogPo> pager, Long childId, Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseAccountLogPo> lstData = accountLogDao.querySearch(childId,parentId);
		// 获取分页后所需信息
		PageInfo<EnterpriseAccountLogPo> pageInfo = new PageInfo<EnterpriseAccountLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public EnterpriseAccountLogPo queryById(Long id) {
		return accountLogDao.queryById(id);
	}

	@Override
	public Page<EnterpriseAccountLogPo> query(Page<EnterpriseAccountLogPo> pager) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseAccountLogPo> lstData = accountLogDao.query();
		// 获取分页后所需信息
		PageInfo<EnterpriseAccountLogPo> pageInfo = new PageInfo<EnterpriseAccountLogPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void update(EnterpriseAccountLogPo entity) {
		accountLogDao.save(entity);
	}

	@Override
	public void save(EnterpriseAccountLogPo entity) {accountLogDao.insert(entity);
	}

	@Override
	public EnterpriseAccountLogPo getOrder(Long childId, Long parentId, Integer type, Long enterpriseOrdersId) {
		return accountLogDao.queryOrder(childId,parentId,type,enterpriseOrdersId);
	}
}