
package com.yuntu.sale.orders.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.EnterpriseOrdersRefund;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.orders.service.EnterpriseOrdersRefundService;
import com.yuntu.sale.orders.service.OrdersRefundService;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.orders.vo.EnterpriseOrdersRefundVo;
import com.yuntu.sale.orders.vo.OrdersRefundListVo;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 退款主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/platform/refund/my")
public class PlatformOrdersRefundController extends BaseAction{

    @Resource
    private SupplierProductService supplierProductService;

    @Resource
    private ScenicService scenicService;

    @Resource
    private OrdersService ordersService;

    @Resource
    private OrdersRefundService ordersRefundService;

    @Resource
    private EnterpriseOrdersRefundService enterpriseOrdersRefundService;

    @Resource
    private InfoUserService infoUserService;

    /**
     * 进入主页面
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/orders/platform/refund/my/main" ;
    }

    /**
     * 进入列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "/orders/platform/refund/my/list" ;
    }

    /**
     * 通过条件（模糊）查询
     */
    @RequestMapping(value="/getSearch", method=RequestMethod.GET)
    @ResponseBody
    public String getSearch(HttpServletRequest request, String productNo,String productName,String starttime,String endtime,Integer state) {
        // 处理页面参数
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
        int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

        // 调用分页查询
        Page<OrdersRefundListVo> pager = new Page<OrdersRefundListVo>() ;
        pager.setStart(pageNumber) ;
        pager.setSize(pageSize) ;
        Page<OrdersRefundListVo> page = ordersRefundService.getList(pager, null,productNo,productName,starttime,endtime,state);

        // 处理成前端需要的Json对象
        PageJsonVo jsonData = new PageJsonVo() ;
        jsonData.setTotal(page.getTotal()) ;
        jsonData.setRows(page.getResult()) ;
        String json = JSONObject.toJSON(jsonData).toString() ;
        return json ;
    }

    /**
     * 详细页
     */
    @RequestMapping(value = "/{id}/toCard", method = RequestMethod.GET)
    public String getcard(HttpServletRequest request, @PathVariable Long id, Model model) {

        EnterpriseOrdersRefund enterpriseOrders = enterpriseOrdersRefundService.get(id);
        model.addAttribute("refund", enterpriseOrders) ;

        OrdersRefund ordersRefund = ordersRefundService.get(enterpriseOrders.getRefundId());
        model.addAttribute("fund", ordersRefund) ;

        Orders orders = ordersService.get(enterpriseOrders.getOrdersId()) ;
        model.addAttribute("order", orders) ;

        SupplierProduct supplierProduct = supplierProductService.getById(enterpriseOrders.getSproductId());
        model.addAttribute("product", supplierProduct) ;

        InfoScenicPo infoScenicPo = scenicService.getById(supplierProduct.getScenicId());
        model.addAttribute("scenic", infoScenicPo) ;

        InfoUser infoUser = ordersRefund.getCreateId() != null ? infoUserService.getById(ordersRefund.getCreateId()) : new InfoUser();
        model.addAttribute("cname", infoUser) ;

        InfoUser iUser =ordersRefund.getAuditId() != null ? infoUserService.getById(ordersRefund.getAuditId()) : new InfoUser();
        model.addAttribute("aname", iUser) ;
        return "/orders/platform/refund/my/card" ;
    }

}