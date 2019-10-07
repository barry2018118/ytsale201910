package com.yuntu.sale.info.service.impl;

import javax.annotation.Resource;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.info.po.InfoScenicPo;
import org.springframework.stereotype.Service;

import com.yuntu.sale.info.dao.ScenicDao;
import com.yuntu.sale.info.service.ScenicService;

import java.util.List;

/**
 * @Description 景区信息Service实现类 
 * @author snps
 * @date 2018年2月27日 上午9:50:11
 */
@Service("scenicService")
public class ScenicServiceImpl implements ScenicService {

	@Resource
	private ScenicDao dao;

	@Override
	public Page<InfoScenicPo> getByName(Page<InfoScenicPo> pager, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<InfoScenicPo> lstData = dao.queryByName(name);

		// 获取分页后所需信息
		PageInfo<InfoScenicPo> pageInfo = new PageInfo<InfoScenicPo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;

	}

	@Override
	public InfoScenicPo getById(Long id) {
		return dao.queryById(id);
	}

	@Override
	public InfoScenicPo getByUniqueName(String name) {
		return dao.queryByUniqueName(name);
	}

	@Override
	public void save(InfoScenicPo entity) {
		dao.insert(entity);
	}

	@Override
	public void update(InfoScenicPo entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}
}