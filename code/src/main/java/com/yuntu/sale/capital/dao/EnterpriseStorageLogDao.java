package com.yuntu.sale.capital.dao;

import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 企业预收款日志Dao接口  
 * @author snps
 * @date 2018年3月8日 下午2:23:59
 */
public interface EnterpriseStorageLogDao {
    /**
     * 通过id查询
     * @param id
     */
    EnterpriseStorageLogPo queryById(@Param("id") Long id);
    /**
     * 通过parentId查询
     * @param parentId
     */
    List<EnterpriseStorageLogPo> queryByParentId(@Param("parentId") Long parentId);
    /**
     * 通过childId查询
     * @param childId
     */
    List<EnterpriseStorageLogPo> queryByChildId(@Param("childId") Long childId);
    /**
     * 匹配查询
     * @param childId
     * @param parentId
     */
    List<EnterpriseStorageLogPo> querySearch(@Param("childId") Long childId,@Param("parentId") Long parentId);
    /**
     * 新增
     * @param entity
     */
    Long insert(EnterpriseStorageLogPo entity);
    /**
     * 新增
     * @param entity
     */
    Long save(EnterpriseStorageLogPo entity);
	
}