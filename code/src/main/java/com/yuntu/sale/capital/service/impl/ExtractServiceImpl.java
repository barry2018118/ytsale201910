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
import com.yuntu.sale.capital.dao.ExtractDao;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.ExtractPo;
import com.yuntu.sale.capital.service.ExtractService;

/**
 * @Description 提现Service实现类
 * @author snps
 * @date 2018年3月8日 下午3:07:28
 */
@Service("extractService")
public class ExtractServiceImpl implements ExtractService {

	@Resource
	private ExtractDao extractDao;

	@Resource
	private EnterpriseAccountLogDao accountLogDao;

	@Resource
	private EnterpriseCapitalDao enterpriseCapitalDao;

	@Override
	public ExtractPo queryById(Long id) {
		return extractDao.queryById(id);
	}

	@Override
	public Page<ExtractPo> queryByTime(Page<ExtractPo> pager, Long enterpriseId, String start, String end,Integer state) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<ExtractPo> lstData = extractDao.queryRechargeTime(enterpriseId, start, end, state);
		// 获取分页后所需信息
		PageInfo<ExtractPo> pageInfo = new PageInfo<ExtractPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void save(ExtractPo entity,EnterpriseCapitalPo enterpriseCapital)  {
		//提现
		extractDao.insert(entity);
		//平台资金
		enterpriseCapitalDao.update(enterpriseCapital);
	}

	@Override
	public void update(ExtractPo entity,EnterpriseCapitalPo enterpriseCapital,EnterpriseAccountLogPo enterpriseAccountLogPo) {
		extractDao.save(entity);
		//平台资金
		enterpriseCapitalDao.update(enterpriseCapital);
		//资金变动
		enterpriseAccountLogPo.setActionId(entity.getId());
		accountLogDao.insert(enterpriseAccountLogPo);
	}

	@Override
	public ExtractPo getByDate(Long enterpriseId, Date startDate, Date endDate) {
		return extractDao.queryByDate(enterpriseId, startDate, endDate);
	}
	
}