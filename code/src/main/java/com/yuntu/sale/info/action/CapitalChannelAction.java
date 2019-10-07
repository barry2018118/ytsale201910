package com.yuntu.sale.info.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuntu.sale.info.service.CapitalChannelService;
import com.yuntu.sale.manage.action.BaseAction;

/**
 * @Description 资金渠道Action
 * @author snps
 * @date 2018年3月8日 上午9:11:15
 */
@Controller
@RequestMapping(value = {"/info/capitalChannel"})
public class CapitalChannelAction extends BaseAction {

	@Resource
	private CapitalChannelService capitalChannelService;
	
	
	
}