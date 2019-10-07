package com.yuntu.sale.base;

import java.util.Random;

/**
 * @Summary 随机字符串公共类
 * @Author snps
 * @Detail
 * @Nonuse
 */
public class RandomStringUtils {
	private static final String S_ZERO = "0" ;
	
	/**
	 * 生成指定位长的随机数
	 * @param maxValue 随机数最大值
	 * @param bitLength 返回字符串长度
	 * @return String
	 */
	public static String getStringRandom(int maxValue, int bitLength) {
		StringBuffer sbuValue = new StringBuffer() ;
		int randomValue = new Random().nextInt(maxValue) ;
		
		if(randomValue == 0) {
			for(int i=0 ; i<bitLength; i++) {
				sbuValue.append(S_ZERO) ;
			}
			return sbuValue.toString() ;
		} else {
			int iTemp = 0 ;
			for(int i=bitLength-1; i>=0; i--) {
				if(randomValue >= (Math.pow(10, i))) {
					Number numberPow = Math.pow(10, i) ;
					iTemp = randomValue / numberPow.intValue() ;
					randomValue = randomValue % numberPow.intValue() ;
					sbuValue.append(String.valueOf(iTemp)) ;
				} else {
					sbuValue.append(S_ZERO) ;
				}
			}
			return sbuValue.toString() ;
		}
	}
	
	
	public static void main(String[] args) {
		for(int i=0; i<20; i++) {
			System.out.println(RandomStringUtils.getStringRandom(10000, 4));
		}
	}
}
