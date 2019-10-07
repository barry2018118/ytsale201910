package com.yuntu.sale.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.base.po.role.InfoRoleAction;
import com.yuntu.sale.base.po.role.InfoRoleMenu;
import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.capital.dao.EnterpriseCapitalDao;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.manage.dao.role.InfoRoleActionDao;
import com.yuntu.sale.manage.dao.role.InfoRoleMenuDao;
import com.yuntu.sale.manage.dao.role.InfoRoleModuleDao;
import com.yuntu.sale.manage.dao.user.InfoUserActionDao;
import com.yuntu.sale.manage.dao.user.InfoUserMenuDao;
import com.yuntu.sale.manage.dao.user.InfoUserModuleDao;
import com.yuntu.sale.user.dao.InfoEnterpriseDao;
import com.yuntu.sale.user.dao.InfoEnterpriseRelationDao;
import com.yuntu.sale.user.dao.InfoUserDao;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoEnterpriseRelation;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;

/**
 * @Description 企业信息Service实现类
 * @author snps
 * @date 2018年2月26日 下午2:06:15
 */
@Service
public class InfoEnterpriseServiceImpl implements InfoEnterpriseService {

	@Resource
	private InfoEnterpriseDao infoEnterpriseDao;
	
	@Resource
	private InfoEnterpriseRelationDao infoEnterpriseRelationDao;

	@Resource
	private InfoUserDao userDao;

	@Resource
	private EnterpriseCapitalDao enterpriseCapitalDao;

	@Resource
	private InfoRoleModuleDao infoRoleModuleDao;

	@Resource
	private InfoRoleMenuDao infoRoleMenuDao;

	@Resource
	private InfoRoleActionDao infoRoleActionDao;

	@Resource
	private InfoUserModuleDao infoUserModuleDao;
	
	@Resource
	private InfoUserMenuDao infoUserMenuDao;
	
	@Resource
	private InfoUserActionDao infoUserActionDao;

	@Resource
	private EnterpriseStorageMoneyService enterpriseStorageMoneyService;

