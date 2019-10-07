package com.yuntu.sale.info.dao;

import com.yuntu.sale.info.po.NoticePo;
import com.yuntu.sale.info.po.NoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 公告Dao
 * @author snps
 * @date 2018年2月27日 下午2:29:38
 */
public interface NoticeDao {
    /*通过标题模糊查询公告*/

	List<NoticeVo> queryToTitle(@Param("title") String title);

	List<NoticePo> queryByTitle(@Param("title") String title);
	
	/**
	 * 获得指定数量的公告信息
	 * @param num 要获取的数量
	 * @return List<NoticePo>
	 */
	List<NoticePo> queryByNum(@Param("num") int num);

	/**
	 * 通过Id查询
	 * @return InfoScenicPo
	 */
	NoticePo queryById(Long id);
	/**
	 * 通过商品类别（唯一）查询	（使用：用于判断商品类别是否重复）
	 * @param title
	 * @return InfoScenicPo
	 */


	NoticePo queryByTitleName(String title);
	/**
	 * 插入一行新的数据
	 * @param entity
	 */


	void insert(NoticePo entity);

	/**
	 * 修改
	 * @param entity
	 */
	void update(NoticePo entity);
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
}