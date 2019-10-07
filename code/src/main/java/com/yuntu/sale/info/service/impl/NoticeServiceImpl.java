package com.yuntu.sale.info.service.impl;

import com.coolshow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.sale.info.dao.NoticeDao;
import com.yuntu.sale.info.po.NoticePo;
import com.yuntu.sale.info.po.NoticeVo;
import org.springframework.stereotype.Service;

import com.yuntu.sale.info.service.NoticeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 公告Service实现类
 * @author snps
 * @date 2018年2月27日 下午2:26:41
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeDao dao;

    @Override
    public Page<NoticeVo> getByTitle(Page<NoticeVo> pager, String title) {
        // 启用语句拦截，进行分页设置
        PageHelper.startPage(pager.getStart(), pager.getSize());
        // 查询数据
        List<NoticeVo> lstData = dao.queryToTitle(title);

        // 获取分页后所需信息
        PageInfo<NoticeVo> pageInfo = new PageInfo<NoticeVo>(lstData);
        pager.setTotal(pageInfo.getTotal());
        pager.setResult(lstData);
        return pager;
    }
    
    @Override
    public NoticePo getById(Long id) {
        return dao.queryById(id);
    }

    @Override
    public NoticePo getByTitleName(String title) {
        return dao.queryByTitleName(title);
    }

    @Override
    public void save(NoticePo entity) {
         dao.insert(entity);
    }

    @Override
    public void update(NoticePo entity) {
          dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);

    }

	@Override
	public List<NoticePo> getByNum(int num) {
		return dao.queryByNum(num);
	}
	
}