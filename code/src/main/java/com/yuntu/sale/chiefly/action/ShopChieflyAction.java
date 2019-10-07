package com.yuntu.sale.chiefly.action;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.coolshow.util.DateUtil;
import com.coolshow.util.DateUtils;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.ExtractPo;
import com.yuntu.sale.capital.po.RechargePo;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.ExtractService;
import com.yuntu.sale.capital.service.RechargeService;
import com.yuntu.sale.chiefly.service.IShopChieflyService;
import com.yuntu.sale.chiefly.vo.ScenicSaleColumnChartVo;
import com.yuntu.sale.chiefly.vo.SimpleColumnChartVo;
import com.yuntu.sale.info.po.NoticePo;
import com.yuntu.sale.info.service.NoticeService;
import com.yuntu.sale.manage.action.BaseAction;

/**
 * @Description 商户首页Action 
 * @author snps
 * @date 2018年5月2日 上午9:38:38
 */
@Controller
@RequestMapping(value = {"/chiefly/shop"})
public class ShopChieflyAction extends BaseAction {

	@Resource
	private NoticeService noticeService;
	
	@Resource
    private EnterpriseCapitalService enterpriseCapitalService;
	
	@Resource
	private RechargeService rechargeService;
	
	@Resource
	private ExtractService extractService;
	
