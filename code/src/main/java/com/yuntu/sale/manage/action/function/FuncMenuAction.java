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

import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 功能_菜单管理Action
 * @Description 
 * @author snps
 * @date 下午11:59:05
 */
@Controller
@RequestMapping(value = {"/manage/function/menu"})
public class FuncMenuAction extends BaseAction {
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	/**
	 * 得到模块下的功能菜单
	 */
	@RequestMapping(value="/{moduleId}/getByModule", method=RequestMethod.GET)
	public String getByModule(@PathVariable Long moduleId, Model model) {
		
		return "/manage/function/menu/getByModule" ;
	}
	
	/**
	 * 新建功能菜单
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		FuncModule module = funcModuleService.getById(Long.parseLong(request.getParameter("moduleId"))) ;
		model.addAttribute("module", module) ;
		return "/manage/function/menu/add" ;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, FuncMenu entity) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		entity.setCreateId(currentUser.getId()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setCreateTime(new Date()) ;
		entity.setUpdateTime(new Date()) ;
		funcMenuService.save(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 编辑功能菜单
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String toEdit(@PathVariable Long id, Model model) {
		FuncMenu menu = funcMenuService.getById(id) ;
		FuncModule module = funcModuleService.getById(menu.getModuleId()) ;
		
		model.addAttribute("module", module) ;
		model.addAttribute("menu", menu) ;
		return "/manage/function/menu/edit" ;
	}
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Long id, FuncMenu menu) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		FuncMenu entity = funcMenuService.getById(id) ;
		entity.setName(menu.getName()) ;
		entity.setUrl(menu.getUrl()) ;
		entity.setDescription(menu.getDescription()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setUpdateTime(new Date()) ;
		funcMenuService.edit(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable Long id, Model model) {
		int count = funcMenuService.delete(id) ;
		return String.valueOf(count) ;
	}
	
	/**
	 * 查看详细
	 */
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		
		return "/manage/function/menu/detail" ;
	}
	
}