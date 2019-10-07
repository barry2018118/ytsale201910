package com.yuntu.sale.product.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.yuntu.sale.base.FileUploadAider;
import com.yuntu.sale.base.GenerateNo;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.common.GrobalConstant;
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

/**
 * @Description 供应商产品Action
 * @author snps
 * @date 2018年2月14日 下午4:23:08
 */
@Controller
@RequestMapping(value = "/product/supplier")
public class SupplierProductAction extends BaseAction {

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
		return "/product/supplier/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("EnterpriseId", super.getCurrentUser().getEnterpriseId()) ;
		return "/product/supplier/list" ;
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
		Page<SupplierProductVo> page = supplierProductService.querySearch(pager, super.getCurrentUser().getEnterpriseId(),productNo,productName, productategory,  productStatus, productSource, productAffiliation) ;

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
		//商品类别
		List<ProductCategory> entity = productCategoryService.getAll();
		model.addAttribute("ctategory", entity) ;
		//第三方接口
		List<EnterprisePlatformInterfacePo> list = enterprisePlatformInterfaceService.listOfAvailableGys(super.getCurrentUser().getEnterpriseId());
		model.addAttribute("thirdplatform", list) ;
		// 查询省信息
		List<AreaPo> lstProvince = areaService.getProvince();
		model.addAttribute("provinces", lstProvince) ;
		return "/product/supplier/add" ;
	}

	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, SupplierProduct entity,@RequestParam(value = "file", required = false) MultipartFile file)  throws IOException {
		//图片上传
		Map<String, String> path = new HashMap<String, String>();
		//path.put("windows","/product/pic/");
		path.put("linux","project.product.path");
		Map<String, String> filename = FileUploadAider.uploadPic(request,super.getCurrentUser().getEnterpriseId(),path,file);
		if(filename.containsKey("fail")){
			OperatorFailureVo msg = new OperatorFailureVo(filename.get("fail"));
			return JSONObject.toJSONString(msg) ;
		}
		//如果选择第三方接口，需要录入第三方商品编号
		if(!BaseUtil.isEmpty(entity.getThirdPlatformId()) && BaseUtil.isEmpty(entity.getThirdPlatformNo())){
			OperatorFailureVo message = new OperatorFailureVo("请录入第三方产品编号！") ;
			return JSONObject.toJSONString(message) ;
		}
		// 通过商品类别名称进行查询
		SupplierProduct existEntity = supplierProductService.getByName(entity.getName());
		// 如果已存在同名的商品类别，返回提示信息
		if(!BaseUtil.isEmpty(existEntity)) {
			OperatorFailureVo message = new OperatorFailureVo("此商品已存在！") ;
			return JSONObject.toJSONString(message) ;
		} else {	// 如果不存在同名的商品类别，执行保存操作
			entity.setNo(GenerateNo.getProductNo(entity.getCategoryId().toString()));
			entity.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
			//entity.setPic(filename.get("filename"));
			entity.setPic(filename.get("uploadPath"));
			entity.setCreateId(super.getCurrentUser().getId());
			entity.setCreateTime(new Date());
			entity.setUpdateId(super.getCurrentUser().getId());
			entity.setUpdateTime(new Date());
			entity.setProductInfo(BaseUtil.toHTMLString(entity.getProductInfo()));

			//企业-商品 关系
			EnterpriseProduct enterpriseProduct = new EnterpriseProduct();
			enterpriseProduct.setEnterpriseId(super.getCurrentUser().getEnterpriseId());
			enterpriseProduct.setChildId(super.getCurrentUser().getEnterpriseId());
			enterpriseProduct.setIsSupplier(GrobalConstant.I_ENTERPRISE_PRODUCT_IS_SUPPLIER_YES);
			enterpriseProduct.setUserTracks(super.getCurrentUser().getEnterpriseId()+"");
			enterpriseProduct.setCreateId(super.getCurrentUser().getId());
			enterpriseProduct.setCreateTime(new Date());
			enterpriseProduct.setUpdateId(super.getCurrentUser().getId());
			enterpriseProduct.setUpdateTime(new Date());
			enterpriseProduct.setIsDistribution(1);

			supplierProductService.save(entity,enterpriseProduct);
			OperatorSuccessVo message = new OperatorSuccessVo("新建商品成功！") ;
			return JSONObject.toJSONString(message) ;
		}
	}

	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		// 商品
		SupplierProduct product = supplierProductService.getById(id) ;
		product.setProductInfo(this.transHTMLtoString(product.getProductInfo()));
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
		return "/product/supplier/edit" ;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, SupplierProduct entity,
					   @RequestParam(value = "file", required = false) MultipartFile file)  throws IOException {

		//如果选择第三方接口，需要录入第三方商品编号
		if(!BaseUtil.isEmpty(entity.getThirdPlatformId()) && BaseUtil.isEmpty(entity.getThirdPlatformNo())){
			OperatorFailureVo message = new OperatorFailureVo("请录入第三方产品编号！") ;
			return JSONObject.toJSONString(message) ;
		}

		// 通过商品名称进行查询
		SupplierProduct exist = supplierProductService.getByName(entity.getName());
		// 如果不存在同名的商品，执行修改操作
		if(BaseUtil.isEmpty(exist) || exist.getId().equals(entity.getId())) {
			SupplierProduct existEntity = supplierProductService.getById(entity.getId());
			existEntity.setName(entity.getName());
			existEntity.setCategoryId(entity.getCategoryId());
			existEntity.setMarketPrice(entity.getMarketPrice());
			existEntity.setLimitPrice(entity.getLimitPrice());
			existEntity.setScenicId(entity.getScenicId());
			existEntity.setProvinceId(entity.getProvinceId());
			existEntity.setCityId(entity.getCityId());
			existEntity.setCostInside(entity.getCostInside());
			existEntity.setCostOutside(entity.getCostOutside());
			//图片上传
			if(file != null) {
				Map<String, String> path = new HashMap<String, String>();
				//path.put("windows", "/product/pic/");
				path.put("linux", "project.product.path");
				Map<String, String> filename = FileUploadAider.uploadPic(request, super.getCurrentUser().getEnterpriseId(), path, file);
				if (filename.containsKey("fail")) {
					OperatorFailureVo msg = new OperatorFailureVo(filename.get("fail"));
					return JSONObject.toJSONString(msg);
				}
				//existEntity.setPic(filename.get("filename"));
				existEntity.setPic(filename.get("uploadPath"));
			}
			existEntity.setBuyOption(entity.getBuyOption());
			existEntity.setBuyTimeHour(entity.getBuyTimeHour());
			existEntity.setBuyTimeMinute(entity.getBuyTimeMinute());
			existEntity.setBuyUseDay(entity.getBuyUseDay());
			existEntity.setBuyUseHour(entity.getBuyUseHour());
			existEntity.setBuyUseMinute(entity.getBuyUseMinute());
			existEntity.setBuyMinNumber(entity.getBuyMinNumber());
			existEntity.setBuyUseAfterHour(entity.getBuyUseAfterHour());
			existEntity.setBuyMaxNumber(entity.getBuyMaxNumber());
			existEntity.setPlayMode(entity.getPlayMode());
			//existEntity.setPlayDate(entity.getPlayDate());
			existEntity.setValidStartDate(entity.getValidStartDate());
			existEntity.setValidEndDate(entity.getValidEndDate());
			existEntity.setStoreMode(entity.getStoreMode());
			existEntity.setStoreNum(entity.getStoreNum());
			existEntity.setRefundMode(entity.getRefundMode());
			existEntity.setAuditMode(entity.getAuditMode());
			existEntity.setServiceMode(entity.getServiceMode());
			existEntity.setRefundTime(entity.getRefundTime());
			existEntity.setRefundAfterDay(entity.getRefundAfterDay());
			existEntity.setRefundAfterHour(entity.getRefundAfterHour());
			existEntity.setRefundAfterMinute(entity.getRefundAfterMinute());
		    existEntity.setServiceProductCost(entity.getServiceProductCost());
		    existEntity.setServiceOrderCost(entity.getServiceProductCost());
			existEntity.setServiceOrderCost(entity.getServiceOrderCost());
			//existEntity.setServiceTel(entity.getServiceTel());
			//existEntity.setMessageTemplate(entity.getMessageTemplate());
			existEntity.setThirdPlatformId(entity.getThirdPlatformId());
			existEntity.setThirdPlatformNo(entity.getThirdPlatformNo());
			existEntity.setIsRealname(entity.getIsRealname());
			existEntity.setProductInfo(BaseUtil.toHTMLString(entity.getProductInfo()));
			existEntity.setUpdateId(super.getCurrentUser().getId());
			existEntity.setUpdateTime(new Date());
			existEntity.setIsMustCard(BaseUtil.isEmpty(entity.getIsMustCard())?0:1);
			existEntity.setRefundMethod(entity.getRefundMethod());
			supplierProductService.update(existEntity);
			OperatorSuccessVo message = new OperatorSuccessVo("编辑商品成功！") ;
			return JSONObject.toJSONString(message) ;
		} else {	// 如果已存在同名的商品类别，返回提示信息
			OperatorFailureVo message = new OperatorFailureVo("此商品名称已存在！请重新输入!") ;
			return JSONObject.toJSONString(message) ;
		}
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
		return "/product/supplier/card" ;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		EnterpriseProduct enterpriseProduct =  enterpriseProductService.getById(id);
		// Integer num = enterpriseProductService.getByProductId(enterpriseProduct.getProductId());
		// 2018-06-09修改	by snps 
		Integer distributionNum = enterpriseProductService.getProductDistributionNum(enterpriseProduct.getProductId());
		if(distributionNum == 1){
			supplierProductService.delete(id);
			OperatorSuccessVo message = new OperatorSuccessVo("删除商品成功！") ;
			return JSONObject.toJSONString(message) ;
		}else{
			OperatorFailureVo msg = new OperatorFailureVo("该商品已分销,禁止删除!");
			return JSONObject.toJSONString(msg) ;
		}
	}

	/**
	 * 状态
	 */
	@RequestMapping(value = "/{id}/status", method = RequestMethod.POST)
	@ResponseBody
	public String status(HttpServletRequest request, @PathVariable Long id, Model model) {
		supplierProductService.status(supplierProductService.getById(id));
		OperatorSuccessVo message = new OperatorSuccessVo("商品状态成功！") ;
		return JSONObject.toJSONString(message) ;
	}

	/**
	 * 选择景区页
	 */
	@RequestMapping(value = "/toScenic", method = RequestMethod.GET)
	public String toScenic(HttpServletRequest request, Model model) {
		return "/product/supplier/scenic" ;
	}


	public String transHTMLtoString(String pstrContent) {
			if (pstrContent.contains("<br>")) {
				pstrContent = pstrContent.replaceAll("<br>","\n");
			}
			if (pstrContent.contains("&#039;")) {
				pstrContent = pstrContent.replaceAll("&#039;","'");
			}
			if (pstrContent.contains("&#034;")) {
				pstrContent = pstrContent.replaceAll("&#034;","\"");
			}
			if (pstrContent.contains("&lt;")) {
				pstrContent = pstrContent.replaceAll("&lt;","<");
			}
			if (pstrContent.contains("&gt;")) {
				pstrContent = pstrContent.replaceAll("&gt;",">");
			}
			if (pstrContent.contains("&amp;")) {
				pstrContent = pstrContent.replaceAll("&amp;","&");
			}
			if (pstrContent.contains("&nbsp;")) {
				pstrContent = pstrContent.replaceAll("&nbsp;"," ");
			}
		return pstrContent;
	}

}
