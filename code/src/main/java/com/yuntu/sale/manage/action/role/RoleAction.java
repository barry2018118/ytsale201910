package com.yuntu.sale.manage.action.role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coolshow.util.Encodes;
import com.coolshow.util.Page;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.role.InfoRole;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.manage.service.role.InfoRoleFunctionService;
import com.yuntu.sale.manage.service.role.InfoRoleService;
import com.yuntu.sale.manage.service.user.InfoUserFunctionService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 角色Action
 * @author wzw
 * @date 
 */
@Controller
@RequestMapping(value = {"/manage/role"})
public class RoleAction extends BaseAction {
	@Resource
	private InfoRoleService infoRoleService;
	
	@Resource(name="roleFunctionService")
	private InfoRoleFunctionService roleFunctionService;
	
	@Resource(name="functionService")
	private FunctionService functionService;
	
	@Resource(name="userFunctionService")
	private InfoUserFunctionService userFunctionService;
	
	@Resource(name="infoUserService")
	private InfoUserService infoUserService;
	
	
	/**
	 * 树是否包含常量
	 */
	private static final String IS_HAVE = "1" ;
	
	/**
	 * 进入角色管理主页 
	 */
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/manage/role/main";
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String searchRole(HttpServletRequest request, Model model,Page<InfoRole> pager,String title) {
		InfoUser currentUser = super.getCurrentUser();
		
		if(title!=null){
			title = Encodes.decoding(title);
		}
		if(pager == null){
			  pager = new Page<InfoRole>();
			}
		if(pager.getStart()==null){
			pager.setStart(1);
		}
		if(pager.getSize()==null){
			pager.setSize(20);
		}
		Page<InfoRole> page = infoRoleService.searchByEnterpriseId(pager, currentUser.getEnterpriseId(), title);
		
		model.addAttribute("page", page);
		return "/manage/role/list";
	}
	
