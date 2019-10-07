package com.yuntu.sale.manage.service.enterprise.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.enterprise.InfoEnterpriseAction;
import com.yuntu.sale.base.po.enterprise.InfoEnterpriseButton;
import com.yuntu.sale.base.po.enterprise.InfoEnterpriseMenu;
import com.yuntu.sale.base.po.enterprise.InfoEnterpriseModule;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseActionService;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseButtonService;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseMenuService;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseModuleService;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncButtonService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.manage.service.user.InfoUserActionService;
import com.yuntu.sale.manage.service.user.InfoUserButtonService;
import com.yuntu.sale.manage.service.user.InfoUserMenuService;
import com.yuntu.sale.manage.service.user.InfoUserModuleService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

@Service("enterpriseFunctionService")
public class InfoEnterpriseFunctionServiceImpl implements FunctionService {
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	@Resource
	private FuncButtonService funcButtonService ;
	
	@Resource
	private InfoEnterpriseModuleService enterpriseModuleService ;
	
	@Resource
	private InfoEnterpriseMenuService enterpriseMenuService ;
	
	@Resource
	private InfoEnterpriseActionService enterpriseActionService ;
	
	@Resource
	private InfoEnterpriseButtonService enterpriseButtonService ;
	
	@Resource
	private InfoUserModuleService userModuleService ;
	
	@Resource
	private InfoUserMenuService userMenuService ;
	
	@Resource
	private InfoUserActionService userActionService ;
	
	@Resource
	private InfoUserButtonService userButtonService ;
	
