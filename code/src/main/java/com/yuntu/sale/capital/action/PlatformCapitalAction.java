package com.yuntu.sale.capital.action;

import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * @Description 平台-资金管理Action
 * @author snps
 * @date 2018年3月18日 上午11:21:23
 */
@Controller
@RequestMapping(value = {"/capital/platform/money"})
public class PlatformCapitalAction extends BaseAction {

    @Resource
    private EnterpriseCapitalService enterpriseCapitalService;
    /**
     * 查看平台资金概览
     */
    @RequestMapping(value = "/capital", method = RequestMethod.GET)
    public String capital(HttpServletRequest request, Model model ) {
        EnterpriseCapitalPo capitalInfo= enterpriseCapitalService.getSum();
        model.addAttribute("info", capitalInfo);
        return "/capital/platform/list";
    }
	
}