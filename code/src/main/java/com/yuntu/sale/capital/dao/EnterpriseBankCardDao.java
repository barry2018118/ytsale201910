package com.yuntu.sale.capital.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.EnterpriseBankCardPo;

/**
 * @Description 企业-银行卡Dao接口
 * @author snps
 * @date 2018年3月8日 下午2:34:52
 */
public interface EnterpriseBankCardDao {

	/**
	 * 通过企业Id获取企业银行卡
	 * @param enterpriseId 企业Id
	 * @return List<EnterpriseBankCardPo>
	 */
	List<EnterpriseBankCardPo> queryByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 通过Id获取
	 * @param id
	 * @return EnterpriseBankCardPo
	 */
	EnterpriseBankCardPo queryById(@Param("id") Long id);

	/**
	 * 通过cardNo获取
	 * @param cardNo
	 * @return EnterpriseBankCardPo
	 */
	EnterpriseBankCardPo queryCardNo(@Param("cardNo") String cardNo);

	/**
	 * 查询我的用户
	 * @param enterpriseId 企业Id
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	List<EnterpriseBankCardPo> queryMyCard(@Param("enterpriseId") Long enterpriseId,
							   @Param("username") String username, @Param("name") String name);

	/**
	 * 保存
	 * @param entity
	 */
	void insert(EnterpriseBankCardPo entity);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(EnterpriseBankCardPo entity);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(@Param("id") Long id);

}