package com.yuntu.sale.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.product.po.EnterpriseProduct;
import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.po.SupplierProductVo;

/**
 * 供应商产品Service接口
 * @author zy
 * @version 2018-04-02
 */
public interface SupplierProductService {

    /**
     * 通过Id查询
     *
     * @return SupplierProduct
     */
    SupplierProduct getById(@Param("id") Long id);

    /**
     * 通过检索条件查询
     *
     * @return SupplierProduct
     */
    Page<SupplierProductVo> querySearch(Page<SupplierProductVo> pager,
                                        @Param("enterpriseId") Long enterpriseId,
                                        @Param("productNo") String productNo,
                                        @Param("productName") String productName,
                                        @Param("productategory") Integer productategory,
                                        @Param("productStatus") Integer productStatus,
                                        @Param("productSource") Integer productSource,
                                        @Param("productAffiliation") Integer productAffiliation);
    
    /**
     * 商品组中-添加商品-列表
     * @param enterpriseId
     * @param productNo
     * @param productName
     * @param productategory
     * @param productStatus
     * @param productSource
     * @param productAffiliation
     * @return
     */
    List<SupplierProductVo> querySearchNoPage(Long enterpriseId, String productNo, String productName, 
    		Integer productategory, Integer productStatus, Integer productSource, Integer productAffiliation);

    /**
     * 保存
     *
     * @param entity
     */
    void save( @Param("entity")SupplierProduct entity, @Param("enterpriseProduct")EnterpriseProduct enterpriseProduct);

    /**
     * 修改
     *
     * @param entity
     */
    void update(SupplierProduct entity);

    /**
     * 状态
     *
     * @param entity
     */
    void status(SupplierProduct entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(@Param("id") Long id);

    /**
     * 通过name查询
     * @param name 商品名
     * @return SupplierProduct
     */
    SupplierProduct getByName(@Param("name") String name);

    /**
     * 订单 购买商品
     * @param enterpriseId 企业id
     * @param productNo 商品编号
     * @param productName 商名名称
     * @return SupplierProduct
     */
    Page<SupplierProduct> getShopProduct(Page<SupplierProduct> pager,
                                         @Param("enterpriseId") Long enterpriseId,
                                         @Param("productNo")String productNo,
                                         @Param("productName")String productName);
}