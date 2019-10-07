package com.yuntu.sale.finance.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.DateUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.finance.service.IEnterpriseFinanceService;
import com.yuntu.sale.finance.vo.BillMessageVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;

/**
 * @Description 企业-下级分销商-财务Action
 * @author snps
 * @date 2018年5月25日 上午10:50:39
 */
@Controller
@RequestMapping(value = {"/finance/enterprise/child"})
public class EnterpriseChildFinanceAction extends BaseAction {

	@Resource
	private IEnterpriseFinanceService enterpriseFinanceService;
	
	@Resource
	private InfoEnterpriseService infoEnterpriseService;
	
	
	/**
	 * 进入“企业-下级分销商”主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String toSaleMain(HttpServletRequest request, Model model) {
		return "/finance/enterprise/child_main";
	}
	
	/**
	 * 我的分销商_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String supplierList(HttpServletRequest request, Model model) {
		return "/finance/enterprise/child_list";
	}

	/**
	 * 我的商户_数据查询（供应商、分销商）
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public String supplierSearch(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoEnterprise> pager = new Page<InfoEnterprise>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoEnterprise> page = enterpriseFinanceService.getMyShop(
				pager, getCurrentUser().getEnterpriseId(), GrobalConstant.I_SHOP_DISTRIBUTOR, name) ;

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	
	/**
	 * 进入导出页
	 */
	@RequestMapping(value="/{childId}/report",method=RequestMethod.GET)
	public String toReport(HttpServletRequest request, @PathVariable("childId") Long childId, Model model) {
		InfoEnterprise enterprise = infoEnterpriseService.getById(childId) ;
		model.addAttribute("enterprise", enterprise) ;
		
		String startDate = super.getFirstDayOfMonty() ;
		String endDate = DateUtil.getTodayStr() ;
		model.addAttribute("startDate", startDate) ;
		model.addAttribute("endDate", endDate) ;
		
		return "/finance/enterprise/child_report";
	}
	
	/**
	 * 报表导出
	 */
	@RequestMapping(value="/{childId}/export", method=RequestMethod.GET)
	@ResponseBody
	public String doExport(HttpServletRequest request, @PathVariable("childId") Long childId, 
			Integer searchMethod, String startDate, String endDate) {
		
		InfoUser currentUser = super.getCurrentUser();
		InfoEnterprise enterprise = infoEnterpriseService.getById(childId);
		
		String reportFilepath = enterpriseFinanceService.getReport(2, childId, currentUser.getEnterpriseId(), startDate, endDate, searchMethod) ;
		BillMessageVo billMessage = new BillMessageVo("1");
		StringBuffer sbuFilename = new StringBuffer(enterprise.getName()).append("-").append("应收报表").append("-");
		if(searchMethod == 1) {
			sbuFilename.append("按销售");
		} else if(searchMethod == 2) {
			sbuFilename.append("按消费");
		} else {
		}
		sbuFilename.append("-").append(startDate).append("到").append(endDate).append(".xlsx");
		billMessage.setFileName(sbuFilename.toString());
		billMessage.setFilepath(reportFilepath);
		
		String json = JSONObject.toJSON(billMessage).toString();
		return json;
	}
	
}