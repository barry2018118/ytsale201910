
package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.EnterpriseOrdersRefund;
import com.yuntu.sale.orders.vo.EnterpriseOrdersRefundVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 退款子表-企业退款表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface EnterpriseOrdersRefundDao  {

     EnterpriseOrdersRefund get(@Param("id")Long id);

     List<EnterpriseOrdersRefund> findList(EnterpriseOrdersRefund enterpriseOrdersRefund) ;

     void save(EnterpriseOrdersRefund enterpriseOrdersRefund);

     void update(EnterpriseOrdersRefund enterpriseOrdersRefund);

     void delete(@Param("id")Long id);

    List<EnterpriseOrdersRefundVo> queryList(@Param("childId") Long childId,
                                             @Param("parentId") Long parentId,
                                             @Param("productNo")String productNo,
                                             @Param("productName")String productName,
                                             @Param("startTime")String startTime,
                                             @Param("endTime")String endTime,
                                             @Param("status")Integer status);

    EnterpriseOrdersRefund queryOne(@Param("id")Long id, @Param("childId")Long childId, @Param("parentId")Long parentId);

    List<EnterpriseOrdersRefund> queryOrder(@Param("id")Long id, @Param("childId")Long childId, @Param("parentId")Long parentId);
}