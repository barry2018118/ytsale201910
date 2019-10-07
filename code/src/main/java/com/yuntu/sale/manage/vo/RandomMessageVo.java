package com.yuntu.sale.manage.vo;

import java.io.Serializable;

/**
 * @Summary 随机字段串消息包装类
 * @Author snps
 * @Detail 包装类，用于封装数据字段，无业务意义
 * @Nonuse
 */
public class RandomMessageVo implements Serializable {

	private static final long serialVersionUID = -3100963403089200391L;
	
	/**
	 * 短信发送状态返回码：1：成功，2：失败
	 */
	private String flag;
	private String key;
	private String value;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}