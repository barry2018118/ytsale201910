
package com.yuntu.sale.orders.service.impl;

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
import com.yuntu.sale.orders.dao.CodeProvideDao;
import com.yuntu.sale.orders.dao.EnterpriseOrdersDao;
import com.yuntu.sale.orders.dao.OrdersDao;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.po.CodeProvide;
import com.yuntu.sale.orders.po.EnterpriseOrders;
import com.yuntu.sale.orders.po.Orders;
import com.yuntu.sale.orders.service.OrdersService;
import com.yuntu.sale.product.dao.SupplierProductDao;
import com.yuntu.sale.product.po.SupplierProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单主表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

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

	@Override
	public Orders get(Long id) {
		return ordersDao.get(id);
	}

	public Orders getByNo(String orderno){
		return ordersDao.getByNo(orderno);
	}

	@Override
	public List<Orders> findList(Orders orders) {
		return ordersDao.findList(orders);
	}

	@Override
	public void thirdSave(Orders orders, List<Code> codeList, List<EnterpriseOrders> listEnterpriseOrders, SupplierProduct product) {

		System.out.println("添加订单开始+++++++++++++");
		//订单
		ordersDao.add(orders);
		//System.out.println("order :" + orders.toString());
		//码信息表
		Iterator<Code> codeId = codeList.iterator();
		while (codeId.hasNext()) {
			Code code = codeId.next();
			code.setOrdersId(orders.getId());
			codeDao.save(code);
		//	System.out.println("code :" + code.toString());
		}

		//企业订单表
		Iterator<EnterpriseOrders> order = listEnterpriseOrders.iterator();
		while (order.hasNext()) {
			EnterpriseOrders enterpriseOrders = order.next();
			enterpriseOrders.setOrdersId(orders.getId());
			enterpriseOrdersDao.save(enterpriseOrders);
		//	System.out.println("ordersub :" + enterpriseOrders.toString());
		}
		//产品库存
		if(product.getStoreMode() == 2){
			supplierProductDao.update(product);
		}
		//System.out.println("product :" + product.toString());
		//System.out.println("添加订单结束+++++++++++++");
	}

	@Override
	public void thirdUpdate(Orders orders, List<Code> codeList, List<Map> listEnterpriseOrders ) {
		BigDecimal before;                                      //变动前资金
		BigDecimal now;                                         //变动资金
		BigDecimal after ;                                      //变动后资金

		System.out.println("添加订单开始+++++++++++++");
		//订单
		ordersDao.update(orders);
		System.out.println("order :" + orders.toString());
		//码信息表
		Iterator<Code> codeId = codeList.iterator();
		while (codeId.hasNext()) {
			Code code = codeId.next();
			codeDao.update(code);
			System.out.println("code :" + code.toString());
		}

		//企业订单表
		Iterator<Map> order = listEnterpriseOrders.iterator();
		while (order.hasNext()) {
			Map<String,Object> orderSub = order.next();
			int type = (Integer) orderSub.get("type");
			EnterpriseOrders enterpriseOrders = (EnterpriseOrders)orderSub.get("orders");
			enterpriseOrdersDao.update(enterpriseOrders);
			System.out.println("ordersub :" + enterpriseOrders.toString());
			//下级
			if(type == 1){
				enterpriseStorageMoneyDao.save((EnterpriseStorageMoneyPo) orderSub.get("money"));
				System.out.println("money : ============");
				enterpriseStorageLogDao.insert((EnterpriseStorageLogPo) orderSub.get("moneyLog"));
				System.out.println("moneyLog :: ============");
				//
				EnterpriseAccountLogPo eap = (EnterpriseAccountLogPo) orderSub.get("accountLog");
				eap.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				enterpriseAccountLogDao.insert(eap);
				System.out.println("capitallog :: ============");
			}
			if(type == 2){
				EnterpriseAccountLogPo eap = (EnterpriseAccountLogPo) orderSub.get("accountLog");

				EnterpriseCapitalPo enterpriseCapitalOrder = enterpriseCapitalDao.queryByEnterpriseId(eap.getChildId());

				before = enterpriseCapitalOrder.getTotalMoney();
				now = eap.getPrice();
				enterpriseCapitalOrder.setTotalMoney(before.subtract(now));
				after = enterpriseCapitalOrder.getTotalMoney();
				BigDecimal usable = enterpriseCapitalOrder.getUsableMoney().subtract(now);
				enterpriseCapitalOrder.setUsableMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);

				EnterpriseCapitalPo childMoney = (EnterpriseCapitalPo) orderSub.get("capital");
				childMoney.setTotalMoney(enterpriseCapitalOrder.getTotalMoney());
				childMoney.setUsableMoney(enterpriseCapitalOrder.getUsableMoney());
				enterpriseCapitalDao.update(childMoney);
				System.out.println("capital :: ============");

				eap.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				eap.setOldPrice(before);
				eap.setPrice(now.multiply(new BigDecimal(-1)));
				eap.setCurrentPrice(after);
				enterpriseAccountLogDao.insert(eap);
				System.out.println("capitallog :: ============");
				//上级
				enterpriseCapitalDao.update((EnterpriseCapitalPo) orderSub.get("parentcapital"));
				System.out.println("parentcapital :: ============");
				EnterpriseAccountLogPo eap1 = (EnterpriseAccountLogPo) orderSub.get("parentcapitallog");
				eap1.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				enterpriseAccountLogDao.insert(eap1);
				System.out.println("parentlog :: ============");
			}
		}
	}

	@Override
	public void save(Orders orders, List<Code> codeList, List<Map> listEnterpriseOrders, SupplierProduct product) {
		BigDecimal before;                                      //变动前资金
		BigDecimal now;                                         //变动资金
		BigDecimal after ;                                      //变动后资金

		System.out.println("添加订单开始+++++++++++++");
		//订单
		ordersDao.add(orders);
		System.out.println("order :" + orders.toString());
		//码信息表
		Iterator<Code> codeId = codeList.iterator();
		while (codeId.hasNext()) {
			Code code = codeId.next();
			code.setOrdersId(orders.getId());
			codeDao.save(code);
			System.out.println("code :" + code.toString());
		}

		//企业订单表
		Iterator<Map> order = listEnterpriseOrders.iterator();
		while (order.hasNext()) {
			Map<String,Object> orderSub = order.next();
			int type = (Integer) orderSub.get("type");
			EnterpriseOrders enterpriseOrders = (EnterpriseOrders)orderSub.get("orders");
			enterpriseOrders.setOrdersId(orders.getId());
			enterpriseOrdersDao.save(enterpriseOrders);
			System.out.println("ordersub :" + enterpriseOrders.toString());
			//下级
			if(type == 1){
				enterpriseStorageMoneyDao.save((EnterpriseStorageMoneyPo) orderSub.get("money"));
				System.out.println("money : ============");
				enterpriseStorageLogDao.insert((EnterpriseStorageLogPo) orderSub.get("moneyLog"));
				System.out.println("moneyLog :: ============");
				//
				EnterpriseAccountLogPo eap = (EnterpriseAccountLogPo) orderSub.get("accountLog");
				eap.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				enterpriseAccountLogDao.insert(eap);
				System.out.println("capitallog :: ============");
			}
			if(type == 2){
				EnterpriseAccountLogPo eap = (EnterpriseAccountLogPo) orderSub.get("accountLog");

				EnterpriseCapitalPo enterpriseCapitalOrder = enterpriseCapitalDao.queryByEnterpriseId(eap.getChildId());

				before = enterpriseCapitalOrder.getTotalMoney();
				now = eap.getPrice();
				enterpriseCapitalOrder.setTotalMoney(before.subtract(now));
				after = enterpriseCapitalOrder.getTotalMoney();
				BigDecimal usable = enterpriseCapitalOrder.getUsableMoney().subtract(now);
				enterpriseCapitalOrder.setUsableMoney(usable.compareTo(new BigDecimal(0)) == -1 ? new BigDecimal(0) : usable);

				EnterpriseCapitalPo childMoney = (EnterpriseCapitalPo) orderSub.get("capital");
				childMoney.setTotalMoney(enterpriseCapitalOrder.getTotalMoney());
				childMoney.setUsableMoney(enterpriseCapitalOrder.getUsableMoney());
				enterpriseCapitalDao.update(childMoney);
				System.out.println("capital :: ============");

				eap.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				eap.setOldPrice(before);
				eap.setPrice(now.multiply(new BigDecimal(-1)));
				eap.setCurrentPrice(after);
				enterpriseAccountLogDao.insert(eap);
				System.out.println("capitallog :: ============");
				//上级
				enterpriseCapitalDao.update((EnterpriseCapitalPo) orderSub.get("parentcapital"));
				System.out.println("parentcapital :: ============");
				EnterpriseAccountLogPo eap1 = (EnterpriseAccountLogPo) orderSub.get("parentcapitallog");
				eap1.setActionId(enterpriseOrders.getId());//根据企业--订单表下
				enterpriseAccountLogDao.insert(eap1);
				System.out.println("parentlog :: ============");
			}
		}
		//产品库存
		if(product.getStoreMode() == 2){
			supplierProductDao.update(product);
		}
		System.out.println("product :" + product.toString());
		System.out.println("添加订单结束+++++++++++++");
	}

	@Override
	public void update(Orders orders) {ordersDao.update(orders);}

	@Override
	public void delete(Long status,Long id) {
		ordersDao.delete(status,id);
	}

	@Override
	public Page<Orders> getList(Page<Orders> pager, Long childId, Long parentId, String productNo, String productName, String startTime, String endTime, Integer status) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<Orders> lstData = ordersDao.queryList(childId,parentId,productNo,productName,startTime,endTime,status);

		// 获取分页后所需信息
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Page<EnterpriseOrders> getOrdersList(Page<EnterpriseOrders> pager, Long childId,Long parentId, 
			String productNo, String productName, String linkMan, String linkPhone, String startTime, String endTime, Integer status) {
		// 启用语句拦截，进行分页设置
		PageHelper.startPage(pager.getStart(), pager.getSize());
		// 查询数据
		List<EnterpriseOrders> lstData = enterpriseOrdersDao.queryList(childId, parentId, productNo, productName,
				linkMan, linkPhone, startTime, endTime, status);

		// 获取分页后所需信息
		PageInfo<EnterpriseOrders> pageInfo = new PageInfo<EnterpriseOrders>(lstData);
		pager.setTotal(pageInfo.getTotal());
		pager.setResult(lstData);
		return pager;
	}

	@Override
	public Orders getThirdOrders(String tuanname, String payId) {
		return ordersDao.queryThirdOrders(tuanname,payId);
	}
}