package com.yuntu.sale.product.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.service.SaleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 分销组Controller
 * @author zy
 * @version 2018-04-02
 */

@Controller
@RequestMapping(value = "/product/salegroup")
public class SaleGroupAction extends BaseAction {

	@Autowired
	private SaleGroupService saleGroupService;
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/product/salegroup/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/product/salegroup/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<SaleGroup> pager = new Page<SaleGroup>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<SaleGroup> page = saleGroupService.queryByName(pager, name,super.getCurrentUser().getEnterpriseId()) ;

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}

	/**
	 * 进入新建页
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, Model model) {
		return "/product/salegroup/add" ;
	}

	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, SaleGroup entity) {
		// 通过商品类别名称进行查询
		String theName = entity.getName() ;
		SaleGroup existEntity = saleGroupService.getByName(theName, super.getCurrentUser().getEnterpriseId());

		// 如果已存在同名的商品组，返回提示信息
		if(!BaseUtil.isEmpty(existEntity)) {
			OperatorFailureVo message = new OperatorFailureVo("分销组已存在！") ;
			return JSONObject.toJSONString(message) ;
		} else {	// 如果不存在同名的商品组，执行保存操作
			SaleGroup sale = new SaleGroup();
			sale.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
			sale.setName(entity.getName());
			sale.setUserNumber(0);
			sale.setProductNumber(0);
			sale.setCreateId(super.getCurrentUser().getId());
			sale.setCreateTime(new Date());
			sale.setUpdateId(super.getCurrentUser().getId());
			sale.setUpdateTime(new Date());
			saleGroupService.save(sale);
			OperatorSuccessVo message = new OperatorSuccessVo("新建分销组成功！") ;
			return JSONObject.toJSONString(message) ;
		}
	}

	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		SaleGroup entity = saleGroupService.getById(id) ;
		model.addAttribute("entity", entity) ;
		return "/product/salegroup/edit" ;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, String name) {
		SaleGroup entity = saleGroupService.getById(id) ;
		entity.setName(name);
		entity.setUpdateId(super.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		saleGroupService.update(entity);
		OperatorSuccessVo message = new OperatorSuccessVo("编辑分销组成功！") ;
		return JSONObject.toJSONString(message) ;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		SaleGroup entity = saleGroupService.getById(id);
		if(entity.getProductNumber() == 0 && entity.getUserNumber() == 0){
			saleGroupService.delete(id);
			OperatorSuccessVo message = new OperatorSuccessVo("删除分销组成功！") ;
			return JSONObject.toJSONString(message) ;
		}else{
			OperatorFailureVo message = new OperatorFailureVo("分销组在分销商品,删除分销组失败！");
			return JSONObject.toJSONString(message) ;
		}

	}
}
