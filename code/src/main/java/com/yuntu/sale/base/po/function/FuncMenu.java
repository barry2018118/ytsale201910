package com.yuntu.sale.base.po.function;

import java.io.Serializable;
import java.util.List;

import com.yuntu.sale.manage.action.function.BaseFunction;

/**
 * @Description 基础功能_菜单
 * @Table func_menu
 * @author snps
 * @date 下午8:33:21
 */
public class FuncMenu extends BaseFunction implements Serializable {
	
	private static final long serialVersionUID = -7006917402068498799L;

	/**
	 * 功能动作
	 */
	private List<FuncAction> actions ;

	/**
	 * 所属模块Id
	 */
	private Long moduleId;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public List<FuncAction> getActions() {
		return actions;
	}

	public void setActions(List<FuncAction> actions) {
		this.actions = actions;
	}
}