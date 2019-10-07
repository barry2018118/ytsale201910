package com.yuntu.sale.info.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.po.InfoScenicPo;
import com.yuntu.sale.info.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorSuccessVo;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.info.service.ScenicService;
import com.yuntu.sale.manage.action.BaseAction;

/**
 * @Description 景区信息Action 
 * @author snps
 * @date 2018年2月27日 上午9:47:39
 */
@Controller
@RequestMapping(value = {"/info/scenic"})
public class ScenicAction extends BaseAction {

	@Resource
	private ScenicService scenicService;

	@Resource
	private AreaService areaService;
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "/info/scenic/main" ;
	}
	
	/**
	 * 进入列表页
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "/info/scenic/list" ;
	}
	
	/**
	 * 通过景区名称（模糊）查询
	 */
	@RequestMapping(value="/getByName", method=RequestMethod.GET)
	@ResponseBody
	public String getByName(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<InfoScenicPo> pager =  new Page<InfoScenicPo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<InfoScenicPo> page = scenicService.getByName(pager, name) ;
		//Page<ProductCategory> page = null ;
		
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
		return "/info/scenic/add";
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, InfoScenicPo entity) {
		scenicService.save(entity);
		OperatorSuccessVo message = new OperatorSuccessVo("新建景区成功！");
		return JSONObject.toJSONString(message);
	}

	
	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable Long id, Model model) {
		InfoScenicPo entity = scenicService.getById(id) ;
		model.addAttribute("entity", entity) ;

		// 获取城市信息
		AreaPo city = areaService.getById(entity.getCityId());
		model.addAttribute("city", city);

		return "/info/scenic/edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @PathVariable Long id,InfoScenicPo entity) {

		/*//获取景点的名字
		String name=entity.getName();
		//验证景区名字
		InfoScenicPo scenic= scenicService.getByUniqueName(name);*/

		//获取景区信息
		InfoScenicPo po=scenicService.getById(id);
		po.setName(entity.getName());//景点名字
		po.setAddress(entity.getAddress());//景点地址
		po.setCityId(entity.getCityId());//城市
		po.setLevel(entity.getLevel());//星级
		po.setProvinceId(entity.getProvinceId());//省
		po.setTel(entity.getTel());//电话

        scenicService.update(po);


		OperatorSuccessVo message = new OperatorSuccessVo("编辑景区信息成功！");
		return JSONObject.toJSONString(message);

	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable Long id, Model model) {
		scenicService.delete(id);
		OperatorSuccessVo message = new OperatorSuccessVo("删除景区成功！") ;
		return JSONObject.toJSONString(message) ;
	}

	/**
	 * 通过景区名称（模糊）查询 - 创建产品
	 */
	@RequestMapping(value="/getByLIst", method=RequestMethod.POST)
	@ResponseBody
	public String getByLIst(HttpServletRequest request, String name) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;

		// 调用分页查询
		Page<InfoScenicPo> pager =  new Page<InfoScenicPo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		Page<InfoScenicPo> page = scenicService.getByName(pager, name) ;
		//Page<ProductCategory> page = null ;

		// 处理成前端需要的Json对象
		PageJsonVo jsonData = new PageJsonVo() ;
		jsonData.setTotal(page.getTotal()) ;
		jsonData.setRows(page.getResult()) ;
		String json = JSONObject.toJSON(jsonData).toString() ;
		return json ;
	}
	
}