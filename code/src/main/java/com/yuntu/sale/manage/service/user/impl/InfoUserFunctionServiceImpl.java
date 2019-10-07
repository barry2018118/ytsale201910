package com.yuntu.sale.manage.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.manage.dao.user.InfoUserActionDao;
import com.yuntu.sale.manage.dao.user.InfoUserMenuDao;
import com.yuntu.sale.manage.dao.user.InfoUserModuleDao;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.user.InfoUserFunctionService;

@Service("userFunctionService")
public class InfoUserFunctionServiceImpl implements InfoUserFunctionService {
	@Resource
	private InfoUserModuleDao userModuleDao;
	
	@Resource
	private InfoUserMenuDao userMenuDao ;
	
	@Resource
	private InfoUserActionDao userActionDao ;
	
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	
	/**
	 * 得到给用户分配的功能（模块-菜单-功能-按钮<子功能>）
	 */
	public List<FuncModule> getFunction(Long id) {
		// 得到所有功能模块
		List<InfoUserModule> lstUserModule = userModuleDao.queryByUser(id);
		List<FuncModule> lstModule = null ;
		if(lstUserModule!=null && !lstUserModule.isEmpty()) {
			lstModule = new ArrayList<FuncModule>(lstUserModule.size()) ;
			// 得到功能模块下的菜单
			for(InfoUserModule userModule : lstUserModule) {
				FuncModule module = funcModuleService.getById(userModule.getModuleId()) ;
				Map<String, Object> mapUserMenuCondition = new HashMap<String, Object>() ;
				mapUserMenuCondition.put("userId", id) ;
				mapUserMenuCondition.put("moduleId", userModule.getModuleId()) ;
				List<InfoUserMenu> lstUserMenu = userMenuDao.queryByUser(mapUserMenuCondition);
				List<FuncMenu> lstMenu = null ;
				if(!lstUserMenu.isEmpty()) {
					lstMenu = new ArrayList<FuncMenu>(lstUserMenu.size()) ;
					// 得到功能菜单下的动作
					for(InfoUserMenu userMenu : lstUserMenu) {
						FuncMenu menu = funcMenuService.getById(userMenu.getMenuId()) ;
						Map<String, Object> mapUserActionCondition = new HashMap<String, Object>() ;
						mapUserActionCondition.put("userId", id) ;
						mapUserActionCondition.put("menuId", userMenu.getMenuId()) ;
						List<InfoUserAction> lstUserAction = userActionDao.queryByUser(mapUserActionCondition);
						List<FuncAction> lstAction = null ;
						if(!lstUserAction.isEmpty()) {
							lstAction = new ArrayList<FuncAction>(lstUserAction.size()) ;
							// 得到功能动作下的按钮
							for(InfoUserAction userAction : lstUserAction) {
								FuncAction action = funcActionService.getById(userAction.getActionId()) ;
								Map<String, Object> mapUserButtonCondition = new HashMap<String, Object>() ;
								mapUserButtonCondition.put("roleId", id) ;
								mapUserButtonCondition.put("actionId", userAction.getActionId()) ;
								List<FuncButton> lstButton = null ;
								action.setButtons(lstButton) ;
								lstAction.add(action) ;
							}
						}
						menu.setActions(lstAction) ;
						lstMenu.add(menu) ;
					}
				}
				module.setMenus(lstMenu) ;
				lstModule.add(module) ;
			}
		}
		return lstModule ;
	}
	
	/**
	 * 得到给权限组分配的功能（模块-菜单-功能-按钮<子功能>）
	 * 稍稍提高效率
	 * @param id
	 * @return
	 */
	public List<FuncModule> getFuctions(Long id){/*
		List<FuncModule> lstmodule = userModuleDao.queryByUser(id);
		List<FuncMenu> lstmenu = userMenuDao.queryAllByRoleId(id);
		List<FuncAction> lstaction = userActionDao.queryAllByRoleId(id);
		for (FuncModule module : lstmodule) {
			for (FuncMenu menu : lstmenu) {
				if(menu.getModuleId().equals(module.getId())){
					if(module.getMenus()==null){
						module.setMenus(new ArrayList<FuncMenu>());
					}
					module.getMenus().add(menu);
					for (FuncAction action : lstaction) {
						if(action.getMenuId().equals(menu.getId())){
							if(menu.getActions()==null){
								menu.setActions(new ArrayList<FuncAction>());
							}
							menu.getActions().add(action);
						}
					}
				}
			}
		}
		return lstmodule;
	*/	List<FuncModule> aa = new ArrayList<FuncModule>();
		return aa;
	}

