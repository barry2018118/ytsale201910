package com.yuntu.sale.capital.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.capital.dao.EnterpriseAccountLogDao;
import com.yuntu.sale.capital.dao.EnterpriseCapitalDao;
import com.yuntu.sale.capital.dao.RechargeDao;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.RechargePo;
import com.yuntu.sale.capital.service.RechargeService;

/**
 * @Description 充值Service实现类
 * @author snps
 * @date 2018年3月8日 下午3:05:33
 */
@Service("rechargeService")
public class RechargeServiceImpl implements RechargeService {

	@Resource
	private RechargeDao rechargeDao;

	@Resource
	private EnterpriseAccountLogDao accountLogDao;

	@Resource
	private EnterpriseCapitalDao enterpriseCapitalDao;

	@Override
	public RechargePo queryById(Long id) {
		return rechargeDao.queryById(id);
	}

	@Override
	public Page<RechargePo> queryByTime(Page<RechargePo> pager, Long enterpriseId, String username, String start, String end) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<RechargePo> lstData = rechargeDao.queryRechargeTime(enterpriseId, username, start, end);
		// 获取分页后所需信息
		PageInfo<RechargePo> pageInfo = new PageInfo<RechargePo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void update(RechargePo entity) {
		rechargeDao.save(entity);
	}

	@Override
	public void save(RechargePo entity, EnterpriseCapitalPo enterpriseCapital, EnterpriseAccountLogPo log) {
		//企业资金变动 --PC 端充值
		rechargeDao.insert(entity);
		//平台资金
		enterpriseCapitalDao.update(enterpriseCapital);
		//日志
		log.setActionId(entity.getId());
		accountLogDao.insert(log);
	}

	@Override
	public RechargePo getByDate(Long enterpriseId, Date startDate, Date endDate) {
		return rechargeDao.queryByDate(enterpriseId, startDate, endDate);
	}
	
}