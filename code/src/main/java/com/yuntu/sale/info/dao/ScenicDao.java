package com.yuntu.sale.info.dao;

import com.yuntu.sale.info.po.InfoScenicPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 景区信息Dao 
 * @author snps
 * @date 2018年2月27日 上午9:54:49
 */
public interface ScenicDao {

    /**
     * 通过商品类别（模糊）查询
     * @return List<InfoScenicPo>
     */

    List<InfoScenicPo> queryByName(@Param("name")String name);

    /**
     * 通过Id查询
     * @return InfoScenicPo
     */

    InfoScenicPo queryById(Long id);

    /**
     * 通过商品类别（唯一）查询	（使用：用于判断商品类别是否重复）
     * @param name
     * @return InfoScenicPo
     */

    InfoScenicPo queryByUniqueName(String name);

    /**
     * 保存
     * @param entity
     */

    int insert(InfoScenicPo entity);

    /**
     * 修改
     * @param entity
     */

    int update(InfoScenicPo entity);

    /**
     * 删除
     * @param id
     */

    void delete(Long id);
}