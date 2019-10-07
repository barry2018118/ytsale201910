package com.yuntu.sale.capital.service;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyChasePo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.user.po.InfoEnterprisePo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 企业预收款Service接口 
 * @author snps
 * @date 2018年3月8日 下午2:18:23
 */
public interface EnterpriseStorageMoneyService {

    /**
     * 通过Id获取
     * @param id
     * @return RechargePo
     */
    EnterpriseStorageMoneyPo queryById(@Param("id") Long id);
    /**
     * 通过parentId查询
     * @param parentId
     * @return Page<EnterpriseStorageLogPo>
     */
    Page<EnterpriseStorageMoneyPo> queryByParentId(Page<EnterpriseStorageMoneyPo> pager, @Param("parentId") Long parentId);
    /**
     * 通过childId查询
     * @param childId
     * @return Page<EnterpriseStorageLogPo>
     */
    Page<EnterpriseStorageMoneyPo> queryByChildId(Page<EnterpriseStorageMoneyPo> pager,@Param("childId") Long childId);
    /**
     * 匹配查询
     * @param childId
     * @param parentId
     * @return EnterpriseStorageLogPo
     */
    EnterpriseStorageMoneyPo querySearch(@Param("childId") Long childId,@Param("parentId") Long parentId);

    /**
     * 保存
     * @param entity 预收款
     * @param newLog 预收款日志
     * @param enterpriseAccountLogPo 企业资金变动日志
     */
    void update(@Param("entity")EnterpriseStorageMoneyPo entity,@Param("newLog")EnterpriseStorageLogPo newLog,@Param("enterpriseAccountLogPo")EnterpriseAccountLogPo enterpriseAccountLogPo);

    /**
     * 新增
     * @param entity 预收款
     * @param newLog 预收款日志
     * @param enterpriseAccountLogPo 企业资金变动日志
     */
    void save(@Param("entity")EnterpriseStorageMoneyPo entity,@Param("newLog")EnterpriseStorageLogPo newLog,@Param("enterpriseAccountLogPo")EnterpriseAccountLogPo enterpriseAccountLogPo);

    /**
     * 资金管理 - 预收款 - 供应商列表
     * @param pager 分页
     * @param name 检索企业 名称
     * @param childId 下级企业id
     */
    Page<EnterpriseStorageMoneyChasePo> getMySupplier(@Param("pager")Page<EnterpriseStorageMoneyChasePo> pager, @Param("name")String name,@Param("childId") Long childId);

    /**
     * 资金管理 - 预存款 - 分销商列表
     * @param pager
     * @param parentId 上级Id
     * @param name 商户名称
     * @return List<InfoEnterprise>
     */
    Page<EnterpriseStorageMoneyChasePo> getMyDistributor(Page<EnterpriseStorageMoneyChasePo> pager, @Param("name") String name, @Param("parentId") Long parentId);
}