package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseBankCardPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.ExtractPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.capital.service.EnterpriseBankCardService;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.capital.service.ExtractService;
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
import java.util.List;


/**
 * @Description 企业-提现Action
 * @author snps
 * @date 2018年3月8日 下午2:53:13
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/info/extract"})
public class EnterpriseExtractAction extends BaseAction {

	@Resource
	private EnterpriseBankCardService enterpriseBankCardService;

	@Resource
	private ExtractService extractService;

	@Resource
	private EnterpriseCapitalService enterpriseCapitalService;

	/**
	 * 提现_主页
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/info/extract/main";
	}

	/**
	 * 提现_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/capital/enterprise/info/extract/list";
	}

	/**
	 * 根据企业id_查询提现数据
	 */
	@RequestMapping(value = "/toSearch", method = RequestMethod.GET)
	@ResponseBody
	public String myCardSearch(HttpServletRequest request, String start,String end,Integer state) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<ExtractPo> pager = new Page<ExtractPo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<ExtractPo> page = extractService.queryByTime(pager,super.getCurrentUser().getEnterpriseId(), start, end,state);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	/**
	 * 新增提现
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		List<EnterpriseBankCardPo> bank = enterpriseBankCardService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());
		model.addAttribute("bank", bank);
		EnterpriseCapitalPo capital = enterpriseCapitalService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());
		model.addAttribute("capital", capital.getUsableMoney());
		return "/capital/enterprise/info/extract/add";
	}

	/**
	 * 新增
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, ExtractPo card) {
		//检验提现资金是否超出企业可提现资金
		EnterpriseCapitalPo enterpriseCapital = enterpriseCapitalService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());
		if(card.getExtractMoney().compareTo(enterpriseCapital.getUsableMoney()) == 1 ){
			OperatorFailureVo msg = new OperatorFailureVo("申请提现金额超出企业可提现金额"+enterpriseCapital.getUsableMoney()+" !!!");
			return JSONObject.toJSONString(msg);
		}else{
			//提现表
			ExtractPo newCard = new ExtractPo();
			newCard.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
			newCard.setBankCardId(card.getBankCardId());
			newCard.setExtractMoney(card.getExtractMoney());
			newCard.setCreateId(super.getCurrentUser().getId());
			//平台资金
			enterpriseCapital.setForzenMoney(enterpriseCapital.getForzenMoney().add(card.getExtractMoney()));//冻结资金
			enterpriseCapital.setUsableMoney(enterpriseCapital.getUsableMoney().subtract(card.getExtractMoney()));//可提现资金
			enterpriseCapital.setUpdateId(super.getCurrentUser().getId());
			enterpriseCapital.setUpdateTime(new Date());

			extractService.save(newCard,enterpriseCapital);
			OperatorSuccessVo message = new OperatorSuccessVo("提现申请成功！");
			return JSONObject.toJSONString(message);
		}
	}

	/**
	 * 查看提现信息
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) throws ParseException {
		ExtractPo card=extractService.queryById(id);
		model.addAttribute("card", card);
		return "/capital/enterprise/info/extract/card";
	}
}