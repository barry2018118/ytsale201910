package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 码信息表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface CodeDao {

    public Code get(String id);

    public List<Code> findList(Code code);

    public Page<Code> findPage(Page<Code> page, Code code) ;

    public void save(Code code);

    public void update(Code code);

    public void delete(Code code);

    List<Code> queryOrderList(@Param("id")Long id);
}