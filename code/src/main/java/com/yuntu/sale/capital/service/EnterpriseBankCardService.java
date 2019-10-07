package com.yuntu.sale.capital.service;

import java.util.List;

import com.coolshow.util.Page;
import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.EnterpriseBankCardPo;

/**
 * @Description 企业-银行卡Service接口
 * @author snps
 * @date 2018年3月8日 下午2:46:37
 */
public interface EnterpriseBankCardService {

	/**
	 * 通过企业Id获取企业银行卡
	 * @param enterpriseId 企业Id
	 * @return List<EnterpriseBankCardPo>
	 */
	List<EnterpriseBankCardPo> getByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 通过Id获取
	 * @param id
	 * @return EnterpriseBankCardPo
	 */
	EnterpriseBankCardPo getById(@Param("id") Long id);

	/**
	 * 通过卡号查询
	 * @param cardNo
	 * @return EnterpriseBankCardPo
	 */
	EnterpriseBankCardPo getByCardNo(String cardNo);

	/**
	 * 查询企业的银行卡
	 * @param pager
	 * @param enterpriseId 企业Id
	 * @param username 用户名
	 * @param name 用户姓名
	 * @return Page<InfoUser>
	 */
	Page<EnterpriseBankCardPo> getMyBankCard(Page<EnterpriseBankCardPo> pager, Long enterpriseId, String username, String name);

	/**
	 * 保存
	 * @param entity
	 */
	void save(EnterpriseBankCardPo entity);
	
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