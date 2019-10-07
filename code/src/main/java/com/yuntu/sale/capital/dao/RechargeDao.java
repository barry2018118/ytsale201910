package com.yuntu.sale.capital.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuntu.sale.capital.po.RechargePo;

/**
 * @Description 充值Dao接口
 * @author snps
 * @date 2018年3月8日 下午2:57:25
 */
public interface RechargeDao {

    /**
     * 通过Id获取
     * @param id
     * @return RechargePo
     */
    RechargePo queryById(@Param("id") Long id);

    /**
     * 查询我的用户
     * @param enterpriseId 企业Id
     * @param username 用户名
     * @param start 开始时间
     * @param end 结束时间
     * @return List<RechargePo>
     */
    List<RechargePo> queryRechargeTime(@Param("enterpriseId") Long enterpriseId, @Param("username") String username, @Param("start") String start, @Param("end") String end);

    /**
     * 新增
     * @param entity
     */
    Long insert(RechargePo entity);

    /**
     * 保存
     * @param entity
     */
    Long save(RechargePo entity);
	
    /**
     * 查询某企业的累计充值金额
     * @param enterpriseId 企业Id
     * @param startDate 开始时间
     * @param endDate 截止时间
     * @return RechargePo
     */
    RechargePo queryByDate(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
}