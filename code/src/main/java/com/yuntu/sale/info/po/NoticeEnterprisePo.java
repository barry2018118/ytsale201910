package com.yuntu.sale.info.po;

import java.io.Serializable;

import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 公告-企业关系
 * @Table info_notice_enterprise
 * @author snps
 * @date 2018年2月27日 下午2:48:08
 */
public class NoticeEnterprisePo implements Serializable {

	private static final long serialVersionUID = -1130495242883730320L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 公告Id
	 */
	private Long noticeId;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	/**
	 * 是否已读（1：是，0：否）
	 */
	private Integer isRead;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
	/*********************************************************
	 * Constructor
	 */
	public NoticeEnterprisePo() {
		this.isRead = GrobalConstant.I_INDEX_READ_NO;
	}
	
	public NoticeEnterprisePo(Long noticeId, Long enterpriseId) {
		this.noticeId = noticeId;
		this.enterpriseId = enterpriseId;
		this.isRead = GrobalConstant.I_INDEX_READ_NO;
	}

}