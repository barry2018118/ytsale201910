package com.yuntu.sale.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuntu.sale.info.dao.ActionTypeDao;
import com.yuntu.sale.info.po.ActionTypePo;
import com.yuntu.sale.info.service.ActionTypeService;

/**
 * @Description 业务动作类型Service实现类
 * @author snps
 * @date 2018年3月8日 上午9:46:45
 */
@Service("actionTypeService")
public class ActionTypeServiceImpl implements ActionTypeService {

	@Resource
	private ActionTypeDao dao;
	
	@Override
	public List<ActionTypePo> getList() {
		return dao.queryList();
	}

	@Override
	public ActionTypePo getById(Long id) {
		return dao.queryById(id);
	}

}