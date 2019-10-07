package com.yuntu.sale.report.action;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.ProductCategory;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.report.service.IDistributorSaleReportService;
import com.yuntu.sale.report.service.IOtaSaleReportService;
import com.yuntu.sale.report.service.IProductSaleReportService;
import com.yuntu.sale.report.service.ISaleReportService;
import com.yuntu.sale.report.vo.DistributorSaleDataSummaryVo;
import com.yuntu.sale.report.vo.SaleDataSummaryVo;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 商户-销售汇总报表
 * @author snps
 * @date 2018年4月27日 上午11:14:08
 */
@Controller
@RequestMapping(value = {"/report/shop"})
public class ShopReportAction extends BaseAction {

	@Resource
	private ProductCategoryService productCategoryService;
	
	@Resource
	private IProductSaleReportService shopProductSaleReportService;
	
	@Resource
	private IDistributorSaleReportService shopDistributorSaleReportService;
	
	@Resource
	private IOtaSaleReportService shopOtaSaleReportService;
	
	@Resource
	private EnterprisePlatformInterfaceService enterprisePlatformInterfaceService;
	
	@Resource
	private ISaleReportService saleReportService;
	
	
	/**
	 * 进入“销售汇总”主页面
	 */
	@RequestMapping(value = "/sale/main", method = RequestMethod.GET)
	public String toSaleMain(HttpServletRequest request, Model model) {
		//商品类别
		/*List<ProductCategory> lstProductCategory = productCategoryService.getAll();
		model.addAttribute("lstProductCategory", lstProductCategory);*/
		
		// 初始化日期
		String startDate = super.getFirstDayOfMonty();
		String endDate = DateUtil.getTodayStr();
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "/report/shop/sale/main";
	}
	
	/**
	 * 进入“销售汇总”列表页
	 */
	@RequestMapping(value = "/sale/list", method = RequestMethod.GET)
	public String toSaleList(HttpServletRequest request, Model model) {
		return "/report/shop/sale/list";
	}
	
	/**
	 * “销售汇总”查询（查询分销商对我购买的）
	 */
	@RequestMapping(value="/sale/search", method=RequestMethod.GET)
	@ResponseBody
	public String doSaleSearch(HttpServletRequest request, String productName, String startDate, String endDate) {
		InfoUser user = super.getCurrentUser();
		Date searchEndDate = DateUtil.addDays(DateUtil.parseToDate(endDate), 1);
		
		List<SaleDataSummaryVo> lstData = saleReportService.getSaleSummaryData(user.getEnterpriseId(), productName, 
				startDate, DateUtil.formatDate(searchEndDate));
		Collections.sort(lstData);
		
		String json = JSONObject.toJSON(lstData).toString();
		return json;
	}
	
	/*********************************************************************************************
	 * 进入“分销商销售”主页面
	 */
	@RequestMapping(value = "/distributor/main", method = RequestMethod.GET)
	public String toDistributorMain(HttpServletRequest request, Model model) {
		// 初始化日期
		String startDate = super.getFirstDayOfMonty();
		String endDate = DateUtil.getTodayStr();
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "/report/shop/distributor/main";
	}
	
	/**
	 * 进入“分销商销售”列表页
	 */
	@RequestMapping(value = "/distributor/list", method = RequestMethod.GET)
	public String toDistributorList(HttpServletRequest request, Model model) {
		return "/report/shop/distributor/list";
	}
	
	/**
	 * “分销商销售”查询
	 */
	@RequestMapping(value="/distributor/search", method=RequestMethod.GET)
	@ResponseBody
	public String doDistributorSearch(HttpServletRequest request, String childName, String startDate, String endDate) {
		InfoUser user = super.getCurrentUser();
		Date searchEndDate = DateUtil.addDays(DateUtil.parseToDate(endDate), 1);
		
		List<DistributorSaleDataSummaryVo> lstData = saleReportService.getDistributorSaleData(user.getEnterpriseId(), childName, 
				startDate, DateUtil.formatDate(searchEndDate));
		String json = JSONObject.toJSON(lstData).toString();
		return json;
	}
	
	/*********************************************************************************************
	 * 进入“采购汇总”主页面
	 */
	@RequestMapping(value = "/buy/main", method = RequestMethod.GET)
	public String toBuyMain(HttpServletRequest request, Model model) {
		//商品类别
		/*List<ProductCategory> lstProductCategory = productCategoryService.getAll();
		model.addAttribute("lstProductCategory", lstProductCategory);*/
		
		// 初始化日期
		String startDate = super.getFirstDayOfMonty();
		String endDate = DateUtil.getTodayStr();
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "/report/shop/buy/main";
	}
	
	/**
	 * 进入“采购汇总”列表页
	 */
	@RequestMapping(value = "/buy/list", method = RequestMethod.GET)
	public String toBuyList(HttpServletRequest request, Model model) {
		return "/report/shop/buy/list";
	}
	
	/**
	 * “采购汇总”查询（查询我购买的）
	 */
	@RequestMapping(value="/buy/search", method=RequestMethod.GET)
	@ResponseBody
	public String doBuySearch(HttpServletRequest request, String productName, String startDate, String endDate) {
		InfoUser user = super.getCurrentUser();
		Date searchEndDate = DateUtil.addDays(DateUtil.parseToDate(endDate), 1);
		
		List<SaleDataSummaryVo> lstData = saleReportService.getBuySummaryData(user.getEnterpriseId(), productName, 
				startDate, DateUtil.formatDate(searchEndDate));
		Collections.sort(lstData);
		
		String json = JSONObject.toJSON(lstData).toString();
		return json;
	}
	
	/*********************************************************************************************
	 * 进入“OTA销售”主页面
	 */
	@RequestMapping(value = "/ota/main", method = RequestMethod.GET)
	public String toOtaMain(HttpServletRequest request, Model model) {
		// 查询OTA信息
		Long enterpriseId = getCurrentUser().getEnterpriseId();
		List<EnterprisePlatformInterfacePo> lstOTA = enterprisePlatformInterfaceService.listOfAvailableGys(enterpriseId);
		model.addAttribute("lstOTA", lstOTA);
		
		// 初始化日期
		String startDate = super.getFirstDayOfMonty();
		String endDate = DateUtil.getTodayStr();
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "/report/shop/ota/main";
	}
	
	/**
	 * 进入“OTA”列表页
	 */
	@RequestMapping(value = "/ota/list", method = RequestMethod.GET)
	public String toOtaList(HttpServletRequest request, Model model) {
		return "/report/shop/ota/list";
	}
	
	/**
	 * “OTA”查询
	 */
	@RequestMapping(value="/ota/search", method=RequestMethod.GET)
	@ResponseBody
	public String doOtaSearch(HttpServletRequest request, Long otaId, String startDate, String endDate) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<ProductCategory> pager = new Page<ProductCategory>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<ProductCategory> page = null; // productCategoryService.getByName(pager, name) ;
		
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}
	
}