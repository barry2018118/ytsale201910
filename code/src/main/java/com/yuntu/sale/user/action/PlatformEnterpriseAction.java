package com.yuntu.sale.user.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.coolshow.util.FileUtils;
import com.coolshow.util.Page;
import com.yuntu.sale.base.FileUploadAider;
import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.RandomStringUtils;
import com.yuntu.sale.base.po.role.InfoRole;
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
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 平台运营商管理Action
 * @author snps
 * @date 2018年2月24日 下午2:01:16
 */
@Controller
@RequestMapping(value = {"/platform/enterprise"})
public class PlatformEnterpriseAction extends BaseAction {

	@Resource
	private InfoEnterpriseService infoEnterpriseService;
	
	@Resource
	private InfoUserService infoUserService;
	
	@Resource
	private InfoRoleService infoRoleService;
	
	@Resource
	private AreaService areaService;
	

	/**
	 * 平台运营商_主页
	 */
	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	public String operation(HttpServletRequest request, Model model) {
		return "/enterprise/platform/operation";
	}

	/**
	 * 平台运营商_列表页
	 */
	@RequestMapping(value = "/operation/list", method = RequestMethod.GET)
	public String operationList(HttpServletRequest request, Model model) {
		return "/enterprise/platform/operation_list";
	}

