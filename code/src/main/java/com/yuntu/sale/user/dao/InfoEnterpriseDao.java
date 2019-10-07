package com.yuntu.sale.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.user.po.InfoEnterprise;

/**
 * @Description 企业信息Dao接口 
 * @author snps
 * @date 2018年2月26日 下午2:08:51
 */
public interface InfoEnterpriseDao {

	/**
	 * 查询平台运营商
	 * @param name
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryOperation(@Param("name") String name);

	/**
	 * 查询全部商户
	 * @param companyType 商户类型
	 * @param name 商户名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryShop(@Param("companyType") Integer companyType, @Param("name") String name);
	
	/**
	 * 查询我的商户
	 * @param parentId 上级Id 
	 * @param companyType 商户类型
	 * @param name 商户名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryMyShop(@Param("parentId") Long parentId, 
			@Param("companyType") Integer companyType, @Param("name") String name);
	
	/**
	 * 查询我的供应商
	 * @param myId 我的企业Id
	 * @param name 供应商名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryMySupplier(@Param("myId") Long myId, @Param("name") String name);
	
	/**
	 * 查询我的分销商
	 * @param myId 我的企业Id
	 * @param name 分销商名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> queryMyDistributor(@Param("myId") Long myId, @Param("name") String name);

	/**
	 * 保存
	 */
	Long insert(InfoEnterprise entity);
	
	/**
	 * 通过Id查询
	 * @param id
	 * @return InfoEnterprise
	 */
	InfoEnterprise queryById(@Param("id") Long id);
	
	/**
	 * 通过企业名称查询（唯一）
	 * @param name
	 * @return InfoEnterprise
	 */
	InfoEnterprise queryByName(@Param("name") String name);
	
	/**
	 * 通过企业二级域名查询（唯一）
	 * @param domain
	 * @return InfoEnterprise
	 */
	InfoEnterprise queryByDomain(@Param("domain") String domain);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoEnterprise entity) ;
	
	/**
	 * 启用/停用
	 * @param id
	 * @param status （1：启用，0：停用）
	 */
	void updateStatus(@Param("id") Long id, @Param("status") Integer status);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(@Param("id") Long id);
	
	/**
	 * 查询可用的下级公司数量
	 * @param id 企业Id
	 * @return int
	 */
	int queryChildCount(@Param("id") Long id);

	/**
	 * 修改网站信息
	 * @param entity
	 */
	void updateWebsiteInfo(InfoEnterprise entity) ;

	/**
	 * 查询分销商 商品组 添加 分销商
	 * @param parentId 上级Id
	 * @param name 商户名称
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> querySaleDistributor(@Param("name") String name,@Param("parentId") Long parentId);
	
	/**
	 * 重置企业主账号
	 * @param enterpriseId 企业Id
	 * @param account 主账号
	 */
	void updateEnterpriseAccount(@Param("enterpriseId") Long enterpriseId, @Param("account") String account);
	
}