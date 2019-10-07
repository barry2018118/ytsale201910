package com.yuntu.sale.ifconf.vo;

/**
 * 供应商请求结果
 * @author Jack.jiang
 *
 */
public class ReqResutlVo {
	
	/** 是否请求成功 **/
	protected Boolean success;
	
	/** 描述 **/
	protected String msg;
	
	/** 错误状态码 **/
	protected String statusCode;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}
