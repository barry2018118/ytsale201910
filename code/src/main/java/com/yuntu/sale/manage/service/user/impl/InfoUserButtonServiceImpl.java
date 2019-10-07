package com.yuntu.sale.manage.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolshow.util.BaseUtil;
import com.yuntu.sale.base.po.function.FuncButton;
import com.yuntu.sale.base.po.user.InfoUserButton;
import com.yuntu.sale.manage.dao.user.InfoUserButtonDao;
import com.yuntu.sale.manage.service.function.FuncButtonService;
import com.yuntu.sale.manage.service.user.InfoUserButtonService;
@Service
public class InfoUserButtonServiceImpl implements InfoUserButtonService {
	@Resource
	private InfoUserButtonDao dao ;
	
	@Resource
	private FuncButtonService buttonService ;
	
	
	/**
	 * 通过userId + actionId 查询
	 * @param mapCondition
	 * @return List<InfoUserAction>
	 */
	public List<InfoUserButton> getByUser(long userId, long actionId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("actionId", actionId) ;
		List<InfoUserButton> lstUserButton = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserButton)) {
			return null ;
		}
		
		for(InfoUserButton userButton : lstUserButton) {
			FuncButton button = buttonService.getById(userButton.getButtonId()) ;
			if(!BaseUtil.isEmpty(button)) {
				userButton.setButtonName(button.getName()) ;
				userButton.setButtonUrl(button.getUrl()) ;
			}
		}
		
		return lstUserButton ;
	}

	/**
	 * 得到用户某功能动作下的按钮
	 * @param userId
	 * @param actionId
	 * @return List<InfoUserButton>
	 */
	public List<InfoUserButton> getUserButton(long userId, long actionId) {
		Map<String, Object> mapCondition = new HashMap<String, Object>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("actionId", actionId) ;
		List<InfoUserButton> lstUserButton = dao.queryByUser(mapCondition) ;
		if(BaseUtil.isEmpty(lstUserButton)) {
			return null ;
		}
		
		for(InfoUserButton userButton : lstUserButton) {
			FuncButton button = buttonService.getById( userButton.getButtonId()) ;
			if(!BaseUtil.isEmpty(button)) {
				userButton.setButtonName(button.getName()) ;
				// userMenu.setMenuUrl(menu.getUrl()) ;
			}
		}
		return lstUserButton ;
	}
	
	public InfoUserButton getById(long id) {
		return dao.queryById(id) ;
	}

	public long add(InfoUserButton entity) {
		return dao.insert(entity) ;
	}

	public void edit(InfoUserButton entity) {
		dao.update(entity) ;
	}
	
	public void remove(long id) {
		dao.deleteById(id) ;
	}
	
	public void removeByUser(long userId) {
		dao.deleteByUser(userId) ;
	}

	public boolean checkUserButton(long userId, long buttonId) {
		Map<String, Long> mapCondition = new HashMap<String, Long>() ;
		mapCondition.put("userId", userId) ;
		mapCondition.put("actionId", buttonId) ;
		InfoUserButton infoUserButton = dao.queryByUserIdAndButtonId(mapCondition) ;
		return BaseUtil.isEmpty(infoUserButton)?Boolean.FALSE:Boolean.TRUE ;
	}
}