package com.yuntu.sale.base;

/**
 * @Description 生成编号功能类 
 * @author snps
 * @date 2018年4月19日 下午5:25:11
 */
public class GenerateNo {

	
	/**
	 * 获得景区编号
	 * @return String
	 */
	public static final String getScenicNo() {
		StringBuffer subNo = new StringBuffer("0");
		subNo.append(RandomStringUtils.getStringRandom(1000000000, 9));
		return subNo.toString();
	}
	
	/**
	 * 
	 * 获得商品编号
	 * @param productCategory
	 * @return 商品类型 + 当时时间戳 + 6位随机数字 
	 */
	public static final String getProductNo(String productCategory) {
		StringBuffer subNo = new StringBuffer(productCategory);
		subNo.append(System.currentTimeMillis());
		subNo.append(RandomStringUtils.getStringRandom(1000000, 6));
		return subNo.toString();
	}
	
	/**
	 * 获得订单编号
	 * @return String
	 */
	public static final String getOrderNo() {
		StringBuffer subNo = new StringBuffer("");
		subNo.append(System.currentTimeMillis());
		subNo.append(RandomStringUtils.getStringRandom(100000000, 8));
		return subNo.toString();
	}
	
	/**
	 * 获得电子码
	 * @return String
	 */
	public static final String getPwdCode() {
		StringBuffer subNo = new StringBuffer("00");
		subNo.append(RandomStringUtils.getStringRandom(100000000, 8));
		return subNo.toString();
	}
	
	
	/**
	 * MainTest
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0; i<500; i++) {
			// System.out.println("景区编号：" + GenerateNo.getScenicNo());
			// System.out.println("商品编号：" + GenerateNo.getProductNo("1"));
			// System.out.println("订单号：" + GenerateNo.getOrderNo());
			// System.out.println("电子码：" + GenerateNo.getPwdCode());
			System.out.println("insert into t_code_provide(card_pwd, status) values(" + GenerateNo.getPwdCode() + ", 5) ;");
		}
	}
	
}