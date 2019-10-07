package com.yuntu.sale.base.po.function;

import java.io.Serializable;
import java.util.List;

import com.yuntu.sale.manage.action.function.BaseFunction;

/**
 * @Description 基础功能_模块
 * @Table func_module
 * @author snps
 * @date 下午8:22:29
 */
public class FuncModule extends BaseFunction implements Serializable {

	private static final long serialVersionUID = -7289358751474965183L;

	/**
	 * 功能菜单
	 */
	private List<FuncMenu> menus;

	public List<FuncMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<FuncMenu> menus) {
		this.menus = menus;
	}
	
}