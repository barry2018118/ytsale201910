package com.yuntu.sale.orders.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.orders.dao.CodeProvideDao;
import com.yuntu.sale.orders.po.CodeProvide;
import com.yuntu.sale.orders.service.CodeProvideService;

/**
 * 码库表Service
 * @author zy
 * @version 2018-04-23
 */
@Service("codeProvideService")
public class CodeProvideServiceImpl implements CodeProvideService {

	@Resource
	private CodeProvideDao codeProvideDao;

	public CodeProvide get(Long id) {
		return codeProvideDao.get(id);
	}
	
	public List<CodeProvide> find(Integer num) {
		codeProvideDao.begin();
		List<CodeProvide> code = codeProvideDao.find(num);
		Iterator<CodeProvide> codecode = code.iterator();
		while (codecode.hasNext()){
			codeProvideDao.update(codecode.next().getId());
		}
		codeProvideDao.commit();
		return code;
	}
	
	public void save(CodeProvide codeProvide) {
		codeProvideDao.save(codeProvide);
	}
	
	public void update(Long id) {
		codeProvideDao.update(id);
	}

	@Override
	public Integer getCountByCodeAndStatus5(String code) {
		return codeProvideDao.quertCountByCodeAndStatus5(code);
	}

}