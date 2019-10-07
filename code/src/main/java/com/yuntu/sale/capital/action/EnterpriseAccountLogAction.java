package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @Description  企业-资金变动Action
 * @author snps
 * @date 2018年3月18日 上午11:24:47
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/accountLog"})
public class EnterpriseAccountLogAction extends BaseAction {

    @Resource
    private EnterpriseAccountLogService service;

    /**
     * 企业-资金变动_主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/capital/enterprise/account_log/main";
    }

    /**
     * 列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "/capital/enterprise/account_log/list";
    }

    /**
     * 根据
     */
    @RequestMapping(value = "/toSearch", method = RequestMethod.GET)
    @ResponseBody
    public String accountLogSearch(HttpServletRequest request,Long actionType,String startTime,String endTime) {
        // 处理页面参数
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        // 调用分页查询
        Page<EnterpriseAccountLogPo> pager = new Page<EnterpriseAccountLogPo>();
        pager.setStart(pageNumber);
        pager.setSize(pageSize);
        Page<EnterpriseAccountLogPo> page = service.queryByActionType(pager, super.getCurrentUser().getEnterpriseId(), actionType,startTime,endTime);

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

        EnterpriseAccountLogPo card = service.queryById(id);
        model.addAttribute("card", card);
        return "/capital/enterprise/account_log/card";
    }
}