package com.yuntu.sale.orders.po;

import com.yuntu.sale.base.BasePo;
import java.io.Serializable;
/**
 * 码信息表Entity
 * @author zy
 * @version 2018-04-23
 */
public class Code implements Serializable {

	private static final long serialVersionUID = -6176775170279775608L;
	private Long id;
	private Long ordersId;		// 订单Id
	private String cardPwd;		// 电子码
	private String codeName;		// 门票名称
	private Long sproductId;		// 产品Id
	private Long enterpriseId;		// 所属企业Id
	private Long shopId;		// 商户Id
	private Integer num;		// 数量
	private Integer starttim;		// 开始验证时间
	private String starttimtime;
	private Integer endtim;		// 验证时间截止
	private String endtimtime;		// 验证时间截止
	private Integer printnum;		// 已核销数
	private Integer locknum;		// 已退款数
	private Integer clocknum;		// 冻结数（退款申请冻结）
	private String tuanname;		// 下单来源（PC、APP、第三方系统）
	private String realName;		// 实名制信息
	private Integer status;		// 状态：同订单状态
	private Integer updatedAt;		// 更新时间
	private String updataAttime;
	private Integer version;		// 版本号

	private String statusName;     //订单状态

	public Code() {
		this.printnum=0;
		this.locknum = 0;
		this.clocknum = 0;
		this.tuanname= "PC";
		this.updatedAt=(int)(System.currentTimeMillis()/1000);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStarttim() {
		return starttim;
	}

	public void setStarttim(Integer starttim) {
		this.starttim = starttim;
	}

	public String getStarttimtime() {
		return starttimtime;
	}

	public void setStarttimtime(String starttimtime) {
		this.starttimtime = starttimtime;
	}

	public Integer getEndtim() {
		return endtim;
	}

	public void setEndtim(Integer endtim) {
		this.endtim = endtim;
	}

	public String getEndtimtime() {
		return endtimtime;
	}

	public void setEndtimtime(String endtimtime) {
		this.endtimtime = endtimtime;
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

	public String getTuanname() {
		return tuanname;
	}

	public void setTuanname(String tuanname) {
		this.tuanname = tuanname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdataAttime() {
		return updataAttime;
	}

	public void setUpdataAttime(String updataAttime) {
		this.updataAttime = updataAttime;
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
		return "Code{" +
				"id=" + id +
				", ordersId=" + ordersId +
				", cardPwd='" + cardPwd + '\'' +
				", codeName='" + codeName + '\'' +
				", sproductId=" + sproductId +
				", enterpriseId=" + enterpriseId +
				", shopId=" + shopId +
				", num=" + num +
				", starttim=" + starttim +
				", starttimtime='" + starttimtime + '\'' +
				", endtim=" + endtim +
				", endtimtime='" + endtimtime + '\'' +
				", printnum=" + printnum +
				", locknum=" + locknum +
				", clocknum=" + clocknum +
				", tuanname='" + tuanname + '\'' +
				", realName='" + realName + '\'' +
				", status=" + status +
				", updatedAt=" + updatedAt +
				", updataAttime='" + updataAttime + '\'' +
				", version=" + version +
				'}';
	}
}