	/**
	 * 添加角色
	 */
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(Model model) {
		return "manage/role/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String addRole(HttpServletResponse response, InfoRole infoRole){
		try {
			InfoUser user = (InfoUser) super.getCurrentUser();
			infoRole.setEnterpriseId(user.getEnterpriseId());
			infoRole.setCreateId(user.getId());
			infoRole.setUpdateId(user.getId());
			infoRoleService.addRole(infoRole);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 跳转到编辑角色页面
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String toEdit(Model model,@PathVariable("id") Long id){
		InfoRole infoRole = infoRoleService.searchById(id);
		
		model.addAttribute("infoRole",infoRole);
		return "/manage/role/edit";
	}
	
	/**
	 * 编辑角色信息
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public @ResponseBody String editRole(Model model,InfoRole infoRole){
		try {
			InfoUser user = (InfoUser) super.getCurrentUser();
			infoRole.setCreateId(user.getId());
			infoRole.setUpdateId(user.getId());
			infoRoleService.modify(infoRole);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 跳转到角色详情页
	 */
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String searchEnterprise(Model model,Long id){
		InfoRole infoRole = infoRoleService.searchById(id);
		model.addAttribute("infoRole", infoRole);
		return "manage/role/view";
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public void dropRole(HttpServletResponse response,@PathVariable("id") Long id){
		try {
			infoRoleService.deleteRole(id);
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断机角色名称是否重名
	 */
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public @ResponseBody void toCheck(HttpServletResponse response,String title,Long id){
		try {
			title = Encodes.decoding(title);
			if(id==null){
				InfoRole infoRole = infoRoleService.searchByName(title);
				if(infoRole==null){
					response.getWriter().write("true");
					return;
				}
			}else{
				response.setContentType("text/html;charset=utf-8");
				InfoRole roleByName = infoRoleService.searchByName(title);
				InfoRole roleById = infoRoleService.searchById(id);
				if(roleByName==null || roleById.getTitle().equals(title))
					response.getWriter().write("true");
				else
					response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到权限授权页面
	 */
	@RequestMapping(value="/authorize",method=RequestMethod.GET)
	public String authorize(Long id,Model model) {
		// 得到所有功能模块
		InfoUser user = (InfoUser) super.getCurrentUser();
		List<FuncModule> lstModule = functionService.getFunction(user.getEnterpriseId());
		if(lstModule!=null&&!lstModule.isEmpty()) {
			// 得到功能模块下的菜单
			for(FuncModule module : lstModule) {
				List<FuncMenu> lstMenu = module.getMenus();
				if(lstMenu!=null&&!lstMenu.isEmpty()) {
					// 得到功能菜单下的动作
					for(FuncMenu menu : lstMenu) {
						List<FuncAction> lstAction = menu.getActions();
						if(lstAction!=null&&!lstAction.isEmpty()) {
							// 得到功能动作下的按钮
							for(FuncAction action : lstAction) {
								List<FuncButton> lstButton = action.getButtons();
								action.setButtons(lstButton) ;
								// 查看权限组是否分配此功能
								if(roleFunctionService.haveAction(id, action.getId())) {
									action.setIsHave(IS_HAVE) ;
								}
							}
						}
						menu.setActions(lstAction) ;
						// 查看权限组是否分配此菜单
						if(roleFunctionService.haveMenu(id, menu.getId())) {
							menu.setIsHave(IS_HAVE) ;
						}
					}
				}
				module.setMenus(lstMenu) ;
				// 查看权限组是否分配此模块
				if(roleFunctionService.haveModule(id, module.getId())) {
					module.setIsHave(IS_HAVE) ;
				}
			}
		}
		model.addAttribute("lstModule", lstModule);
		
		model.addAttribute("roleId", id);
		return "/manage/role/distribution";
	}
	
	/**
	 * 跳转到权限授权页面
	 */
	@RequestMapping(value="/userAuthorize",method=RequestMethod.GET)
	public String userAuthorize(Long id,Model model) {
		// 得到所有功能模块
		InfoUser user = (InfoUser) super.getCurrentUser();
		InfoUser master = infoUserService.getEnterpriseMasterUser(user.getEnterpriseId());
		List<FuncModule> lstModule = userFunctionService.getFunction(master.getId());
		if(lstModule!=null&&!lstModule.isEmpty()) {
			// 得到功能模块下的菜单
			for(FuncModule module : lstModule) {
				List<FuncMenu> lstMenu = module.getMenus();
				if(lstMenu!=null&&!lstMenu.isEmpty()) {
					// 得到功能菜单下的动作
					for(FuncMenu menu : lstMenu) {
						List<FuncAction> lstAction = menu.getActions();
						if(lstAction!=null&&!lstAction.isEmpty()) {
							// 得到功能动作下的按钮
							for(FuncAction action : lstAction) {
								List<FuncButton> lstButton = action.getButtons();
								action.setButtons(lstButton) ;
								// 查看权限组是否分配此功能
								if(roleFunctionService.haveAction(id, action.getId())) {
									action.setIsHave(IS_HAVE) ;
								}
							}
						}
						menu.setActions(lstAction) ;
						// 查看权限组是否分配此菜单
						if(roleFunctionService.haveMenu(id, menu.getId())) {
							menu.setIsHave(IS_HAVE) ;
						}
					}
				}
				module.setMenus(lstMenu) ;
				// 查看权限组是否分配此模块
				if(roleFunctionService.haveModule(id, module.getId())) {
					module.setIsHave(IS_HAVE) ;
				}
			}
		}
		model.addAttribute("lstModule", lstModule);
		
		model.addAttribute("roleId", id);
		return "/manage/role/distribution";
	}
	
	
	/**
	 * 给角色授权，保存到角色与功能关系对应的表
	 */
	@RequestMapping(value="/saveAuthorize", method=RequestMethod.POST)
	public @ResponseBody String authorize(Long roleId,
			@RequestParam(value="moduleId[]", required=false) String[] arysModuleId, 
			@RequestParam(value="menuId[]", required=false) String[] arysMenuId,
			@RequestParam(value="actionId[]", required=false) String[] arysActionId,
			@RequestParam(value="buttonId[]", required=false) String[] arysButtonId) {
		
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		// 构造要保存的模块Id
		List<Long> lstModuleId = new ArrayList<Long>() ;
		if(arysModuleId!=null)
		for(String moduleId : arysModuleId) {
			lstModuleId.add(Long.parseLong(moduleId)) ;
		}
		
		// 构造要保存的菜单Id
		List<Long> lstMenuId = new ArrayList<Long>() ;
		if(arysMenuId!=null)
		for(String menuId : arysMenuId) {
			lstMenuId.add(Long.parseLong(menuId)) ;
		}
		
		// 构造要保存的功能Id
		List<Long> lstActionId = new ArrayList<Long>() ;
		if(arysActionId!=null)
		for(String actionId : arysActionId) {
			lstActionId.add(Long.parseLong(actionId)) ;
		}
		
		// 调用业务方法，给权限组分配功能
		roleFunctionService.setFunction(roleId, currentUser.getEnterpriseId(), lstModuleId, lstMenuId, lstActionId, currentUser.getId()) ;
		return SUCCESS ;
	}
	
}