package com.yuntu.sale.manage.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.role.InfoRoleAction;
import com.yuntu.sale.base.po.role.InfoRoleMenu;
import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.manage.dao.role.InfoRoleActionDao;
import com.yuntu.sale.manage.dao.role.InfoRoleMenuDao;
import com.yuntu.sale.manage.dao.role.InfoRoleModuleDao;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.role.InfoRoleFunctionService;

@Service("roleFunctionService")
public class InfoRoleFunctionServiceImpl implements InfoRoleFunctionService {
	@Resource
	private InfoRoleModuleDao roleModuleDao;
	
	@Resource
	private InfoRoleMenuDao roleMenuDao ;
	
	@Resource
	private InfoRoleActionDao roleActionDao ;
	
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	
	/**
	 * 得到给权限组分配的功能（模块-菜单-功能-按钮<子功能>）
	 */
	public List<FuncModule> getFunction(Long id) {
		// 得到所有功能模块
		List<InfoRoleModule> lstRoleModule = roleModuleDao.queryAll(id);
		List<FuncModule> lstModule = null ;
		if(lstRoleModule!=null && !lstRoleModule.isEmpty()) {
			lstModule = new ArrayList<FuncModule>(lstRoleModule.size()) ;
			// 得到功能模块下的菜单
			for(InfoRoleModule roleModule : lstRoleModule) {
				FuncModule module = funcModuleService.getById(roleModule.getModuleId()) ;
				Map<String, Object> mapRoleMenuCondition = new HashMap<String, Object>() ;
				mapRoleMenuCondition.put("roleId", id) ;
				mapRoleMenuCondition.put("moduleId", roleModule.getModuleId()) ;
				List<InfoRoleMenu> lstRoleMenu = roleMenuDao.queryByModule(mapRoleMenuCondition);
				List<FuncMenu> lstMenu = null ;
				if(!lstRoleMenu.isEmpty()) {
					lstMenu = new ArrayList<FuncMenu>(lstRoleMenu.size()) ;
					// 得到功能菜单下的动作
					for(InfoRoleMenu roleMenu : lstRoleMenu) {
						FuncMenu menu = funcMenuService.getById(roleMenu.getMenuId()) ;
						Map<String, Object> mapRoleActionCondition = new HashMap<String, Object>() ;
						mapRoleActionCondition.put("roleId", id) ;
						mapRoleActionCondition.put("menuId", roleMenu.getMenuId()) ;
						 List<InfoRoleAction> lstRoleAction = roleActionDao.queryByMenu(mapRoleMenuCondition);
						List<FuncAction> lstAction = null ;
						if(!lstRoleAction.isEmpty()) {
							lstAction = new ArrayList<FuncAction>(lstRoleAction.size()) ;
							// 得到功能动作下的按钮
							for(InfoRoleAction roleAction : lstRoleAction) {
								FuncAction action = funcActionService.getById(roleAction.getActionId()) ;
								Map<String, Object> mapRoleButtonCondition = new HashMap<String, Object>() ;
								mapRoleButtonCondition.put("roleId", id) ;
								mapRoleButtonCondition.put("actionId", roleAction.getActionId()) ;
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
	public List<FuncModule> getFuctions(Long id){
		List<FuncModule> lstmodule = roleModuleDao.queryAllByRoleId(id);
		List<FuncMenu> lstmenu = roleMenuDao.queryAllByRoleId(id);
		List<FuncAction> lstaction = roleActionDao.queryAllByRoleId(id);
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
	}

	public boolean haveModule(Long entityId, long moduleId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("roleId", entityId) ;
		mapCondition.put("moduleId", moduleId) ;
		InfoRoleModule roleModule = roleModuleDao.queryModule(mapCondition);
		return !BaseUtil.isEmpty(roleModule)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveMenu(Long entityId, long menuId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("roleId", entityId) ;
		mapCondition.put("menuId", menuId) ;
		InfoRoleMenu roleMenu = roleMenuDao.queryMenu(mapCondition);
		return !BaseUtil.isEmpty(roleMenu)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveAction(Long entityId, long actionId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("roleId", entityId) ;
		mapCondition.put("actionId", actionId) ;
		InfoRoleAction roleAction = roleActionDao.queryAction(mapCondition);
		return !BaseUtil.isEmpty(roleAction)?Boolean.TRUE:Boolean.FALSE ;
	}


	/**
	 * 为角色设置功能
	 */
	public void setFunction(Long entityId, Long enterpriseId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId,  Long currentUserId) {
		// 删除之前给权限组分配的功能（模块、菜单、功能、按钮<子功能>）
		List<InfoRoleModule> lstRoleModule = roleModuleDao.queryAll(entityId);
		for(InfoRoleModule roleModule : lstRoleModule) {
			roleModuleDao.delete(roleModule.getId()) ;
		}
		List<InfoRoleMenu> lstRoleMenu = roleMenuDao.queryAll(entityId) ;
		for(InfoRoleMenu roleMenu : lstRoleMenu) {
			roleMenuDao.delete(roleMenu.getId()) ;
		}
		List<InfoRoleAction> lstRoleAciton = roleActionDao.queryAll(entityId);
		for(InfoRoleAction roleAction : lstRoleAciton) {
			roleActionDao.delete(roleAction.getId());
		}
		
		// 给权限组分配新功能（模块、菜单、功能、按钮<子功能>）
		for(Long moduleId : lstModuleId) {
			InfoRoleModule roleModule = new InfoRoleModule(enterpriseId, entityId, moduleId, currentUserId);
			roleModule.setSortNo(funcModuleService.getById(moduleId).getSortNo());
			if(!BaseUtil.isEmpty(roleModule)) {
				roleModuleDao.insert(roleModule);
			}
		}
		
		for(Long menuId : lstMenuId) {
			FuncMenu menu= funcMenuService.getById(menuId);
			if(!BaseUtil.isEmpty(menu)) {
				InfoRoleMenu roleMenu = new InfoRoleMenu(enterpriseId, entityId, menu.getModuleId(), menuId, currentUserId);
				roleMenu.setSortNo(menu.getSortNo());
				roleMenuDao.insert(roleMenu);
			}
		}
		
		for(Long actionId : lstActionId) {
			FuncAction action = funcActionService.getById(actionId) ;
			if(!BaseUtil.isEmpty(action)) {
				InfoRoleAction roleAction = new InfoRoleAction(enterpriseId, entityId, 
						action.getModuleId(), action.getMenuId(), actionId, currentUserId);
				roleAction.setSortNo(action.getSortNo());
				roleActionDao.insert(roleAction) ;
			}
		}
	}
	
	public List<FuncModule> getSomeRolesFunction(List<Long> roleIds){
		Map<Long,FuncModule> mapModule= new HashMap<Long,FuncModule>();
		Map<Long,FuncMenu> mapMenu= new HashMap<Long,FuncMenu>();
		Map<Long,FuncAction> mapAction= new HashMap<Long,FuncAction>();
		Map<Long,FuncButton> mapButton= new HashMap<Long,FuncButton>();
		for (Long id : roleIds) {
			List<FuncModule> lstmodule = roleModuleDao.queryAllByRoleId(id);
			for (FuncModule funcModule : lstmodule) {
				mapModule.put(funcModule.getId(),funcModule);
			}
			List<FuncMenu> lstmenu = roleMenuDao.queryAllByRoleId(id);
			for (FuncMenu funcMenu : lstmenu) {
				mapMenu.put(funcMenu.getId(), funcMenu);
			}
			List<FuncAction> lstaction = roleActionDao.queryAllByRoleId(id);
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
	}

	public void setFunction(Long entityId, List<Long> lstModuleId,
			List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) {
	}

	@Override
	public boolean haveButton(Long entityId, long buttonId) {
		return false;
	}

}