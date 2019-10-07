package com.yuntu.sale.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.user.po.InfoUser;

public interface InfoUserDao {
	
	/**
	 * 查询平台用户
	 * @param pager
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	List<InfoUser> queryUser(@Param("username") String username, @Param("name") String name);
	
	/**
	 * 查询我的用户
	 * @param pager
	 * @param enterpriseId 企业Id
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	List<InfoUser> queryMyUser(@Param("enterpriseId") Long enterpriseId, 
			@Param("username") String username, @Param("name") String name);
	
	/**
	 * 通过用户名和密码查询
	 * @param username
	 * @param password
	 * @return InfoUser
	 */
	InfoUser queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	/**
	 * 通过Id查询
	 */
	InfoUser queryById(@Param("id") Long id);
	
	/**
	 * 得到企业主账号
	 * @param enterpriseId 企业Id
	 * @return InfoUser
	 */
	InfoUser getEnterpriseMasterUser(Long enterpriseId);
	
	/**
	 * 通过用户名（唯一）查询	（使用：用于判断用户名是否重名）
	 * @param username
	 * @return InfoUser
	 */
	InfoUser queryByUsername(String username);
	
	/**
	 * 保存
	 * @param user
	 */
	Long insert(InfoUser user);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUser entity);
	
	/**
	 * 修改所有信息（包括用户名、密码）
	 * @param entity
	 */
	void updateUsernameAndPassword(InfoUser entity);
	
	/**
	 * 启用/停用
	 * @param id
	 * @param status （1：启用，0：停用）
	 */
	void updateStatus(@Param("id")Long id, @Param("status")Integer status);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 修改密码
	 * @param user
	 */
	void updatePassword(InfoUser user);


}