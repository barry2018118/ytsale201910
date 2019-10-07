package com.yuntu.sale.ifconf.dao;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.ifconf.po.PlatformInterfacePo;

/**
 * @Description 基础配置需求Dao
 * @author Jack.jiang
 * @date 2018年3月23日 
 */
public interface PlatformInterfaceDao  {
	
	/**
	 * 查询列表
	 * @param mapParam
	 * @return
	 */
	List<PlatformInterfacePo> listBy(Map<String,Object> mapParam);
	
	/**
     * 通过Id查询
     * @return PlatformInterfacePo
     */
	PlatformInterfacePo getById(Integer id);

    /**
     * 保存
     * @param entity
     */
    int insertDynamic(PlatformInterfacePo entity);

    /**
     * 修改
     * @param entity
     */
    int updateDynamic(PlatformInterfacePo entity);
    
}