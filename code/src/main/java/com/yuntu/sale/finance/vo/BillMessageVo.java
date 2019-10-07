package com.yuntu.sale.finance.vo;

import java.io.Serializable;

/**
 * @Description 账单返回信息Vo
 * @author snps
 * @date 2017-4-5 上午10:58:48
 */
public class BillMessageVo implements Serializable {

	private static final long serialVersionUID = -3228076093683043514L;

	/**
	 * 返回码（0：未生成、1：正在导出、2：导出失败、5：已导出）
	 */
	private String code;

	/**
	 * 返回提示信息
	 */
	private String info;

	/**
	 * 文件路径
	 */
	private String filepath;
	
	/**
	 * 文件名
	 */
	private String fileName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/****************************************
	 * Constructor
	 */
	public BillMessageVo() {
	}
	
	public BillMessageVo(String code) {
		this.code = code;
	}

	public BillMessageVo(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public BillMessageVo(String code, String info, String filepath) {
		this.code = code;
		this.info = info;
		this.filepath = filepath;
	}
	
	public BillMessageVo(String code, String info, String filepath,String fileName) {
		this.code = code;
		this.info = info;
		this.filepath = filepath;
		this.fileName = fileName;
	}
	
}