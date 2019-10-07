package com.yuntu.sale.orders.po;


import java.io.Serializable;

/**
 * 重新打印记录表Entity
 * @author zy
 * @version 2018-04-23
 */
public class Reprint implements Serializable {

	private static final long serialVersionUID = -3865860934959973455L;
	private Long id;            //id
	private Long consumeLogId;		// consume_log_id
	private Long ordersId;		// orders_id
	private Integer shopId;		// shop_id
	private String shopName;		// shop_name
	private Long sproductId;		// sproduct_id
	private String productSortname;		// product_sortname
	private Long shareposUid;		// sharepos_uid
	private String cardpwd;		// cardpwd
	private Long machineid;		// machineid
	private Integer printtim;		// printtim
	
	public Reprint() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConsumeLogId() {
		return consumeLogId;
	}

	public void setConsumeLogId(Long consumeLogId) {
		this.consumeLogId = consumeLogId;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getSproductId() {
		return sproductId;
	}

	public void setSproductId(Long sproductId) {
		this.sproductId = sproductId;
	}

	public String getProductSortname() {
		return productSortname;
	}

	public void setProductSortname(String productSortname) {
		this.productSortname = productSortname;
	}

	public Long getShareposUid() {
		return shareposUid;
	}

	public void setShareposUid(Long shareposUid) {
		this.shareposUid = shareposUid;
	}

	public String getCardpwd() {
		return cardpwd;
	}

	public void setCardpwd(String cardpwd) {
		this.cardpwd = cardpwd;
	}

	public Long getMachineid() {
		return machineid;
	}

	public void setMachineid(Long machineid) {
		this.machineid = machineid;
	}

	public Integer getPrinttim() {
		return printtim;
	}

	public void setPrinttim(Integer printtim) {
		this.printtim = printtim;
	}
}