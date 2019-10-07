package com.yuntu.sale.capital.dao;

import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 企业资金变动Dao
 * @author snps
 * @date 2018年3月8日 下午3:02:15
 */
public interface EnterpriseAccountLogDao {

    /**
     * 通过业务行为类型ActionType获取资金变动
     * @param actionType 业务行为类型Id
     * @return List<EnterpriseBankCardPo>
     */
    List<EnterpriseAccountLogPo> queryByActionType(@Param("enterpriseId")Long enterpriseId, @Param("actionType") Long actionType, @Param("capitalType") Integer capitalType, @Param("startTime")String startTime,@Param("endTime")String endTime);

    List<EnterpriseAccountLogPo> queryAllByEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("capitalType") Integer capitalType, @Param("startTime")String startTime,@Param("endTime")String endTime);

    List<EnterpriseAccountLogPo> queryAllByEnterpriseIdTrans(@Param("enterpriseId")Long enterpriseId, @Param("startTime")String startTime,@Param("endTime")String endTime);

    List<EnterpriseAccountLogPo> querySearch(@Param("childId") Long childId,@Param("parentId") Long parentId);

    /**
     * 通过id获取资金变动
     * @param id
     * @return List<EnterpriseBankCardPo>
     */
    EnterpriseAccountLogPo queryById(@Param("id") Long id);
    /**
     * 暂定查询全部
     */
    List<EnterpriseAccountLogPo> query();

    /**
     * 新增
     * @param entity
     */
    void insert(EnterpriseAccountLogPo entity);

    /**
     * 保存
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
    EnterpriseAccountLogPo queryOrder(@Param("childId") Long childId,@Param("parentId") Long parentId,@Param("type")Integer type,@Param("enterpriseOrdersId") Long enterpriseOrdersId);
}