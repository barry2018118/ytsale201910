package com.yuntu.sale.capital.po;

import java.io.Serializable;
import java.util.Date;

import com.yuntu.sale.common.GrobalConstant;

/**
 * @Description 企业-银行卡Po
 * @Table t_enterprise_bank_card
 * @author snps
 * @date 2018年3月8日 上午10:00:40
 */
public class EnterpriseBankCardPo implements Serializable {

	private static final long serialVersionUID = -3084635096722381982L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 企业Id
	 */
	private Long enterpriseId;

	/**
	 * 开户银行
	 */
	private String bank;
	
	/**
	 * 开户地址
	 */
	private String address;
	
	/**
	 * 银行卡号
	 */
	private String cardNo;

	/**
	 * 持卡人
	 */
	private String cardMaster;

	/**
	 * 持卡人身份证号
	 */
	private String cardMasterNo;

	/**
	 * 持卡人手机号
	 */
	private String cardMasterPhone;

	/**
	 * 备注信息
	 */
	private String remark;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 排序号
	 */
	private Integer sortNo;

	/**
	 * 创建人Id
	 */
	private Long createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardMaster() {
		return cardMaster;
	}

	public void setCardMaster(String cardMaster) {
		this.cardMaster = cardMaster;
	}

	public String getCardMasterNo() {
		return cardMasterNo;
	}

	public void setCardMasterNo(String cardMasterNo) {
		this.cardMasterNo = cardMasterNo;
	}

	public String getCardMasterPhone() {
		return cardMasterPhone;
	}

	public void setCardMasterPhone(String cardMasterPhone) {
		this.cardMasterPhone = cardMasterPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	
	/*************************************************
	 * Constructor
	 */
	public EnterpriseBankCardPo() {
		this.isDelete = GrobalConstant.I_INDEX_DELETE_NO;
		this.sortNo = GrobalConstant.I_NUM_1;
		this.createTime = new Date();
	}
	
	public EnterpriseBankCardPo(Long enterpriseId) {
		new EnterpriseBankCardPo();
		this.enterpriseId = enterpriseId;
	}
	
}