	@Override
	public Page<InfoEnterprise> getOperation(Page<InfoEnterprise> pager, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoEnterprise> lstData = infoEnterpriseDao.queryOperation(name);

		// 获取分页后所需信息
		PageInfo<InfoEnterprise> pageInfo = new PageInfo<InfoEnterprise>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<InfoEnterprise> getAllShop(Page<InfoEnterprise> pager, Integer companyType, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoEnterprise> lstData = infoEnterpriseDao.queryShop(companyType, name);

		// 获取分页后所需信息
		PageInfo<InfoEnterprise> pageInfo = new PageInfo<InfoEnterprise>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<InfoEnterprise> getMyShop(Page<InfoEnterprise> pager, Long myId, Integer companyType, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoEnterprise> lstData = null ;
		if(companyType == GrobalConstant.I_SHOP_SUPPLIER) {
			lstData = infoEnterpriseDao.queryMySupplier(myId, name);
		} else if(companyType == GrobalConstant.I_SHOP_DISTRIBUTOR) {
			lstData = infoEnterpriseDao.queryMyDistributor(myId, name);
		} 

		// 获取分页后所需信息
		PageInfo<InfoEnterprise> pageInfo = new PageInfo<InfoEnterprise>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public InfoEnterprise getById(Long id) {
		return infoEnterpriseDao.queryById(id);
	}

	@Override
	public InfoEnterprise getByName(String name) {
		return infoEnterpriseDao.queryByName(name);
	}

	@Override
	public InfoEnterprise getByDoamin(String domain) {
		return infoEnterpriseDao.queryByDomain(domain);
	}

	@Override
	public void save(InfoEnterprise enterprise, InfoEnterpriseRelation relation, InfoUser user, EnterpriseCapitalPo enterpriseCapital) {
		// 保存企业信息
		infoEnterpriseDao.insert(enterprise);
		
		// 保存企业-关系信息（企业上下级关系）
		if(!BaseUtil.isEmpty(relation) && !BaseUtil.isEmpty(relation.getCreateEnterprise())) {
			if(relation.getCreateType() == 2) {
				relation.setChildId(relation.getCreateEnterprise());
				relation.setParentId(enterprise.getId());
			} else if (relation.getCreateType() == 3) {
				relation.setChildId(enterprise.getId());
				relation.setParentId(relation.getCreateEnterprise());
			}
			infoEnterpriseRelationDao.insert(relation);
		} 
		
		// 保存企业-平台资金信息
		enterpriseCapital.setEnterpriseId(enterprise.getId());
		enterpriseCapitalDao.insert(enterpriseCapital);

		// 保存企业-主账号信息
		user.setEnterpriseId(enterprise.getId());
		userDao.insert(user);
		// 为企业主账号分配功能
		saveUserFunc(user, user.getCreateId());
	}
	
	@Override
	public void update(InfoEnterprise enterprise, InfoUser user) {
		// 修改企业信息
		if(!BaseUtil.isEmpty(enterprise)) {
			infoEnterpriseDao.update(enterprise);
		}
		
		// 修改企业-主账号信息
		/*if(!BaseUtil.isEmpty(user)) {
			userDao.updateUsernameAndPassword(user);
		}*/
	}

	@Override
	public void update(InfoEnterprise entity) {
		infoEnterpriseDao.update(entity);
	}

	@Override
	public OperatorMessageVo setStatus(Long id, Integer status) {
		if (status == 1) {
			infoEnterpriseDao.updateStatus(id, status);
			return new OperatorSuccessVo("设置启用成功！");
		} else {
			// 查询可用的下级公司数量
			int iChildCount = infoEnterpriseDao.queryChildCount(id);
			if (iChildCount == 0) {
				infoEnterpriseDao.updateStatus(id, status);
				return new OperatorSuccessVo("设置停用成功！");
			} else {
				return new OperatorFailureVo("公司有下级商户，不能设置停用！");
			}
		}
	}

	@Override
	public void delete(Long id) {
		infoEnterpriseDao.delete(id);
	}

	/**
	 * 为用户分配权限下的功能
	 * @param infoUser 用户信息
	 * @param currentUserId 操作人Id
	 */
	private void saveUserFunc(InfoUser infoUser, Long currentUserId) {
		// 分配模块
		List<InfoRoleModule> lstModule = infoRoleModuleDao.queryAll(infoUser.getRoleId());
		for (InfoRoleModule roleModule : lstModule) {
			InfoUserModule userModule = new InfoUserModule();
			userModule.setEnterpriseId(infoUser.getEnterpriseId());
			userModule.setUserId(infoUser.getId());
			userModule.setModuleId(roleModule.getModuleId());
			userModule.setSortNo(roleModule.getSortNo());
			userModule.setCreateId(currentUserId);
			userModule.setUpdateId(currentUserId);
			infoUserModuleDao.insert(userModule);

			// 分配菜单
			Map<String, Object> mapSearchMenu = new HashMap<String, Object>();
			mapSearchMenu.put("roleId", infoUser.getRoleId());
			mapSearchMenu.put("moduleId", roleModule.getModuleId());
			List<InfoRoleMenu> lstMenu = infoRoleMenuDao.queryByModule(mapSearchMenu);
			for (InfoRoleMenu roleMenu : lstMenu) {
				InfoUserMenu userMenu = new InfoUserMenu();
				userMenu.setEnterpriseId(infoUser.getEnterpriseId());
				userMenu.setUserId(infoUser.getId());
				userMenu.setModuleId(roleModule.getModuleId());
				userMenu.setMenuId(roleMenu.getMenuId());
				userMenu.setSortNo(roleMenu.getSortNo());
				userMenu.setCreateId(currentUserId);
				userMenu.setUpdateId(currentUserId);
				infoUserMenuDao.insert(userMenu);

				// 分配菜单功能
				Map<String, Object> mapSearchAction = new HashMap<String, Object>();
				mapSearchAction.put("moduleId", roleModule.getModuleId());
				mapSearchAction.put("menuId", roleMenu.getMenuId());
				mapSearchAction.put("roleId", infoUser.getRoleId());
				List<InfoRoleAction> lstAction = infoRoleActionDao.queryByModuleAndMenu(mapSearchAction);
				for (InfoRoleAction roleAction : lstAction) {
					InfoUserAction userAction = new InfoUserAction();
					userAction.setEnterpriseId(infoUser.getEnterpriseId());
					userAction.setUserId(infoUser.getId());
					userAction.setModuleId(roleModule.getModuleId());
					userAction.setMenuId(roleMenu.getMenuId());
					userAction.setActionId(roleAction.getActionId());
					userAction.setSortNo(roleAction.getSortNo());
					userAction.setCreateId(currentUserId);
					userAction.setUpdateId(currentUserId);
					infoUserActionDao.insert(userAction);
				}
			}
		}
	}

	@Override
	public void updateUsernameAndPassword(InfoUser entity) {
		userDao.update(entity);
	}

	@Override
	public void updateWebsiteInfo(InfoEnterprise entity) {
		infoEnterpriseDao.updateWebsiteInfo(entity);
	}

	@Override
	public Page<InfoEnterprise> getMyDistributor(Page<InfoEnterprise> pager, String name,Long parentId) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<InfoEnterprise> lstData = infoEnterpriseDao.querySaleDistributor(name,parentId);

		// 获取分页后所需信息
		PageInfo<InfoEnterprise> pageInfo = new PageInfo<InfoEnterprise>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	@Override
	public List<InfoEnterprise> getMyDistributorNoPage(String name, Long parentId) {
		List<InfoEnterprise> lstData = infoEnterpriseDao.querySaleDistributor(name,parentId);
		return lstData;
	}

	@Override
	public void updateEnterpriseAccount(Long enterpriseId, String account) {
		infoEnterpriseDao.updateEnterpriseAccount(enterpriseId, account);
	}

}