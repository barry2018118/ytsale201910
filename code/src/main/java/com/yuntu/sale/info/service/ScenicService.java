package com.yuntu.sale.info.service;

import com.coolshow.util.Page;
import com.yuntu.sale.info.po.InfoScenicPo;

/**
 * @Description 景区信息Service接口 
 * @author snps
 * @date 2018年2月27日 上午9:49:41
 */
public interface ScenicService {

    /**
     * 通过景区类别（模糊）查询
     * @param pager 分页对象
     * @param name 景区名字
     * @return Page<InfoScenicPo>
     */
    Page<InfoScenicPo> getByName(Page<InfoScenicPo> pager, String name) ;

    /**
     * 通过Id查询
     * @return InfoScenicPo
     */
    InfoScenicPo getById(Long id) ;

    /**
     * 通过景点名字（唯一）查询	（使用：用于判断商品类别是否重复）
     * @param   name
     * @return InfoScenicPo
     * UniqueName 独特的名字
     */
    InfoScenicPo  getByUniqueName(String name) ;
    /**
     * 保存
     * @param entity
     */
    void save(InfoScenicPo entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(InfoScenicPo entity) ;

    /**
     * 删除
     * @param id
     */
    void delete(Long id) ;



}