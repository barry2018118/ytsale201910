package com.yuntu.sale.user.action;

import java.util.Date;

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
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.service.AreaService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.manage.service.role.InfoRoleService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoEnterpriseRelation;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 我的商户管理Action（运营商使用）
 * @author snps
 * @date 2018年2月24日 下午5:17:37
 */
@Controller
@RequestMapping(value = {"/my/enterprise"})
public class MyEnterpriseAction extends BaseAction {
	
	@Resource
	private InfoEnterpriseService infoEnterpriseService;
	
	@Resource
	private InfoUserService infoUserService;
	
	@Resource
	private InfoRoleService infoRoleService;
	
	@Resource
	private AreaService areaService;

	
	/**
	 * 我的供应商_主页
	 */
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	public String supplier(HttpServletRequest request, Model model) {
		return "/enterprise/my/supplier";
	}

	/**
	 * 我的供应商_列表页
	 */
	@RequestMapping(value = "/supplier/list", method = RequestMethod.GET)
	public String supplierList(HttpServletRequest request, Model model) {
		return "/enterprise/my/supplier_list";
	}

	/**
	 * 我的商户_数据查询（供应商）
	 */
	@RequestMapping(value = "/supplier/search", method = RequestMethod.GET)
	@ResponseBody
	public String supplierSearch(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoEnterprise> pager = new Page<InfoEnterprise>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoEnterprise> page = infoEnterpriseService.getMyShop(
				pager, getCurrentUser().getEnterpriseId(), GrobalConstant.I_SHOP_SUPPLIER, name) ;

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	
	/**
	 * 新建供应商
	 */
	@RequestMapping(value = "/supplier/toAdd", method = RequestMethod.GET)
	public String toSupplierAdd(HttpServletRequest request, Model model) {
		return "/enterprise/my/supplier_add";
	}
	
	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	@ResponseBody
	public String supplierAdd(HttpServletRequest request, InfoEnterprise enterprise) {
		// 检查供应商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		if(!BaseUtil.isEmpty(existEnterprise)) {
			OperatorFailureVo message = new OperatorFailureVo("此供应商已存在！");
			return JSONObject.toJSONString(message);
		}
		// 检查供应商主账号是否存在
		String userAccount = enterprise.getUsername();
		InfoUser existUser = infoUserService.getByUsername(userAccount);
		if(!BaseUtil.isEmpty(existUser)) {
			OperatorFailureVo message = new OperatorFailureVo("供应商主账号已存在！");
			return JSONObject.toJSONString(message);
		}
		
		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		enterprise.setDomain("www");
		enterprise.setCompanyType(GrobalConstant.I_SHOP_SUPPLIER);
		enterprise.setParentId(currentUser.getEnterpriseId());
		enterprise.setLogo("");
		enterprise.setBanner("");
		enterprise.setCreateId(currentUser.getId());
		enterprise.setUpdateId(currentUser.getId());
		enterprise.setContacterPhone(enterprise.getUsername());
		
		// 设置企业关系信息
		InfoEnterpriseRelation relation  = new InfoEnterpriseRelation();
		relation.setChildId(currentUser.getEnterpriseId());
		relation.setCreateEnterprise(currentUser.getEnterpriseId());
		relation.setCreateType(GrobalConstant.I_SHOP_SUPPLIER);
		
		// 设置企业-主账号信息
		InfoUser user = new InfoUser();
		user.setAccountType(GrobalConstant.I_INDEX_ACCOUNT_SHOP);
		user.setIsMaster(GrobalConstant.I_INDEX_MASTER_YES);
		user.setUsername(enterprise.getUsername());
		user.setPassword(enterprise.getPassword());
		user.setName(enterprise.getContacterName());
		user.setPhone(enterprise.getContacterPhone());
		user.setRoleId(enterprise.getRoleId());
		user.setCreateId(currentUser.getId());
		user.setUpdateId(currentUser.getId());
		
		// 设置企业-平台平台资金信息
		EnterpriseCapitalPo enterpriseCapital = new EnterpriseCapitalPo();
		enterpriseCapital.setCreateId(currentUser.getId());
		enterpriseCapital.setUpdateId(currentUser.getId());
		
		// 保存信息并为公司主账号分配功能
		infoEnterpriseService.save(enterprise, relation, user, enterpriseCapital);
		OperatorSuccessVo message = new OperatorSuccessVo("新建供应商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 编辑供应商
	 */
	@RequestMapping(value = "/supplier/{id}/toEdit", method = RequestMethod.GET)
	public String toSupplierEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		// 获取企业-主账号信息
		InfoUser user = infoUserService.getEnterpriseMasterUser(enterprise.getId());
		model.addAttribute("user", user);
		
		// 获取城市信息
		AreaPo city = areaService.getById(enterprise.getCity());
		model.addAttribute("city", city);
		
		return "/enterprise/my/supplier_edit";
	}
	
	@RequestMapping(value="/supplier/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String supplierEdit(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		// 检查供应商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		// 获取原企业信息
		InfoEnterprise originalEnterprise = infoEnterpriseService.getById(id);
		if(!BaseUtil.isEmpty(existEnterprise) && !enterpriseName.equals(originalEnterprise.getName())) {
			OperatorFailureVo message = new OperatorFailureVo("此供应商已存在！");
			return JSONObject.toJSONString(message);
		}

		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		originalEnterprise.setDomain(enterprise.getDomain());
		originalEnterprise.setName(enterprise.getName());
		originalEnterprise.setContacterName(enterprise.getContacterName());
		originalEnterprise.setContacterPhone(enterprise.getContacterPhone());
		originalEnterprise.setEmail(enterprise.getEmail());
		originalEnterprise.setProvince(enterprise.getProvince());
		originalEnterprise.setCity(enterprise.getCity());
		originalEnterprise.setAddress(enterprise.getAddress());
		originalEnterprise.setIntroduction(enterprise.getIntroduction());
		originalEnterprise.setUpdateId(currentUser.getId());
		originalEnterprise.setUpdateTime(new Date());
		
		// 保存企业信息、员工信息
		infoEnterpriseService.update(originalEnterprise, null);
		OperatorSuccessVo message = new OperatorSuccessVo("编辑供应商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 设置状态（1：启用、0：禁用）
	 */
	@RequestMapping(value = "/supplier/{id}/setStatus", method = RequestMethod.POST)
	@ResponseBody
	public String setSupplierStatus(HttpServletRequest request, @PathVariable Long id, Integer status) {
		OperatorMessageVo message = infoEnterpriseService.setStatus(id, status) ;
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/supplier/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String supplierDelete(HttpServletRequest request, @PathVariable Long id) {
		infoEnterpriseService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 供应商-主账号重置
	 */
	@RequestMapping(value = "/supplier/{id}/toResetAccount", method = RequestMethod.GET)
	public String toResetSupplierAccount(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		return "/enterprise/my/supplier_reset";
	}
	
	@RequestMapping(value = "/supplier/{id}/doResetAccount", method = RequestMethod.POST)
	@ResponseBody
	public String doResetSupplierAccount(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		infoEnterpriseService.updateEnterpriseAccount(id, enterprise.getUsername());
		OperatorSuccessVo message = new OperatorSuccessVo("主账号重置成功！");
		return JSONObject.toJSONString(message);
	}
	
	/*************************************************************
	 * 我的分销商_主页
	 */
	@RequestMapping(value = "/distributor", method = RequestMethod.GET)
	public String operation(HttpServletRequest request, Model model) {
		return "/enterprise/my/distributor";
	}

	/**
	 * 我的分销商_列表页
	 */
	@RequestMapping(value = "/distributor/list", method = RequestMethod.GET)
	public String operationList(HttpServletRequest request, Model model) {
		return "/enterprise/my/distributor_list";
	}

	/**
	 * 我的分销商_数据查询
	 */
	@RequestMapping(value = "/distributor/search", method = RequestMethod.GET)
	@ResponseBody
	public String operationSearch(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoEnterprise> pager = new Page<InfoEnterprise>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoEnterprise> page = infoEnterpriseService.getMyShop(
				pager, getCurrentUser().getEnterpriseId(), GrobalConstant.I_SHOP_DISTRIBUTOR, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	
	/**
	 * 新建分销商
	 */
	@RequestMapping(value = "/distributor/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/enterprise/my/distributor_add";
	}
	
	@RequestMapping(value = "/distributor/add", method = RequestMethod.POST)
	@ResponseBody
	public String toAdd(HttpServletRequest request, InfoEnterprise enterprise) {
		// 检查分销商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		if(!BaseUtil.isEmpty(existEnterprise)) {
			OperatorFailureVo message = new OperatorFailureVo("此分销商已存在！");
			return JSONObject.toJSONString(message);
		}
		// 检查分销商主账号是否存在
		String userAccount = enterprise.getUsername();
		InfoUser existUser = infoUserService.getByUsername(userAccount);
		if(!BaseUtil.isEmpty(existUser)) {
			OperatorFailureVo message = new OperatorFailureVo("分销商主账号已存在！");
			return JSONObject.toJSONString(message);
		}
		
		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		enterprise.setDomain("www");
		enterprise.setCompanyType(GrobalConstant.I_SHOP_DISTRIBUTOR);
		enterprise.setParentId(currentUser.getEnterpriseId());
		enterprise.setLogo("");
		enterprise.setBanner("");
		enterprise.setCreateId(currentUser.getId());
		enterprise.setUpdateId(currentUser.getId());
		enterprise.setContacterPhone(enterprise.getUsername());
		
		// 设置企业关系信息
		InfoEnterpriseRelation relation  = new InfoEnterpriseRelation();
		relation.setParentId(currentUser.getEnterpriseId());
		relation.setCreateEnterprise(currentUser.getEnterpriseId());
		relation.setCreateType(GrobalConstant.I_SHOP_DISTRIBUTOR);
		
		// 设置企业-主账号信息
		InfoUser user = new InfoUser();
		user.setAccountType(GrobalConstant.I_INDEX_ACCOUNT_SHOP);
		user.setIsMaster(GrobalConstant.I_INDEX_MASTER_YES);
		user.setUsername(enterprise.getUsername());
		user.setPassword(enterprise.getPassword());
		user.setName(enterprise.getContacterName());
		user.setPhone(enterprise.getContacterPhone());
		user.setRoleId(enterprise.getRoleId());
		user.setCreateId(currentUser.getId());
		user.setUpdateId(currentUser.getId());
		
		// 设置企业-平台平台资金信息
		EnterpriseCapitalPo enterpriseCapital = new EnterpriseCapitalPo();
		enterpriseCapital.setCreateId(currentUser.getId());
		enterpriseCapital.setUpdateId(currentUser.getId());
		
		// 保存信息并为公司主账号分配功能
		infoEnterpriseService.save(enterprise, relation, user, enterpriseCapital);
		OperatorSuccessVo message = new OperatorSuccessVo("新建分销商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 编辑分销商
	 */
	@RequestMapping(value = "/distributor/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		// 获取企业-主账号信息
		InfoUser user = infoUserService.getEnterpriseMasterUser(enterprise.getId());
		model.addAttribute("user", user);
		
		// 获取城市信息
		AreaPo city = areaService.getById(enterprise.getCity());
		model.addAttribute("city", city);
		
		return "/enterprise/my/distributor_edit";
	}
	
	@RequestMapping(value="/distributor/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		// 检查供应商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		// 获取原企业信息
		InfoEnterprise originalEnterprise = infoEnterpriseService.getById(id);
		if(!BaseUtil.isEmpty(existEnterprise) && !enterpriseName.equals(originalEnterprise.getName())) {
			OperatorFailureVo message = new OperatorFailureVo("此分销商已存在！");
			return JSONObject.toJSONString(message);
		}

		// 检查企业-主账号是否存在
		String username = enterprise.getUsername();
		InfoUser existUser = infoUserService.getByUsername(username);
		// 获取原企业-主账号信息
		InfoUser originalUser = infoUserService.getEnterpriseMasterUser(originalEnterprise.getId());
		if(!BaseUtil.isEmpty(existUser) && !username.equals(originalUser.getUsername())) {
			OperatorFailureVo message = new OperatorFailureVo("分销商主账号已存在！");
			return JSONObject.toJSONString(message);
		}
		
		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		originalEnterprise.setDomain(enterprise.getDomain());
		originalEnterprise.setName(enterprise.getName());
		originalEnterprise.setContacterName(enterprise.getContacterName());
		originalEnterprise.setContacterPhone(enterprise.getContacterPhone());
		originalEnterprise.setEmail(enterprise.getEmail());
		originalEnterprise.setProvince(enterprise.getProvince());
		originalEnterprise.setCity(enterprise.getCity());
		originalEnterprise.setAddress(enterprise.getAddress());
		originalEnterprise.setIntroduction(enterprise.getIntroduction());
		originalEnterprise.setUpdateId(currentUser.getId());
		originalEnterprise.setUpdateTime(new Date());
		
		// 设置企业-主账号信息
		originalUser.setUsername(enterprise.getUsername());
		originalUser.setPassword(enterprise.getPassword());
		originalUser.setUpdateId(currentUser.getId());
		originalUser.setUpdateTime(new Date());
		
		// 保存信息并为公司主账号分配功能
		infoEnterpriseService.update(originalEnterprise, originalUser);
		OperatorSuccessVo message = new OperatorSuccessVo("编辑分销商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 设置状态（1：启用、0：禁用）
	 */
	@RequestMapping(value = "/distributor/{id}/setStatus", method = RequestMethod.POST)
	@ResponseBody
	public String setStatus(HttpServletRequest request, @PathVariable Long id, Integer status) {
		OperatorMessageVo message = infoEnterpriseService.setStatus(id, status) ;
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/distributor/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id) {
		infoEnterpriseService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 分销商-主账号重置
	 */
	@RequestMapping(value = "/distributor/{id}/toResetAccount", method = RequestMethod.GET)
	public String toResetDistributorAccount(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		return "/enterprise/my/distributor_reset";
	}
	
	@RequestMapping(value = "/distributor/{id}/doResetAccount", method = RequestMethod.POST)
	@ResponseBody
	public String doResetDistributorAccount(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		infoEnterpriseService.updateEnterpriseAccount(id, enterprise.getUsername());
		OperatorSuccessVo message = new OperatorSuccessVo("主账号重置成功！");
		return JSONObject.toJSONString(message);
	}
	
	/************************************************************************
	 * 编辑企业信息
	 */
	@RequestMapping(value = "/info/toEdit", method = RequestMethod.GET)
	public String toInfoEdit(HttpServletRequest request, Model model) {
		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();

		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(currentUser.getEnterpriseId());
		model.addAttribute("enterprise", enterprise);
		
		// 获取城市信息
		AreaPo city = areaService.getById(enterprise.getCity());
		model.addAttribute("city", city);
		
		return "/enterprise/my/info_edit";
	}
	
	@RequestMapping(value="/info/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String infoEdit(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		// 检查供应商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		// 获取原企业信息
		InfoEnterprise originalEnterprise = infoEnterpriseService.getById(id);
		if(!BaseUtil.isEmpty(existEnterprise) && !enterpriseName.equals(originalEnterprise.getName())) {
			OperatorFailureVo message = new OperatorFailureVo("企业名称已存在！");
			return JSONObject.toJSONString(message);
		}

		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		originalEnterprise.setDomain(enterprise.getDomain());
		originalEnterprise.setName(enterprise.getName());
		originalEnterprise.setContacterName(enterprise.getContacterName());
		originalEnterprise.setContacterPhone(enterprise.getContacterPhone());
		originalEnterprise.setEmail(enterprise.getEmail());
		originalEnterprise.setProvince(enterprise.getProvince());
		originalEnterprise.setCity(enterprise.getCity());
		originalEnterprise.setAddress(enterprise.getAddress());
		originalEnterprise.setIntroduction(enterprise.getIntroduction());
		originalEnterprise.setUpdateId(currentUser.getId());
		originalEnterprise.setUpdateTime(new Date());
		
		// 保存企业信息、员工信息
		infoEnterpriseService.update(originalEnterprise, null);
		OperatorSuccessVo message = new OperatorSuccessVo("编辑成功！");
		return JSONObject.toJSONString(message);
	}
	
}