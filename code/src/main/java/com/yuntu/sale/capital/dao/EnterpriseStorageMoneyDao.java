package com.yuntu.sale.capital.dao;

import com.yuntu.sale.capital.po.EnterpriseStorageMoneyChasePo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 企业预收款Dao接口 
 * @author snps
 * @date 2018年3月8日 下午2:14:45
 */
public interface EnterpriseStorageMoneyDao {

    /**
     * 通过Id获取
     * @param id
     * @return RechargePo
     */
    EnterpriseStorageMoneyPo queryById(@Param("id") Long id);
    /**
     * 通过parentId查询
     * @param parentId
     */
    List<EnterpriseStorageMoneyPo> queryByParentId(@Param("parentId") Long parentId);
    /**
     * 通过childId查询
     * @param childId
     */
    List<EnterpriseStorageMoneyPo> queryByChildId(@Param("childId") Long childId);
    /**
     * 匹配查询
     * @param childId
     * @param parentId
     */
    EnterpriseStorageMoneyPo querySearch(@Param("childId") Long childId,@Param("parentId") Long parentId);

    /**
     * 新增
     * @param entity
     */
    Long insert(EnterpriseStorageMoneyPo entity);

    /**
     * 保存
     * @param entity
     */
    Long save(EnterpriseStorageMoneyPo entity);

    /**
     * 资金管理 - 预收款 - 供应商列表
     * @param name 检索企业 名称
     * @param childId 下级企业id
     */
    List<EnterpriseStorageMoneyChasePo> queryMySupplier(@Param("name") String name,@Param("childId") Long childId);

    /**
     * 资金管理 - 预存款 - 分销商列表
     * @param parentId 上级Id
     * @param name 商户名称
     * @return List<InfoEnterprise>
     */
    List<EnterpriseStorageMoneyChasePo> queryMyDistributor(@Param("name") String name, @Param("parentId") Long parentId);
}