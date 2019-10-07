package com.yuntu.sale.ifconf.service;

import java.util.List;
import java.util.Map;

import com.coolshow.util.Page;
import com.yuntu.sale.ifconf.po.PlatformInterfacePo;

/**
 * @Description 接口基础配置Service接口 
 * @author Jack.jiang
 * @date 2018年3月23日 
 */
public interface PlatformInterfaceService {

    /**
     * 分页查询
     * @param pager 分页对象
     * @param param 查询参数
     * @return Page<PlatformInterfacePo>
     */
    Page<PlatformInterfacePo> listPage(Page<PlatformInterfacePo> pager, Map<String,Object> param) ;
    
    /**
     * 查询列表
     * @param param
     * @return
     */
    List<PlatformInterfacePo> findByType(Integer typeId);

    /**
     * 通过Id查询
     * @return InfoScenicPo
     */
    PlatformInterfacePo getById(Integer id) ;

   
    /**
     * 保存
     * @param entity
     */
    void save(PlatformInterfacePo entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(PlatformInterfacePo entity) ;

}