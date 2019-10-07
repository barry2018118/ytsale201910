package com.yuntu.sale.product.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.ProductCategory;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.po.SaleGroupProduct;
import com.yuntu.sale.product.po.SaleGroupProductVo;
import com.yuntu.sale.product.po.SupplierProductVo;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SaleGroupEnterpriseService;
import com.yuntu.sale.product.service.SaleGroupProductService;
import com.yuntu.sale.product.service.SaleGroupService;
import com.yuntu.sale.product.service.SupplierProductService;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * 商品组 设置商品
 * @author zy
 * @version 2018-04-02
 */

@Controller
@RequestMapping(value = "/product/salegroup/product")
public class SaleGroupProductAction extends BaseAction {

	@Autowired
	private SaleGroupService saleGroupService;

	@Autowired
	private SaleGroupProductService saleGroupProductService;

	@Autowired
	private InfoUserService infoUserService;

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
		return "/product/salegroup/product/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("salegroupId", id) ;
		return "/product/salegroup/product/list" ;
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

	/**
	 * 进入新建页
	 */
	@RequestMapping(value = "/{id}/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, @PathVariable Long id,Model model) {
		
		List<ProductCategory> entity = productCategoryService.getAll();
		
		model.addAttribute("EnterpriseId", super.getCurrentUser().getEnterpriseId()) ;
		model.addAttribute("saleGroupId", id) ;
		model.addAttribute("ctategory", entity) ;
		return "/product/salegroup/product/add" ;
	}

	/**
	 * 新建
	 */
	@RequestMapping(value="/{id}/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, @PathVariable Long id, @RequestParam("box[]") String[] box) {
		saleGroupProductService.save(id, box, super.getCurrentUser().getId(), super.getCurrentUser().getEnterpriseId()) ;
		OperatorSuccessVo message = new OperatorSuccessVo("添加商品成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
	/**
	 * 开启分销
	 */
	@RequestMapping(value="/{groupId}/describution/{sproductId}", method=RequestMethod.POST)
	@ResponseBody
	public String describution(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long sproductId) {
		saleGroupProductService.saveDescribution(groupId, sproductId);
		OperatorSuccessVo message = new OperatorSuccessVo("分销商品成功！") ;
		return JSONObject.toJSONString(message) ;
	}
	
	/**
	 * 停止分销
	 */
	@RequestMapping(value="/{groupId}/stopDescribution/{sproductId}", method=RequestMethod.POST)
	@ResponseBody
	public String stopDescribution(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long sproductId) {
		saleGroupProductService.saveStopDescribution(groupId, sproductId);
		OperatorSuccessVo message = new OperatorSuccessVo("商品已停止分销！") ;
		return JSONObject.toJSONString(message) ;
	}
	
	/**
	 * 添加商品（模糊）查询
	 */
	@RequestMapping(value="/addSearch", method=RequestMethod.GET)
	@ResponseBody
	public String addSearch(HttpServletRequest request, Long saleGroupId, String productName, String productNo, 
			Integer productategory, Integer productStatus, Integer productSource, Integer productAffiliation) {

		Long enterpriseId = super.getCurrentUser().getEnterpriseId();
		
		// 查询我的商品
		List<SupplierProductVo> lstData = supplierProductService.querySearchNoPage(enterpriseId, productNo, productName, 
				productategory, productStatus, productSource, productAffiliation);

		// 查询组中已存在的商品
		List<SupplierProductVo> lstReturnData = new ArrayList<SupplierProductVo>() ;
		List<SaleGroupProduct> lstGroupProduct = saleGroupProductService.getIsExistProduct(saleGroupId) ;
		Map<Long, Long> mapGroupProduct = null ;
		if(!BaseUtil.isEmpty(lstGroupProduct)) {
			mapGroupProduct = new HashMap<Long, Long>(lstGroupProduct.size());
			for(SaleGroupProduct product : lstGroupProduct) {
				mapGroupProduct.put(product.getProductId(), product.getProductId()) ;
			}
			
			// 遍历设置组中商品是否已存在
			if(!BaseUtil.isEmpty(lstData)) {
				for(SupplierProductVo vo : lstData) {
					if(BaseUtil.isEmpty(mapGroupProduct.get(vo.getProductId()))) {
						lstReturnData.add(vo);
					}
				}
			}
		}
		
		// 处理成前端需要的Json对象
		String json = JSONObject.toJSON(lstReturnData).toString();
		return json ;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		SaleGroupProduct saleGroupProduct = saleGroupProductService.getById(id);
		Integer count = enterpriseProductService.getByProductId(saleGroupProduct.getProductId());
		if(count > 1){
			OperatorFailureVo msg = new OperatorFailureVo("商品已经分销! 禁止删除");
			return JSONObject.toJSONString(msg) ;
		}
		//分销组设置
		SaleGroup entity = saleGroupService.getById(saleGroupProduct.getGroupId());
		Integer num = entity.getProductNumber();
		num -= 1;
		entity.setProductNumber(num);
		entity.setUpdateId(super.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		//分销组 操作日志
		SaleGroupLog saleGroupLog = new SaleGroupLog();
		saleGroupLog.setOperateFlag(20);
		List<SaleGroupEnterpriseVo> list= saleGroupEnterpriseService.getEnterPriseList(saleGroupProduct.getGroupId(),saleGroupProduct.getCreateEnterpriseId());
		String ids = "";
		Iterator<SaleGroupEnterpriseVo> iterator = list.iterator();
		while (iterator.hasNext()) {
		ids = ids + iterator.next().getChildEnterpriseId() + ",";
		}
		saleGroupLog.setUserIds(ids);
		saleGroupLog.setProductIds(saleGroupProduct.getProductId()+"");
		saleGroupLog.setCreateId(super.getCurrentUser().getId());
		saleGroupLog.setCreateTime(new Date());
		//分销商 - 商品 关系
		saleGroupProductService.delete(id,super.getCurrentUser().getEnterpriseId(),entity,saleGroupLog);
		OperatorSuccessVo message = new OperatorSuccessVo("删除商品成功！");
		return JSONObject.toJSONString(message) ;
	}
}
