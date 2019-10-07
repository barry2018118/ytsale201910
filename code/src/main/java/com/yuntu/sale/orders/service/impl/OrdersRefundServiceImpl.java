package com.yuntu.sale.orders.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.capital.dao.EnterpriseAccountLogDao;
import com.yuntu.sale.capital.dao.EnterpriseCapitalDao;
import com.yuntu.sale.capital.dao.EnterpriseStorageLogDao;
import com.yuntu.sale.capital.dao.EnterpriseStorageMoneyDao;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.orders.dao.CodeDao;
import com.yuntu.sale.orders.dao.EnterpriseOrdersDao;
import com.yuntu.sale.orders.dao.EnterpriseOrdersRefundDao;
import com.yuntu.sale.orders.dao.OrdersDao;
import com.yuntu.sale.orders.dao.OrdersRefundDao;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.po.EnterpriseOrdersRefund;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.po.OrdersRefund;
import com.yuntu.sale.orders.service.OrdersRefundService;
import com.yuntu.sale.orders.vo.EnterpriseOrdersRefundVo;
import com.yuntu.sale.orders.vo.OrdersRefundListVo;
import com.yuntu.sale.product.dao.SupplierProductDao;
import com.yuntu.sale.product.po.SupplierProduct;

/**
 * 退款主表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("ordersRefundService")
public class OrdersRefundServiceImpl implements OrdersRefundService {

	@Resource
    private OrdersRefundDao ordersRefundDao;

	@Resource
	private EnterpriseOrdersRefundDao enterpriseOrdersRefundDao;

	@Resource
	private OrdersDao ordersDao;

	@Resource
	private CodeDao codeDao;

	@Resource
	private EnterpriseOrdersDao enterpriseOrdersDao;

	@Resource
	private SupplierProductDao supplierProductDao;

	@Resource
	private EnterpriseCapitalDao enterpriseCapitalDao;

	@Resource
	private EnterpriseStorageMoneyDao enterpriseStorageMoneyDao;

	@Resource
	private EnterpriseStorageLogDao enterpriseStorageLogDao;

	@Resource
	private EnterpriseAccountLogDao enterpriseAccountLogDao;


	public OrdersRefund get(Long id) {
		return ordersRefundDao.get(id);
	}

	@Override
	public OrdersRefund getByOrderAndSerial(Long orderId, String serialNumber){
		return ordersRefundDao.getByOrderAndSerial(orderId, serialNumber);
	}
	
	public List<OrdersRefund> findList(OrdersRefund ordersRefund) {
		return ordersRefundDao.findList(ordersRefund);
	}

	@Override
	public void update(OrdersRefund ordersRefund) {
	}

	@Override
	public void delete(Long status, Long id) {
	}
	
	@Override
	public Page<OrdersRefundListVo> getList(Page<OrdersRefundListVo> pager, Long childId, String productNo, String productName, String startTime, String endTime, Integer status) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<OrdersRefundListVo> lstData = ordersRefundDao.queryList(childId,productNo,productName,startTime,endTime,status);

		// 获取分页后所需信息
		PageInfo<OrdersRefundListVo> pageInfo = new PageInfo<OrdersRefundListVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	@Override
	public Page<EnterpriseOrdersRefundVo> getOrdersList(Page<EnterpriseOrdersRefundVo> pager,  Long childId, Long parentId, String productNo, String productName, String startTime, String endTime, Integer status) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseOrdersRefundVo> lstData = enterpriseOrdersRefundDao.queryList(childId,parentId,productNo,productName,startTime,endTime,status);

		// 获取分页后所需信息
		PageInfo<EnterpriseOrdersRefundVo> pageInfo = new PageInfo<EnterpriseOrdersRefundVo>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}
	
	/**
	 * ordersRefund
	 * orders
	 * Listcode
	 * enterpriseOrdersList
	 * mapList
	 *         Map
	 *              enterpriseOrdersRefund,enterpriseOrdersRefund
	 *              不审核
	 *                    type,1
	 *                    预收款
	 *                           action, 1
	 *                           enterpriseStorageMoney,enterpriseStorageMoneyOrder
	 *                           enterpriseStorageMoneylog,enterpriseStorageLogOrder
	 *                    平台资金
	 *                            action, 2
	 *                            childCapital,enterpriseCapitalChild
	 *                             parentCapital,enterpriseCapitalPrent
	 *                              parentCapitalLog,enterpriseAccountLogOrder
	 *                     childCapitalLog,enterpriseAccountLogOrder
	 *                     商品 ^-\
	 *              审核
	 *                    type,2
	 *                    预收款
	 *                             action, 1
	 *                    平台资金
	 *                             action, 2
	 *                    parentCapital,enterpriseCapitalPrent
	 * product _/^
	 */
	@Override
	public void save(OrdersRefund ordersRefund, Orders orders, List<Code> codeList, List<EnterpriseOrders> enterpriseOrdersList, List<Map> mapList, SupplierProduct product) {
		BigDecimal before = new BigDecimal(0);              //变动前资金
		BigDecimal price = new BigDecimal(0);               //变动
		BigDecimal after = new BigDecimal(0);               //变动后

		BigDecimal usable = new BigDecimal(0);               // 平台可提现金额

		System.out.println("退款开始======");
		//退款主表
		ordersRefundDao.save(ordersRefund);
		System.out.println("退款主表======");
		//订单主表
		ordersDao.update(orders);
		System.out.println("订单主表======");
		//码集合
		Iterator<Code> code = codeList.iterator();
		while (code.hasNext()){
			codeDao.update(code.next());
			System.out.println("码集合表======");
		}
		//子订单集合
		Iterator<EnterpriseOrders> order = enterpriseOrdersList.iterator();
		while (order.hasNext()){
			enterpriseOrdersDao.update(order.next());
			System.out.println("子订单集合表======");
		}
		//子退款单
		Iterator<Map> itmap = mapList.iterator();
		while (itmap.hasNext()){
			Map<String,Object> map = itmap.next();
			EnterpriseOrdersRefund enterpriseOrdersRefund = (EnterpriseOrdersRefund) map.get("enterpriseOrdersRefund");
			enterpriseOrdersRefund.setRefundId(ordersRefund.getId());
			enterpriseOrdersRefundDao.save(enterpriseOrdersRefund);
			System.out.println("子退款单======");
			int type =(Integer) map.get("type");

			if(type == 1){
				int action =(Integer) map.get("action");
				if(action == 1){
					EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder  = (EnterpriseStorageMoneyPo)map.get("enterpriseStorageMoney");
					enterpriseStorageMoneyDao.save(enterpriseStorageMoneyOrder);
					System.out.println("预收款表======");
					EnterpriseStorageLogPo enterpriseStorageLogOrder= (EnterpriseStorageLogPo)map.get("enterpriseStorageMoneylog");
					enterpriseStorageLogDao.insert(enterpriseStorageLogOrder);
					System.out.println("预收款日志表======");
					EnterpriseAccountLogPo enterpriseAccountLogOrderchild = (EnterpriseAccountLogPo)map.get("childCapitalLog");
					enterpriseAccountLogOrderchild.setActionId(enterpriseOrdersRefund.getId());
					enterpriseAccountLogDao.insert(enterpriseAccountLogOrderchild);
					System.out.println("资金日志表======");
				}
				if(action == 2){
					EnterpriseAccountLogPo enterpriseAccountLogOrderchild = (EnterpriseAccountLogPo)map.get("childCapitalLog");

					EnterpriseCapitalPo enterpriseCapitalPo= enterpriseCapitalDao.queryByEnterpriseId(enterpriseAccountLogOrderchild.getChildId());

					before = enterpriseCapitalPo.getTotalMoney();
					price = enterpriseAccountLogOrderchild.getPrice();
					enterpriseCapitalPo.setTotalMoney(before.add(price));
					after = enterpriseCapitalPo.getTotalMoney();
					usable = enterpriseCapitalPo.getUsableMoney().add(price);

					EnterpriseCapitalPo enterpriseCapitalchild = (EnterpriseCapitalPo)map.get("childCapital");
					enterpriseCapitalchild.setTotalMoney(after);
					enterpriseCapitalchild.setUsableMoney(usable);
					enterpriseCapitalDao.update(enterpriseCapitalchild);
					System.out.println("平台资金表======");

					enterpriseAccountLogOrderchild.setOldPrice(before);
					enterpriseAccountLogOrderchild.setCurrentPrice(after);
					enterpriseAccountLogOrderchild.setActionId(enterpriseOrdersRefund.getId());
					enterpriseAccountLogDao.insert(enterpriseAccountLogOrderchild);
					System.out.println("资金日志表======");

					EnterpriseCapitalPo enterpriseCapitalparent = (EnterpriseCapitalPo)map.get("parentCapital");
					enterpriseCapitalDao.update(enterpriseCapitalparent);
					System.out.println("上级平台资金主表======");
					EnterpriseAccountLogPo enterpriseAccountLogOrderparent = (EnterpriseAccountLogPo)map.get("parentCapitalLog");
					enterpriseAccountLogOrderparent.setActionId(enterpriseOrdersRefund.getId());
					enterpriseAccountLogDao.insert(enterpriseAccountLogOrderparent);
					System.out.println("上级资金日志表======");
				}
			}
			if(type == 2){
				int action =(Integer) map.get("action");
				if(action == 2){
					EnterpriseCapitalPo enterpriseCapitalPrent = (EnterpriseCapitalPo)map.get("parentCapital");
					enterpriseCapitalDao.update(enterpriseCapitalPrent);
					System.out.println("审核上级平台资金冻结======");
				}

			}
		}
		//商品

			if (product.getStoreMode() == 2) {
				supplierProductDao.update(product);
				System.out.println("商品库存======");
			}

		System.out.println("退款结束======");
	}

}