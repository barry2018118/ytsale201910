package com.yuntu.sale.capital.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorDataVo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseBankCardPo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yuntu.sale.capital.service.EnterpriseBankCardService;
import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description 企业-银行卡Action
 * @author snps
 * @date 2018年3月8日 下午2:53:13
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/bankCard"})
public class EnterpriseBankCardAction extends BaseAction {

	@Resource
	private EnterpriseBankCardService enterpriseBankCardService;

	/**
	 * 用户_主页
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/bank/main";
	}

	/**
	 * 用户_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/capital/enterprise/bank/list";
	}

	/**
	 * 根据企业id_查询银行卡数据
	 */
	@RequestMapping(value = "/toSearch", method = RequestMethod.GET)
	@ResponseBody
	public String myCardSearch(HttpServletRequest request,Long enterpriseId, String username, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseBankCardPo> pager = new Page<EnterpriseBankCardPo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<EnterpriseBankCardPo> page = enterpriseBankCardService.getMyBankCard(pager,getCurrentUser().getEnterpriseId(), username, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	/**
	 * 新建银行卡
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/capital/enterprise/bank/add";
	}

	/**
	 * 新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, EnterpriseBankCardPo card) {
		// 检查卡号是否存在
		String cardNO = card.getCardNo();
		EnterpriseBankCardPo existUser = enterpriseBankCardService.getByCardNo(cardNO);
		if(!BaseUtil.isEmpty(existUser)) {
			OperatorFailureVo message = new OperatorFailureVo("此银行卡已存在！");
			return JSONObject.toJSONString(message);
		}
		EnterpriseBankCardPo newCard = new EnterpriseBankCardPo();
		// 设置员工信息
		newCard.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
		newCard.setBank(card.getBank());
		newCard.setAddress(card.getAddress());
		newCard.setCardNo(card.getCardNo());
		newCard.setCardMaster(card.getCardMaster());
		// newCard.setCardMasterNo(card.getCardMasterNo());
		// newCard.setCardMasterPhone(card.getCardMasterPhone());
		newCard.setRemark(card.getRemark());
		newCard.setCreateId(super.getCurrentUser().getId());

		// 保存信息并分配功能
		enterpriseBankCardService.save(newCard);
		OperatorSuccessVo message = new OperatorSuccessVo("新银行卡添加成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 编辑银行卡信息
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		EnterpriseBankCardPo card=enterpriseBankCardService.getById(id);
		model.addAttribute("card", card);
		return "/capital/enterprise/bank/edit";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable  Long id, EnterpriseBankCardPo card) {
		//当前员工用户
		EnterpriseBankCardPo cardUpdate = enterpriseBankCardService.getById(id);

		cardUpdate.setBank(card.getBank());
		cardUpdate.setAddress(card.getAddress());
		cardUpdate.setCardNo(card.getCardNo());
		cardUpdate.setCardMaster(card.getCardMaster());
		// cardUpdate.setCardMasterNo(card.getCardMasterNo());
		// cardUpdate.setCardMasterPhone(card.getCardMasterPhone());
		cardUpdate.setRemark(card.getRemark());

		enterpriseBankCardService.update(cardUpdate);
		OperatorSuccessVo message = new OperatorSuccessVo("银行卡信息编辑成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id) {
		enterpriseBankCardService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 根据id查询信息
	 */
	@RequestMapping(value = "/{id}/search", method = RequestMethod.POST)
	@ResponseBody
	public String search(HttpServletRequest request, @PathVariable Long id) {
		EnterpriseBankCardPo listCard = enterpriseBankCardService.getById(id);

		OperatorDataVo jsonVo = new OperatorDataVo(Boolean.TRUE);
		jsonVo.setMessage(listCard);
		return JSONObject.toJSON(jsonVo).toString();
	}
}