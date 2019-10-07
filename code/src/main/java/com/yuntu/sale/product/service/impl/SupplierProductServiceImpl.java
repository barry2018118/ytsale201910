package com.yuntu.sale.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.product.dao.EnterpriseProductDao;
import com.yuntu.sale.product.dao.SupplierProductDao;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.po.SupplierProductVo;
import com.yuntu.sale.product.service.SupplierProductService;

/**
 * @Description 供应商产品Service实现类
 * @author snps
 * @date 2018年2月14日 下午4:35:40
 */
@Service("supplierProductService")
public class SupplierProductServiceImpl implements SupplierProductService {
	
	@Resource
	private SupplierProductDao dao;

	@Resource
	private EnterpriseProductDao enterpriseProductDao;

	@Override
	public SupplierProduct getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public Page<SupplierProductVo> querySearch(Page<SupplierProductVo> pager, Long enterpriseId, String productNo, String productName, Integer productategory, Integer productStatus, Integer productSource, Integer productAffiliation) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<SupplierProductVo> lstData = dao.querySearch(enterpriseId,productNo,productName,productategory,productStatus,productSource,productAffiliation);
		// 获取分页后所需信息
		PageInfo<SupplierProductVo> pageInfo = new PageInfo<SupplierProductVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public void save(SupplierProduct entity,EnterpriseProduct enterpriseProduct) {
		//商品
		dao.insert(entity);
		//企业-商品 关系
		enterpriseProduct.setProductId(entity.getId());
		enterpriseProductDao.save(enterpriseProduct);
	}

	@Override
	public void update(SupplierProduct entity) {
		dao.update(entity);
	}

	@Override
	public void status(SupplierProduct entity) {
		dao.status(entity);
	}

	@Override
	public void delete(Long id) {
		enterpriseProductDao.delete(id);
		dao.delete(enterpriseProductDao.queryById(id).getProductId());
	}

	@Override
	public SupplierProduct getByName(String name) {
		return dao.queryByName(name);
	}

	@Override
	public Page<SupplierProduct> getShopProduct(Page<SupplierProduct> pager, Long enterpriseId, String productNo, String productName) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<SupplierProduct> lstData = dao.queryMySearch(enterpriseId,productNo,productName);
		// 获取分页后所需信息
		PageInfo<SupplierProduct> pageInfo = new PageInfo<SupplierProduct>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public List<SupplierProductVo> querySearchNoPage(Long enterpriseId, String productNo, String productName,
			Integer productategory, Integer productStatus, Integer productSource, Integer productAffiliation) {
		List<SupplierProductVo> lstData = dao.querySearch(enterpriseId, productNo, productName, 
				productategory, productStatus, productSource, productAffiliation);
		return lstData;
	}
	
}