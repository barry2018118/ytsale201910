package com.yuntu.sale.product.service.impl;

import com.yuntu.sale.product.dao.EnterpriseProductDao;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.service.EnterpriseProductService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 商品管理Service
 * @author zy
 * @version 2018-04-02
 */
@Service("enterpriseProductService")
public class EnterpriseProductServiceImpl implements EnterpriseProductService {

	@Resource
	private EnterpriseProductDao dao;

	@Override
	public EnterpriseProduct getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public Integer getByProductId(Long productId) {
		return dao.queryByProductId(productId);
	}

	@Override
	public Integer getByParentId(Long parentId) {
		return dao.queryByParentId(parentId);
	}

	@Override
	public void save(EnterpriseProduct entity) {
		dao.save(entity);
	}

	@Override
	public void update(EnterpriseProduct entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public List<EnterpriseProduct> getSearch(Long productId, Long childId, Long groupId) {
		return dao.getSearch(productId, childId, groupId);
	}

	@Override
	public EnterpriseProduct getOne(Long productId, Long parentId, Long childId) {
		return dao.queryOne(productId, parentId, childId);
	}

	@Override
	public List<EnterpriseProduct> getDelSearch(Long productId,Long parentId, Long childId, Long groupId) {
		return dao.getDelSearch(productId,parentId, childId, groupId);
	}

	@Override
	public Integer getProductDistributionNum(Long productId) {
		return dao.queryProductDistributionNum(productId);
	}
}