package com.yuntu.sale.manage.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncAction;
import com.yuntu.sale.base.po.user.InfoUserAction;
import com.yuntu.sale.manage.dao.user.InfoUserActionDao;
import com.yuntu.sale.manage.service.function.FuncActionService;
import com.yuntu.sale.manage.service.user.InfoUserActionService;

@Service("userActionService")
public class InfoUserActionServiceImpl implements InfoUserActionService {
	@Resource
	private InfoUserActionDao dao ;
	
	@Resource
	private FuncActionService actionService ;
	
	
	/**
	 * 通过userId + menuId 查询
	 * @param mapCondition
	 * @return List<InfoUserAction>
	 */
	public List<InfoUserAction> getByUser(long userId, long menuId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("menuId", menuId) ;
		List<InfoUserAction> lstUserAction = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserAction)) {
			return null ;
		}
		
		for(InfoUserAction userAction : lstUserAction) {
			FuncAction action = actionService.getById(userAction.getActionId()) ;
			if(!BaseUtil.isEmpty(action)) {
				userAction.setActionName(action.getName()) ;
				userAction.setActionUrl(action.getUrl()) ;
			}
		}
		
		return lstUserAction ;
	}
	
	/**
	 * 得到用户某功能菜单下的动作
	 * @param userId
	 * @param menuId
	 * @return List<InfoUserAction>
	 */
	public List<InfoUserAction> getUserAction(long userId, long menuId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("menuId", menuId) ;
		List<InfoUserAction> lstUserAction = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserAction)) {
			return null ;
		}
		
		for(InfoUserAction userAction : lstUserAction) {
			FuncAction action = actionService.getById(userAction.getActionId()) ;
			if(!BaseUtil.isEmpty(action)) {
				userAction.setActionName(action.getName()) ;
				// userMenu.setMenuUrl(menu.getUrl()) ;
			}
		}
		return lstUserAction ;
	}

	public InfoUserAction getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoUserAction entity) {
		return dao.insert(entity) ;
	}

	public void edit(InfoUserAction entity) {
		dao.update(entity) ;
	}
	
	public void remove(long id) {
		dao.deleteById(id) ;
	}
	
	public void removeByUser(long userId) {
		dao.deleteByUser(userId) ;
	}

	public boolean checkUserAction(long userId, long actionId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("actionId", actionId) ;
		InfoUserAction infoUserAction = dao.queryByUserIdAndActionId(mapCondition) ;
		return BaseUtil.isEmpty(infoUserAction)?Boolean.FALSE:Boolean.TRUE ;
	}
}