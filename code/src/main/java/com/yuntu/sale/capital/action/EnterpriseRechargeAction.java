package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.RechargePo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.RechargeService;
import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;


/**
 * @Description 企业-充值Action
 * @author snps
 * @date 2018年3月20日 上午12:00:00
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/info/recharge"})
public class EnterpriseRechargeAction extends BaseAction {

	@Resource
	private RechargeService rechargeService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;

	@Resource
	private EnterpriseCapitalService enterpriseCapitalService;

	/**
	 * 充值_主页
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/info/recharge/main";
	}

	/**
	 * 充值_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/capital/enterprise/info/recharge/list";
	}

	/**
	 * 根据企业id_查询充值数据
	 */
	@RequestMapping(value = "/toSearch", method = RequestMethod.GET)
	@ResponseBody
	public String myCardSearch(HttpServletRequest request,Long enterpriseId, String username, String start,String end) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<RechargePo> pager = new Page<RechargePo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<RechargePo> page = rechargeService.queryByTime(pager,super.getCurrentUser().getEnterpriseId(), username, start, end);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	/**
	 * 新增充值
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/capital/enterprise/info/recharge/add";
	}

	/**
	 * 新增
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, RechargePo card) {
		EnterpriseAccountLogPo enterpriseAccountLogPo = new EnterpriseAccountLogPo();

		EnterpriseCapitalPo enterpriseCapital = enterpriseCapitalService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());

		//充值记录
		card.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
		card.setTerminal("PC");
		card.setCreateId(super.getCurrentUser().getId());
		//平台资金
		BigDecimal TotalMoney = enterpriseCapital.getTotalMoney();
		enterpriseCapital.setTotalMoney(enterpriseCapital.getTotalMoney().add(card.getActualMoney()));
		BigDecimal CurrentMoney = enterpriseCapital.getTotalMoney();
		enterpriseCapital.setUsableMoney(enterpriseCapital.getUsableMoney().add(card.getActualMoney()));
		enterpriseCapital.setUpdateId(super.getCurrentUser().getId());
		enterpriseCapital.setUpdateTime(new Date());
		//企业资金变动 --PC 端充值 日志
		enterpriseAccountLogPo.setChildId(super.getCurrentUser().getEnterpriseId());
		enterpriseAccountLogPo.setParentId(null);
		enterpriseAccountLogPo.setActionType(1L);
		enterpriseAccountLogPo.setCapitalType(1);
		// --资金变动
		enterpriseAccountLogPo.setOldPrice(TotalMoney);
		enterpriseAccountLogPo.setPrice(card.getActualMoney());
		enterpriseAccountLogPo.setCurrentPrice(CurrentMoney);
		// --
		enterpriseAccountLogPo.setCreateTime(new Date());
		enterpriseAccountLogPo.setTerminal("PC");
		enterpriseAccountLogPo.setPhoneSystem(null);
		enterpriseAccountLogPo.setActionDesc(null);

		rechargeService.save(card,enterpriseCapital,enterpriseAccountLogPo);
		OperatorSuccessVo message = new OperatorSuccessVo("充值成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 查看充值信息
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) throws ParseException {

		RechargePo card=rechargeService.queryById(id);
		model.addAttribute("card", card);
		return "/capital/enterprise/info/recharge/card";
	}


}