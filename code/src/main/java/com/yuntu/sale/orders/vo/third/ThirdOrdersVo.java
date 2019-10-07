package com.yuntu.sale.orders.vo.third;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 第三方下单Vo 
 * @author snps
 * @date 2018年5月22日 下午1:31:48
 */
public class ThirdOrdersVo implements Serializable {

	private static final long serialVersionUID = -7854224909681031983L;

	/**
	 * 下单企业Id
	 */
	private Long enterpriseId;
	
	/**
	 * 下单购买商品Id
	 */
	private Long sproductId;
	
	/**
	 * 下单购买商品数量
	 */
	private Integer orderNum;
	
	/**
	 * （OTA）销售单价
	 */
	private BigDecimal unitPrice;
	
	/**
	 * （OTA）销售总价
	 */
	private BigDecimal price;
	
	/**
	 * 预计游玩日期
	 */
	private Long consumeTim;
	
	/**
	 * 取票人-姓名
	 */
	private String buyUser;
	
	/**
	 * 取票人-电话
	 */
	private String tel;
	
	/**
	 * 取票人-身份证号
	 */
	private String idcard;
	
	/**
	 * （OTA）接口配置Id
	 */
	private Long payApiId;
	
	/**
	 * 来源
	 */
	private String tuanname;
	
	/**
	 * （OTA）订单号
	 */
	private String payid;
	
	/**
	 * 谁来发码（发短信）（1：系统不发码，2：系统发码）
	 */
	private Integer sendtype;
	
	/**
	 * 订单备注
	 */
	private String note;
	
	/**
	 * 实名制信息
	 */
	private List<TouristVo> idcards;
	

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getConsumeTim() {
		return consumeTim;
	}

	public void setConsumeTim(Long consumeTim) {
		this.consumeTim = consumeTim;
	}

	public String getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(String buyUser) {
		this.buyUser = buyUser;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Long getPayApiId() {
		return payApiId;
	}

	public void setPayApiId(Long payApiId) {
		this.payApiId = payApiId;
	}

	public String getTuanname() {
		return tuanname;
	}

	public void setTuanname(String tuanname) {
		this.tuanname = tuanname;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public Integer getSendtype() {
		return sendtype;
	}

	public void setSendtype(Integer sendtype) {
		this.sendtype = sendtype;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TouristVo> getIdcards() {
		return idcards;
	}

	public void setIdcards(List<TouristVo> idcards) {
		this.idcards = idcards;
	}

	
	/******************************************************************
	 * Constructor
	 * 		payApiId 	对应  t_orders.pay_api_id
	 * 		tuanname 	对应  t_orders.tuanname
	 * 		payid 	  	对应  t_orders.payid
	 *      sendtype：	1：系统不发码，2：系统发码
	 */
	public ThirdOrdersVo() {
		this.idcards = new ArrayList<TouristVo>();
	}

	@Override
	public String toString() {
		return "ThirdOrdersVo [enterpriseId=" + enterpriseId + ", sproductId=" + sproductId + ", orderNum=" + orderNum
				+ ", unitPrice=" + unitPrice + ", price=" + price + ", consumeTim=" + consumeTim + ", buyUser="
				+ buyUser + ", tel=" + tel + ", idcard=" + idcard + ", payApiId=" + payApiId + ", tuanname=" + tuanname
				+ ", payid=" + payid + ", sendtype=" + sendtype + ", note=" + note + ", idcards=" + idcards + "]";
	}
	
}