package com.yuntu.sale.info.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.info.po.AreaPo;

/**
 * @Description 区域（省、市）信息Dao接口 
 * @author snps
 * @date 2018年2月27日 上午9:40:24
 */
public interface AreaDao {

	/**
	 * 得到省信息
	 * @return List<AreaPo>
	 */
	List<AreaPo> queryProvince();
	
	/**
	 * 通过省Id得到相关的城市
	 * @param id 
	 * @return List<AreaPo>
	 */
	List<AreaPo> queryCityByProvinceId(@Param("id") Long id);
	
	/**
	 * 通过Id获取
	 * @param id
	 * @return AreaPo
	 */
	AreaPo queryById(Long id) ;
	
}