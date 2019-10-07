package com.yuntu.sale.manage.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.base.po.user.InfoUserMenu;
import com.yuntu.sale.manage.dao.user.InfoUserMenuDao;
import com.yuntu.sale.manage.service.function.FuncMenuService;
import com.yuntu.sale.manage.service.user.InfoUserActionService;
import com.yuntu.sale.manage.service.user.InfoUserMenuService;

@Service("userMenuService")
public class InfoUserMenuServiceImpl implements InfoUserMenuService {
	@Resource
	private InfoUserMenuDao dao ;
	
	@Resource
	private FuncMenuService menuService ;
	
	@Resource
	private InfoUserActionService infoUserActionService ;
	
	
	/**
	 * 通过userId + moduleId 查询
	 * @param mapCondition
	 * @return List<InfoUserMenu>
	 */
	public List<InfoUserMenu> getByUser(long userId, long moduleId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("moduleId", moduleId) ;
		List<InfoUserMenu> lstUserMenu = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserMenu)) {
			return null ;
		}
		
		for(InfoUserMenu userMenu : lstUserMenu) {
			FuncMenu menu = menuService.getById(userMenu.getMenuId()) ;
			if(!BaseUtil.isEmpty(menu)) {
				userMenu.setMenuName(menu.getName()) ;
				userMenu.setMenuUrl(menu.getUrl()) ;
			}
			// 添加Menu下的Action
			List<InfoUserAction> lstUserAction = infoUserActionService.getByUser(userId, userMenu.getMenuId()) ;
			if(!BaseUtil.isEmpty(lstUserAction)) {
				userMenu.setUserAction(lstUserAction) ;
			}
		}
		return lstUserMenu ;
	}
	
	/**
	 * 得到用户某功能模块下的菜单
	 * @param userId
	 * @param moduleId
	 * @return List<InfoUserMenu>
	 */
	public List<InfoUserMenu> getUserMenu(long userId, long moduleId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("moduleId", moduleId) ;
		List<InfoUserMenu> lstUserMenu = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserMenu)) {
			return null ;
		}
		
		for(InfoUserMenu userMenu : lstUserMenu) {
			FuncMenu menu = menuService.getById(userMenu.getMenuId()) ;
			if(!BaseUtil.isEmpty(menu)) {
				userMenu.setMenuName(menu.getName()) ;
				// userMenu.setMenuUrl(menu.getUrl()) ;
			}
		}
		return lstUserMenu ;
	}

	public InfoUserMenu getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoUserMenu entity) {
		return dao.insert(entity) ;
	}

	public void edit(InfoUserMenu entity) {
		dao.update(entity) ;
	}

	public void remove(long id) {
		dao.deleteById(id) ;
	}
	
	public void removeByUser(long userId) {
		dao.deleteByUser(userId) ;
	}

	public boolean checkUserMenu(long userId, long menuId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("menuId", menuId) ;
		InfoUserMenu infoUserMenu = dao.queryByUserIdAndMenuId(mapCondition) ;
		return BaseUtil.isEmpty(infoUserMenu)?Boolean.FALSE:Boolean.TRUE ;
	}
}