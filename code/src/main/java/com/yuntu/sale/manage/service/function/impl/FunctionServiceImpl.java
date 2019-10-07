package com.yuntu.sale.manage.service.function.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncButtonService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.function.FunctionService;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	@Resource
	private FuncButtonService funcButtonService ;
	
	/**
	 * 得到系统功能（模块-菜单-功能-按钮<子功能>）
	 */
	public List<FuncModule> getFunction(Long id) {
		// 得到所有功能模块
		List<FuncModule> lstModule = funcModuleService.getAll() ;
		if(!lstModule.isEmpty()) {
			// 得到功能模块下的菜单
			for(FuncModule module : lstModule) {
				List<FuncMenu> lstMenu = funcMenuService.getByModule(module.getId()) ;
				if(!lstMenu.isEmpty()) {
					// 得到功能菜单下的动作
					for(FuncMenu menu : lstMenu) {
						List<FuncAction> lstAction = funcActionService.getByMenu(menu.getId()) ;
						menu.setActions(lstAction) ;
					}
				}
				module.setMenus(lstMenu) ;
			}
		}
		return lstModule ;
	}

	public boolean haveModule(Long entityId, long moduleId) {
		FuncModule module = funcModuleService.getById(moduleId) ;
		return !BaseUtil.isEmpty(module)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveMenu(Long entityId, long menuId) {
		FuncMenu menu = funcMenuService.getById(menuId) ;
		return !BaseUtil.isEmpty(menu)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveAction(Long entityId, long actionId) {
		FuncAction action = funcActionService.getById(actionId) ;
		return !BaseUtil.isEmpty(action)?Boolean.TRUE:Boolean.FALSE ;
	}

	public boolean haveButton(Long entityId, long buttonId) {
		FuncButton button = funcButtonService.getById(buttonId) ;
		return !BaseUtil.isEmpty(button)?Boolean.TRUE:Boolean.FALSE ;
	}

	public void setFunction(Long entityId, List<Long> lstModuleId, List<Long> lstMenuId, List<Long> lstActionId, Long currentUserId) {
		
	}
	
}