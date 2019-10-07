package com.yuntu.sale.manage.action.function;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.function.FuncModuleService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 功能_模块管理Action
 * @Description 
 * @author snps
 * @date 下午11:59:05
 */
@Controller
@RequestMapping(value = {"/manage/function/module"})
public class FuncModuleAction extends BaseAction {
	@Resource
	private FuncModuleService funcModuleService ;
	
	/**
	 * 进入功能模块主页面
	 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String toMain(Model model) {
		List<FuncModule> lstModule = funcModuleService.getAll() ;
		model.addAttribute("lstModule", lstModule) ;
		return "/manage/function/module/main" ;
	}
	
	/**
	 * 新建功能模块 （平台管理员可进入）
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd(Model model) {
		return "/manage/function/module/add" ;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(FuncModule entity) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		entity.setCreateId(currentUser.getId()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setCreateTime(new Date()) ;
		entity.setUpdateTime(new Date()) ;
		funcModuleService.save(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 编辑功能模块
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String toEdit(@PathVariable Long id, Model model) {
		FuncModule module = funcModuleService.getById(id) ;
		model.addAttribute("module", module) ;
		return "/manage/function/module/edit" ;
	}
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Long id, FuncModule module) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		FuncModule entity = funcModuleService.getById(id) ;
		entity.setName(module.getName()) ;
		entity.setIcon(module.getIcon()) ;
		entity.setUrl(module.getUrl()) ;
		entity.setDescription(module.getDescription()) ;
		entity.setUpdateId(currentUser.getId()) ;
		entity.setUpdateTime(new Date()) ;
		funcModuleService.edit(entity) ;
		return SUCCESS ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable Long id, Model model) {
		int count = funcModuleService.delete(id) ;
		return String.valueOf(count) ;
	}
	
	/**
	 * 查看详细
	 */
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		
		return "/manage/function/module/detail" ;
	}
	
}