package com.yuntu.sale.ifconf.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.Page;
import com.google.common.collect.Maps;
import com.yuntu.sale.base.vo.PageJsonVo;
import com.yuntu.sale.ifconf.po.PlatformInterfacePo;
import com.yuntu.sale.ifconf.service.PlatformInterfaceService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 接口基础配置Action 
 * @author Jack.jiang
 * @date 2018年3月23日
 */
@Controller
@RequestMapping(value = {"/ifconf/basic"})
public class PlatformInterfaceAction extends BaseAction{

	@Autowired
	private PlatformInterfaceService platformInterfaceService;
	
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		return "/ifconf/basic/main" ;
	}
	
	/**
	 * 进入列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "/ifconf/basic/list" ;
	}
	
	/**
	 * 分页列表数据
	 */
	@RequestMapping(value = "/list/page", method = RequestMethod.GET)
	@ResponseBody
	public String listPage(HttpServletRequest request, String name,Integer typeId) {
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<PlatformInterfacePo> pager =  new Page<PlatformInterfacePo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		
		Map<String,Object> mapParam=Maps.newHashMap();
		if(!StringUtils.isEmpty(name))
			mapParam.put("name_like", "%"+name+"%");
		if(typeId!=null&&typeId>0)
			mapParam.put("typeId", typeId);
		mapParam.put("status_not", 1);
		Page<PlatformInterfacePo> page = platformInterfaceService.listPage(pager, mapParam) ;
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
	public String toAdd( Model model) {
		return "/ifconf/basic/add";
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(PlatformInterfacePo paramPo) {
		if(StringUtils.isEmpty(paramPo.getName())||StringUtils.isEmpty(paramPo.getEname())) {
			return "paramErr";
		}
		
		InfoUser user=super.getCurrentUser();
		paramPo.setCreatedBy(user.getName());
		paramPo.setCreatedId(user.getId());
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		platformInterfaceService.save(paramPo);
		return "ok" ;
	}
	
	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit( @PathVariable Integer id, Model model) {
		PlatformInterfacePo platformInterfacePo=platformInterfaceService.getById(id);
		model.addAttribute("platformInterfacePo",platformInterfacePo);
		return "/ifconf/basic/edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Integer id,PlatformInterfacePo paramPo) {
		if(StringUtils.isEmpty(paramPo.getName())||StringUtils.isEmpty(paramPo.getEname())) {
			return "paramErr";
		}
		paramPo.setId(id);
		InfoUser user=super.getCurrentUser();
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		platformInterfaceService.update(paramPo);
		return "ok";
	}
	
	//数组状态
	 static final int [] statusArr= {5,2,1};
	
	/**
	 * 启用/禁用/删除 5/2/1
	 */
	@RequestMapping(value="/{id}/status", method=RequestMethod.POST)
	@ResponseBody
	public String editStatus(@PathVariable Integer id,Integer status) {
		if(status==null||!ArrayUtils.contains(statusArr, status)) {
			return "paramErr";
		}
		PlatformInterfacePo paramPo=new PlatformInterfacePo();
		paramPo.setId(id);
		paramPo.setStatus(status);
		InfoUser user=super.getCurrentUser();
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		platformInterfaceService.update(paramPo);
		return "ok";
	}
	
}
