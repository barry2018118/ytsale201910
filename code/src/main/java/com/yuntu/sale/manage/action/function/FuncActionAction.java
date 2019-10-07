package com.yuntu.sale.manage.action.function;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 功能_动作管理Action
 * @Description 
 * @author snps
 * @date 下午11:59:05
 */
@Controller
@RequestMapping(value = {"/manage/function/action"})
public class FuncActionAction extends BaseAction {
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;

	/**
	 * 得到菜单下的功能动作
	 */
	@RequestMapping(value="/{menuId}/getByMenu", method=RequestMethod.GET)
	public String getByMenu(@PathVariable Long menuId, Model model) {
		
		return "/manage/function/menu/getByMenu" ;
	}
	
	/**
	 * 新建功能动作
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		FuncMenu menu = funcMenuService.getById(Long.parseLong(request.getParameter("menuId"))) ;
		FuncModule module = funcModuleService.getById(menu.getModuleId()) ;
		
		model.addAttribute("menu", menu) ;
		model.addAttribute("module", module) ;
		return "/manage/function/action/add" ;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, FuncAction entity) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		entity.setCreateId(currentUser.getId()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setCreateTime(new Date()) ;
		entity.setUpdateTime(new Date()) ;
		funcActionService.save(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 编辑功能动作
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String toEdit(@PathVariable Long id, Model model) {
		FuncAction action = funcActionService.getById(id) ;
		FuncMenu menu = funcMenuService.getById(action.getMenuId()) ;
		FuncModule module = funcModuleService.getById(action.getModuleId()) ;
		
		model.addAttribute("module", module) ;
		model.addAttribute("menu", menu) ;
		model.addAttribute("action", action) ;
		return "/manage/function/action/edit" ;
	}
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Long id, FuncAction action) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		FuncAction entity = funcActionService.getById(id) ;
		entity.setName(action.getName()) ;
		entity.setUrl(action.getUrl()) ;
		entity.setDescription(action.getDescription()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setUpdateTime(new Date()) ;
		funcActionService.edit(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable Long id, Model model) {
		int count = funcActionService.delete(id) ;
		return String.valueOf(count) ;
	}
	
	/**
	 * 查看详细
	 */
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		
		return "/manage/function/action/detail" ;
	}
	
}