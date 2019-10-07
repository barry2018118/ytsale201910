package com.yuntu.sale.manage.action.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncButtonService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 功能总览Action
 * @Description 
 * @author snps
 * @date 上午10:26:30
 */
@Controller
@RequestMapping(value = {"/manage/function"})
public class FunctionAction extends BaseAction {
	
	@Resource(name="functionService")
	private FunctionService functionService ;
	
	@Resource(name="enterpriseFunctionService")
	private FunctionService enterpriseFunctionService ;
	
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	@Resource
	private FuncButtonService funcButtonService ;
	
	
	private static final String IS_HAVE = "1" ;
	
	/**
	 * 进入所有功能页（查看系统所有功能）
	 */
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Model model) {
		List<FuncModule> lstModule = functionService.getFunction(null) ;
		model.addAttribute("lstModule", lstModule) ;
		return "/manage/function/manage" ;
	}
	
	/**
	 * 为企业分配功能
	 */
	@RequestMapping(value="/distribution", method=RequestMethod.GET)
	//@UserRoleCheckAnnotation(funcId="action_enterprise_distribution",functionDescription = GrobalSystemConstant.FUNC_ATION)
	public String toDistribution(Model model,@RequestParam(required=false) Long enterpriseId) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		if(enterpriseId==null)
		enterpriseId = currentUser.getEnterpriseId() ;
		
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
						if(!lstAction.isEmpty()) {
							// 得到功能动作下的按钮
							for(FuncAction action : lstAction) {
								List<FuncButton> lstButton = funcButtonService.getByAction(action.getId()) ;
								if(!lstButton.isEmpty()) {
									for(FuncButton button : lstButton) {
										// 查看企业是否分配此按钮<子功能>
										if(enterpriseFunctionService.haveButton(enterpriseId, button.getId())) {
											button.setIsHave(IS_HAVE) ;
										}
									}
								}
								action.setButtons(lstButton) ;
								// 查看企业是否分配此功能
								if(enterpriseFunctionService.haveAction(enterpriseId, action.getId())) {
									action.setIsHave(IS_HAVE) ;
								}
							}
						}
						menu.setActions(lstAction) ;
						// 查看企业是否分配此菜单
						if(enterpriseFunctionService.haveMenu(enterpriseId, menu.getId())) {
							menu.setIsHave(IS_HAVE) ;
						}
					}
				}
				module.setMenus(lstMenu) ;
				// 查看企业是否分配此模块
				if(enterpriseFunctionService.haveModule(enterpriseId, module.getId())) {
					module.setIsHave(IS_HAVE) ;
				}
			}
		}
		
		model.addAttribute("lstModule", lstModule) ;
		return "/manage/function/distribution" ;
	}
	
	@RequestMapping(value="/distribution", method=RequestMethod.POST)
	@ResponseBody
	public String distribution(HttpServletRequest request, 
			@RequestParam(value="moduleId[]", required=false) String[] arysModuleId, 
			@RequestParam(value="menuId[]", required=false) String[] arysMenuId,
			@RequestParam(value="actionId[]", required=false) String[] arysActionId,
			@RequestParam(value="buttonId[]", required=false) String[] arysButtonId, Model model,
			@RequestParam(required=false) Long enterpriseId) {
		
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		if(enterpriseId==null)
			enterpriseId = currentUser.getEnterpriseId() ;
		
		// 构造要保存的模块Id
		List<Long> lstModuleId = new ArrayList<Long>() ;
		if(!BaseUtil.isEmpty(arysModuleId)) {
			for(String moduleId : arysModuleId) {
				lstModuleId.add(Long.parseLong(moduleId)) ;
			}
		}
		
		// 构造要保存的菜单Id
		List<Long> lstMenuId = new ArrayList<Long>() ;
		if(!BaseUtil.isEmpty(arysMenuId)) {
			for(String menuId : arysMenuId) {
				lstMenuId.add(Long.parseLong(menuId)) ;
			}
		}
		
		// 构造要保存的功能Id
		List<Long> lstActionId = new ArrayList<Long>() ;
		if(!BaseUtil.isEmpty(arysActionId)) {
			for(String actionId : arysActionId) {
				lstActionId.add(Long.parseLong(actionId)) ;
			}
		}
		
		// 构造要保存的按钮<子功能>Id
		List<Long> lstButtonId = new ArrayList<Long>() ;
		if(!BaseUtil.isEmpty(arysButtonId)) {
			for(String buttonId : arysButtonId) {
				lstButtonId.add(Long.parseLong(buttonId)) ;
			}
		}
		
		// 调用业务方法，给企业分配功能
		enterpriseFunctionService.setFunction(enterpriseId, lstModuleId, lstMenuId, lstActionId,  currentUser.getId()) ;
		return SUCCESS ;
	}
}