	public boolean haveModule(Long entityId, long moduleId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", entityId) ;
		mapCondition.put("moduleId", moduleId) ;
		InfoUserModule userModule = userModuleDao.queryByUserIdAndModuleId(mapCondition);
		return !BaseUtil.isEmpty(userModule)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveMenu(Long entityId, long menuId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", entityId) ;
		mapCondition.put("menuId", menuId) ;
		InfoUserMenu userMenu = userMenuDao.queryByUserIdAndMenuId(mapCondition);
		return !BaseUtil.isEmpty(userMenu)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveAction(Long entityId, long actionId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", entityId) ;
		mapCondition.put("actionId", actionId) ;
		InfoUserAction userAction = userActionDao.queryByUserIdAndActionId(mapCondition);
		return !BaseUtil.isEmpty(userAction)?Boolean.TRUE:Boolean.FALSE ;
	}

	@Override
	public void setFunction(Long entityId, List<Long> lstModuleId,List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 为用户设置功能
	 */
	@Override
	public void setFunction(Long entityId,Long enterpriseId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId,  Long currentUserId) {
		// 删除之前给用户分配的功能（模块、菜单、功能、按钮<子功能>）
		List<InfoUserModule> lstUserModule = userModuleDao.queryByUser(entityId);
		for(InfoUserModule userModule : lstUserModule) {
			userModuleDao.deleteById(userModule.getId()) ;
		}
		List<InfoUserMenu> lstUserMenu = userMenuDao.queryByUserId(entityId) ;
		for(InfoUserMenu userMenu : lstUserMenu) {
			userMenuDao.deleteById(userMenu.getId()) ;
		}
		List<InfoUserAction> lstUserAciton = userActionDao.queryByUserId(entityId);
		for(InfoUserAction userAction : lstUserAciton) {
			userActionDao.deleteById(userAction.getId());
		}
		
		// 给用户分配新功能（模块、菜单、功能、按钮<子功能>）
		for(Long moduleId : lstModuleId) {
			InfoUserModule userModule = new InfoUserModule(enterpriseId, entityId, moduleId, currentUserId) ;
			if(!BaseUtil.isEmpty(userModule)) {
				userModuleDao.insert(userModule) ;
			}
		}
		for(Long menuId : lstMenuId) {
			FuncMenu menu= funcMenuService.getById(menuId) ;
			if(!BaseUtil.isEmpty(menu)) {
				InfoUserMenu userMenu = new InfoUserMenu(enterpriseId,  entityId,
						menu.getModuleId(), menuId, currentUserId) ;
				userMenuDao.insert(userMenu) ;
			}
		}
		for(Long actionId : lstActionId) {
			FuncAction action = funcActionService.getById(actionId) ;
			if(!BaseUtil.isEmpty(action)) {
				InfoUserAction userAction = new InfoUserAction(enterpriseId, entityId, action.getModuleId(),
						action.getMenuId(), actionId, currentUserId);
				userActionDao.insert(userAction) ;
			}
		}
	}
	
	/*public List<FuncModule> getSomeRolesFunction(List<Long> roleIds){
		Map<Long,FuncModule> mapModule= new HashMap<Long,FuncModule>();
		Map<Long,FuncMenu> mapMenu= new HashMap<Long,FuncMenu>();
		Map<Long,FuncAction> mapAction= new HashMap<Long,FuncAction>();
		Map<Long,FuncButton> mapButton= new HashMap<Long,FuncButton>();
		for (Long id : roleIds) {
			List<FuncModule> lstmodule = userModuleDao.queryAllByRoleId(id);
			for (FuncModule funcModule : lstmodule) {
				mapModule.put(funcModule.getId(),funcModule);
			}
			List<FuncMenu> lstmenu = userMenuDao.queryAllByRoleId(id);
			for (FuncMenu funcMenu : lstmenu) {
				mapMenu.put(funcMenu.getId(), funcMenu);
			}
			List<FuncAction> lstaction = userActionDao.queryAllByRoleId(id);
			for (FuncAction funcAction : lstaction) {
				mapAction.put(funcAction.getId(), funcAction);
			}
		}
		List<FuncModule> lstmodule =new ArrayList<FuncModule>();
		List<FuncMenu> lstmenu = new ArrayList<FuncMenu>();
		List<FuncAction> lstaction = new ArrayList<FuncAction>();
		List<FuncButton> lstbutton = new ArrayList<FuncButton>();
		Set<Entry<Long, FuncModule>> setModule = mapModule.entrySet();
		for (Entry<Long, FuncModule> entry : setModule) {
			lstmodule.add(entry.getValue());
		}
		Set<Entry<Long, FuncMenu>> setMenu = mapMenu.entrySet();
		for (Entry<Long, FuncMenu> entry : setMenu) {
			lstmenu.add(entry.getValue());
		}
		Set<Entry<Long, FuncAction>> setAction = mapAction.entrySet();
		for (Entry<Long, FuncAction> entry : setAction) {
			lstaction.add(entry.getValue());
		}
		Set<Entry<Long, FuncButton>> setButton = mapButton.entrySet();
		for (Entry<Long, FuncButton> entry : setButton) {
			lstbutton.add(entry.getValue());
		}
		for (FuncModule module : lstmodule) {
			for (FuncMenu menu : lstmenu) {
				if(menu.getModuleId().equals(module.getId())){
					if(module.getMenus()==null){
						module.setMenus(new ArrayList<FuncMenu>());
					}
					module.getMenus().add(menu);
					for (FuncAction action : lstaction) {
						if(action.getMenuId().equals(menu.getId())){
							if(menu.getActions()==null){
								menu.setActions(new ArrayList<FuncAction>());
							}
							menu.getActions().add(action);
							for (FuncButton button : lstbutton) {
								if(action.getButtons()==null){
									action.setButtons(new ArrayList<FuncButton>());
								}
								if(button.getActionId().equals(action.getId()))
									action.getButtons().add(button);
							}
						}
					}
				}
			}
		}
		return lstmodule;
	}*/


	@Override
	public boolean haveButton(Long entityId, long buttonId) {
		return false;
	}

	@Override
	public List<FuncModule> getSomeUserFunction(List<Long> roleIds) {
		// TODO Auto-generated method stub
		return null;
	}


	

}