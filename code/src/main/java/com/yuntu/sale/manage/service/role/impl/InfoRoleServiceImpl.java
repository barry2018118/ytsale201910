package com.yuntu.sale.manage.service.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.base.po.role.InfoRole;
import com.yuntu.sale.manage.dao.role.InfoRoleDao;
import com.yuntu.sale.manage.service.role.InfoRoleService;

@Service
public class InfoRoleServiceImpl implements InfoRoleService {
	@Resource
	private InfoRoleDao infoRoleDao;

	public InfoRole searchById(Long id) {
		return infoRoleDao.selectByPrimaryKey(id);
	}

	public Page<InfoRole> search(Page<InfoRole> pager, String title) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		List<InfoRole> items = infoRoleDao.search(title);

		// 获取分页后所需信息
		PageInfo<InfoRole> pageInfo = new PageInfo<InfoRole>(items);
		long total = pageInfo.getTotal();
		pager.setTotal(total);
		pager.setResult(items);
		return pager;
	}
	
	public Page<InfoRole> searchByEnterpriseId(Page<InfoRole> pager, Long enterpriseId, String title) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		List<InfoRole> items = infoRoleDao.searchByEnterpriseId(enterpriseId, title);

		// 获取分页后所需信息
		PageInfo<InfoRole> pageInfo = new PageInfo<InfoRole>(items);
		long total = pageInfo.getTotal();
		pager.setTotal(total);
		pager.setResult(items);
		return pager;
	}
	
	@Override
	public List<InfoRole> searchByEnterpriseId(Long enterpriseId) {
		return infoRoleDao.searchByEnterpriseId(enterpriseId, null);
	}

	public Long addRole(InfoRole infoRole) {
		return infoRoleDao.insert(infoRole);
	}

	public InfoRole searchByName(String title) {
		return infoRoleDao.selectByName(title);
	}

	public int modify(InfoRole infoRole) {
		return infoRoleDao.editByPrimaryKey(infoRole);
	}

	public void deleteRole(Long id) {
		infoRoleDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<InfoRole> searchRole() {
		return infoRoleDao.selectAll();
	}

	public int update(InfoRole infoRole) {
		return infoRoleDao.updateByPrimaryKey(infoRole);
	}

}