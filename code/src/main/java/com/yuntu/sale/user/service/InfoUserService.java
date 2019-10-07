package com.yuntu.sale.user.service;

import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 用户信息Service接口 
 * @author snps
 * @date 2018年2月26日 下午2:26:11
 */
public interface InfoUserService {
	
	/**
	 * 查询用户
	 * @param pager
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	Page<InfoUser> getUser(Page<InfoUser> pager, String username, String name);
	
	/**
	 * 查询我的用户
	 * @param pager
	 * @param enterpriseId 企业Id
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	Page<InfoUser> getMyUser(Page<InfoUser> pager, Long enterpriseId, String username, String name);

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return InfoUser
	 */
	InfoUser login(String username, String password);
	
	/**
	 * 通过Id查询
	 * @param id
	 * @return InfoUser
	 */
	InfoUser getById(Long id);
	
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
	InfoUser getByUsername(String username);
	
	/**
	 * 保存
	 * @param user
	 */
	void save(InfoUser user);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoUser entity);
	
	/**
	 * 启用/停用
	 * @param id
	 * @param status （1：启用，0：停用）
	 */
	OperatorMessageVo setStatus(Long id, Integer status);
	
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