package com.yuntu.sale.manage.action.function;

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

import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.function.FuncButtonService;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 功能_按钮管理Action
 * @Description 
 * @author snps
 * @date 下午11:59:05
 */
@Controller
@RequestMapping(value = {"/manage/function/button"})
public class FuncButtonAction extends BaseAction {
	@Resource
	private FuncModuleService funcModuleService ;
	
	@Resource
	private FuncMenuService funcMenuService ;
	
	@Resource
	private FuncActionService funcActionService ;
	
	@Resource
	private FuncButtonService funcButtonService ;
	
	/**
	 * 得到动作下的功能按钮
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getByAction(HttpServletRequest request, Model model) {
		FuncAction action = funcActionService.getById(Long.parseLong(request.getParameter("actionId"))) ;
		FuncMenu menu = funcMenuService.getById(action.getMenuId()) ;
		FuncModule module = funcModuleService.getById(action.getModuleId()) ;
		List<FuncButton> lstButton = funcButtonService.getByAction(Long.parseLong(request.getParameter("actionId"))) ;
		
		model.addAttribute("action", action) ;
		model.addAttribute("menu", menu) ;
		model.addAttribute("module", module) ;
		model.addAttribute("lstButton", lstButton) ;
		return "/manage/function/button/list" ;
	}
	
	/**
	 * 新建功能按钮
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		FuncAction action = funcActionService.getById(Long.parseLong(request.getParameter("actionId"))) ;
		FuncMenu menu = funcMenuService.getById(action.getMenuId()) ;
		FuncModule module = funcModuleService.getById(action.getModuleId()) ;
		
		model.addAttribute("action", action) ;
		model.addAttribute("menu", menu) ;
		model.addAttribute("module", module) ;
		return "/manage/function/button/add" ;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, FuncButton entity) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		entity.setCreateId(currentUser.getId()) ;
		entity.setUpdateId(currentUser.getId()) ;
		funcButtonService.save(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 编辑功能按钮
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String toEdit(@PathVariable Long id, Model model) {
		FuncButton button = funcButtonService.getById(id) ;
		FuncAction action = funcActionService.getById(button.getActionId()) ;
		FuncMenu menu = funcMenuService.getById(button.getMenuId()) ;
		FuncModule module = funcModuleService.getById(button.getModuleId()) ;
		
		model.addAttribute("module", module) ;
		model.addAttribute("menu", menu) ;
		model.addAttribute("action", action) ;
		model.addAttribute("button", button) ;
		return "/manage/function/button/edit" ;
	}
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Long id, FuncButton button) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		FuncButton entity = funcButtonService.getById(id) ;
		entity.setName(button.getName()) ;
		entity.setUrl(button.getUrl()) ;
		entity.setDescription(button.getDescription()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setUpdateTime(new Date()) ;
		funcButtonService.edit(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable Long id, Model model) {
		int count = funcButtonService.delete(id) ;
		return String.valueOf(count) ;
	}
	
	/**
	 * 查看详细
	 */
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		
		return "/manage/function/button/detail" ;
	}
	
}