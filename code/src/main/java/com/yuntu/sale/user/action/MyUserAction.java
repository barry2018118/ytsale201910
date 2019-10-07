package com.yuntu.sale.user.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.role.InfoRole;
import com.yuntu.sale.base.po.role.InfoRoleModule;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.manage.service.role.InfoRoleService;
import com.yuntu.sale.manage.service.user.InfoUserActionService;
import com.yuntu.sale.manage.service.user.InfoUserFunctionService;
import com.yuntu.sale.manage.service.user.InfoUserMenuService;
import com.yuntu.sale.manage.service.user.InfoUserModuleService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 我的用户管理Action
 * @author snps
 * @date 2018年2月26日 下午3:43:06
 */
@Controller
@RequestMapping(value = { "/my/user" })
public class MyUserAction extends BaseAction {

	@Resource
	private InfoUserService infoUserService;

	@Resource
	private InfoRoleService infoRoleService;

	@Resource(name = "functionService")
	private FunctionService functionService;

	@Resource(name = "userFunctionService")
	private InfoUserFunctionService userFunctionService;

	@Resource
	private InfoUserModuleService userModuleService;

	@Resource
	private InfoUserMenuService userMenuService;

	@Resource
	private InfoUserActionService userActionService;
	
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;

	private static final String IS_HAVE = "1";
	

