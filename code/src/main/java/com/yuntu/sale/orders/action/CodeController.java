
package com.yuntu.sale.orders.action;


import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 码信息表Controller
 * @author zy
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "/order/code/code")
public class CodeController extends BaseAction {

	@Resource
	private CodeService codeService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/product/category/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/product/category/list" ;
	}

	/**
	 * 通过商品类别（模糊）查询
	 */
	@RequestMapping(value="/getByName", method=RequestMethod.GET)
	@ResponseBody
	public String getByName(HttpServletRequest request, String name) {
		String json = JSONObject.toJSON("").toString() ;
		return json ;
	}

	/**
	 * 进入新建页
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/product/category/add" ;
	}

	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Code entity) {
			OperatorSuccessVo message = new OperatorSuccessVo("新建商品类别成功！") ;
			return JSONObject.toJSONString(message) ;
	}

	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		return "/product/category/edit" ;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, String name) {
			OperatorFailureVo message = new OperatorFailureVo("此商品类别已存在！") ;
			return JSONObject.toJSONString(message) ;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		OperatorSuccessVo message = new OperatorSuccessVo("删除商品类别成功！") ;
		return JSONObject.toJSONString(message) ;
	}
}