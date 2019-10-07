package com.yuntu.sale.orders.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表Entity
 * @author zy
 * @version 2018-04-23
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = 1817548239948874825L;
	private Long id ; //id
	private String orderno;		// 订单号
	private Long user;		// 下单用户Id
	private String userName;		// 下单用户名称
	private Long enterpriseId;		// 企业Id
	private String enterpriseName;		// 企业名称
	private Long payApiId;		// 渠道接口Id
	private String payid;		// 渠道订单号
	private Long typeId;		// 门票类型Id
	private Long sproductId;		// 产品Id
	private String customerName;		// 取票人
	private String tel;		// 取票人手机（用于接收短信）
	private String idcard;		// 身份证
	private Integer consumeTime;		// 预订游玩日期
	private String consumeTimeTime;		// 预订游玩日期
	private BigDecimal unitPrice;		// 产品单价
	private BigDecimal price;		// 订单总价
	private Long apimsg;		// 供应商接口Id
	private String thirdno;		// 供应商订单Id
	private String tradeNo;		// 供应商码号
	private Integer paydate;		// 下单时间
	private String tuanname;		// 来源（PC，APP）
	private Integer num;		// 数量
	private Integer printnum;		// 核销数
	private Integer locknum;		// 已退款数
	private Integer clocknum;		// 冻结数（退款审核冻结）
	private String notes;		// 客户备注
	private Integer createdAt;		// 创建时间
	private String createdAttime;		// 创建时间
	private Integer status;		// 订单状态 （指标待定，后续提供）
	private Integer sendnum;		// sendnum
	private String route;		// route
	private String teamId;		// team_id
	private String guide;		// guide
	private String guideCard;		// guide_card
	private Long province;		// province
	private String apptoken;		// apptoken
	private Integer isChainmode;		// is_chainmode
	private String phoneProvince;		// phone_province
	private String phoneCity;		// phone_city
	private Integer isZbtimenum;		// is_zbtimenum
	private Integer saleMode;		// 销售方式(2:众包)
	private Integer isTag;		// 订单是否已经标注 0 否  1  是 
	private String isEmail;		// 电子邮箱
	private Integer version;		// 版本号
	private String statusName;     //订单状态
	private Integer sendtype;      //发码方式
	private String qrcode;		   //二维码地址
	
	public Orders() {
		this.payApiId = 0L;
		this.payid = "";
		this.typeId = 1L;//门票类型Id 暂时默认 1
		this.apimsg = 0L;
		this.thirdno = "";
		this.tradeNo = "";
		this.paydate = 0;
		this.printnum = 0;
		this.locknum = 0;
		this.clocknum = 0;
		this.createdAt = (int)(System.currentTimeMillis()/1000) ;
		this.status = 10;//订单状态  （指标待定，后续提供）
		this.sendnum = 0;
		this.route = "";
		this.teamId = "";
		this.guide = "";
		this.guideCard = "";
		this.province = 0L;
		this.apptoken = "";
		this.isChainmode = 0;
		this.phoneProvince = "";
		this.phoneCity = "";
		this.isZbtimenum = 0;
		this.saleMode = 2;//销售方式(2:众包)
		this.isTag = 0;//订单是否已经标注 0 否  1  是
		this.isEmail = "";
		this.version = 1;
		this.sendtype = 0;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Integer getSendtype() {
		return sendtype;
	}

	public void setSendtype(Integer sendtype) {
		this.sendtype = sendtype;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getCreatedAttime() {
		return createdAttime;
	}

	public void setCreatedAttime(String createdAttime) {
		this.createdAttime = createdAttime;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getConsumeTimeTime() {
		return consumeTimeTime;
	}

	public void setConsumeTimeTime(String consumeTimeTime) {
		this.consumeTimeTime = consumeTimeTime;
	}

	public Long getPayApiId() {
		return payApiId;
	}

	public void setPayApiId(Long payApiId) {
		this.payApiId = payApiId;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Integer getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Integer consumeTime) {
		this.consumeTime = consumeTime;
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

	public Long getApimsg() {
		return apimsg;
	}

	public void setApimsg(Long apimsg) {
		this.apimsg = apimsg;
	}

	public String getThirdno() {
		return thirdno;
	}

	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Integer getPaydate() {
		return paydate;
	}

	public void setPaydate(Integer paydate) {
		this.paydate = paydate;
	}

	public String getTuanname() {
		return tuanname;
	}

	public void setTuanname(String tuanname) {
		this.tuanname = tuanname;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPrintnum() {
		return printnum;
	}

	public void setPrintnum(Integer printnum) {
		this.printnum = printnum;
	}

	public Integer getLocknum() {
		return locknum;
	}

	public void setLocknum(Integer locknum) {
		this.locknum = locknum;
	}

	public Integer getClocknum() {
		return clocknum;
	}

	public void setClocknum(Integer clocknum) {
		this.clocknum = clocknum;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSendnum() {
		return sendnum;
	}

	public void setSendnum(Integer sendnum) {
		this.sendnum = sendnum;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getGuideCard() {
		return guideCard;
	}

	public void setGuideCard(String guideCard) {
		this.guideCard = guideCard;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public String getApptoken() {
		return apptoken;
	}

	public void setApptoken(String apptoken) {
		this.apptoken = apptoken;
	}

	public Integer getIsChainmode() {
		return isChainmode;
	}

	public void setIsChainmode(Integer isChainmode) {
		this.isChainmode = isChainmode;
	}

	public String getPhoneProvince() {
		return phoneProvince;
	}

	public void setPhoneProvince(String phoneProvince) {
		this.phoneProvince = phoneProvince;
	}

	public String getPhoneCity() {
		return phoneCity;
	}

	public void setPhoneCity(String phoneCity) {
		this.phoneCity = phoneCity;
	}

	public Integer getIsZbtimenum() {
		return isZbtimenum;
	}

	public void setIsZbtimenum(Integer isZbtimenum) {
		this.isZbtimenum = isZbtimenum;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public Integer getIsTag() {
		return isTag;
	}

	public void setIsTag(Integer isTag) {
		this.isTag = isTag;
	}

	public String getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"id=" + id +
				", orderno='" + orderno + '\'' +
				", user=" + user +
				", userName='" + userName + '\'' +
				", enterpriseId=" + enterpriseId +
				", enterpriseName='" + enterpriseName + '\'' +
				", payApiId=" + payApiId +
				", payid='" + payid + '\'' +
				", typeId=" + typeId +
				", sproductId=" + sproductId +
				", customerName='" + customerName + '\'' +
				", tel='" + tel + '\'' +
				", idcard='" + idcard + '\'' +
				", consumeTime=" + consumeTime +
				", unitPrice=" + unitPrice +
				", price=" + price +
				", apimsg=" + apimsg +
				", thirdno='" + thirdno + '\'' +
				", tradeNo='" + tradeNo + '\'' +
				", paydate=" + paydate +
				", tuanname='" + tuanname + '\'' +
				", num=" + num +
				", printnum=" + printnum +
				", locknum=" + locknum +
				", clocknum=" + clocknum +
				", notes='" + notes + '\'' +
				", createdAt=" + createdAt +
				", status=" + status +
				", sendnum=" + sendnum +
				", route='" + route + '\'' +
				", teamId='" + teamId + '\'' +
				", guide='" + guide + '\'' +
				", guideCard='" + guideCard + '\'' +
				", province=" + province +
				", apptoken='" + apptoken + '\'' +
				", isChainmode=" + isChainmode +
				", phoneProvince='" + phoneProvince + '\'' +
				", phoneCity='" + phoneCity + '\'' +
				", isZbtimenum=" + isZbtimenum +
				", saleMode=" + saleMode +
				", isTag=" + isTag +
				", isEmail='" + isEmail + '\'' +
				", version=" + version +
				'}';
	}
}