	@Resource
	private IShopChieflyService shopChieflyService;
	
	
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/chiefly/shop/main" ;
	}
	
	/**
	 * 获取公告信息
	 */
	@RequestMapping(value = "/getNotice", method = RequestMethod.GET)
	public String getNotice(HttpServletRequest request, Model model) {
		List<NoticePo> lstNotice = noticeService.getByNum(6);
		model.addAttribute("lstNotice", lstNotice);
		return "/chiefly/shop/notice_list" ;
	}
	
	/**
	 * 获取具体公告信息
	 */
	@RequestMapping(value = "/getNotice/{id}", method = RequestMethod.GET)
	public String getNoticeById(HttpServletRequest request, @PathVariable Long id, Model model) {
		NoticePo notice = noticeService.getById(id);
		model.addAttribute("notice", notice);
		return "/chiefly/shop/notice" ;
	}
	
	/*************************************************************************
	 * 进入资金页面
	 */
	@RequestMapping(value = "/toCapital", method = RequestMethod.GET)
	public String toCapital(HttpServletRequest request, Model model) {
		return "/chiefly/shop/capital" ;
	}
	
	/**
	 * 获取资金信息
	 */
	@RequestMapping(value = "/getCapital", method = RequestMethod.GET)
	public String getCapital(HttpServletRequest request, Integer capitalDataFlag, Model model) {
		Map<String, Date> searchDate = this.getSearchDate(capitalDataFlag);
		Date startDate = searchDate.get("startDate");
		Date endDate = searchDate.get("endDate");
		
		// 查询平台余额
		EnterpriseCapitalPo capitalInfo = enterpriseCapitalService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());
        model.addAttribute("capitalInfo", capitalInfo);
        
        // 查询累计充值
        RechargePo recharge = rechargeService.getByDate(super.getCurrentUser().getEnterpriseId(), startDate, endDate);
        model.addAttribute("recharge", recharge);
        
        // 查询累计提现
        ExtractPo extract = extractService.getByDate(super.getCurrentUser().getEnterpriseId(), startDate, endDate);
        model.addAttribute("extract", extract);
		
		return "/chiefly/shop/capital_data";
	}
	
	/*************************************************************************
	 * 进入热门景区销售排行页面
	 */
	@RequestMapping(value = "/toScenicSaleChart", method = RequestMethod.GET)
	public String toScenicSaleChart(HttpServletRequest request, Model model) {
		return "/chiefly/shop/scenic_sale" ;
	}
	
	/**
	 * 获取热门景区销售排行数据
	 */
	@RequestMapping(value = "/getScenicSaleChart", method = RequestMethod.GET)
	public String getcenicSaleChart(HttpServletRequest request, Integer scenicSaleDataFlag, Model model) {
		Map<String, Date> searchDate = this.getSearchDate(scenicSaleDataFlag);
		Date startDate = searchDate.get("startDate");
		Date endDate = searchDate.get("endDate");
		
		// 查询数据
		List<ScenicSaleColumnChartVo> lstData = shopChieflyService.getTopScenicSaleData(
				super.getCurrentUser().getEnterpriseId(), 10, startDate, endDate);
		
		// 转换为图表数据
		if(BaseUtil.isEmpty(lstData)) {
			 model.addAttribute("hasChart", "no");
		} else {
			model.addAttribute("hasChart", "yes");
			Collections.sort(lstData);
			SimpleColumnChartVo chartVo = new SimpleColumnChartVo();
			StringBuffer sbuCategories = new StringBuffer();
			StringBuffer sbuData = new StringBuffer();
			for(int i=0; i<lstData.size(); i++) {
				if(i ==0) {
					sbuCategories.append(lstData.get(i).getName());
					sbuData.append(lstData.get(i).getNumber());
				} else {
					sbuCategories.append(",").append(lstData.get(i).getName());
					sbuData.append(",").append(lstData.get(i).getNumber());
				}
			}
			
			chartVo.setName("景区销量");
			chartVo.setCategories(sbuCategories.toString());
			chartVo.setData(sbuData.toString());
			model.addAttribute("scenicSaleChart", chartVo);
		}
		
		return "/chiefly/shop/scenic_sale_data";
	}
	
	/*************************************************************************
	 * 进入商户销售排行页面
	 */
	@RequestMapping(value = "/toDistributionSaleChart", method = RequestMethod.GET)
	public String toDistributionSaleChart(HttpServletRequest request, Model model) {
		return "/chiefly/shop/distribution_sale" ;
	}
	
	/**
	 * 获取商户销售排行数据
	 */
	@RequestMapping(value = "/getDistributionSaleChart", method = RequestMethod.GET)
	public String getDistributionSaleChart(HttpServletRequest request, Integer distributionSaleDataFlag, Model model) {
		Map<String, Date> searchDate = this.getSearchDate(distributionSaleDataFlag);
		Date startDate = searchDate.get("startDate");
		Date endDate = searchDate.get("endDate");
		
		// 查询数据
		List<ScenicSaleColumnChartVo> lstData = shopChieflyService.getTopDistributionSaleData(
				super.getCurrentUser().getEnterpriseId(), 10, startDate, endDate);
		
		// 转换为图表数据
		if(BaseUtil.isEmpty(lstData)) {
			model.addAttribute("distributionHasChart", "no");
		} else {
			model.addAttribute("distributionHasChart", "yes");
			Collections.sort(lstData);
			SimpleColumnChartVo chartVo = new SimpleColumnChartVo();
			StringBuffer sbuCategories = new StringBuffer();
			StringBuffer sbuData = new StringBuffer();
			for(int i=0; i<lstData.size(); i++) {
				if(i ==0) {
					sbuCategories.append(lstData.get(i).getName());
					sbuData.append(lstData.get(i).getNumber());
				} else {
					sbuCategories.append(",").append(lstData.get(i).getName());
					sbuData.append(",").append(lstData.get(i).getNumber());
				}
			}
			
			chartVo.setName("分销商销量");
			chartVo.setCategories(sbuCategories.toString());
			chartVo.setData(sbuData.toString());
			model.addAttribute("distributionSaleChart", chartVo);
		}
		
		
		return "/chiefly/shop/distribution_sale_data";
	}
	
	/*************************************************************************
	 * 进入“查码页”
	 */
	@RequestMapping(value = "/searchCode", method = RequestMethod.GET)
	public String searchCode(HttpServletRequest request, Model model) {
		return "/chiefly/shop/search_code" ;
	}
	
	
	/**
	 * 处理查询日期方法
	 * @param capitalDataFlag
	 * @return Map<String, Date>
	 */
	private Map<String, Date> getSearchDate(Integer capitalDataFlag) {
		// 计算查询日期
		Date startDate = null;
		Date endDate = null;
		
		// 今日
		if(capitalDataFlag == 1) {
			startDate = DateUtil.parseToDate(DateUtils.getDate());
			endDate = DateUtils.addDay(DateUtil.parseToDate(DateUtils.getDate()), 1);
		}
		// 7天
		if(capitalDataFlag == 2) {
			startDate = DateUtils.addDay(DateUtil.parseToDate(DateUtils.getDate()), -6);
			endDate = DateUtils.addDay(DateUtil.parseToDate(DateUtils.getDate()), 1);
		}
		// 本月
		if(capitalDataFlag == 3) {
			startDate = DateUtil.parseToDate(super.getFirstDayOfMonty());
			endDate = DateUtils.addDay(DateUtil.parseToDate(DateUtils.getDate()), 1);
		}
		
		Map<String, Date> mapDate = new HashMap<String, Date>();
		mapDate.put("startDate", startDate);
		mapDate.put("endDate", endDate);
		return mapDate;
	}
	
	

	/**
	 * testSubmit
	 */
	@RequestMapping(value = "/testSubmit", method = RequestMethod.POST)
	@ResponseBody
	public String testSubmit(HttpServletRequest request, Model model) {
		System.out.println("测试提交------------------------------------") ;
		OperatorSuccessVo message = new OperatorSuccessVo("测试提交成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
}