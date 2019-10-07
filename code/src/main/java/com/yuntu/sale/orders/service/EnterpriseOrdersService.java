
package com.yuntu.sale.orders.service;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.vo.EnterpriseOrdersConsumeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表子-企业订单表Service
 * @author zy
 * @version 2018-04-23
 */

public interface EnterpriseOrdersService {

	EnterpriseOrders get(@Param("id") Long id);

	List<EnterpriseOrders> findList(@Param("enterpriseOrders")EnterpriseOrders enterpriseOrders);

	void save(@Param("enterpriseOrders")EnterpriseOrders enterpriseOrders);

	void update(@Param("enterpriseOrders")EnterpriseOrders enterpriseOrders);

	/**
	 * 查询子订单
	 * @param id  订单 id
	 * @param childId  发起订单 企业id
	 * @param parentId  上级企业id
	 * @return
	 */
    EnterpriseOrders getOne(@Param("id")Long id,@Param("childId") Long childId,@Param("parentId") Long parentId);

	/**
	 * 消费列表
	 * @param pager
	 * @param enterpriseId
	 * @param productNo
	 * @param productName
	 * @return
	 */
    Page<EnterpriseOrdersConsumeVo> getConsumeList(Page<EnterpriseOrdersConsumeVo> pager,
												   @Param("enterpriseId")Long enterpriseId,
												   @Param("productNo")String productNo,
												   @Param("productName")String productName);
}