package com.yuntu.sale.orders.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yuntu.sale.base.GenerateNo;
import com.yuntu.sale.orders.po.CodeProvide;
import com.yuntu.sale.orders.service.CodeProvideService;

/**
 * @Description 生成码Service类 
 * @author snps
 * @date 2018年5月10日 下午4:00:43
 */
@Service
public class CreateCodeServiceImpl {
	
	/**
	 * 码生成数量
	 */
	@Value("${code.number}")
	private String codeNumber;
	
	@Resource
	private CodeProvideService codeProvideService;

	
	/**
	 * 更新到回款日期的数据
	 */
	public void saveCode() {
		System.out.println("Create code Number is : " + codeNumber);
		
		for(int i=0; i<20; i++) {
			String code = GenerateNo.getPwdCode();
			int status = codeProvideService.getCountByCodeAndStatus5(code);
			if(status == 0) {
				CodeProvide codeProvide = new CodeProvide();
				codeProvide.setCardPwd(code);
				codeProvide.setStatus(1);
				codeProvideService.save(codeProvide);
			}
			
			System.out.println(code + " -- " + codeProvideService.getCountByCodeAndStatus5(code));
			
		}
	}
	
}