	@Resource
	private InfoUserService userService ;
	
	
	/**
	 * 得到给企业分配的功能（模块-菜单-功能-按钮<子功能>）
	 */
	public List<FuncModule> getFunction(Long id) {
		// 得到所有功能模块
		List<InfoEnterpriseModule> lstEnterpriseModule = enterpriseModuleService.getAll(id) ;
		List<FuncModule> lstModule = null ;
		if(!lstEnterpriseModule.isEmpty()) {
			lstModule = new ArrayList<FuncModule>(lstEnterpriseModule.size()) ;
			// 得到功能模块下的菜单
			for(InfoEnterpriseModule enterpriseModule : lstEnterpriseModule) {
				FuncModule module = funcModuleService.getById(enterpriseModule.getModuleId()) ;
				Map<String, Object> mapEnterpriseMenuCondition = new HashMap<String, Object>() ;
				mapEnterpriseMenuCondition.put("enterpriseId", id) ;
				mapEnterpriseMenuCondition.put("moduleId", enterpriseModule.getModuleId()) ;
				List<InfoEnterpriseMenu> lstEnterpriseMenu = enterpriseMenuService.getByModule(mapEnterpriseMenuCondition) ;
				List<FuncMenu> lstMenu = null ;
				if(!lstEnterpriseMenu.isEmpty()) {
					lstMenu = new ArrayList<FuncMenu>(lstEnterpriseMenu.size()) ;
					// 得到功能菜单下的动作
					for(InfoEnterpriseMenu enterpriseMenu : lstEnterpriseMenu) {
						FuncMenu menu = funcMenuService.getById(enterpriseMenu.getMenuId()) ;
						Map<String, Object> mapEnterpriseActionCondition = new HashMap<String, Object>() ;
						mapEnterpriseActionCondition.put("enterpriseId", id) ;
						mapEnterpriseActionCondition.put("menuId", enterpriseMenu.getMenuId()) ;
						List<InfoEnterpriseAction> lstEnterpriseAction = enterpriseActionService.getByMenu(mapEnterpriseActionCondition) ;
						List<FuncAction> lstAction = null ;
						if(!lstEnterpriseAction.isEmpty()) {
							lstAction = new ArrayList<FuncAction>(lstEnterpriseAction.size()) ;
							// 得到功能动作下的按钮
							for(InfoEnterpriseAction enterpriseAction : lstEnterpriseAction) {
								FuncAction action = funcActionService.getById(enterpriseAction.getActionId()) ;
								Map<String, Object> mapEnterpriseButtonCondition = new HashMap<String, Object>() ;
								mapEnterpriseButtonCondition.put("enterpriseId", id) ;
								mapEnterpriseButtonCondition.put("actionId", enterpriseAction.getActionId()) ;
								List<InfoEnterpriseButton> lstEnterpriseButton = enterpriseButtonService.getByAction(mapEnterpriseButtonCondition) ;
								List<FuncButton> lstButton = null ;
								if(!lstEnterpriseButton.isEmpty()) {
									lstButton = new ArrayList<FuncButton>(lstEnterpriseButton.size()) ;
									for(InfoEnterpriseButton enterpriseButton : lstEnterpriseButton) {
										FuncButton button = funcButtonService.getById(enterpriseButton.getButtonId()) ;
										lstButton.add(button) ;
									}
								}
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

	public boolean haveModule(Long entityId, long moduleId) {
		InfoEnterpriseModule enterpriseModule = enterpriseModuleService.getEnterpriseModule(entityId, moduleId) ;
		return !BaseUtil.isEmpty(enterpriseModule)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveMenu(Long entityId, long menuId) {
		InfoEnterpriseMenu enterpriseMenu = enterpriseMenuService.getEnterpriseMenu(entityId, menuId) ;
		return !BaseUtil.isEmpty(enterpriseMenu)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveAction(Long entityId, long actionId) {
		InfoEnterpriseAction enterpriseAction = enterpriseActionService.getEnterpriseAction(entityId, actionId) ;
		return !BaseUtil.isEmpty(enterpriseAction)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveButton(Long entityId, long buttonId) {
		InfoEnterpriseButton enterpriseButton = enterpriseButtonService.getEnterpriseButton(entityId, buttonId) ;
		return !BaseUtil.isEmpty(enterpriseButton)?Boolean.TRUE:Boolean.FALSE ;
	}

	/**
	 * 为企业设置功能
	 */
	public void setFunction(Long entityId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) {
		// 删除之前给企业分配的功能（模块、菜单、功能、按钮<子功能>）
		List<InfoEnterpriseModule> lstEnterpriseModule = enterpriseModuleService.getAll(entityId) ;
		for(InfoEnterpriseModule enterpriseModule : lstEnterpriseModule) {
			enterpriseModuleService.remove(enterpriseModule.getId()) ;
		}
		List<InfoEnterpriseMenu> lstEnterpriseMenu = enterpriseMenuService.getAll(entityId) ;
		for(InfoEnterpriseMenu enterpriseMenu : lstEnterpriseMenu) {
			enterpriseMenuService.remove(enterpriseMenu.getId()) ;
		}
		List<InfoEnterpriseAction> lstEnterpriseAction = enterpriseActionService.getAll(entityId) ;
		for(InfoEnterpriseAction enterpriseAction : lstEnterpriseAction) {
			enterpriseActionService.remove(enterpriseAction.getId()) ;
		}
		List<InfoEnterpriseButton> lstEnterpriseButton = enterpriseButtonService.getAll(entityId) ;
		for(InfoEnterpriseButton enterpriseButton : lstEnterpriseButton) {
			enterpriseButtonService.remove(enterpriseButton.getId()) ;
		}
		
		// 给企业分配新功能（模块、菜单、功能、按钮<子功能>）
		for(Long moduleId : lstModuleId) {
			InfoEnterpriseModule enterpriseModule = new InfoEnterpriseModule() ;
			enterpriseModule.setEnterpriseId(entityId) ;
			enterpriseModule.setCategoryId(0L) ;
			enterpriseModule.setModuleId(moduleId) ;
			enterpriseModule.setCreateId(currentUserId) ;
			enterpriseModule.setUpdateId(currentUserId) ;
			enterpriseModuleService.add(enterpriseModule) ;
		}
		for(Long menuId : lstMenuId) {
			FuncMenu menu= funcMenuService.getById(menuId) ;
			if(!BaseUtil.isEmpty(menu)) {
				InfoEnterpriseMenu enterpriseMenu = new InfoEnterpriseMenu() ;
				enterpriseMenu.setEnterpriseId(entityId) ;
				enterpriseMenu.setModuleId(menu.getModuleId()) ;
				enterpriseMenu.setMenuId(menuId) ;
				enterpriseMenu.setCreateId(currentUserId) ;
				enterpriseMenu.setUpdateId(currentUserId) ;
				enterpriseMenuService.add(enterpriseMenu) ;
			}
		}
		for(Long actionId : lstActionId) {
			FuncAction action = funcActionService.getById(actionId) ;
			if(!BaseUtil.isEmpty(action)) {
				InfoEnterpriseAction enterpriseAction = new InfoEnterpriseAction() ;
				enterpriseAction.setEnterpriseId(entityId) ;
				enterpriseAction.setModuleId(action.getModuleId()) ;
				enterpriseAction.setMenuId(action.getMenuId()) ;
				enterpriseAction.setActionId(actionId) ;
				enterpriseAction.setCreateId(currentUserId) ;
				enterpriseAction.setUpdateId(currentUserId) ;
				enterpriseActionService.add(enterpriseAction) ;
			}
		}
		
		// 为企业管理员分配功能
		setFunctionToManager(entityId, lstModuleId, lstMenuId, lstActionId,  currentUserId) ;
		
		// 如果当前登录用户隶属于平台管理机构，重新添加“功能注册”模块及“企业管理”功能
		if(entityId==100L) {
			setBaseActionToSuperEnterprise() ;
		}
		
	}
	
	/**
	 * 为企业管理员分配功能
	 * @param entityId 企业Id
	 * @param lstModuleId
	 * @param lstMenuId
	 * @param lstActionId
	 * @param lstButtonId
	 * @param currentUserId
	 */
	private void setFunctionToManager(Long entityId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId,  Long currentUserId) {
		// 得到企业管理员
		InfoUser enterpriseManager = userService.getById(1L) ;
		
		// 删除之前给企业管理员分配的功能（模块、菜单、功能、按钮<子功能>）
		userModuleService.removeByUser(enterpriseManager.getId()) ;
		userMenuService.removeByUser(enterpriseManager.getId()) ;
		userActionService.removeByUser(enterpriseManager.getId()) ;
		userButtonService.removeByUser(enterpriseManager.getId()) ;
		
		// 给企业管理员分配新功能（模块、菜单、功能、按钮<子功能>）
		for(Long moduleId : lstModuleId) {
			InfoUserModule userModule = new InfoUserModule() ;
			userModule.setEnterpriseId(entityId) ;
			userModule.setUserId(enterpriseManager.getId()) ;
			userModule.setCategoryId(0L) ;
			userModule.setModuleId(moduleId) ;
			userModule.setCreateId(currentUserId) ;
			userModule.setUpdateId(currentUserId) ;
			userModuleService.add(userModule) ;
		}
		for(Long menuId : lstMenuId) {
			FuncMenu menu= funcMenuService.getById(menuId) ;
			if(!BaseUtil.isEmpty(menu)) {
				InfoUserMenu userMenu = new InfoUserMenu() ;
				userMenu.setEnterpriseId(entityId) ;
				userMenu.setUserId(enterpriseManager.getId()) ;
				userMenu.setModuleId(menu.getModuleId()) ;
				userMenu.setMenuId(menuId) ;
				userMenu.setCreateId(currentUserId) ;
				userMenu.setUpdateId(currentUserId) ;
				userMenuService.add(userMenu) ;
			}
		}
		for(Long actionId : lstActionId) {
			FuncAction action = funcActionService.getById(actionId) ;
			if(!BaseUtil.isEmpty(action)) {
				InfoUserAction userAction = new InfoUserAction() ;
				userAction.setEnterpriseId(entityId) ;
				userAction.setUserId(enterpriseManager.getId()) ;
				userAction.setModuleId(action.getModuleId()) ;
				userAction.setMenuId(action.getMenuId()) ;
				userAction.setActionId(actionId) ;
				userAction.setCreateId(currentUserId) ;
				userAction.setUpdateId(currentUserId) ;
				userActionService.add(userAction) ;
			}
		}
	}
	
	/**
	 * 为平台管理企业用户（及企业管理员）设置基础功能
	 */
	private void setBaseActionToSuperEnterprise() {
		long enterpriseId = 100L ;
		long userId = 1L ;
		long currentUserId = 1L ;
		
		// 为平台管理企业用户设置基础功能
		// Module--功能注册
		if(BaseUtil.isEmpty(enterpriseModuleService.getEnterpriseModule(enterpriseId, 1L))) {
			InfoEnterpriseModule enterpriseModule1 = new InfoEnterpriseModule(enterpriseId, 1L, currentUserId) ;
			enterpriseModuleService.add(enterpriseModule1) ;
		}
		// Module--企业管理
		if(BaseUtil.isEmpty(enterpriseModuleService.getEnterpriseModule(enterpriseId, 2L))) {
			InfoEnterpriseModule enterpriseModule2 = new InfoEnterpriseModule(enterpriseId, 2L, currentUserId) ;
			enterpriseModuleService.add(enterpriseModule2) ;
		}
		// Menu--功能管理
		if(BaseUtil.isEmpty(enterpriseMenuService.getEnterpriseMenu(enterpriseId, 1L))) {
			InfoEnterpriseMenu enterpriseMenu1 = new InfoEnterpriseMenu(enterpriseId, 1L, 1L, currentUserId) ;
			enterpriseMenuService.add(enterpriseMenu1) ;
		}
		// Menu--企业管理
		if(BaseUtil.isEmpty(enterpriseMenuService.getEnterpriseMenu(enterpriseId, 2L))) {
			InfoEnterpriseMenu enterpriseMenu2 = new InfoEnterpriseMenu(enterpriseId, 2L, 2L, currentUserId) ;
			enterpriseMenuService.add(enterpriseMenu2) ;
		}
		// Action--全部功能
		if(BaseUtil.isEmpty(enterpriseActionService.getEnterpriseAction(enterpriseId, 1L))) {
			InfoEnterpriseAction enterpriseAction1 = new InfoEnterpriseAction(enterpriseId, 1L, 1L, 1L, currentUserId) ;
			enterpriseActionService.add(enterpriseAction1) ;
		}
		// Action--企业功能设置
		if(BaseUtil.isEmpty(enterpriseActionService.getEnterpriseAction(enterpriseId, 2L))) {
			InfoEnterpriseAction enterpriseAction2 = new InfoEnterpriseAction(enterpriseId, 1L, 1L, 2L, currentUserId) ;
			enterpriseActionService.add(enterpriseAction2) ;
		}
		// Action--模块列表
		if(BaseUtil.isEmpty(enterpriseActionService.getEnterpriseAction(enterpriseId, 4L))) {
			InfoEnterpriseAction enterpriseAction4 = new InfoEnterpriseAction(enterpriseId, 1L, 1L, 4L, currentUserId) ;
			enterpriseActionService.add(enterpriseAction4) ;
		}
		// Action--企业管理
		if(BaseUtil.isEmpty(enterpriseActionService.getEnterpriseAction(enterpriseId, 5L))) {
			InfoEnterpriseAction enterpriseAction5 = new InfoEnterpriseAction(enterpriseId, 2L, 2L, 5L, currentUserId) ;
			enterpriseActionService.add(enterpriseAction5) ;
		}
		
		// 为平台管理企业管理员用户设置基础功能
		// Module--功能注册
		if(!userModuleService.checkUserModule(userId, 1L)) {
			InfoUserModule userModule1 = new InfoUserModule(enterpriseId, userId, 1L, currentUserId) ;
			userModuleService.add(userModule1) ;
		}
		// Module--企业管理
		if(!userModuleService.checkUserModule(userId, 2L)) {
			InfoUserModule userModule2 = new InfoUserModule(enterpriseId, userId, 2L, currentUserId) ;
			userModuleService.add(userModule2) ;
		}
		// Menu--功能管理
		if(!userMenuService.checkUserMenu(userId, 1L)) {
			InfoUserMenu userMenu1 = new InfoUserMenu(enterpriseId, userId, 1L, 1L, currentUserId) ;
			userMenuService.add(userMenu1) ;
		}
		// Menu--企业管理
		if(!userMenuService.checkUserMenu(userId, 2L)) {
			InfoUserMenu userMenu2 = new InfoUserMenu(enterpriseId, userId, 2L, 2L, currentUserId) ;
			userMenuService.add(userMenu2) ;
		}
		// Action--全部功能
		if(!userActionService.checkUserAction(userId, 1L)) {
			InfoUserAction userAction1 = new InfoUserAction(enterpriseId, userId, 1L, 1L, 1L, currentUserId) ;
			userActionService.add(userAction1) ;
		}
		// Action--企业功能设置
		if(!userActionService.checkUserAction(userId, 2L)) {
			InfoUserAction userAction2 = new InfoUserAction(enterpriseId, userId, 1L, 1L, 2L, currentUserId) ;
			userActionService.add(userAction2) ;
		}
		// Action--模块列表
		if(!userActionService.checkUserAction(userId, 4L)) {
			InfoUserAction userAction4 = new InfoUserAction(enterpriseId, userId, 1L, 1L, 4L, currentUserId) ;
			userActionService.add(userAction4) ;
		}
		// Action--企业管理
		if(!userActionService.checkUserAction(userId, 5L)) {
			InfoUserAction userAction5 = new InfoUserAction(enterpriseId, userId, 2L, 2L, 5L, currentUserId) ;
			userActionService.add(userAction5) ;
		}
	}
}