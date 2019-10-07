package com.yuntu.sale.capital.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.ExtractPo;

/**
 * @Description 提现Dao接口
 * @author snps
 * @date 2018年3月8日 下午3:00:07
 */
public interface ExtractDao {

    /**
     * 通过Id获取
     * @param id
     * @return text
     */
    ExtractPo queryById(@Param("id") Long id);

    /**
     * 查询我的用户
     * @param enterpriseId 企业Id
     * @param start 开始时间
     * @param end 结束时间
     * @return List<text>
     */
    List<ExtractPo> queryRechargeTime(@Param("enterpriseId") Long enterpriseId, @Param("start") String start, @Param("end") String end,@Param("state") Integer state);

    /**
     * 新增
     * @param entity
     */
    Long insert(ExtractPo entity);
    /**
     * 保存
     * @param entity
     */
    Long save(ExtractPo entity);

    /**
     * 查询某企业的累计提现金额
     * @param enterpriseId 企业Id
     * @param startDate 开始时间
     * @param endDate 截止时间
     * @return ExtractPo
     */
    ExtractPo queryByDate(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
}