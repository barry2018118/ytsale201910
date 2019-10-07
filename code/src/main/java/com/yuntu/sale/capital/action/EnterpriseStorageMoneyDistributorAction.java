package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorDataVo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyChasePo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.capital.service.EnterpriseStorageLogService;
import com.yuntu.sale.capital.service.EnterpriseStorageMoneyService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoEnterprisePo;
import com.yuntu.sale.user.service.InfoEnterpriseService;
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
 * @Description 企业-预收款Action 分销商
 * @author snps
 * @date 2018年3月8日 下午2:11:50
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/storageMoney/distributor"})
public class EnterpriseStorageMoneyDistributorAction extends BaseAction {

	@Resource
	private EnterpriseStorageMoneyService enterpriseStorageMoneyService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;

	@Resource
	private InfoEnterpriseService infoEnterpriseService;

	@Resource
	private EnterpriseStorageLogService enterpriseStorageLogService;


	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/distributor/main";
	}

	/**
	 *分销商 _列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/distributor/list";
	}
	/**
	 * 根据企业id_查询分销商
	 */
	@RequestMapping(value = "/toSearch", method = RequestMethod.GET)
	@ResponseBody
	public String toSearch(HttpServletRequest request,String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseStorageMoneyChasePo> pager = new Page<EnterpriseStorageMoneyChasePo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<EnterpriseStorageMoneyChasePo> page = enterpriseStorageMoneyService.getMyDistributor(pager,name,super.getCurrentUser().getEnterpriseId());

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	/**
	 * 查看分销商 信息
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String toCard(HttpServletRequest request, @PathVariable Long id, Model model) throws ParseException {
		model.addAttribute("childId", id);
		return "/capital/enterprise/storageMoney/distributor/cardList";
	}
	/**
	 * 查看分销商对我明细列表
	 */
	@RequestMapping(value = "/{id}/CardList", method = RequestMethod.GET)
	@ResponseBody
	public String CardList(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseAccountLogPo> pager = new Page<EnterpriseAccountLogPo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Long childId = id;
		long parentId = super.getCurrentUser().getEnterpriseId();
		Page<EnterpriseAccountLogPo> page =enterpriseAccountLogService.querySeach(pager,childId,parentId);
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 查看分销商对我明细信息
	 */
	@RequestMapping(value = "/{id}/card", method = RequestMethod.GET)
	public String card(HttpServletRequest request, @PathVariable Long id, Model model)  {
		EnterpriseAccountLogPo card=enterpriseAccountLogService.queryById(id);
		model.addAttribute("card", card);
		return "/capital/enterprise/storageMoney/distributor/card";
	}

	/**
	 * 查看预存款设置
	 */
	@RequestMapping(value = "/{id}/toStorage", method = RequestMethod.GET)
	public String toStorage(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("childId", id);
		return "/capital/enterprise/storageMoney/distributor/storageList";
	}

	/**
	 * 查看我对供应商明细列表
	 */
	@RequestMapping(value = "/{id}/StorageList", method = RequestMethod.GET)
	@ResponseBody
	public String StorageList(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseStorageLogPo> pager = new Page<EnterpriseStorageLogPo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		long childId = id;
		Long parentId = super.getCurrentUser().getEnterpriseId();
		Page<EnterpriseStorageLogPo> page =enterpriseStorageLogService.querySearch(pager,childId,parentId);
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 预收款设置
	 */
	@RequestMapping(value = "/{id}/toSet", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, @PathVariable Long id, Model model) {
		EnterpriseStorageMoneyPo SetCard = enterpriseStorageMoneyService.querySearch(id,super.getCurrentUser().getEnterpriseId());
		if(BaseUtil.isEmpty(SetCard)) {
			SetCard = new EnterpriseStorageMoneyPo();
			SetCard.setParentId(super.getCurrentUser().getEnterpriseId());
			SetCard.setChildId(id);
			SetCard.setStorageMoney(new BigDecimal(0.00));
		}
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		model.addAttribute("enterprise", enterprise);
		model.addAttribute("storage", SetCard);
		return  "/capital/enterprise/storageMoney/distributor/set";
	}

	/**
	 * 新建对下级预收款设置
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, EnterpriseStorageMoneyPo card) {
		//检测 ChildId 和 ParentId 记录是否存在
		EnterpriseStorageMoneyPo newCard = enterpriseStorageMoneyService.queryById(card.getId());
		EnterpriseStorageLogPo newLog = new EnterpriseStorageLogPo();
		EnterpriseAccountLogPo enterpriseAccountLogPo = new EnterpriseAccountLogPo();
		if(!BaseUtil.isEmpty(newCard)) {
			// 预收款存在
			System.out.print(newCard.getStorageMoney());
			System.out.print(card.getStorageMoney());
			BigDecimal money = null;
			if(newCard.getStorageMoney() != null){
				 money = newCard.getStorageMoney().add(card.getStorageMoney());
			}
			//预收款日志
			newLog.setParentId(card.getParentId());
			newLog.setChildId(card.getChildId());
			newLog.setBeforeMoney(newCard.getStorageMoney());
			newLog.setStorageMoney(card.getStorageMoney());
			newLog.setAfterMoney(money);
			newLog.setCreateId(super.getCurrentUser().getId());
			newLog.setCreateTime(new Date());
			//资金流动日志
			enterpriseAccountLogPo.setChildId(card.getParentId());
			enterpriseAccountLogPo.setParentId(card.getChildId());
			enterpriseAccountLogPo.setActionType(null);
			enterpriseAccountLogPo.setActionId(newCard.getId());
			enterpriseAccountLogPo.setCapitalType(0);
			//  -- 资金变动
			enterpriseAccountLogPo.setOldPrice(newCard.getStorageMoney());
			enterpriseAccountLogPo.setPrice(card.getStorageMoney());
			enterpriseAccountLogPo.setCurrentPrice(money);
			//  --
			enterpriseAccountLogPo.setCreateTime(new Date());
			enterpriseAccountLogPo.setTerminal("PC");
			enterpriseAccountLogPo.setPhoneSystem(null);
			enterpriseAccountLogPo.setActionDesc(null);

			newCard.setStorageMoney(money);
			enterpriseStorageMoneyService.update(newCard,newLog,enterpriseAccountLogPo);
		}else{
			// 预收款不存在
			newCard = new EnterpriseStorageMoneyPo();
			newCard.setChildId(card.getChildId());
			newCard.setParentId(super.getCurrentUser().getEnterpriseId());
			newCard.setStorageMoney(card.getStorageMoney());
			//预收款日志
			newLog.setParentId(super.getCurrentUser().getEnterpriseId());
			newLog.setChildId(card.getChildId());
			newLog.setBeforeMoney(null);
			newLog.setStorageMoney(card.getStorageMoney());
			newLog.setAfterMoney(card.getStorageMoney());
			newLog.setCreateId(super.getCurrentUser().getId());
			newLog.setCreateTime(new Date());
			//资金流动日志
			enterpriseAccountLogPo.setChildId(card.getParentId());
			enterpriseAccountLogPo.setParentId(card.getChildId());
			enterpriseAccountLogPo.setParentId(null);
			enterpriseAccountLogPo.setActionType(null);
			enterpriseAccountLogPo.setActionId(newCard.getId());
			enterpriseAccountLogPo.setCapitalType(0);
			//  -- 资金变动
			enterpriseAccountLogPo.setOldPrice(null);
			enterpriseAccountLogPo.setPrice(card.getStorageMoney());
			enterpriseAccountLogPo.setCurrentPrice(card.getStorageMoney());
			// --
			enterpriseAccountLogPo.setCreateTime(new Date());
			enterpriseAccountLogPo.setTerminal("PC");
			enterpriseAccountLogPo.setPhoneSystem(null);
			enterpriseAccountLogPo.setActionDesc(null);

			enterpriseStorageMoneyService.save(newCard,newLog,enterpriseAccountLogPo);
		}
		OperatorSuccessVo message = new OperatorSuccessVo("预存款设置成功！");
		return JSONObject.toJSONString(message);
	}

	/**
	 * 根据id查询企业信息
	 */
	@RequestMapping(value = "/{id}/enterprise", method = RequestMethod.POST)
	@ResponseBody
	public String search(HttpServletRequest request, @PathVariable Long id) {
		InfoEnterprise enterprise = infoEnterpriseService.getById(id);
		OperatorDataVo jsonVo = new OperatorDataVo(Boolean.TRUE);
		jsonVo.setMessage(enterprise);
		return JSONObject.toJSON(jsonVo).toString();
	}
}