package com.yuntu.sale.ifconf.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;
import com.yuntu.sale.ifconf.po.PlatformInterfacePo;
import com.yuntu.sale.ifconf.service.EnterprisePlatformInterfaceService;
import com.yuntu.sale.ifconf.service.PlatformInterfaceService;
import com.yuntu.sale.manage.action.BaseAction;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoUser;
import com.yuntu.sale.user.service.InfoEnterpriseService;

/**
 * @Description 企业接口配置Action 
 * @author Jack.jiang
 * @date 2018年4月2日
 */
@Controller
@RequestMapping(value = {"/ifconf/setting/{typeId}"})
public class EnterprisePlatformInterfaceAction extends BaseAction{

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnterprisePlatformInterfaceService enterprisePlatformInterfaceService;
	
	@Autowired
	private PlatformInterfaceService platformInterfaceService;
	
	@Autowired
	private InfoEnterpriseService infoEnterpriseService;
	
	/**
	 * 进入主页面
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main( @PathVariable Integer typeId , Model model) {
		model.addAttribute("typeId", typeId);
		return "/ifconf/setting/main" ;
	}
	
	/**
	 * 进入列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PathVariable Integer typeId ,Model model) {
		model.addAttribute("typeId", typeId);
		return "/ifconf/setting/list" ;
	}
	
	/**
	 * 分页列表数据
	 */
	@RequestMapping(value = "/list/page", method = RequestMethod.GET)
	@ResponseBody
	public String listPage(HttpServletRequest request,
						@PathVariable Integer typeId,
						String name) {
		InfoUser user=super.getCurrentUser();
		// 处理页面参数
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		// 调用分页查询
		Page<EnterprisePlatformInterfacePo> pager =  new Page<EnterprisePlatformInterfacePo>() ;
		pager.setStart(pageNumber) ;
		pager.setSize(pageSize) ;
		
		Map<String,Object> mapParam=Maps.newHashMap();
		if(!StringUtils.isEmpty(name))
			mapParam.put("name_like", "%"+name+"%");
		mapParam.put("enterpriseId", user.getEnterpriseId());
		mapParam.put("typeId", typeId);
		mapParam.put("status_not", 1);
		Page<EnterprisePlatformInterfacePo> page = enterprisePlatformInterfaceService.listPage(pager, mapParam) ;
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
	public String toAdd(@PathVariable Integer typeId, Model model) {
		List<PlatformInterfacePo> interfaceList= platformInterfaceService.findByType(typeId);
		model.addAttribute("interfaceList", interfaceList);
		model.addAttribute("typeId", typeId);
		return "/ifconf/setting/add";
	}
	
	/**
	 * 新建
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(EnterprisePlatformInterfacePo paramPo) {
		if(paramPo.getInterfaceId()==null||paramPo.getInterfaceId()<=0) {
			return "paramErr";
		}
		//接口数据
		PlatformInterfacePo platformInterfacePo=platformInterfaceService.getById(paramPo.getInterfaceId());
		if(platformInterfacePo==null||platformInterfacePo.getStatus()!=5) {
			return "paramErr";
		}
		paramPo.setInterfaceEname(platformInterfacePo.getEname());
		paramPo.setInterfaceName(platformInterfacePo.getName());
		if(!StringUtils.isEmpty(paramPo.getConfig())) {
			//合并 固定参数与配置参数 JSON串
			paramPo.setConfig(this.mergeJson(platformInterfacePo.getData(),paramPo.getConfig()));
			LOGGER.info(" config => {}",paramPo.getConfig());
		}
		//用户数据
		InfoUser user=super.getCurrentUser();
		InfoEnterprise infoEnterprise=infoEnterpriseService.getById(user.getEnterpriseId());
		paramPo.setCompany(infoEnterprise.getName());
		paramPo.setEnterpriseId(user.getEnterpriseId());
		paramPo.setCreatedBy(user.getName());
		paramPo.setCreatedId(user.getId());
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		enterprisePlatformInterfaceService.save(paramPo);
		return "ok" ;
	}
	
	/**
	 * 进入编辑页
	 */
	@RequestMapping(value = "/{id}/toEdit", method = RequestMethod.GET)
	public String toEdit(@PathVariable Integer typeId, @PathVariable Integer id, Model model) {
		EnterprisePlatformInterfacePo enterprisePlatformInterfacePo=enterprisePlatformInterfaceService.getById(id);
		model.addAttribute("enterprisePlatformInterfacePo",enterprisePlatformInterfacePo);
		//基础接口配置
		PlatformInterfacePo platformInterfacePo=platformInterfaceService.getById(enterprisePlatformInterfacePo.getInterfaceId());
		if(platformInterfacePo!=null){
			model.addAttribute("resJson", platformInterfacePo.getInterfaceConfig());
		}
		model.addAttribute("typeId", typeId);
		return "/ifconf/setting/edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
	@ResponseBody
	public String edit(@PathVariable Integer id,EnterprisePlatformInterfacePo paramPo) {
		if(StringUtils.isEmpty(paramPo.getName())) {
			return "paramErr";
		}
		paramPo.setId(id);
		InfoUser user=super.getCurrentUser();
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		
		if(!StringUtils.isEmpty(paramPo.getConfig())) {
			PlatformInterfacePo platformInterfacePo=platformInterfaceService.getById(paramPo.getInterfaceId());
			if(platformInterfacePo==null) {
				return "paramErr";
			}
			//合并 固定参数与配置参数 JSON串
			if(!StringUtils.isEmpty(platformInterfacePo.getData())&&!StringUtils.isEmpty(paramPo.getConfig())) 
				paramPo.setConfig(this.mergeJson(platformInterfacePo.getData(),paramPo.getConfig()));
			LOGGER.info(" config => {}",paramPo.getConfig());
		}
		enterprisePlatformInterfaceService.update(paramPo);
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
		EnterprisePlatformInterfacePo paramPo=new EnterprisePlatformInterfacePo();
		paramPo.setId(id);
		paramPo.setStatus(status);
		InfoUser user=super.getCurrentUser();
		paramPo.setUpdatedBy(user.getName());
		paramPo.setUpdatedId(user.getId());
		enterprisePlatformInterfaceService.update(paramPo);
		return "ok";
	}
	
	/**
	 * 合并两个JSON串为一个
	 * @param str1
	 * @param str2
	 * @return
	 */
	private String mergeJson(String str1,String str2) {
		LOGGER.info("str1 => {}",str1);
		JSONObject dataJson1=JSONObject.parseObject(str1);
		LOGGER.info("str2 => {}",str2);
		JSONObject dataJson2=JSONObject.parseObject(str2);
		JSONObject newJson = new JSONObject();
		newJson.putAll(dataJson1);
		newJson.putAll(dataJson2);
		String resJson=newJson.toJSONString();
		LOGGER.info("Result Json => {}",resJson);
		return resJson;
	}
	
}
