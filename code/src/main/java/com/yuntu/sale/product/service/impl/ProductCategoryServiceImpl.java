package com.yuntu.sale.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yuntu.sale.product.po.ProductCategory;
import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.product.dao.ProductCategoryDao;
import com.yuntu.sale.product.service.ProductCategoryService;

/**
 * @Description 产品类别Service实现类
 * @author snps
 * @date 2018年2月14日 下午4:32:30
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Resource
	private ProductCategoryDao dao;

	@Override
	public Page<ProductCategory> getByName(Page<ProductCategory> pager, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<ProductCategory> lstData = dao.queryByName(name);

		// 获取分页后所需信息
		PageInfo<ProductCategory> pageInfo = new PageInfo<ProductCategory>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public ProductCategory getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public ProductCategory getByUniqueName(String name) {
		return dao.queryByUniqueName(name);
	}

	@Override
	public void save(ProductCategory entity) {
		dao.insert(entity);
	}

	@Override
	public void update(ProductCategory entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public List<ProductCategory> getAll() {
		return dao.query();
	};


}