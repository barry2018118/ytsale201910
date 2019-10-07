package com.yuntu.sale.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.coolshow.util.DateUtil;
import com.yuntu.sale.common.SmsManager;

/**
 * @Description 测试用的 
 * @author snps
 * @date 2018年3月13日 下午1:53:20
 */

public class TestAction  {
	public static void main(String[] args) throws IOException {
		Integer ie = -1;

		System.out.println(ie);

		System.out.println(DateUtil.formatDate(new Date()));

		int a = 10;
		System.out.println("a"+ (a - 1-2-3));
		String[] ids = {"1","2","3","4","5"};
		List<String> userIdsList = new ArrayList<String>(ids.length);
		for (int i = ids.length - 1; i >= 0; i--) {
			userIdsList.add(ids[i]);
		}
		System.out.println(userIdsList.toString());
		Iterator<String> userId = userIdsList.iterator();
		while (userId.hasNext()){
			System.out.println(Long.parseLong(userId.next()));
			if(userId.hasNext()){
				System.out.println(Long.parseLong(userId.next()));
			}
		}
		int c= (int) System.currentTimeMillis() /1000;
		int b= (int)( System.currentTimeMillis() /1000 );
		System.out.println(c);
		System.out.println(b);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date(c);
		Date date2 = new Date(System.currentTimeMillis());
		String res = simpleDateFormat.format(date1);
		String eed = simpleDateFormat.format(date2);

		System.out.println("==================");

		System.out.println(res);
		System.out.println(eed);

		System.out.println("==================");
	}

	/**
	 * 实名制短信发送
	 */
	public void testSendOrderMessage() {
		System.out.println(111);
    	String content = "【云端行】{name}({idCard})，您好。您所购买的“{product}”产品，购买数量：{num}。产品验证码为：{code}。请使用此验证码进行消费！严防诈骗！不要告诉任何人！";
    	content = content.replace("{name}", "游客");
    	content = content.replace("{idCard}", "370202198302011412");
		content = content.replace("{product}", "故宫门票");
		content = content.replace("{num}", "1张");
		content = content.replace("{code}", "123456");
		SmsManager.sendMessage("15011230721", content);
		System.out.println(content);
		System.out.println(222);
	}
	
}