package com.yuntu.sale.report.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.DateUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.report.service.IFinanceReportService;
import com.yuntu.sale.report.vo.DistributorSaleDataVo;

/**
 * @Description 商户-财务报表Action
 * @author snps
 * @date 2018年5月23日 下午4:42:58
 */
@Controller
@RequestMapping(value = {"/finance/shop"})
public class FinanceReportAction extends BaseAction {

	@Resource
	private IFinanceReportService financeReportService;
	
	
	/*********************************************************************************************
	 * 进入“财务报表”主页面
	 */
	@RequestMapping(value = "/orders/main", method = RequestMethod.GET)
	public String toFinanceMain(HttpServletRequest request, Model model) {
		// 初始化日期
		String startDate = super.getFirstDayOfMonty();
		String endDate = DateUtil.getTodayStr();
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "/report/shop/finance/orders_main";
	}
	
	/**
	 * 进入“财务报表”列表页
	 */
	@RequestMapping(value = "/orders/list", method = RequestMethod.GET)
	public String toFinanceList(HttpServletRequest request, Model model) {
		return "/report/shop/finance/orders_list";
	}
	
	/**
	 * “财务报表”查询
	 */
	@RequestMapping(value="/orders/search", method=RequestMethod.GET)
	@ResponseBody
	public String doFinanceSearch(HttpServletRequest request, String childName, String startDate, String endDate) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<DistributorSaleDataVo> pager = new Page<DistributorSaleDataVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<DistributorSaleDataVo> page = null;
		
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}
	
	/**
	 * “分销商销售”报表导出
	 */
	@RequestMapping(value="/finance/export", method=RequestMethod.GET)
	@ResponseBody
	public String doFinanceExport(HttpServletRequest request, String productName, Long productCategory, String startDate, String endDate) {
		
		return null;
	}
	
}