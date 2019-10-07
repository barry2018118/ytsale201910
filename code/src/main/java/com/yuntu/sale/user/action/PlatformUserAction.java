package com.yuntu.sale.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * @Description 平台用户管理Action
 * @author snps
 * @date 2018年2月24日 下午2:01:16
 */
@Controller
@RequestMapping(value = {"/platform/user"})
public class PlatformUserAction extends BaseAction {

	@Resource
	private InfoUserService infoUserService;

	/**
	 * 用户_主页
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/user/platform/main";
	}

	/**
	 * 用户_列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/user/platform/list";
	}

	/**
	 * 平台运营商_数据查询
	 */
	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	@ResponseBody
	public String search(HttpServletRequest request, String username, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// 调用分页查询
		Page<InfoUser> pager = new Page<InfoUser>();
		pager.setStart(pageNumber);
		pager.setSize(pageSize);
		Page<InfoUser> page = infoUserService.getUser(pager, username, name);

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo();
		jsonData.setTotal(page.getTotal());
		jsonData.setRows(page.getResult());
		String json = JSONObject.toJSON(jsonData).toString();
		return json;
	}
	
}