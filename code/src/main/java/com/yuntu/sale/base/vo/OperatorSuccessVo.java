package com.yuntu.sale.base.vo;

import java.io.Serializable;
import java.util.List;

import com.yuntu.sale.orders.vo.RealCustomerMessageVo;

/**
 * @Description 操作信息Vo
 * @author snps
 * @date 2018年2月22日 上午10:03:57
 */
public class OperatorSuccessVo extends OperatorMessageVo implements Serializable {

	private static final long serialVersionUID = 4282286533583105804L;

	private Integer type ;

	private List<RealCustomerMessageVo> codeList;

	private String code;
	/*************************************
	 * Constructor
	 */
	public OperatorSuccessVo() {
		this.setFlag(Boolean.TRUE);
	}

	public OperatorSuccessVo(Object message) {
		this.setFlag(Boolean.TRUE);
		this.setMessage(message);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<RealCustomerMessageVo> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<RealCustomerMessageVo> codeList) {
		this.codeList = codeList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}