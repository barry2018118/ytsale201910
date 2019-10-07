package com.yuntu.sale.product.dao;

import com.yuntu.sale.product.po.SupplierProduct;
import com.yuntu.sale.product.po.SupplierProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 供应商产品Dao接口 
 * @author snps
 * @date 2018年2月14日 下午4:19:16
 */
public interface SupplierProductDao {
    /**
     * 通过Id查询
     * @return SupplierProduct
     */
    SupplierProduct queryById(@Param("id") Long id) ;

    /**
     * 通过检索条件查询
     * @return  List<SupplierProductVo>
     */
    List<SupplierProductVo> querySearch( @Param("enterpriseId") Long enterpriseId,
                                       @Param("productNo") String productNo,
                                       @Param("productName") String productName,
                                       @Param("productategory") Integer productategory,
                                       @Param("productStatus") Integer productStatus,
                                       @Param("productSource") Integer productSource,
                                       @Param("productAffiliation") Integer productAffiliation) ;

    /**
     * 保存
     * @param entity
     */
    void insert(SupplierProduct entity) ;

    /**
     * 修改
     * @param entity
     */
    void update(SupplierProduct entity) ;

    /**
     * 状态
     * @param entity
     */
    void status(SupplierProduct entity) ;

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") Long id) ;

    /**
     * 订单 购买商品
     * @param enterpriseId 企业id
     * @param productNo 商品编号
     * @param productName 商名名称
     * @return SupplierProduct
     */
    List<SupplierProduct> queryMySearch(@Param("enterpriseId") Long enterpriseId,
                                        @Param("productNo") String productNo,
                                        @Param("productName") String productName) ;
    /**
     * 通过name查询
     * @param name 商品名
     * @return SupplierProduct
     */
    SupplierProduct queryByName(String name);
}