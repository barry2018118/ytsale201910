package com.yuntu.sale.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SaleGroupEnterprise;
import com.yuntu.sale.product.po.SaleGroupEnterpriseVo;
import com.yuntu.sale.product.po.SaleGroupLog;

/**
 * 分销组商户Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupEnterpriseService {

	/**
	 * 分销组 - 分销商列表
	 * @param name 检索字段
	 * @param groupId 分销组 id
	 * @param createGroupId 创建分销组企业 id
 	 * @return SaleGroupEnterpriseVo
	 */
	Page<SaleGroupEnterpriseVo> getList(Page<SaleGroupEnterpriseVo> pager, @Param("name")String name,@Param("groupId") Long groupId,@Param("createGroupId") Long createGroupId) ;

	/**
	 * 通过Id查询
	 * @return SaleGroup
	 */
	SaleGroupEnterprise getById(@Param("id") Long id) ;

	/**
	 * 保存
	 * @param id  分销组 id
	 * @param box 选中商品列表
	 * @param userId 当前登陆用户
	 * @param myEnterpriseId 当前登陆企业id
	 */
	void save(@Param("id")Long id,@Param("box")String[] box,@Param("userId")Long userId,@Param("myEnterpriseId")Long myEnterpriseId);

	/**
	 * 修改
	 * @param entity
	 */
	void update(SaleGroupEnterprise entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @param enterpriseId 当前企业id
	 * @param entity 分销组
	 * @param saleGroupLog 分销组 操作日志
	 */
	void delete(@Param("id") Long id,@Param("enterpriseId") Long enterpriseId,@Param("entity")SaleGroup entity,@Param("saleGroupLog")SaleGroupLog saleGroupLog) ;

	/**
	 * 根据条件单条记录查询
	 * @param groupId   分销组id
	 * @param createGroupId  创建分销组id
	 * @param childEnterpriseId  分销商企业id
	 * @return
	 */
	List<SaleGroupEnterprise> getOne(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId, @Param("childEnterpriseId") Long childEnterpriseId);

	/**
	 * 分销组 - 分销商列表
	 * @param groupId 分销组 id
	 * @param createGroupId 创建分销组企业 id
	 * @return SaleGroupEnterpriseVo
	 */
	List<SaleGroupEnterpriseVo> getEnterPriseList(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId) ;
	
	/**
     * 查询组中已存在的企业
     * @param groupId 商品组Id
     * @return List<SaleGroupEnterprise>
     */
    List<SaleGroupEnterprise> getIsExistEnterprise(Long groupId);
    
    /**
     * 查询企业是否已属于商品组
     * @param enterpriseId 企业Id
     * @return int
     */
    int getChildIsBePartGroup(Long enterpriseId);
    
}