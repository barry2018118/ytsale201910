package com.yuntu.sale.capital.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.coolshow.util.Page;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.yuntu.sale.base.FileUploadAider;
import com.yuntu.sale.base.GrobalSystemConstant;
import com.yuntu.sale.base.RandomStringUtils;
import com.yuntu.sale.base.servlet.PropertiesInitServlet;
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
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import org.apache.commons.io.FileUtils;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @Description 平台-提现资金Action
 * @author snps
 * @date 2018年3月18日 上午11:21:23
 */
@Controller
@RequestMapping(value = {"/capital/platform/money/extract"})
public class PlatformCapitalExtractAction extends BaseAction {

    @Resource
    EnterpriseBankCardService enterpriseBankCardService;

    @Resource
    private ExtractService extractService;

    @Resource
    private EnterpriseCapitalService enterpriseCapitalService;

    @Resource
    private InfoEnterpriseService infoEnterpriseService;

    /**
     * 提现_主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/capital/platform/extract/main";
    }

    /**
     * 提现_列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "/capital/platform/extract/list";
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
        Page<ExtractPo> page = extractService.queryByTime(pager,null, start, end,state);

        // 处理成前端需要的Json对象
        PageJsonVo jsonData = new PageJsonVo();
        jsonData.setTotal(page.getTotal());
        jsonData.setRows(page.getResult());
        String json = JSONObject.toJSON(jsonData).toString();
        return json;
    }

    /**
     * 提现审核
     */
    @RequestMapping(value = "/{id}/toAdd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request,@PathVariable Long id, Model model) {

        ExtractPo card=extractService.queryById(id);
        model.addAttribute("card", card);

        InfoEnterprise infoEnterprise=infoEnterpriseService.getById(card.getEnterpriseId());
        model.addAttribute("infoEnterprise", infoEnterprise);

        EnterpriseBankCardPo bank = enterpriseBankCardService.getById(card.getBankCardId());
        model.addAttribute("bank", bank);
        return "/capital/platform/extract/add";
    }

    /**
     * 审核
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletRequest request, ExtractPo card,
                      @RequestParam(value = "file", required = false) MultipartFile file)  throws IOException {
        //提现表
        ExtractPo newCard = extractService.queryById(card.getId());
        //平台资金
        EnterpriseCapitalPo enterpriseCapital = enterpriseCapitalService.getByEnterpriseId(newCard.getEnterpriseId());
        //日志
        EnterpriseAccountLogPo enterpriseAccountLogPo = new EnterpriseAccountLogPo();
        //
        BigDecimal TotalMoney = new BigDecimal(0);//变动前
        BigDecimal PriceMoney = new BigDecimal(0);//本次变动
        BigDecimal CurrentMoney = new BigDecimal(0);//变动后

        newCard.setAuditId(super.getCurrentUser().getId());
        newCard.setAuditTime(new Date());
        newCard.setStatus(card.getStatus());
        if(card.getStatus() == 1){//通过
            //图片上传
            Map<String, String> path = new HashMap<String, String>();
            //path.put("windows","/capital/voucher/");
            path.put("linux","project.voucher.path");
            Map<String, String> filename = FileUploadAider.uploadPic(request,super.getCurrentUser().getEnterpriseId(),path,file);
            if(filename.containsKey("fail")){
                OperatorFailureVo msg = new OperatorFailureVo(filename.get("fail"));
                return JSONObject.toJSONString(msg) ;
            }
            //newCard.setVoucher(filename.get("filename"));
            newCard.setVoucher(filename.get("uploadPath"));
            //平台资金
            TotalMoney = enterpriseCapital.getTotalMoney();
            PriceMoney = newCard.getExtractMoney();
            enterpriseCapital.setTotalMoney(enterpriseCapital.getTotalMoney().subtract(newCard.getExtractMoney()));
            CurrentMoney = enterpriseCapital.getTotalMoney();
        }else if(card.getStatus() == 2){//不通过
            newCard.setReason(card.getReason());
            //平台资金
            enterpriseCapital.setUsableMoney(enterpriseCapital.getUsableMoney().add(newCard.getExtractMoney()));
            TotalMoney = enterpriseCapital.getTotalMoney();
            PriceMoney =new BigDecimal(0);
            CurrentMoney = enterpriseCapital.getTotalMoney();
        }
        enterpriseCapital.setForzenMoney(enterpriseCapital.getForzenMoney().subtract(newCard.getExtractMoney()));//冻结资金
        enterpriseCapital.setUpdateId(super.getCurrentUser().getId());
        enterpriseCapital.setUpdateTime(new Date());
        //企业资金表
        enterpriseAccountLogPo.setChildId(newCard.getEnterpriseId());
        enterpriseAccountLogPo.setParentId(null);
        enterpriseAccountLogPo.setActionType(2L);
        enterpriseAccountLogPo.setCapitalType(1);
        enterpriseAccountLogPo.setOldPrice(TotalMoney);
        enterpriseAccountLogPo.setPrice(PriceMoney.multiply(new BigDecimal(-1)));
        enterpriseAccountLogPo.setCurrentPrice(CurrentMoney);
        enterpriseAccountLogPo.setCreateTime(new Date());
        enterpriseAccountLogPo.setTerminal("PC");
        enterpriseAccountLogPo.setPhoneSystem(null);
        enterpriseAccountLogPo.setActionDesc(null);
        extractService.update(newCard,enterpriseCapital,enterpriseAccountLogPo);
        OperatorSuccessVo message = new OperatorSuccessVo("审核成功！");
        return JSONObject.toJSONString(message);
    }

    /**
     * 查看提现信息
     */
    @RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) throws ParseException {

        ExtractPo card=extractService.queryById(id);
        model.addAttribute("card", card);

        InfoEnterprise infoEnterprise=infoEnterpriseService.getById(card.getEnterpriseId());
        model.addAttribute("infoEnterprise", infoEnterprise);

        EnterpriseBankCardPo bank = enterpriseBankCardService.getById(card.getBankCardId());
        model.addAttribute("bank", bank);
        return "/capital/platform/extract/card";
    }
	
}