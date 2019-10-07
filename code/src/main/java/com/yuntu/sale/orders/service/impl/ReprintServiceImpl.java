
package com.yuntu.sale.orders.service.impl;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.dao.ReprintDao;
import com.yuntu.sale.orders.po.Reprint;
import com.yuntu.sale.orders.service.ReprintService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 重新打印记录表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("ReprintService")
public class ReprintServiceImpl implements ReprintService {

	@Resource
	private ReprintDao reprintDao;

	public Reprint get(String id) {
		return reprintDao.get(id);
	}
	
	public List<Reprint> findList(Reprint reprint) {
		return reprintDao.findList(reprint);
	}
	
	public Page<Reprint> findPage(Page<Reprint> page, Reprint reprint) {
		return reprintDao.findPage(page, reprint);
	}
	
	public void save(Reprint reprint) {
		reprintDao.save(reprint);
	}
	
	public void delete(Reprint reprint) {
		reprintDao.delete(reprint);
	}
	
	
	
	
}