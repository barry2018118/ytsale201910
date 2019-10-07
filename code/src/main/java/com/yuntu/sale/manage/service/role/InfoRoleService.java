package com.yuntu.sale.manage.service.role;

import java.util.List;

import com.coolshow.util.Page;
import com.yuntu.sale.base.po.role.InfoRole;

/**
 * 角色Service
 * @author zhangw
 * @date
 */
public interface InfoRoleService {
	
	/**
	 * 查询角色详情
	 */
	InfoRole searchById(Long id);
	
	/**
	 * 查询企业下的角色信息
	 * @param pager
	 * @param enterpriseId 企业Id
	 * @param title 角色名
	 * @return Page<InfoRole>
	 */
	Page<InfoRole> searchByEnterpriseId(Page<InfoRole> pager, Long enterpriseId, String title);
	
	/**
	 * 查询企业下的角色信息
	 * @param enterpriseId
	 * @return List<InfoRole>
	 */
	List<InfoRole> searchByEnterpriseId(Long enterpriseId);
	
	/**
	 * 角色列表and条件查询
	 * @param pager
	 * @param name
	 * @param province
	 * @param city
	 * @return
	 */
	Page<InfoRole> search(Page<InfoRole> pager,String title);
	
	/**
	 * 验证角色名称重名
	 * @param name
	 * @return
	 */
	InfoRole searchByName(String title);
	
	
	/**
	 * 添加角色
	 * @param infoEnterprise
	 * @return
	 */
	Long addRole(InfoRole infoRole);
	
	/**
	 * 编辑角色
	 * @param 
	 * @return
	 */
	int modify(InfoRole infoRole);
	
	
	/**
	 * 删除角色
	 * @param id
	 */
	void deleteRole(Long id);
	
	/**
	 * 查询全部机构
	 */
	List<InfoRole> searchRole();

	int update(InfoRole role);
	
}