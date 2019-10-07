package com.yuntu.sale.base.po.function;

import java.io.Serializable;

import com.yuntu.sale.manage.action.function.BaseFunction;

/**
 * @Description 基础功能_按钮
 * @Table func_button
 * @author snps
 * @date 下午8:36:40
 */
public class FuncButton extends BaseFunction implements Serializable {

	private static final long serialVersionUID = 4099963255310145801L;

	/**
	 * 所属模块Id
	 */
	private Long moduleId;
	
	/**
	 * 所属菜单Id
	 */
	private Long menuId;
	
	/**
	 * 所属功能（动作）Id
	 */
	private Long actionId;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	
}