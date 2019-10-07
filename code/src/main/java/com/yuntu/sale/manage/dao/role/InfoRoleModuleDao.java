package com.yuntu.sale.manage.dao.role;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.function.FuncModule;
import com.yuntu.sale.base.po.role.InfoRoleModule;
/**
 * 类描述：权限组_功能模块Dao
 * @author wj_tang
 * 创建时间：2017-1-11 下午1:46:33
 */
public interface InfoRoleModuleDao {

	/**
	 * 得到给权限组分配的功能模块
	 * @param roleId
	 * @return List<InfoRoleModule>
	 */
	List<InfoRoleModule> queryAll(long roleId) ;
	
	/**
	 * 通过权限组Id+模块Id查询
	 * @param mapCondition(roleId, moduleId)
	 * @return InfoRoleModule
	 */
	InfoRoleModule queryModule(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoRoleModule
	 */
	InfoRoleModule queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoRoleModule entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id) ;
	
	/**
	 * 通过roleId查询其下所有模块
	 * @param RoleId
	 * @return
	 */
	List<FuncModule> queryAllByRoleId(Long RoleId);
}
