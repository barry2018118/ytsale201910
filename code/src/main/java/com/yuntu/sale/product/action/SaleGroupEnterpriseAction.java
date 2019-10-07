package com.yuntu.sale.product.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SaleGroupEnterprise;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.po.SaleGroupProductVo;
import com.yuntu.sale.product.service.EnterpriseProductService;
import com.yuntu.sale.product.service.SaleGroupEnterpriseService;
import com.yuntu.sale.product.service.SaleGroupProductService;
import com.yuntu.sale.product.service.SaleGroupService;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;
import com.yuntu.sale.user.service.InfoUserService;

/**
 * 商品组 设置分销商
 * @author zy
 * @version 2018-04-02
 */

@Controller
@RequestMapping(value = "/product/salegroup/enterprise")
public class SaleGroupEnterpriseAction extends BaseAction {

	@Autowired
	private SaleGroupService saleGroupService;

	@Autowired
	private SaleGroupEnterpriseService saleGroupEnterpriseService;

	@Autowired
	private InfoEnterpriseService infoEnterpriseService;

	@Autowired
	private InfoUserService infoUserService;

	@Autowired
	private EnterpriseProductService enterpriseProductService;

	@Autowired
	private SaleGroupProductService saleGroupProductService;


	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/{id}/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("salegroupId", id) ;
		return "/product/salegroup/enterprise/main" ;
	}

	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, @PathVariable Long id, Model model) {
		model.addAttribute("salegroupId", id) ;
		return "/product/salegroup/enterprise/list" ;
	}

	/**
	 * 通过条件（模糊）查询
	 */
	@RequestMapping(value="/{id}/getSearch", method=RequestMethod.GET)
	@ResponseBody
	public String getSearch(HttpServletRequest request,@PathVariable Long id, String name) {

		SaleGroup enpity = saleGroupService.getById(id);
		InfoUser infoUser=infoUserService.getById(enpity.getCreateId());
		long createGroupId = infoUser.getEnterpriseId();

		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<SaleGroupEnterpriseVo> pager = new Page<SaleGroupEnterpriseVo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<SaleGroupEnterpriseVo> page = saleGroupEnterpriseService.getList(pager, name, id,createGroupId) ;//检索字段,分销组id,创建分销组id

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
		model.addAttribute("saleGroupId", id) ;
		return "/product/salegroup/enterprise/add" ;
	}

	/**
	 * 新建
	 */
	@RequestMapping(value="/{id}/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request,@PathVariable Long id,@RequestParam("box[]") String[] box) {
		//定义
		List<SaleGroupEnterprise> saleGroupEnterpriseList ; //分销商是否分配
		Long groupId = id;                                  //分销组id
		//分销组
		SaleGroup enpity = saleGroupService.getById(id);
		//创建分销组企业id
		InfoUser infoUser=infoUserService.getById(enpity.getCreateId());
		long createGroupId = infoUser.getEnterpriseId();
		if(box!=null) {
			//循环选中的分销商 id
			for (String boId : box) {
				saleGroupEnterpriseList = saleGroupEnterpriseService.getOne(null, createGroupId, Long.parseLong(boId));
				if (!BaseUtil.isEmpty(saleGroupEnterpriseList)) {
					//分销企业已经分配
					OperatorFailureVo msg = new OperatorFailureVo("分销商已分配,请重新选择!");
					return JSONObject.toJSONString(msg);
				}
				saleGroupEnterpriseList = null;
			}
		}
		saleGroupEnterpriseService.save(id,box,super.getCurrentUser().getId(),super.getCurrentUser().getEnterpriseId());
		OperatorSuccessVo message = new OperatorSuccessVo("分销组分配分销商成功！") ;
		return JSONObject.toJSONString(message) ;

	}
	/**
	 * 添加分销商（模糊）查询
	 */
	@RequestMapping(value="/addSearch", method=RequestMethod.GET)
	@ResponseBody
	public String addSearch(HttpServletRequest request, Long saleGroupId, String name) {
		// 查询我的分销商
		List<InfoEnterprise> lstData = infoEnterpriseService.getMyDistributorNoPage(name, super.getCurrentUser().getEnterpriseId());
		
		// 遍历分销商信息，查看企业是否已处于其他商品组
		List<InfoEnterprise> lstReturnData = new ArrayList<InfoEnterprise>() ;
		if(!BaseUtil.isEmpty(lstData)) {
			for(InfoEnterprise vo : lstData) {
				int flag = saleGroupEnterpriseService.getChildIsBePartGroup(vo.getId());
				if(flag == 0) {
					lstReturnData.add(vo);
				}
			}
		}
		
		// 处理成前端需要的Json对象
		String json = JSONObject.toJSON(lstReturnData).toString();
		return json;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		//分销组 - 分销商 - 关系
		SaleGroupEnterprise saleGroupEnterprise = saleGroupEnterpriseService.getById(id);
		Integer count = enterpriseProductService.getByParentId(saleGroupEnterprise.getChildEnterpriseId());
		if(count > 1){
			OperatorFailureVo msg = new OperatorFailureVo("该企业在分销商品! 禁止删除");
			return JSONObject.toJSONString(msg) ;
		}
        //分销组设置
		SaleGroup entity = saleGroupService.getById(saleGroupEnterpriseService.getById(id).getGroupId());
		Integer num = entity.getUserNumber();
		num -= 1;
		entity.setUserNumber(num);
		entity.setUpdateId(super.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		//分销组 操作日志
		SaleGroupLog saleGroupLog = new SaleGroupLog();
		saleGroupLog.setOperateFlag(10);

		List<SaleGroupProductVo> list= saleGroupProductService.getProductList(saleGroupEnterprise.getGroupId(),saleGroupEnterprise.getCreateEnterpriseId());
		String ids = "";
		Iterator<SaleGroupProductVo> iterator = list.iterator();
		while (iterator.hasNext()) {
			ids = ids + iterator.next().getProductId() + ",";
		}
		saleGroupLog.setUserIds(saleGroupEnterprise.getChildEnterpriseId()+"");
		saleGroupLog.setProductIds(ids);
		saleGroupLog.setCreateId(super.getCurrentUser().getId());
		saleGroupLog.setCreateTime(new Date());
		//分销商 - 商品 关系
		saleGroupEnterpriseService.delete(id,super.getCurrentUser().getEnterpriseId(),entity,saleGroupLog);
		OperatorSuccessVo message = new OperatorSuccessVo("删除分销组成功！");
		return JSONObject.toJSONString(message) ;
	}
	
}