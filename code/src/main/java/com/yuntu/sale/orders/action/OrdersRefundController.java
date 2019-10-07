
package com.yuntu.sale.orders.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuntu.sale.manage.action.BaseAction;

/**
 * 退款主表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/orders/refund")
public class OrdersRefundController extends BaseAction{
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
        return "/orders/refund/main";
    }

    @RequestMapping(value = "/tomain", method = RequestMethod.GET)
    public String tomain(HttpServletRequest request, Model model) {
        return "/orders/refund/tomain";
    }


}