package com.yuntu.sale.capital.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.yuntu.sale.capital.dao.EnterpriseBankCardDao;
import com.yuntu.sale.capital.po.EnterpriseBankCardPo;
import com.yuntu.sale.capital.service.EnterpriseBankCardService;

/**
 * 
 * @Description EnterpriseBankCardService
 * @author snps
 * @date 2018年3月8日 下午2:47:53
 */
@Service
public class EnterpriseBankCardServiceImpl implements EnterpriseBankCardService {
	
	@Resource
	private EnterpriseBankCardDao dao;

	@Override
	public List<EnterpriseBankCardPo> getByEnterpriseId(Long enterpriseId) {
		return dao.queryByEnterpriseId(enterpriseId);
	}

	@Override
	public EnterpriseBankCardPo getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public Page<EnterpriseBankCardPo> getMyBankCard(Page<EnterpriseBankCardPo> pager, Long enterpriseId, String username, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseBankCardPo> lstData = dao.queryMyCard(enterpriseId, username, name);
		// 获取分页后所需信息
		PageInfo<EnterpriseBankCardPo> pageInfo = new PageInfo<EnterpriseBankCardPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public EnterpriseBankCardPo getByCardNo(String cardNo) {
		return  dao.queryCardNo(cardNo);
	}

	@Override
	public void save(EnterpriseBankCardPo entity) {
		dao.insert(entity);
	}

	@Override
	public void update(EnterpriseBankCardPo entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
