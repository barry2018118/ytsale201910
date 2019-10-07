package com.yuntu.sale.finance.service;

import com.coolshow.util.Page;
import com.yuntu.sale.user.po.InfoEnterprise;

/**
 * @Description 企业-财务Service接口
 * @author snps
 * @date 2018年5月25日 上午10:53:14
 */
public interface IEnterpriseFinanceService {
	
	/**
	 * 查看我的商户
	 * @param pager
	 * @param myId 我的企业Id
	 * @param companyType 商户类型
	 * @param name 商户名称
	 * @return Page<InfoEnterprise>
	 */
	Page<InfoEnterprise> getMyShop(Page<InfoEnterprise> pager, Long myId, Integer companyType, String name);

	/**
	 * 导出报表
	 * @param searchType 查询报表类型（1：供应商报表，2：分销商报表）
	 * @param childId 下级企业Id
	 * @param parentId 上级企业Id
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param searchMethod 导出方式（1：按销售、2：按消费）
	 * @return String 导出报表位置
	 */
	String getReport(Integer searchType, long childId, long parentId, String startDate, String endDate, Integer searchMethod) ;
	
}