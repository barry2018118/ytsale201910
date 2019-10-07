package com.yuntu.sale.info.service;

import java.util.List;

import com.yuntu.sale.info.po.AreaPo;

/**
 * @Description 区域（省、市）信息Service接口
 * @author snps
 * @date 2018年2月27日 上午9:38:42
 */
public interface AreaService {

	/**
	 * 得到省信息
	 * @return List<AreaPo>
	 */
	List<AreaPo> getProvince();
	
	/**
	 * 通过省Id得到相关的城市
	 * @param id 
	 * @return List<AreaPo>
	 */
	List<AreaPo> getCityByProvinceId(Long id);
	
	/**
	 * 通过Id获取
	 * @param id
	 * @return AreaPo
	 */
	AreaPo getById(Long id) ;
	
}