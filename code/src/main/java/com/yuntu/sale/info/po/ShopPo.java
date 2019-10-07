package com.yuntu.sale.info.po;

import java.io.Serializable;

/**
 * @Description 商户Po
 * @author snps
 * @date 2018年2月27日 下午2:37:13
 */
public class ShopPo implements Serializable {

	private static final long serialVersionUID = 6634233463060574728L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * 景区名称
	 */
	private String name;

	/**
	 * 景区地址
	 */
	private String address;

	/**
	 * 星级（0，1，2，3，4，5）
	 */
	private Integer level;

	/**
	 * 景区电话
	 */
	private String tel;

	/**
	 * 景区图片
	 */
	private String pic;

	/**
	 * 省Id
	 */
	private Long provinceId;

	/**
	 * 市Id
	 */
	private Long cityId;

	/**
	 * 是否删除（1：是，0：否）
	 */
	private Integer isDelete;

	/**
	 * 排序号
	 */
	private Integer sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

}