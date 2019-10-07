package com.yuntu.sale.orders.service.impl;



import com.coolshow.util.Page;
import com.yuntu.sale.orders.dao.CodeDao;
import com.yuntu.sale.orders.dao.CodeProvideDao;
import com.yuntu.sale.orders.po.Code;
import com.yuntu.sale.orders.service.CodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 码信息表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Resource
	private CodeDao codeDao;

	public Code get(String id) {
		return codeDao.get(id);
	}
	
	public List<Code> findList(Code code) {
		return codeDao.findList(code);
	}
	
	public Page<Code> findPage(Page<Code> page, Code code) {
		return codeDao.findPage(page, code);
	}
	
	public void save(Code code) {
		codeDao.save(code);
	}
	
	public void delete(Code code) {
		codeDao.delete(code);
	}

	@Override
	public List<Code> findOrderList(Long id) {
		return codeDao.queryOrderList(id);
	}


}