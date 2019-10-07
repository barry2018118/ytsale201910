package com.yuntu.sale.ifconf.service;

import java.util.List;
import java.util.Map;

import com.coolshow.util.Page;
import com.yuntu.sale.ifconf.po.EnterprisePlatformInterfacePo;

/**
 * @Description 企业接口配置Service接口 
 * @author Jack.jiang
 * @date 2018年3月26日 
 */
public interface EnterprisePlatformInterfaceService {

    /**
     * 分页查询
     * @param pager 分页对象
     * @param param 查询参数
     * @return Page<PlatformInterfacePo>
     */
    Page<EnterprisePlatformInterfacePo> listPage(Page<EnterprisePlatformInterfacePo> pager, Map<String,Object> param) ;

    /**
     * 通过Id查询
     * @return EnterprisePlatformInterfacePo
     */
    EnterprisePlatformInterfacePo getById(Integer id) ;
    
    /**
     *  获取可用的供应商接口
     * @return List<EnterprisePlatformInterfacePo>
     */
    List<EnterprisePlatformInterfacePo> listOfAvailableGys(Long enterpriseId) ;

   
    /**
     * 保存
     * @param entity
     */
    void save(EnterprisePlatformInterfacePo entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(EnterprisePlatformInterfacePo entity) ;

}