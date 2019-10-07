package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorDataVo;
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

/**
 * @Description 企业-预收款Action   供应商
 * @author snps
 * @date 2018年3月8日 下午2:11:50
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/storageMoney/supplier"})
public class EnterpriseStorageMoneySupplierAction extends BaseAction {

	@Resource
	private EnterpriseStorageMoneyService enterpriseStorageMoneyService;

	@Resource
	private EnterpriseStorageLogService enterpriseStorageLogService;

	@Resource
	private EnterpriseAccountLogService enterpriseAccountLogService;

	@Resource
	private InfoEnterpriseService infoEnterpriseService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/supplier/main";
	}

	/**
	 *供应商_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/supplier/list";
	}

	/**
	 * 根据企业id_查询供应商
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
		Page<EnterpriseStorageMoneyChasePo> page = enterpriseStorageMoneyService.getMySupplier(pager,name,super.getCurrentUser().getEnterpriseId());

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 查看预存款设置
	 */
	@RequestMapping(value = "/{id}/toStorage", method = RequestMethod.GET)
	public String toStorage(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("parentId", id);
		return "/capital/enterprise/storageMoney/supplier/storageList";
	}
	/**
	 * 查看供应商信息
	 */
	@RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("parentId", id);
		return "/capital/enterprise/storageMoney/supplier/cardList";
	}

	/**
	 * 查看我对供应商明细列表
	 */
	@RequestMapping(value = "/{id}/CardList", method = RequestMethod.GET)
	@ResponseBody
	public String CardList(HttpServletRequest request, @PathVariable Long parentId, Model model) {
        // 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<EnterpriseAccountLogPo> pager = new Page<EnterpriseAccountLogPo>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		long childId = super.getCurrentUser().getEnterpriseId();
		Page<EnterpriseAccountLogPo> page =enterpriseAccountLogService.querySeach(pager,childId,parentId);
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
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
		long childId = super.getCurrentUser().getEnterpriseId();
		Long parentId = id;
		Page<EnterpriseStorageLogPo> page =enterpriseStorageLogService.querySearch(pager,childId,parentId);
		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}

	/**
	 * 查看我对供应商明细信息
	 */
	@RequestMapping(value = "/{id}/card", method = RequestMethod.GET)
	public String card(HttpServletRequest request, @PathVariable Long id, Model model)  {
		EnterpriseAccountLogPo card=enterpriseAccountLogService.queryById(id);
		model.addAttribute("card", card);
		return "/capital/enterprise/storageMoney/supplier/card";
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