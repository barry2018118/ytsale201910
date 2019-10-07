package com.yuntu.sale.product.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yuntu.sale.product.po.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.service.ProductCategoryService;

/**
 * @Description 产品类别Action 
 * @author snps
 * @date 2018年2月14日 下午4:21:32
 */

@Controller
@RequestMapping(value = {"/product/category"})
public class ProductCategoryAction extends BaseAction {

	@Resource
	private ProductCategoryService productCategoryService ;
	
	
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
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<ProductCategory> pager = new Page<ProductCategory>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<ProductCategory> page = productCategoryService.getByName(pager, name) ;
		
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
		return "/product/category/add" ;
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, ProductCategory entity) {
		// 通过商品类别名称进行查询
		String theName = entity.getName() ;
		ProductCategory existEntity = productCategoryService.getByUniqueName(theName) ;
		
		// 如果已存在同名的商品类别，返回提示信息
		if(!BaseUtil.isEmpty(existEntity)) {
			OperatorFailureVo message = new OperatorFailureVo("此商品类别已存在！") ;
			return JSONObject.toJSONString(message) ;
		} else {	// 如果不存在同名的商品类别，执行保存操作
			productCategoryService.save(entity);
			OperatorSuccessVo message = new OperatorSuccessVo("新建商品类别成功！") ;
			return JSONObject.toJSONString(message) ;
		}
	}
	
	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		ProductCategory entity = productCategoryService.getById(id) ;
		model.addAttribute("entity", entity) ;
		return "/product/category/edit" ;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id, String name) {
		// 通过商品类别名称进行查询
		ProductCategory existEntity = productCategoryService.getByUniqueName(name) ;
		
		// 通过id获得要修改的商品类别信息
		ProductCategory entity = productCategoryService.getById(id) ;
		
		// 如果不存在同名的商品类别，执行修改操作
		if(BaseUtil.isEmpty(existEntity) || (!BaseUtil.isEmpty(existEntity) && name.equals(entity.getName()))) {
			entity.setName(name);
			productCategoryService.update(entity);
			OperatorSuccessVo message = new OperatorSuccessVo("编辑商品类别成功！") ;
			return JSONObject.toJSONString(message) ;
		} else {	// 如果已存在同名的商品类别，返回提示信息
			OperatorFailureVo message = new OperatorFailureVo("此商品类别已存在！") ;
			return JSONObject.toJSONString(message) ;
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		productCategoryService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除商品类别成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
}