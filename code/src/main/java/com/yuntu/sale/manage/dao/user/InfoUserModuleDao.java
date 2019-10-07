package com.yuntu.sale.manage.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.user.InfoUserModule;

/**
 * 用户_功能模块_Dao
 * @Description 
 * @author snps
 * @date 下午2:22:50
 */
public interface InfoUserModuleDao {

	/**
	 * 通过用户Id查询
	 * @param userId
	 * @return List<InfoUserModule>
	 */
	List<InfoUserModule> queryByUser(long userId) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoUserModule
	 */
	InfoUserModule queryById(long id) ;

	/**
	 * 保存
	 * @param entity
	 * @return long
	 */
	long insert(InfoUserModule entity) ;

	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUserModule entity) ;
	
	/**
	 * 删除用户下的所有模块
	 * 伪删除
	 * @param userId
	 */
	void deleteByUser(Long userId);
	
	/**
	 * 删除用户下指定模块
	 * @param map
	 */
	void delete(HashMap<String, Long> map);
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteById(long id) ;

	/**
	 * 通过用户Id + moduleId查询
	 * @param mapCondition
	 * @return InfoUserModule
	 */
	InfoUserModule queryByUserIdAndModuleId(Map<String, Long> mapCondition) ;
}