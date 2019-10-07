package com.yuntu.sale.ifconf.vo;

public class ThirdOrderNumVo {
	
	/** 供应商订单号 **/
	private String thirdno;
	
	/** 系统订单号 **/
	private String orderno;
	
	/** 总数 **/
	private Integer num=0;
	
	/** 已使用数 **/
	private Integer printnum=0;
	
	/** 已退款数 **/
	private Integer locknum=0;
	
	/** 可使用数 **/
	private Integer validnum=0;

	public String getThirdno() {
		return thirdno;
	}

	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
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

	public Integer getValidnum() {
		return validnum;
	}

	public void setValidnum(Integer validnum) {
		this.validnum = validnum;
	}

}
