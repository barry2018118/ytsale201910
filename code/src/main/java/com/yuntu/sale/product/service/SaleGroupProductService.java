package com.yuntu.sale.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.product.po.SaleGroup;
import com.yuntu.sale.product.po.SaleGroupLog;
import com.yuntu.sale.product.po.SaleGroupProduct;
import com.yuntu.sale.product.po.SaleGroupProductVo;

/**
 * 分销组商品Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface SaleGroupProductService {

	/**
	 * 通过Id查询
	 * @return SaleGroupProduct
	 */
	SaleGroupProduct getById(@Param("id") Long id) ;

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
	void update(SaleGroupProduct entity) ;
	
	/**
	 * 删除
	 * @param id
	 * @param enterpriseId 当前企业id
	 * @param entity 分销组
	 * @param saleGroupLog 分销组 操作日志
	 */
	void delete(@Param("id") Long id, @Param("enterpriseId") Long enterpriseId, @Param("entity")SaleGroup entity, @Param("saleGroupLog")SaleGroupLog saleGroupLog) ;

	/**
	 * 分销组 - 商品列表
	 *  @param no 编号
	 * @param name 名称
	 * @param groupId 分销组 id
	 * @param createGroupId 创建分销组企业 id
	 * @return SaleGroupEnterpriseVo
	 */
	Page<SaleGroupProductVo> getList(Page<SaleGroupProductVo> pager,@Param("no")String no, @Param("name")String name, @Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId) ;

	/**
	 * 分销组 - 商品列表
	 * @param groupId 分销组 id
	 * @param createGroupId 创建分销组企业 id
	 * @return SaleGroupEnterpriseVo
	 */
	List<SaleGroupProductVo> getProductList(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId) ;

	/**
	 * 根据条件单条记录查询
	 * @param groupId   分销组id
	 * @param createGroupId  创建分销组id
	 * @param productId  商品id
	 * @return
	 */
    List<SaleGroupProduct> getOne(@Param("groupId") Long groupId, @Param("createGroupId") Long createGroupId, @Param("productId") Long productId);

    /**
     * 查询组中已存在的商品
     * @param groupId 商品组Id
     * @return List<SaleGroupProduct>
     */
    List<SaleGroupProduct> getIsExistProduct(@Param("groupId") Long groupId);
 
    /**
     * 开启分销
     * @param groupId 商品组Id
     * @param sproductId 商品Id
     */
    void saveDescribution(Long groupId, Long sproductId);
    
    /**
     * 停止分销
     * @param groupId 商品组Id
     * @param sproductId 商品Id
     */
    void saveStopDescribution(Long groupId, Long sproductId);
    
}