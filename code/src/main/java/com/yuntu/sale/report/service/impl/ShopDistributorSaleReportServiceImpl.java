package com.yuntu.sale.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.report.dao.DistributorSaleReportDao;
import com.yuntu.sale.report.service.IDistributorSaleReportService;
import com.yuntu.sale.report.vo.DistributorSaleDataVo;
import com.yuntu.sale.report.vo.SimpleEnterpriseVo;

/**
 * @Description 商户-分销商销售报表Service接口
 * @author snps
 * @date 2018年4月27日 上午11:41:29
 */
@Service("shopDistributorSaleReportService")
public class ShopDistributorSaleReportServiceImpl implements IDistributorSaleReportService {

	@Resource
	private DistributorSaleReportDao dao;

	@Override
	public Page<DistributorSaleDataVo> getSaleData(Page<DistributorSaleDataVo> pager, 
			Long enterpriseId, String childName, String startDate, String endDate) {
		// Step_1：查询用户的分销商信息
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询商品数据
		List<SimpleEnterpriseVo> lstChildtData = dao.queryMyChild(enterpriseId, childName);
		List<DistributorSaleDataVo> lstData = null;
		// 遍历商品数据，查询销售、消费、退款数据
		if(!BaseUtil.isEmpty(lstChildtData)) {
			lstData = new ArrayList<DistributorSaleDataVo>();
			for(SimpleEnterpriseVo child : lstChildtData) {
				DistributorSaleDataVo vo = new DistributorSaleDataVo();
				vo.setChildId(child.getId());
				vo.setChildName(child.getName());
				
				// 查询销售数据
				DistributorSaleDataVo saleData = dao.queryOrderSaleData(enterpriseId, vo.getChildId(), startDate, endDate);
				vo.setOrdersNum(saleData.getOrdersNum());
				vo.setSaleNum(saleData.getSaleNum());
				vo.setSaleMoney(saleData.getSaleMoney());
				
				// 查询消费数据
				DistributorSaleDataVo consumeData = dao.queryOrderConsumeData(enterpriseId, vo.getChildId(), startDate, endDate);
				vo.setConsumeNum(consumeData.getConsumeNum());
				vo.setConsumeMoney(consumeData.getConsumeMoney());
				
				// 查询退款数据
				DistributorSaleDataVo refundData = dao.queryOrderRefundData(enterpriseId, vo.getChildId(), startDate, endDate);
				vo.setRefundNum(refundData.getRefundNum());
				vo.setRefundDeductMoney(refundData.getRefundDeductMoney());
				vo.setRefundMoney(refundData.getRefundMoney());
				
				lstData.add(vo);
			}
		}
		
		// 获取分页后所需信息
		PageInfo<DistributorSaleDataVo> pageInfo = new PageInfo<DistributorSaleDataVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
}