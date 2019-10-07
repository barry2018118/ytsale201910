package com.yuntu.sale.manage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.base.po.user.InfoUserModule;
import com.yuntu.sale.manage.service.user.InfoUserMenuService;
import com.yuntu.sale.manage.service.user.InfoUserModuleService;
import com.yuntu.sale.user.po.InfoUser;
/**
 * @Description 用户功能Action 
 * @author snps
 * @date 2017-4-7 下午5:31:12
 */

@Controller
@RequestMapping(value = {"/manage/userFunction"})
public class UserFunctionAction extends BaseAction {
	@Resource
	private InfoUserModuleService infoUserModuleService ;
	
	@Resource
	private InfoUserMenuService infoUserMenuService ;

	/**
	 * 获取用户功能模块
	 */
	@RequestMapping(value={"/getModule", "/getTop"}, method=RequestMethod.GET)
	public String getTop(HttpServletRequest request, Model model) {
		InfoUser currentUser = super.getCurrentUser() ;
		
		List<InfoUserModule> lstUserModule = infoUserModuleService.getByUser(currentUser.getId()) ;
		model.addAttribute("user", currentUser);
		model.addAttribute("lstUserModule", lstUserModule) ;
		return "/layouts/common/header" ;
	}
	
	/**
	 * 获取用户功能菜单（动作）
	 */
	@RequestMapping(value={"/getMenu", "/getLeft"}, method=RequestMethod.GET)
	//@UserRoleCheckAnnotation(funcId="module_enterprise_manage",functionDescription=GrobalSystemConstant.FUNC_MODULE)
	public String getLeft(HttpServletRequest request, Model model) {
		InfoUser currentUser = super.getCurrentUser() ;
		long moduleId = Long.parseLong(request.getParameter("moduleId")) ;
		
		List<InfoUserMenu> lstUserMenu = infoUserMenuService.getByUser(currentUser.getId(), moduleId) ;
		model.addAttribute("lstUserMenu", lstUserMenu) ;
		return "/layouts/common/left" ;
	}
}