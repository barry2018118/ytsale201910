package com.yuntu.sale.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.manage.service.role.InfoRoleFunctionService;
import com.yuntu.sale.manage.service.role.InfoRoleService;
import com.yuntu.sale.manage.service.user.InfoUserFunctionService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 用户信息管理Action
 * @author snps
 * @date 2018年2月26日 下午3:42:11
 */
@Controller
@RequestMapping(value = {"/manage/user"})
public class UserManageAction extends BaseAction {
	@Resource
	private InfoUserService infoUserService;
	
	@Resource
	private InfoEnterpriseService infoEnterpriseService;
	
	@Resource
	private InfoRoleService infoRoleService;
	
	@Resource(name = "functionService")
	private FunctionService functionService;
	
	@Resource(name = "roleFunctionService")
	private InfoRoleFunctionService roleFunctionService;
	
	@Resource(name = "userFunctionService")
	private InfoUserFunctionService userFunctionService;

	private static final String IS_HAVE = "1";

	/**
	 * 跳转到用户功能授权页面
	 */
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public String authorize(Model model) {
		// 得到所有功能模块
		InfoUser user = (InfoUser) super.getCurrentUser();
		Long id = user.getId();
		List<FuncModule> lstModule = functionService.getFunction(user.getEnterpriseId());
		if (lstModule != null && !lstModule.isEmpty()) {
			// 得到功能模块下的菜单
			for (FuncModule module : lstModule) {
				List<FuncMenu> lstMenu = module.getMenus();
				if (lstMenu != null && !lstMenu.isEmpty()) {
					// 得到功能菜单下的动作
					for (FuncMenu menu : lstMenu) {
						List<FuncAction> lstAction = menu.getActions();
						if (lstAction != null && !lstAction.isEmpty()) {
							// 得到功能动作下的按钮
							for (FuncAction action : lstAction) {
								List<FuncButton> lstButton = action.getButtons();
								action.setButtons(lstButton);
								// 查看用户是否分配此功能
								if (userFunctionService.haveAction(id, action.getId())) {
									action.setIsHave(IS_HAVE);
								}
							}
						}
						menu.setActions(lstAction);
						// 查看用户是否分配此菜单
						if (userFunctionService.haveMenu(id, menu.getId())) {
							menu.setIsHave(IS_HAVE);
						}
					}
				}
				module.setMenus(lstMenu);
				// 查看用户是否分配此模块
				if (userFunctionService.haveModule(id, module.getId())) {
					module.setIsHave(IS_HAVE);
				}
			}
		}
		model.addAttribute("lstModule", lstModule);

		model.addAttribute("userId", id);
		return "/manage/user/udistribution";
	}

	/**
	 * 给用户授权，保存到用户与功能关系对应的表
	 */
	@RequestMapping(value = "/saveAuthorize", method = RequestMethod.POST)
	public @ResponseBody String authorize(Long userId,
			@RequestParam(value = "moduleId[]", required = false) String[] arysModuleId,
			@RequestParam(value = "menuId[]", required = false) String[] arysMenuId,
			@RequestParam(value = "actionId[]", required = false) String[] arysActionId,
			@RequestParam(value = "buttonId[]", required = false) String[] arysButtonId) {

		InfoUser currentUser = (InfoUser) super.getCurrentUser();

		// 构造要保存的模块Id
		List<Long> lstModuleId = new ArrayList<Long>();
		if (arysModuleId != null)
			for (String moduleId : arysModuleId) {
				lstModuleId.add(Long.parseLong(moduleId));
			}

		// 构造要保存的菜单Id
		List<Long> lstMenuId = new ArrayList<Long>();
		if (arysMenuId != null)
			for (String menuId : arysMenuId) {
				lstMenuId.add(Long.parseLong(menuId));
			}

		// 构造要保存的功能Id
		List<Long> lstActionId = new ArrayList<Long>();
		if (arysActionId != null)
			for (String actionId : arysActionId) {
				lstActionId.add(Long.parseLong(actionId));
			}

		// 调用业务方法，给权限组分配功能
		userFunctionService.setFunction(userId, currentUser.getEnterpriseId(), lstModuleId, lstMenuId, lstActionId,
				currentUser.getId());
		// 更新权限组的更新时间
		// InfoRole role = infoRoleService.searchById(roleId);
		// role.setUpdateTime(System.currentTimeMillis()/1000);
		// infoRoleService.update(role);
		return SUCCESS;
	}
	
	/**
	 * 跳转到当前登陆账户密码
	 */
	@RequestMapping(value="/editpassword", method=RequestMethod.GET)
	public String updatepassword(Model model) {
		InfoUser user = infoUserService.getById(super.getCurrentUser().getId()) ;
		model.addAttribute("user", user);
		return "/user/edit_password";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/editpassword", method = RequestMethod.POST)
	@ResponseBody
	public String updatepassword(InfoUser user) {
		infoUserService.updatePassword(user);
		OperatorSuccessVo message = new OperatorSuccessVo("修改密码成功！");
		return JSONObject.toJSONString(message);
	}

}