	/**
	 * 平台运营商_数据查询
	 */
	@RequestMapping(value = "/operation/search", method = RequestMethod.GET)
	@ResponseBody
	public String operationSearch(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoEnterprise> pager = new Page<InfoEnterprise>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoEnterprise> page = infoEnterpriseService.getOperation(pager, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	
	/******************************************************************************
	 * 平台运营商_新建企业
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		// 查询平台权限
		List<InfoRole> lstRole = infoRoleService.searchByEnterpriseId(1L);
		
		model.addAttribute("lstRole", lstRole);
		return "/enterprise/platform/add";
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, InfoEnterprise enterprise) {
		// 检查运营商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		if(!BaseUtil.isEmpty(existEnterprise)) {
			OperatorFailureVo message = new OperatorFailureVo("此运营商已存在！");
			return JSONObject.toJSONString(message);
		}
		// 检查运营商主账号是否存在
		String userAccount = enterprise.getUsername();
		InfoUser existUser = infoUserService.getByUsername(userAccount);
		if(!BaseUtil.isEmpty(existUser)) {
			OperatorFailureVo message = new OperatorFailureVo("此账号已存在！");
			return JSONObject.toJSONString(message);
		}
		
		// 获得当前用户
		InfoUser currentUser = super.getCurrentUser();
		
		// 设置企业信息
		enterprise.setDomain("www");
		enterprise.setCompanyType(GrobalConstant.I_SHOP_OPERATION);
		enterprise.setParentId(currentUser.getEnterpriseId());
		enterprise.setLogo("");
		enterprise.setBanner("");
		enterprise.setCreateId(currentUser.getId());
		enterprise.setUpdateId(currentUser.getId());
		enterprise.setContacterPhone(enterprise.getUsername());
		
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
		infoEnterpriseService.save(enterprise, null, user, enterpriseCapital);
		OperatorSuccessVo message = new OperatorSuccessVo("新建平台运营商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 平台运营商_编辑企业信息
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
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
		
		return "/enterprise/platform/edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		// 检查运营商是否存在
		String enterpriseName = enterprise.getName();
		InfoEnterprise existEnterprise = infoEnterpriseService.getByName(enterpriseName);
		// 获取原企业信息
		InfoEnterprise originalEnterprise = infoEnterpriseService.getById(id);
		if(!BaseUtil.isEmpty(existEnterprise) && !enterpriseName.equals(originalEnterprise.getName())) {
			OperatorFailureVo message = new OperatorFailureVo("此运营商已存在！");
			return JSONObject.toJSONString(message);
		}

		// 检查企业-主账号是否存在
		String username = enterprise.getUsername();
		InfoUser existUser = infoUserService.getByUsername(username);
		// 获取原企业-主账号信息
		InfoUser originalUser = infoUserService.getEnterpriseMasterUser(originalEnterprise.getId());
		if(!BaseUtil.isEmpty(existUser) && !username.equals(originalUser.getUsername())) {
			OperatorFailureVo message = new OperatorFailureVo("运营商主账号已存在！");
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
		OperatorSuccessVo message = new OperatorSuccessVo("编辑平台运营商成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 设置状态（1：启用、0：禁用）
	 */
	@RequestMapping(value = "/{id}/setStatus", method = RequestMethod.POST)
	@ResponseBody
	public String setStatus(HttpServletRequest request, @PathVariable Long id, Integer status) {
		OperatorMessageVo message = infoEnterpriseService.setStatus(id, status) ;
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id) {
		infoEnterpriseService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除成功！");
		return JSONObject.toJSONString(message);
	}
	
	/**
	 * 运营商-主账号重置
	 */
	@RequestMapping(value = "/{id}/toResetAccount", method = RequestMethod.GET)
	public String toResetDistributorAccount(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		return "/enterprise/platform/reset";
	}
	
	@RequestMapping(value = "/{id}/doResetAccount", method = RequestMethod.POST)
	@ResponseBody
	public String doResetDistributorAccount(HttpServletRequest request, @PathVariable Long id, InfoEnterprise enterprise) {
		infoEnterpriseService.updateEnterpriseAccount(id, enterprise.getUsername());
		OperatorSuccessVo message = new OperatorSuccessVo("主账号重置成功！");
		return JSONObject.toJSONString(message);
	}

	/******************************************************************************
	 * 平台商户_主页
	 */
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(HttpServletRequest request, Model model) {
		return "/enterprise/platform/shop";
	}

	/**
	 * 平台商户_列表页
	 */
	@RequestMapping(value = "/shop/list", method = RequestMethod.GET)
	public String shopList(HttpServletRequest request, Model model) {
		return "/enterprise/platform/shop_list";
	}

	/**
	 * 平台商户_数据查询
	 */
	@RequestMapping(value = "/shop/search", method = RequestMethod.GET)
	@ResponseBody
	public String shopSearch(HttpServletRequest request, Integer companyType, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoEnterprise> pager = new Page<InfoEnterprise>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoEnterprise> page = infoEnterpriseService.getAllShop(pager, companyType, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/{id}/toView", method = RequestMethod.GET)
	public String toView(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 获取企业信息
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		
		// 获取企业-主账号信息
		InfoUser user = infoUserService.getEnterpriseMasterUser(enterprise.getId());
		model.addAttribute("user", user);
		
		// 获取省、市信息
		AreaPo province = areaService.getById(enterprise.getProvince());
		model.addAttribute("province", province);
		AreaPo city = areaService.getById(enterprise.getCity());
		model.addAttribute("city", city);
		
		return "/enterprise/view";
	}

	/**
	 * 编辑网站信息
	 */
	@RequestMapping(value = "/toEditWebsiteInfo", method = RequestMethod.GET)
	public String toEditWebsiteInfo(HttpServletRequest request, Model model) {
		InfoEnterprise enterprise = infoEnterpriseService.getById(super.getCurrentUser().getEnterpriseId());
		model.addAttribute("enterprise", enterprise);
		return "/enterprise/platform/website";
	}
	
	@RequestMapping(value = "/editWebsiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public String editWebsiteInfo(HttpServletRequest request, String customerPhone, String platformName,
			@RequestParam(value = "logo", required = false) MultipartFile logo, 
			@RequestParam(value = "banner", required = false) MultipartFile banner) throws IOException {
		
		OperatorSuccessVo message = new OperatorSuccessVo() ;
		Long enterpriseId = super.getCurrentUser().getEnterpriseId();
		InfoEnterprise enterprise = infoEnterpriseService.getById(enterpriseId);
		String businessPath = new StringBuffer(GrobalSystemConstant.S_SLASH).append(enterpriseId)
				.append(GrobalSystemConstant.S_SLASH).toString();
		String path = "website.path";
		// 处理Logo上传
		if (!BaseUtil.isEmpty(logo)) {
			String extendFileName= FileUtils.getExtendFileName(logo.getOriginalFilename()) ;
			// 生成实时文件名
			String time = DateUtil.getCurrentTimestamp().toString();
			String filename = time + "-" + RandomStringUtils.getStringRandom(10000, 4) + extendFileName ;
			Map<String, String> mapUploadInfo = FileUploadAider.uploadFile(logo.getInputStream(), businessPath, filename,path);
			enterprise.setLogo(mapUploadInfo.get("uploadPath"));
		}
		// 处理Logo上传
		if (!BaseUtil.isEmpty(banner)) {
			String extendFileName= FileUtils.getExtendFileName(banner.getOriginalFilename()) ;
			// 生成实时文件名
			String time = DateUtil.getCurrentTimestamp().toString();
			String filename = time + "-" + RandomStringUtils.getStringRandom(10000, 4) + extendFileName ;
			Map<String, String> mapUploadInfo = FileUploadAider.uploadFile(banner.getInputStream(), businessPath, filename,path);
			enterprise.setBanner(mapUploadInfo.get("uploadPath"));
		}
		
		// 修改企业网站信息
		enterprise.setPlatformName(platformName);
		enterprise.setCustomerPhone(customerPhone);
		infoEnterpriseService.updateWebsiteInfo(enterprise);
		
		// 返回提示信息
		message.setFlag(Boolean.TRUE);
		message.setMessage("编辑网站信息成功！");
		return JSONObject.toJSONString(message);
	}
}