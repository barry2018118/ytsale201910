package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.capital.po.RechargePo;
import com.yuntu.sale.capital.service.RechargeService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @Description 平台-充值资金Action
 * @author snps
 * @date 2018年3月18日 上午11:21:23
 */
@Controller
@RequestMapping(value = {"/capital/platform/money/recharge"})
public class PlatformCapitalRechargeAction extends BaseAction {

    @Resource
    private RechargeService rechargeService;

    @Resource
    private InfoEnterpriseService infoEnterpriseService;
    /**
     * 充值_主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/capital/platform/recharge/main";
    }

    /**
     * 充值_列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "/capital/platform/recharge/list";
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
        Page<RechargePo> page = rechargeService.queryByTime(pager,null, username, start, end);
        // 处理成前端需要的Json对象
        PageJsonVo jsonData = new PageJsonVo();
        jsonData.setTotal(page.getTotal());
        jsonData.setRows(page.getResult());
        String json = JSONObject.toJSON(jsonData).toString();
        return json;
    }

    /**
     * 查看充值信息
     */
    @RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) throws ParseException {
        RechargePo card=rechargeService.queryById(id);
        model.addAttribute("card", card);

        InfoEnterprise infoEnterprise=infoEnterpriseService.getById(card.getEnterpriseId());
        model.addAttribute("infoEnterprise", infoEnterprise);
        return "/capital/platform/recharge/card";
    }
	
}