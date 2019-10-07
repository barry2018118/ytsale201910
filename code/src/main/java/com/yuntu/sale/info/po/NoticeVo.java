package com.yuntu.sale.info.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 公告Po
 * @author snps
 * @date 2018年2月27日 下午2:44:04
 */
public class NoticeVo implements Serializable {

	private static final long serialVersionUID = 1321653240204557812L;
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
	 * 是否置顶（1：是，0：否）
	 */
	private Integer isTop;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	/**
	 * 创建者名称
	 */
	private String createName;

	/**
	 * 创建人Id
	 */
	private Long createId;


	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/********************************************
	 * Constructor
	 */
	public NoticeVo() {
		this.noticeType = 0;
		this.isTop = GrobalConstant.I_INDEX_TOP_NO;
	}
	
}