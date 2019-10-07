package com.yuntu.sale.ifconf.dao;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;

/**
 * @Description 用户接口配置Dao
 * @author Jack.jiang
 * @date 2018年3月23日 
 */
public interface EnterprisePlatformInterfaceDao {
	
	/**
	 * 查询列表
	 * @param mapParam
	 * @return
	 */
	List<EnterprisePlatformInterfacePo> listBy(Map<String,Object> mapParam);
	
	/**
     * 通过Id查询
     * @return PlatformInterfacePo
     */
	EnterprisePlatformInterfacePo getById(Integer id);

    /**
     * 保存
     * @param entity
     */
    int insertDynamic(EnterprisePlatformInterfacePo entity);

    /**
     * 修改
     * @param entity
     */
    int updateDynamic(EnterprisePlatformInterfacePo entity);

    
}