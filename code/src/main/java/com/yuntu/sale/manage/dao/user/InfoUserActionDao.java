package com.yuntu.sale.manage.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.user.InfoUserAction;

/**
 * 用户_功能动作_Dao
 * @Description 
 * @author snps
 * @date 下午2:26:49
 */
public interface InfoUserActionDao {
	
	/**
	 * 通过用户Id查询
	 * @param userId
	 * @return List<InfoUserAction>
	 */
	List<InfoUserAction> queryByUserId(long userId) ;

	/**
	 * 得到用户的功能动作
	 * @param mapCondition (userId + moduleId)
	 * @return List<InfoUserAction>
	 */
	List<InfoUserAction> queryByUser(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserAction
	 */
	InfoUserAction queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(InfoUserAction entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUserAction entity) ;
	
	/**
	 * 修改是否删除
	 * @param id
	 * @param isDelete
	 */
	void updateIsDelete(long id, int isDelete) ;
	
	/**
	 * 伪删除用户下所有的动作
	 * @param entity
	 */
	void deleteByUser(Long userId);
	
	/**
	 * 删除用户下指定动作
	 * @param map
	 */
	void delete(HashMap<String, Long> map);
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteById(long id) ;
	
	/**
	 * 通过用户Id + actionId查询
	 * @param mapCondition
	 * @return InfoUserAction
	 */
	InfoUserAction queryByUserIdAndActionId(Map<String, Long> mapCondition) ;
}