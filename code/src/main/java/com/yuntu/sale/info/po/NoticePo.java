package com.yuntu.sale.info.po;

import java.io.Serializable;

import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 公告Po
 * @author snps
 * @date 2018年2月27日 下午2:44:04
 */
public class NoticePo extends BasePo implements Serializable {

	private static final long serialVersionUID = 2187895473998380170L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 公告类型（系统公告-0、产品公告、调价公告、其它）
	 */
	private Integer noticeType;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 是否置顶（1：是，0：否）
	 */
	private Integer isTop;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 排序号
	 */
	private Integer sortNo;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;
	
	/**
	 * 创建时间字符格式
	 */
	private String ctreateTimeStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCtreateTimeStr() {
		if(BaseUtil.isEmpty(this.getCreateTime())) {
			return "--";
		} else {
			return DateUtil.formatDateTime(this.getCreateTime());
		}
	}

	public void setCtreateTimeStr(String ctreateTimeStr) {
		this.ctreateTimeStr = ctreateTimeStr;
	}

	/********************************************
	 * Constructor
	 */
	public NoticePo() {
		this.noticeType = 0;
		this.isTop = GrobalConstant.I_INDEX_TOP_NO;
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
	}
	
}