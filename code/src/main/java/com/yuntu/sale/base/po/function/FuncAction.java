package com.yuntu.sale.base.po.function;

import java.io.Serializable;
import java.util.List;

import com.yuntu.sale.manage.action.function.BaseFunction;

/**
 * @Description 基础功能_动作
 * @Table func_action
 * @author snps
 * @date 下午8:34:37
 */
public class FuncAction extends BaseFunction implements Serializable {

	private static final long serialVersionUID = -2629216221844854636L;

	/**
	 * 功能按钮
	 */
	private List<FuncButton> buttons;

	/**
	 * 所属模块Id
	 */
	private Long moduleId;

	/**
	 * 所属菜单Id
	 */
	private Long menuId;

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

	public List<FuncButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<FuncButton> buttons) {
		this.buttons = buttons;
	}
	
}