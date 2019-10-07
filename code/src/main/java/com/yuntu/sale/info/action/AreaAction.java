package com.yuntu.sale.info.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuntu.sale.base.vo.OperatorDataVo;
import com.yuntu.sale.info.po.AreaPo;
import com.yuntu.sale.info.service.AreaService;
import com.yuntu.sale.manage.action.BaseAction;

/**
 * @Description 区域（省、市）信息Action
 * @author snps
 * @date 2018年2月27日 上午9:21:08
 */
@Controller
@RequestMapping(value = {"/info/area"})
public class AreaAction extends BaseAction {

	@Resource
	private AreaService areaService;
	
	
	/**
	 * 得到省信息
	 */
	@RequestMapping(value = "/province", method = RequestMethod.GET)
	@ResponseBody
	public String getProvince(HttpServletRequest request) {
		// 查询省信息
		List<AreaPo> lstProvince = areaService.getProvince();
		
		// 返回省信息 
		OperatorDataVo jsonVo = new OperatorDataVo(Boolean.TRUE);
		jsonVo.setMessage(lstProvince);
		return JSONObject.toJSON(jsonVo).toString();
	}
	
	/**
	 * 得到省下的城市信息
	 */
	@RequestMapping(value="/province/{provinceId}/city", method=RequestMethod.GET)
	@ResponseBody
	public String getCityByProvince(HttpServletRequest request, @PathVariable Long provinceId) {
		// 查询省下的城市信息
		List<AreaPo> lstCity = areaService.getCityByProvinceId(provinceId);
		
		// 返回城市信息 
		OperatorDataVo jsonVo = new OperatorDataVo(Boolean.TRUE);
		jsonVo.setMessage(lstCity);
		return JSONObject.toJSON(jsonVo).toString();
	}
	
}