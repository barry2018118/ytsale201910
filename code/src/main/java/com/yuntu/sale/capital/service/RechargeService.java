package com.yuntu.sale.capital.service;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.RechargePo;

/**
 * 
 * @Description 充值Service接口
 * @author snps
 * @date 2018年3月8日 下午3:04:53
 */
public interface RechargeService {

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
     * @return Page<InfoUser>
     */
    Page<RechargePo> queryByTime(Page<RechargePo> pager,@Param("enterpriseId") Long enterpriseId,@Param("username") String username, @Param("start") String start, @Param("end") String end);

    /**
     * 保存
     * @param entity
     */
    void update(RechargePo entity);

    /**
     * 新增
     * @param entity
     */
    void save(@Param("entity")RechargePo entity, @Param("enterpriseCapital")EnterpriseCapitalPo enterpriseCapital, @Param("log")EnterpriseAccountLogPo log);

    /**
     * 查询某企业的累计充值金额
     * @param enterpriseId 企业Id
     * @param startDate 开始时间
     * @param endDate 截止时间
     * @return RechargePo
     */
    RechargePo getByDate(Long enterpriseId, Date startDate, Date endDate);
    
}