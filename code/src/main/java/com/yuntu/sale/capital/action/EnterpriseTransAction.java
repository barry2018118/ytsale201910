package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.service.EnterpriseAccountLogService;
import com.yuntu.sale.manage.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description  交易资金Action
 * @author zy
 * @date 2018年5月14日
 */
@Controller
@RequestMapping(value = {"/capital/enterprise/trans"})
public class EnterpriseTransAction extends BaseAction {

    @Resource
    private EnterpriseAccountLogService service;

    /**
     * 主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/capital/enterprise/trans/main";
    }

    /**
     * 列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "/capital/enterprise/trans/list";
    }

    /**
     * 根据
     */
    @RequestMapping(value = "/toSearch", method = RequestMethod.GET)
    @ResponseBody
    public String accountLogSearch(HttpServletRequest request,Long actionType) {
        // 处理页面参数
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        // 调用分页查询
        Page<EnterpriseAccountLogPo> pager = new Page<EnterpriseAccountLogPo>();
        pager.setStart(pageNumber);
        pager.setSize(pageSize);
        Page<EnterpriseAccountLogPo> page = service.queryByActionType(pager, super.getCurrentUser().getEnterpriseId(), actionType,null,null);

        // 处理成前端需要的Json对象
        PageJsonVo jsonData = new PageJsonVo();
        jsonData.setTotal(page.getTotal());
        jsonData.setRows(page.getResult());
        String json = JSONObject.toJSON(jsonData).toString();
        return json;
    }

    /**
     * 查看明细信息
     */
    @RequestMapping(value = "/{id}/card", method = RequestMethod.GET)
    public String card(HttpServletRequest request, @PathVariable Long id, Model model)  {

        EnterpriseAccountLogPo card = service.queryById(id);
        model.addAttribute("card", card);
        return "/capital/enterprise/trans/card";
    }
}