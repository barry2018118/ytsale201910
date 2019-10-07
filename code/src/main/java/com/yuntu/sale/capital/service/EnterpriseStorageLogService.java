package com.yuntu.sale.capital.service;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 企业预收款日志Service接口
 * @author snps
 * @date 2018年3月8日 下午2:18:23
 */
public interface EnterpriseStorageLogService {

    /**
     * 通过id查询
     * @param id
     */
    EnterpriseStorageLogPo queryById(@Param("id") Long id);
    /**
     * 通过parentId查询
     * @param parentId
     * @return Page<EnterpriseStorageLogPo>
     */
    Page<EnterpriseStorageLogPo> queryByParentId(Page<EnterpriseStorageLogPo> pager, @Param("parentId") Long parentId);
    /**
     * 通过childId查询
     * @param childId
     * @return Page<EnterpriseStorageLogPo>
     */
    Page<EnterpriseStorageLogPo> queryByChildId(Page<EnterpriseStorageLogPo> pager,@Param("childId") Long childId);
    /**
     * 匹配查询
     * @param childId
     * @param parentId
     * @return Page<EnterpriseStorageLogPo>
     */
    Page<EnterpriseStorageLogPo> querySearch(Page<EnterpriseStorageLogPo> pager,@Param("childId") Long childId,@Param("parentId") Long parentId);
    /**
     * 新增
     * @param entity
     */
    void save(EnterpriseStorageLogPo entity);
    /**
     * 新增
     * @param entity
     */
    void update(EnterpriseStorageLogPo entity);
}