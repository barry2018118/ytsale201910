package com.yuntu.sale.capital.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 企业-预收款Action
 * @author snps
 * @date 2018年3月8日 下午2:11:50
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/storageMoney"})
public class EnterpriseStorageMoneyAction extends BaseAction {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/main";
	}

	@RequestMapping(value = "/tomain", method = RequestMethod.GET)
	public String tomain(HttpServletRequest request, Model model) {
		return "/capital/enterprise/storageMoney/tomain";
	}

}