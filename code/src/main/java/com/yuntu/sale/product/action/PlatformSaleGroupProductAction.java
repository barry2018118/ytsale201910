package com.yuntu.sale.product.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.*;
import com.yuntu.sale.product.service.*;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 商品组 设置商品
 * @author zy
 * @version 2018-04-02
 */

@Controller
@RequestMapping(value = "/product/platform/salegroup/product")
public class PlatformSaleGroupProductAction extends BaseAction {

	@Autowired
	private SaleGroupService saleGroupService;

	@Autowired
	private SaleGroupProductService saleGroupProductService;

	@Autowired
	private InfoUserService infoUserService;

	@Autowired
	private InfoEnterpriseService infoEnterpriseService;

	@Autowired
	private SaleGroupLogService saleGroupLogService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private SupplierProductService supplierProductService;

	@Autowired
	private EnterpriseProductService enterpriseProductService;

	@Autowired
	private SaleGroupEnterpriseService saleGroupEnterpriseService;
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/{id}/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("salegroupId", id) ;
		return "/product/platform/salegroup/product/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("salegroupId", id) ;
		return "/product/platform/salegroup/product/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/{id}/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request,@PathVariable Long id, String no, String name) {

		SaleGroup enpity = saleGroupService.getById(id);
		InfoUser infoUser=infoUserService.getById(enpity.getCreateId());
		long createGroupId = infoUser.getEnterpriseId();

		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<SaleGroupProductVo> pager = new Page<SaleGroupProductVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<SaleGroupProductVo> page = saleGroupProductService.getList(pager,no, name, id,createGroupId) ;//检索编号,名称,分销组id,创建分销组id

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}


}
