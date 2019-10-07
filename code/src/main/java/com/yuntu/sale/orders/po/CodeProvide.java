package com.yuntu.sale.orders.po;

import java.io.Serializable;

/**
 * 码库表Entity
 * @author zy
 * @version 2018-04-23
 */
public class CodeProvide implements Serializable {

    private static final long serialVersionUID = 5094972445452314415L;
    private Long id;
	private String cardPwd;		// 码
	private Integer status;		// 状态（5可用、1已使用）

    public CodeProvide() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}