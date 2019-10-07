package com.yuntu.sale.info.service;

import java.util.List;

import com.coolshow.util.Page;
import com.yuntu.sale.info.po.NoticePo;
import com.yuntu.sale.info.po.NoticeVo;

/**
 * @Description 公告Service接口 
 * @author snps
 * @date 2018年2月27日 下午2:26:00
 */
public interface NoticeService {
   
	/*通过公告标题进行模糊查询
    * pager 分页对象
    * title 公告标体*/
	Page<NoticeVo> getByTitle(Page<NoticeVo> pager, String title);

	/**
	 * 通过Id查询
	 * @return NoticePo
	 */
	NoticePo getById(Long id) ;

	/**
	 * 通过公告标题（唯一）查询	（使用：用于公告标体是否重复）
	 * @param   title
	 * @return NoticePo
	 * TitleName 独特的名字
	 */
	NoticePo  getByTitleName(String title) ;
	/**
	 * 保存
	 * @param entity
	 */
	void save(NoticePo entity) ;
	/**
	 * 修改
	 * @param entity
	 */
	void update(NoticePo entity) ;
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id) ;

	/**
	 * 首页获取公告信息
	 * @param num 要获取的公告数量
	 * @return List<NoticeVo>
	 */
	List<NoticePo> getByNum(int num);
	
}