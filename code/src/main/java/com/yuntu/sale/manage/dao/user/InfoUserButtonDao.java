package com.yuntu.sale.manage.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.user.InfoUserButton;

/**
 * 用户_功能按钮_Dao
 * @Description 
 * @author snps
 * @date 下午2:30:58
 */
public interface InfoUserButtonDao {

	/**
	 * 得到用户的功能按钮
	 * @param mapCondition (userId + actionId)
	 * @return List<InfoUserButton>
	 */
	List<InfoUserButton> queryByUser(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserButton
	 */
	InfoUserButton queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(InfoUserButton entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUserButton entity) ;
	
	/**
	 * 修改是否删除
	 * @param id
	 * @param isDelete
	 */
	void updateIsDelete(long id, int isDelete) ;
	
	/**
	 * 伪删除用户下的所有按钮
	 * @param entity
	 */
	void deleteByUser(Long userId) ;
	
	/**
	 * 删除用户下指定按钮
	 * @param map
	 */
	void delete(HashMap<String, Long> map) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteById(long id) ;

	/**
	 * 通过用户Id + buttonId查询
	 * @param mapCondition
	 * @return InfoUserButton
	 */
	InfoUserButton queryByUserIdAndButtonId(Map<String, Long> mapCondition) ;
}