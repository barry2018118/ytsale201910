package com.yuntu.sale.user.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntu.sale.base.po.enterprise.InfoEnterpriseModule;
import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.enterprise.InfoEnterpriseModuleService;
import com.yuntu.sale.manage.service.function.FunctionService;
import com.yuntu.sale.user.po.InfoUser;

/**
 * 企业_功能Action
 * @Description 
 * @author snps
 * @date 下午2:29:39
 */
@Controller
@RequestMapping(value = {"/manage/enterpriseFunc"})
public class EnterpriseFunctionAction extends BaseAction {
	@Resource(name="enterpriseFunctionService")
	private FunctionService enterpriseFunctionService ;
	
	@Resource
	private InfoEnterpriseModuleService enterpriseModuleService ;
	
	
	/**
	 * 进入企业功能页（查看给企业分配的功能）
	 */
	@RequestMapping(value="/view", method=RequestMethod.GET)
	//@UserRoleCheckAnnotation(funcId="action_enterprise_function_view",functionDescription=GrobalSystemConstant.FUNC_ATION)
	public String view(Model model) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		List<FuncModule> lstModule = enterpriseFunctionService.getFunction(currentUser.getEnterpriseId()) ;
		model.addAttribute("lstModule", lstModule) ;
		return "/enterprise/function/view" ;
	}
	
	
	/**
	 * 进入企业_功能模块主页面
	 */
	@RequestMapping(value="/moduleList", method=RequestMethod.GET)
	public String moduleList(Model model) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		List<InfoEnterpriseModule> lstEnterpriseModule = enterpriseModuleService.getAll(currentUser.getEnterpriseId()) ;
		model.addAttribute("lstEnterpriseModule", lstEnterpriseModule) ;
		return "/enterprise/function/moduleList" ;
	}
	
	/**
	 * 查看某功能模块下的详情
	 */
	@RequestMapping(value="/module/{id}/detail", method=RequestMethod.GET)
	public String moduleDetail(@PathVariable Long id, Model model) {
		
		return "/enterprise/function/moduleDetail" ;
	}
	
	/**
	 * 查看企业的所有功能
	 */
	@RequestMapping(value="/funcDetail", method=RequestMethod.GET)
	public String functionDetail(Model model) {
		InfoUser currentUser = (InfoUser) super.getCurrentUser() ;
		
		return "/enterprise/function/functionDetail" ;
	}
	
	/**
	 * 设置（部门权限）相关的功能
	 */
	@RequestMapping(value="/setRoleFunc", method=RequestMethod.GET)
	public String toSetRoleFunction(Model model) {
		
		return "/enterprise/function/setRoleFunc" ;
	}
	
	@RequestMapping(value="/setRoleFunc", method=RequestMethod.POST)
	@ResponseBody
	public String setRoleFunction(Model model) {
		
		return super.SUCCESS ;
	}
}