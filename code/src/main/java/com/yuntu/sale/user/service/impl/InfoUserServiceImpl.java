package com.yuntu.sale.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.base.po.role.InfoRoleAction;
import com.yuntu.sale.base.po.role.InfoRoleMenu;
import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.manage.dao.role.InfoRoleActionDao;
import com.yuntu.sale.manage.dao.role.InfoRoleDao;
import com.yuntu.sale.manage.dao.role.InfoRoleMenuDao;
import com.yuntu.sale.manage.dao.role.InfoRoleModuleDao;
import com.yuntu.sale.manage.dao.user.InfoUserActionDao;
import com.yuntu.sale.manage.dao.user.InfoUserMenuDao;
import com.yuntu.sale.manage.dao.user.InfoUserModuleDao;
import com.yuntu.sale.user.dao.InfoUserDao;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

@Service("infoUserService")
public class InfoUserServiceImpl implements InfoUserService {

	@Resource
	private InfoUserDao infoUserDao;
	
	@Resource
	private InfoUserModuleDao infoUserModuleDao;
	
	@Resource
	private InfoUserMenuDao infoUserMenuDao;
	
	@Resource
	private InfoUserActionDao infoUserActionDao;
	
	@Resource
	private InfoRoleDao infoRoleDao;
	
	@Resource
	private InfoRoleModuleDao infoRoleModuleDao;
	
	@Resource
	private InfoRoleMenuDao infoRoleMenuDao;
	
	@Resource
	private InfoRoleActionDao infoRoleActionDao;
	
	@Override
	public Page<InfoUser> getUser(Page<InfoUser> pager, String username, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoUser> lstData = infoUserDao.queryUser(username, name);

		// 获取分页后所需信息
		PageInfo<InfoUser> pageInfo = new PageInfo<InfoUser>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	@Override
	public Page<InfoUser> getMyUser(Page<InfoUser> pager, Long enterpriseId, String username, String name) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());

		// 查询数据
		List<InfoUser> lstData = infoUserDao.queryMyUser(enterpriseId, username, name);

		// 获取分页后所需信息
		PageInfo<InfoUser> pageInfo = new PageInfo<InfoUser>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	@Override
	public InfoUser login(String username, String password) {
		return infoUserDao.queryByUsernameAndPassword(username, password);
	}
	
	@Override
	public InfoUser getById(Long id) {
		return infoUserDao.queryById(id);
	}
	
	@Override
	public InfoUser getEnterpriseMasterUser(Long enterpriseId) {
		return infoUserDao.getEnterpriseMasterUser(enterpriseId);
	}
	
	@Override
	public InfoUser getByUsername(String username) {
		return infoUserDao.queryByUsername(username);
	}
	
	@Override
	public void save(InfoUser user) {
		infoUserDao.insert(user);
		
		// 给用户分配权限(模块,菜单,子菜单)
		List<InfoRoleModule> rModule = infoRoleModuleDao.queryAll(user.getRoleId());
		for(InfoRoleModule roleModule : rModule) {
			InfoUserModule userModule = new InfoUserModule() ;
			userModule.setUserId(user.getId());
			userModule.setEnterpriseId(user.getEnterpriseId()) ;
			userModule.setModuleId(roleModule.getModuleId()) ;
			userModule.setCreateId(user.getCreateId());
			userModule.setUpdateId(user.getCreateId());
			infoUserModuleDao.insert(userModule) ;
			
			//通过roleId和moduleId查询menuId
			Map <String,Object> map = new HashMap<String,Object>();
			map.put("roleId", user.getRoleId());
			map.put("moduleId", roleModule.getModuleId());
			List<InfoRoleMenu> rMenu = infoRoleMenuDao.queryByModule(map);
			
			for(InfoRoleMenu roleMenu : rMenu) {
				InfoUserMenu userMenu = new InfoUserMenu() ;
				userMenu.setUserId(user.getId());
				userMenu.setEnterpriseId(user.getEnterpriseId()) ;
				userMenu.setModuleId(roleModule.getModuleId()) ;
				userMenu.setMenuId(roleMenu.getMenuId()) ;
				userMenu.setCreateId(user.getCreateId());
				userMenu.setUpdateId(user.getCreateId());
				infoUserMenuDao.insert(userMenu) ;
				
				//通过roleId和moduleId和menuId查询actionId
				Map <String,Object> actionMap = new HashMap<String,Object>();
				actionMap.put("moduleId",roleModule.getModuleId());
				actionMap.put("menuId", roleMenu.getMenuId());
				actionMap.put("roleId", user.getRoleId());
				List<InfoRoleAction> rAction = infoRoleActionDao.queryByModuleAndMenu(actionMap);
				
				for (InfoRoleAction roleAction : rAction) {
					InfoUserAction userAction = new InfoUserAction();
					userAction.setUserId(user.getId());
					userAction.setModuleId(roleModule.getModuleId());
					userAction.setMenuId(roleMenu.getMenuId());
					userAction.setActionId(roleAction.getActionId());
					userAction.setEnterpriseId(user.getEnterpriseId());
					userAction.setCreateId(user.getCreateId());
					userAction.setUpdateId(user.getCreateId());
					infoUserActionDao.insert(userAction);
				}
			}
		}
	}
	
	@Override
	public void update(InfoUser entity) {
		infoUserDao.update(entity);
	}

	@Override
	public OperatorMessageVo setStatus(Long id, Integer status) {
		if (status == 1) {
			infoUserDao.updateStatus(id, status);
			return new OperatorSuccessVo("设置启用成功！");
		} else if (status == 0){
				infoUserDao.updateStatus(id, status);
				return new OperatorSuccessVo("设置停用成功！");
		} else {
				return new OperatorFailureVo("没有这个选项！");

		}
	}
	
	@Override
	public void delete(Long id) {
		infoUserDao.delete(id);
	}
	
	@Override
	public void updatePassword(InfoUser user) {
		infoUserDao.updatePassword(user);
	}

}