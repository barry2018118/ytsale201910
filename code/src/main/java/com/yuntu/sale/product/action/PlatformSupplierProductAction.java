package com.yuntu.sale.product.action;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.FileUploadAider;
import com.yuntu.sale.base.GenerateNo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.common.GrobalConstant;
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.AreaService;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.ProductCategory;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.po.SupplierProductVo;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.ProductCategoryService;
import com.yuntu.sale.product.service.SupplierProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 平台产品Action
 * @author snps
 * @date 2018年2月14日 下午4:23:08
 */
@Controller
@RequestMapping(value = "/product/platform/supplier")
public class PlatformSupplierProductAction extends BaseAction {

	@Resource
	private SupplierProductService supplierProductService;

	@Resource
	private ProductCategoryService productCategoryService;

	@Resource
	private EnterpriseProductService enterpriseProductService;

	@Resource
	private EnterprisePlatformInterfaceService enterprisePlatformInterfaceService;

	@Resource
	private AreaService areaService;

	@Resource
	private ScenicService scenicService;

	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		//商品类别
		List<ProductCategory> entity = productCategoryService.getAll();
		model.addAttribute("ctategory", entity) ;
		return "/product/platform/supplier/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("EnterpriseId", super.getCurrentUser().getEnterpriseId()) ;
		return "/product/platform/supplier/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request, String productNo,String productName,Integer productategory, Integer productStatus,Integer productSource,Integer productAffiliation) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<SupplierProductVo> pager = new Page<SupplierProductVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<SupplierProductVo> page = supplierProductService.querySearch(pager, null,productNo,productName, productategory,  productStatus, productSource, productAffiliation) ;

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
	public String card(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 商品
		SupplierProduct product = supplierProductService.getById(id) ;
		model.addAttribute("entity", product) ;
		//第三方接口
		List<EnterprisePlatformInterfacePo> list = enterprisePlatformInterfaceService.listOfAvailableGys(product.getEnterpriseId());
		model.addAttribute("thirdplatform", list) ;
		// 商品类别
		List<ProductCategory> entity = productCategoryService.getAll();
		model.addAttribute("ctategory", entity) ;
		//景区
		InfoScenicPo infoScenicPo = scenicService.getById(product.getScenicId());
		model.addAttribute("scenic", infoScenicPo) ;
		// 查询省信息
		AreaPo lstProvince = areaService.getById(infoScenicPo.getProvinceId());
		model.addAttribute("provinces", lstProvince) ;
		// 查询市信息
		AreaPo lstCitys = areaService.getById(infoScenicPo.getCityId());
		model.addAttribute("city", lstCitys) ;
		return "/product/platform/supplier/card" ;
	}


}