	/**
	 * 用户_主页
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/user/my/main";
	}

	/**
	 * 用户_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/user/my/list";
	}

	/**
	 * 平台运营商_数据查询
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public String search(HttpServletRequest request, String username, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoUser> pager = new Page<InfoUser>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoUser> page = infoUserService.getUser(pager, username, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 根据企业id_查询员工数据
	 */
	@RequestMapping(value = "/myUserSearch", method = RequestMethod.GET)
	@ResponseBody
	public String myUserSearch(HttpServletRequest request, Long enterpriseId, String username, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoUser> pager = new Page<InfoUser>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoUser> page = infoUserService.getMyUser(pager, getCurrentUser().getEnterpriseId(), username, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/******************************************************************************
	 * 平台账户-新建员工账号
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		InfoUser currentUserRole = super.getCurrentUser();
		// 查询平台权限
		List<InfoRole> lstRole = infoRoleService.searchByEnterpriseId(currentUserRole.getEnterpriseId());

		model.addAttribute("lstRole", lstRole);
		return "/user/my/add";
	}

	/**
	 * 新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, InfoUser user) {
		// 检查员工(电话号码)是否存在
		String userName = user.getUsername();
		InfoUser existUser = infoUserService.getByUsername(userName);
		if (!BaseUtil.isEmpty(existUser)) {
			OperatorFailureVo message = new OperatorFailureVo("此员工已存在！");
			return JSONObject.toJSONString(message);
		}
		InfoUser currentUser = super.getCurrentUser();
		// 设置员工信息
		user.setAccountType(GrobalConstant.I_INDEX_ACCOUNT_SHOP);
		user.setIsMaster(GrobalConstant.I_INDEX_MASTER_NO);
		user.setEnterpriseId(currentUser.getEnterpriseId());
		user.setCreateId(currentUser.getId());
		user.setCreateTime(new Date());
		user.setUpdateId(currentUser.getId());
		user.setUpdateTime(new Date());
		user.setPhone(userName);

		// 检查权限是否分配了“模块”
		List<InfoRoleModule> rModule = userModuleService.getByRole(user.getRoleId());
		if(BaseUtil.isEmpty(rModule)) {
			OperatorFailureVo message = new OperatorFailureVo("权限未分配任何功能！");
			return JSONObject.toJSONString(message);
		}
		
		// 保存信息并分配功能
		infoUserService.save(user);
		OperatorSuccessVo message = new OperatorSuccessVo("新建员工成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 编辑员工密码
	 */
	@RequestMapping(value = "/{id}/toPassword", method = RequestMethod.GET)
	public String toPassword(HttpServletRequest request, @PathVariable Long id, Model model) {
		InfoUser user = infoUserService.getById(id);
		model.addAttribute("user", user);
		return "/user/my/password";
	}

	/**
	 * 重设密码
	 */
	@RequestMapping(value = "/{id}/password", method = RequestMethod.POST)
	@ResponseBody
	public String password(HttpServletRequest request, @PathVariable Long id, InfoUser user) {

		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		// 当前员工用户
		InfoUser userUpdatePassword = infoUserService.getById(id);

		userUpdatePassword.setPassword(user.getPassword());
		userUpdatePassword.setUpdateTime(new Date());
		userUpdatePassword.setUpdateId(currentUser.getId());

		infoUserService.updatePassword(user);
		OperatorSuccessVo message = new OperatorSuccessVo("修改密码成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 编辑员工信息
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		// InfoUser currentUserRole = super.getCurrentUser();
		// 查询平台权限
		// List<InfoRole> lstRole =
		// infoRoleService.searchByEnterpriseId(currentUserRole.getEnterpriseId());
		// 获取员工信息
		// model.addAttribute("lstRole", lstRole);

		InfoUser user = infoUserService.getById(id);
		model.addAttribute("user", user);
		return "/user/my/edit";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, InfoUser user) {

		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		// 当前员工用户
		InfoUser userUpdate = infoUserService.getById(id);

		userUpdate.setName(user.getName());
		userUpdate.setPhone(user.getPhone());
		userUpdate.setUpdateTime(new Date());
		userUpdate.setUpdateId(currentUser.getId());

		infoUserService.update(user);
		OperatorSuccessVo message = new OperatorSuccessVo("编辑员工成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 设置状态（1：启用、0：禁用）
	 */
	@RequestMapping(value = "/{id}/setStatus", method = RequestMethod.POST)
	@ResponseBody
	public String setStatus(HttpServletRequest request, @PathVariable Long id, Integer status) {
		OperatorMessageVo message = infoUserService.setStatus(id, status);
		return JSONObject.toJSONString(message);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id) {
		infoUserService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("注销成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 编辑个人资料
	 */
	@RequestMapping(value = "/{username}/toEditUsername", method = RequestMethod.GET)
	public String toEditUsername(HttpServletRequest request, @PathVariable String username, Model model) {

		InfoUser user = infoUserService.getByUsername(username);
		model.addAttribute("user", user);
		
		List<InfoRole> lstRole = infoRoleService.searchByEnterpriseId(1L);
		model.addAttribute("lstRole", lstRole);
		return "/user/my/editUsername";
	}

	@RequestMapping(value = "/{username}/editUsername", method = RequestMethod.POST)
	@ResponseBody
	public String editUsername(HttpServletRequest request, @PathVariable String username, InfoUser user, Model model) {
		InfoUser updateUser = infoUserService.getByUsername(username);
		updateUser.setName(user.getName());
		updateUser.setPhone(user.getPhone());
		updateUser.setUpdateId(super.getCurrentUser().getId());
		updateUser.setUpdateTime(new Date());
		infoUserService.update(user);
		
		OperatorSuccessVo message = new OperatorSuccessVo("编辑个人资料成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 分配权限
	 */
	@RequestMapping(value = "/{id}/toDistribution", method = RequestMethod.GET)
	public String toDistribution(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 得到企业管理员账号
		InfoUser currentUser = infoUserService.getById(id);
		InfoUser manager = infoUserService.getEnterpriseMasterUser(currentUser.getEnterpriseId());
		
		// 得到所有功能模块（分配给企业主账号的）
		List<InfoUserModule> lstUserModule = userModuleService.getByUser(manager.getId());
		List<FuncModule> lstModule = null;
		if(!BaseUtil.isEmpty(lstUserModule)) {
			lstModule = new ArrayList<FuncModule>(lstUserModule.size());
			// 得到功能模块下的菜单
			for (InfoUserModule userModule : lstUserModule) {
				FuncModule module = funcModuleService.getById(userModule.getModuleId());
				List<InfoUserMenu> lstUserMenu = userMenuService.getUserMenu(manager.getId(), userModule.getModuleId());
				List<FuncMenu> lstMenu = null;
				if(!BaseUtil.isEmpty(lstUserMenu)) {
					lstMenu = new ArrayList<FuncMenu>(lstUserMenu.size());
					// 得到功能菜单下的动作
					for (InfoUserMenu userMenu : lstUserMenu) {
						FuncMenu menu = funcMenuService.getById(userMenu.getMenuId());
						List<InfoUserAction> lstUserAction = userActionService.getUserAction(manager.getId(), userMenu.getMenuId());
						List<FuncAction> lstAction = null;
						if (!BaseUtil.isEmpty(lstUserAction)) {
							lstAction = new ArrayList<FuncAction>(lstUserAction.size());
							// 得到功能动作下的按钮
							for (InfoUserAction userAction : lstUserAction) {
								FuncAction action = funcActionService.getById(userAction.getActionId());
								lstAction.add(action);
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
						lstMenu.add(menu);
					}
				}
				module.setMenus(lstMenu);
				// 查看用户是否分配此模块
				if (userFunctionService.haveModule(id, module.getId())) {
					module.setIsHave(IS_HAVE);
				}
				lstModule.add(module);
			}
		}

		model.addAttribute("lstModule", lstModule);
		model.addAttribute("userId", id);
		return "/user/distribution";
	}

}