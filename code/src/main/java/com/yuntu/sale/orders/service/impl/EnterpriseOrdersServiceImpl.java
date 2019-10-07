
package com.yuntu.sale.orders.service.impl;


import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.orders.dao.CodeDao;
import com.yuntu.sale.orders.dao.EnterpriseOrdersDao;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.service.CodeService;
import com.yuntu.sale.orders.service.EnterpriseOrdersService;
import com.yuntu.sale.orders.vo.EnterpriseOrdersConsumeVo;
import com.yuntu.sale.orders.vo.OrdersRefundListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单表子-企业订单表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("enterpriseOrdersService")
public class EnterpriseOrdersServiceImpl implements EnterpriseOrdersService {
	@Resource
	private EnterpriseOrdersDao enterpriseOrdersDao;

	public EnterpriseOrders get(Long id) {
		return enterpriseOrdersDao.get(id);
	}
	
	public List<EnterpriseOrders> findList(EnterpriseOrders enterpriseOrders) {
		return enterpriseOrdersDao.findList(enterpriseOrders);
	}
	
	public void save(EnterpriseOrders enterpriseOrders) {
		enterpriseOrdersDao.save(enterpriseOrders);
	}
	
	public void update(EnterpriseOrders enterpriseOrders) {
		enterpriseOrdersDao.update(enterpriseOrders);
	}

	@Override
	public EnterpriseOrders getOne(Long id, Long childId, Long parentId) {
		return enterpriseOrdersDao.queryOne(id,childId,parentId);
	}

	@Override
	public Page<EnterpriseOrdersConsumeVo> getConsumeList(Page<EnterpriseOrdersConsumeVo> pager, Long enterpriseId, String productNo, String productName) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseOrdersConsumeVo> lstData = enterpriseOrdersDao.queryConsumeList(enterpriseId,productNo,productName);
		// 获取分页后所需信息
		PageInfo<EnterpriseOrdersConsumeVo> pageInfo = new PageInfo<EnterpriseOrdersConsumeVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
}