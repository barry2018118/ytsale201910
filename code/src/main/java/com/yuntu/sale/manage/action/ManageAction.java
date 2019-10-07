package com.yuntu.sale.manage.action;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理后台
 * @Description 
 * @author snps
 * @date 上午10:25:26
 */
@Controller
@RequestMapping(value = {"/manage"})
public class ManageAction {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Model m, HttpServletRequest request) {
		return "/layouts/common/main" ;
	}
}