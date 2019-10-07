package com.yuntu.sale.ifconf.vo;

/**
 * 供应商请求下单结果
 * @author Jack.jiang
 *
 */
public class OrderResutlVo extends ReqResutlVo{
	
	/** 第三方码号 **/
	private String code;
	
	/** 二维码地址 **/
	private String qrcode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
}
