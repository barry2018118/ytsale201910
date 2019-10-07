package com.yuntu.sale.manage.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.base.po.role.InfoRole;

/**
 * 角色管理dao
 * @author 
 * @date
 */
public interface InfoRoleDao {
	
	/**
	 * 添加角色
	 * @param infoEnterprise
	 * @return
	 */
	Long insert(InfoRole infoRole);
	
	/**
	 * 判断角色重名
	 * @param name
	 * @return
	 */
	InfoRole selectByName(String title);
	
	
	/**
	 * 根据主键查询角色信息
	 * @param id
	 * @return
	 */
	InfoRole selectByPrimaryKey(Long id);
	
	/**
	 * 编辑角色信息
	 * @param infoEnterprise
	 * @return
	 */
	int editByPrimaryKey(InfoRole infoRole);
	
	
	/**
	 * 查询所有角色和条件搜索
	 * @param pager
	 * @return List<InfoRole>
	 */
	List<InfoRole> search(@Param("title") String title);
	
	/**
	 * 查询企业下的角色信息
	 * @param enterpriseId 企业Id
	 * @param title 角色名
	 * @return List<InfoRole>
	 */
	List<InfoRole> searchByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("title") String title);
	
	/**
	 * 删除角色
	 * @param id
	 */
	void deleteByPrimaryKey(Long id);
	
	/**
	 * 查询全部角色
	 */
	List<InfoRole> selectAll();

	int updateByPrimaryKey(InfoRole infoRole);
}
