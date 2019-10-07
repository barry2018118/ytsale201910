package com.yuntu.sale.capital.service;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.coolshow.util.Page;
import com.yuntu.sale.capital.po.EnterpriseAccountLogPo;
import com.yuntu.sale.capital.po.EnterpriseCapitalPo;
import com.yuntu.sale.capital.po.ExtractPo;

/**
 * @Description 提现Service接口
 * @author snps
 * @date 2018年3月8日 下午3:06:58
 */
public interface ExtractService {

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
     * @return Page<text>
     */
    Page<ExtractPo> queryByTime(Page<ExtractPo> pager, @Param("enterpriseId") Long enterpriseId, @Param("start") String start, @Param("end") String end,@Param("state") Integer state);

    /**
     * 新增
     * @param entity
     */
    void save(@Param("entity")ExtractPo entity,@Param("enterpriseCapital")EnterpriseCapitalPo enterpriseCapital);
 
    /**
     * 保存
     * @param entity,enterpriseAccountLogPo 提现,资金变动
     */
    void update( @Param("entity")ExtractPo entity,@Param("enterpriseCapital") EnterpriseCapitalPo enterpriseCapital, @Param("enterpriseAccountLogPo")EnterpriseAccountLogPo enterpriseAccountLogPo );
    
    /**
     * 查询某企业的累计提现金额
     * @param enterpriseId 企业Id
     * @param startDate 开始时间
     * @param endDate 截止时间
     * @return ExtractPo
     */
    ExtractPo getByDate(Long enterpriseId, Date startDate, Date endDate);
    
}