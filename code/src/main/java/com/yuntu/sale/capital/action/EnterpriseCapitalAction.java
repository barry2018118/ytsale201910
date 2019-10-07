package com.yuntu.sale.capital.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuntu.sale.capital.service.EnterpriseCapitalService;
import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 企业-资金管理Action
 * @author snps
 * @date 2018年3月8日 下午2:44:55
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/money"})
public class EnterpriseCapitalAction extends BaseAction {

    @Resource
    private EnterpriseCapitalService enterpriseCapitalService;

    /**
     * 查看企业资金概览
     */
    @RequestMapping(value = "/capital", method = RequestMethod.GET)
    public String capital(HttpServletRequest request, Model model ) {
        EnterpriseCapitalPo capitalInfo=enterpriseCapitalService.getByEnterpriseId(super.getCurrentUser().getEnterpriseId());
        model.addAttribute("info", capitalInfo);
        return "/capital/enterprise/list";
    }

}