package com.yuntu.sale.product.po;

import com.yuntu.sale.base.BasePo;
import com.yuntu.sale.common.GrobalConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 分销组分销商Entity
 * @author zy
 * @version 2018-04-02
 */
public class SaleGroupEnterpriseVo extends BasePo implements Serializable {

	private static final long serialVersionUID = -5369865292337443182L;
	private Long id;            //id
	private Long childEnterpriseId;  //分销组分销企业id
	private String name;//公司名称
	private String contacterName;//公司负责人
	private String contacterPhone;//公司负责人手机
	private String email;//企业邮箱

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChildEnterpriseId() {
		return childEnterpriseId;
	}

	public void setChildEnterpriseId(Long childEnterpriseId) {
		this.childEnterpriseId = childEnterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterPhone() {
		return contacterPhone;
	}

	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}