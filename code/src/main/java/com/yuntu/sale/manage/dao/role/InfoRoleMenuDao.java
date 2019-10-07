package com.yuntu.sale.manage.dao.role;

import java.util.List;
import java.util.Map;

import com.yuntu.sale.base.po.function.FuncMenu;
import com.yuntu.sale.base.po.role.InfoRoleMenu;

/**
 * 类描述： 权限组_功能菜单Dao
 * @author wj_tang
 * 创建时间：2017-1-11 下午1:46:22
 */
public interface InfoRoleMenuDao {
	
	/**
	 * 得到给权限组分配的功能菜单
	 * @param roleId
	 * @return List<InfoRoleMenu>
	 */
	List<InfoRoleMenu> queryAll(long roleId) ;

	/**
	 * 得到给权限组分配的功能菜单
	 * @param mapCondition (roleId + moduleId)
	 * @return List<InfoRoleMenu>
	 */
	List<InfoRoleMenu> queryByModule(Map<String, Object> mapCondition) ;
	
	/**
	 * 通过权限组Id+菜单Id查询
	 * @param mapCondition(roleId, menuId)
	 * @return InfoRoleMenu
	 */
	InfoRoleMenu queryMenu(Map<String, Long> mapCondition) ;
	
	/**
	 * 通过id获取
	 * @param id
	 * @return InfoRoleMenu
	 */
	InfoRoleMenu queryById(long id) ;
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	long insert(InfoRoleMenu entity) ;
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id) ;
	
	/**
	 * 通过roleId查询其下所有菜单
	 * @param RoleId
	 * @return
	 */
	List<FuncMenu> queryAllByRoleId(Long RoleId);
	
	
}