
package com.yuntu.sale.orders.dao;


import com.coolshow.util.Page;
import com.yuntu.sale.orders.po.Reprint;

import java.util.List;

/**
 * 重新打印记录表DAO接口
 * @author zy
 * @version 2018-04-23
 */

public interface ReprintDao {
    public Reprint get(String id);

    public List<Reprint> findList(Reprint reprint);

    public Page<Reprint> findPage(Page<Reprint> page, Reprint reprint);

    public void save(Reprint reprint);

    public void delete(Reprint reprint);


}