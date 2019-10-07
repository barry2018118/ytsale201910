package com.yuntu.sale.capital.service;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 企业资金变动Service接口 
 * @author snps
 * @date 2018年3月8日 下午3:08:41
 */
public interface EnterpriseAccountLogService {

    /**
     * 通过业务行为类型ActionType获取资金变动
     * @param actionType 业务行为类型Id
     * @return Page<EnterpriseAccountLogPo>
     */
    Page<EnterpriseAccountLogPo> queryByActionType(Page<EnterpriseAccountLogPo> pager, Long enterpriseId, Long actionType,String startTime,String endTime);

    /**
     * 通过 企业  id   获取资金变动
     * @param childId
     * @param parentId
     * @return Page<EnterpriseAccountLogPo>
     */
    Page<EnterpriseAccountLogPo> querySeach(Page<EnterpriseAccountLogPo> pager,@Param("childId") Long childId,@Param("parentId") Long parentId);

    /**
     * 通过id获取信息
     * @param id
     * @return List<EnterpriseBankCardPo>
     */
    EnterpriseAccountLogPo queryById(@Param("id") Long id);

    /**
     * ==暂定 查询全部
     * @param pager
     * @return List<EnterpriseBankCardPo>
     */
    Page<EnterpriseAccountLogPo> query(Page<EnterpriseAccountLogPo> pager);

    /**
     * 保存
     * @param entity
     */
    void update(EnterpriseAccountLogPo entity);

    /**
     * 新增
     * @param entity
     */
    void save(EnterpriseAccountLogPo entity);

    /**
     * 获取 企业子订单
     * @param childId
     * @param parentId
     * @param type 类型
     * @param enterpriseOrdersId 记录id
     * @return
     */
    EnterpriseAccountLogPo getOrder(@Param("childId") Long childId,@Param("parentId") Long parentId,@Param("type")Integer type,@Param("enterpriseOrdersId") Long enterpriseOrdersId);

}