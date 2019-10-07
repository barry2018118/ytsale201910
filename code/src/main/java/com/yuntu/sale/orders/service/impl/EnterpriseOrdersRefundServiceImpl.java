
package com.yuntu.sale.orders.service.impl;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.dao.EnterpriseAccountLogDao;
import com.yuntu.sale.capital.dao.EnterpriseCapitalDao;
import com.yuntu.sale.capital.dao.EnterpriseStorageLogDao;
import com.yuntu.sale.capital.dao.EnterpriseStorageMoneyDao;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.EnterpriseStorageLogPo;
import com.yuntu.sale.capital.po.EnterpriseStorageMoneyPo;
import com.yuntu.sale.orders.dao.*;
import com.yuntu.sale.orders.po.*;
import com.yuntu.sale.orders.service.EnterpriseOrdersRefundService;
import com.yuntu.sale.product.dao.SupplierProductDao;
import com.yuntu.sale.product.po.SupplierProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 退款子表-企业退款表Service
 * @author zy
 * @version 2018-04-23
 */

@Service("enterpriseOrdersRefundService")
public class EnterpriseOrdersRefundServiceImpl implements EnterpriseOrdersRefundService {

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

	public EnterpriseOrdersRefund get(Long id) {
		return enterpriseOrdersRefundDao.get(id);
	}

	public List<EnterpriseOrdersRefund> findList(EnterpriseOrdersRefund enterpriseOrdersRefund) {
		return enterpriseOrdersRefundDao.findList(enterpriseOrdersRefund);
	}

	public void delete(Long id) {
		enterpriseOrdersRefundDao.delete(id);
	}

	@Override
	public EnterpriseOrdersRefund getOne(Long id, Long childId, Long parentId) {
		return enterpriseOrdersRefundDao.queryOne(id,childId,parentId);
	}
	@Override
	public List<EnterpriseOrdersRefund> getorder(Long id, Long childId, Long parentId) {
		return enterpriseOrdersRefundDao.queryOrder(id,childId,parentId);
	}
	/**
	 * status ,1
	 *        ordersRefund
	 *        orders
	 *        Listcode
	 *        enterpriseOrdersList
	 *        mapList
	 *             map
	 *               type ,1
	 *                   enterpriseStorageMoney,enterpriseStorageMoneyOrder
	 *                   enterpriseStorageMoneylog,enterpriseStorageLogOrder
	 *               type ,2
	 *                   childCapital,enterpriseCapitalChild
	 *                   enterpriseCapitalPrent,enterpriseCapitalPrent
	 *                   enterpriseAccountLogOrderParent,enterpriseAccountLogOrder
	 *               childCapitalLog,enterpriseAccountLogOrder
	 *               product
	 * status ,2
	 *        ordersRefund
	 *        orders
	 *        Listcode
	 *        enterpriseOrdersList
	 *        mapList
	 *            map
	 *               type ,1
	 *               type ,2
	 *                   parentCapital,enterpriseCapitalPrent
	 * product
	 */
	@Override
	public void save(Long status, OrdersRefund ordersRefund, Orders orders, List<Code> listcode, List<EnterpriseOrders> enterpriseOrdersList, List<Map> mapList, SupplierProduct product) {
		BigDecimal before = new BigDecimal(0);              //变动前资金
		BigDecimal price = new BigDecimal(0);               //变动
		BigDecimal after = new BigDecimal(0);               //变动后

		BigDecimal usable = new BigDecimal(0);               // 平台可提现金额

		System.out.println("退款审核开始======");
		//退款主表
		ordersRefundDao.update(ordersRefund);
		System.out.println("退款主表======");
		//订单主表
		ordersDao.update(orders);
		System.out.println("订单主表======");
		//码集合
		Iterator<Code> code = listcode.iterator();
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
		//审核通过
		if(status == 1){
			System.out.println("执行审核通过======");
			Iterator<Map> itmap = mapList.iterator();
			while (itmap.hasNext()){
				Map<String,Object> map = itmap.next();
				int type =(Integer) map.get("type");
				if(type == 1){
						EnterpriseStorageMoneyPo enterpriseStorageMoneyOrder  = (EnterpriseStorageMoneyPo)map.get("enterpriseStorageMoney");
						enterpriseStorageMoneyDao.save(enterpriseStorageMoneyOrder);
						System.out.println("预收款表收入加======");
						EnterpriseStorageLogPo enterpriseStorageLogOrder= (EnterpriseStorageLogPo)map.get("enterpriseStorageMoneylog");
						enterpriseStorageLogDao.insert(enterpriseStorageLogOrder);
						System.out.println("预收款日志表======");
						EnterpriseAccountLogPo enterpriseAccountLog = (EnterpriseAccountLogPo)map.get("childCapitalLog");
						enterpriseAccountLogDao.insert(enterpriseAccountLog);
						System.out.println("资金日志表======");
				}
				if(type == 2){

					EnterpriseAccountLogPo enterpriseAccountLog = (EnterpriseAccountLogPo)map.get("childCapitalLog");

					EnterpriseCapitalPo enterpriseCapitalPo= enterpriseCapitalDao.queryByEnterpriseId(enterpriseAccountLog.getChildId());

					before = enterpriseCapitalPo.getTotalMoney();
					price = enterpriseAccountLog.getPrice();
					enterpriseCapitalPo.setTotalMoney(before.add(price));
					after = enterpriseCapitalPo.getTotalMoney();
					usable = enterpriseCapitalPo.getUsableMoney().add(price);

					EnterpriseCapitalPo enterpriseCapitalChild = (EnterpriseCapitalPo)map.get("childCapital");
					enterpriseCapitalChild.setTotalMoney(after);
					enterpriseCapitalChild.setUsableMoney(usable);
					System.out.println("平台资金收入加======");

					enterpriseAccountLog.setOldPrice(before);
					enterpriseAccountLog.setCurrentPrice(after);
					enterpriseAccountLogDao.insert(enterpriseAccountLog);
					System.out.println("资金日志表======");

					EnterpriseCapitalPo enterpriseCapitalPrent = (EnterpriseCapitalPo)map.get("enterpriseCapitalPrent");
					enterpriseCapitalDao.update(enterpriseCapitalPrent);
					System.out.println("上级平台资金主表======");
					EnterpriseAccountLogPo enterpriseAccountLogOrder = (EnterpriseAccountLogPo)map.get("enterpriseAccountLogOrderParent");
					enterpriseAccountLogDao.insert(enterpriseAccountLogOrder);
					System.out.println("上级资金日志表======");
				}
			}

		}
		//审核不通过
		if(status == 2){
			System.out.println("执行审核不通过======");
			Iterator<Map> itmap = mapList.iterator();
			while (itmap.hasNext()) {
				Map<String, Object> map = itmap.next();
				int action = (Integer) map.get("type");
				if(action == 2){
					EnterpriseCapitalPo enterpriseCapitalPrent = (EnterpriseCapitalPo)map.get("parentCapital");
					enterpriseCapitalDao.update(enterpriseCapitalPrent);
					System.out.println("上级平台资金解触冻结======");
				}
			}
			//商品
			if(product.getStoreMode() == 2 ){
				supplierProductDao.update(product);
				System.out.println("商品库存======");
			}
		}
		System.out.println("退款审核结束======");
	}
}