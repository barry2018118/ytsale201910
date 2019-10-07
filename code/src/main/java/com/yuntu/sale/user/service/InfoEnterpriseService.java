package com.yuntu.sale.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.base.vo.OperatorMessageVo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.user.po.InfoEnterprise;
import com.yuntu.sale.user.po.InfoEnterpriseRelation;
import com.yuntu.sale.user.po.InfoUser;

/**
 * @Description 企业信息Service接口 
 * @author snps
 * @date 2018年2月26日 下午2:05:50
 */
public interface InfoEnterpriseService {
	
	/**
	 * 查询平台运营商
	 * @param pager
	 * @param name 商户名称
	 * @return Page<InfoEnterprise>
	 */
	Page<InfoEnterprise> getOperation(Page<InfoEnterprise> pager, String name);
	
	/**
	 * 查看全部商户
	 * @param pager
	 * @param companyType 商户类型
	 * @param name 商户名称
	 * @return Page<InfoEnterprise>
	 */
	Page<InfoEnterprise> getAllShop(Page<InfoEnterprise> pager, Integer companyType, String name);
	
	/**
	 * 查看我的商户
	 * @param pager
	 * @param myId 我的企业Id
	 * @param companyType 商户类型
	 * @param name 商户名称
	 * @return Page<InfoEnterprise>
	 */
	Page<InfoEnterprise> getMyShop(Page<InfoEnterprise> pager, Long myId, Integer companyType, String name);

	/**
	 * 通过Id查询
	 */
	InfoEnterprise getById(Long id);
	
	/**
	 * 通过企业名称（唯一）查询	（使用：用于判断企业是否重名）
	 * @param name
	 * @return InfoEnterprise
	 */
	InfoEnterprise getByName(String name);
	
	/**
	 * 通过企业二级域名（唯一）查询	（使用：用于判断企业二级域名是否重复）
	 * @param domian
	 * @return InfoEnterprise
	 */
	InfoEnterprise getByDoamin(String domian);
	
	/**
	 * 保存企业信息	（同时保存：企业信息、企业关系、企业-主账号信息、企业-平台平台资金信息）
	 * @param enterprise 企业信息
	 * @param relation 企业关系信息
	 * @param user 企业主账户信息
	 * @param enterpriseCapital 企业平台资金信息
	 */
	void save(InfoEnterprise enterprise, InfoEnterpriseRelation relation, InfoUser user, EnterpriseCapitalPo enterpriseCapital);
	
	/**
	 * 修改企业信息	（同时修改：企业信息、企业-主账号信息）
	 * @param entity
	 */
	void update(InfoEnterprise enterprise, InfoUser user);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(InfoEnterprise entity);
	
	/**
	 * 启用/停用
	 * @param id
	 * @param status
	 * @return OperatorSuccessVo
	 */
	OperatorMessageVo setStatus(Long id, Integer status);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 修改所有信息（包括用户名、密码）
	 * @param entity
	 */
	void updateUsernameAndPassword(InfoUser entity);

	/**
	 * 修改网站信息
	 * @param entity
	 */
	void updateWebsiteInfo(InfoEnterprise entity);

	/**
	 * 查询分销商 商品组 添加 分销商
	 * @param parentId 上级Id
	 * @param name 商户名称
	 * @return List<InfoEnterprise>
	 */
	Page<InfoEnterprise> getMyDistributor(Page<InfoEnterprise> pager,@Param("name") String name,@Param("parentId") Long parentId);

	/**
	 * 查询分销商 商品组 添加 分销商
	 * @param name 分销商账号
	 * @param parentId 上级企业Id
	 * @return List<InfoEnterprise>
	 */
	List<InfoEnterprise> getMyDistributorNoPage(String name, Long parentId);
	
	/**
	 * 重置企业主账号
	 * @param enterpriseId 企业Id
	 * @param account 主账号
	 */
	void updateEnterpriseAccount(Long enterpriseId, String account);
	
}