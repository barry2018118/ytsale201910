package com.yuntu.sale.manage.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserButton;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.manage.dao.role.InfoRoleModuleDao;
import com.yuntu.sale.manage.dao.user.InfoUserModuleDao;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.user.InfoUserActionService;
import com.yuntu.sale.manage.service.user.InfoUserButtonService;
import com.yuntu.sale.manage.service.user.InfoUserMenuService;
import com.yuntu.sale.manage.service.user.InfoUserModuleService;

@Service("userModuleService")
public class InfoUserModuleServiceImpl implements InfoUserModuleService {
	@Resource
	private InfoUserModuleDao dao ;
	
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private InfoUserMenuService userMenuService ;
	
	@Resource
	private InfoUserActionService userActoinService ;
	
	@Resource
	private InfoUserButtonService userButtonService ;
	
	@Resource
	private InfoRoleModuleDao infoRoleModuleDao;
	
	
	public List<InfoUserModule> getByUser(long userId) {
		List<InfoUserModule> lstUserModule =dao.queryByUser(userId) ;
		if(BaseUtil.isEmpty(lstUserModule)) {
			return null ;
		}
		
		for(InfoUserModule userModule : lstUserModule) {
			FuncModule module = funcModuleService.getById(userModule.getModuleId()) ;
			if(!BaseUtil.isEmpty(module)) {
				userModule.setModuleName(module.getName()) ;
				userModule.setModuleIcon(module.getIcon()) ;
				userModule.setModuleUrl(module.getUrl()) ;
			}
		}
		return lstUserModule ;
	}

	/**
	 * 得到用户的功能权限
	 */
	public List<InfoUserModule> getUserFunction(long userId) {
		// 得到用户_模块
		List<InfoUserModule> lstUserModule =dao.queryByUser(userId) ;
		if(BaseUtil.isEmpty(lstUserModule)) {
			return null ;
		}
		
		for(InfoUserModule userModule : lstUserModule) {
			FuncModule module = funcModuleService.getById(userModule.getModuleId()) ;
			if(!BaseUtil.isEmpty(module)) {
				userModule.setModuleName(module.getName()) ;
			}
			// 得到用户_菜单
			List<InfoUserMenu> lstUserMenu = userMenuService.getUserMenu(userId, userModule.getModuleId()) ;
			if(!BaseUtil.isEmpty(lstUserMenu)) {
				for(InfoUserMenu userMenu : lstUserMenu) {
					// 得到用户_功能（动作）
					List<InfoUserAction> lstUserAction = userActoinService.getUserAction(userId, userMenu.getMenuId()) ;
					if(!BaseUtil.isEmpty(lstUserAction)) {
						for(InfoUserAction userAction : lstUserAction) {
							// 得到用户_按钮
							List<InfoUserButton> lstUserButton = userButtonService.getUserButton(userId, userAction.getActionId()) ;
							userAction.setUserButton(lstUserButton) ;
						}
						userMenu.setUserAction(lstUserAction) ;
					}
				}
				userModule.setUserMenu(lstUserMenu) ;
			}
		}
		return lstUserModule ;
	}

	public InfoUserModule getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoUserModule entity) {
		return dao.insert(entity) ;
	}

	public void edit(InfoUserModule entity) {
		dao.update(entity) ;
	}

	public void remove(long id) {
		dao.deleteById(id) ;
	}
	
	public void removeByUser(long userId) {
		dao.deleteByUser(userId) ;
	}

	public boolean checkUserModule(long userId, long moduleId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("moduleId", moduleId) ;
		InfoUserModule infoUserModule = dao.queryByUserIdAndModuleId(mapCondition) ;
		return BaseUtil.isEmpty(infoUserModule)?Boolean.FALSE:Boolean.TRUE ;
	}

	@Override
	public List<InfoRoleModule> getByRole(long roleId) {
		return infoRoleModuleDao.queryAll(roleId);
	}
	
}